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

public class ProductoDAO implements IProductoDAO {

    private final IConexionBD conexionBD;
    private static final Logger LOG = Logger.getLogger(ProductoDAO.class.getName());
    

    public ProductoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

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
    
    @Override
public void agregarProducto(Producto producto) throws PersistenciaException {
    String sql = "INSERT INTO Producto (nombre, tipo, descripcion, precio, estado) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = conexionBD.crearConexion(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
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
}
