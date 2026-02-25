/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectopanaderia;

import negocio.DTOs.ClienteDTO;

/**
 *
 * @author joser
 */
public class SesionCliente {
    private static ClienteDTO clienteSesion;

    public static void iniciarSesion(ClienteDTO cliente) {
        clienteSesion = cliente;
    }

    public static void cerrarSesion() {
        clienteSesion = null;
    }

    public static ClienteDTO getCliente() {
        return clienteSesion;
    }

    public static boolean estaLogueado() {
        return clienteSesion != null;
    }
}
