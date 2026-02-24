/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import negocio.BOs.IPedidoBO;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;
import persistencia.dominio.Cupon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import negocio.BOs.IPedidoBO;
import negocio.DTOs.PedidoEntregaDTO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;

/**
 * Panel de interfaz gráfica para procesar el cobro de un pedido específico.
 * Gestiona la aplicación de cupones de descuento y la confirmación del pago.
 *
 * * @author golea
 */
public class PantallaCobro extends javax.swing.JPanel {

    /**
     * Identificador del pedido que se va a cobrar
     */
    private int idPedido;
    /**
     * Interfaz de lógica de negocio para pedidos y cupones
     */
    private final IPedidoBO pedidoBO;
    /**
     * Almacena el tipo de pedido (Express o Programado)
     */
    private String tipoPedido;
    /**
     * Monto base del pedido antes de aplicar descuentos
     */
    private float montoTotal = 0;
    /**
     * Colores institucionales para el diseño
     */
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");
    /**
     * Referencia para la navegación entre paneles
     */
    private FramePrincipal principal;

    /**
     * Constructor que inicializa la pantalla de cobro para un pedido
     * particular.
     *
     * @param principal Frame contenedor.
     * @param idPedido ID del pedido a cobrar.
     * @param tipo Tipo de pedido.
     * @param monto Monto total inicial.
     */
    public PantallaCobro(int idPedido, FramePrincipal principal) {
        this.idPedido = idPedido;
        this.principal = principal;
        this.tipoPedido = tipoPedido;
        this.pedidoBO = FabricaBOs.obtenerPedidoBO();
        initComponents();
        obtenerMontoDePedido();
        disenoManual();

    }

