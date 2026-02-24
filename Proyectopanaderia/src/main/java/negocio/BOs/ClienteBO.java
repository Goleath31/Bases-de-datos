/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.DTOs.ClienteDTO;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.IClienteDAO;
import persistencia.DAOs.IPedidoDAO;
import persistencia.dominio.Cliente;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public class ClienteBO implements IClienteBO{
    private final IClienteDAO clienteDAO;
    private static final Logger LOG = Logger.getLogger(PedidoBO.class.getName());

    public ClienteBO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    

    @Override
    public ClienteDTO agregarCliente(ClienteDTO clienteDTO) throws NegocioException {
        if (clienteDTO == null) {
            LOG.log( Level.WARNING, "Se intento agregar un cliente nulo");
            throw new NegocioException("El cliente a agregar no puede ser null");
        }
        
        validarCliente(clienteDTO);
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidoPaterno(clienteDTO.getApellidoPaterno());
        cliente.setApellidoMaterno(clienteDTO.getApellidoMaterno());
        cliente.setDomicilio(clienteDTO.getDomicilio());
        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setContraseña(clienteDTO.getContraseña());
        
        try{
            clienteDAO.agregarCliente(cliente);
            clienteDTO.setIdCliente(cliente.getId());
        
            return clienteDTO;
        }
        catch(PersistenciaException ex){
            LOG.log( Level.WARNING, "Error al querer agregar un cliente " + ex.getMessage());
            throw new NegocioException("Error al agrear un cliente " + ex.getMessage());
        }
        
    }

    @Override
    public ClienteDTO editarCliente(int id, ClienteDTO cliente) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClienteDTO leerClientePorCorreo(String correo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validarCliente(String correo, String contraseña) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void validarCliente(ClienteDTO clienteDTO) throws NegocioException {
        if (clienteDTO == null) {
            throw new NegocioException("Los datos del cliente no pueden ser nulos.");
        }

        // --- Validación de Nombres y Apellidos ---
        // Explicación Regex: ^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$ (Letras y espacios solamente)
        String regexNombre = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$";

        if (clienteDTO.getNombre() == null || !clienteDTO.getNombre().matches(regexNombre)) {
            throw new NegocioException("El nombre es inválido o contiene caracteres no permitidos.");
        }
        if (clienteDTO.getApellidoPaterno() == null || !clienteDTO.getApellidoPaterno().matches(regexNombre)) {
            throw new NegocioException("El apellido paterno es inválido.");
        }
        // El materno puede ser opcional en algunos sistemas, pero aquí lo validamos si existe
        if (clienteDTO.getApellidoMaterno() != null && !clienteDTO.getApellidoMaterno().matches(regexNombre)) {
            throw new NegocioException("El apellido materno contiene caracteres no permitidos.");
        }

        // --- Validación de Correo ---
        // Formato: texto@texto.dominio
        String regexCorreo = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (clienteDTO.getCorreo() == null || !clienteDTO.getCorreo().matches(regexCorreo)) {
            throw new NegocioException("El formato del correo electrónico no es válido.");
        }

        // --- Validación de Contraseña (Antes del Hash) ---
        // Al menos 8 caracteres, una letra y un número
        String regexPassword = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
        if (clienteDTO.getContraseña() == null || !clienteDTO.getContraseña().matches(regexPassword)) {
            throw new NegocioException("La contraseña debe tener al menos 4 caracteres, incluyendo una letra y un número.");
        }

        // --- Validación de Edad (Mayoría de edad) ---
        if (clienteDTO.getFechaNacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }

        // --- Validación de Domicilio ---
        if (clienteDTO.getDomicilio() == null || clienteDTO.getDomicilio().trim().length() < 10) {
            throw new NegocioException("El domicilio debe ser más específico (mínimo 10 caracteres).");
        }
    }
    
}
