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
 * Clase de Acceso a Datos (DAO) para la entidad Telefono. Proporciona métodos
 * para realizar operaciones CRUD sobre los teléfonos asociados a los clientes
 * en la base de datos.
 *
 * * @author joser
 * @version 1.0
 */
public class TelefonoDAO implements ITelefonoDAO {

    private final IConexionBD conexionBD;
    private static final Logger LOG = Logger.getLogger(TelefonoDAO.class.getName());

    public TelefonoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Consulta y retorna una lista de todos los teléfonos asociados a un
     * cliente específico.
     *
     * * @param id Identificador único del cliente.
     * @return Lista de objetos {@link Telefono} pertenecientes al cliente.
     * @throws PersistenciaException Si ocurre un error de SQL durante la
     * consulta.
     */
    @Override
    public List<Telefono> consultarTelefonos(int id) throws PersistenciaException {
        List<Telefono> listaEntregable = new ArrayList<>();
        String query = """
                       SELECT id_telefono ,numero, etiqueta FROM telefono WHERE id_cliente = ?
                       """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaEntregable.add(new Telefono(
                            rs.getInt("id_telefono"),
                            rs.getString("numero"),
                            rs.getString("etiqueta"),
                            id
                    ));
                }
            }

            return listaEntregable;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }

    /**
     * Actualiza los datos (número y etiqueta) de un registro telefónico
     * existente.
     *
     * * @param id Identificador único del teléfono a editar.
     * @param telefonoNuevo Objeto con los nuevos datos a persistir.
     * @return El objeto {@link Telefono} actualizado si tuvo éxito;
     * {@code null} si no se encontró el ID.
     * @throws PersistenciaException Si hay errores en la conexión o ejecución
     * del UPDATE.
     */
    @Override
    public Telefono editarTelefono(int id, Telefono telefonoNuevo) throws PersistenciaException {
        String query = """
                       UPDATE telefono SET numero = ?,  etiqueta = ? WHERE id_telefono = ?
                       """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
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

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }

    /**
     * Elimina un registro telefónico de la base de datos mediante su
     * identificador.
     *
     * * @param id Identificador del teléfono a eliminar.
     * @return {@code true} si se eliminó correctamente; {@code false} en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error durante el borrado.
     */
    @Override
    public boolean eliminarTelefono(int id) throws PersistenciaException {
        String query = """
                       DELETE FROM telefono WHERE id_telefono = ?
                       """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }

    /**
     * Inserta un nuevo teléfono en la base de datos y recupera el ID generado.
     *
     * * @param telefono Objeto {@link Telefono} con la información a insertar
     * (número, etiqueta e id_cliente).
     * @return El objeto {@link Telefono} con su ID asignado por la base de
     * datos.
     * @throws PersistenciaException Si falla la inserción o la recuperación de
     * llaves generadas.
     */
    @Override
    public Telefono crearTelefono(Telefono telefono) throws PersistenciaException {
        String query = """
                       INSERT INTO telefono (numero, etiqueta, id_cliente) 
                       VALUES (?, ?, ?)
                       """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
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

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }

}
