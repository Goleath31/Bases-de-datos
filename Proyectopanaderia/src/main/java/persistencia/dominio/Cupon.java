/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

import java.util.Date;

/**
 * Representa un cupón de descuento dentro del sistema. Esta entidad contiene la
 * lógica de negocio para validar la vigencia, el porcentaje de descuento y los
 * límites de uso de las promociones.
 *
 * * @author joser
 */
public class Cupon {

    private int id;
    private String codigo;
    private float descuento;
    private Date fechaVigencia;
    private int limiteUsos;
    private int usosActuales;

    /**
     * Constructor para crear un nuevo cupón sin ID (para inserción).
     *
     * * @param codigo Cadena alfanumérica única del cupón.
     * @param descuento Valor numérico del descuento (ej. 0.15 para 15%).
     * @param fechaVigencia Fecha límite para utilizar el cupón.
     * @param limiteUsos Cantidad máxima de veces que puede ser canjeado.
     * @param usosActuales Contador de veces que ha sido utilizado hasta el
     * momento.
     */
    public Cupon(String codigo, float descuento, Date fechaVigencia, int limiteUsos, int usosActuales) {
        this.codigo = codigo;
        this.descuento = descuento;
        this.fechaVigencia = fechaVigencia;
        this.limiteUsos = limiteUsos;
        this.usosActuales = usosActuales;
    }

    /**
     * Constructor completo para recuperar cupones existentes de la base de
     * datos.
     *
     * * @param id Identificador único del cupón.
     * @param codigo Código alfanumérico.
     * @param descuento Monto o porcentaje de descuento.
     * @param fechaVigencia Fecha de expiración.
     * @param limiteUsos Capacidad máxima de redención.
     * @param usosActuales Redenciones registradas actualmente.
     */
    public Cupon(int id, String codigo, float descuento, Date fechaVigencia, int limiteUsos, int usosActuales) {
        this.id = id;
        this.codigo = codigo;
        this.descuento = descuento;
        this.fechaVigencia = fechaVigencia;
        this.limiteUsos = limiteUsos;
        this.usosActuales = usosActuales;
    }

    /**
     * Constructor por defecto para inicializaciones vacías o frameworks.
     */
    public Cupon() {
    }

    /**
     * Constructor simplificado para consultas rápidas de valor.
     *
     * * @param id Identificador único.
     * @param codigo Código del cupón.
     * @param descuento Valor del descuento.
     */
    public Cupon(int id, String codigo, float descuento) {
        this.id = id;
        this.codigo = codigo;
        this.descuento = descuento;
    }

    /**
     * @return El identificador único del cupón.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id El nuevo ID a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return El código alfanumérico del cupón.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo El código promocional.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return El valor del descuento.
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * @param descuento El porcentaje o monto a descontar.
     */
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    /**
     * @return La fecha de vencimiento del cupón.
     */
    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * @param fechaVigencia La nueva fecha de vigencia.
     */
    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    /**
     * @return El límite total de usos permitidos.
     */
    public int getLimiteUsos() {
        return limiteUsos;
    }

    /**
     * @param limiteUsos El máximo de usos permitidos.
     */
    public void setLimiteUsos(int limiteUsos) {
        this.limiteUsos = limiteUsos;
    }

    /**
     * @return El número de veces que ya se ha usado el cupón.
     */
    public int getUsosActuales() {
        return usosActuales;
    }

    /**
     * @param usosActuales El contador de usos actuales.
     */
    public void setUsosActuales(int usosActuales) {
        this.usosActuales = usosActuales;
    }
}
