/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

import java.util.List;
import java.util.ArrayList;

/**
 * Data Transfer Object (DTO) que representa la creación de un nuevo pedido en
 * el sistema. Este objeto es complejo ya que encapsula la información del
 * cliente, el encabezado del pedido y la lista de productos (detalles)
 * asociados a la transacción.
 *
 * * * @author golea
 */
public class PedidoNuevoDTO {

    private int idCliente;
    private double montoTotal;
    private String tipoPedido;
    private List<DetalleDTO> detalles;

    /**
     * Constructor por defecto. Inicializa la lista de detalles como un
     * ArrayList vacío para evitar errores de puntero nulo
     * (NullPointerException).
     */
    public PedidoNuevoDTO() {
        this.detalles = new ArrayList<>();
    }

    /**
     * Constructor completo para inicializar un nuevo pedido con todos sus
     * componentes.
     *
     * * * @param idCliente Identificador único del cliente que realiza la
     * compra.
     * @param montoTotal Suma total calculada de todos los productos en el
     * pedido.
     * @param tipoPedido Categoría del pedido (ej. "Express", "Agendado",
     * "Local").
     * @param detalles Lista de objetos {@link DetalleDTO} que contienen los
     * productos y cantidades.
     */
    public PedidoNuevoDTO(int idCliente, double montoTotal, String tipoPedido, List<DetalleDTO> detalles) {
        this.idCliente = idCliente;
        this.montoTotal = montoTotal;
        this.tipoPedido = tipoPedido;
        this.detalles = detalles;
    }

    /**
     * @return El identificador del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente El ID del cliente a establecer.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return El monto total acumulado del pedido.
     */
    public double getMontoTotal() {
        return montoTotal;
    }

    /**
     * @param montoTotal El monto total a establecer.
     */
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * @return La descripción del tipo de pedido.
     */
    public String getTipoPedido() {
        return tipoPedido;
    }

    /**
     * @param tipoPedido El tipo de pedido a establecer.
     */
    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    /**
     * * Obtiene la lista de detalles del pedido.
     *
     * @return Una lista de objetos {@link DetalleDTO}.
     */
    public List<DetalleDTO> getDetalles() {
        return detalles;
    }

    /**
     * * Establece la lista de detalles del pedido.
     *
     * @param detalles La lista de detalles a asignar.
     */
    public void setDetalles(List<DetalleDTO> detalles) {
        this.detalles = detalles;
    }
}
