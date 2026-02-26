/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.List;
import negocio.DTOs.DetallePedidoDTO;
import negocio.DTOs.PedidoAgendadoDTO;
import negocio.DTOs.PedidoDTO;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.DTOs.PedidoExpressDTO;
import negocio.DTOs.PedidoNuevoDTO;
import negocio.excepciones.NegocioException;
import persistencia.dominio.Cupon;
import persistencia.dominio.DetallePedido;

/**
 * Interfaz principal de Negocio para la gestión de Pedidos. Orquestra el flujo
 * de venta desde el registro inicial hasta la entrega final, gestionando
 * estados, validaciones de seguridad (PIN) y cupones.
 */
public interface IPedidoBO {

    /**
     * Realiza el registro simplificado de un nuevo pedido en el sistema.
     *
     * @param pedido DTO con la información base del pedido.
     * @throws NegocioException Si los datos de la orden son incompletos.
     */
    public void registrarPedido(PedidoNuevoDTO pedido) throws NegocioException;

    /**
     * Busca pedidos que ya han sido preparados y están listos para entregarse
     * al cliente.
     *
     * @param filtro Criterio de búsqueda (folio, nombre cliente).
     * @return Lista de pedidos en fase de entrega.
     * @throws NegocioException Si el proceso de búsqueda falla.
     */
    public List<PedidoEntregaDTO> buscarPedidosPendientesEntrega(String filtro) throws NegocioException;

    /**
     * Valida el PIN de seguridad proporcionado por el cliente para recolectar
     * un pedido Express.
     *
     * @param idPedido ID del pedido a validar.
     * @param pin Código de seguridad ingresado por el usuario.
     * @throws NegocioException Si el PIN es incorrecto o el pedido no es tipo
     * Express.
     */
    public void validarPinExpress(int idPedido, String pin) throws NegocioException;

    /**
     * Finaliza el proceso de venta registrando el pago y marcando el pedido
     * como entregado.
     *
     * @param idPedido ID del pedido a cerrar.
     * @param metodoPago Forma en la que se liquidó la cuenta.
     * @throws NegocioException Si el pedido no está en un estado apto para
     * entrega.
     */
    public void procesarEntrega(int idPedido, String metodoPago) throws NegocioException;

    /**
     * Consulta la lista de cupones de descuento que pueden aplicarse
     * actualmente.
     *
     * @return Lista de entidades {@link Cupon} válidas.
     * @throws NegocioException Si hay errores en la consulta de promociones.
     */
    public List<Cupon> obtenerCuponesVigentes() throws NegocioException;

    /**
     * Lista los pedidos que se encuentran actualmente en el área de
     * cocina/preparación.
     *
     * @return Lista de pedidos por preparar.
     * @throws NegocioException Si ocurre un error al recuperar la cola de
     * producción.
     */
    public List<PedidoEntregaDTO> listarPedidosPreparacion() throws NegocioException;

    /**
     * Gestiona la máquina de estados del pedido (ej. de 'Pendiente' a 'En
     * Preparación').
     *
     * @param idPedido ID del pedido a mover.
     * @param estadoActual Estado en el que se encuentra antes del cambio.
     * @throws NegocioException Si la transición de estado es inválida.
     */
    public void avanzarEstado(int idPedido, String estadoActual) throws NegocioException;

    /**
     * Registra un pedido con fecha programada junto con todo su detalle de
     * productos.
     *
     * @param pedido Información general de la agenda.
     * @param detalles Lista de productos y cantidades.
     * @throws NegocioException Si la fecha no es válida o el detalle está
     * vacío.
     */
    public void registrarPedidoAgendado(PedidoAgendadoDTO pedido, List<DetallePedidoDTO> detalles) throws NegocioException;

    /**
     * Registra una venta inmediata (Express) con sus respectivos productos.
     *
     * @param pedidoExpress Datos de la venta inmediata.
     * @param detalles Productos solicitados.
     * @throws NegocioException Si hay errores en el cálculo de totales o stock.
     */
    public void registrarPedidoExpress(PedidoExpressDTO pedidoExpress, List<DetallePedidoDTO> detalles) throws NegocioException;

    /**
     * Realiza una búsqueda exhaustiva de pedidos utilizando múltiples criterios
     * de filtrado.
     *
     * @param filtro Cadena de texto para filtrado avanzado.
     * @return Lista de pedidos encontrados.
     * @throws NegocioException Si el filtro es inválido.
     */
    public List<PedidoEntregaDTO> buscarPedidosAvanzado(String filtro) throws NegocioException;

}
