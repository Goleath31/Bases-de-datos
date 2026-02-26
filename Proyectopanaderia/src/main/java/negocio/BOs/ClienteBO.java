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
import proyectopanaderia.SesionCliente;

/**
 * Clase de Objeto de Negocio (BO) para la gestión de Clientes. Contiene la
 * lógica de validación, registro, edición y autenticación de usuarios.
 *
 * * @author joser
 *
 */
public class ClienteBO implements IClienteBO {

    private final IClienteDAO clienteDAO;
    private static final Logger LOG = Logger.getLogger(PedidoBO.class.getName());

    /**
     * Constructor que inyecta la dependencia del DAO de cliente.
     *
     * * @param clienteDAO Interfaz de acceso a datos para clientes.
     */
    public ClienteBO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    /**
     * Registra un nuevo cliente en el sistema tras validar sus datos.
     *
     * * @param clienteDTO Objeto con la información del cliente a registrar.
     * @return ClienteDTO El objeto registrado incluyendo su ID generado.
     * @throws NegocioException Si los datos son inválidos o hay un error en la
     * persistencia.
     */
    @Override
    public ClienteDTO agregarCliente(ClienteDTO clienteDTO) throws NegocioException {
        if (clienteDTO == null) {
            LOG.log(Level.WARNING, "Se intento agregar un cliente nulo");
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

        try {
            clienteDAO.agregarCliente(cliente);
            clienteDTO.setIdCliente(cliente.getId());

            return clienteDTO;
        } catch (PersistenciaException ex) {
            LOG.log(Level.WARNING, "Error al querer agregar un cliente " + ex.getMessage());
            throw new NegocioException("Error al agrear un cliente " + ex.getMessage());
        }

    }

    /**
     * Actualiza la información de un cliente existente.
     *
     * * @param id Identificador único del cliente.
     * @param clienteDTO Objeto con los nuevos datos del cliente.
     * @return ClienteDTO Los datos actualizados.
     * @throws NegocioException Si el ID es inválido, los datos son nulos o el
     * cliente no existe.
     */
    @Override
    public ClienteDTO editarCliente(int id, ClienteDTO clienteDTO) throws NegocioException {
        if (id <= 0) {
            throw new NegocioException("El ID del cliente no es válido para edición.");
        }

        if (clienteDTO == null) {
            throw new NegocioException("Los datos de actualización no pueden ser nulos.");
        }

        validarCliente(clienteDTO);

        Cliente clienteEntidad = new Cliente();
        clienteEntidad.setId(id);
        clienteEntidad.setNombre(clienteDTO.getNombre());
        clienteEntidad.setApellidoPaterno(clienteDTO.getApellidoPaterno());
        clienteEntidad.setApellidoMaterno(clienteDTO.getApellidoMaterno());
        clienteEntidad.setDomicilio(clienteDTO.getDomicilio());
        clienteEntidad.setFechaNacimiento(clienteDTO.getFechaNacimiento());

        try {
            Cliente resultado = clienteDAO.editarCliente(id, clienteEntidad);

            if (resultado == null) {
                throw new NegocioException("No se encontró el cliente con ID " + id + " para editar.");
            }
            return clienteDTO;

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al editar cliente con ID: " + id, ex);
            throw new NegocioException("Error al actualizar los datos: " + ex.getMessage());
        }
    }

    /**
     * Busca un cliente en la base de datos mediante su correo electrónico.
     *
     * * @param correo Correo electrónico del cliente.
     * @return ClienteDTO con la información encontrada.
     * @throws NegocioException Si el formato del correo es inválido o no se
     * encuentra el cliente.
     */
    @Override
    public ClienteDTO leerClientePorCorreo(String correo) throws NegocioException {
        String regexCorreo = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (correo == null || !correo.matches(regexCorreo)) {
            throw new NegocioException("El formato del correo electrónico proporcionado no es válido.");
        }

        try {
            Cliente cliente = clienteDAO.leerClientePorCorreo(correo);

            if (cliente == null) {
                throw new NegocioException("No se encontró ningún cliente registrado con ese correo.");
            }

            ClienteDTO dto = new ClienteDTO();
            dto.setIdCliente(cliente.getId());
            dto.setNombre(cliente.getNombre());
            dto.setApellidoPaterno(cliente.getApellidoPaterno());
            dto.setApellidoMaterno(cliente.getApellidoMaterno());
            dto.setCorreo(cliente.getCorreo());
            dto.setDomicilio(cliente.getDomicilio());
            dto.setFechaNacimiento(cliente.getFechaNacimiento());

            return dto;

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al consultar cliente por correo: " + correo, ex);
            throw new NegocioException("Error técnico al recuperar los datos del cliente.");
        }
    }

    /**
     * Valida las credenciales de acceso de un cliente e inicia su sesión
     * global.
     *
     * * @param correo Correo electrónico del usuario.
     * @param contraseña Contraseña del usuario.
     * @return true si las credenciales son correctas.
     * @throws NegocioException Si faltan datos, las credenciales son
     * incorrectas o hay error de sistema.
     */
    @Override
    public boolean validarCliente(String correo, String contraseña) throws NegocioException {
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo es obligatorio para iniciar sesión.");
        }
        if (contraseña == null || contraseña.trim().isEmpty()) {
            throw new NegocioException("La contraseña es obligatoria.");
        }

        try {
            boolean esValido = clienteDAO.validarCliente(correo, contraseña);

            if (!esValido) {

                throw new NegocioException("Correo o contraseña incorrectos.");
            }
            Cliente clienteEntidad = clienteDAO.leerClientePorCorreo(correo);
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setIdCliente(clienteEntidad.getId());
            clienteDTO.setNombre(clienteEntidad.getNombre());
            clienteDTO.setApellidoPaterno(clienteEntidad.getApellidoPaterno());
            clienteDTO.setApellidoMaterno(clienteEntidad.getApellidoMaterno());
            clienteDTO.setDomicilio(clienteEntidad.getDomicilio());
            clienteDTO.setCorreo(clienteEntidad.getCorreo());

            SesionCliente.iniciarSesion(clienteDTO);
            return true;

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error durante el login del correo: " + correo, ex);
            throw new NegocioException("Error al intentar validar las credenciales.");
        }
    }

