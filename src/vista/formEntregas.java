/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import misClases.Validacion;
import modelo.ServiciosCRUD;
import modelo.ServiciosModel;

/**
 *
 * @author JUAN_PC
 */
public class formEntregas extends javax.swing.JDialog {

      
    /** formBuscarClientes frmBusqueda = new formBuscarClientes();
      
     * Creates new form formBuscarClientes
     */
    public formEntregas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         
         this.setLocationRelativeTo(null);
             mostrarServiciosNoEntregados("", "Entregado",jtableUsuarios1);
             mostrarServiciosNoEntregados("", "Despachado",jtableUsuarios2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopopMenu1 = new javax.swing.JPopupMenu();
        popoDetallle1 = new javax.swing.JMenuItem();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableServi = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtableUsuarios1 = new javax.swing.JTable();
        btnBuscarserv = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar3 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtableUsuarios2 = new javax.swing.JTable();
        btnBuscarserv1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        popoDetallle1.setText("ENTREGAR");
        popoDetallle1.setName("hola"); // NOI18N
        popoDetallle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popoDetallle1ActionPerformed(evt);
            }
        });
        jPopopMenu1.add(popoDetallle1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("ENTREGA DE PAQUETES");
        jLabel3.setAlignmentX(0.5F);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel6.setText("ID CLIENTE :");

        jtableServi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtableServi.setComponentPopupMenu(jPopopMenu1);
        jtableServi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtableServi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableServiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtableServi);

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 102));
        jLabel20.setText("Clic derecho robre el elemento para mas detalle");

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("NUEVA ENTREGA");

        jLabel7.setText("lISTADO DE TODAS LAS ENTREGAS EFECTUADAS ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel20))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)))))
                        .addGap(0, 426, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(323, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(303, 303, 303)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(485, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addGap(207, 207, 207)))
        );

        jTabbedPane1.addTab("REGISTRAR ENTREGA", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel8.setText("ID CLIENTE :");

        txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscar2KeyReleased(evt);
            }
        });

        jtableUsuarios1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtableUsuarios1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtableUsuarios1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableUsuarios1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtableUsuarios1);

        btnBuscarserv.setText("BUSCAR");
        btnBuscarserv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarservActionPerformed(evt);
            }
        });

        jLabel1.setText("lISTADO DE TODAS LAS ENTREGAS EFECTUADAS ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarserv)))
                        .addGap(0, 432, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarserv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ENTREGAS EFECTUADAS", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel9.setText("ID CLIENTE :");

        txtBuscar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscar3KeyReleased(evt);
            }
        });

        jtableUsuarios2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtableUsuarios2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtableUsuarios2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableUsuarios2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtableUsuarios2);

        btnBuscarserv1.setText("BUSCAR");
        btnBuscarserv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarserv1ActionPerformed(evt);
            }
        });

        jLabel2.setText("lISTADO DE TODAS LAS ENTREGAS QUE SE ENCUENTRAN PENDIENTES POR ENTREGAR");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarserv1)))
                        .addGap(0, 399, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarserv1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ENTREGAS PENDIENTES", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(324, 324, 324))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtableServiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableServiMouseClicked
      
    }//GEN-LAST:event_jtableServiMouseClicked

    private void jtableUsuarios1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableUsuarios1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtableUsuarios1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarServiciosNoEntregados(txtIdCliente.getText(),"Despachado",jtableServi);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void popoDetallle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popoDetallle1ActionPerformed
       ServiciosCRUD actualizar = new ServiciosCRUD();
       ServiciosModel modelo = new ServiciosModel();
       
        //aggarra la fecha y hora del sistema para saber la ultima sesion del usuario
       Date fecha = new Date();
       DateFormat fechaHoraSistema = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             //2.2 se llena en el modelo la  hora actual del sistema como ultima sesion activa
      
     
       
       //obtiene la fila de la jtable
        int fila = jtableServi.getSelectedRow();
        //guarda el id de la fila seleccionada
        int id= Integer.parseInt((jtableServi.getValueAt(fila, 0).toString().trim()));
        
        //llena el modelo 
       modelo.setServi_id(id);
       modelo.setServi_estado("Entregado");
       modelo.setServi_fecha_entrega(fechaHoraSistema.format(fecha).toString()); 
       
       //mensaje de advertencia de entrega del servicio
       int respuesta= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea Entregar este servicio?", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
     
            if(respuesta==0)
            {
                
                //agrega al modelo los parametros necesarios para la consulta
                if( actualizar.actualizarEstado(modelo))
                JOptionPane.showMessageDialog(null, "Servicio entregado con exito ", " ", JOptionPane.INFORMATION_MESSAGE);
                mostrarServiciosNoEntregados(txtIdCliente.getText(),"Despachado",jtableServi);
                mostrarServiciosNoEntregados(txtBuscar2.getText(), "Entregado",jtableUsuarios1); 
                mostrarServiciosNoEntregados(txtBuscar3.getText(), "Despachado",jtableUsuarios2);
            }
       
      
    }//GEN-LAST:event_popoDetallle1ActionPerformed

    private void txtBuscar2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyReleased
      Validacion validar= new Validacion ();
      validar.isNumeric(evt);
    }//GEN-LAST:event_txtBuscar2KeyReleased

    private void btnBuscarservActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarservActionPerformed
        mostrarServiciosNoEntregados(txtBuscar2.getText(), "Entregado",jtableUsuarios1);
    }//GEN-LAST:event_btnBuscarservActionPerformed

    private void txtBuscar3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar3KeyReleased

    private void jtableUsuarios2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableUsuarios2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtableUsuarios2MouseClicked

    private void btnBuscarserv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarserv1ActionPerformed
     mostrarServiciosNoEntregados(txtBuscar3.getText(), "Despachado",jtableUsuarios2);
    }//GEN-LAST:event_btnBuscarserv1ActionPerformed

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
            java.util.logging.Logger.getLogger(formEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formEntregas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formEntregas dialog = new formEntregas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    public  void limpiar()
    {
        
    }
    
    //    ===============================================================================================
    //SERVICIOS NO ENTREGADOS
    public void mostrarServiciosNoEntregados( String valor,String estado_sevicio,JTable tabla) 
   {
             
        DefaultTableModel modelo = new  DefaultTableModel();

        
     // instancia de la clase serviioscud
     ServiciosCRUD servi= new ServiciosCRUD();
     ResultSet rs=servi.getServcioNoEntregado(valor,estado_sevicio);
   
      
        modelo.setColumnIdentifiers(new Object[]{
            "ID SERVICIO","CLIENTE","ID CLIENTE","PAQUETE ", " CIUDAD ORIGEN"," CIUDAD DESTINO", "FECHA ENTREGA"});
 
       if(rs!=null)
        {
            try 
            {
              
            while(rs.next())
            {
                      modelo.addRow(new Object[]{
                        rs.getString("SERVI_ID"),
                        rs.getString("clie_nombre"),               
                        rs.getString("clie_ID"),   
                        rs.getString("PAQUE_NOMBRE"),
                        rs.getString("CIUD_NOMBRE"),
                        rs.getString("SERV_CIUDAD_DESTINO"),
                        rs.getString("SERVI_FECHA_ENTREGA"),
                                   
                      
                });
            }
            tabla.setModel(modelo);
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarserv;
    private javax.swing.JButton btnBuscarserv1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JPopupMenu jPopopMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jtableServi;
    public javax.swing.JTable jtableUsuarios1;
    public javax.swing.JTable jtableUsuarios2;
    public javax.swing.JMenuItem popoDetallle1;
    public javax.swing.JTextField txtBuscar2;
    public javax.swing.JTextField txtBuscar3;
    public javax.swing.JTextField txtIdCliente;
    // End of variables declaration//GEN-END:variables
}
