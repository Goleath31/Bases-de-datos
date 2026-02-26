/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectopanaderia;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author joser
 */
public class GeneradorFolioPin {
    /**
     * Genera un folio alfanumérico aleatorio (Ej: "F-A8B2")
     */
    public static String generarFolio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder("F-");
        for (int i = 0; i < 4; i++) {
            int index = ThreadLocalRandom.current().nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Genera un PIN numérico de 4 dígitos (Ej: 1024)
     */
    public static String generarPin() {
        // Genera un número entre 1000 y 9999
        return String.valueOf(ThreadLocalRandom.current().nextInt(1000, 10000));
    }
}
