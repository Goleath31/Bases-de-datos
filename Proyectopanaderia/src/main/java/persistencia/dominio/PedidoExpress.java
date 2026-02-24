/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

import java.util.Date;

/**
 *
 * @author joser
 */
public class PedidoExpress extends Pedido{
    private int idPedidoExpress;
    private String folio;
    private String pinSeguridad;
    
    public PedidoExpress(int id, Date fecha, float total, String estado) {
        super(id, fecha, total, estado);
    }

    public PedidoExpress(int idPedidoExpress, String folio, String pinSeguridad, int id, Date fecha, float total, String estado) {
        super(id, fecha, total, estado);
        this.idPedidoExpress = idPedidoExpress;
        this.folio = folio;
        this.pinSeguridad = pinSeguridad;
    }

    public PedidoExpress() {
    }

    public PedidoExpress(Date fecha, float total, String estado) {
        super(fecha, total, estado);
    }

    public int getIdPedidoExpress() {
        return idPedidoExpress;
    }

    public void setIdPedidoExpress(int idPedidoExpress) {
        this.idPedidoExpress = idPedidoExpress;
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
