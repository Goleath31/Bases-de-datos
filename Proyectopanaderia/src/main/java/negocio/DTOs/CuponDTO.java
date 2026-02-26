/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

import java.util.Date;

/**
 * Data Transfer Object (DTO) que representa la información de un Cupón. Se
 * utiliza para gestionar los datos de descuentos y su vigencia dentro del
 * sistema.
 *
 * * @author joser
 */
public class CuponDTO {

    private int idCupon;
    private String codigo;
    private float descuento;
    private Date vigencia;
    private int usosActuales;

    /**
     * Constructor vacío por defecto.
     */
    public CuponDTO() {
    }

    /**
     * @return El identificador único del cupón.
     */
    public int getIdCupon() {
        return idCupon;
    }

    /**
     * @param idCupon El identificador único a establecer.
     */
    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }

    /**
     * @return El código alfanumérico del cupón.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo El código promocional a establecer.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return El valor o porcentaje de descuento del cupón.
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * @param descuento El valor de descuento a establecer.
     */
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    /**
     * @return La fecha de vencimiento del cupón.
     */
    public Date getVigencia() {
        return vigencia;
    }

    /**
     * @param vigencia La fecha límite de uso a establecer.
     */
    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    /**
     * @return La cantidad de veces que el cupón ha sido utilizado.
     */
    public int getUsosActuales() {
        return usosActuales;
    }

    /**
     * @param usosActuales El número de usos realizados a establecer.
     */
    public void setUsosActuales(int usosActuales) {
        this.usosActuales = usosActuales;
    }

}
