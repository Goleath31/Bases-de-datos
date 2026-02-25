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


public interface IPedidoBO {

    public void registrarPedido(PedidoNuevoDTO pedido) throws NegocioException;

    public List<PedidoEntregaDTO> buscarPedidosPendientesEntrega(String filtro) throws NegocioException;

    public void validarPinExpress(int idPedido, String pin) throws NegocioException;

    public void procesarEntrega(int idPedido, String metodoPago) throws NegocioException;

    public List<Cupon> obtenerCuponesVigentes() throws NegocioException;

    public List<PedidoEntregaDTO> listarPedidosPreparacion() throws NegocioException;

    public void avanzarEstado(int idPedido, String estadoActual) throws NegocioException;


    public void registrarPedidoAgendado(PedidoAgendadoDTO pedido, List<DetallePedidoDTO> detalles) throws NegocioException;

    public void registrarPedidoExpress(PedidoExpressDTO pedidoExpress, List<DetallePedidoDTO> detalles) throws NegocioException;
    

    public List<PedidoEntregaDTO> buscarPedidosAvanzado(String filtro) throws NegocioException;

}
