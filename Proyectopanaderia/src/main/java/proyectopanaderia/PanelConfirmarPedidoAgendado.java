/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author joser
 */
public class PanelConfirmarPedidoAgendado extends javax.swing.JPanel {
    
    private FramePrincipal principal;

    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");
    private Color colorGrisClaro = Color.decode("#D5D5D5");

    /**
     * Creates new form PanelConfirmarPedidoAgendado
     */
    public PanelConfirmarPedidoAgendado(FramePrincipal principal) {
        //Considerar instanciar en el constructor los datos del pedido
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
        panelCabecera.setPreferredSize(new Dimension(1024, 100));
        panelCabecera.setMaximumSize(new Dimension(1024, 100));
        panelCabecera.setBorder(new EmptyBorder(15, 30, 15, 30));

        JLabel lblTitulo = new JLabel("Crear pedido");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 28));
        
        panelCabecera.add(lblTitulo);
        panelCabecera.add(Box.createHorizontalGlue());

        JPanel panelResumen = new JPanel(new BorderLayout(0, 15)); 
        panelResumen.setBackground(colorPaneles);
        panelResumen.setMaximumSize(new Dimension(750, 350));
        panelResumen.setPreferredSize(new Dimension(750, 350));
        panelResumen.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblResumen = new JLabel("Resumen de orden");
        lblResumen.setForeground(Color.WHITE);
        lblResumen.setFont(new Font("Arial", Font.PLAIN, 20));
        panelResumen.add(lblResumen, BorderLayout.NORTH);
        
        JPanel panelListaProductos = new JPanel();
        panelListaProductos.setLayout(new BoxLayout(panelListaProductos, BoxLayout.Y_AXIS));
        panelListaProductos.setBackground(colorPaneles);
        //DATOS GENERICOS DE PRUEBA MODIFICAR DSP
        // Aquí es donde en el futuro pondrás tu: for(Producto p : listaCarrito) { ... }
        for (int i = 1; i <= 10; i++) {
            panelListaProductos.add(crearFilaProducto("Producto genérico " + i, 2, 150.50));
            panelListaProductos.add(Box.createRigidArea(new Dimension(0, 10))); 
        }

        JScrollPane scrollProductos = new JScrollPane(panelListaProductos);
        scrollProductos.setBorder(BorderFactory.createEmptyBorder());
        scrollProductos.getViewport().setBackground(colorPaneles);
        scrollProductos.getVerticalScrollBar().setUnitIncrement(16);
        scrollProductos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelResumen.add(scrollProductos, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.X_AXIS));
        panelInferior.setOpaque(false);
        panelInferior.setMaximumSize(new Dimension(750, 80));

        JPanel panelCupon = new JPanel();
        panelCupon.setLayout(new BoxLayout(panelCupon, BoxLayout.Y_AXIS));
        panelCupon.setOpaque(false);
        
        JLabel lblCupon = new JLabel("Cupón");
        lblCupon.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCupon.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextField txtCupon = new JTextField("");
        txtCupon.setBackground(colorGrisClaro);
        txtCupon.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        txtCupon.setFont(new Font("Arial", Font.PLAIN, 18));
        txtCupon.setMaximumSize(new Dimension(300, 45));
        txtCupon.setPreferredSize(new Dimension(300, 45));
        txtCupon.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelCupon.add(lblCupon);
        panelCupon.add(Box.createRigidArea(new Dimension(0, 5)));
        panelCupon.add(txtCupon);

        JButton btnConfirmar = new JButton("Confirmar pedido");
        btnConfirmar.setBackground(colorConfirmar);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 18));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmar.setPreferredSize(new Dimension(220, 45));
        btnConfirmar.setMaximumSize(new Dimension(220, 45));
        btnConfirmar.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Agregar validacion de cupon y su respectivo dialog de mensaje en caso de no ser correcto
                //Agregar validacion al crear el pedido y meter los datos aqui
                JOptionPane.showMessageDialog(PanelConfirmarPedidoAgendado.this, 
                    "¡Tu pedido se ha confirmado con éxito!", 
                    "Pedido Confirmado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                principal.mostrarPanel(new PanelPedidoRealizadoAgendado(principal)); 
            }
        });

        panelInferior.add(panelCupon);
        panelInferior.add(Box.createHorizontalGlue());
        panelInferior.add(btnConfirmar);


        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(panelCabecera);
        
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(crearLineaSeparadora()); 
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        
        this.add(panelResumen);
        
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(crearLineaSeparadora()); 
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        
        this.add(panelInferior);
    }
    
    /**
     * Crea una línea horizontal fina del color de los paneles para usar como separador
     */
    private JPanel crearLineaSeparadora() {
        JPanel linea = new JPanel();
        linea.setBackground(colorPaneles);
        linea.setMaximumSize(new Dimension(800, 1)); 
        return linea;
    }
    
    private JPanel crearFilaProducto(String nombre, int cantidad, double precioTotal) {
        JPanel panelFila = new JPanel();
        panelFila.setLayout(new BoxLayout(panelFila, BoxLayout.X_AXIS));
        panelFila.setBackground(colorPaneles);
        panelFila.setMaximumSize(new Dimension(700, 30));

        JLabel lblNombre = new JLabel(cantidad + "x " + nombre);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel lblPrecio = new JLabel("$" + String.format("%.2f", precioTotal));
        lblPrecio.setForeground(Color.WHITE);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 18));

        panelFila.add(lblNombre);
        panelFila.add(Box.createHorizontalGlue());
        panelFila.add(lblPrecio);

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
