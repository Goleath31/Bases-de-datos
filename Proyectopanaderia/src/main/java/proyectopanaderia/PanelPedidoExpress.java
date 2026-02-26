/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import negocio.DTOs.ProductoDTO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;

/**
 *
 * @author joser
 */
public class PanelPedidoExpress extends javax.swing.JPanel {
    FramePrincipal principal;
    
    private JPanel panelListaProductos;

    //paleta de colores
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");
    
    private Map<ProductoDTO, Integer> cantidadesPedido = new HashMap<>();

    /**
     * Creates new form PanelPedidoExpress
     */
    public PanelPedidoExpress(FramePrincipal principal) {
        this.principal = principal;
        initComponents();
        iniciarComponentes();
        cargarProductosDisponibles();
    }

    /**
     * Metodo que inicializa componentes del panel de pedido express
     */
    public void iniciarComponentes(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);

        //header
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
        panelHeader.setBackground(colorHeader);
        panelHeader.setMaximumSize(new Dimension(1024, 120));
        panelHeader.setPreferredSize(new Dimension(1024, 120));
        panelHeader.setBorder(new EmptyBorder(0, 50, 0, 0));

        JLabel lblTitulo = new JLabel("Pedido express");
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 36));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(new EmptyBorder(30, 80, 30, 80)); 

        JPanel panelSubtitulo = new JPanel();
        panelSubtitulo.setLayout(new BoxLayout(panelSubtitulo, BoxLayout.X_AXIS));
        panelSubtitulo.setOpaque(false);
        panelSubtitulo.setMaximumSize(new Dimension(860, 40));
        
        JLabel lblSubtitulo = new JLabel("Productos disponibles");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 28));
        lblSubtitulo.setForeground(Color.BLACK);
        panelSubtitulo.add(lblSubtitulo);
        panelSubtitulo.add(Box.createHorizontalGlue());

        JSeparator sep1 = new JSeparator();
        sep1.setMaximumSize(new Dimension(860, 1));
        sep1.setForeground(colorPaneles);

        //lista de productos
        panelListaProductos = new JPanel();
        panelListaProductos.setLayout(new BoxLayout(panelListaProductos, BoxLayout.Y_AXIS));
        panelListaProductos.setOpaque(false);

        JScrollPane scrollProductos = new JScrollPane(panelListaProductos);
        scrollProductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollProductos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProductos.setBorder(null);
        scrollProductos.getViewport().setOpaque(false);
        scrollProductos.setOpaque(false);

        JSeparator sep2 = new JSeparator();
        sep2.setMaximumSize(new Dimension(860, 1));
        sep2.setForeground(colorPaneles);

        // botones
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setOpaque(false);
        bottomPanel.setMaximumSize(new Dimension(860, 50));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnRegresar.addActionListener(e -> {
            principal.mostrarPanel(new PanelIndexCliente(principal));
        });

        JButton btnConfirmar = new JButton("Confirmar pedido");
        btnConfirmar.setFont(new Font("SansSerif", Font.PLAIN, 20));
        btnConfirmar.setBackground(colorConfirmar);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.addActionListener(e -> {
            List<ProductoDTO> seleccionados = new ArrayList<>();

            cantidadesPedido.forEach((prod, cant) -> {
                if (cant > 0) {
                    System.out.println("Producto: " + prod.getNombre() + " Cantidad: " + cant);
                    seleccionados.add(prod);
                }
            });

            if (seleccionados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona al menos un producto.");
                return;
            }

            System.out.println(cantidadesPedido);
            principal.mostrarPanel(new PanelConfirmarPedidoExpress(principal, cantidadesPedido));
        });
        
        bottomPanel.add(btnRegresar);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(btnConfirmar);
        
        mainContent.add(panelSubtitulo);
        mainContent.add(Box.createRigidArea(new Dimension(0, 15)));
        mainContent.add(sep1);
        mainContent.add(Box.createRigidArea(new Dimension(0, 15)));
        mainContent.add(scrollProductos);
        mainContent.add(Box.createRigidArea(new Dimension(0, 15)));
        mainContent.add(sep2);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(bottomPanel);

        this.add(panelHeader);
        this.add(mainContent);
    }
    
    
    private void cargarProductosDisponibles() {
        //Agregar interaccion con el backend aquí
        try{
            List<ProductoDTO> productosDisponibles = FabricaBOs.obtenerProductoBO().obtenerTodosLosProductosActivos();

            panelListaProductos.removeAll();
            cantidadesPedido.clear();

            for (ProductoDTO producto : productosDisponibles) {
                cantidadesPedido.put(producto, 0);
                agregarFilaProducto(producto);
            }

            panelListaProductos.revalidate();
            panelListaProductos.repaint();
        }
        catch(NegocioException ex){
           
        }

    }
    
    /**
     * Metodo de soporte para agregar un producto al panel con sus respectivos datos
     * @param nombreProducto 
     */
    private void agregarFilaProducto(ProductoDTO producto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(colorPaneles);
        panel.setMaximumSize(new Dimension(800, 70));
        panel.setPreferredSize(new Dimension(800, 70));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblNombre.setForeground(Color.WHITE);


        JLabel lblCant = new JLabel("  0  ");
    
        JButton btnMenos = new JButton("-");
        btnMenos.addActionListener(e -> {
            int actual = cantidadesPedido.get(producto);
            if (actual > 0) {
                actual--;
                cantidadesPedido.put(producto, actual);
                lblCant.setText("  " + actual + "  ");
            }
        });

        JButton btnMas = new JButton("+");
        btnMas.addActionListener(e -> {
            int actual = cantidadesPedido.get(producto);
            actual++;
            cantidadesPedido.put(producto, actual);
            lblCant.setText("  " + actual + "  ");
        });

        panel.add(lblNombre);
        panel.add(Box.createHorizontalGlue());
        panel.add(btnMenos);
        panel.add(lblCant);
        panel.add(btnMas);

        panelListaProductos.add(panel);
        panelListaProductos.add(Box.createRigidArea(new Dimension(0, 10)));
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
