/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import persistencia.dominio.Cupon;
import persistencia.excepciones.PersistenciaException;

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para la entidad Cupon. Define las
 * operaciones de lectura y validación de cupones de descuento en el sistema de
 * persistencia.
 *
 * * @author joser
 */
public interface ICuponDAO {

    /**
     * Busca y recupera la información de un cupón mediante su código
     * alfanumérico.
     *
     * * @param codigo El código único del cupón (ej. "DESCUENTO20").
     * @return Un objeto {@link Cupon} con los detalles del descuento, o null si
     * el código no existe en la base de datos.
     * @throws PersistenciaException Si ocurre un error técnico al acceder a la
     * fuente de datos o la conexión falla.
     */
    public Cupon consultarCupon(String codigo) throws PersistenciaException;
}
