/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import negocio.DTOs.ProductoDTO;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.IProductoDAO;
import persistencia.dominio.Producto;
import persistencia.excepciones.PersistenciaException;

/**
 * Clase de Objeto de Negocio (BO) para la gestión del catálogo de productos.
 * Implementa la lógica para listar, agregar y actualizar productos con
 * validaciones de precio.
 */
public class ProductoBO implements IProductoBO {

    private final IProductoDAO productoDAO;
    private static final Logger LOG = Logger.getLogger(ProductoBO.class.getName());

    /**
     * Constructor que inyecta la dependencia del DAO de productos.
     *
     * @param productoDAO DAO para persistencia de productos.
     */
    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    /**
     * Recupera todos los productos (incluyendo inactivos) del sistema.
     *
     * @return Lista de entidades {@link Producto}.
     * @throws NegocioException Si hay un error de acceso a datos.
     */
    @Override
    public List<Producto> listarProductos() throws NegocioException {
        try {
            return productoDAO.obtenerTodosLosProductos();
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Actualiza un producto existente previa validación de que el precio no sea
     * negativo.
     *
     * @param producto Entidad con los datos nuevos.
     * @throws NegocioException Si el precio es menor a cero.
     */
    @Override
    public void actualizar(Producto producto) throws NegocioException {
        if (producto.getPrecio() < 0) {
            throw new NegocioException("El precio no puede ser menor a cero.");
        }

        try {
            productoDAO.actualizarProducto(producto);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo actualizar el producto en la base de datos.", e);
        }
    }

    /**
     * Registra un nuevo producto validando campos obligatorios.
     *
     * @param producto Entidad a persistir.
     * @throws NegocioException Si el nombre está vacío o el precio es menor o
     * igual a cero.
     */
    @Override
    public void agregar(Producto producto) throws NegocioException {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto es obligatorio.");
        }
        if (producto.getPrecio() <= 0) {
            throw new NegocioException("El precio debe ser mayor a cero.");
        }

        try {
            productoDAO.agregarProducto(producto);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar el producto.", e);
        }
    }

    /**
     * Realiza una búsqueda de productos filtrando por nombre o descripción.
     *
     * @param filtro Texto de búsqueda.
     * @return Lista de productos coincidentes.
     */
    @Override
    public List<Producto> buscarProductos(String filtro) throws NegocioException {
        try {
            return productoDAO.buscarProductos(filtro);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Filtra una lista de productos según su estado (ej. Activo/Inactivo)
     * mediante Streams.
     *
     * @param estado Estado deseado.
     * @return Lista filtrada de productos.
     */
    public List<Producto> filtrarPorEstado(String estado) throws NegocioException {
        return listarProductos().stream()
                .filter(p -> p.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }

    /**
     * Mapea y retorna únicamente los productos que se encuentran en estado
     * Activo.
     *
     * @return Lista de {@link ProductoDTO} para la interfaz de usuario.
     * @throws NegocioException Si ocurre un error al cargar el catálogo.
     */
    @Override
    public List<ProductoDTO> obtenerTodosLosProductosActivos() throws NegocioException {
        List<ProductoDTO> listaDTO = new ArrayList<>();

        try {

            List<Producto> productosEntidad = productoDAO.obtenerTodosLosProductosActivos();

            for (Producto p : productosEntidad) {
                ProductoDTO dto = new ProductoDTO();
                dto.setIdProducto(p.getId());
                dto.setNombre(p.getNombre());
                dto.setPrecio(p.getPrecio());
                dto.setTipo(p.getTipo());
                dto.setDescripcion(p.getDescripcion());

                listaDTO.add(dto);
            }

            return listaDTO;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener el catálogo: " + ex.getMessage());
        }
    }

    /**
     * Obtiene solo los nombres de los productos para su uso en selectores o
     * combos.
     *
     * @return Lista de Strings con los nombres.
     * @throws NegocioException Si no hay productos disponibles.
     */
    @Override
    public List<String> obtenerNombresProductos() throws NegocioException {
        try {
            List<String> productos = productoDAO.obtenerNombresProductos();
            if (productos.isEmpty()) {
                throw new NegocioException("No hay productos disponibles en este momento.");
            }
            return productos;
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public String obtenerNombreProductoPorId(int idProducto) throws NegocioException {
        if (idProducto <= 0) {
            throw new NegocioException("El ID del producto debe ser un número positivo.");
        }

        try {
            String nombre = productoDAO.obtenerNombreProductoPorId(idProducto);

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new NegocioException("No se encontró ningún producto con el ID: " + idProducto);
            }

            return nombre;

        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error de negocio al buscar producto " + idProducto, ex);
            throw new NegocioException("Error al consultar el catálogo de productos.");
        }
    }
}
