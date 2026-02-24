/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import persistencia.dominio.Cliente;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public interface IClienteDAO {
    public Cliente agregarCliente(Cliente cliente) throws PersistenciaException;
    
    public Cliente editarCliente(int id, Cliente cliente) throws PersistenciaException;
    
    public Cliente leerClientePorCorreo(String correo) throws PersistenciaException;
    
    public boolean validarCliente(String correo, String contraseña) throws PersistenciaException;
}
