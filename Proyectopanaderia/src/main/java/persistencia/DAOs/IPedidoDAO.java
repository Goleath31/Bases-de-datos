/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.util.List;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.DTOs.PedidoNuevoDTO;
import persistencia.dominio.Cupon;
import persistencia.dominio.DetallePedido;
import persistencia.dominio.PedidoExpress;
import persistencia.dominio.PedidoProgramado;
import persistencia.excepciones.PersistenciaException;

/**
 * Interfaz que define las operaciones permitidas para la gestión de pedidos.
 * Define el contrato para el manejo de estados, creación de pedidos de diversos
 * tipos y consultas de filtrado avanzado.
 */
public interface IPedidoDAO {

    /**
     * Cuenta pedidos en estados activos (no finalizados/cancelados) para un
     * cliente.
     *
     * @param idCliente ID del cliente a consultar.
     * @return Total de pedidos en curso.
     * @throws PersistenciaException Si falla la consulta SQL.
     */
    public int contarPedidosActivos(int idCliente) throws PersistenciaException;

    /**
     * Registra un pedido estándar y sus detalles de productos.
     *
     * @param pedido DTO con la información de cabecera y lista de productos.
     * @throws PersistenciaException Si ocurre un error en la transacción.
     */
    public void agregarPedido(PedidoNuevoDTO pedido) throws PersistenciaException;

    public List<PedidoEntregaDTO> buscarPedidos(String filtro) throws PersistenciaException;

    public String obtenerEstadoPedido(int idPedido) throws PersistenciaException;

    /**
     * Valida el PIN de seguridad de un pedido de tipo Express.
     *
     * @param idPedido ID del pedido.
     * @param pin PIN proporcionado por el cliente.
     * @return true si coincide, false en caso contrario.
     * @throws PersistenciaException Error de acceso a datos.
     */
    public boolean verificarPin(int idPedido, String pin) throws PersistenciaException;

    public void actualizarAEntregadoConPago(int idPedido, String metodoPago) throws PersistenciaException;

    /**
     * Obtiene cupones cuya fecha de vencimiento sea mayor o igual a la actual.
     *
     * @return Lista de cupones vigentes.
     * @throws PersistenciaException Error al consultar la tabla Cupon.
     */
    public List<Cupon> obtenerCuponesVigentes() throws PersistenciaException;

    public List<PedidoEntregaDTO> obtenerPedidosPreparacion() throws PersistenciaException;

    public void actualizarEstadoPedido(int idPedido, String nuevoEstado) throws PersistenciaException;

    public void pasarAListo(int idPedido) throws PersistenciaException;

    public void pasarANoEntregado(int idPedido) throws PersistenciaException;

    public void pasarACancelado(int idPedido) throws PersistenciaException;

    public void agregarPedidoProgramado(PedidoProgramado pedidoProgramado, List<DetallePedido> listaDetallePedido) throws PersistenciaException;

    public void agregarPedidoExpress(PedidoExpress pedidoExpress, List<DetallePedido> listaDetallePedido) throws PersistenciaException;

    public List<PedidoEntregaDTO> buscarPedidosAvanzado(String filtro) throws PersistenciaException;

}
