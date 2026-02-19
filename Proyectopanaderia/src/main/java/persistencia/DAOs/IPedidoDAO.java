/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

/**
 *
 * @author golea
 */
import negocio.DTOs.PedidoNuevoDTO;
import persistencia.excepciones.PersistenciaException;

public interface IPedidoDAO {
    public int contarPedidosActivos(int idCliente) throws PersistenciaException;
    public void agregarPedido(PedidoNuevoDTO pedido) throws PersistenciaException;
}
