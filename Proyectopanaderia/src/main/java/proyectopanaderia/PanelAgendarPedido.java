/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author joser
 */
public class PanelAgendarPedido extends javax.swing.JPanel {
    FramePrincipal principal;   
    private Date fechaSeleccionada;
    private Set<String> productosSeleccionados = new HashSet<>();
    
    // Paleta de colores
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");
    
    private JPanel panelListaProductos;
    private JPanel btnAgregarProducto;
    
    /**
     * Creates new form PanelAgendarPedido
     */
    public PanelAgendarPedido(FramePrincipal principal) {
        this.principal = principal;
        initComponents();
        iniciarComponentes();
    }
    
    /**
     * Crea los componentes de nuestro panel de forma directa con código
     */
    public void iniciarComponentes() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);

        // header
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
        panelHeader.setBackground(colorHeader);
        panelHeader.setMaximumSize(new Dimension(1024, 120));
        panelHeader.setPreferredSize(new Dimension(1024, 120));
        panelHeader.setBorder(new EmptyBorder(0, 50, 0, 0));

        JLabel lblTitulo = new JLabel("Crear pedido");
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 36));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        // panel principal
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(new EmptyBorder(30, 80, 30, 80)); // Márgenes: Arriba, Izquierda, Abajo, Derecha

        // fecha de entrega
        JPanel panelFecha = new JPanel();
        panelFecha.setLayout(new BoxLayout(panelFecha, BoxLayout.X_AXIS));
        panelFecha.setBackground(colorPaneles);
        panelFecha.setMaximumSize(new Dimension(800, 50));
        panelFecha.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel lblFecha = new JLabel("Fecha de entrega:");
        lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblFecha.setForeground(Color.WHITE);
        
        SpinnerDateModel modeloFecha = new SpinnerDateModel();
        JSpinner spinnerFecha = new JSpinner(modeloFecha);
        JSpinner.DateEditor editorFecha = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
        spinnerFecha.setEditor(editorFecha);
        spinnerFecha.setMaximumSize(new Dimension(150, 30));
        spinnerFecha.setFont(new Font("SansSerif", Font.PLAIN, 18));
        
        // Listener para actualizar tu variable Date cuando cambien el valor
        spinnerFecha.addChangeListener(e -> {
            // 1. Obtenemos la fecha util del JSpinner
            java.util.Date fechaUtil = modeloFecha.getDate();
            // 2. La convertimos a sql.Date usando getTime()
            fechaSeleccionada = new java.sql.Date(fechaUtil.getTime());
        });
        
        // Inicializamos variable fecha
        java.util.Date fechaUtilInicial = modeloFecha.getDate();
        fechaSeleccionada = new java.sql.Date(fechaUtilInicial.getTime());

        panelFecha.add(lblFecha);
        panelFecha.add(Box.createRigidArea(new Dimension(20, 0)));
        panelFecha.add(spinnerFecha);
        panelFecha.add(Box.createHorizontalGlue());
        
        JSeparator sep1 = new JSeparator();
        sep1.setMaximumSize(new Dimension(860, 1));
        sep1.setForeground(colorPaneles);

        // Scroll lista de productos
        panelListaProductos = new JPanel();
        panelListaProductos.setLayout(new BoxLayout(panelListaProductos, BoxLayout.Y_AXIS));
        panelListaProductos.setOpaque(false);

        // Creamos el botón inicial de "+"
        btnAgregarProducto = crearPanelAgregarProducto();
        panelListaProductos.add(btnAgregarProducto);

        //en caso de que se llene la lista se envuelve en un scroll el panel de productos
        JScrollPane scrollProductos = new JScrollPane(panelListaProductos);
        scrollProductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollProductos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProductos.setBorder(null);
        scrollProductos.getViewport().setOpaque(false);
        scrollProductos.setOpaque(false);

        JSeparator sep2 = new JSeparator();
        sep2.setMaximumSize(new Dimension(860, 1));
        sep2.setForeground(colorPaneles);

        // Botones inferiores
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setOpaque(false);
        bottomPanel.setMaximumSize(new Dimension(860, 50));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnRegresar.addActionListener(e -> principal.mostrarPanel(new PanelIndexCliente(principal)));

        JButton btnConfirmar = new JButton("Confirmar pedido");
        btnConfirmar.setFont(new Font("SansSerif", Font.PLAIN, 20));
        btnConfirmar.setBackground(colorConfirmar);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.addActionListener(e -> {
            //agregar datos a constructor (?)
            principal.mostrarPanel(new PanelConfirmarPedidoAgendado(principal));
        
        });

        bottomPanel.add(btnRegresar);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(btnConfirmar);

        mainContent.add(panelFecha);
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
    
    /**
     * Metodo de ayuda para crear paneles de producto a la hora de quere agregar un 
     * producto en pantalla
     * @return JPanel del producto buscado
     */
    private JPanel crearPanelAgregarProducto() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(colorPaneles);
        panel.setMaximumSize(new Dimension(800, 70));
        panel.setPreferredSize(new Dimension(800, 70));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

        JPanel placeholderGrid = new JPanel();
        placeholderGrid.setBackground(Color.LIGHT_GRAY);
        placeholderGrid.setPreferredSize(new Dimension(80, 50));
        placeholderGrid.setMaximumSize(new Dimension(80, 50));
        placeholderGrid.add(new JLabel("+")); 

        JLabel lblAdd = new JLabel("Agregar producto");
        lblAdd.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblAdd.setForeground(Color.WHITE);

        panel.add(placeholderGrid);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(lblAdd);
        panel.add(Box.createHorizontalGlue());

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Simula backend para comprobar que funciona la pantalla, modificar posteriormente
                String[] productosBack = {"Conchas", "Pan de Muerto", "Baguette"};
                String elegido = (String) JOptionPane.showInputDialog(PanelAgendarPedido.this, 
                        "Seleccione un producto:", "", 
                        JOptionPane.QUESTION_MESSAGE, null, productosBack, productosBack[0]);
                
                if (elegido != null) {
                    // VERIFICACIÓN DE DUPLICADOS
                    if (productosSeleccionados.contains(elegido)) {
                        JOptionPane.showMessageDialog(panel, 
                            "El producto '" + elegido + "' ya está en el pedido.\nModifica la cantidad directamente en la lista.", 
                            "Producto duplicado", JOptionPane.WARNING_MESSAGE);
                    } else {
                        productosSeleccionados.add(elegido);
                        agregarFilaProducto(elegido);
                    }
                }
            }
        });

        return panel;
    }
    
    /**
     * Metodo de ayuda para agregar fila nueva del producto que se busca 
     * @param nombreProducto 
     */
    private void agregarFilaProducto(String nombreProducto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(colorPaneles);
        panel.setMaximumSize(new Dimension(800, 70));
        panel.setPreferredSize(new Dimension(800, 70));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        //Aqui va la imagen en caso de tener, no sé si tengamos tiempo de agregar imagenes pero el soporte está
        JPanel imgPlaceholder = new JPanel();
        imgPlaceholder.setBackground(Color.LIGHT_GRAY);
        imgPlaceholder.setPreferredSize(new Dimension(80, 50));
        imgPlaceholder.setMaximumSize(new Dimension(80, 50));

        JLabel lblNombre = new JLabel(nombreProducto);
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblNombre.setForeground(Color.WHITE);

        final String[] nota = {""}; 
        final int[] cantidad = {1};

        JButton btnLapiz = new JButton("✎");
        btnLapiz.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnLapiz.setFocusPainted(false);
        btnLapiz.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Nota para " + nombreProducto + ":", nota[0]);
            if (input != null) {
                nota[0] = input; // AQUI SE GUARDA LA NOTA EN STRING 
                System.out.println("Nota guardada: " + nota[0]); //borrar despues de depurar
            }
        });


        JLabel lblCant = new JLabel("  " + cantidad[0] + "  ");
        lblCant.setForeground(Color.WHITE);
        lblCant.setFont(new Font("SansSerif", Font.BOLD, 18));

        JButton btnMenos = new JButton("-");
        btnMenos.addActionListener(e -> {
            if (cantidad[0] > 1) {
                cantidad[0]--;
                lblCant.setText("  " + cantidad[0] + "  ");
            } else {
                panelListaProductos.remove(panel);
                
                productosSeleccionados.remove(nombreProducto);
                
                panelListaProductos.revalidate();
                panelListaProductos.repaint();
            }
        });

        JButton btnMas = new JButton("+");
        btnMas.addActionListener(e -> {
            cantidad[0]++;
            lblCant.setText("  " + cantidad[0] + "  ");
        });

        panel.add(imgPlaceholder);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(lblNombre);
        panel.add(Box.createHorizontalGlue());
        panel.add(btnLapiz);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(btnMenos);
        panel.add(lblCant);
        panel.add(btnMas);

        int index = panelListaProductos.getComponentCount() - 1; 
        panelListaProductos.add(panel, index);
        panelListaProductos.add(Box.createRigidArea(new Dimension(0, 10)), index + 1); // Gap entre productos

        panelListaProductos.revalidate();
        panelListaProductos.repaint();
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
