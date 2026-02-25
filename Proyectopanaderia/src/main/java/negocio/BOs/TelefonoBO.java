/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.DTOs.TelefonoDTO;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.ITelefonoDAO;
import persistencia.dominio.Telefono;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public class TelefonoBO implements ITelefonoBO {
    private final ITelefonoDAO telefonoDAO;
    private static final Logger LOG = Logger.getLogger(TelefonoBO.class.getName());

    public TelefonoBO(ITelefonoDAO telefonoDAO) {
        this.telefonoDAO = telefonoDAO;
    }

    @Override
    public List<TelefonoDTO> consultarTelefonos(int idCliente) throws NegocioException {
        if (idCliente <= 0) {
            throw new NegocioException("ID de cliente no válido para consultar teléfonos.");
        }

        List<TelefonoDTO> listaTelefonosDTO = new ArrayList<>();

        try {
            List<Telefono> listaEntidades = telefonoDAO.consultarTelefonos(idCliente);

            if (listaEntidades.isEmpty()) {
                return listaTelefonosDTO;
            }

            for (Telefono t : listaEntidades) {
                TelefonoDTO dto = new TelefonoDTO();
                dto.setIdTelefono(t.getId());
                dto.setNumero(t.getNumero());
                dto.setEtiqueta(t.getEtiqueta());

                listaTelefonosDTO.add(dto);
            }

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al consultar teléfonos del cliente: " + idCliente, ex);
            throw new NegocioException("No se pudieron cargar tus teléfonos. Intenta más tarde.");
        }

        return listaTelefonosDTO;
    }

    @Override
    public TelefonoDTO editarTelefono(int idTelefono, TelefonoDTO telefonoNuevoDTO) throws NegocioException {
        if (idTelefono <= 0) {
            throw new NegocioException("El ID del teléfono no es válido.");
        }

        validarDatosTelefono(telefonoNuevoDTO);

        try {
            Telefono entidad = new Telefono();
            entidad.setId(idTelefono);
            entidad.setNumero(telefonoNuevoDTO.getNumero());
            entidad.setEtiqueta(telefonoNuevoDTO.getEtiqueta());

            Telefono resultado = telefonoDAO.editarTelefono(idTelefono, entidad);

            if (resultado == null) {
                throw new NegocioException("No se encontró el teléfono para actualizar.");
            }

            return telefonoNuevoDTO;

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al editar teléfono ID: " + idTelefono, ex);
            throw new NegocioException("Error al actualizar el teléfono: " + ex.getMessage());
        }
    }

    @Override
    public boolean eliminarTelefono(int idTelefono) throws NegocioException {
        if (idTelefono <= 0) {
            throw new NegocioException("El ID del teléfono no es válido.");
        }

        try {

            boolean eliminado = telefonoDAO.eliminarTelefono(idTelefono);

            if (!eliminado) {
                throw new NegocioException("No se pudo eliminar el teléfono. Es posible que ya no exista.");
            }

            LOG.log(Level.INFO, "Teléfono con ID {0} eliminado exitosamente.", idTelefono);
            return true;

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al intentar eliminar el teléfono ID: " + idTelefono, ex);
            throw new NegocioException("Error técnico al intentar eliminar el registro.");
        }
    }

    @Override
    public TelefonoDTO crearTelefono(TelefonoDTO telefonoDTO) throws NegocioException {
        if (telefonoDTO == null) {
            throw new NegocioException("Los datos del teléfono son requeridos.");
        }

        validarDatosTelefono(telefonoDTO);

        if (telefonoDTO.getIdCliente() <= 0) {
            throw new NegocioException("No se puede registrar un teléfono sin un cliente asociado.");
        }

        try {
            Telefono entidad = new Telefono();
            entidad.setNumero(telefonoDTO.getNumero());
            entidad.setEtiqueta(telefonoDTO.getEtiqueta());
            entidad.setId(telefonoDTO.getIdCliente());

            Telefono resultado = telefonoDAO.crearTelefono(entidad);

            if (resultado == null) {
                throw new NegocioException("La base de datos no pudo procesar el registro.");
            }

            TelefonoDTO respuesta = new TelefonoDTO();
            respuesta.setIdTelefono(resultado.getId());
            respuesta.setNumero(resultado.getNumero());
            respuesta.setEtiqueta(resultado.getEtiqueta());
            respuesta.setIdCliente(resultado.getId());

            LOG.log(Level.INFO, "Teléfono creado y mapeado exitosamente: ID {0}", respuesta.getIdTelefono());

            return respuesta;

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error en la creación del teléfono", ex);
            throw new NegocioException("Error al guardar en el sistema: " + ex.getMessage());
        }
    }
    
    
    /**
     * Valida que el teléfono tenga un formato de 10 dígitos y que la etiqueta
     * sea válida.
     */
    private void validarDatosTelefono(TelefonoDTO telefono) throws NegocioException {
        if (telefono == null) {
            throw new NegocioException("Los datos del teléfono son nulos.");
        }

        // 1. Validar el Número (exactamente 10 dígitos numéricos)
        String numero = telefono.getNumero();
        if (numero == null || !numero.matches("^\\d{10}$")) {
            throw new NegocioException("El número de teléfono debe tener exactamente 10 dígitos numéricos.");
        }

        // 2. Validar la Etiqueta (Ej: Casa, Oficina, Celular)
        String etiqueta = telefono.getEtiqueta();
        if (etiqueta == null || etiqueta.trim().isEmpty()) {
            throw new NegocioException("Debes asignar una etiqueta al teléfono (ej. Casa, Celular).");
        }

        if (etiqueta.length() > 20) {
            throw new NegocioException("La etiqueta es demasiado larga (máximo 20 caracteres).");
        }
    }

}
