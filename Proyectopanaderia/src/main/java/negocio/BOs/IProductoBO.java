/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.List;
import negocio.DTOs.ProductoDTO;
import negocio.excepciones.NegocioException;
import persistencia.dominio.Producto;

/**
 * Interfaz de Negocio para la gestión del catálogo de productos. Define las
 * operaciones para el mantenimiento y consulta de productos disponibles en la
 * panadería.
 */
public interface IProductoBO {

    /**
     * Recupera la lista completa de productos registrados en el sistema.
     *
     * @return Lista de objetos {@link Producto}.
     * @throws NegocioException Si ocurre un error al consultar la base de
     * datos.
     */
    public List<Producto> listarProductos() throws NegocioException;

    /**
     * Actualiza la información técnica o comercial de un producto existente.
     *
     * @param producto La entidad con los datos actualizados.
     * @throws NegocioException Si los datos del producto son inválidos o no
     * existe el registro.
     */
    public void actualizar(Producto producto) throws NegocioException;

    /**
     * Registra un nuevo producto en el catálogo.
     *
     * @param producto Entidad a persistir.
     * @throws NegocioException Si hay duplicidad de datos o campos obligatorios
     * vacíos.
     */
    public void agregar(Producto producto) throws NegocioException;

    /**
     * Busca productos basándose en un criterio de texto (nombre, descripción,
     * etc.).
     *
     * @param filtro Cadena de texto para la búsqueda.
     * @return Lista de productos que coinciden con el filtro.
     * @throws NegocioException Si el filtro no cumple con los requisitos
     * mínimos.
     */
    public List<Producto> buscarProductos(String filtro) throws NegocioException;

    /**
     * Filtra los productos según su estado de disponibilidad (ej. Activo,
     * Agotado).
     *
     * @param estado Etiqueta del estado a filtrar.
     * @return Lista de productos bajo el estado solicitado.
     * @throws NegocioException Si el estado proporcionado no es válido.
     */
    public List<Producto> filtrarPorEstado(String estado) throws NegocioException;

    /**
     * Obtiene una lista simplificada (DTO) de todos los productos que pueden
     * ser vendidos actualmente.
     *
     * @return Lista de {@link ProductoDTO} activos.
     * @throws NegocioException Si hay errores en la recuperación de datos.
     */
    public List<ProductoDTO> obtenerTodosLosProductosActivos() throws NegocioException;

    /**
     * Recupera únicamente los nombres de los productos registrados. Útil para
     * autocompletado o listados rápidos.
     *
     * @return Lista de cadenas de texto con los nombres.
     * @throws NegocioException Si ocurre un error técnico.
     */
    public List<String> obtenerNombresProductos() throws NegocioException;
}
