/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import java.util.List;
import persistencia.dominio.Pedido;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public interface IPedidoAgendadoDAO {
    
    public List<Pedido> consultarPedidoPorCliente(int idCliente) throws PersistenciaException;
    
}
