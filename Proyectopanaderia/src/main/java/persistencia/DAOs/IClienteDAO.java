/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import persistencia.dominio.Cliente;
import persistencia.excepciones.PersistenciaException;

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para la entidad Cliente. Define
 * las operaciones necesarias para gestionar la persistencia de los clientes en
 * la base de datos.
 *
 * * @author joser
 */
public interface IClienteDAO {

    /**
     * Registra un nuevo cliente en el sistema.
     *
     * * @param cliente Objeto con la información del cliente a registrar.
     * @return El objeto {@link Cliente} persistido, incluyendo su ID generado.
     * @throws PersistenciaException Si ocurre un error durante la inserción en
     * la base de datos.
     */
    public Cliente agregarCliente(Cliente cliente) throws PersistenciaException;

    /**
     * Actualiza la información de un cliente existente.
     *
     * * @param id Identificador único del cliente a modificar.
     * @param cliente Objeto con los nuevos datos del cliente.
     * @return El objeto {@link Cliente} actualizado.
     * @throws PersistenciaException Si el ID no existe o hay un error en la
     * actualización.
     */
    public Cliente editarCliente(int id, Cliente cliente) throws PersistenciaException;

    /**
     * Recupera la información de un cliente mediante su correo electrónico.
     *
     * * @param correo Correo electrónico del cliente.
     * @return El objeto {@link Cliente} encontrado, o null si no existe.
     * @throws PersistenciaException Si ocurre un error en la consulta.
     */
    public Cliente leerClientePorCorreo(String correo) throws PersistenciaException;

    /**
     * Verifica si las credenciales de acceso de un cliente son válidas.
     *
     * * @param correo Correo electrónico del usuario.
     * @param contraseña Contraseña asociada a la cuenta.
     * @return true si las credenciales coinciden, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error de conexión o consulta.
     */
    public boolean validarCliente(String correo, String contraseña) throws PersistenciaException;

    /**
     * Realiza un borrado lógico de un cliente cambiando su estado a inactivo.
     *
     * * @param idCliente Identificador único del cliente a desactivar.
     * @throws PersistenciaException Si el cliente no puede ser desactivado.
     */
    public void desactivarCliente(int idCliente) throws PersistenciaException;

    /**
     * Recupera la información de un cliente mediante su ID único.
     *
     * * @param idCliente Identificador único del cliente.
     * @return El objeto {@link Cliente} correspondiente al ID.
     * @throws PersistenciaException Si el ID no se encuentra en el sistema.
     */
    public Cliente leerClientePorId(int idCliente) throws PersistenciaException;
}
