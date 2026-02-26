/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 *
 * @author joser
 */
public class PedidoExpressDTO {
    private int idPedido;
    private String folio;
    private String pinSeguridad;

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

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getPinSeguridad() {
        return pinSeguridad;
    }

    public void setPinSeguridad(String pinSeguridad) {
        this.pinSeguridad = pinSeguridad;
    }
    
    
    
}
