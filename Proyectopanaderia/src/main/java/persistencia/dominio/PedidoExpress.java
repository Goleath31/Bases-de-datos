/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

import java.util.Date;

/**
 * Representa una modalidad de pedido rápido o "Express" en el sistema. Hereda
 * los atributos básicos de la clase {@link Pedido} y añade campos de seguridad
 * y seguimiento como folios y pines.
 *
 * * @author joser
 */
public class PedidoExpress extends Pedido {

    private int idPedidoExpress;
    private String folio;
    private String pinSeguridad;

    /**
     * Constructor que inicializa un pedido express con los datos básicos del
     * pedido padre.
     *
     * * @param id Identificador del pedido.
     * @param fecha Fecha de registro.
     * @param total Monto total.
     * @param estado Estado del pedido.
     */
    public PedidoExpress(int id, Date fecha, float total, String estado) {
        super(id, fecha, total, estado);
    }

    /**
     * Constructor completo que inicializa tanto los datos del pedido padre como
     * los campos específicos de la entrega express.
     *
     * * @param idPedidoExpress Identificador único de la extensión express.
     * @param folio Código de seguimiento único para el cliente.
     * @param pinSeguridad Código de verificación para la entrega.
     * @param id ID del pedido base.
     * @param fecha Fecha de la transacción.
     * @param total Monto total.
     * @param estado Estado actual (ej. "En ruta").
     */
    public PedidoExpress(int idPedidoExpress, String folio, String pinSeguridad, int id, Date fecha, float total, String estado) {
        super(id, fecha, total, estado);
        this.idPedidoExpress = idPedidoExpress;
        this.folio = folio;
        this.pinSeguridad = pinSeguridad;
    }

    /**
     * Constructor por defecto.
     */
    public PedidoExpress() {
    }

    /**
     * Constructor para nuevos pedidos express (sin ID de pedido base).
     *
     * * @param fecha Fecha de creación.
     * @param total Importe total.
     * @param estado Estado inicial.
     */
    public PedidoExpress(Date fecha, float total, String estado) {
        super(fecha, total, estado);
    }

    /**
     * @return El identificador de la extensión express.
     */
    public int getIdPedidoExpress() {
        return idPedidoExpress;
    }

    /**
     * @param idPedidoExpress El nuevo ID express a asignar.
     */
    public void setIdPedidoExpress(int idPedidoExpress) {
        this.idPedidoExpress = idPedidoExpress;
    }

    /**
     * @return El folio de seguimiento del pedido.
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio El nuevo folio alfanumérico.
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return El pin de seguridad para la validación de entrega.
     */
    public String getPinSeguridad() {
        return pinSeguridad;
    }

    /**
     * @param pinSeguridad El nuevo pin de seguridad.
     */
    public void setPinSeguridad(String pinSeguridad) {
        this.pinSeguridad = pinSeguridad;
    }
}