    private void disenoManual() {
        // Configuración del Panel Principal (1024x768)
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorFondo);
        Dimension size = new Dimension(1024, 768);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);

        // --- HEADER ---
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
        panelHeader.setBackground(colorHeader);
        panelHeader.setMaximumSize(new Dimension(1024, 120));
        panelHeader.setPreferredSize(new Dimension(1024, 120));
        panelHeader.setBorder(new EmptyBorder(0, 50, 0, 0));

        JLabel lblTituloHeader = new JLabel("Proceso de Pago");
        lblTituloHeader.setFont(new Font("SansSerif", Font.PLAIN, 36));
        lblTituloHeader.setForeground(Color.WHITE);
        panelHeader.add(lblTituloHeader);

        // --- CONTENIDO CENTRAL ---
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(new EmptyBorder(40, 150, 40, 150));

        // Estilizar Labels de información
        estilizarLabelInfo(lblmontoapagar1, 24, true);
        estilizarLabelInfo(lblmostrarmontoapagar1, 48, true); // El monto resalta más
        lblmostrarmontoapagar1.setForeground(colorConfirmar);

        estilizarLabelInfo(lbltextodescuento, 18, false);
        estilizarLabelInfo(lbldescuento, 20, true);

        estilizarLabelInfo(lblIntroduceelmonto, 18, false);

        // Campo de Texto y ComboBox
        txtMontoaintroducir.setFont(new Font("SansSerif", Font.PLAIN, 24));
        txtMontoaintroducir.setMaximumSize(new Dimension(400, 50));
        Descuentoscuponera.setMaximumSize(new Dimension(400, 40));

        // Botones
        btnpagar.setBackground(colorConfirmar);
        btnpagar.setForeground(Color.WHITE);
        btnpagar.setFont(new Font("SansSerif", Font.BOLD, 22));
        btnpagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnpagar.setMaximumSize(new Dimension(300, 60));
        btnpagar.setAlignmentX(CENTER_ALIGNMENT);

        btnregresar1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnregresar1.setAlignmentX(CENTER_ALIGNMENT);

        // Ensamblar
        mainContent.add(lblmontoapagar1);
        mainContent.add(lblmostrarmontoapagar1);
        mainContent.add(Box.createRigidArea(new Dimension(0, 30)));
        mainContent.add(new JSeparator());
        mainContent.add(Box.createRigidArea(new Dimension(0, 30)));

        mainContent.add(lbltextodescuento);
        mainContent.add(Descuentoscuponera);
        mainContent.add(lbldescuento);

        mainContent.add(Box.createRigidArea(new Dimension(0, 40)));
        mainContent.add(lblIntroduceelmonto);
        mainContent.add(txtMontoaintroducir);

        mainContent.add(Box.createRigidArea(new Dimension(0, 50)));
        mainContent.add(btnpagar);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(btnregresar1);

        // Reconstrucción de la vista
        this.removeAll();
        this.add(panelHeader);
        this.add(mainContent);
        this.revalidate();
        this.repaint();
    }

    private void estilizarLabelInfo(JLabel lbl, int size, boolean bold) {
        lbl.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, size));
        lbl.setForeground(colorHeader);
        lbl.setAlignmentX(CENTER_ALIGNMENT);
    }

    // Los métodos obtenerMontoDePedido(), cargarCupones() 
    // y los ActionPerformed se mantienen igual.
    private void obtenerMontoDePedido() {
        try {
            // Buscamos el pedido específico para traer su monto_total
            List<PedidoEntregaDTO> lista = pedidoBO.buscarPedidosPendientesEntrega(String.valueOf(idPedido));

            for (PedidoEntregaDTO p : lista) {
                if (p.getIdPedido() == idPedido) {
                    this.montoTotal = p.getMontoTotal();
                    // Actualizamos el label (que por defecto decía 00)
                    lbldescuento.setText(String.format("%.2f", montoTotal));
                    break;
                }
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el monto: " + ex.getMessage());
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

        lbldescuento = new javax.swing.JLabel();
        lbltitulo = new javax.swing.JLabel();
        txtMontoaintroducir = new javax.swing.JTextField();
        lblIntroduceelmonto = new javax.swing.JLabel();
        lblmontoapagar1 = new javax.swing.JLabel();
        btnpagar = new javax.swing.JButton();
        btnregresar1 = new javax.swing.JButton();
        lbltextodescuento = new javax.swing.JLabel();
        lblmostrarmontoapagar1 = new javax.swing.JLabel();
        Descuentoscuponera = new javax.swing.JComboBox();

        lbldescuento.setBackground(new java.awt.Color(141, 169, 196));
        lbldescuento.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        lbldescuento.setText("00");

        lbltitulo.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbltitulo.setText("Pago");

        txtMontoaintroducir.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        txtMontoaintroducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoaintroducirActionPerformed(evt);
            }
        });

        lblIntroduceelmonto.setBackground(new java.awt.Color(141, 169, 196));
        lblIntroduceelmonto.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        lblIntroduceelmonto.setText("Introduce el monto:");

        lblmontoapagar1.setBackground(new java.awt.Color(141, 169, 196));
        lblmontoapagar1.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        lblmontoapagar1.setText("Monto a pagar:");

        btnpagar.setText("Pagar");
        btnpagar.setActionCommand("Regresar");
        btnpagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpagarActionPerformed(evt);
            }
        });

        btnregresar1.setText("regresar");
        btnregresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresar1ActionPerformed(evt);
            }
        });

        lbltextodescuento.setBackground(new java.awt.Color(141, 169, 196));
        lbltextodescuento.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        lbltextodescuento.setText("Descuento:");

        lblmostrarmontoapagar1.setBackground(new java.awt.Color(141, 169, 196));
        lblmostrarmontoapagar1.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        lblmostrarmontoapagar1.setText("00");

        Descuentoscuponera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Descuentos" }));
        Descuentoscuponera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescuentoscuponeraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblmontoapagar1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblmostrarmontoapagar1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbltextodescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblIntroduceelmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMontoaintroducir, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbldescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(btnregresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(btnpagar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Descuentoscuponera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmontoapagar1)
                    .addComponent(lblmostrarmontoapagar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltextodescuento)
                    .addComponent(lbldescuento))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIntroduceelmonto)
                    .addComponent(txtMontoaintroducir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Descuentoscuponera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnregresar1)
                    .addComponent(btnpagar))
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoaintroducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoaintroducirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoaintroducirActionPerformed

    private void btnpagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpagarActionPerformed
        // TODO add your handling code here:
        try {
            float pagoRecibido = Float.parseFloat(txtMontoaintroducir.getText());

            if (pagoRecibido < montoTotal) {
                JOptionPane.showMessageDialog(this, "Dinero insuficiente. El total es: $" + montoTotal);
                return;
            }

            // Procesar la entrega en la base de datos
            pedidoBO.procesarEntrega(idPedido, "Efectivo");

            float cambio = pagoRecibido - montoTotal;
            JOptionPane.showMessageDialog(this, "Pago exitoso. Su cambio es: $" + String.format("%.2f", cambio));

            // Regresar a la pantalla anterior
            principal.mostrarPanel(new Entrega_y_Cobro1(principal));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad numérica válida.");
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnpagarActionPerformed

    private void btnregresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresar1ActionPerformed
        // TODO add your handling code here:
        principal.mostrarPanel(new Entrega_y_Cobro1(principal));

    }//GEN-LAST:event_btnregresar1ActionPerformed

    /**
     * Evento que se dispara al seleccionar una opción en el ComboBox de
     * cupones. Calcula el nuevo total basándose en el porcentaje de descuento
     * del cupón seleccionado.
     */
    private void DescuentoscuponeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescuentoscuponeraActionPerformed
        // TODO add your handling code here:
        Object seleccionado = Descuentoscuponera.getSelectedItem();
        float nuevoTotal = montoTotal; // montoTotal es el precio original sin descuento

        if (seleccionado instanceof Cupon) {
            Cupon cupon = (Cupon) seleccionado;
            float porcentaje = cupon.getDescuento();
            float cantidadADescontar = montoTotal * (porcentaje / 100);
            nuevoTotal = montoTotal - cantidadADescontar;

            // Mostramos cuánto se descontó en la etiqueta de descuento
            lbldescuento.setText(String.format("-$%.2f", cantidadADescontar));
        } else {
            lbldescuento.setText("$0.00");
        }
        lblmostrarmontoapagar1.setText(String.format("%.2f", nuevoTotal));
    }//GEN-LAST:event_DescuentoscuponeraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Descuentoscuponera;
    private javax.swing.JButton btnpagar;
    private javax.swing.JButton btnregresar1;
    private javax.swing.JLabel lblIntroduceelmonto;
    private javax.swing.JLabel lbldescuento;
    private javax.swing.JLabel lblmontoapagar1;
    private javax.swing.JLabel lblmostrarmontoapagar1;
    private javax.swing.JLabel lbltextodescuento;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtMontoaintroducir;
    // End of variables declaration//GEN-END:variables
}
