/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectopanaderia;

import javax.swing.JOptionPane;
import negocio.BOs.IPedidoBO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import negocio.BOs.IPedidoBO;
import negocio.excepciones.NegocioException;
import negocio.fabrica.FabricaBOs;

/**
 * Panel de interfaz gráfica para la validación de seguridad de pedidos express.
 * Solicita un PIN al usuario y lo contrasta con la base de datos antes de
 * permitir el cobro.
 *
 * * @author golea
 */
public class ValidacionPin extends javax.swing.JPanel {

    /**
     * ID del pedido que se está intentando validar
     */
    private int idPedido;
    /**
     * Referencia a la lógica de negocio de pedidos
     */
    private final IPedidoBO pedidoBO;
    // Colores corporativos
    private Color colorHeader = Color.decode("#13315C");
    private Color colorFondo = Color.decode("#EEF4ED");
    private Color colorPaneles = Color.decode("#8DA9C4");
    private Color colorConfirmar = Color.decode("#134074");
    /**
     * Referencia al frame principal para el intercambio de paneles
     */
    private FramePrincipal principal;

    /**
     * Constructor que prepara la validación para un pedido específico.
     *
     * @param idPedido Identificador único del pedido express.
     * @param principal Marco contenedor de la aplicación.
     */
    public ValidacionPin(int idPedido, FramePrincipal principal) {
        this.idPedido = idPedido;
        this.principal = principal;
        this.pedidoBO = FabricaBOs.obtenerPedidoBO();
        initComponents();
        disenoManual();
        this.Iblidmostrar.setText(String.valueOf(idPedido));

    }

    private void disenoManual() {
        // Configuración Principal
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

        JLabel lblTituloHeader = new JLabel("Seguridad - Pedido Express");
        lblTituloHeader.setFont(new Font("SansSerif", Font.PLAIN, 36));
        lblTituloHeader.setForeground(Color.WHITE);
        panelHeader.add(lblTituloHeader);

        // --- CONTENIDO CENTRAL (Cuadro de validación) ---
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBorder(new EmptyBorder(100, 300, 100, 300)); // Muy centrado

        // ID del pedido pequeño arriba
        lblNombre1.setText("Validando Pedido ID:");
        lblNombre1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblNombre1.setForeground(colorPaneles);
        lblNombre1.setAlignmentX(CENTER_ALIGNMENT);

        Iblidmostrar.setFont(new Font("SansSerif", Font.BOLD, 20));
        Iblidmostrar.setForeground(colorHeader);
        Iblidmostrar.setAlignmentX(CENTER_ALIGNMENT);

        // Instrucción principal
        lblNombre.setText("Introduce el PIN de validación:");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblNombre.setForeground(colorHeader);
        lblNombre.setAlignmentX(CENTER_ALIGNMENT);

        // Campo de texto (PIN)
        txtnombre.setFont(new Font("SansSerif", Font.PLAIN, 30));
        txtnombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombre.setMaximumSize(new Dimension(250, 60));
        txtnombre.setBackground(Color.WHITE);

        // Botón Validar
        btnvalidarpin.setBackground(colorConfirmar);
        btnvalidarpin.setForeground(Color.WHITE);
        btnvalidarpin.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnvalidarpin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnvalidarpin.setMaximumSize(new Dimension(250, 60));
        btnvalidarpin.setAlignmentX(CENTER_ALIGNMENT);

        // Botón Regresar
        btnregresar1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnregresar1.setAlignmentX(CENTER_ALIGNMENT);

        // Ensamblar
        mainContent.add(lblNombre1);
        mainContent.add(Iblidmostrar);
        mainContent.add(Box.createRigidArea(new Dimension(0, 40)));
        mainContent.add(lblNombre);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(txtnombre);
        mainContent.add(Box.createRigidArea(new Dimension(0, 40)));
        mainContent.add(btnvalidarpin);
        mainContent.add(Box.createRigidArea(new Dimension(0, 20)));
        mainContent.add(btnregresar1);

        // Actualizar Panel
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbltitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        btnvalidarpin = new javax.swing.JButton();
        btnregresar1 = new javax.swing.JButton();
        lblNombre1 = new javax.swing.JLabel();
        Iblidmostrar = new javax.swing.JLabel();

        lbltitulo.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbltitulo.setText("Validar pin");

        lblNombre.setBackground(new java.awt.Color(141, 169, 196));
        lblNombre.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        lblNombre.setText("Validar Pin:");

        txtnombre.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N

        btnvalidarpin.setText("Validar Pin");
        btnvalidarpin.setActionCommand("Regresar");
        btnvalidarpin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvalidarpinActionPerformed(evt);
            }
        });

        btnregresar1.setText("Regresar");
        btnregresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresar1ActionPerformed(evt);
            }
        });

        lblNombre1.setBackground(new java.awt.Color(141, 169, 196));
        lblNombre1.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        lblNombre1.setText("ID:");

        Iblidmostrar.setBackground(new java.awt.Color(141, 169, 196));
        Iblidmostrar.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        Iblidmostrar.setText("-----");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnvalidarpin))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Iblidmostrar)
                                .addGap(115, 115, 115)))))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(btnregresar1)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1)
                    .addComponent(Iblidmostrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addGap(18, 18, 18)
                .addComponent(btnvalidarpin)
                .addGap(65, 65, 65))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(212, Short.MAX_VALUE)
                    .addComponent(btnregresar1)
                    .addGap(64, 64, 64)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento ejecutado al presionar el botón "Validar PIN". Envía el texto
     * ingresado a la capa de negocio para su verificación.
     */
    private void btnvalidarpinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvalidarpinActionPerformed
        // TODO add your handling code here:
        // 1. Obtener el PIN del campo de texto (txtnombre según tu archivo .form)
        String pinIngresado = txtnombre.getText().trim();

        // 2. Validación básica de campo vacío
        if (pinIngresado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el PIN de seguridad.");
            return;
        }

        try {
            // 3. Llamar a la capa de negocio para validar
            // Este método en PedidoBO lanza una NegocioException si el PIN es incorrecto
            pedidoBO.validarPinExpress(idPedido, pinIngresado);

            // 4. Si no lanzó excepción, el PIN es correcto: Abrir Pantalla de Cobro
            JOptionPane.showMessageDialog(this, "PIN Correcto. Procediendo al cobro.");
            cambiarPantalla(new PantallaCobro(idPedido, principal));

        } catch (NegocioException ex) {
            // 5. Si el PIN es incorrecto o hay error de BD, mostramos el mensaje de error
            JOptionPane.showMessageDialog(this, "El PIN es incorrecto", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            txtnombre.setText(""); // Limpiamos el campo para reintentar
            txtnombre.requestFocus();
        }


    }//GEN-LAST:event_btnvalidarpinActionPerformed

    /**
     * Cancela la operación actual y regresa a la pantalla de listado de entregas.
     */
    private void btnregresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresar1ActionPerformed
        // TODO add your handling code here:
        principal.mostrarPanel(new Entrega_y_Cobro1(principal));


    }//GEN-LAST:event_btnregresar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Iblidmostrar;
    private javax.swing.JButton btnregresar1;
    private javax.swing.JButton btnvalidarpin;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
