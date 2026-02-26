/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

import java.util.Date;

/**
 * Representa un pedido que ha sido programado o agendado por un cliente.
 * Esta clase extiende de {@link Pedido} e incorpora la vinculación con el cliente 
 * que realiza la compra y, opcionalmente, un cupón de descuento aplicado.
 * * @author joser
 */
public class PedidoProgramado extends Pedido {

    private int idPedidoProgramado;
    private int idCliente;
    private int idCupon;

    /**
     * Constructor por defecto.
     */
    public PedidoProgramado() {
    }

    /**
     * Constructor para inicializar un pedido programado con datos básicos de la
     * superclase.
     *
     * * @param id Identificador único del pedido base.
     * @param fecha Fecha en la que se registra el pedido.
     * @param total Monto total de la transacción.
     * @param estado Estado actual del pedido (ej. "Agendado").
     */
    public PedidoProgramado(int id, Date fecha, float total, String estado) {
        super(id, fecha, total, estado);
    }

    /**
     * Constructor completo que inicializa los atributos de la superclase y las
     * referencias específicas.
     *
     * * @param idPedidoProgramado Identificador único de la extensión de
     * pedido programado.
     * @param idCliente Referencia al {@link Cliente} que realizó el pedido.
     * @param idCupon Referencia al {@link Cupon} aplicado (puede ser 0 si no
     * hay cupón).
     * @param id ID del pedido base.
     * @param fecha Fecha de realización.
     * @param total Monto total.
     * @param estado Estado del pedido.
     */
    public PedidoProgramado(int idPedidoProgramado, int idCliente, int idCupon, int id, Date fecha, float total, String estado) {
        super(id, fecha, total, estado);
        this.idPedidoProgramado = idPedidoProgramado;
        this.idCliente = idCliente;
        this.idCupon = idCupon;
    }

    /**
     * Constructor simplificado para la creación de nuevos pedidos programados.
     *
     * * @param fecha Fecha de creación.
     * @param total Importe total.
     * @param estado Estado inicial.
     */
    public PedidoProgramado(Date fecha, float total, String estado) {
        super(fecha, total, estado);
    }

    /**
     * @return El identificador de la extensión de pedido programado.
     */
    public int getIdPedidoProgramado() {
        return idPedidoProgramado;
    }

    /**
     * @param idPedidoProgramado El nuevo ID programado a asignar.
     */
    public void setIdPedidoProgramado(int idPedidoProgramado) {
        this.idPedidoProgramado = idPedidoProgramado;
    }

    /**
     * @return El identificador del cliente asociado.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente El ID del cliente que programa el pedido.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return El identificador del cupón utilizado.
     */
    public int getIdCupon() {
        return idCupon;
    }

    /**
     * @param idCupon El ID del cupón a aplicar al pedido.
     */
    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }
}
