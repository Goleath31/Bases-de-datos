/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

import java.util.Date;

/**
 *
 * @author joser
 */
public class PedidoDTO {
    private int idPedido;
    private Date fechahora;
    private float total;
    private String estado;
    private int idEmpleado;

    public PedidoDTO(int idPedido, Date fechahora, float total, String estado, int idEmpleado) {
        this.idPedido = idPedido;
        this.fechahora = fechahora;
        this.total = total;
        this.estado = estado;
        this.idEmpleado = idEmpleado;
    }

    public PedidoDTO(Date fechahora, float total, String estado, int idEmpleado) {
        this.fechahora = fechahora;
        this.total = total;
        this.estado = estado;
        this.idEmpleado = idEmpleado;
    }

    public PedidoDTO(int idPedido, Date fechahora, float total, String estado) {
        this.idPedido = idPedido;
        this.fechahora = fechahora;
        this.total = total;
        this.estado = estado;
    }

    public PedidoDTO(Date fechahora, float total, String estado) {
        this.fechahora = fechahora;
        this.total = total;
        this.estado = estado;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    
}
