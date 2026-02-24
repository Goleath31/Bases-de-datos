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
import persistencia.dominio.Cupon;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public class CuponDAO implements ICuponDAO{
    private final IConexionBD conexionBD;
    private static final Logger LOG = Logger.getLogger(TelefonoDAO.class.getName());

    public CuponDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    

    @Override
    public Cupon consultarCupon(String codigo) throws PersistenciaException {
        String query = """
                       SELECT id_cupon, descuento, vigencia, limite_usos, usos_actuales FROM cupon WHERE codigo = ?
                       """;
        
        
        try(Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, codigo);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Cupon cuponRetornable = new Cupon();
                    cuponRetornable.setId(rs.getInt("id_cupon"));
                    cuponRetornable.setCodigo(codigo);
                    cuponRetornable.setDescuento(rs.getFloat("descuento"));
                    java.sql.Date fechaSql = rs.getDate("vigencia");
                    cuponRetornable.setFechaVigencia(fechaSql);
                    cuponRetornable.setLimiteUsos(rs.getInt("limite_usos"));
                    cuponRetornable.setUsosActuales(rs.getInt("usos_actuales"));
                    return cuponRetornable;
                }
                
            }
           return null;
        }
        catch(SQLException ex){
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Error al conectar con la base de datos: " + ex.getMessage());
        }
    }
    
}
