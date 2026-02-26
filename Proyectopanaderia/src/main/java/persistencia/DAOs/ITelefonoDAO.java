/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import java.util.List;
import persistencia.dominio.Telefono;
import persistencia.excepciones.PersistenciaException;

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para la entidad Telefono.
 * Define las operaciones CRUD para gestionar los números telefónicos asociados
 * a las entidades del sistema (como Clientes o Empleados).
 * * @author joser
 */
public interface ITelefonoDAO {

    /**
     * Recupera una lista de todos los teléfonos asociados a un identificador
     * específico.
     *
     * * @param id Identificador único del propietario (ej. idCliente) de los
     * teléfonos.
     * @return Una {@link List} de objetos {@link Telefono} asociados al ID
     * proporcionado.
     * @throws PersistenciaException Si ocurre un error al consultar la base de
     * datos.
     */
    public List<Telefono> consultarTelefonos(int id) throws PersistenciaException;

    /**
     * Actualiza los datos de un registro telefónico existente.
     *
     * * @param id Identificador único del registro telefónico a modificar.
     * @param telefonoNuevo Objeto {@link Telefono} con la información
     * actualizada.
     * @return El objeto {@link Telefono} tras haber sido actualizado en la
     * persistencia.
     * @throws PersistenciaException Si el ID no existe o hay un error en la
     * actualización.
     */
    public Telefono editarTelefono(int id, Telefono telefonoNuevo) throws PersistenciaException;

    /**
     * Elimina de forma permanente un registro telefónico del sistema.
     *
     * * @param id Identificador único del teléfono a eliminar.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error técnico durante la
     * eliminación.
     */
    public boolean eliminarTelefono(int id) throws PersistenciaException;

    /**
     * Crea y persiste un nuevo registro de teléfono en el sistema.
     *
     * * @param telefono Objeto {@link Telefono} que contiene la información a
     * registrar.
     * @return El objeto {@link Telefono} persistido, idealmente con su ID
     * generado.
     * @throws PersistenciaException Si los datos son inválidos o falla la
     * inserción.
     */
    public Telefono crearTelefono(Telefono telefono) throws PersistenciaException;
}