    /**
     * Realiza validaciones de formato y reglas de negocio sobre los datos de un
     * cliente. Valida nombres, correo, contraseña (mínimo 4 caracteres con
     * letra y número) y domicilio.
     *
     * * @param clienteDTO Objeto con los datos a validar.
     * @throws NegocioException Si alguna validación de formato o regla de
     * negocio falla.
     */
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

    /**
     * Desactiva la cuenta de un cliente "ensuciando" su contraseña con un
     * prefijo. Esto impide futuros inicios de sesión manteniendo la integridad
     * referencial.
     *
     * * @param idCliente ID del cliente a desactivar.
     * @throws NegocioException Si el ID es inválido, el cliente no existe o ya
     * está desactivado.
     */
    @Override
    public void desactivarCuenta(int idCliente) throws NegocioException {
        if (idCliente <= 0) {
            throw new NegocioException("ID de cliente no válido.");
        }

        try {
            Cliente cliente = clienteDAO.leerClientePorId(idCliente);

            if (cliente == null) {
                throw new NegocioException("El cliente no existe.");
            }

            String prefijo = "DESACTIVADO";
            String hashActual = cliente.getContraseña();

            if (hashActual.startsWith(prefijo)) {
                throw new NegocioException("La cuenta ya se encuentra desactivada.");
            }

            String hashEnsuciado = prefijo + hashActual;
            cliente.setContraseña(hashEnsuciado);

            clienteDAO.desactivarCliente(idCliente);

            LOG.log(Level.INFO, "Cliente con ID {0} ha sido desactivado exitosamente.", idCliente);

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al desactivar cliente: " + idCliente, ex);
            throw new NegocioException("No se pudo desactivar la cuenta en este momento.");
        }
    }

}
