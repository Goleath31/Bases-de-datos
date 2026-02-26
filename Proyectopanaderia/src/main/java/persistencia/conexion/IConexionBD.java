/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Define los métodos necesarios para obtener una conexión con el manejador de
 * base de datos. * Esta interfaz permite desacoplar la lógica de persistencia
 * del motor de base de datos específico, facilitando pruebas unitarias o el
 * cambio de proveedor de base de datos en el futuro.
 *
 * * @author golea
 * @version 1.0
 */
public interface IConexionBD {

    /**
     * Establece y devuelve una conexión activa a la base de datos.
     *
     * * @return Un objeto {@link Connection} listo para ejecutar sentencias
     * SQL.
     * @throws SQLException Si ocurre un error al intentar acceder a la base de
     * datos o si los parámetros de conexión son incorrectos.
     */
    public Connection crearConexion() throws SQLException;
}
