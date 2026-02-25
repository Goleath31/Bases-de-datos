package persistencia.fabrica;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author golea
 */
import persistencia.DAOs.ClienteDAO;
import persistencia.DAOs.CuponDAO;
import persistencia.DAOs.EmpleadoDAO;
import persistencia.DAOs.IClienteDAO;
import persistencia.DAOs.ICuponDAO;
import persistencia.DAOs.IEmpleadoDAO;
import persistencia.DAOs.IPedidoAgendadoDAO;
import persistencia.DAOs.IPedidoDAO;
import persistencia.DAOs.IProductoDAO;
import persistencia.DAOs.ITelefonoDAO;
import persistencia.DAOs.PedidoAgendadoDAO;
import persistencia.DAOs.PedidoDAO;
import persistencia.DAOs.ProductoDAO;
import persistencia.DAOs.TelefonoDAO;
import persistencia.conexion.ConexionBD;
import persistencia.conexion.IConexionBD;

public class FabricaDAOs {
    
    private static IConexionBD conexion = new ConexionBD();
    
    public static IPedidoDAO obtenerPedidoDAO() {
        return new PedidoDAO(conexion);
    }

    public static IProductoDAO obtenerProductoDAO() {
        return new ProductoDAO(conexion);
    }

    public static IEmpleadoDAO obtenerEmpleadoDAO() {
        return new EmpleadoDAO(conexion);
    }
    
    public static IClienteDAO obtenerClienteDAO(){
        return new ClienteDAO(conexion);
    }
    
    public static ICuponDAO obtenerCuponDAO(){
        return new CuponDAO(conexion);
    }
    
    public static IPedidoAgendadoDAO obtenerPedidoAgendadoDAO(){
        return new PedidoAgendadoDAO(conexion);
    }
    
    public static ITelefonoDAO obtenerTelefonoDAO(){
        return new TelefonoDAO(conexion);
    }
    
    
}
