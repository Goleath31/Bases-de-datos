/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

import java.util.Date;

/**
 * Representa a un cliente dentro del sistema. Esta clase se utiliza para
 * transportar la información personal y de acceso de los usuarios desde la base
 * de datos hacia las capas superiores.
 *
 * * @author golea
 */
public class Cliente {

    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String domicilio;
    private Date fechaNacimiento;
    private String correo;
    private String contraseña;

    /**
     * Constructor por defecto. Útil para frameworks de persistencia o
     * inicializaciones vacías.
     */
    public Cliente() {
    }

    /**
     * Constructor completo para crear un cliente con ID. Se utiliza normalmente
     * cuando se recuperan datos existentes de la base de datos.
     *
     * * @param id Identificador único asignado por el sistema.
     * @param nombre Nombre(s) del cliente.
     * @param apellidoPaterno Primer apellido.
     * @param apellidoMaterno Segundo apellido.
     * @param domicilio Dirección física de contacto.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     * @param correo Dirección de correo electrónico (funciona como usuario).
     * @param contraseña Clave de acceso al sistema.
     */
    public Cliente(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, Date fechaNacimiento, String correo, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    /**
     * Constructor sin ID. Ideal para el registro de nuevos clientes donde el ID
     * aún no ha sido generado por la base de datos (Auto-increment).
     *
     * * @param nombre Nombre(s) del cliente.
     * @param apellidoPaterno Primer apellido.
     * @param apellidoMaterno Segundo apellido.
     * @param domicilio Dirección física de contacto.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     * @param correo Dirección de correo electrónico.
     * @param contraseña Clave de acceso al sistema.
     */
    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, Date fechaNacimiento, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    /**
     * @return El identificador único del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id Nuevo identificador para el cliente.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return El nombre o nombres del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El primer apellido.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno El primer apellido a asignar.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return El segundo apellido.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno El segundo apellido a asignar.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return El domicilio completo registrado.
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * @param domicilio La nueva dirección del cliente.
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * @return La fecha de nacimiento registrada.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento La nueva fecha de nacimiento.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return El correo electrónico de contacto.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo El nuevo correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return La contraseña almacenada (hash).
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña La nueva contraseña a asignar.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
