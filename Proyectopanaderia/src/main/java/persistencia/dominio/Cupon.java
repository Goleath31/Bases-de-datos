/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

import java.util.Date;


public class Cupon {
    private int id;
    private String codigo;
    private float descuento;
    private Date fechaVigencia;
    private int limiteUsos;
    private int usosActuales;

    public Cupon(String codigo, float descuento, Date fechaVigencia, int limiteUsos, int usosActuales) {
        this.codigo = codigo;
        this.descuento = descuento;
        this.fechaVigencia = fechaVigencia;
        this.limiteUsos = limiteUsos;
        this.usosActuales = usosActuales;
    }

    public Cupon(int id, String codigo, float descuento, Date fechaVigencia, int limiteUsos, int usosActuales) {
        this.id = id;
        this.codigo = codigo;
        this.descuento = descuento;
        this.fechaVigencia = fechaVigencia;
        this.limiteUsos = limiteUsos;
        this.usosActuales = usosActuales;
    }

    public Cupon() {
    }

    public Cupon(int id, String codigo, float descuento) {
        this.id = id;
        this.codigo = codigo;
        this.descuento = descuento;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public int getLimiteUsos() {
        return limiteUsos;
    }

    public void setLimiteUsos(int limiteUsos) {
        this.limiteUsos = limiteUsos;
    }

    public int getUsosActuales() {
        return usosActuales;
    }

    public void setUsosActuales(int usosActuales) {
        this.usosActuales = usosActuales;
    }
    
    

    
    
    
    
}