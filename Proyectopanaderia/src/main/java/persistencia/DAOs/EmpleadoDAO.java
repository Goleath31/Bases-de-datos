/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.*;
import persistencia.conexion.IConexionBD;
import persistencia.excepciones.PersistenciaException;

public class EmpleadoDAO implements IEmpleadoDAO {

    private final IConexionBD conexionBD;

    public EmpleadoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public boolean validarCredenciales(String correo, String password) throws PersistenciaException {
        String sql = "SELECT COUNT(*) FROM Empleado WHERE correo = ? AND contrasena = ?";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, password); // Nota: En un futuro aquí deberías comparar hashes, no texto plano

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
