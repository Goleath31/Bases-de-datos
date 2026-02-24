/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.DTOs.DetalleDTO;
import negocio.DTOs.DetallePedidoDTO;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.DTOs.PedidoNuevoDTO;
import persistencia.conexion.IConexionBD;
import persistencia.dominio.Cupon;
import persistencia.dominio.DetallePedido;
import persistencia.dominio.Pedido;
import persistencia.dominio.PedidoExpress;
import persistencia.dominio.PedidoProgramado;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author golea
 */
public class PedidoDAO implements IPedidoDAO {

    private final IConexionBD conexionBD;
    private static final Logger LOG = Logger.getLogger(PedidoDAO.class.getName());

    public PedidoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public int contarPedidosActivos(int idCliente) throws PersistenciaException {
        String sql = "SELECT COUNT(*) FROM Pedido WHERE id_cliente = ? AND estado NOT IN ('Entregado', 'Cancelado', 'No Reclamado')";
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al contar pedidos", e);
            throw new PersistenciaException("No se pudo validar el límite de pedidos.");
        }
        return 0;
    }

    public void agregarPedido(PedidoNuevoDTO pedidoDTO) throws PersistenciaException {
        String sqlPedido = "INSERT INTO Pedido (id_cliente, monto_total, tipo_pedido) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO Detalle_Pedido (id_pedido, id_producto, cantidad, precio_unitario, notas_personalizadas) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = this.conexionBD.crearConexion()) {
            conexion.setAutoCommit(false);

            try (PreparedStatement psP = conexion.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                psP.setInt(1, pedidoDTO.getIdCliente());
                psP.setDouble(2, pedidoDTO.getMontoTotal());
                psP.setString(3, pedidoDTO.getTipoPedido());
                psP.executeUpdate();

                ResultSet rs = psP.getGeneratedKeys();
                if (rs.next()) {
                    int idPedido = rs.getInt(1);

                    try (PreparedStatement psD = conexion.prepareStatement(sqlDetalle)) {
                        for (DetalleDTO det : pedidoDTO.getDetalles()) {
                            psD.setInt(1, idPedido);
                            psD.setInt(2, det.getIdProducto());
                            psD.setInt(3, det.getCantidad());
                            psD.setDouble(4, det.getPrecioVenta());
                            psD.setString(5, det.getNotas());
                            psD.addBatch();
                        }
                        psD.executeBatch();
                    }
                }
                conexion.commit();
            } catch (SQLException e) {
                conexion.rollback();
                throw new PersistenciaException("Error al registrar el pedido y sus detalles", e);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error de conexión", e);
        }
    }

    @Override
    public void actualizarAEntregadoConPago(int idPedido, String metodoPago) throws PersistenciaException {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            conn.setAutoCommit(false);

            String estadoAnterior = obtenerEstadoPedido(idPedido);

            String sqlUpdate = "UPDATE Pedido SET estado = 'Entregado' WHERE id_pedido = ?";
            try (PreparedStatement ps1 = conn.prepareStatement(sqlUpdate)) {
                ps1.setInt(1, idPedido);
                ps1.executeUpdate();
            }

            String sqlHistorial = "INSERT INTO Historial_Estado (id_pedido, estado_anterior, estado_nuevo, fecha_hora_cambio) VALUES (?, ?, 'Entregado', NOW())";
            try (PreparedStatement ps2 = conn.prepareStatement(sqlHistorial)) {
                ps2.setInt(1, idPedido);
                ps2.setString(2, estadoAnterior);
                ps2.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                }
            }
            throw new PersistenciaException("Fallo en BD: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public boolean verificarPin(int idPedido, String pin) throws PersistenciaException {
        String sql = "SELECT COUNT(*) FROM Pedido_Express WHERE id_pedido = ? AND pin_seguridad = ?";
        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al validar PIN");
        }
    }

    @Override
    public String obtenerEstadoPedido(int idPedido) throws PersistenciaException {
        String sql = "SELECT estado FROM Pedido WHERE id_pedido = ?";
        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("estado");
            }
            return "";
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar estado");
        }
    }

    @Override
    public List<PedidoEntregaDTO> buscarPedidos(String filtro) throws PersistenciaException {
        List<PedidoEntregaDTO> lista = new ArrayList<>();

        String sql = "SELECT p.id_pedido, "
                + "CASE WHEN pp.id_cliente IS NOT NULL THEN CONCAT(c.nombre, ' ', c.apellido_paterno, ' ', c.apellido_materno) "
                + "ELSE 'Venta Express / Anónimo' END AS nombre_cliente, "
                + "CASE WHEN pp.id_pedido IS NOT NULL THEN 'Programado' "
                + "WHEN pe.id_pedido IS NOT NULL THEN 'Express' ELSE 'General' END AS tipo_pedido, "
                + "p.total AS monto_total, p.estado "
                + "FROM Pedido p "
                + "LEFT JOIN Pedido_Programado pp ON p.id_pedido = pp.id_pedido "
                + "LEFT JOIN Cliente c ON pp.id_cliente = c.id_cliente "
                + "LEFT JOIN Telefono t ON c.id_cliente = t.id_cliente "
                + // Union con telefonos
                "LEFT JOIN Pedido_Express pe ON p.id_pedido = pe.id_pedido "
                + "WHERE (pe.folio LIKE ? "
                + // Filtro 1: Folio Express
                "OR t.numero LIKE ? "
                + // Filtro 2: Teléfono Cliente
                "OR c.nombre LIKE ? "
                + // Filtro 3: Nombre Cliente
                "OR CAST(p.id_pedido AS CHAR) LIKE ?) "
                + // Filtro 4: ID Pedido
                "AND p.estado IN ('Listo', 'Pendiente') "
                + "GROUP BY p.id_pedido";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String p = "%" + filtro + "%";
            ps.setString(1, p);
            ps.setString(2, p);
            ps.setString(3, p);
            ps.setString(4, p);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new PedidoEntregaDTO(
                        rs.getInt("id_pedido"),
                        rs.getString("nombre_cliente"),
                        rs.getString("tipo_pedido"),
                        rs.getFloat("monto_total"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error en la búsqueda avanzada: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Cupon> obtenerCuponesVigentes() throws PersistenciaException {
        List<Cupon> lista = new ArrayList<>();
        String sql = "SELECT id_cupon, codigo, descuento FROM Cupon WHERE fecha_vencimiento >= CURDATE()";
        try (Connection conn = conexionBD.crearConexion(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cupon(rs.getInt("id_cupon"), rs.getString("codigo"), rs.getInt("descuento")));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al cargar cupones");
        }
        return lista;
    }

    public List<PedidoEntregaDTO> listarPedidosPreparacion() throws PersistenciaException {
        List<PedidoEntregaDTO> lista = new ArrayList<>();
        String sql = "SELECT p.id_pedido AS 'ID', "
                + "CASE "
                + "    WHEN pe.id_pedido IS NOT NULL THEN (SELECT numero FROM Telefono WHERE id_cliente = p.id_cliente LIMIT 1) "
                + "    WHEN pp.id_pedido IS NOT NULL THEN CONCAT(c.nombre, ' ', c.apellido_paterno) "
                + "    ELSE 'General' "
                + "END AS 'Identificador', "
                + "CASE "
                + "    WHEN pp.id_pedido IS NOT NULL THEN 'Programado' "
                + "    WHEN pe.id_pedido IS NOT NULL THEN 'Express' "
                + "    ELSE 'Venta Directa' "
                + "END AS 'Tipo', "
                + "p.monto_total, p.estado AS 'Estado' "
                + "FROM Pedido p "
                + "LEFT JOIN Pedido_Programado pp ON p.id_pedido = pp.id_pedido "
                + "LEFT JOIN Pedido_Express pe ON p.id_pedido = pe.id_pedido "
                + "LEFT JOIN Cliente c ON pp.id_cliente = c.id_cliente "
                + "WHERE p.estado IN ('Pendiente', 'En Preparacion') "
                + "ORDER BY p.id_pedido DESC";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new PedidoEntregaDTO(
                        rs.getInt("ID"),
                        rs.getString("Identificador"),
                        rs.getString("Tipo"),
                        rs.getFloat("monto_total"),
                        rs.getString("Estado")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar pedidos: " + e.getMessage());
        }
    }

    @Override
    public List<PedidoEntregaDTO> obtenerPedidosPreparacion() throws PersistenciaException {
        List<PedidoEntregaDTO> lista = new ArrayList<>();
        String sql = "SELECT p.id_pedido, "
                + "CASE WHEN pe.id_pedido IS NOT NULL THEN COALESCE(pe.folio, 'Express s/n') "
                + "WHEN pp.id_pedido IS NOT NULL THEN CONCAT(c.nombre, ' ', c.apellido_paterno) "
                + "ELSE 'General' END AS identificador, "
                + "CASE WHEN pp.id_pedido IS NOT NULL THEN 'Programado' "
                + "WHEN pe.id_pedido IS NOT NULL THEN 'Express' "
                + "ELSE 'Venta Directa' END AS tipo, "
                + "p.estado "
                + "FROM Pedido p "
                + "LEFT JOIN Pedido_Programado pp ON p.id_pedido = pp.id_pedido "
                + "LEFT JOIN Pedido_Express pe ON p.id_pedido = pe.id_pedido "
                + "LEFT JOIN Cliente c ON pp.id_cliente = c.id_cliente "
                + "ORDER BY p.id_pedido DESC";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                lista.add(new PedidoEntregaDTO(
                        rs.getInt("id_pedido"),
                        rs.getString("identificador"),
                        rs.getString("tipo"),
                        0,
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al cargar pedidos de preparación: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizarEstadoPedido(int idPedido, String nuevoEstado) throws PersistenciaException {
        String sql = "UPDATE Pedido SET estado = ? WHERE id_pedido = ?";
        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al cambiar estado: " + e.getMessage());
        }

    }

    @Override
    public void pasarAListo(int idPedido) throws PersistenciaException {
        String sql = "UPDATE Pedido SET estado = 'Listo' WHERE id_pedido = ? AND estado = 'Pendiente'";
        ejecutarUpdate(sql, idPedido);
    }

    @Override
    public void pasarANoEntregado(int idPedido) throws PersistenciaException {
        String sqlUpdate = "UPDATE Pedido SET estado = 'No Entregado' WHERE id_pedido = ? AND estado = 'Listo'";
        String sqlHistorial = "INSERT INTO Historial_Estado (id_pedido, estado_anterior, estado_nuevo) VALUES (?, 'Listo', 'No Entregado')";

        try (Connection conn = conexionBD.crearConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(sqlUpdate); PreparedStatement ps2 = conn.prepareStatement(sqlHistorial)) {

                ps1.setInt(1, idPedido);
                ps1.executeUpdate();

                ps2.setInt(1, idPedido);
                ps2.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al pasar a No Entregado: " + e.getMessage());
        }
    }

    @Override
    public void pasarACancelado(int idPedido) throws PersistenciaException {
        String sql = "UPDATE Pedido SET estado = 'Cancelado' WHERE id_pedido = ? AND estado = 'No Entregado'";
        ejecutarUpdate(sql, idPedido);
    }

    private void ejecutarUpdate(String sql, int idPedido) throws PersistenciaException {
        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error de base de datos: " + e.getMessage());
        }
    }

    @Override
    public void agregarPedidoProgramado(PedidoProgramado pedidoProgramado, List<DetallePedido> detalles) throws PersistenciaException {
        //(id_empleado, id_cliente, id_cupon)
        String query
                = """
                {CALL sp_crear_pedido_programado(?, ?, ?, ?)}
                """;
        try (Connection conn = conexionBD.crearConexion(); CallableStatement cs = conn.prepareCall(query)) {
            conn.setAutoCommit(false);

            //AGREGAR VALIDACIONES DE DATOS
            cs.setNull(1, java.sql.Types.INTEGER);
            cs.setInt(2, pedidoProgramado.getIdCliente());

            if (pedidoProgramado.getIdCupon() > 0) {
                cs.setInt(3, pedidoProgramado.getIdCupon());
            } else {
                cs.setNull(3, java.sql.Types.INTEGER);
            }

            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            cs.execute();

            int idGenerado = cs.getInt(4);

            String queryDetalle = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement psDetalle = conn.prepareStatement(queryDetalle);

            for (DetallePedido item : detalles) {
                psDetalle.setInt(1, idGenerado);
                psDetalle.setInt(2, item.getIdProducto());
                psDetalle.setInt(3, item.getCantidad());
                psDetalle.addBatch();
            }
            psDetalle.executeBatch();

            conn.commit();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", e);
            throw new PersistenciaException("Error al conectar con la base de datos: " + e.getMessage());
        }

    }

    @Override
    public void agregarPedidoExpress(PedidoExpress pedidoExpress, List<DetallePedido> listaDetallePedido) throws PersistenciaException {
        //(id_empleado, folio, pin_seguridad)
        String query = "{CALL sp_crear_pedido_express(?, ?, ?, ?)}";
        //AGREGAR VALIDACIONES DE DATOS
        try (Connection conn = conexionBD.crearConexion(); CallableStatement cs = conn.prepareCall(query)) {
            conn.setAutoCommit(false);

            cs.setNull(1, java.sql.Types.INTEGER);
            cs.setString(2, pedidoExpress.getFolio());
            cs.setString(3, String.valueOf(pedidoExpress.getPinSeguridad()));
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            cs.execute();

            int idPedidoGenerado = cs.getInt(4);

            String sqlDetalle = "INSERT INTO Detalle_Pedido (id_pedido, id_producto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);

            for (DetallePedido item : listaDetallePedido) {
                psDetalle.setInt(1, idPedidoGenerado);
                psDetalle.setInt(2, item.getIdProducto());
                psDetalle.setInt(3, item.getCantidad());
                psDetalle.addBatch();
            }
            psDetalle.executeBatch();

            conn.commit();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", e);
            throw new PersistenciaException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    @Override
    public List<PedidoEntregaDTO> buscarPedidosAvanzado(String filtro) throws PersistenciaException {
        List<PedidoEntregaDTO> lista = new ArrayList<>();
        String sql = "{CALL BuscarPedidoTabla(?)}";
        try (Connection conn = conexionBD.crearConexion(); CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, filtro);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    // Mapeamos los alias definidos en tu procedimiento SQL
                    lista.add(new PedidoEntregaDTO(
                            rs.getInt("ID"),
                            rs.getString("Nombre/Folio"),
                            rs.getString("Tipo de pedido"),
                            0, // El monto no se solicita en este SP, se puede poner 0 o null
                            rs.getString("Estado")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al buscar pedidos: " + e.getMessage());
        }
        return lista;
    }

}
