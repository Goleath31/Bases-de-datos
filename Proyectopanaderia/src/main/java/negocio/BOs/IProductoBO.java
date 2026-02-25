/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.List;
import negocio.DTOs.ProductoDTO;
import negocio.excepciones.NegocioException;
import persistencia.dominio.Producto;

public interface IProductoBO {

    public List<Producto> listarProductos() throws NegocioException;

    public void actualizar(Producto producto) throws NegocioException;

    public void agregar(Producto producto) throws NegocioException;

    public List<Producto> buscarProductos(String filtro) throws NegocioException;

    public List<Producto> filtrarPorEstado(String estado) throws NegocioException;
    
    public List<ProductoDTO> obtenerTodosLosProductosActivos() throws NegocioException;
}
