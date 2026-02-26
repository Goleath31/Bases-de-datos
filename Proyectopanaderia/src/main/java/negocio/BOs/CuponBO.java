/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.BOs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.DTOs.CuponDTO;
import negocio.excepciones.NegocioException;
import persistencia.DAOs.ICuponDAO;
import persistencia.dominio.Cupon;
import persistencia.excepciones.PersistenciaException;

/**
 * Clase de Objeto de Negocio (BO) para la gestión y validación de Cupones.
 * Implementa las reglas de negocio relativas a la caducidad de las promociones
 * y el control de límites de uso por cliente o campaña.
 *
 * * @author joser
 */
public class CuponBO implements ICuponBO {

    private final ICuponDAO cuponDAO;
    private static final Logger LOG = Logger.getLogger(CuponBO.class.getName());

    /**
     * Constructor que inicializa el acceso a datos para la gestión de cupones.
     *
     * * @param cuponDAO Interfaz DAO de cupones.
     */
    public CuponBO(ICuponDAO cuponDAO) {
        this.cuponDAO = cuponDAO;
    }

    /**
     * Realiza el proceso integral de validación de un cupón. Verifica formato,
     * existencia en base de datos y reglas de vigencia.
     *
     * * @param codigo El código alfanumérico a validar.
     * @return {@link CuponDTO} mapeado desde la entidad de persistencia.
     * @throws NegocioException Si alguna validación de negocio falla.
     */
    @Override
    public CuponDTO validarCupon(String codigo) throws NegocioException {
        validarFormatoCodigo(codigo);

        try {
            Cupon cuponRecibido = cuponDAO.consultarCupon(codigo);
            validarVigenciaCupon(cuponRecibido);

            CuponDTO cuponMapeado = new CuponDTO();
            cuponMapeado.setIdCupon(cuponRecibido.getId());
            cuponMapeado.setCodigo(cuponRecibido.getCodigo());
            cuponMapeado.setDescuento(cuponRecibido.getDescuento());
            cuponMapeado.setVigencia(cuponRecibido.getFechaVigencia());

            return cuponMapeado;

        } catch (PersistenciaException ex) {
            LOG.log(Level.WARNING, "Error al buscar el cupon: " + ex.getMessage());
            throw new NegocioException("Error al buscar el cupon: " + ex.getMessage());
        }

    }

    /**
     * Verifica si el cupón cumple con las condiciones temporales y de uso.
     * Comprueba: 1. Existencia del registro. 2. Que la fecha actual no sea
     * posterior a la de vigencia. 3. Que no se haya superado el límite de usos
     * permitidos.
     *
     * * @param cupon Entidad {@link Cupon} a validar.
     * @throws NegocioException Si el cupón es nulo, expiró o está agotado.
     */
    public void validarVigenciaCupon(Cupon cupon) throws NegocioException {
        // 1. Verificar si el cupón existe
        if (cupon == null) {
            throw new NegocioException("El código de cupón no existe o es inválido.");
        }

        Date hoy = new Date();

        if (cupon.getFechaVigencia() != null && hoy.after(cupon.getFechaVigencia())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            throw new NegocioException("El cupón expiró el día: " + sdf.format(cupon.getFechaVigencia()));
        }

        if (cupon.getUsosActuales() >= cupon.getLimiteUsos()) {
            throw new NegocioException("El cupón ha alcanzado su límite máximo de usos.");
        }
    }

    /**
     * Valida que el código cumpla con los estándares de formato del sistema. El
     * código debe ser alfanumérico y tener una longitud de entre 3 y 15
     * caracteres.
     *
     * * @param codigo Cadena a validar.
     * @throws NegocioException Si el código es nulo, vacío o no cumple el
     * patrón regex.
     */
    private void validarFormatoCodigo(String codigo) throws NegocioException {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new NegocioException("El código del cupón no puede estar vacío.");
        }

        // ^[A-Z0-9]+$ obliga a que sean mayúsculas y números (opcional, puedes quitar A-Z por a-zA-Z)
        String regexCupon = "^[a-zA-Z0-9]{3,15}$";

        if (!codigo.matches(regexCupon)) {
            throw new NegocioException("El código del cupón debe ser alfanumérico y tener entre 3 y 15 caracteres.");
        }
    }
}
