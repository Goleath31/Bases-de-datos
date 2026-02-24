/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.conexion.IConexionBD;
import persistencia.dominio.Pedido;
import persistencia.excepciones.PersistenciaException;

/**
 *
 * @author joser
 */
public class PedidoAgendadoDAO implements IPedidoAgendadoDAO{
    
    private final IConexionBD conexionBD;
    private static final Logger LOG = Logger.getLogger(PedidoDAO.class.getName());

    public PedidoAgendadoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    

    @Override
    public List<Pedido> consultarPedidoPorCliente(int idCliente) throws PersistenciaException {
        String query = """
                       SELECT p.id_pedido, p.fecha_hora, p.total, p.estado 
                       FROM Pedido p
                       INNER JOIN Pedido_Programado pp ON p.id_pedido = pp.id_pedido
                       WHERE pp.id_cliente = ?
                       ORDER BY p.fecha_hora DESC
                       """;
        List<Pedido> lista = new ArrayList<>();

        try (Connection conn = conexionBD.crearConexion(); 
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pedido p = new Pedido();
                    p.setId(rs.getInt("id_pedido")); 

                    java.sql.Timestamp ts = rs.getTimestamp("fecha_hora");
                    if (ts != null) {
                        p.setFecha(new java.util.Date(ts.getTime())); 
                    }

                    p.setTotal(rs.getFloat("total"));
                    p.setEstado(rs.getString("estado"));

                    lista.add(p);
                }
            }

            return lista;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al consultar pedidos programados del cliente: " + idCliente, ex);
            throw new PersistenciaException("Error al obtener el historial de pedidos: " + ex.getMessage());
        }
    }
    
}
