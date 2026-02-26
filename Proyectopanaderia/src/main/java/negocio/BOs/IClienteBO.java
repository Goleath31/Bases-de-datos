/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.BOs;

import negocio.DTOs.ClienteDTO;
import negocio.excepciones.NegocioException;

/**
 * Interfaz de la Capa de Negocio para la gestión de Clientes. Define las
 * operaciones permitidas para el manejo de cuentas de usuario, incluyendo
 * registro, actualización, consulta y seguridad.
 *
 * * @author joser
 */
public interface IClienteBO {

    /**
     * Registra un nuevo cliente en el sistema. Debe validar que los datos
     * cumplan con las reglas de negocio antes de persistirlos.
     *
     * * @param cliente Objeto DTO con la información del nuevo cliente.
     * @return El {@link ClienteDTO} con los datos registrados y su
     * identificador único generado.
     * @throws NegocioException Si los datos son inválidos o ocurre un error en
     * el proceso.
     */
    public ClienteDTO agregarCliente(ClienteDTO cliente) throws NegocioException;

    /**
     * Actualiza la información de un cliente existente identificado por su ID.
     *
     * * @param id Identificador único del cliente a modificar.
     * @param cliente Objeto DTO con la información actualizada.
     * @return El {@link ClienteDTO} con los cambios aplicados.
     * @throws NegocioException Si el ID no existe, los datos son erróneos o hay
     * fallos de persistencia.
     */
    public ClienteDTO editarCliente(int id, ClienteDTO cliente) throws NegocioException;

    /**
     * Recupera la información de un cliente utilizando su dirección de correo
     * electrónico.
     *
     * * @param correo Correo electrónico del cliente a buscar.
     * @return {@link ClienteDTO} con los datos del cliente encontrado.
     * @throws NegocioException Si el formato del correo es inválido o el
     * cliente no existe.
     */
    public ClienteDTO leerClientePorCorreo(String correo) throws NegocioException;

    /**
     * Valida las credenciales de un cliente para el acceso al sistema (Login).
     * Si la validación es exitosa, se espera que el sistema gestione la sesión
     * activa.
     *
     * * @param correo Correo electrónico registrado.
     * @param contraseña Contraseña asociada a la cuenta.
     * @return {@code true} si las credenciales coinciden; {@code false} en caso
     * contrario.
     * @throws NegocioException Si los campos están vacíos o hay errores
     * técnicos en la validación.
     */
    public boolean validarCliente(String correo, String contraseña) throws NegocioException;

    /**
     * Realiza la baja lógica de una cuenta de cliente. Este método debe
     * asegurar que el usuario ya no pueda autenticarse sin eliminar sus
     * registros históricos del sistema.
     *
     * * @param idCliente Identificador del cliente cuya cuenta será
     * desactivada.
     * @throws NegocioException Si el ID es inválido o la cuenta ya se encuentra
     * desactivada.
     */
    public void desactivarCuenta(int idCliente) throws NegocioException;
}
