/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.*;
import persistencia.conexion.IConexionBD;
import persistencia.excepciones.PersistenciaException;

/**
 * Implementación de la interfaz {@link IEmpleadoDAO} para el acceso a datos de
 * la entidad Empleado en la base de datos.
 */
public class EmpleadoDAO implements IEmpleadoDAO {

    /**
     * Manejador de la conexión a la base de datos.
     */
    private final IConexionBD conexionBD;

    /**
     * Constructor que recibe la conexión de la fábrica.
     *
     * @param conexionBD Proveedor de conexión.
     */
    public EmpleadoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Valida si un empleado existe basándose en sus credenciales.
     *
     * * @param correo Correo electrónico del empleado.
     * @param password Contraseña en formato de texto plano.
     * @return true si las credenciales son válidas, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error técnico en la base de
     * datos.
     */
    @Override
    public boolean validarCredenciales(String correo, String password) throws PersistenciaException {
        String sql = "SELECT COUNT(*) FROM Empleado WHERE correo = ? AND contrasena = ?";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Si el conteo es mayor a 0, existe el usuario
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al validar credenciales en la base de datos", e);
        }
        return false;
    }
}
