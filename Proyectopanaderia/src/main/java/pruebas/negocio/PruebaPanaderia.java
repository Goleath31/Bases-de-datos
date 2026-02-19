/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas.negocio;

import negocio.BOs.IPedidoBO;
import negocio.DTOs.PedidoNuevoDTO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;

/**
 *
 * @author golea
 */
public class PruebaPanaderia {

    /**
     * @param args the command line arguments
     */
 public static void main(String[] args) {
        IPedidoBO pedidoBO = FabricaBOs.obtenerPedidoBO();
        
        PedidoNuevoDTO nuevo = new PedidoNuevoDTO();
        nuevo.setIdCliente(1);
        nuevo.setMontoTotal(250.0);
        nuevo.setTipoPedido("Programado");

        try {
            pedidoBO.registrarPedido(nuevo);
            System.out.println("¡Pedido registrado con exito!");
        } catch (NegocioException e) {
            System.err.println("No se pudo registrar: " + e.getMessage());
        }
    }
}
