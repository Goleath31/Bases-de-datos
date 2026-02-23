/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;
import java.util.List;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.DTOs.PedidoNuevoDTO;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.IPedidoDAO;
import persistencia.dominio.Cupon;
import persistencia.excepciones.PersistenciaException;
/**
 *
 * @author golea
 */
public class PedidoBO implements IPedidoBO {
    private final IPedidoDAO pedidoDAO;

    public PedidoBO(IPedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    @Override
    public void registrarPedido(PedidoNuevoDTO pedidoDTO) throws NegocioException {
        try {
            int activos = pedidoDAO.contarPedidosActivos(pedidoDTO.getIdCliente());
            if (activos >= 3) {
                throw new NegocioException("El cliente ya tiene 3 pedidos activos. No puede pedir más.");
            }
            pedidoDAO.agregarPedido(pedidoDTO);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error en el sistema de datos: " + e.getMessage());
        }
    }
    
    @Override
    public void procesarEntrega(int idPedido, String metodoPago) throws NegocioException {
        try {
            String estadoActual = pedidoDAO.obtenerEstadoPedido(idPedido);

            if (!estadoActual.equals("Listo")) {
                throw new NegocioException("El pedido no puede entregarse porque está en estado: " + estadoActual);
            }

            pedidoDAO.actualizarAEntregadoConPago(idPedido, metodoPago);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al procesar la entrega: " + e.getMessage());
        }
    }

    @Override
    public void validarPinExpress(int idPedido, String pin) throws NegocioException {
        try {
            boolean esValido = pedidoDAO.verificarPin(idPedido, pin);
            if (!esValido) {
                throw new NegocioException("El PIN ingresado es incorrecto.");
            }
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<PedidoEntregaDTO> buscarPedidosPendientesEntrega(String filtro) throws NegocioException {
        try {
            return pedidoDAO.buscarPedidos(filtro);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron recuperar los pedidos.");
        }
    }

    @Override
    public List<Cupon> obtenerCuponesVigentes() throws NegocioException {
        try {
            return pedidoDAO.obtenerCuponesVigentes();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error de negocio: No se pudieron consultar los cupones actuales.", e);
        }
    }

    @Override
    public List<PedidoEntregaDTO> listarPedidosPreparacion() throws NegocioException {
        try {
            return pedidoDAO.obtenerPedidosPreparacion();
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void avanzarEstado(int idPedido, String estadoActual) throws NegocioException {
        String nuevoEstado = "";

        // Lógica del flujo de estados
        switch (estadoActual) {
            case "Pendiente":
                nuevoEstado = "Listo";
                break;
            case "Listo":
                nuevoEstado = "Cancelado";
                break;
            case "Cancelado":
                nuevoEstado = "No Entregado";
                break;
            default:
                return;
        }
       
        try {
            pedidoDAO.actualizarEstadoPedido(idPedido, nuevoEstado);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}