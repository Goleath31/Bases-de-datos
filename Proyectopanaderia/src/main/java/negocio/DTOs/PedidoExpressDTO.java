/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 * Data Transfer Object (DTO) que representa un Pedido de tipo Express. Esta
 * clase contiene la información de seguridad y rastreo rápido para pedidos
 * inmediatos.
 *
 * * @author joser
 */
public class PedidoExpressDTO {

    private int idPedido;
    private String folio;
    private String pinSeguridad;

    /**
     * Constructor para inicializar un pedido express con sus credenciales de
     * seguridad.
     *
     * * @param idPedido Identificador único del pedido asociado.
     * @param folio Código de seguimiento único del pedido.
     * @param pinSeguridad Código de seguridad requerido para la validación del
     * pedido.
     */
    public PedidoExpressDTO(int idPedido, String folio, String pinSeguridad) {
        this.idPedido = idPedido;
        this.folio = folio;
        this.pinSeguridad = pinSeguridad;
    }

    public PedidoExpressDTO(String folio, String pinSeguridad) {
        this.folio = folio;
        this.pinSeguridad = pinSeguridad;
    }

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
     * @return El folio de seguimiento.
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio El folio a establecer.
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return El PIN de seguridad del pedido.
     */
    public String getPinSeguridad() {
        return pinSeguridad;
    }

    /**
     * @param pinSeguridad El PIN de seguridad a establecer.
     */
    public void setPinSeguridad(String pinSeguridad) {
        this.pinSeguridad = pinSeguridad;
    }
}
