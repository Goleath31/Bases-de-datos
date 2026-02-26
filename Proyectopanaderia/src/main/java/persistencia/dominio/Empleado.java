/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.dominio;

/**
 * Representa a un empleado dentro del sistema de persistencia.
 * Esta clase se utiliza para gestionar la información del personal, 
 * facilitando procesos como la autenticación y la asignación de tareas.
 * * @author joser
 */
public class Empleado {

    private int idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contrasena;

    /**
     * Constructor vacío por defecto. Permite la creación de instancias sin
     * inicializar atributos de forma inmediata.
     */
    public Empleado() {
    }

    /**
     * Constructor con todos los campos para inicializar un empleado completo.
     * Generalmente utilizado para cargar datos existentes desde la base de
     * datos.
     *
     * * @param idEmpleado Identificador único del empleado.
     * @param nombre Nombre del empleado.
     * @param apellidoPaterno Primer apellido del empleado.
     * @param apellidoMaterno Segundo apellido del empleado.
     * @param correo Dirección de correo electrónico institucional.
     * @param contrasena Contraseña de acceso al sistema (almacenada como hash).
     */
    public Empleado(int idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasena) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    /**
     * @return El identificador único del empleado.
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado El nuevo identificador a asignar.
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return El nombre del empleado.
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
     * @return El primer apellido del empleado.
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
     * @return El segundo apellido del empleado.
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
     * @return El correo electrónico institucional.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo El nuevo correo institucional.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return La contraseña de acceso actual.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena La nueva contraseña a asignar.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
