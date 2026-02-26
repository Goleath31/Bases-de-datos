/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia.DAOs;

import java.util.List;
import persistencia.dominio.Pedido;
import persistencia.excepciones.PersistenciaException;

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para la gestión de Pedidos
 * Agendados. Define las operaciones de consulta y recuperación de pedidos
 * programados por los clientes en el sistema.
 *
 * * @author joser
 */
public interface IPedidoAgendadoDAO {

    /**
     * Recupera el historial o la lista de pedidos asociados a un cliente
     * específico.
     *
     * * @param idCliente Identificador único del cliente en la base de datos.
     * @return Una {@link List} de objetos {@link Pedido} pertenecientes al
     * cliente. Si el cliente no tiene pedidos, devuelve una lista vacía.
     * @throws PersistenciaException Si ocurre un error al realizar la consulta
     * en la base de datos o hay problemas de conectividad.
     */
    public List<Pedido> consultarPedidoPorCliente(int idCliente) throws PersistenciaException;

}
