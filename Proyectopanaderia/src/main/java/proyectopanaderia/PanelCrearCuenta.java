/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import negocio.DTOs.ClienteDTO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;

/**
 *
 * @author joser
 */
public class PanelCrearCuenta extends javax.swing.JPanel {
    
    private FramePrincipal principal;
    Date fechaSeleccionada;

    private JTextField txtCorreo;
    private JTextField txtNombres;
    private JTextField txtApPaterno;
    private JTextField txtApMaterno;
    private JTextField txtTelefono;
    private JTextField txtDomicilio;
    private JPasswordField txtPassword;
    
    //paleta de colores
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");

    /**
     * Creates new form PanelCrearCuenta
     */
    public PanelCrearCuenta(FramePrincipal principal) {
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
        panelCabecera.setBorder(new EmptyBorder(15, 30, 15, 30));

        JLabel lblTitulo = new JLabel("Crear cuenta");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 28));
        
        panelCabecera.add(lblTitulo);
        panelCabecera.add(Box.createHorizontalGlue());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setOpaque(false);

        // Correo
        txtCorreo = crearTextField("correo electrónico", false);
        panelFormulario.add(envolverEnPanelAzul(txtCorreo));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));

        // Nombres
        txtNombres = crearTextField("Nombres", false);
        panelFormulario.add(envolverEnPanelAzul(txtNombres));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));

        // Apellidos (Doble)
        txtApPaterno = crearTextField("Apellido paterno", false);
        txtApMaterno = crearTextField("Apellido materno", false);
        panelFormulario.add(crearFilaInputDobleManual(txtApPaterno, txtApMaterno));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // FEcha
        panelFormulario.add(crearFilaSpinnerFecha("Fecha de nacimiento"));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Teléfono
        txtTelefono = crearTextField("Numero de telefono", false);
        panelFormulario.add(envolverEnPanelAzul(txtTelefono));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        
        txtDomicilio = crearTextField("Domicilio completo (Calle, Número, Colonia)", false);
        panelFormulario.add(envolverEnPanelAzul(txtDomicilio));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));

        // Contraseña
        txtPassword = (JPasswordField) crearTextField("Contraseña", true);
        panelFormulario.add(envolverEnPanelAzul(txtPassword));
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
        

        
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new BoxLayout(panelBoton, BoxLayout.X_AXIS));
        panelBoton.setOpaque(false);
        panelBoton.setMaximumSize(new Dimension(800, 50));

        JButton btnCrear = new JButton("Crear cuenta");
        btnCrear.setBackground(colorConfirmar);
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        btnCrear.setFont(new Font("Arial", Font.PLAIN, 18));
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCrear.setPreferredSize(new Dimension(200, 45));
        btnCrear.setMaximumSize(new Dimension(200, 45));

        btnCrear.addActionListener(e -> {
            
            String correo = txtCorreo.getText();
            String nombre = txtNombres.getText();
            String apPaterno = txtApPaterno.getText();
            String apMaterno = txtApMaterno.getText();
            String domicilio = txtDomicilio.getText();
            String password = new String(txtPassword.getPassword());
            
            if (nombre.equals("Nombres") || correo.equals("correo electrónico")) {
                JOptionPane.showMessageDialog(this, "Por favor llena todos los campos");
                return;
            }
            
            try {
                ClienteDTO dto = new ClienteDTO();
                dto.setNombre(nombre);
                dto.setApellidoPaterno(apPaterno);
                dto.setApellidoMaterno(apMaterno);
                dto.setCorreo(correo);
                dto.setDomicilio(domicilio);
                dto.setContraseña(password);
                dto.setFechaNacimiento(fechaSeleccionada); 

                FabricaBOs.obtenerClienteBO().agregarCliente(dto);

                JOptionPane.showMessageDialog(this, "Cuenta creada exitosamente");
                principal.mostrarPanel(new PanelIndexCliente(principal));

            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            principal.mostrarPanel(new PanelIndexCliente(principal)); 
        });

        
        panelBoton.add(Box.createHorizontalGlue());
        panelBoton.add(btnCrear);

        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(panelCabecera);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(panelFormulario);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(panelBoton);
    }

    private JPanel crearFilaInputUnico(String placeholder, boolean esPassword) {
        JPanel panelFila = configurarPanelAzul();
        JTextField campo = crearTextField(placeholder, esPassword);
        panelFila.add(campo);
        return panelFila;
    }

    private JPanel crearFilaInputDoble(String placeholder1, String placeholder2) {
        JPanel panelFila = configurarPanelAzul();
        panelFila.setLayout(new BoxLayout(panelFila, BoxLayout.X_AXIS));

        JTextField campo1 = crearTextField(placeholder1, false);
        JTextField campo2 = crearTextField(placeholder2, false);

        panelFila.add(campo1);
        panelFila.add(Box.createRigidArea(new Dimension(20, 0))); 
        panelFila.add(campo2);

        return panelFila;
    }

    private JPanel crearFilaSpinnerFecha(String etiqueta) {
        JPanel panelFila = configurarPanelAzul();
        panelFila.setLayout(new BorderLayout()); 

        
        JPanel panelBlanco = new JPanel();
        panelBlanco.setLayout(new BoxLayout(panelBlanco, BoxLayout.X_AXIS));
        panelBlanco.setBackground(Color.WHITE);
        panelBlanco.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel lblDescriptivo = new JLabel(etiqueta + ":   ");
        lblDescriptivo.setForeground(Color.GRAY);
        lblDescriptivo.setFont(new Font("Arial", Font.PLAIN, 18));
        
        SpinnerDateModel modeloFecha = new SpinnerDateModel();
        JSpinner spinnerFecha = new JSpinner(modeloFecha);
        JSpinner.DateEditor editorFecha = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
        spinnerFecha.setEditor(editorFecha);
        spinnerFecha.setMaximumSize(new Dimension(150, 30));
        
        editorFecha.getTextField().setFont(new Font("Arial", Font.PLAIN, 18));
        editorFecha.getTextField().setBorder(BorderFactory.createEmptyBorder()); 
        editorFecha.getTextField().setBackground(Color.WHITE);

        spinnerFecha.addChangeListener(e -> {
            java.util.Date fechaUtil = modeloFecha.getDate();
            fechaSeleccionada = new java.sql.Date(fechaUtil.getTime());
        });
        
        java.util.Date fechaUtilInicial = modeloFecha.getDate();
        fechaSeleccionada = new java.sql.Date(fechaUtilInicial.getTime());

        panelBlanco.add(lblDescriptivo);
        panelBlanco.add(spinnerFecha);
        panelBlanco.add(Box.createHorizontalGlue()); 

        panelFila.add(panelBlanco, BorderLayout.CENTER);

        return panelFila;
    }

    private JPanel configurarPanelAzul() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(colorPaneles); 
        panel.setMaximumSize(new Dimension(800, 55));
        panel.setPreferredSize(new Dimension(800, 55));
        panel.setBorder(new EmptyBorder(10, 15, 10, 15)); 
        return panel;
    }

    private JTextField crearTextField(String placeholder, boolean esPassword) {
        JTextField txt;
        if (esPassword) {
            txt = new JPasswordField();
            ((JPasswordField)txt).setEchoChar((char)0); 
        } else {
            txt = new JTextField();
        }

        txt.setText(placeholder);
        txt.setForeground(Color.GRAY);
        txt.setFont(new Font("Arial", Font.PLAIN, 18));
        txt.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); 
        
        // Lógica para que el placeholder desaparezca al hacer clic
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt.getText().equals(placeholder)) {
                    txt.setText("");
                    txt.setForeground(Color.BLACK);
                    if (esPassword) {
                        ((JPasswordField)txt).setEchoChar('•'); // Cambia a viñetas
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt.getText().isEmpty()) {
                    if (esPassword) {
                        ((JPasswordField)txt).setEchoChar((char)0); // Quita las viñetas
                    }
                    txt.setForeground(Color.GRAY);
                    txt.setText(placeholder);
                }
            }
        });

        return txt;
    }
    
    private JPanel envolverEnPanelAzul(JComponent componente) {
        JPanel panelFila = configurarPanelAzul();
        panelFila.add(componente);
        return panelFila;
    }

    private JPanel crearFilaInputDobleManual(JTextField c1, JTextField c2) {
        JPanel panelFila = configurarPanelAzul();
        panelFila.setLayout(new BoxLayout(panelFila, BoxLayout.X_AXIS));
        panelFila.add(c1);
        panelFila.add(Box.createRigidArea(new Dimension(20, 0)));
        panelFila.add(c2);
        return panelFila;
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
