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

public class ProductoBO implements IProductoBO {

    private final IProductoDAO productoDAO;
    private static final Logger LOG = Logger.getLogger(ProductoBO.class.getName());
    

    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public List<Producto> listarProductos() throws NegocioException {
        try {
            return productoDAO.obtenerTodosLosProductos();
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

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

    @Override
    public List<Producto> buscarProductos(String filtro) throws NegocioException {
        try {
            return productoDAO.buscarProductos(filtro);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public List<Producto> filtrarPorEstado(String estado) throws NegocioException {
        return listarProductos().stream()
                .filter(p -> p.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }

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
}
