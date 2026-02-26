/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.conexion.IConexionBD;
import persistencia.dominio.Cliente;
import persistencia.excepciones.PersistenciaException;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Implementación del Objeto de Acceso a Datos (DAO) para la entidad Cliente.
 * Proporciona métodos para realizar operaciones CRUD y validaciones de
 * seguridad sobre la tabla 'cliente' en la base de datos MySQL.
 *
 * * @author joser, golea
 * @version 1.0
 */
public class ClienteDAO implements IClienteDAO {

    /**
     * Proveedor de conexiones a la base de datos.
     */
    private final IConexionBD conexionBD;

    /**
     * Registrador de eventos para depuración y control de errores.
     */
    private static final Logger LOG = Logger.getLogger(PedidoDAO.class.getName());

    /**
     * Inicializa el DAO con un manejador de conexión específico.
     *
     * @param conexionBD Implementación de la conexión a utilizar.
     */
    public ClienteDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Registra un nuevo cliente en la base de datos. La contraseña se almacena
     * de forma segura utilizando un hash generado por BCrypt.
     *
     * * @param cliente Objeto con los datos del cliente a registrar.
     * @return El objeto {@link Cliente} incluyendo el ID generado por la base
     * de datos.
     * @throws PersistenciaException Si ocurre un error en la inserción o
     * conexión.
     */
    @Override
    public Cliente agregarCliente(Cliente cliente) throws PersistenciaException {
        //(nombre, apellidop, apellidom, domicilio, fechanacimiento, correo, contraseña)
        String query = """  
                       INSERT INTO cliente (nombre, apellido_paterno, apellido_materno, domicilio, fecha_nacimiento, correo, contrasena) VALUES(?, ?, ?, ?, ?, ?, ?)
                       """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getApellidoMaterno());
            ps.setString(4, cliente.getDomicilio());
            ps.setDate(5, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setString(6, cliente.getCorreo());
            String contrasenaHash = BCrypt.hashpw(cliente.getContraseña(), BCrypt.gensalt(12));
            ps.setString(7, contrasenaHash);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }

            return cliente;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }

    }

    /**
     * Actualiza la información personal de un cliente existente.
     *
     * * @param id Identificador único del cliente.
     * @param cliente Objeto con los nuevos datos.
     * @return El objeto {@link Cliente} actualizado, o null si no se encontró
     * el ID.
     * @throws PersistenciaException Si ocurre un error en la sentencia SQL.
     */
    @Override
    public Cliente editarCliente(int id, Cliente cliente) throws PersistenciaException {
        String query = """
                       UPDATE cliente SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, domicilio = ? WHERE id_cliente = ?
                       """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getApellidoMaterno());
            ps.setString(4, cliente.getDomicilio());
            ps.setInt(5, cliente.getId());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return cliente;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }

    }

    /**
     * Busca un cliente por su dirección de correo electrónico.
     *
     * * @param correo Correo electrónico a consultar.
     * @return Objeto {@link Cliente} si existe, de lo contrario null.
     * @throws PersistenciaException Si ocurre un error en la consulta.
     */
    @Override
    public Cliente leerClientePorCorreo(String correo) throws PersistenciaException {
        String query = """
                      SELECT id_cliente, nombre, apellido_paterno, apellido_materno, domicilio, fecha_nacimiento FROM cliente WHERE correo = ?
                      """;
        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente clienteRetornable = new Cliente();
                    clienteRetornable.setId(rs.getInt("id_cliente"));
                    clienteRetornable.setNombre(rs.getString("nombre"));
                    clienteRetornable.setApellidoPaterno(rs.getString("apellido_paterno"));
                    clienteRetornable.setApellidoMaterno(rs.getString("apellido_materno"));
                    clienteRetornable.setCorreo(correo);
                    clienteRetornable.setDomicilio(rs.getString("domicilio"));
                    java.sql.Date fechaSql = rs.getDate("fecha_nacimiento");
                    if (fechaSql != null) {
                        clienteRetornable.setFechaNacimiento(fechaSql);
                    }
                    
                    return clienteRetornable;
                }
            }
            return null;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
    }

    /**
     * Valida las credenciales de acceso de un cliente. Compara la contraseña en
     * texto plano con el hash almacenado en la base de datos.
     *
     * * @param correo Correo electrónico del cliente.
     * @param contraseña Contraseña en texto plano.
     * @return true si las credenciales son válidas, false en caso contrario.
     * @throws PersistenciaException Si hay errores técnicos en la validación.
     */
    @Override
    public boolean validarCliente(String correo, String contraseña) throws PersistenciaException {
        String query = """
                       SELECT correo, contrasena FROM cliente WHERE correo = ?
                       """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashBD = rs.getString("contrasena");
                    return BCrypt.checkpw(contraseña, hashBD);
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al conectar con la base de datos", ex);
            throw new PersistenciaException("Se ha producido un error al conectar con las base de datos: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Inactiva la cuenta de un cliente modificando su hash de contraseña.
     *
     * @param idCliente ID del cliente a desactivar.
     * @throws PersistenciaException Si el proceso de bloqueo falla.
     */
    @Override
    public void desactivarCliente(int idCliente) throws PersistenciaException {
        String query = "UPDATE Clientes SET contraseña = CONCAT(contraseña, 'DESACTIVADO') WHERE id_cliente = ?";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idCliente);
            ps.executeUpdate();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al concatenar bloqueo al hash", ex);
            throw new PersistenciaException("No se pudo bloquear la cuenta.");
        }
    }

    /**
     * Recupera todos los datos de un cliente mediante su ID único.
     *
     * * @param idCliente ID del cliente.
     * @return Objeto {@link Cliente} completo o null si no existe.
     * @throws PersistenciaException Si ocurre un error en la base de datos.
     */
    @Override
    public Cliente leerClientePorId(int idCliente) throws PersistenciaException {
        String query
                = """
                SELECT id_cliente, nombre, apellido_paterno, apellido_materno, correo, contraseña, domicilio, fecha_nacimiento
                FROM Clientes WHERE id_cliente = ?
                """;

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id_cliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellidoPaterno(rs.getString("apellido_paterno"));
                    cliente.setApellidoMaterno(rs.getString("apellido_materno"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setContraseña(rs.getString("contraseña")); //contraseña en hash 
                    cliente.setDomicilio(rs.getString("domicilio"));
                    cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));

                    return cliente;
                }
            }

            return null; // Si no se encuentra el ID

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al leer cliente por ID: " + idCliente, ex);
            throw new PersistenciaException("Error al consultar los datos del cliente.");
        }
    }

}
