/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 * Data Transfer Object (DTO) que representa la información resumida de un
 * pedido para procesos de entrega. Esta clase agrupa datos clave del pedido y
 * el cliente para facilitar la gestión logística.
 *
 * * @author golea
 */
public class PedidoEntregaDTO {

    private int idPedido;
    private String cliente;
    private String tipoPedido;
    private float montoTotal;
    private String estado;

    /**
     * Constructor con parámetros para inicializar todos los atributos del DTO
     * de entrega.
     *
     * * @param idPedido Identificador único del pedido.
     * @param cliente Nombre o identificador del cliente que realizó el pedido.
     * @param tipoPedido Clasificación del pedido (ej. Local, Domicilio).
     * @param montoTotal Valor total a cobrar o registrado en la entrega.
     * @param estado Situación actual del proceso de entrega.
     */
    public PedidoEntregaDTO(int idPedido, String cliente, String tipoPedido, float montoTotal, String estado) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.tipoPedido = tipoPedido;
        this.montoTotal = montoTotal;
        this.estado = estado;
    }

    /**
     * Obtiene el identificador del pedido.
     *
     * @return El ID del pedido.
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Establece el identificador del pedido.
     *
     * @param idPedido El ID del pedido a establecer.
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Obtiene la información del cliente.
     *
     * @return El nombre o dato del cliente.
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Establece la información del cliente.
     *
     * @param cliente El cliente a establecer.
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene el tipo de pedido.
     *
     * @return La descripción del tipo de pedido.
     */
    public String getTipoPedido() {
        return tipoPedido;
    }

    /**
     * Establece el tipo de pedido.
     *
     * @param tipoPedido El tipo de pedido a establecer.
     */
    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    /**
     * Obtiene el monto total de la entrega.
     *
     * @return El monto total.
     */
    public float getMontoTotal() {
        return montoTotal;
    }

    /**
     * Establece el monto total de la entrega.
     *
     * @param montoTotal El monto total a establecer.
     */
    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * Obtiene el estado actual de la entrega.
     *
     * @return El estado del pedido.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la entrega.
     *
     * @param estado El estado a establecer.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
