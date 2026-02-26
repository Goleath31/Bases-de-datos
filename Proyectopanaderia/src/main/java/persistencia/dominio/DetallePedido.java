/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

/**
 * Representa el desglose individual de un producto dentro de un pedido. Esta
 * clase vincula un producto específico con un pedido, incluyendo la cantidad
 * solicitada, anotaciones especiales y el cálculo del subtotal.
 *
 * * @author joser
 */
public class DetallePedido {

    private int idDetallePedido;
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private String notas;
    private float subtotal;

    /**
     * Constructor completo con ID propio y subtotal calculado.
     *
     * * @param idDetallePedido Identificador único de la línea de detalle.
     * @param idPedido Referencia al ID del pedido padre.
     * @param idProducto Referencia al ID del producto solicitado.
     * @param cantidad Número de unidades del producto.
     * @param notas Instrucciones especiales (ej. "Sin cebolla", "Empaque de
     * regalo").
     * @param subtotal Monto económico resultante (cantidad * precio unitario).
     */
    public DetallePedido(int idDetallePedido, int idPedido, int idProducto, int cantidad, String notas, float subtotal) {
        this.idDetallePedido = idDetallePedido;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.notas = notas;
        this.subtotal = subtotal;
    }

    /**
     * Constructor con ID propio pero sin subtotal inicial.
     */
    public DetallePedido(int idDetallePedido, int idPedido, int idProducto, int cantidad, String notas) {
        this.idDetallePedido = idDetallePedido;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.notas = notas;
    }

    /**
     * Constructor sin ID de detalle, con subtotal. Ideal para crear nuevos
     * registros antes de persistirlos en la BD.
     */
    public DetallePedido(int idPedido, int idProducto, int cantidad, String notas, float subtotal) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.notas = notas;
        this.subtotal = subtotal;
    }

    /**
     * Constructor simplificado para inicializar una línea de pedido básica.
     */
    public DetallePedido(int idPedido, int idProducto, int cantidad, String notas) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.notas = notas;
    }

    /**
     * Constructor por defecto.
     */
    public DetallePedido() {
    }

    /**
     * @return El identificador único de esta línea de detalle.
     */
    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    /**
     * @param idDetallePedido El nuevo ID de la línea de detalle.
     */
    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    /**
     * @return El ID del pedido al que pertenece este detalle.
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido El ID del pedido padre.
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return El ID del producto asociado.
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto El ID del producto.
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return La cantidad de unidades.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad La nueva cantidad de unidades.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return Las notas o comentarios adicionales del producto.
     */
    public String getNotas() {
        return notas;
    }

    /**
     * @param notas El texto de las observaciones.
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * @return El subtotal calculado para esta línea.
     */
    public float getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal El monto del subtotal.
     */
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
