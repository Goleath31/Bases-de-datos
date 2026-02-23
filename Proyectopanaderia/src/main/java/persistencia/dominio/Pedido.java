/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

import java.util.Date;

/**
 *
 * @author golea
 */
public class Pedido {
    private int id;
    private Date fecha;
    private float total;
    private String estado;

    public Pedido(int id, Date fecha, float total, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }
    
    
    public Pedido(Date fecha, float total, String estado) {
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
}