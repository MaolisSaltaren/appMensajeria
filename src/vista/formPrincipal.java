 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.UsuariosController;
import controlador.ClientesController;
import javax.swing.JOptionPane;
import modelo.SesionModel;
import modelo.UsuariosCRUD;
import modelo.UsuariosModel;

import modelo.ClientesCRUD;
import modelo.ClientesModel;

/**
 *
 * @author JUAN_PC
 */
public class formPrincipal extends javax.swing.JFrame {

    
    public static UsuariosController frmUsuarios =null;
    public static ClientesController frmClientes =null;
    
     SesionModel modSesion;
    /**
     * Creates new form formPrincipal
     */
    public formPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblNombreSede = new javax.swing.JLabel();
        lblIdSede = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        lblNombre_usuario = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblIdUsuario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnGestionUsu = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnPaquetes = new javax.swing.JButton();
        btnAjustes = new javax.swing.JButton();
        btnServicios = new javax.swing.JButton();
        btnEntregas = new javax.swing.JButton();
        btnDespachos = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("SOFTWARE SGM");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/user-32.png"))); // NOI18N

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SEDE NOMBRE");

        lblNombreSede.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreSede.setText("XXX");

        lblIdSede.setForeground(new java.awt.Color(255, 255, 255));
        lblIdSede.setText("XXX");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SEDE  ID ");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CIUDAD");

        lblCiudad.setForeground(new java.awt.Color(255, 255, 255));
        lblCiudad.setText("XXX");

        lblRol.setForeground(new java.awt.Color(255, 255, 255));
        lblRol.setText("XXX");

        lblNombre_usuario.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lblNombre_usuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre_usuario.setText("XXX");
        lblNombre_usuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ROL:");

        lblIdUsuario.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        lblIdUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblIdUsuario.setText("XXX");

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID USUARIO");

        btnCerrarSesion.setBackground(new java.awt.Color(102, 102, 102));
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("cerrar sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(btnCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(lblNombre_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(43, 43, 43)
                                .addComponent(lblIdSede, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNombreSede, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(lblNombre_usuario)
                .addGap(18, 18, 18)
                .addComponent(btnCerrarSesion)
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblIdUsuario))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRol)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblCiudad))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdSede)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreSede)
                    .addComponent(jLabel7))
                .addContainerGap(259, Short.MAX_VALUE))
        );

        btnGestionUsu.setBackground(new java.awt.Color(102, 102, 102));
        btnGestionUsu.setForeground(new java.awt.Color(255, 255, 255));
        btnGestionUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/gestionDeUsuarios.png"))); // NOI18N
        btnGestionUsu.setText("Gestion de usuarios");
        btnGestionUsu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGestionUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionUsuActionPerformed(evt);
            }
        });

        btnClientes.setBackground(new java.awt.Color(102, 102, 102));
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/user-32.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnPaquetes.setBackground(new java.awt.Color(102, 102, 102));
        btnPaquetes.setForeground(new java.awt.Color(255, 255, 255));
        btnPaquetes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Product-32.png"))); // NOI18N
        btnPaquetes.setText("paquetes");
        btnPaquetes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPaquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaquetesActionPerformed(evt);
            }
        });

        btnAjustes.setBackground(new java.awt.Color(102, 102, 102));
        btnAjustes.setForeground(new java.awt.Color(255, 255, 255));
        btnAjustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cambiar.png"))); // NOI18N
        btnAjustes.setText("ajustes");
        btnAjustes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjustesActionPerformed(evt);
            }
        });

        btnServicios.setBackground(new java.awt.Color(102, 102, 102));
        btnServicios.setForeground(new java.awt.Color(255, 255, 255));
        btnServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Order-32.png"))); // NOI18N
        btnServicios.setText("servicios");
        btnServicios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServiciosActionPerformed(evt);
            }
        });

        btnEntregas.setBackground(new java.awt.Color(102, 102, 102));
        btnEntregas.setForeground(new java.awt.Color(255, 255, 255));
        btnEntregas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/entregas.png"))); // NOI18N
        btnEntregas.setText("Entregas");
        btnEntregas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntregas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregasActionPerformed(evt);
            }
        });

        btnDespachos.setBackground(new java.awt.Color(102, 102, 102));
        btnDespachos.setForeground(new java.awt.Color(255, 255, 255));
        btnDespachos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/despachos.png"))); // NOI18N
        btnDespachos.setText("Despachos");
        btnDespachos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDespachos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDespachosActionPerformed(evt);
            }
        });

        btnReportes.setBackground(new java.awt.Color(102, 102, 102));
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/REPORTES.png"))); // NOI18N
        btnReportes.setText("Reportes");
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGestionUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnDespachos, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(390, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDespachos, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 170, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(jLabel1)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
       formLogin.frmPrincipal = null;
    }//GEN-LAST:event_formWindowClosing

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        formLogin.frmPrincipal=null;
        
        formLogin frm = new formLogin();
        frm.setVisible(true);
        
        dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnGestionUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionUsuActionPerformed
        if(frmUsuarios == null)
        {
            //objetos de las clases correspondientes
            UsuariosModel mod= new UsuariosModel();
            UsuariosCRUD crud = new UsuariosCRUD();
            formUsuarios frm = new formUsuarios();

            //llama al formulario correspondiente
             frmUsuarios= new UsuariosController(modSesion,mod,frm,crud);
            frmUsuarios.iniciar();
            frm.setVisible(true);
        }
    }//GEN-LAST:event_btnGestionUsuActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
       if(frmClientes == null)
        {
            //objetos de las clases correspondientes
            ClientesModel mod= new ClientesModel();
            ClientesCRUD crud = new ClientesCRUD();
            formClientes frm = new formClientes();

            //llama al formulario correspondiente
             frmClientes= new ClientesController(mod,frm,crud);
            frmClientes.iniciar();
            frm.setVisible(true);
        }
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaquetesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPaquetesActionPerformed

    private void btnAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjustesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjustesActionPerformed

    private void btnServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiciosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnServiciosActionPerformed

    private void btnEntregasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEntregasActionPerformed

    private void btnDespachosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDespachosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDespachosActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formPrincipal().setVisible(true);
            }
        });
    }
    
    public formPrincipal(SesionModel sesion){
           initComponents();
        this.modSesion = sesion; 
        //pone el formulario centrado 
        setLocationRelativeTo(null);
        
  
     
        lblIdUsuario.setText(String.valueOf(modSesion.getId_trabajador()).toUpperCase());
        lblNombre_usuario.setText(modSesion.getNombre_trabajador());
        lblRol.setText(modSesion.getRol_nombre());
        lblCiudad.setText(modSesion.getCiudad_sede());
        lblIdSede.setText(String.valueOf(modSesion.getId_sede()));
        lblNombreSede.setText(modSesion.getSede_nombre());
      //agarra el rol de la sesion 
        String tipoUsuario= modSesion.getRol_nombre();
       
    if ( tipoUsuario.equals("Admin"))
        {
               btnGestionUsu.setVisible(true);
            btnClientes.setVisible(true);
            btnPaquetes.setVisible(true);
            btnAjustes.setVisible(true);
            btnServicios.setVisible(true);
            btnEntregas.setVisible(true);
            btnDespachos.setVisible(true);
            btnReportes.setVisible(true);
            
        
        }
     else if  ( tipoUsuario.equals("Cajero"))
        {
                    
            btnGestionUsu.setVisible(false);
            btnClientes.setVisible(true);
            btnPaquetes.setVisible(true);
            btnAjustes.setVisible(false);
            btnServicios.setVisible(true);
            btnEntregas.setVisible(false);
            btnDespachos.setVisible(false);
            btnReportes.setVisible(true);
            
            
        }
     else if (tipoUsuario.equals("Mensajero"))
     {
            btnGestionUsu.setVisible(false);
            btnClientes.setVisible(false);
            btnPaquetes.setVisible(false);
            btnAjustes.setVisible(false);
            btnServicios.setVisible(false);
            btnEntregas.setVisible(true);
            btnDespachos.setVisible(false);
            btnReportes.setVisible(false);
         
     }
     else if (tipoUsuario.equals("Bodeguero"))
     {
            btnGestionUsu.setVisible(false);
            btnClientes.setVisible(false);
            btnPaquetes.setVisible(false);
            btnAjustes.setVisible(false);
            btnServicios.setVisible(false);
            btnEntregas.setVisible(true);
            btnDespachos.setVisible(true);
            btnReportes.setVisible(false);
         
     }
     else if (tipoUsuario.equals("Conductor"))
     {
            btnGestionUsu.setVisible(false);
            btnClientes.setVisible(false);
            btnPaquetes.setVisible(false);
            btnAjustes.setVisible(false);
            btnServicios.setVisible(false);
            btnEntregas.setVisible(true);
            btnDespachos.setVisible(true);
            btnReportes.setVisible(false);
         
     }
         
     
   


           
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjustes;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnDespachos;
    private javax.swing.JButton btnEntregas;
    private javax.swing.JButton btnGestionUsu;
    private javax.swing.JButton btnPaquetes;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnServicios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblIdSede;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblNombreSede;
    private javax.swing.JLabel lblNombre_usuario;
    private javax.swing.JLabel lblRol;
    // End of variables declaration//GEN-END:variables
}
