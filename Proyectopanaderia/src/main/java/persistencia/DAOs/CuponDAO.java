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
 * Implementación del DAO para la gestión de cupones en la base de datos.
 * Proporciona métodos para consultar la información y vigencia de cupones de
 * descuento.
 *
 * * @author joser
 * @version 1.0
 */
public class CuponDAO implements ICuponDAO {

    /**
     * Manejador de la conexión a la base de datos.
     */
    private final IConexionBD conexionBD;
    /**
     * Registrador de eventos para errores técnicos en esta clase.
     */
    private static final Logger LOG = Logger.getLogger(TelefonoDAO.class.getName());

    /**
     * Constructor que inyecta la conexión a la base de datos.
     *
     * * @param conexionBD La instancia de conexión a utilizar.
     */
    public CuponDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Consulta un cupón en la base de datos mediante su código único. Recupera
     * información sobre el descuento, vigencia y límites de uso.
     *
     * * @param codigo El código de texto del cupón a buscar.
     * @return Un objeto {@link Cupon} con los datos encontrados, o {@code null}
     * si no existe.
     * @throws PersistenciaException Si ocurre un error de comunicación con la
     * base de datos.
     */
    @Override
    public Cupon consultarCupon(String codigo) throws PersistenciaException {
        String query = """
                       SELECT id_cupon, descuento, vigencia, limite_usos, usos_actuales FROM cupon WHERE codigo = ?
                       """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
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
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Error al conectar con la base de datos: " + ex.getMessage());
        }
    }

}
