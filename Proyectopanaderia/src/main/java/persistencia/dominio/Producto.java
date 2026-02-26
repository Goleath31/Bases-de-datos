/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

/**
 * Representa un producto dentro del catálogo del sistema. Esta clase almacena
 * la información comercial y descriptiva de los artículos, incluyendo su
 * precio, categoría y disponibilidad actual.
 *
 * * @author golea
 */
public class Producto {

    private int id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private float precio;
    private String estado;

    /**
     * Constructor para la creación de nuevos productos (sin ID). Ideal para
     * registros iniciales que aún no han sido persistidos en la base de datos.
     *
     * * @param nombre Nombre comercial del producto.
     * @param tipo Categoría o tipo de producto (ej. "Bebida", "Platillo").
     * @param descripcion Detalle extendido de los componentes o
     * características.
     * @param precio Valor unitario de venta.
     * @param estado Disponibilidad actual del producto (ej. "Activo",
     * "Agotado").
     */
    public Producto(String nombre, String tipo, String descripcion, float precio, String estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

    /**
     * Constructor por defecto.
     */
    public Producto() {
    }

    /**
     * @return La categoría o tipo del producto.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo El nuevo tipo de producto a asignar.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return La descripción detallada del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion El nuevo texto descriptivo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return El estado de disponibilidad actual.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado El nuevo estado (ej. "Activo", "Inactivo").
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return El identificador único del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id El nuevo ID generado por el sistema.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return El nombre comercial del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El precio unitario de venta.
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio El nuevo valor monetario del producto.
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
