package persistencia.fabrica;

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

/**
 * Fábrica encargada de centralizar la creación de los Objetos de Acceso a Datos
 * (DAOs). * Esta clase implementa el patrón de diseño Factory para desacoplar
 * la lógica de creación de los DAOs del resto de la aplicación, inyectando de
 * manera centralizada la conexión a la base de datos.
 *
 * * @author golea
 * @version 1.0
 */
public class FabricaDAOs {

    /**
     * Instancia única de la conexión a la base de datos para ser compartida
     * entre todos los DAOs generados por esta fábrica.
     */
    private static IConexionBD conexion = new ConexionBD();

    /**
     * Proporciona una instancia de la interfaz para el manejo de pedidos.
     *
     * * @return Una implementación de {@link IPedidoDAO} configurada con la
     * conexión actual.
     */
    public static IPedidoDAO obtenerPedidoDAO() {
        return new PedidoDAO(conexion);
    }

    /**
     * Proporciona una instancia de la interfaz para el manejo de productos.
     *
     * * @return Una implementación de {@link IProductoBO} configurada con la
     * conexión actual.
     */
    public static IProductoDAO obtenerProductoDAO() {
        return new ProductoDAO(conexion);
    }

    /**
     * Proporciona una instancia de la interfaz para el manejo de empleados.
     *
     * * @return Una implementación de {@link IEmpleadoDAO}.
     */
    public static IEmpleadoDAO obtenerEmpleadoDAO() {
        return new EmpleadoDAO(conexion);
    }

    /**
     * Proporciona una instancia de la interfaz para el manejo de clientes.
     *
     * * @return Una implementación de {@link IClienteDAO}.
     */
    public static IClienteDAO obtenerClienteDAO() {
        return new ClienteDAO(conexion);
    }

    /**
     * Proporciona una instancia de la interfaz para el manejo de cupones.
     *
     * * @return Una implementación de {@link ICuponDAO}.
     */
    public static ICuponDAO obtenerCuponDAO() {
        return new CuponDAO(conexion);
    }

    /**
     * Proporciona una instancia de la interfaz para el manejo de pedidos
     * agendados.
     *
     * * @return Una implementación de {@link IPedidoAgendadoDAO}.
     */
    public static IPedidoAgendadoDAO obtenerPedidoAgendadoDAO() {
        return new PedidoAgendadoDAO(conexion);
    }

    /**
     * Proporciona una instancia de la interfaz para el manejo de teléfonos.
     *
     * * @return Una implementación de {@link ITelefonoDAO}.
     */
    public static ITelefonoDAO obtenerTelefonoDAO() {
        return new TelefonoDAO(conexion);
    }

}
