/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import negocio.BOs.IPedidoBO;
import negocio.fabrica.FabricaBOs;
import negocio.DTOs.PedidoEntregaDTO;
import javax.swing.JOptionPane;
import negocio.excepciones.NegocioException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import negocio.BOs.IPedidoBO;
import negocio.fabrica.FabricaBOs;
import negocio.DTOs.PedidoEntregaDTO;
import javax.swing.JOptionPane;
import negocio.excepciones.NegocioException;

/**
 * Panel de interfaz gráfica para el proceso de entrega de pedidos y cobro.
 * Permite filtrar pedidos listos, validar PIN de seguridad y completar la
 * transacción.
 *
 * * @author golea
 */
public class Entrega_y_Cobro1 extends javax.swing.JPanel {

    /**
     * Interfaz de lógica de negocio para gestionar pedidos
     */
    private final IPedidoBO pedidoBO;
    /**
     * Contador cíclico para alternar entre diferentes tipos de ordenamiento en
     * la tabla
     */
    private int contadorFiltros = 0;
    /**
     * Definición de colores institucionales para la coherencia visual
     */
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");
    /**
     * Referencia al frame principal para navegación entre pantallas
     */
    private FramePrincipal principal;

    /**
     * Constructor del panel Entrega y Cobro.
     *
     * @param principal Instancia del marco contenedor para gestionar cambios de
     * vista.
     */
    public Entrega_y_Cobro1(FramePrincipal principal) {
        this.principal = principal;
        // Se obtiene la implementación del BO desde la fábrica
        initComponents();
        // Inicializamos el BO usando la fábrica que ya tienes
        this.pedidoBO = FabricaBOs.obtenerPedidoBO();
        disenoManual();
        cargarPedidos(""); // Carga inicial sin filtro

    }

    private void disenoManual() {
        // Configuración General
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);
        this.setPreferredSize(new Dimension(1024, 768));

        // --- HEADER ---
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
        panelHeader.setBackground(colorHeader);
        panelHeader.setMaximumSize(new Dimension(1024, 120));
        panelHeader.setPreferredSize(new Dimension(1024, 120));
        panelHeader.setBorder(new EmptyBorder(0, 50, 0, 0));

        JLabel lblTituloHeader = new JLabel("Entrega y Cobro de Pedidos");
        lblTituloHeader.setFont(new Font("SansSerif", Font.PLAIN, 36));
        lblTituloHeader.setForeground(Color.WHITE);
        panelHeader.add(lblTituloHeader);

        // --- CONTENIDO ---
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(new EmptyBorder(20, 50, 20, 50));

        // Barra de Búsqueda
        JPanel searchBar = new JPanel();
        searchBar.setLayout(new BoxLayout(searchBar, BoxLayout.X_AXIS));
        searchBar.setOpaque(false);

