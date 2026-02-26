/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

import java.util.Date;

/**
 * Data Transfer Object (DTO) que representa la información general de un
 * Pedido. Encapsula los datos maestros de la transacción, incluyendo el monto
 * total, el estado actual y el personal responsable.
 *
 * * @author joser
 */
public class PedidoDTO {

    private int idPedido;
    private Date fechahora;
    private float total;
    private String estado;
    private int idEmpleado;

    /**
     * Constructor completo para inicializar un pedido con todos sus datos,
     * incluyendo identificadores de sistema y empleado.
     *
     * * @param idPedido Identificador único del pedido.
     * @param fechahora Fecha y hora en que se registró el pedido.
     * @param total Monto total de la transacción.
     * @param estado Estado actual (ej. Pendiente, Pagado, Cancelado).
     * @param idEmpleado Identificador del empleado que procesó el pedido.
     */
    public PedidoDTO(int idPedido, Date fechahora, float total, String estado, int idEmpleado) {
        this.idPedido = idPedido;
        this.fechahora = fechahora;
        this.total = total;
        this.estado = estado;
        this.idEmpleado = idEmpleado;
    }

    /**
     * Constructor para la creación de nuevos pedidos donde el ID aún no ha sido
     * asignado por la base de datos, pero ya existe un empleado responsable.
     *
     * * @param fechahora Fecha y hora del registro.
     * @param total Monto total.
     * @param estado Estado inicial del pedido.
     * @param idEmpleado ID del empleado asignado.
     */
    public PedidoDTO(Date fechahora, float total, String estado, int idEmpleado) {
        this.fechahora = fechahora;
        this.total = total;
        this.estado = estado;
        this.idEmpleado = idEmpleado;
    }

    /**
     * Constructor para consultas de pedidos existentes donde no se requiere la
     * información del empleado.
     *
     * * @param idPedido Identificador único del pedido.
     * @param fechahora Fecha y hora del registro.
     * @param total Monto total.
     * @param estado Estado del pedido.
     */
    public PedidoDTO(int idPedido, Date fechahora, float total, String estado) {
        this.idPedido = idPedido;
        this.fechahora = fechahora;
        this.total = total;
        this.estado = estado;
    }

    /**
     * Constructor simplificado para inicialización básica de datos de venta.
     *
     * * @param fechahora Fecha y hora del registro.
     * @param total Monto total.
     * @param estado Estado del pedido.
     */
    public PedidoDTO(Date fechahora, float total, String estado) {
        this.fechahora = fechahora;
        this.total = total;
        this.estado = estado;
    }

    /**
     * Constructor vacío por defecto.
     */
    public PedidoDTO() {
    }

    /**
     * @return El identificador único del pedido.
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido El ID del pedido a establecer.
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return La fecha y hora del pedido.
     */
    public Date getFechahora() {
        return fechahora;
    }

    /**
     * @param fechahora La fecha y hora a establecer.
     */
    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    /**
     * @return El total de la venta.
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total El monto total a establecer.
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return El estado del pedido (ej. "Entregado").
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado El estado a establecer.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return El identificador del empleado responsable.
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado El ID del empleado a establecer.
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
