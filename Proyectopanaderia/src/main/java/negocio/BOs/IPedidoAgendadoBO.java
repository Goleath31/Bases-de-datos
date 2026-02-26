/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.BOs;

import java.util.List;
import negocio.DTOs.PedidoDTO;
import negocio.excepciones.NegocioException;

/**
 * Interfaz de Negocio especializada en la gestión de Pedidos Agendados. Se
 * enfoca en las operaciones de recuperación de pedidos programados por cliente.
 *
 * * @author joser
 */
public interface IPedidoAgendadoBO {

    /**
     * Obtiene el historial de pedidos realizados por un cliente específico.
     * @param idCliente Identificador único del cliente.
     * @return Lista de {@link PedidoDTO} asociados al cliente.
     * @throws NegocioException Si el ID del cliente es inválido o no tiene pedidos.
     */
    public List<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws NegocioException;
}
