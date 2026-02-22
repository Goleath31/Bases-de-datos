/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author joser
 */
public class PanelIndexCliente extends javax.swing.JPanel {
    private FramePrincipal principal;
    
    /**
     * Creates new form PanelIndexCliente
     */
    public PanelIndexCliente(FramePrincipal principal) {
        this.principal = principal;
        
        initComponents();
        iniciarComponentes();
    }

    public void iniciarComponentes(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.decode("#EEF4ED")); 

        //colores globales
        Color colorHeader = Color.decode("#13315C");
        Color colorBotonesTop = Color.decode("#134074");
        Color colorBotonesBot = Color.decode("#8DA9C4");
        Font fuenteBotones = new Font("SansSerif", Font.PLAIN, 28);
        
        //dimensiones boton
        Dimension tamañoBoton = new Dimension(390, 200);

        //header
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS)); 
        panelHeader.setBackground(colorHeader);
        panelHeader.setMaximumSize(new Dimension(1024, 160)); 
        panelHeader.setPreferredSize(new Dimension(1024, 160));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        JLabel lblIndice = new JLabel("Indice");
        lblIndice.setFont(new Font("SansSerif", Font.BOLD, 40)); 
        lblIndice.setForeground(Color.WHITE); 

        panelHeader.add(lblIndice);
        
        //fila de botones sup
        JPanel fila1 = new JPanel();
        fila1.setLayout(new BoxLayout(fila1, BoxLayout.X_AXIS));
        fila1.setOpaque(false); 

        JButton btnAgendar = new JButton("<html><center>Agendar<br>pedido</center></html>");
        aplicarEstiloBoton(btnAgendar, tamañoBoton, fuenteBotones, colorBotonesTop);
        btnAgendar.addActionListener(e -> {
            principal.mostrarPanel(new PanelAgendarPedido(principal));
        });

        JButton btnExpress = new JButton("<html><center>Pedido<br>Express</center></html>");
        aplicarEstiloBoton(btnExpress, tamañoBoton, fuenteBotones, colorBotonesTop);
        btnExpress.addActionListener(e -> { /* Acción pendiente */ });

        fila1.add(Box.createHorizontalGlue());
        fila1.add(btnAgendar);
        fila1.add(Box.createRigidArea(new Dimension(40, 0))); 
        fila1.add(btnExpress);
        fila1.add(Box.createHorizontalGlue());

        //fila de botones inf
        JPanel fila2 = new JPanel();
        fila2.setLayout(new BoxLayout(fila2, BoxLayout.X_AXIS));
        fila2.setOpaque(false);

        JButton btnPerfil = new JButton("<html><center>Gestionar<br>perfil</center></html>");
        aplicarEstiloBoton(btnPerfil, tamañoBoton, fuenteBotones, colorBotonesBot);
        btnPerfil.addActionListener(e -> { /* Acción pendiente */ });

        JButton btnHistorial = new JButton("<html><center>Historial<br>pedidos</center></html>");
        aplicarEstiloBoton(btnHistorial, tamañoBoton, fuenteBotones, colorBotonesBot);
        btnHistorial.addActionListener(e -> { /* Acción pendiente */ });
        
        fila2.add(Box.createHorizontalGlue());
        fila2.add(btnPerfil);
        fila2.add(Box.createRigidArea(new Dimension(40, 0))); 
        fila2.add(btnHistorial);
        fila2.add(Box.createHorizontalGlue());

        //footer
        JPanel panelFooter = new JPanel();
        panelFooter.setLayout(new BoxLayout(panelFooter, BoxLayout.X_AXIS));
        panelFooter.setOpaque(false);
        panelFooter.setMaximumSize(new Dimension(820, 40)); 

        JLabel lblTexto = new JLabel("No tienes cuenta? ");
        lblTexto.setFont(new Font("SansSerif", Font.PLAIN, 22));
        lblTexto.setForeground(Color.BLACK);

        JButton btnRegistrate = new JButton("Registrate....");
        btnRegistrate.setFont(new Font("SansSerif", Font.PLAIN, 22));
        btnRegistrate.setForeground(Color.BLACK);
        btnRegistrate.setContentAreaFilled(false);
        btnRegistrate.setBorderPainted(false);
        btnRegistrate.setFocusPainted(false);
        btnRegistrate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrate.setMargin(new Insets(0, 0, 0, 0)); 
        btnRegistrate.addActionListener(e -> { /* Acción para registro */ });

        panelFooter.add(lblTexto);
        panelFooter.add(btnRegistrate);
        panelFooter.add(Box.createHorizontalGlue()); 

        this.add(panelHeader);
        this.add(Box.createRigidArea(new Dimension(0, 50))); 
        this.add(fila1);
        this.add(Box.createRigidArea(new Dimension(0, 40))); 
        this.add(fila2);
        this.add(Box.createRigidArea(new Dimension(0, 20))); 
        this.add(panelFooter);
        this.add(Box.createVerticalGlue());
    }
    
    private void aplicarEstiloBoton(JButton boton, Dimension dim, Font fuente, Color colorFondo) {
        boton.setFont(fuente);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setPreferredSize(dim);
        boton.setMaximumSize(dim);
        boton.setMinimumSize(dim);
        boton.setFocusPainted(false);
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
