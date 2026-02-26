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
 * Objeto de Negocio especializado en la gestión de Pedidos
 * Agendados/Programados. Maneja principalmente consultas de historial y estados
 * específicos de clientes.
 *
 * * @author joser
 */
public class PedidoAgendadoBO implements IPedidoAgendadoBO {

    private final IPedidoAgendadoDAO pedidoAgendadoDAO;
    private static final Logger LOG = Logger.getLogger(PedidoBO.class.getName());

    /**
     * Constructor que inicializa el DAO de pedidos agendados.
     *
     * @param pedidoAgendadoDAO DAO específico para persistencia de agendados.
     */
    public PedidoAgendadoBO(IPedidoAgendadoDAO pedidoAgendadoDAO) {
        this.pedidoAgendadoDAO = pedidoAgendadoDAO;
    }

    /**
     * Obtiene todos los pedidos realizados por un cliente específico. Mapea los
     * objetos de dominio {@link Pedido} a {@link PedidoDTO} para la capa de
     * vista.
     *
     * * @param idCliente ID del cliente a consultar.
     * @return Lista de pedidos simplificados.
     * @throws NegocioException Si el ID es inválido o falla la conexión.
     */
    @Override
    public List<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws NegocioException {
        if (idCliente <= 0) {
            LOG.log(Level.WARNING, "Error con el id del cliente");
            throw new NegocioException("El id del cliente no es valido");
        }

        List<PedidoDTO> listaRegresada = new ArrayList<>();

        try {
            List<Pedido> listaRecibida = pedidoAgendadoDAO.consultarPedidoPorCliente(idCliente);

            for (Pedido item : listaRecibida) {
                PedidoDTO itemMapeado = new PedidoDTO();

                itemMapeado.setIdPedido(item.getId());
                itemMapeado.setFechahora(item.getFecha());
                itemMapeado.setTotal(item.getTotal());
                itemMapeado.setEstado(item.getEstado());

                listaRegresada.add(itemMapeado);
            }

        } catch (PersistenciaException ex) {
            LOG.log(Level.WARNING, "Error al querer obtener los pedidos del cliente: " + ex.getMessage());
            throw new NegocioException("Error al obtener los pedidos del cliente: " + ex.getMessage());
        }

        return listaRegresada;

    }

}
