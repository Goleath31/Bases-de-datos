/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 * Data Transfer Object (DTO) que representa la información de un Producto.
 * Contiene los atributos necesarios para identificar, describir y catalogar los
 * artículos disponibles en el catálogo del sistema.
 *
 * * @author joser
 */
public class ProductoDTO {

    private int idProducto;
    private String nombre;
    private String tipo;
    private String descripcion;
    private float precio;
    private String estado;

    /**
     * Constructor vacío por defecto para la creación de instancias genéricas.
     */
    public ProductoDTO() {
    }

    /**
     * @return El identificador único del producto.
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto El identificador del producto a establecer.
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return El nombre comercial del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre comercial a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return La categoría o tipo al que pertenece el producto.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo El tipo de producto a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return La descripción detallada de las características del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion La descripción a establecer.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return El precio de lista asignado al producto.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio El precio de lista a establecer.
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return El estado actual del producto (ej. "Activo", "Agotado").
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado El estado operativo a establecer.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
