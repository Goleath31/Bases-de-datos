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
import persistencia.conexion.IConexionBD;
import persistencia.dominio.Producto;
import persistencia.excepciones.PersistenciaException;

/**
 * Clase de Acceso a Datos (DAO) para la gestión de Productos. Implementa
 * operaciones para el mantenimiento del catálogo, incluyendo búsquedas
 * avanzadas mediante procedimientos almacenados.
 *
 * * @version 1.0
 */
public class ProductoDAO implements IProductoDAO {

    private final IConexionBD conexionBD;
    private static final Logger LOG = Logger.getLogger(ProductoDAO.class.getName());

    public ProductoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Recupera el catálogo completo de productos registrados, sin importar su
     * estado.
     *
     * * @return Lista de todos los objetos {@link Producto}.
     * @throws PersistenciaException Si ocurre un error al consultar la tabla
     * Producto.
     */
    @Override
    public List<Producto> obtenerTodosLosProductos() throws PersistenciaException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, tipo, precio, estado, descripcion FROM Producto";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setTipo(rs.getString("tipo"));
                p.setPrecio(rs.getFloat("precio"));
                p.setEstado(rs.getString("estado"));
                p.setDescripcion(rs.getString("descripcion"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", e);
            throw new PersistenciaException("Error al consultar el catálogo de productos: " + e.getMessage());
        }

    }

    /**
     * Actualiza la información técnica y comercial de un producto existente.
     *
     * * @param producto Objeto conteniendo el ID del producto y sus nuevos
     * valores (nombre, tipo, precio, etc.).
     * @throws PersistenciaException Si ocurre un error en la sentencia UPDATE.
     */
    @Override
    public void actualizarProducto(Producto producto) throws PersistenciaException {
        String sql = "UPDATE Producto SET nombre=?, tipo=?, descripcion=?, precio=?, estado=? WHERE id_producto=?";
        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getDescripcion());
            ps.setFloat(4, producto.getPrecio());
            ps.setString(5, producto.getEstado());
            ps.setInt(6, producto.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar producto: " + e.getMessage());
        }
    }

    /**
     * Registra un nuevo producto en el catálogo del sistema.
     *
     * * @param producto Objeto con los datos del nuevo producto a insertar.
     * @throws PersistenciaException Si la inserción falla por restricciones de
     * base de datos.
     */
    @Override
    public void agregarProducto(Producto producto) throws PersistenciaException {
        String sql = "INSERT INTO Producto (nombre, tipo, descripcion, precio, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getDescripcion());
            ps.setFloat(4, producto.getPrecio());
            ps.setString(5, producto.getEstado());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al insertar el producto: " + e.getMessage());
        }
    }

    /**
     * Realiza una búsqueda avanzada de productos utilizando el procedimiento
     * almacenado {@code BuscarProductoCompleto}.
     *
     * * @param filtro Cadena de texto para filtrar por diversos criterios del
     * producto.
     * @return Lista de productos que coinciden con el criterio de búsqueda.
     * @throws PersistenciaException Si el procedimiento almacenado falla o no
     * existe.
     */
    @Override
    public List<Producto> buscarProductos(String filtro) throws PersistenciaException {
        List<Producto> lista = new ArrayList<>();
        String sql = "{CALL BuscarProductoCompleto(?)}";

        try (Connection conn = conexionBD.crearConexion(); CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, filtro);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getInt("id_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setTipo(rs.getString("tipo"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setPrecio(rs.getFloat("precio"));
                    p.setEstado(rs.getString("estado"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al ejecutar búsqueda avanzada: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Obtiene únicamente los productos que tienen el estado 'Disponible'. Útil
     * para mostrar el catálogo a clientes o en la toma de pedidos.
     *
     * * @return Lista de productos activos/disponibles.
     * @throws PersistenciaException Si no se puede acceder al catálogo.
     */
    @Override
    public List<Producto> obtenerTodosLosProductosActivos() throws PersistenciaException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, tipo, precio, estado, descripcion FROM Producto WHERE estado = 'Disponible'";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setTipo(rs.getString("tipo"));
                p.setPrecio(rs.getFloat("precio"));
                p.setEstado(rs.getString("estado"));
                p.setDescripcion(rs.getString("descripcion"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Error al consultar catálogo", e);
            throw new PersistenciaException("No se pudo cargar el catálogo de productos.");
        }
    }

    /**
     * Recupera una lista simplificada que contiene solo los nombres de los
     * productos disponibles.
     *
     * * @return Lista de strings con los nombres de productos con estado
     * 'Disponible'.
     * @throws PersistenciaException Si ocurre un error en la consulta
     * selectiva.
     */
    public List<String> obtenerNombresProductos() throws PersistenciaException {
        List<String> nombres = new ArrayList<>();
        // Solo productos con estado 'Disponible' según el script SQL 
        String query = "SELECT nombre FROM Producto WHERE estado = 'Disponible'";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
            return nombres;
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al consultar nombres de productos: " + ex.getMessage());
        }
    }
}
