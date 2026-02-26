/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 * Data Transfer Object (DTO) que representa la información de contacto telefónico de un cliente.
 * Permite gestionar múltiples números telefónicos asociados a una misma cuenta mediante etiquetas.
 * *
 * @author joser
 */
public class TelefonoDTO {
    private int idTelefono;
    private String numero;
    private String etiqueta;
    private int idCliente;

    /**
     * Constructor vacío por defecto para la creación de instancias genéricas.
     *
     */
    public TelefonoDTO() {
    }

    /**
     * Obtiene el identificador único del registro telefónico.
     * @return El ID del teléfono.
     */
    public int getIdTelefono() {
        return idTelefono;
    }

    /**
     * Establece el identificador único del registro telefónico.
     * @param idTelefono El ID a establecer.
     */
    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }

    /**
     * Obtiene el número de teléfono.
     * @return Una cadena con el número telefónico.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de teléfono.
     * @param numero El número telefónico a establecer.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la etiqueta descriptiva del teléfono (ej. "Casa", "Celular", "Trabajo").
     * @return La etiqueta del teléfono.
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * Establece la etiqueta descriptiva del teléfono.
     * @param etiqueta La descripción de la línea a establecer.
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * Obtiene el identificador del cliente al que pertenece este número.
     * @return El ID del cliente asociado.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Vincula este número telefónico con un cliente específico.
     * @param idCliente El identificador del cliente a establecer.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}