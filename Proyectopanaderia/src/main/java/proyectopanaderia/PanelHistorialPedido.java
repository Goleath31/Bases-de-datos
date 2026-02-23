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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author joser
 */
public class PanelHistorialPedido extends javax.swing.JPanel {
    
    FramePrincipal principal;

    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");

    private JPanel panelContenedorLista;
    private List<JButton> botonesEncabezado;
    
    
    //LISTA SOLO DEBUG
    private List<Pedido> listaPedidos;
    
    /**
     * Creates new form PanelHistorialPedido
     */
    public PanelHistorialPedido(FramePrincipal principal) {
        this.botonesEncabezado = new ArrayList<>();
        this.principal = principal;
        
        cargarDatosSimulados();
        initComponents();
        iniciarComponentes();
        
        ordenarYRenderizarLista("No. Pedido");
    }
    
    /**
     * Metodo iniciar componentes
     */
    public void iniciarComponentes(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);
        this.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel lblMiga = new JLabel("Historial pedidos");
        lblMiga.setForeground(Color.GRAY);
        lblMiga.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMiga.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel panelMiga = new JPanel();
        panelMiga.setLayout(new BoxLayout(panelMiga, BoxLayout.X_AXIS));
        panelMiga.setOpaque(false);
        panelMiga.add(lblMiga);
        panelMiga.add(Box.createHorizontalGlue()); // Empuja el texto a la izquierda
        panelMiga.setMaximumSize(new Dimension(800, 20));

        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BoxLayout(panelCabecera, BoxLayout.X_AXIS));
        panelCabecera.setBackground(colorHeader);
        panelCabecera.setMaximumSize(new Dimension(800, 60));
        panelCabecera.setBorder(new EmptyBorder(15, 30, 15, 30));

        JLabel lblTitulo = new JLabel("Historial de mis pedidos");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 22));
        panelCabecera.add(lblTitulo);
        panelCabecera.add(Box.createHorizontalGlue());

        //encabezados
        JPanel panelEncabezados = new JPanel();
        panelEncabezados.setLayout(new BoxLayout(panelEncabezados, BoxLayout.X_AXIS));
        panelEncabezados.setOpaque(false);
        panelEncabezados.setMaximumSize(new Dimension(800, 40));
        panelEncabezados.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorPaneles)); // Línea inferior

        JButton btnPedido = crearBotonOrden("No. Pedido");
        JButton btnFecha = crearBotonOrden("Fecha");
        JButton btnEstado = crearBotonOrden("Estado");
        JButton btnPrecio = crearBotonOrden("Precio");

        panelEncabezados.add(Box.createHorizontalGlue());
        panelEncabezados.add(btnPedido);
        panelEncabezados.add(crearSeparadorEncabezado());
        panelEncabezados.add(btnFecha);
        panelEncabezados.add(crearSeparadorEncabezado());
        panelEncabezados.add(btnEstado);
        panelEncabezados.add(crearSeparadorEncabezado());
        panelEncabezados.add(btnPrecio);
        panelEncabezados.add(Box.createHorizontalGlue());

        //contenedor de lista
        panelContenedorLista = new JPanel();
        panelContenedorLista.setLayout(new BoxLayout(panelContenedorLista, BoxLayout.Y_AXIS));
        panelContenedorLista.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(panelContenedorLista);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setMaximumSize(new Dimension(800, 400));
        
        JPanel panelLineaInferior = new JPanel();
        panelLineaInferior.setMaximumSize(new Dimension(800, 2));
        panelLineaInferior.setBackground(colorPaneles);

        JPanel panelPie = new JPanel();
        panelPie.setLayout(new BoxLayout(panelPie, BoxLayout.X_AXIS));
        panelPie.setOpaque(false);
        panelPie.setMaximumSize(new Dimension(800, 50));

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(colorConfirmar);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(e -> {
            principal.mostrarPanel(new PanelIndexCliente(principal));
        }); 

        panelPie.add(Box.createHorizontalGlue());
        panelPie.add(btnRegresar);
        panelPie.add(Box.createHorizontalGlue());

        this.add(panelMiga);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(panelCabecera);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(panelEncabezados);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(scrollPane);
        this.add(panelLineaInferior);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(panelPie);
    }

    /**
     * Metodo auxiliar crear botones
     * @param texto
     * @return 
     */
    private JButton crearBotonOrden(String texto) {
        JButton boton = new JButton(texto);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        
        botonesEncabezado.add(boton);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarYRenderizarLista(texto);
            }
        });
        return boton;
    }
    
    /**
     * Metodo auxiliar renderizar según ordenamiento pedido
     * @param criterio 
     */
    private void ordenarYRenderizarLista(String criterio) {
        for (JButton btn : botonesEncabezado) {
            if (btn.getText().equals(criterio)) {
                btn.setForeground(colorHeader);
                btn.setFont(new Font("Arial", Font.BOLD, 16));
            } else {
                btn.setForeground(Color.BLACK); 
                btn.setFont(new Font("Arial", Font.PLAIN, 16));
            }
        }

        //Ordena lista de pedidos según lo que pidamos
        Collections.sort(listaPedidos, (p1, p2) -> {
            switch (criterio) {
                case "Fecha": return p1.fecha.compareTo(p2.fecha);
                case "Estado": return p1.estado.compareTo(p2.estado);
                case "Precio": return Integer.compare(p1.precio, p2.precio);
                default: return p1.noPedido.compareTo(p2.noPedido);
            }
        });

        panelContenedorLista.removeAll();
        for (Pedido p : listaPedidos) {
            panelContenedorLista.add(crearFilaPedido(p.noPedido, p.fecha, p.estado, "$" + p.precio));
            panelContenedorLista.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre filas
        }

        panelContenedorLista.revalidate();
        panelContenedorLista.repaint();
    }
    
    /**
    * Metodo ordenar fila por fila
    * @param no
    * @param fecha
    * @param estado
    * @param precio
    * @return 
    */ 
    private JPanel crearFilaPedido(String no, String fecha, String estado, String precio) {
        JPanel fila = new JPanel(new GridLayout(1, 7)); 
        fila.setBackground(colorPaneles);
        fila.setMaximumSize(new Dimension(800, 40));
        fila.setPreferredSize(new Dimension(800, 40));

        Font fontFila = new Font("Arial", Font.PLAIN, 14);
        Color colorTexto = Color.WHITE;

        fila.add(crearLabelCentrado(no, fontFila, colorTexto));
        fila.add(crearLabelCentrado("|", fontFila, colorTexto));
        fila.add(crearLabelCentrado(fecha, fontFila, colorTexto));
        fila.add(crearLabelCentrado("|", fontFila, colorTexto));
        fila.add(crearLabelCentrado(estado, fontFila, colorTexto));
        fila.add(crearLabelCentrado("|", fontFila, colorTexto));
        fila.add(crearLabelCentrado(precio, fontFila, colorTexto));

        return fila;
    }
    
    /**
     * Metodo auxiliar crear label
     * @param texto
     * @param fuente
     * @param color
     * @return 
     */
    private JLabel crearLabelCentrado(String texto, Font fuente, Color color) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(fuente);
        label.setForeground(color);
        return label;
    }
    
    /**
     * Metodo auxiliar crear separador
     * @return 
     */
    private JLabel crearSeparadorEncabezado() {
        JLabel sep = new JLabel("   |   ");
        sep.setFont(new Font("Arial", Font.PLAIN, 16));
        return sep;
    }
    
    //CARGAR DATOS SOLO DEBUG 
    private void cargarDatosSimulados() {
        listaPedidos = new ArrayList<>();
        listaPedidos.add(new Pedido("4038", "13 / 02 / 26", "Pendiente", 320));
        listaPedidos.add(new Pedido("4741", "12 / 01 / 26", "Entregado", 420));
        listaPedidos.add(new Pedido("3975", "02 / 02 / 26", "Cancelado", 67));
    }

    //CLASE SOLO DE DEBUG
    private class Pedido {
        String noPedido, fecha, estado;
        int precio;

        public Pedido(String noPedido, String fecha, String estado, int precio) {
            this.noPedido = noPedido;
            this.fecha = fecha;
            this.estado = estado;
            this.precio = precio;
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
