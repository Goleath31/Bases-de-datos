/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import negocio.DTOs.PedidoExpressDTO;

/**
 *
 * @author joser
 */
public class PanelDetallesPedidoExpress extends javax.swing.JPanel {

    private FramePrincipal principal;
    PedidoExpressDTO pedidoExpress;

    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");
    /**
     * Creates new form PanelDetallesPedidoExpress
     */
    public PanelDetallesPedidoExpress(FramePrincipal principal, PedidoExpressDTO pedidoExpressDTO) {
        this.pedidoExpress = pedidoExpressDTO;
        this.principal = principal;
        initComponents();
        
        iniciarComponentes();
    }

    
    public void iniciarComponentes() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);
        this.setBorder(new EmptyBorder(0, 0, 50, 0));

        // --- HEADER ---
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        header.setBackground(colorHeader);
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel lblTituloHeader = new JLabel("Crear pedido");
        lblTituloHeader.setFont(new Font("SansSerif", Font.PLAIN, 22));
        lblTituloHeader.setForeground(Color.WHITE);
        header.add(lblTituloHeader);

        // --- TÍTULO DE ÉXITO ---
        JLabel lblExito = new JLabel("Pedido realizado con éxito");
        lblExito.setFont(new Font("SansSerif", Font.PLAIN, 28));
        lblExito.setForeground(Color.BLACK);
        lblExito.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblExito.setBorder(new EmptyBorder(30, 0, 10, 0));

        // --- SEPARADOR SUPERIOR ---
        JSeparator sep1 = new JSeparator();
        sep1.setMaximumSize(new Dimension(700, 2));
        sep1.setForeground(colorPaneles);

        // --- TARJETA DE INFORMACIÓN (AZUL ACERO) ---
        JPanel cardInfo = new JPanel();
        cardInfo.setLayout(new BoxLayout(cardInfo, BoxLayout.Y_AXIS));
        cardInfo.setBackground(colorPaneles); // El azul claro de tus paneles (#8DA9C4)
        cardInfo.setMaximumSize(new Dimension(700, 350));
        cardInfo.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Datos del DTO (Deja los setters o variables aquí)
        String fecha = "Hoy, pedido listo para recoger en los proximos 20 minutos"; // pedidoExpress.getFechaRecoleccion()
        String folio = pedidoExpress.getFolio(); // ASDF532
        String pin = pedidoExpress.getPinSeguridad();

        cardInfo.add(crearLabelInfo("Fecha de recolección:"));
        cardInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        cardInfo.add(crearLabelDato(fecha));
        cardInfo.add(Box.createRigidArea(new Dimension(0, 30)));
        cardInfo.add(crearLabelInfo("Folio de recolección:"));
        cardInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        cardInfo.add(crearLabelDato(folio));
        cardInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        cardInfo.add(crearLabelInfo("Pin de seguridad:"));
        cardInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        cardInfo.add(crearLabelInfo(pin));
        cardInfo.add(Box.createRigidArea(new Dimension(0, 10)));

        // --- SEPARADOR INFERIOR ---
        JSeparator sep2 = new JSeparator();
        sep2.setMaximumSize(new Dimension(700, 2));
        sep2.setForeground(colorPaneles);

        // --- BOTÓN REGRESAR ---
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 100, 0));
        panelBoton.setOpaque(false);

        JButton btnRegresar = new JButton("Regresar al menú");
        btnRegresar.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnRegresar.setBackground(colorConfirmar);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setPreferredSize(new Dimension(220, 50));
        btnRegresar.addActionListener(e -> principal.mostrarPanel(new PanelIndexCliente(principal)));
        panelBoton.add(btnRegresar);

        // Ensamblaje
        this.add(header);
        this.add(lblExito);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(sep1);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(cardInfo);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(sep2);
        this.add(Box.createVerticalGlue());
        this.add(panelBoton);
    }
    
    private JLabel crearLabelInfo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel crearLabelDato(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
