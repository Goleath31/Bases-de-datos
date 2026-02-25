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
 *
 * @author joser
 */
public interface ITelefonoBO {
    public List<TelefonoDTO> consultarTelefonos(int id) throws NegocioException;
    
    public TelefonoDTO editarTelefono(int id, TelefonoDTO telefonoNuevoDTO) throws NegocioException;
    
    public boolean eliminarTelefono(int id) throws NegocioException;
    
    public TelefonoDTO crearTelefono(TelefonoDTO telefonoDTO) throws NegocioException;
}
