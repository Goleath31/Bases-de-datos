/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.fabrica;

/**
 *
 * @author golea
 */
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

public class FabricaBOs {

    public static IPedidoBO obtenerPedidoBO() {
        IPedidoDAO pedidoDAO = FabricaDAOs.obtenerPedidoDAO();
        return new PedidoBO(pedidoDAO);
    }

   
    public static IProductoBO obtenerProductoBO() {
        return new ProductoBO(FabricaDAOs.obtenerProductoDAO());
    }

    public static IClienteBO obtenerClienteBO(){
        IClienteDAO clienteDAO = FabricaDAOs.obtenerClienteDAO();
        return new ClienteBO(clienteDAO);
    }
    
    public static ICuponBO obtenerCuponBO(){
        ICuponDAO cuponDAO = FabricaDAOs.obtenerCuponDAO();
        return new CuponBO(cuponDAO);
    }
    
    public static IPedidoAgendadoBO obtenerPedidoAgendadoBO(){
        IPedidoAgendadoDAO pedidoAgendadoDAO = FabricaDAOs.obtenerPedidoAgendadoDAO();
        return new PedidoAgendadoBO(pedidoAgendadoDAO);
    }
    
    public static ITelefonoBO obtenerTelefonoBO(){
        ITelefonoDAO telefonoDAO = FabricaDAOs.obtenerTelefonoDAO();
        return new TelefonoBO(telefonoDAO);
    }
    
}
