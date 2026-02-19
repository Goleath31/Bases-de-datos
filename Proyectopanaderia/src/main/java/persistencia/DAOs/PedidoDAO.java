/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.DTOs.DetalleDTO;
import negocio.DTOs.DetallePedidoDTO;
import negocio.DTOs.PedidoNuevoDTO;
import persistencia.conexion.IConexionBD;
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
}
