/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author joser
 */
public class PanelGestionarPerfil extends javax.swing.JPanel {
    FramePrincipal principal;
    
    //VARIABLES QUE SIMULAN EL BACKED SOLO TEMPORAL
    private final String[] nombreCliente = {"Juan Rodriguez Villanueva"};
    private final String[] direccionCliente = {"Rio de la juventud #34 Col. Itson"};
    private final String[] fechaNacimiento = {"30 de noviembre del 2001"};
    
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorBotones = Color.decode("#134074");

    /**
     * Creates new form PanelGestionarPerfil
     */
    public PanelGestionarPerfil(FramePrincipal principal) {
        this.principal = principal;
        initComponents();
        iniciarComponentes();
    }
    
    /**
     * Metodo de inicialización de los componentes del panel 
     */
    public void iniciarComponentes(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);

        // header
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
        panelHeader.setBackground(colorHeader);
        panelHeader.setMaximumSize(new Dimension(1024, 120));
        panelHeader.setPreferredSize(new Dimension(1024, 120));
        panelHeader.setBorder(new EmptyBorder(0, 50, 0, 0));

        JLabel lblTituloHeader = new JLabel("Gestor de perfil");
        lblTituloHeader.setFont(new Font("SansSerif", Font.PLAIN, 36));
        lblTituloHeader.setForeground(Color.WHITE);
        panelHeader.add(lblTituloHeader);

        // panel principal
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(new EmptyBorder(30, 80, 30, 80));

        //seccion sup
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
        panelTop.setOpaque(false);
        panelTop.setMaximumSize(new Dimension(860, 50));

        //Botón de opciones 
        JButton btnEngrane = new JButton("⚙"); 
        btnEngrane.setFont(new Font("SansSerif", Font.PLAIN, 30));
        btnEngrane.setForeground(Color.BLACK);
        btnEngrane.setContentAreaFilled(false);
        btnEngrane.setBorderPainted(false);
        btnEngrane.setFocusPainted(false);
        btnEngrane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEngrane.addActionListener(e -> { 
            /* Acción pendiente para el engrane */ 
        });

        JLabel lblSubtitulo = new JLabel("Mis datos");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 32));
        lblSubtitulo.setForeground(Color.BLACK);

        panelTop.add(btnEngrane);
        panelTop.add(Box.createRigidArea(new Dimension(10, 0))); 
        panelTop.add(lblSubtitulo);
        panelTop.add(Box.createHorizontalGlue());

        JSeparator sep1 = new JSeparator();
        sep1.setMaximumSize(new Dimension(860, 1));
        sep1.setForeground(colorPaneles);

        //lista de datos
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setOpaque(false);

        //cuadros de informacion
        panelDatos.add(crearRecuadroDato("Nombre", nombreCliente));
        panelDatos.add(Box.createRigidArea(new Dimension(0, 15)));
        panelDatos.add(crearRecuadroDato("Dirección", direccionCliente));
        panelDatos.add(Box.createRigidArea(new Dimension(0, 15)));
        panelDatos.add(crearRecuadroDato("Fecha de nacimiento", fechaNacimiento));

        JSeparator sep2 = new JSeparator();
        sep2.setMaximumSize(new Dimension(860, 1));
        sep2.setForeground(colorPaneles);

        //botones
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setOpaque(false);
        bottomPanel.setMaximumSize(new Dimension(860, 50));

        JButton btnOpcionesTel = new JButton("Opciones de telefono");
        estilarBotonInferior(btnOpcionesTel);
        btnOpcionesTel.addActionListener(e -> { /* Acción pendiente */ });

        JButton btnRegresar = new JButton("Regresar");
        estilarBotonInferior(btnRegresar);
        btnRegresar.addActionListener(e -> { 
            principal.mostrarPanel(new PanelIndexCliente(principal));
        });

        bottomPanel.add(Box.createHorizontalGlue()); 
        bottomPanel.add(btnOpcionesTel);
        bottomPanel.add(Box.createRigidArea(new Dimension(40, 0))); 
        bottomPanel.add(btnRegresar);
        bottomPanel.add(Box.createHorizontalGlue()); 

        mainContent.add(panelTop);
        mainContent.add(Box.createRigidArea(new Dimension(0, 10)));
        mainContent.add(sep1);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(panelDatos);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(sep2);
        mainContent.add(Box.createRigidArea(new Dimension(0, 30)));
        mainContent.add(bottomPanel);

        this.add(panelHeader);
        this.add(mainContent);
    }

    /**
     * Metodo de ayuda para dar formato a paneles de datos
     * @param etiqueta etiqueta del panel
     * @param variableValor dato a insertar
     * @return JPanel con formato con los datos
     */
    private JPanel crearRecuadroDato(String etiqueta, String[] variableValor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(colorPaneles);
        panel.setMaximumSize(new Dimension(800, 80));
        panel.setPreferredSize(new Dimension(800, 80));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblEtiqueta.setForeground(Color.BLACK);

        JLabel lblValor = new JLabel(variableValor[0]);
        lblValor.setFont(new Font("SansSerif", Font.PLAIN, 22));
        lblValor.setForeground(Color.WHITE);

        textPanel.add(lblEtiqueta);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5))); 
        textPanel.add(lblValor);

        JButton btnLapiz = new JButton("✎");
        btnLapiz.setFont(new Font("SansSerif", Font.BOLD, 28));
        btnLapiz.setFocusPainted(false);
        btnLapiz.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //lógica para editar el dato
        btnLapiz.addActionListener(e -> {
            String nuevoDato = JOptionPane.showInputDialog(this, "Editar " + etiqueta + ":", variableValor[0]);
            
            // Si el usuario no canceló y no dejó el campo vacío
            if (nuevoDato != null && !nuevoDato.trim().isEmpty()) {
                variableValor[0] = nuevoDato; 
                lblValor.setText(nuevoDato);  
            }
        });

        panel.add(textPanel);
        panel.add(Box.createHorizontalGlue()); 
        panel.add(btnLapiz);

        return panel;
    }
    
    /**
     * Metodo de ayuda para dar estilo los botones
     * @param boton 
     */
    private void estilarBotonInferior(JButton boton) {
        boton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        boton.setBackground(colorBotones);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(250, 50));
        boton.setMaximumSize(new Dimension(250, 50));
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
