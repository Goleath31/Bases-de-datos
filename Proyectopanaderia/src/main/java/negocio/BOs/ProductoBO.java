/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.List;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.IProductoDAO;
import persistencia.dominio.Producto;
import persistencia.excepciones.PersistenciaException;

public class ProductoBO implements IProductoBO {

    private final IProductoDAO productoDAO;

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
        // Aquí puedes validar reglas de negocio
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
}
