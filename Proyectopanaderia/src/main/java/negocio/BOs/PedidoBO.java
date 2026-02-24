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
import persistencia.dominio.PedidoExpress;
import persistencia.dominio.PedidoProgramado;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author golea
 */
public class PedidoBO implements IPedidoBO {

    private final IPedidoDAO pedidoDAO;
    private static final Logger LOG = Logger.getLogger(PedidoBO.class.getName());

    public PedidoBO(IPedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

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

    @Override
    public List<PedidoEntregaDTO> buscarPedidosPendientesEntrega(String filtro) throws NegocioException {
        try {
            return pedidoDAO.buscarPedidos(filtro);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron recuperar los pedidos.");
        }
    }

    @Override
    public List<Cupon> obtenerCuponesVigentes() throws NegocioException {
        try {
            return pedidoDAO.obtenerCuponesVigentes();
        } catch (PersistenciaException e) {
            throw new NegocioException("Error de negocio: No se pudieron consultar los cupones actuales.", e);
        }
    }

    @Override
    public List<PedidoEntregaDTO> listarPedidosPreparacion() throws NegocioException {
        try {
            return pedidoDAO.obtenerPedidosPreparacion();
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

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

//    @Override
//    public List<PedidoDTO> obtenerPedidosPorCliente(int idCliente) throws NegocioException {
//        if (idCliente <= 0) {
//            LOG.log(Level.WARNING, "Error con el id del cliente");
//            throw new NegocioException("El id del cliente no es valido");
//        }
//        List
//        try {
//        }
//
//    }

    /**
     * Valida los datos de un DTO de Pedido Exprés antes de enviarlos al DAO.
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
     * Valida los datos de un artículo a agregar en el pedido exprés. Lanza una
     * excepción si algún dato rompe las reglas de negocio.
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
     * Valida los datos principales de un Pedido Agendado (Programado).
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
     * Valida los datos de un artículo individual a agregar en el pedido
     * agendado.
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

    @Override
    public List<PedidoEntregaDTO> buscarPedidosAvanzado(String filtro) throws NegocioException {
        try {
            return pedidoDAO.buscarPedidosAvanzado(filtro);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al filtrar la lista de pedidos: " + e.getMessage());
        }
    }

}
