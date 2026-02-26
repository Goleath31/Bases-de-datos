/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.excepciones;

/**
 * Representa una excepción personalizada para la capa de persistencia.
 * Esta clase se utiliza para encapsular errores técnicos que ocurren durante
 * el acceso a datos, como fallos en la conexión SQL o errores de E/S.
 * * @author golea
 * @version 1.0
 */
public class PersistenciaException extends Exception {

    /**
     * Construye una nueva excepción de persistencia con un mensaje descriptivo.
     * * @param message El mensaje detallado que explica la causa del error.
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Construye una nueva excepción de persistencia con un mensaje y la causa original.
     * captura una SQLException y relanzarla como PersistenciaException sin perder
     * el stack trace original.
     * * @param message El mensaje detallado del error.
     * @param cause La causa original (generalmente una excepción de nivel inferior).
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}