/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.BOs;

import negocio.DTOs.ClienteDTO;
import negocio.excepciones.NegocioException;




/**
 *
 * @author joser
 */
public interface IClienteBO {
    
    public ClienteDTO agregarCliente(ClienteDTO cliente) throws NegocioException;
    
    public ClienteDTO editarCliente(int id, ClienteDTO cliente) throws NegocioException;
    
    public ClienteDTO leerClientePorCorreo(String correo) throws NegocioException;
    
    public boolean validarCliente(String correo, String contraseña) throws NegocioException;
    
    public void desactivarCuenta(int idCliente) throws NegocioException;
}
