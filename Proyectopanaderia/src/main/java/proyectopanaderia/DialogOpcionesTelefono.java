/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectopanaderia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 *
 * @author joser
 */
public class DialogOpcionesTelefono extends javax.swing.JDialog {

    private JPanel panelListaTelefonos;
    
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorBotones = Color.decode("#13315C");
    
    /**
     * Creates new form DialogOpcionesTelefono
     */
    public DialogOpcionesTelefono(JFrame parent) {
        super(parent, true);
        this.setUndecorated(true);
        iniciarComponentes();
        this.setSize(896, 672);
        this.setLocationRelativeTo(parent); 
        this.setUndecorated(true); 
        
        
        this.getRootPane().setBorder(new LineBorder(colorBotones, 10));
    }
    
    private void iniciarComponentes(){
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(colorFondo);
        mainContent.setBorder(new EmptyBorder(50, 50, 50, 50)); 

        //lista de telefonos
        panelListaTelefonos = new JPanel();
        panelListaTelefonos.setLayout(new BoxLayout(panelListaTelefonos, BoxLayout.Y_AXIS));
        panelListaTelefonos.setOpaque(false);

        //SOLO PARA DEBUGEAR CAMBIAR AL CONECTAR CON BACKEND
        panelListaTelefonos.add(crearFilaTelefono("Numero de casa", "402 438 2790"));
        panelListaTelefonos.add(Box.createRigidArea(new Dimension(0, 20)));
        panelListaTelefonos.add(crearFilaTelefono("Numero trabajo", "402 438 2790"));
        panelListaTelefonos.add(Box.createRigidArea(new Dimension(0, 20)));
        panelListaTelefonos.add(crearFilaTelefono("Numero novia", "402 438 2790"));

        JScrollPane scrollTelefonos = new JScrollPane(panelListaTelefonos);
        scrollTelefonos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollTelefonos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTelefonos.setBorder(null);
        scrollTelefonos.getViewport().setOpaque(false);
        scrollTelefonos.setOpaque(false);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setOpaque(false);
        bottomPanel.setMaximumSize(new Dimension(800, 60));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("SansSerif", Font.PLAIN, 24));
        btnRegresar.setBackground(colorBotones);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setPreferredSize(new Dimension(250, 60));
        btnRegresar.setMaximumSize(new Dimension(250, 60));
        
        //cerrar popup
        btnRegresar.addActionListener(e -> this.dispose());

        bottomPanel.add(Box.createHorizontalGlue()); 
        bottomPanel.add(btnRegresar);

        mainContent.add(scrollTelefonos);
        mainContent.add(Box.createRigidArea(new Dimension(0, 30)));
        mainContent.add(bottomPanel);

        this.add(mainContent);
    }
    
    
    private JPanel crearFilaTelefono(String tipo, String numero) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(colorPaneles);
        panel.setMaximumSize(new Dimension(800, 90));
        panel.setPreferredSize(new Dimension(800, 90));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel lblTipo = new JLabel(tipo);
        lblTipo.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblTipo.setForeground(Color.BLACK);

        //arreglo de 1 posición para poder modificarlo al editar
        final String[] numeroActual = {numero};
        JLabel lblNumero = new JLabel(numeroActual[0]);
        lblNumero.setFont(new Font("SansSerif", Font.PLAIN, 24));
        lblNumero.setForeground(Color.BLACK);

        textPanel.add(lblTipo);
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        textPanel.add(lblNumero);

        JButton btnEditar = new JButton("✎");
        btnEditar.setFont(new Font("SansSerif", Font.PLAIN, 32));
        btnEditar.setContentAreaFilled(false);
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(e -> {
            String nuevoNumero = JOptionPane.showInputDialog(this, "Editar número:", numeroActual[0]);
            if (nuevoNumero != null && !nuevoNumero.trim().isEmpty()) {
                numeroActual[0] = nuevoNumero;
                lblNumero.setText(nuevoNumero);
            }
        });

        //botón eliminar
        JButton btnEliminar = new JButton("🗑"); 
        btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 32));
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar este número?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                panelListaTelefonos.remove(panel);
                panelListaTelefonos.revalidate();
                panelListaTelefonos.repaint();
            }
        });

        panel.add(textPanel);
        panel.add(Box.createHorizontalGlue());
        panel.add(btnEditar);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(btnEliminar);

        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
