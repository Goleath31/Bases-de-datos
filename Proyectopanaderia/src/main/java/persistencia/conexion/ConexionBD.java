/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementación de {@link IConexionBD} específica para el motor de base de
 * datos MySQL. Proporciona la configuración necesaria para conectar con la base
 * de datos "panaderia_db" en un entorno local.
 *
 * * @author golea
 * @version 1.0
 */
public class ConexionBD implements IConexionBD {

    /**
     * URL de conexión que incluye el protocolo jdbc, el servidor (localhost) y
     * el puerto (3306).
     */
    private final String cadenaConexion = "jdbc:mysql://localhost:3306/panaderia_db";

    /**
     * Nombre de usuario para la autenticación en MySQL.
     */
    private final String usuario = "root";

    /**
     * Contraseña para la autenticación en MySQL.
     */
    private final String password = "goleath9090";

    /**
     * Crea una conexión física con la base de datos utilizando JDBC.
     *
     * * @return Una instancia de {@link Connection} activa.
     * @throws SQLException Si hay un error de red, el usuario/password es
     * inválido o la base de datos no existe.
     */
    @Override
    public Connection crearConexion() throws SQLException {
        return DriverManager.getConnection(cadenaConexion, usuario, password);
    }
}
