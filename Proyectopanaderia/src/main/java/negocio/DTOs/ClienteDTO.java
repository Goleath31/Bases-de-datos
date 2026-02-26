/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

import java.util.Date;

/**
 * Data Transfer Object (DTO) que representa la información de un Cliente. Se
 * utiliza para transportar datos entre las capas de la aplicación.
 *
 * * @author joser
 */
public class ClienteDTO {

    private int idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String domicilio;
    private Date fechaNacimiento;
    private String correo;
    private String contraseña;

    /**
     * Constructor completo para inicializar un Cliente con todos sus atributos,
     * incluyendo el identificador único.
     *
     * * @param idCliente Identificador único del cliente.
     * @param nombre Nombre(s) del cliente.
     * @param apellidoPaterno Apellido paterno del cliente.
     * @param apellidoMaterno Apellido materno del cliente.
     * @param domicilio Dirección de residencia del cliente.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     * @param correo Dirección de correo electrónico.
     * @param contraseña Contraseña de acceso al sistema.
     */
    public ClienteDTO(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, Date fechaNacimiento, String correo, String contraseña) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    /**
     * Constructor sin el ID, ideal para operaciones de creación (Insert) donde
     * el identificador es generado automáticamente por la base de datos.
     *
     * * @param nombre Nombre(s) del cliente.
     * @param apellidoPaterno Apellido paterno del cliente.
     * @param apellidoMaterno Apellido materno del cliente.
     * @param domicilio Dirección de residencia del cliente.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     * @param correo Dirección de correo electrónico.
     * @param contraseña Contraseña de acceso al sistema.
     */
    public ClienteDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, Date fechaNacimiento, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    /**
     * Constructor vacío por defecto.
     */
    public ClienteDTO() {
    }

    /**
     * @return El ID del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente El ID del cliente a establecer.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El apellido paterno.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno El apellido paterno a establecer.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return El apellido materno.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno El apellido materno a establecer.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return El domicilio registrado.
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * @param domicilio El domicilio a establecer.
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * @return La fecha de nacimiento.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento La fecha de nacimiento a establecer.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return El correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo El correo electrónico a establecer.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return La contraseña del cliente.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña La contraseña a establecer.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", domicilio=" + domicilio + ", fechaNacimiento=" + fechaNacimiento + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + '}';
    }

}
