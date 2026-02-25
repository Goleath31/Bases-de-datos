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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import negocio.BOs.IClienteBO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;

/**
 *
 * @author joser
 */
public class PanelIniciarSesion extends javax.swing.JPanel {

    private FramePrincipal principal;

    // Paleta de colores
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");

    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    
    /**
     * Creates new form PanelIniciarSesion
     */
    public PanelIniciarSesion(FramePrincipal principal) {
        this.principal = principal;
        initComponents();
        iniciarComponentes();
    }

    
    public void iniciarComponentes(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);
        
        
        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BoxLayout(panelCabecera, BoxLayout.X_AXIS));
        panelCabecera.setBackground(colorHeader);
        panelCabecera.setMaximumSize(new Dimension(1024, 100)); 
        panelCabecera.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Iniciar sesión");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 36)); 
        
        panelCabecera.add(lblTitulo);
        panelCabecera.add(Box.createHorizontalGlue());

        
        txtCorreo = new JTextField();
        txtPassword = new JPasswordField();


        JPanel panelCorreo = crearBloqueInput("Correo", txtCorreo);
        JPanel panelPassword = crearBloqueInput("Contraseña", txtPassword);
        panelCorreo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setMaximumSize(new Dimension(700, 50));

        JButton btnRegresar = crearBoton("Regresar");
        JButton btnIniciar = crearBoton("Iniciar Sesion");

        btnRegresar.addActionListener(e -> {
            principal.mostrarPanel(new PanelIndexCliente(principal));
        }); 
        
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });

        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.add(btnRegresar);
        panelBotones.add(Box.createRigidArea(new Dimension(60, 0)));
        panelBotones.add(btnIniciar);
        panelBotones.add(Box.createHorizontalGlue());

        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(panelCabecera);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(panelCorreo);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(panelPassword);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(panelBotones);
    
    }
    
    
    private JPanel crearBloqueInput(String etiqueta, JTextField campoTexto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(colorPaneles);
        panel.setMaximumSize(new Dimension(700, 110));
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Arial", Font.PLAIN, 24));
        lbl.setForeground(Color.BLACK);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        campoTexto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        campoTexto.setPreferredSize(new Dimension(660, 40));
        campoTexto.setFont(new Font("Arial", Font.PLAIN, 18));
        campoTexto.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoTexto.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 

        panel.add(lbl);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        panel.add(campoTexto);

        return panel;
    }
    
    
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorConfirmar);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.PLAIN, 18));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boton.setPreferredSize(new Dimension(180, 40));
        boton.setMaximumSize(new Dimension(180, 40));
        return boton;
    }

    
    private void Login() {
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            IClienteBO bo = FabricaBOs.obtenerClienteBO();

            bo.validarCliente(correo, password);

            JOptionPane.showMessageDialog(this, "Bienvenido " + SesionCliente.getCliente().getNombre());

            principal.mostrarPanel(new PanelIndexCliente(principal));

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        

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
