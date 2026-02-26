/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.BOs;

import java.util.List;
import negocio.DTOs.TelefonoDTO;
import negocio.excepciones.NegocioException;
import persistencia.dominio.Telefono;

/**
 * Objeto de Negocio (BO) para la gestión de teléfonos de clientes. Proporciona
 * validaciones antes de interactuar con la capa de persistencia.
 *
 * * @author joser
 */
public interface ITelefonoBO {

    /**
     * Actualiza la información de un teléfono existente.
     *
     * @param id Identificador del teléfono a editar.
     * @param telefonoNuevoDTO Objeto con los nuevos datos.
     * @return {@link TelefonoDTO} actualizado.
     * @throws NegocioException Si los datos son inválidos o falla la
     * persistencia.
     */
    public List<TelefonoDTO> consultarTelefonos(int id) throws NegocioException;

    /**
     * Elimina un teléfono del sistema.
     *
     * @param id Identificador del teléfono.
     * @return true si se eliminó correctamente, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante el proceso.
     */
    public TelefonoDTO editarTelefono(int id, TelefonoDTO telefonoNuevoDTO) throws NegocioException;

    /**
     * Elimina un teléfono del sistema.
     *
     * @param id Identificador del teléfono.
     * @return true si se eliminó correctamente, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante el proceso.
     */
    public boolean eliminarTelefono(int id) throws NegocioException;

    /**
     * Registra un nuevo teléfono en el sistema.
     * @param telefonoDTO Datos del teléfono a crear.
     * @return {@link TelefonoDTO} con el ID generado.
     * @throws NegocioException Si el formato es incorrecto o hay duplicados.
     */
    public TelefonoDTO crearTelefono(TelefonoDTO telefonoDTO) throws NegocioException;
}
