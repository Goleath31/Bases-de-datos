/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;
import negocio.DTOs.PedidoNuevoDTO;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.IPedidoDAO;
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
}