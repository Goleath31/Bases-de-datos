/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 * Data Transfer Object (DTO) que representa el detalle específico de un pedido.
 * Vincula un pedido con un producto y especifica las condiciones de esa línea
 * de venta.
 *
 * * @author golea
 */
public class DetallePedidoDTO {

    private int idPedido;
    private int idProducto;
    private int cantidad;
    private String notas;
    private double precioUnitario;

    /**
     * Constructor por defecto.
     */
    public DetallePedidoDTO() {
    }

    /**
     * Constructor completo para inicializar todos los atributos del detalle del
     * pedido.
     *
     * * @param idPedido Identificador del pedido al que pertenece este
     * detalle.
     * @param idProducto Identificador del producto solicitado.
     * @param cantidad Número de unidades del producto.
     * @param notas Observaciones o especificaciones adicionales para este
     * artículo.
     * @param precioUnitario Precio por unidad aplicado en el momento del
     * pedido.
     */
    public DetallePedidoDTO(int idPedido, int idProducto, int cantidad, String notas, double precioUnitario) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.notas = notas;
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return El identificador del pedido.
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido El identificador del pedido a establecer.
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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
     * @return La cantidad de productos solicitados.
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
     * @return Las notas o comentarios del artículo.
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

    /**
     * @return El precio unitario registrado.
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario El precio unitario a establecer.
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
