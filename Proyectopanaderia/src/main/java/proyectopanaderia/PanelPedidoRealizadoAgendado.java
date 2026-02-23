/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author joser
 */
public class PanelPedidoRealizadoAgendado extends javax.swing.JPanel {

    private FramePrincipal principal;

    //Paleta de colores
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorTextoOscuro = Color.decode("#000000");
    
    /**
     * Creates new form PanelPedidoRealizadoAgendado
     */
    public PanelPedidoRealizadoAgendado(FramePrincipal principal) {
        this.principal = principal;
        initComponents();
        iniciarComponentes();
    }
    
    public void iniciarComponentes(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);
        this.setBorder(new EmptyBorder(0, 0, 0, 0)); 

        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BoxLayout(panelCabecera, BoxLayout.X_AXIS));
        panelCabecera.setBackground(colorHeader);
        panelCabecera.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        panelCabecera.setPreferredSize(new Dimension(800, 80));
        panelCabecera.setBorder(new EmptyBorder(15, 50, 15, 30));

        JLabel lblTituloHeader = new JLabel("Crear pedido");
        lblTituloHeader.setForeground(Color.WHITE);
        lblTituloHeader.setFont(new Font("Arial", Font.PLAIN, 28));
        
        panelCabecera.add(lblTituloHeader);
        panelCabecera.add(Box.createHorizontalGlue());

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setOpaque(false);
        panelContenido.setBorder(new EmptyBorder(30, 80, 30, 80));

        
        JPanel panelTituloCentral = new JPanel();
        panelTituloCentral.setLayout(new BoxLayout(panelTituloCentral, BoxLayout.X_AXIS));
        panelTituloCentral.setOpaque(false);
        panelTituloCentral.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblExito = new JLabel("Pedido realizado con éxito");
        lblExito.setForeground(colorTextoOscuro);
        lblExito.setFont(new Font("Arial", Font.PLAIN, 24));
        
        panelTituloCentral.add(Box.createRigidArea(new Dimension(30, 0)));
        panelTituloCentral.add(lblExito);
        panelTituloCentral.add(Box.createHorizontalGlue());

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(colorPaneles);
        panelInfo.setMaximumSize(new Dimension(800, 350));
        panelInfo.setPreferredSize(new Dimension(800, 350));
        panelInfo.setBorder(new EmptyBorder(25, 30, 25, 30));
        panelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Agregar datos por back
        panelInfo.add(crearLabelBlanco("Fecha de entrega:"));
        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfo.add(crearLabelBlanco("18 de febrero de 2026"));
        
        panelInfo.add(Box.createRigidArea(new Dimension(0, 20)));
        
        

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new BoxLayout(panelBoton, BoxLayout.X_AXIS));
        panelBoton.setOpaque(false);

        JButton btnRegresar = new JButton("Regresar al menú");
        btnRegresar.setBackground(colorHeader);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setPreferredSize(new Dimension(200, 45));
        btnRegresar.setMaximumSize(new Dimension(200, 45));

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principal.mostrarPanel(new PanelIndexCliente(principal)); 
            }
        });

        panelBoton.add(Box.createHorizontalGlue());
        panelBoton.add(btnRegresar);


        panelContenido.add(panelTituloCentral);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 15)));
        panelContenido.add(crearLineaSeparadora());
        
        panelContenido.add(Box.createRigidArea(new Dimension(0, 25)));
        panelContenido.add(panelInfo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 25)));
        
        panelContenido.add(crearLineaSeparadora());
        panelContenido.add(Box.createRigidArea(new Dimension(0, 30)));
        
        panelContenido.add(panelBoton);


        this.add(panelCabecera);
        this.add(panelContenido);
        this.add(Box.createVerticalGlue());
    
    }
    
    private JPanel crearLineaSeparadora() {
        JPanel linea = new JPanel();
        linea.setBackground(colorPaneles);
        linea.setMaximumSize(new Dimension(800, 1));
        return linea;
    }

    private JLabel crearLabelBlanco(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.PLAIN, 18));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
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
