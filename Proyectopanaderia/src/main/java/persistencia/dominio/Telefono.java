/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

/**
 *
 * @author joser
 */
public class Telefono {
    private int id;
    private String numero;
    private String etiqueta;
    private int id_cliente;

    public Telefono() {
    }

    public Telefono(String numero, String etiqueta, int id_cliente) {
        this.numero = numero;
        this.etiqueta = etiqueta;
        this.id_cliente = id_cliente;
    }

    public Telefono(int id, String numero, String etiqueta, int id_cliente) {
        this.id = id;
        this.numero = numero;
        this.etiqueta = etiqueta;
        this.id_cliente = id_cliente;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public int getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getId_cliente() {
        return id_cliente;
    }
   
    
}
