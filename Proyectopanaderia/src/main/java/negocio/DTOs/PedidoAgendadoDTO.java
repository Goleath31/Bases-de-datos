/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 * Data Transfer Object (DTO) que representa la vinculación de un pedido
 * agendado. Esta clase se utiliza para asociar a un cliente con un cupón
 * específico al momento de programar o registrar un pedido en el sistema.
 *
 * * @author joser
 */
public class PedidoAgendadoDTO {

    private int idCliente;
    private int idCupon;

    /**
     * Constructor con parámetros para inicializar la asociación del pedido.
     *
     * * @param idCliente Identificador único del cliente que realiza el
     * pedido.
     * @param idCupon Identificador único del cupón aplicado al pedido.
     */
    public PedidoAgendadoDTO(int idCliente, int idCupon) {
        this.idCliente = idCliente;
        this.idCupon = idCupon;
    }

    /**
     * Constructor vacío por defecto.
     */
    public PedidoAgendadoDTO() {
    }

    /**
     * Obtiene el identificador del cliente.
     *
     * * @return El ID del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el identificador del cliente.
     *
     * * @param idCliente El ID del cliente a establecer.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el identificador del cupón vinculado.
     *
     * * @return El ID del cupón.
     */
    public int getIdCupon() {
        return idCupon;
    }

    /**
     * Establece el identificador del cupón vinculado.
     *
     * * @param idCupon El ID del cupón a establecer.
     */
    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }
}
