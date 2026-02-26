/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.fabrica;

import negocio.BOs.ClienteBO;
import negocio.BOs.CuponBO;
import negocio.BOs.IClienteBO;
import negocio.BOs.ICuponBO;
import negocio.BOs.IPedidoAgendadoBO;
import negocio.BOs.IPedidoBO;
import negocio.BOs.IProductoBO;
import negocio.BOs.ITelefonoBO;
import negocio.BOs.PedidoAgendadoBO;
import negocio.BOs.PedidoBO;
import negocio.BOs.ProductoBO;
import negocio.BOs.TelefonoBO;
import persistencia.DAOs.IClienteDAO;
import persistencia.DAOs.ICuponDAO;
import persistencia.DAOs.IPedidoAgendadoDAO;
import persistencia.DAOs.IPedidoDAO;
import persistencia.DAOs.ITelefonoDAO;
import persistencia.fabrica.FabricaDAOs;

/**
 * Fábrica encargada de la creación e instanciación de los Objetos de Negocio (BOs).
 * Implementa el patrón Factory para centralizar el acceso a la lógica de negocio,
 * abstrayendo la creación de las implementaciones y resolviendo sus dependencias 
 * con la capa de persistencia.
 * * @author golea
 * @version 1.0
 */
public class FabricaBOs {

    /**
     * Obtiene una instancia de la lógica de negocio para Pedidos.
     * * @return Una implementación de {@link IPedidoBO}.
     */
    public static IPedidoBO obtenerPedidoBO() {
        IPedidoDAO pedidoDAO = FabricaDAOs.obtenerPedidoDAO();
        return new PedidoBO(pedidoDAO);
    }

    /**
     * Obtiene una instancia de la lógica de negocio para Productos.
     * * @return Una implementación de {@link IProductoBO}.
     */
    public static IProductoBO obtenerProductoBO() {
        return new ProductoBO(FabricaDAOs.obtenerProductoDAO());
    }

    /**
     * Obtiene una instancia de la lógica de negocio para Clientes.
     * * @return Una implementación de {@link IClienteBO}.
     */
    public static IClienteBO obtenerClienteBO(){
        IClienteDAO clienteDAO = FabricaDAOs.obtenerClienteDAO();
        return new ClienteBO(clienteDAO);
    }
    
    /**
     * Obtiene una instancia de la lógica de negocio para Cupones.
     * * @return Una implementación de {@link ICuponBO}.
     */
    public static ICuponBO obtenerCuponBO(){
        ICuponDAO cuponDAO = FabricaDAOs.obtenerCuponDAO();
        return new CuponBO(cuponDAO);
    }
    
    /**
     * Obtiene una instancia de la lógica de negocio para Pedidos Agendados.
     * * @return Una implementación de {@link IPedidoAgendadoBO}.
     */
    public static IPedidoAgendadoBO obtenerPedidoAgendadoBO(){
        IPedidoAgendadoDAO pedidoAgendadoDAO = FabricaDAOs.obtenerPedidoAgendadoDAO();
        return new PedidoAgendadoBO(pedidoAgendadoDAO);
    }
    
    /**
     * Obtiene una instancia de la lógica de negocio para Teléfonos.
     * * @return Una implementación de {@link ITelefonoBO}.
     */
    public static ITelefonoBO obtenerTelefonoBO(){
        ITelefonoDAO telefonoDAO = FabricaDAOs.obtenerTelefonoDAO();
        return new TelefonoBO(telefonoDAO);
    }
    
}