        lblNombre.setText("Buscar Cliente: ");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 16));
        txtnombre.setMaximumSize(new Dimension(400, 40));

        btnbuscar.setBackground(colorConfirmar);
        btnbuscar.setForeground(Color.WHITE);

        btnfiltrar.setBackground(colorPaneles);
        btnfiltrar.setForeground(Color.WHITE);

        searchBar.add(lblNombre);
        searchBar.add(txtnombre);
        searchBar.add(Box.createRigidArea(new Dimension(10, 0)));
        searchBar.add(btnbuscar);
        searchBar.add(Box.createRigidArea(new Dimension(10, 0)));
        searchBar.add(btnfiltrar);

        // Estilo de Tabla
        jScrollPane1.setBorder(null);
        jTable1.setRowHeight(35);
        jTable1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        jTable1.getTableHeader().setBackground(colorPaneles);
        jTable1.getTableHeader().setForeground(Color.WHITE);

        // Botones Inferiores
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setOpaque(false);

        btncompletarpedido.setBackground(colorConfirmar);
        btncompletarpedido.setForeground(Color.WHITE);
        btncompletarpedido.setFont(new Font("SansSerif", Font.BOLD, 18));
        btncompletarpedido.setPreferredSize(new Dimension(250, 50));
        btncompletarpedido.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(btnregresar);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(btncompletarpedido);

        // Montaje
        mainContent.add(searchBar);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(jScrollPane1);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(new JSeparator());
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(bottomPanel);

        this.removeAll();
        this.add(panelHeader);
        this.add(mainContent);
        this.revalidate();
        this.repaint();
    }

    private void cambiarPantalla(javax.swing.JPanel nuevoPanel) {
        // 1. Obtener el contenedor principal (JFrame o el que tenga el scroll/content pane)
        java.awt.Container parent = this.getParent();

        if (parent != null) {
            parent.remove(this); // Quita la pantalla actual (Entrega y Cobro)
            parent.add(nuevoPanel); // Agrega la nueva (Cobro o PIN)

            // 2. Forzar al Swing a dibujar de nuevo y recalcular tamaños
            parent.revalidate();
            parent.repaint();
        }
    }

    /**
     * Consulta al BO los pedidos que están marcados como "Listos" para ser
     * entregados.
     */
    private void cargarPedidos(String filtro) {
        try {
            List<PedidoEntregaDTO> pedidos = pedidoBO.buscarPedidosPendientesEntrega(filtro);
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);

            for (PedidoEntregaDTO pedido : pedidos) {
                Object[] fila = {
                    pedido.getIdPedido(),
                    pedido.getCliente(),
                    pedido.getTipoPedido(),
                    pedido.getMontoTotal(), // Enviar como Float/Number
                    pedido.getEstado()
                };
                modelo.addRow(fila);
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * Mapea la lista de DTOs al modelo de la tabla (jTable1).
     *
     * @param lista Lista de pedidos a renderizar.
     */
    private void actualizarTablaConLista(List<PedidoEntregaDTO> lista) {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpiar

        for (PedidoEntregaDTO p : lista) {
            Object[] fila = {
                p.getIdPedido(),
                p.getCliente(),
                p.getTipoPedido(),
                p.getMontoTotal(),
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

        lblNombre = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnregresar = new javax.swing.JButton();
        btncompletarpedido = new javax.swing.JButton();
        lbltitulo = new javax.swing.JLabel();
        btnfiltrar = new javax.swing.JButton();

        lblNombre.setBackground(new java.awt.Color(141, 169, 196));
        lblNombre.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        lblNombre.setText("Folio o numero de telefono:");

        txtnombre.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        btnbuscar.setText("buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Tipo Pedido", "Total a Pagar", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnregresar.setText("Regresar");
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });

        btncompletarpedido.setText("Completar pedido");
        btncompletarpedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncompletarpedidoActionPerformed(evt);
            }
        });

        lbltitulo.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbltitulo.setText("Entrega y Cobro");

        btnfiltrar.setText("filtrar");
        btnfiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfiltrarActionPerformed(evt);
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
                        .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnregresar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btncompletarpedido)
                        .addGap(118, 118, 118))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscar)
                        .addGap(18, 18, 18)
                        .addComponent(btnfiltrar)
                        .addGap(0, 26, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnbuscar)
                                .addComponent(btnfiltrar)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncompletarpedido)
                            .addComponent(btnregresar))
                        .addGap(17, 17, 17))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        String filtro = txtnombre.getText().trim();

        // 2. Llamar al método que carga los datos (el que creamos anteriormente)
        cargarPedidos(filtro);


    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
        // TODO add your handling code here:
        principal.mostrarPanel(new Interfaz_admin1(principal));


    }//GEN-LAST:event_btnregresarActionPerformed

    /**
     * Procesa la entrega del pedido seleccionado. Si es Express, solicita y
     * valida el PIN. Finalmente, registra el método de pago.
     */
    private void btncompletarpedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncompletarpedidoActionPerformed
        int fila = jTable1.getSelectedRow();

        // 1. Validar que haya una fila seleccionada
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido de la tabla.");
            return;
        }

        // 2. Extraer los datos necesarios (Asegúrate de que los índices de columna coincidan con tu tabla)
        // Asumiendo: Col 0 = ID, Col 2 = Tipo, Col 4 = Estado
        int idPedido = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
        String tipo = jTable1.getValueAt(fila, 2).toString();
        String estado = jTable1.getValueAt(fila, 4).toString();

        // 3. REGLA DE NEGOCIO: Solo pedidos en estado 'Listo'
        if (!estado.equalsIgnoreCase("Listo")) {
            JOptionPane.showMessageDialog(this,
                    "No se puede cobrar un pedido en estado: " + estado + ".\nSolo pedidos 'Listo' pueden ser procesados.",
                    "Acción no permitida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. Si pasó la validación, procedemos al cambio de pantalla
        if (tipo.equalsIgnoreCase("Express")) {
            cambiarPantalla(new ValidacionPin(idPedido, principal));
        } else {
            cambiarPantalla(new PantallaCobro(idPedido, principal));
        }
    }//GEN-LAST:event_btncompletarpedidoActionPerformed

    /**
     * Implementa un ciclo de ordenamiento (Sort) sobre la lista de pedidos
     * actual. Clic 1: Por Nombre | Clic 2: Express primero | Clic 3: Programado
     * primero | Clic 4: ID.
     */
    private void btnfiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfiltrarActionPerformed
        // TODO add your handling code here:
        try {
            // 1. Obtenemos la lista actual de la base de datos (con el filtro de texto que haya)
            String textoFiltro = txtnombre.getText().trim();
            List<PedidoEntregaDTO> lista = pedidoBO.buscarPedidosPendientesEntrega(textoFiltro);

            // 2. Incrementamos el contador (del 1 al 4)
            contadorFiltros++;
            if (contadorFiltros > 4) {
                contadorFiltros = 1; // Reiniciar ciclo
            }
            // 3. Aplicamos el ordenamiento según el número de clic
            switch (contadorFiltros) {
                case 1 -> { // Clic 1: Estado "Listo" ascendente
                    lista.sort((p1, p2) -> p1.getEstado().compareTo(p2.getEstado()));
                    JOptionPane.showMessageDialog(this, "Ordenando por: Estado (Listo primero)");
                }
                case 2 -> { // Clic 2: Tipo "Express" ascendente
                    lista.sort((p1, p2) -> p1.getTipoPedido().compareTo(p2.getTipoPedido()));
                    JOptionPane.showMessageDialog(this, "Ordenando por: Pedidos Express");
                }
                case 3 -> { // Clic 3: Tipo "Programado" ascendente
                    // Invertimos el orden para que Programado salga primero si Express era el menor
                    lista.sort((p1, p2) -> p2.getTipoPedido().compareTo(p1.getTipoPedido()));
                    JOptionPane.showMessageDialog(this, "Ordenando por: Pedidos Programados");
                }
                case 4 -> { // Clic 4: ID numérico
                    lista.sort((p1, p2) -> Integer.compare(p1.getIdPedido(), p2.getIdPedido()));
                    JOptionPane.showMessageDialog(this, "Ordenando por: ID (Número)");
                }
            }

            // 4. Actualizamos la tabla con la lista ya ordenada
            actualizarTablaConLista(lista);

        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al filtrar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnfiltrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncompletarpedido;
    private javax.swing.JButton btnfiltrar;
    private javax.swing.JButton btnregresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
