/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.DTOs;

/**
 *
 * @author joser
 */
public class PedidoAgendadoDTO {
    private int idCliente;
    private int idCupon;

    public PedidoAgendadoDTO(int idCliente, int idCupon) {
        this.idCliente = idCliente;
        this.idCupon = idCupon;
    }

    public PedidoAgendadoDTO() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(int idCupon) {
        this.idCupon = idCupon;
    }
    
    
}
