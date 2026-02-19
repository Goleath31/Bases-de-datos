/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas.negocio;

import java.util.ArrayList;
import java.util.List;
import negocio.BOs.IPedidoBO;
import negocio.DTOs.DetalleDTO;
import negocio.DTOs.PedidoNuevoDTO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;

/**
 *
 * @author golea
 */
public class PruebaRegistrodePedido {
    public static void main(String[] args) {
        IPedidoBO pedidoBO = FabricaBOs.obtenerPedidoBO();

        DetalleDTO producto1 = new DetalleDTO(1, 5, 15.50, "Bien doradas");
        DetalleDTO producto2 = new DetalleDTO(2, 2, 25.00, "Sin sal"); 

        List<DetalleDTO> listaProductos = new ArrayList<>();
        listaProductos.add(producto1);
        listaProductos.add(producto2);

        PedidoNuevoDTO pedidoNuevo = new PedidoNuevoDTO();
        pedidoNuevo.setIdCliente(1);
        pedidoNuevo.setTipoPedido("Programado");
        pedidoNuevo.setMontoTotal(127.50);
        pedidoNuevo.setDetalles(listaProductos);

        System.out.println("--- Iniciando prueba de registro de pedido ---");
        try {
            pedidoBO.registrarPedido(pedidoNuevo);
            System.out.println("ÉXITO: El pedido y sus detalles se guardaron correctamente.");
            System.out.println("Se aplico la transacción y se valido el límite de 3 pedidos.");
            
        } catch (NegocioException e) {
            System.err.println("ERROR DE NEGOCIO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERROR INESPERADO: " + e.getMessage());
            e.printStackTrace();
        }  
    }
    
}
