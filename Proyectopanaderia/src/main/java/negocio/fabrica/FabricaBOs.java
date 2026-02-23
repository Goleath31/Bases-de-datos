/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.fabrica;

/**
 *
 * @author golea
 */
import negocio.BOs.IPedidoBO;
import negocio.BOs.IProductoBO;
import negocio.BOs.PedidoBO;
import negocio.BOs.ProductoBO;
import persistencia.DAOs.IPedidoDAO;
import persistencia.fabrica.FabricaDAOs;

public class FabricaBOs {

    public static IPedidoBO obtenerPedidoBO() {
        IPedidoDAO pedidoDAO = FabricaDAOs.obtenerPedidoDAO();
        return new PedidoBO(pedidoDAO);
    }

   
    public static IProductoBO obtenerProductoBO() {
        return new ProductoBO(FabricaDAOs.obtenerProductoDAO());
    }

}
