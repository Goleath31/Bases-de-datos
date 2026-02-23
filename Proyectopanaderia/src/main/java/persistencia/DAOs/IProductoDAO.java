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

public interface IProductoDAO {

    // Método solicitado para obtener todos los productos del catálogo
    public List<Producto> obtenerTodosLosProductos() throws PersistenciaException;

    public void actualizarProducto(Producto producto) throws PersistenciaException;

    public void agregarProducto(Producto producto) throws PersistenciaException;
}
