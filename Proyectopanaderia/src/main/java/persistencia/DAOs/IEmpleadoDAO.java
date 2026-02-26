/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import persistencia.dominio.Empleado;
import persistencia.excepciones.PersistenciaException;

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para la entidad Empleado. Define
 * las operaciones de autenticación y gestión de acceso para el personal
 * administrativo o de ventas en el sistema.
 *
 * * @author joser
 */
public interface IEmpleadoDAO {

    /**
     * Verifica la identidad de un empleado en el sistema mediante sus
     * credenciales.
     *
     * * @param correo Correo electrónico institucional del empleado.
     * @param password Contraseña de acceso asociada a la cuenta.
     * @return {@code true} si las credenciales coinciden con un registro
     * activo, {@code false} en caso contrario.
     * @throws PersistenciaException Si ocurre un error técnico en la base de
     * datos o durante la validación del hash de la contraseña.
     */
    public boolean validarCredenciales(String correo, String password) throws PersistenciaException;
}
