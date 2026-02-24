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
public class PedidoProgramado extends Pedido {
    private int idPedidoProgramado;
    private int idCliente;
    private int idCupon;

    public PedidoProgramado() {
    }
    
    
    public PedidoProgramado(int id, Date fecha, float total, String estado) {
        super(id, fecha, total, estado);
    }

    public PedidoProgramado(int idPedidoProgramado, int idCliente, int idCupon, int id, Date fecha, float total, String estado) {
        super(id, fecha, total, estado);
        this.idPedidoProgramado = idPedidoProgramado;
        this.idCliente = idCliente;
        this.idCupon = idCupon;
    }

    public PedidoProgramado(Date fecha, float total, String estado) {
        super(fecha, total, estado);
    }

    public int getIdPedidoProgramado() {
        return idPedidoProgramado;
    }

    public void setIdPedidoProgramado(int idPedidoProgramado) {
        this.idPedidoProgramado = idPedidoProgramado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }
    
    
    
}
