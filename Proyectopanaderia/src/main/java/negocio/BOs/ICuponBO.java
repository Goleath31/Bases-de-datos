/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.BOs;

import negocio.DTOs.CuponDTO;
import negocio.excepciones.NegocioException;

/**
 * Interfaz de la Capa de Negocio para la gestión de Cupones de descuento.
 * Define el contrato para las operaciones de verificación y validación de
 * beneficios aplicables a los pedidos.
 *
 * * @author joser
 */
public interface ICuponBO {

    /**
     * Valida la existencia, formato y vigencia de un cupón mediante su código.
     *
     * * @param codigo Cadena de texto que representa el código del cupón.
     * @return {@link CuponDTO} con los detalles del descuento si el cupón es
     * válido.
     * @throws NegocioException Si el cupón ha expirado, alcanzó su límite de
     * usos, no existe o el formato es incorrecto.
     */
    public CuponDTO validarCupon(String codigo) throws NegocioException;
}
