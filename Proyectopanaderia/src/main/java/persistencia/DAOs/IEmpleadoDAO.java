/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import persistencia.dominio.Empleado; 
import persistencia.excepciones.PersistenciaException;

public interface IEmpleadoDAO {
    /**
     * Verifica si existe un empleado con el correo y contraseña proporcionados.
     */
    public boolean validarCredenciales(String correo, String password) throws PersistenciaException;
}