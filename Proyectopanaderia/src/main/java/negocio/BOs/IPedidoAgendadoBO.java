/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio.BOs;

import java.util.List;
import negocio.DTOs.PedidoDTO;
import negocio.excepciones.NegocioException;

/**
 *
 * @author joser
 */
public interface IPedidoAgendadoBO {
        public List<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws NegocioException;
}
