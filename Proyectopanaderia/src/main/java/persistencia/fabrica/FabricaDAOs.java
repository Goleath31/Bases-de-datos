package persistencia.fabrica;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author golea
 */
import persistencia.DAOs.IPedidoDAO;
import persistencia.DAOs.PedidoDAO;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

public class FabricaDAOs {
    public static IPedidoDAO obtenerPedidoDAO() {
        IConexionBD conexion = new ConexionBD();
        return new PedidoDAO(conexion);
    }
}