/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.conexion.IConexionBD;
import persistencia.dominio.Telefono;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public class TelefonoDAO implements ITelefonoDAO{
    private final IConexionBD conexionBD = null;
    private static final Logger LOG = Logger.getLogger(TelefonoDAO.class.getName());

    
    
    @Override
    public List<Telefono> consultarTelefonos(int id) throws PersistenciaException {
        List<Telefono> listaEntregable = new ArrayList<>();
        String query = """
                       SELECT id_telefono ,numero, etiqueta FROM telefono WHERE id_cliente = ?
                       """;
        
        try(Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    listaEntregable.add(new Telefono(
                            rs.getInt("id_telefono"),
                            rs.getString("numero"),
                            rs.getString("etiqueta"),
                            id
                    ));
                }
            }
        
            return listaEntregable;
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }

    @Override
    public Telefono editarTelefono(int id, Telefono telefonoNuevo) throws PersistenciaException {
        String query = """
                       UPDATE telefono SET numero = ?,  etiqueta = ? WHERE id_telefono = ?
                       """;
        
        try(Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)){
            //añadir validaciones del telefono, considerar si aquí o en el BO
            ps.setString(1, telefonoNuevo.getNumero());
            ps.setString(2, telefonoNuevo.getEtiqueta());
            ps.setInt(3, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                telefonoNuevo.setId(id);
                return telefonoNuevo; 
            } else {
                return null; 
            }
            
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }

    @Override
    public boolean eliminarTelefono(int id) throws PersistenciaException {
        String query = """
                       DELETE FROM telefono WHERE id_telefono = ?
                       """;
        
        
        try(Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }

    @Override
    public Telefono crearTelefono(Telefono telefono) throws PersistenciaException {
        String query = """
                       INSERT INTO telefono (numero, etiqueta, id_cliente) 
                       VALUES (?, ?, ?)
                       """;
        
        try(Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, telefono.getNumero());
            ps.setString(2, telefono.getEtiqueta());
            ps.setInt(3, telefono.getId_cliente());
            
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        telefono.setId(rs.getInt(1)); 
                    }
                }
                return telefono; 
            } else {
                return null; 
            }
            
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }
    
}
