/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.conexion.IConexionBD;
import persistencia.dominio.Cliente;
import persistencia.excepciones.PersistenciaException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author joser
 */
public class ClienteDAO implements IClienteDAO{
    private final IConexionBD conexionBD = null;
    private static final Logger LOG = Logger.getLogger(PedidoDAO.class.getName());

    @Override
    public void agregarCliente(Cliente cliente) throws PersistenciaException {
        //(nombre, apellidop, apellidom, domicilio, fechanacimiento, correo, contraseña)
        String query = """  
                       INSERT INTO cliente (nombre, apellido_paterno, apellido_materno, domicilio, fecha_nacimiento, correo, contrasena) VALUES(?, ?, ?, ?, ?, ?, ?)
                       """;
        
        try(Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)){
            
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getApellidoPaterno());
            ps.setString(4, cliente.getDomicilio());
            ps.setDate(5, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setString(6, cliente.getDomicilio());
            String contrasenaHash = BCrypt.hashpw(cliente.getContraseña(), BCrypt.gensalt(12));
            ps.setString(7, contrasenaHash);
            
        
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
        
    }

    @Override
    public Cliente editarCliente(int id, Cliente cliente) throws PersistenciaException {
        String query = """
                       UPDATE cliente SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, domicilio = ?, fecha_nacimiendo = ? WHERE id_cliente = ?
                       """;
        
        try(Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getApellidoMaterno());
            ps.setString(4, cliente.getDomicilio());
            ps.setDate(5, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setInt(6, cliente.getId());
            return cliente;
            
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
        
    }

    @Override
    public Cliente leerClientePorCorreo(String correo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validarCliente(String correo, String contraseña) throws PersistenciaException {
        String query = """
                       SELECT correo, contrasena FROM cliente WHERE correo = ?
                       """;
        
        try(Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, correo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashBD = rs.getString("contrasena");
                    return BCrypt.checkpw(contraseña, hashBD);
                }
            }
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }
    
}
