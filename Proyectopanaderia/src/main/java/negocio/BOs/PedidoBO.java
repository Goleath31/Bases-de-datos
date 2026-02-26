/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.DTOs.DetallePedidoDTO;
import negocio.DTOs.PedidoAgendadoDTO;
import negocio.DTOs.PedidoDTO;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.DTOs.PedidoExpressDTO;
import negocio.DTOs.PedidoNuevoDTO;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.IPedidoDAO;
import persistencia.DAOs.PedidoDAO;
import persistencia.dominio.Cupon;
import persistencia.dominio.DetallePedido;
import persistencia.dominio.Pedido;
import persistencia.dominio.PedidoExpress;
import persistencia.dominio.PedidoProgramado;
import persistencia.excepciones.PersistenciaException;

/**
 * Objeto de Negocio (BO) para la gestión integral de pedidos. Controla el ciclo
 * de vida de pedidos Express y Agendados, validando reglas de negocio como el
 * límite de pedidos activos por cliente y la seguridad por PIN.
 *
 * * @author golea
 */
public class PedidoBO implements IPedidoBO {

    private final IPedidoDAO pedidoDAO;
    private static final Logger LOG = Logger.getLogger(PedidoBO.class.getName());

    /**
     * Constructor que inyecta la dependencia del DAO de pedidos.
     *
     * @param pedidoDAO Interfaz de persistencia para pedidos.
     */
    public PedidoBO(IPedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    /**
     * Registra un pedido básico validando que el cliente no exceda el máximo de
     * 3 pedidos activos.
     *
     * @param pedidoDTO Datos del pedido a registrar.
     * @throws NegocioException Si el cliente ya tiene el cupo máximo de pedidos
     * activos.
     */
    @Override
    public void registrarPedido(PedidoNuevoDTO pedidoDTO) throws NegocioException {
        try {
            int activos = pedidoDAO.contarPedidosActivos(pedidoDTO.getIdCliente());
            if (activos >= 3) {
                throw new NegocioException("El cliente ya tiene 3 pedidos activos. No puede pedir más.");
            }
            pedidoDAO.agregarPedido(pedidoDTO);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en el sistema de datos: " + e.getMessage());
        }
    }

    /**
     * Procesa la entrega física de un pedido verificando que su estado actual
     * sea "Listo".
     *
     * @param idPedido ID del pedido a entregar.
     * @param metodoPago Forma de pago utilizada.
     * @throws NegocioException Si el pedido no está listo para entrega.
     */
    @Override
    public void procesarEntrega(int idPedido, String metodoPago) throws NegocioException {
        try {
            String estadoActual = pedidoDAO.obtenerEstadoPedido(idPedido);

            if (!estadoActual.equals("Listo")) {
                throw new NegocioException("El pedido no puede entregarse porque está en estado: " + estadoActual);
            }

            pedidoDAO.actualizarAEntregadoConPago(idPedido, metodoPago);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al procesar la entrega: " + e.getMessage());
        }
    }

    /**
     * Avanza el pedido a través del flujo de estados definido: Pendiente ->
     * Listo -> Cancelado -> No Entregado.
     *
     * @param idPedido ID del pedido a modificar.
     * @param estadoActual Estado previo al cambio.
     * @throws NegocioException Si ocurre un error en la persistencia.
     */
    @Override
    public void validarPinExpress(int idPedido, String pin) throws NegocioException {
        try {
            boolean esValido = pedidoDAO.verificarPin(idPedido, pin);
            if (!esValido) {
                throw new NegocioException("El PIN ingresado es incorrecto.");
            }
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Busca pedidos que están en espera de ser entregados, permitiendo filtrar
     * por texto.
     *
     * * @param filtro Criterio de búsqueda (nombre cliente, folio, etc.).
     * @return Lista de {@link PedidoEntregaDTO} que coinciden con el filtro.
     * @throws NegocioException Si ocurre un error al recuperar la información.
     */
    @Override
    public List<PedidoEntregaDTO> buscarPedidosPendientesEntrega(String filtro) throws NegocioException {
        try {
            return pedidoDAO.buscarPedidos(filtro);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron recuperar los pedidos.");
        }
    }

    /**
     * Obtiene la lista de cupones de descuento que se encuentran vigentes
     * actualmente.
     *
     * * @return Lista de objetos {@link Cupon}.
     * @throws NegocioException Si hay un fallo en la consulta a la base de
     * datos.
     */
    @Override
    public List<Cupon> obtenerCuponesVigentes() throws NegocioException {
        try {
            return pedidoDAO.obtenerCuponesVigentes();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error de negocio: No se pudieron consultar los cupones actuales.", e);
        }
    }

    /**
     * Lista todos los pedidos que se encuentran actualmente en la fase de
     * preparación.
     *
     * * @return Lista de {@link PedidoEntregaDTO} en preparación.
     * @throws NegocioException Si ocurre un error de persistencia.
     */
    @Override
    public List<PedidoEntregaDTO> listarPedidosPreparacion() throws NegocioException {
        try {
            return pedidoDAO.obtenerPedidosPreparacion();
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Avanza el estado de un pedido siguiendo el flujo lógico establecido:
     * Pendiente -> Listo -> Cancelado -> No Entregado.
     *
     * * @param idPedido ID del pedido a actualizar.
     * @param estadoActual El estado en el que se encuentra el pedido antes del
     * cambio.
     * @throws NegocioException Si ocurre un error al actualizar el estado en
     * BD.
     */
    @Override
    public void avanzarEstado(int idPedido, String estadoActual) throws NegocioException {
        String nuevoEstado = "";

        // Lógica del flujo de estados
        switch (estadoActual) {
            case "Pendiente":
                nuevoEstado = "Listo";
                break;
            case "Listo":
                nuevoEstado = "Cancelado";
                break;
            case "Cancelado":
                nuevoEstado = "No Entregado";
                break;
            default:
                return;
        }

        try {
            pedidoDAO.actualizarEstadoPedido(idPedido, nuevoEstado);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Registra un pedido de tipo Express junto con sus artículos detallados.
     * Realiza validaciones de integridad y formato antes de la persistencia.
     *
     * * @param pedidoExpressDTO Datos generales del pedido express.
     * @param detallesDTO Lista de artículos (productos y cantidades) incluidos.
     * @throws NegocioException Si los parámetros son nulos, la lista está vacía
     * o fallan las reglas de negocio.
     */
    @Override
    public void registrarPedidoExpress(PedidoExpressDTO pedidoExpressDTO, List<DetallePedidoDTO> detallesDTO) throws NegocioException {
        if (pedidoExpressDTO == null || detallesDTO.isEmpty()) {
            LOG.warning("Se intento ingresar un parametro no valido o nulo");
            throw new NegocioException("Error con los parametros del pedido");
        }

        validarPedidoExpress(pedidoExpressDTO);

        for (DetallePedidoDTO item : detallesDTO) {
            validarItemPedidoExpress(item);
        }

        PedidoExpress pedidoExpress = new PedidoExpress();
        pedidoExpress.setFolio(pedidoExpressDTO.getFolio());
        pedidoExpress.setPinSeguridad(pedidoExpressDTO.getPinSeguridad());

        List<DetallePedido> detalles = new ArrayList<>();

        for (DetallePedidoDTO item : detallesDTO) {
            DetallePedido detalleItem = new DetallePedido();

            detalleItem.setIdProducto(item.getIdProducto());
            detalleItem.setCantidad(item.getCantidad());
            detalleItem.setNotas(item.getNotas());

            detalles.add(detalleItem);
        }

        try {
            pedidoDAO.agregarPedidoExpress(pedidoExpress, detalles);

        } catch (PersistenciaException ex) {
            LOG.log(Level.WARNING, "Error al tratar de registrar un pedido express: " + ex.getMessage());
            throw new NegocioException("Error al tratar de registrar un pedido express" + ex.getMessage());
        }

    }

    /**
     * Registra un pedido agendado/programado, permitiendo el uso de cupones.
     *
     * * @param pedidoAgendadoDTO Datos de cabecera del pedido agendado.
     * @param detallesDTO Lista de artículos asociados al pedido.
     * @throws NegocioException Si hay errores en las validaciones de negocio o
     * persistencia.
     */
    @Override
    public void registrarPedidoAgendado(PedidoAgendadoDTO pedidoAgendadoDTO, List<DetallePedidoDTO> detallesDTO) throws NegocioException {
        if (pedidoAgendadoDTO == null || detallesDTO.isEmpty()) {
            LOG.warning("Se intento ingresar un parametro no valido o nulo");
            throw new NegocioException("Error con los parametros del pedido");
        }

        validarPedidoAgendado(pedidoAgendadoDTO);

        for (DetallePedidoDTO item : detallesDTO) {
            validarItemPedidoAgendado(item);
        }

        PedidoProgramado pedidoAgendado = new PedidoProgramado();
        pedidoAgendado.setIdCliente(pedidoAgendadoDTO.getIdCliente());
        pedidoAgendado.setIdCupon(pedidoAgendadoDTO.getIdCupon());

        //en caso de no tener cupon asignaremos id 0 pq xD olvide validar eso me estoy volviendo loco walalala
        List<DetallePedido> detalles = new ArrayList<>();

        for (DetallePedidoDTO item : detallesDTO) {
            DetallePedido detalleItem = new DetallePedido();

            detalleItem.setIdPedido(item.getIdPedido());
            detalleItem.setIdProducto(item.getIdProducto());
            detalleItem.setCantidad(item.getCantidad());
            detalleItem.setNotas(item.getNotas());

            detalles.add(detalleItem);
        }

        try {
            pedidoDAO.agregarPedidoProgramado(pedidoAgendado, detalles);

        } catch (PersistenciaException ex) {
            LOG.log(Level.WARNING, "Error al tratar de registrar un pedido agendado: " + ex.getMessage());
            throw new NegocioException("Error al tratar de registrar un pedido express" + ex.getMessage());
        }

    }

    /**
     * Valida los datos de un DTO de Pedido Exprés antes de enviarlos al DAO.
     * Verifica nulidad, longitud del folio y presencia de PIN.
     *
     * * @param pedidoExpress Objeto DTO con la información del pedido.
     * @throws NegocioException Si algún campo obligatorio falta o excede los
     * límites.
     */
    public void validarPedidoExpress(PedidoExpressDTO pedidoExpress) throws NegocioException {

        // 1. Validar que el objeto en sí no sea nulo
        if (pedidoExpress == null) {
            throw new NegocioException("Los datos del pedido exprés no pueden estar vacíos.");
        }

        // 3. Validar el Folio
        String folio = pedidoExpress.getFolio();
        if (folio == null || folio.trim().isEmpty()) {
            throw new NegocioException("El folio del pedido es obligatorio.");
        }
        if (folio.length() > 20) {
            throw new NegocioException("El folio no puede exceder los 20 caracteres.");
        }

        // 4. Validar el PIN de seguridad
        String pin = pedidoExpress.getPinSeguridad();
        if (pin == null || pin.trim().isEmpty()) {
            throw new NegocioException("El PIN de seguridad es obligatorio.");
        }

        // Si el usuario teclea el PIN en la interfaz, podrías validar que sean exactamente 4 o 6 dígitos numéricos.
        // Ejemplo de validación extra (opcional):
        /*
        if (!pin.matches("\\d{4,6}")) {
            throw new ValidacionException("El PIN debe contener entre 4 y 6 dígitos numéricos.");
        }
         */
        if (pin.length() > 255) {
            throw new NegocioException("El PIN de seguridad excede la longitud permitida.");
        }
    }

    /**
     * Valida los datos de un artículo individual para un pedido exprés.
     * Verifica ID de producto, cantidades lógicas y notas.
     *
     * * @param item DTO que representa el detalle del pedido.
     * @throws NegocioException Si la cantidad es menor a 1, excede 500 o las
     * notas son muy largas.
     */
    public void validarItemPedidoExpress(DetallePedidoDTO item) throws NegocioException {

        // 1. Validar que el objeto no venga nulo
        if (item == null) {
            throw new NegocioException("El artículo del pedido no puede estar vacío.");
        }

        // 2. Validar que el producto sea válido (los IDs en BD empiezan en 1)
        if (item.getIdProducto() <= 0) {
            throw new NegocioException("El identificador del producto no es válido.");
        }

        // 3. Validar cantidad lógica
        if (item.getCantidad() <= 0) {
            throw new NegocioException("La cantidad a comprar debe ser al menos de 1.");
        }

        // Opcional: Límite de seguridad para evitar errores de dedo (ej. pedir 9999 panes)
        if (item.getCantidad() > 500) {
            throw new NegocioException("La cantidad excede el límite permitido de 500 piezas por pedido.");
        }

        // 5. Validar la longitud de las notas para evitar desbordamiento en la BD
        // Aunque tu BD usa TEXT, es buena práctica limitar la entrada del usuario (ej. 500 caracteres)
        if (item.getNotas() != null && !item.getNotas().trim().isEmpty()) {
            if (item.getNotas().length() > 500) {
                throw new NegocioException("Las notas adicionales no pueden exceder los 500 caracteres.");
            }
        }
    }

    /**
     * Valida la integridad de un pedido agendado.
     *
     * * @param pedidoDTO DTO del pedido agendado.
     * @throws NegocioException Si el objeto es nulo, el ID del cliente o cupón
     * no son válidos.
     */
    private void validarPedidoAgendado(PedidoAgendadoDTO pedidoDTO) throws NegocioException {

        // 1. Validar que el objeto no venga nulo
        if (pedidoDTO == null) {
            throw new NegocioException("Los datos del pedido agendado no pueden estar vacíos.");
        }

        // 2. Validar que el cliente sea válido (los IDs en BD empiezan en 1)
        if (pedidoDTO.getIdCliente() <= 0) {
            throw new NegocioException("El identificador del cliente no es válido.");
        }

        // 3. Validar el cupón (Opcional)
        // Si es 0, asumimos que no hay cupón. Si es menor a 0, es un dato corrupto.
        if (pedidoDTO.getIdCupon() < 0) {
            throw new NegocioException("El identificador del cupón no es válido.");
        }
    }

    /**
     * Valida un artículo individual destinado a un pedido agendado.
     *
     * * @param item Detalle del artículo a validar.
     * @throws NegocioException Si los datos del producto o cantidades son
     * incorrectos.
     */
    private void validarItemPedidoAgendado(DetallePedidoDTO item) throws NegocioException {

        // 1. Validar que el objeto no sea nulo
        if (item == null) {
            throw new NegocioException("El artículo del pedido no puede estar vacío.");
        }

        // 2. Validar que el producto exista (ID mayor a 0)
        if (item.getIdProducto() <= 0) {
            throw new NegocioException("El identificador del producto no es válido.");
        }

        // 3. Validar que la cantidad sea lógica
        if (item.getCantidad() <= 0) {
            throw new NegocioException("La cantidad a comprar debe ser al menos de 1.");
        }

        // Opcional: Límite de seguridad
        if (item.getCantidad() > 500) {
            throw new NegocioException("La cantidad excede el límite permitido por pedido.");
        }

        // 4. Validar la longitud de las notas (para evitar desbordamientos en la BD)
        if (item.getNotas() != null && !item.getNotas().trim().isEmpty()) {
            if (item.getNotas().length() > 500) {
                throw new NegocioException("Las notas adicionales no pueden exceder los 500 caracteres.");
            }
        }
    }

    /**
     * Realiza una búsqueda avanzada de pedidos aplicando un filtro de texto.
     *
     * * @param filtro Cadena de texto para filtrar los registros.
     * @return Lista de {@link PedidoEntregaDTO} filtrados.
     * @throws NegocioException Si ocurre un error en la capa de persistencia
     * durante el filtrado.
     */
    @Override
    public List<PedidoEntregaDTO> buscarPedidosAvanzado(String filtro) throws NegocioException {
        try {
            return pedidoDAO.buscarPedidosAvanzado(filtro);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al filtrar la lista de pedidos: " + e.getMessage());
        }
    }

}
