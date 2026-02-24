/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.awt.Color;
import java.awt.Cursor;
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
    private final Color COLOR_PRIMARIO = new Color(11, 37, 69);
    private final Color COLOR_FONDO = new Color(238, 244, 237);
    private FramePrincipal principal;

    /**
     * Creates new form Gestion_preparacion_de_pedidos
     */
    public Gestion_preparacion_de_pedidos(FramePrincipal principal) {
        this.principal = principal;
        initComponents();
        Dimension size = new Dimension(1024, 768);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setSize(size);
        configurarDiseñoManual();
        estilizarBuscador();
        cargarTabla();

    }

    private void estilizarBoton(javax.swing.JButton boton, Dimension dim) {
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setForeground(COLOR_FONDO);
        boton.setBackground(COLOR_PRIMARIO);
        boton.setMaximumSize(dim);
        boton.setPreferredSize(dim);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        boton.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void configurarDiseñoManual() {
        // 1. Configuración del Panel Principal (mismo que PanelAdminUsuario)
        this.setBackground(COLOR_FONDO);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(1024, 768));

        // 2. Panel de Título (Header)
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(COLOR_PRIMARIO);
        Dimension dimensionTitulo = new Dimension(1024, 170);
        panelTitulo.setPreferredSize(dimensionTitulo);
        panelTitulo.setMaximumSize(dimensionTitulo);
        panelTitulo.setMinimumSize(dimensionTitulo);
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));

        JLabel lblHeader = new JLabel("Gestión de Pedidos");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 50));
        lblHeader.setForeground(COLOR_FONDO);
        lblHeader.setAlignmentX(CENTER_ALIGNMENT);

        panelTitulo.add(Box.createVerticalGlue());
        panelTitulo.add(lblHeader);
        panelTitulo.add(Box.createVerticalGlue());

        // 3. Panel de Contenido (Buscador y Tabla)
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(COLOR_FONDO);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Estilizar Buscador
        txtbuscar.setMaximumSize(new Dimension(200, 40));
        txtbuscar.setFont(new Font("Arial", Font.PLAIN, 18));

        // Estilizar Botones
        estilizarBoton(btnBuscar, new Dimension(250, 40));
        estilizarBoton(btncambiarestado, new Dimension(300, 50));
        estilizarBoton(btnregresar, new Dimension(150, 40));

        // Organización de componentes en el panel de contenido
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setOpaque(false);
        panelBusqueda.add(txtbuscar);
        panelBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
        panelBusqueda.add(btnBuscar);

        panelContenido.add(panelBusqueda);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));
        panelContenido.add(jScrollPane1); // La tabla
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de Acciones (Botones inferiores)
        JPanel panelAcciones = new JPanel();
        panelAcciones.setOpaque(false);
        panelAcciones.add(btnregresar);
        panelAcciones.add(Box.createRigidArea(new Dimension(50, 0)));
        panelAcciones.add(btncambiarestado);

        panelContenido.add(panelAcciones);

        // Limpiar y re-agregar al panel principal
        this.removeAll();
        this.add(panelTitulo);
        this.add(panelContenido);
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

    private void estilizarBuscador() {
        // Definimos el tamaño ancho de 450px y alto de 450px
        Dimension tamanoTxt = new Dimension(450, 45);

        // Forzamos el tamaño en todas las propiedades para evitar que el Layout lo ignore
        txtbuscar.setPreferredSize(tamanoTxt);
        txtbuscar.setMaximumSize(tamanoTxt);
        txtbuscar.setMinimumSize(tamanoTxt);

        // Estilo visual: Borde azul oscuro y fuente más grande
        txtbuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(11, 37, 69), 2), // Azul institucional
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtbuscar.setFont(new Font("Arial", Font.PLAIN, 18)); // Texto más legible

        // Ajustamos el botón Buscar para que sea proporcional
        Dimension tamanoBtn = new Dimension(120, 45);
        btnBuscar.setPreferredSize(tamanoBtn);
        btnBuscar.setMaximumSize(tamanoBtn);
        btnBuscar.setBackground(new Color(11, 37, 69));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void actualizarTabla(List<PedidoEntregaDTO> lista) {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpia la tabla actual

        for (PedidoEntregaDTO p : lista) {
            Object[] fila = {
                p.getIdPedido(),
                p.getCliente(),
                p.getTipoPedido(),
                p.getEstado()
            };
            modelo.addRow(fila);
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

        btnregresar = new javax.swing.JButton();
        btncambiarestado = new javax.swing.JButton();
        lbltitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtbuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        btnregresar.setText("Regresar");
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });

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
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

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
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnregresar)
                    .addComponent(btncambiarestado))
                .addContainerGap(106, Short.MAX_VALUE))
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

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
        // TODO add your handling code here:
        btnregresar.addActionListener(e -> {
            // Al presionar regresar, volvemos a la interfaz anterior
            principal.mostrarPanel(new Interfaz_admin1(principal));
        });
    }//GEN-LAST:event_btnregresarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String filtro = txtbuscar.getText().trim();

        try {
            List<PedidoEntregaDTO> resultados;

            // Si el buscador está vacío, cargamos todos los pedidos en preparación
            if (filtro.isEmpty()) {
                resultados = FabricaBOs.obtenerPedidoBO().listarPedidosPreparacion();
            } else {
                // Llamamos al nuevo método del BO (asegúrate de agregarlo al BO también)
                resultados = FabricaBOs.obtenerPedidoBO().buscarPedidosAvanzado(filtro);
            }

            actualizarTabla(resultados);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btncambiarestado;
    private javax.swing.JButton btnregresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
