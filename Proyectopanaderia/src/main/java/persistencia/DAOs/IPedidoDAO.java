/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

/**
 *
 * @author golea
 */
import java.util.List;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.DTOs.PedidoNuevoDTO;
import persistencia.dominio.Cupon;
import persistencia.excepciones.PersistenciaException;

public interface IPedidoDAO {
    public int contarPedidosActivos(int idCliente) throws PersistenciaException;
    public void agregarPedido(PedidoNuevoDTO pedido) throws PersistenciaException;
     public List<PedidoEntregaDTO> buscarPedidos(String filtro) throws PersistenciaException;

    public String obtenerEstadoPedido(int idPedido) throws PersistenciaException;

    public boolean verificarPin(int idPedido, String pin) throws PersistenciaException;

    public void actualizarAEntregadoConPago(int idPedido, String metodoPago) throws PersistenciaException;

    public List<Cupon> obtenerCuponesVigentes() throws PersistenciaException;

    public List<PedidoEntregaDTO> obtenerPedidosPreparacion() throws PersistenciaException;

    public void actualizarEstadoPedido(int idPedido, String nuevoEstado) throws PersistenciaException;
        
    public void pasarAListo(int idPedido) throws PersistenciaException;
    
    public void pasarANoEntregado(int idPedido) throws PersistenciaException;
    
    public void pasarACancelado(int idPedido) throws PersistenciaException;
}
