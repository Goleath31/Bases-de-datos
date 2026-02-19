/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author golea
 */
public class ConexionBD implements IConexionBD {
    private final String cadenaConexion = "jdbc:mysql://localhost:3306/panaderia_db";
    private final String usuario = "root";
    private final String password = "goleath9090";

    @Override
    public Connection crearConexion() throws SQLException {
        return DriverManager.getConnection(cadenaConexion, usuario, password);
    }
}
