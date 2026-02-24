/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.DTOs.PedidoDTO;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.IPedidoAgendadoDAO;
import persistencia.dominio.Pedido;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public class PedidoAgendadoBO implements IPedidoAgendadoBO {
    
    private final IPedidoAgendadoDAO pedidoAgendadoDAO;
    private static final Logger LOG = Logger.getLogger(PedidoBO.class.getName());

    public PedidoAgendadoBO(IPedidoAgendadoDAO pedidoAgendadoDAO) {
        this.pedidoAgendadoDAO = pedidoAgendadoDAO;
    }
    
    @Override
    public List<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws NegocioException {
        if (idCliente <= 0) {
            LOG.log(Level.WARNING, "Error con el id del cliente");
            throw new NegocioException("El id del cliente no es valido");
        }
        
        List<PedidoDTO> listaRegresada = new ArrayList<>();
        
        try{
            List<Pedido> listaRecibida = pedidoAgendadoDAO.consultarPedidoPorCliente(idCliente);
        
            for (Pedido item: listaRecibida) {
                PedidoDTO itemMapeado = new PedidoDTO();
                
                itemMapeado.setIdPedido(item.getId());
                itemMapeado.setFechahora(item.getFecha());
                itemMapeado.setTotal(item.getTotal());
                itemMapeado.setEstado(item.getEstado());
                
                
                listaRegresada.add(itemMapeado);
            }
            
        }
        catch(PersistenciaException ex){
            LOG.log(Level.WARNING, "Error al querer obtener los pedidos del cliente: " + ex.getMessage());
            throw new NegocioException("Error al obtener los pedidos del cliente: " + ex.getMessage());
        }
        
        return listaRegresada;

    }
    
}
