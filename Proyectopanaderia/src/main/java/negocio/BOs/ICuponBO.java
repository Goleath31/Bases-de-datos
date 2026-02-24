/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.BOs;

import negocio.DTOs.CuponDTO;
import negocio.excepciones.NegocioException;

/**
 *
 * @author joser
 */
public interface ICuponBO {
    public CuponDTO validarCupon(String codigo) throws NegocioException;
}
