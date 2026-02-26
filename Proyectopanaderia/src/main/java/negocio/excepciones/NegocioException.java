/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.excepciones;

/**
 * Representa una excepción personalizada para la capa de negocio.
 * Se utiliza para capturar y reportar errores específicos relacionados
 * con las reglas y validaciones de la lógica de negocio.
 * * @author golea
 * @version 1.0
 */
public class NegocioException extends Exception {

    /**
     * Construye una nueva excepción con el mensaje de detalle especificado.
     * * @param message El mensaje que describe la causa del error.
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Construye una nueva excepción con el mensaje de detalle y la causa original.
     * * @param message El mensaje que describe la causa del error.
     * @param cause La causa original del error (otra excepción).
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}