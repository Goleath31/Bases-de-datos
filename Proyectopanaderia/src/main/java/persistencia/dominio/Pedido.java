/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

import java.util.Date;
import java.util.List;

/**
 * Representa un pedido o transacción dentro del sistema. Esta clase centraliza
 * la información general de una venta, incluyendo la fecha, el monto total, el
 * estado actual y el empleado responsable de gestionarlo.
 *
 * * @author golea
 */
public class Pedido {

    private int id;
    private Date fecha;
    private float total;
    private String estado;
    private int idEmpleado;
    //private List<DetallePedido> detalles;

    /**
     * Constructor completo para recuperar pedidos existentes con asignación de
     * empleado.
     *
     * * @param id Identificador único del pedido.
     * @param fecha Fecha y hora en la que se realizó el pedido.
     * @param total Monto económico total de la transacción.
     * @param estado Situación actual del pedido (ej. "Pendiente", "Entregado").
     * @param idEmpleado ID del empleado que procesó o agendó el pedido.
     */
    public Pedido(int id, Date fecha, float total, String estado, int idEmpleado) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.idEmpleado = idEmpleado;
    }

    /**
     * Constructor para nuevos pedidos con empleado asignado
     *
     * * @param fecha Fecha de registro.
     * @param total Suma total de los productos.
     * @param estado Estado inicial del pedido.
     * @param idEmpleado Identificador del empleado responsable.
     */
    public Pedido(Date fecha, float total, String estado, int idEmpleado) {
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.idEmpleado = idEmpleado;
    }

    /**
     * Constructor simplificado para pedidos sin empleado asignado inicialmente.
     *
     * * @param fecha Fecha de registro.
     * @param total Monto total.
     * @param estado Estado del pedido.
     */
    public Pedido(Date fecha, float total, String estado) {
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    /**
     * Constructor para recuperar pedidos donde el empleado es opcional o se
     * carga después.
     *
     * * @param id Identificador único.
     * @param fecha Fecha de registro.
     * @param total Monto total.
     * @param estado Estado actual.
     */
    public Pedido(int id, Date fecha, float total, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    /**
     * Constructor por defecto requerido por frameworks de persistencia.
     */
    public Pedido() {
    }

    /**
     * @return El identificador único del pedido.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id El nuevo identificador del pedido.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return La fecha de realización del pedido.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha La nueva fecha del pedido.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return El monto total de la venta.
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total El nuevo monto total.
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return El estado actual del pedido.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado El nuevo estado (ej. "Cancelado", "Pagado").
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return El ID del empleado vinculado a este pedido.
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado El identificador del empleado responsable.
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
