/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

/**
 *
 * @author golea
 */
import java.util.List;
import persistencia.dominio.Producto;
import persistencia.excepciones.PersistenciaException;

/**
 * Interfaz de Objeto de Acceso a Datos (DAO) para la entidad Producto.
 * Proporciona los métodos necesarios para el inventario, permitiendo
 * operaciones de lectura, búsqueda y actualización de productos en el sistema.
 *
 * * @author golea
 */
public interface IProductoDAO {

    /**
     * Recupera el catálogo completo de productos registrados, sin importar su
     * estado.
     *
     * * @return Una {@link List} con todos los objetos {@link Producto}.
     * @throws PersistenciaException Si ocurre un error al consultar la base de
     * datos.
     */
    public List<Producto> obtenerTodosLosProductos() throws PersistenciaException;

    /**
     * Actualiza la información (nombre, precio, stock, etc.) de un producto
     * existente.
     *
     * * @param producto Objeto {@link Producto} con los datos actualizados.
     * @throws PersistenciaException Si el producto no existe o hay un error de
     * conexión.
     */
    public void actualizarProducto(Producto producto) throws PersistenciaException;

    /**
     * Registra un nuevo producto en el catálogo del sistema.
     *
     * * @param producto El objeto {@link Producto} a persistir.
     * @throws PersistenciaException Si los datos son inválidos o falla la
     * inserción.
     */
    public void agregarProducto(Producto producto) throws PersistenciaException;

    /**
     * Realiza una búsqueda de productos basada en un criterio de texto
     * (filtro). Generalmente busca coincidencias en el nombre o categoría.
     *
     * * @param filtro Cadena de texto para filtrar los resultados.
     * @return Lista de productos que coinciden con el criterio de búsqueda.
     * @throws PersistenciaException Si ocurre un error durante el filtrado.
     */
    public List<Producto> buscarProductos(String filtro) throws PersistenciaException;

    /**
     * Recupera únicamente los productos que se encuentran disponibles para la
     * venta (activos).
     *
     * * @return Una {@link List} de productos con estado activo.
     * @throws PersistenciaException Si hay un fallo en la recuperación de
     * datos.
     */
    public List<Producto> obtenerTodosLosProductosActivos() throws PersistenciaException;

    /**
     * Obtiene una lista simplificada que contiene únicamente los nombres de los
     * productos. Útil para llenar componentes de selección (ComboBox) o
     * sugerencias de búsqueda.
     *
     * * @return Una {@link List} de cadenas (String) con los nombres de los
     * productos.
     * @throws PersistenciaException Si ocurre un error en la consulta.
     */
    public List<String> obtenerNombresProductos() throws PersistenciaException;

}
