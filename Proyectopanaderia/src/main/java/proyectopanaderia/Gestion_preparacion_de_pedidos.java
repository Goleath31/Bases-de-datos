/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.fabrica.FabricaBOs;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import negocio.excepciones.NegocioException;

/**
 *
 * @author golea
 */
public class Gestion_preparacion_de_pedidos extends javax.swing.JPanel {

    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");
    
    /**
     * Creates new form Gestion_preparacion_de_pedidos
     */
    public Gestion_preparacion_de_pedidos() {
        initComponents();
        Dimension size = new Dimension(1024, 768);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setSize(size);
        disenoManual();             
        cargarTabla();

    }
    
    private void disenoManual() {
        // Configuración del Panel Principal
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);

        // --- HEADER ---
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
        panelHeader.setBackground(colorHeader);
        panelHeader.setMaximumSize(new Dimension(1024, 120));
        panelHeader.setPreferredSize(new Dimension(1024, 120));
        panelHeader.setBorder(new EmptyBorder(0, 50, 0, 0));

        JLabel lblTituloHeader = new JLabel("Gestión de Pedidos");
        lblTituloHeader.setFont(new Font("SansSerif", Font.PLAIN, 36));
        lblTituloHeader.setForeground(Color.WHITE);
        panelHeader.add(lblTituloHeader);

        // --- CONTENIDO PRINCIPAL ---
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(new EmptyBorder(30, 80, 30, 80));

        // Título de la tabla
        lbltitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lbltitulo.setForeground(colorHeader);
        lbltitulo.setAlignmentX(LEFT_ALIGNMENT);

        // Estilizar la Tabla y su ScrollPane
        jScrollPane1.setBorder(null);
        jTable1.setRowHeight(30);
        jTable1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        jTable1.getTableHeader().setBackground(colorPaneles);
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.setSelectionBackground(colorPaneles);

        // Separador
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(1200, 1));
        sep.setForeground(colorPaneles);

        // --- BOTONES INFERIORES ---
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setOpaque(false);

        // Estilo botón Regresar
        btnregresar.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnregresar.addActionListener(e -> {
             // Ajusta esto según tu panel de inicio de empleado/admin
             // principal.mostrarPanel(new PanelIndexAdmin(principal)); 
        });

        // Estilo botón Cambiar Estado
        btncambiarestado.setFont(new Font("SansSerif", Font.BOLD, 18));
        btncambiarestado.setBackground(colorConfirmar);
        btncambiarestado.setForeground(Color.WHITE);
        btncambiarestado.setFocusPainted(false);
        btncambiarestado.setBorder(new EmptyBorder(10, 20, 10, 20));

        bottomPanel.add(btnregresar);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(btncambiarestado);

        // Ensamblar Main Content
        mainContent.add(lbltitulo);
        mainContent.add(Box.createRigidArea(new Dimension(0, 15)));
        mainContent.add(jScrollPane1);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(sep);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(bottomPanel);

        // Añadir al panel general
        this.removeAll(); // Limpiamos el layout de NetBeans
        this.add(panelHeader);
        this.add(mainContent);
        this.revalidate();
        this.repaint();
    }
    

    private void cargarTabla() {
        try {
            // 1. Obtener los datos desde el BO
            List<PedidoEntregaDTO> pedidos = FabricaBOs.obtenerPedidoBO().listarPedidosPreparacion();

            // 2. Obtener el modelo de la tabla
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0); // Limpiar tabla antes de cargar

            // 3. Llenar filas
            for (PedidoEntregaDTO p : pedidos) {
                Object[] fila = {
                    p.getIdPedido(),
                    p.getCliente(), // Aquí va el Nombre/Folio según el SQL
                    p.getTipoPedido(),
                    p.getEstado()
                };
                modelo.addRow(fila);
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error de carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void darDiseno() {
        // Colores basados en PanelAgendarPedido
        Color colorHeader = Color.decode("#13315C");
        Color colorFondo = Color.decode("#EEF4ED");
        Color colorConfirmar = Color.decode("#134074");

        // Fondo del panel principal
        this.setBackground(colorFondo);

        // Diseño de la Tabla
        jTable1.setRowHeight(30);
        jTable1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        jTable1.getTableHeader().setBackground(colorHeader);
        jTable1.getTableHeader().setForeground(Color.WHITE);
        jTable1.setSelectionBackground(Color.decode("#8DA9C4"));
        jTable1.setSelectionForeground(Color.BLACK);
        jTable1.setShowGrid(false);
        jTable1.setIntercellSpacing(new Dimension(0, 0));

        // Estilo del Título
        lbltitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lbltitulo.setForeground(colorHeader);

        // Estilo del Botón "Cambiar Estado" (Acción principal)
        btncambiarestado.setBackground(colorConfirmar);
        btncambiarestado.setForeground(Color.WHITE);
        btncambiarestado.setFont(new Font("SansSerif", Font.BOLD, 16));
        btncambiarestado.setFocusPainted(false);
        btncambiarestado.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Estilo del Botón "Regresar" (Acción secundaria)
        btnregresar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnregresar.setBackground(Color.WHITE);
        btnregresar.setFocusPainted(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnregresar = new javax.swing.JButton();
        btncambiarestado = new javax.swing.JButton();
        lbltitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        btnregresar.setText("Regresar");

        btncambiarestado.setText("Cambiar al siguiente estado");
        btncambiarestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncambiarestadoActionPerformed(evt);
            }
        });

        lbltitulo.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbltitulo.setText("Tabla de pedidos:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre/Folio", "Tipo de pedio", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(310, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnregresar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncambiarestado, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbltitulo)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnregresar)
                    .addComponent(btncambiarestado))
                .addContainerGap(108, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncambiarestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiarestadoActionPerformed
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido.");
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
        String estadoActual = jTable1.getValueAt(fila, 3).toString();

        try {
            FabricaBOs.obtenerPedidoBO().avanzarEstado(id, estadoActual);
            JOptionPane.showMessageDialog(this, "Estado actualizado correctamente.");
            cargarTabla(); // Refresca la tabla con los nuevos estados
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btncambiarestadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncambiarestado;
    private javax.swing.JButton btnregresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbltitulo;
    // End of variables declaration//GEN-END:variables
}
