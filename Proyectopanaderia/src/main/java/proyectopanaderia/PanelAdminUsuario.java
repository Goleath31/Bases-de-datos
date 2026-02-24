/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author joser
 */
public class PanelAdminUsuario extends javax.swing.JPanel {
    
    private FramePrincipal principal;

    /**
     * Creates new form PanelAdminUsuario
     */
    public PanelAdminUsuario(FramePrincipal principal) {
        this.principal = principal;
        
        initComponents();
        iniciarComponentes();
    }
    
    /**
     * Método que genera los componentes manualmente codificados.
     */
    private void iniciarComponentes(){
        this.setBackground(new Color(238, 244, 237));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1024, 768));
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(11, 37, 69));
        Dimension dimensionTitulo = new Dimension(1024, 170);
        panelTitulo.setPreferredSize(dimensionTitulo);
        panelTitulo.setMaximumSize(dimensionTitulo);
        panelTitulo.setMinimumSize(dimensionTitulo);
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        
        JPanel panelContenido1 = new JPanel();
        JPanel panelContenido2 = new JPanel();
        JPanel panelContenido3 = new JPanel();
        
        //setup de todos los paneles
        Dimension contenido = new Dimension(1024, 200);
        Dimension contenido2 = new Dimension(1024, 198);      
        panelContenido1.setPreferredSize(contenido);
        panelContenido2.setPreferredSize(contenido);
        panelContenido3.setPreferredSize(contenido2);
        panelContenido1.setLayout(new BoxLayout(panelContenido1, BoxLayout.Y_AXIS));
        panelContenido2.setLayout(new BoxLayout(panelContenido2, BoxLayout.X_AXIS));
        panelContenido3.setLayout(new BoxLayout(panelContenido3, BoxLayout.Y_AXIS));
        panelContenido1.setBackground(new Color(238, 244, 237));
        panelContenido2.setBackground(new Color(238, 244, 237));
        panelContenido3.setBackground(new Color(238, 244, 237));
            
        //Label de titulo
        JLabel nombrePanaderia = new JLabel("Panadería Carolina");
        nombrePanaderia.setFont(new Font("Arial",Font.BOLD, 66));
        nombrePanaderia.setForeground(new Color(238, 244, 237));       
        nombrePanaderia.setAlignmentX(this.CENTER_ALIGNMENT);
        panelTitulo.add(Box.createVerticalGlue()); 
        panelTitulo.add(nombrePanaderia);
        panelTitulo.add(Box.createVerticalGlue());
        
        //Label de primer texto
        JLabel textoContenido1 = new JLabel("Seleccione el tipo de operaciones que va a realizar:");
        textoContenido1.setFont(new Font("Arial", Font.PLAIN, 36));
        textoContenido1.setForeground(Color.BLACK); 
        textoContenido1.setAlignmentX(this.CENTER_ALIGNMENT);
        panelContenido1.add(Box.createVerticalGlue());
        panelContenido1.add(textoContenido1);
        panelContenido1.add(Box.createVerticalGlue());
        
        //Botones
        JButton btnAdmin = new JButton("Administrador");
        JButton btnCliente = new JButton("Cliente");
        
        
        btnAdmin.setFont(new Font("Arial", Font.PLAIN, 32));
        btnAdmin.setForeground(new Color(238, 244, 237)); 
        btnAdmin.setBackground(new Color(11, 37, 69));
        
        btnAdmin.addActionListener(e -> {
            System.out.println("Cambiando al panel de Ventas...");
            //PONER AQUI EL ACCESO AL INDEX DE ADMIN
            principal.mostrarPanel(new PanerAdminLogin(principal));
        });
        
        Dimension tamañoFijo = new Dimension(310, 100);

        btnAdmin.setMaximumSize(tamañoFijo);
        btnAdmin.setMinimumSize(tamañoFijo);
        btnAdmin.setPreferredSize(tamañoFijo);
        btnAdmin.setAlignmentX(this.CENTER_ALIGNMENT);
        
        btnCliente.setFont(new Font("Arial", Font.PLAIN, 32));
        btnCliente.setForeground(new Color(238, 244, 237)); 
        btnCliente.setBackground(new Color(11, 37, 69));
        
        btnCliente.addActionListener(e -> {
            principal.mostrarPanel(new PanelIndexCliente(principal));
        });

        btnCliente.setMaximumSize(tamañoFijo);
        btnCliente.setMinimumSize(tamañoFijo);
        btnCliente.setPreferredSize(tamañoFijo);
        btnCliente.setAlignmentX(this.CENTER_ALIGNMENT);
        
        panelContenido2.add(Box.createVerticalGlue());
        panelContenido2.add(btnAdmin);
        panelContenido2.add(Box.createRigidArea(new Dimension(80, 0)));
        panelContenido2.add(btnCliente);
        panelContenido2.add(Box.createVerticalGlue());
        
        
        this.add(panelTitulo);
        this.add(panelContenido1);
        this.add(panelContenido2);
        this.add(panelContenido3);
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(238, 244, 237));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
