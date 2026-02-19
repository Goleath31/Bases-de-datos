/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author golea
 */
public class PedidoNuevoDTO {

    private int idCliente;
    private double montoTotal;
    private String tipoPedido;
    private List<DetalleDTO> detalles;

    public PedidoNuevoDTO() {
        this.detalles = new ArrayList<>();
    }

    public PedidoNuevoDTO(int idCliente, double montoTotal, String tipoPedido, List<DetalleDTO> detalles) {
        this.idCliente = idCliente;
        this.montoTotal = montoTotal;
        this.tipoPedido = tipoPedido;
        this.detalles = detalles;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public List<DetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDTO> detalles) {
        this.detalles = detalles;
    }
}
