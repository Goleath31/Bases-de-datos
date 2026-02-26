/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 * Data Transfer Object (DTO) que representa el detalle de una transacción o
 * pedido. Contiene información específica sobre un producto, su cantidad y el
 * precio aplicado.
 *
 * * @author golea
 */
public class DetalleDTO {

    private int idProducto;
    private int cantidad;
    private double precioVenta;
    private String notas;

    /**
     * Constructor por defecto.
     */
    public DetalleDTO() {
    }

    /**
     * Constructor con todos los parámetros para inicializar el detalle.
     *
     * * @param idProducto Identificador único del producto.
     * @param cantidad Cantidad de unidades del producto.
     * @param precioVenta Precio unitario al que se realiza la venta.
     * @param notas Observaciones o comentarios adicionales sobre el detalle.
     */
    public DetalleDTO(int idProducto, int cantidad, double precioVenta, String notas) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.notas = notas;
    }

    /**
     * @return El identificador del producto.
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
     * @return La cantidad de productos.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad La cantidad a establecer.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return El precio de venta unitario.
     */
    public double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * @param precioVenta El precio de venta a establecer.
     */
    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * @return Las notas o comentarios del detalle.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * @param notas Las notas a establecer.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }
}
