/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;
import negocio.DTOs.PedidoNuevoDTO;
import negocio.excepciones.NegocioException;
/**
 *
 * @author golea
 */

public interface IPedidoBO {
    public void registrarPedido(PedidoNuevoDTO pedido) throws NegocioException;
}