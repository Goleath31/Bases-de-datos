/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

/**
 * Representa un número telefónico asociado a un cliente. Esta clase permite
 * gestionar múltiples contactos por usuario, clasificándolos mediante etiquetas
 * (ej. "Casa", "Trabajo") para facilitar la comunicación.
 *
 * * @author joser
 */
public class Telefono {

    private int id;
    private String numero;
    private String etiqueta;
    private int id_cliente;

    /**
     * Constructor por defecto.
     */
    public Telefono() {
    }

    /**
     * Constructor para la creación de nuevos registros (sin ID). Útil para
     * añadir un teléfono a un cliente antes de que la base de datos genere su
     * identificador.
     *
     * * @param numero Cadena con los dígitos del teléfono.
     * @param etiqueta Descripción del tipo de teléfono (ej. "Celular").
     * @param id_cliente Identificador del cliente al que pertenece este número.
     */
    public Telefono(String numero, String etiqueta, int id_cliente) {
        this.numero = numero;
        this.etiqueta = etiqueta;
        this.id_cliente = id_cliente;
    }

    /**
     * Constructor completo para recuperar registros existentes.
     *
     * * @param id Identificador único del registro telefónico.
     * @param numero Cadena con los dígitos del teléfono.
     * @param etiqueta Etiqueta identificadora del contacto.
     * @param id_cliente ID del cliente propietario del número.
     */
    public Telefono(int id, String numero, String etiqueta, int id_cliente) {
        this.id = id;
        this.numero = numero;
        this.etiqueta = etiqueta;
        this.id_cliente = id_cliente;
    }

    /**
     * @param id El nuevo identificador único a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return El identificador único del teléfono.
     */
    public int getId() {
        return id;
    }

    /**
     * @return La cadena de dígitos del número telefónico.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero El nuevo número a registrar.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return El tipo o etiqueta asignada al teléfono.
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta La nueva etiqueta (ej. "Emergencia").
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @return El identificador del cliente vinculado.
     */
    public int getId_cliente() {
        return id_cliente;
    }
}
