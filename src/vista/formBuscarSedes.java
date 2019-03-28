/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.UsuariosController;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.SedesCRUD;
import modelo.SedesModel;
import modelo.SesionModel;
import modelo.UsuariosCRUD;
import modelo.UsuariosModel;

/**
 *
 * @author JUAN_PC
 */
public class formBuscarSedes extends javax.swing.JDialog {

    SedesModel modelSed;
    formUsuarios frmUsu;
    
     
    /** formBuscarClientes frmBusqueda = new formBuscarClientes();
      
     * Creates new form formBuscarClientes
     */
    public formBuscarSedes(java.awt.Frame parent, boolean modal){
       
        super(parent, modal);
        initComponents();
     
    
        this.setLocationRelativeTo(null);
        //muestra todas las sedes
        mostrarSedesByid("");
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableSedes = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jConboBuscar = new javax.swing.JComboBox<>();
        btnEscatblecerIdSede = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel6.setText("Busqueda de usuarios por nombre");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jtableSedes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtableSedes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtableSedes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableSedesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtableSedes);

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 102));
        jLabel20.setText("Clic derecho robre el elemento para mas detalle");

        jConboBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar por ID", "Buscar por Nombre" }));
        jConboBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnEscatblecerIdSede.setText("establecer id");
        btnEscatblecerIdSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscatblecerIdSedeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jConboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEscatblecerIdSede))
                            .addComponent(jLabel6))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jConboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEscatblecerIdSede))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("BUSQUEDA DE SEDES ");
        jLabel3.setAlignmentX(0.5F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(171, 171, 171))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtableSedesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableSedesMouseClicked
                formUsuarios frmUsuario=new formUsuarios();
                
        //creamos un objeto del controlador 
        UsuariosController crtUsu = new UsuariosController(null,null,frmUsuario,null);
        
    //obtiene la fila de la jtable
        int fila = jtableSedes.getSelectedRow();
        //guarda el id de la fila seleccionada
        int id;
        id= Integer.parseInt(jtableSedes.getValueAt(fila, 0).toString().trim());
       crtUsu.modelsedes.setSede_id(id);
       crtUsu.modelsedes.setSede_nombre(String.valueOf( jtableSedes.getValueAt(fila, 1).toString().trim()));
       this.dispose();
       
    }//GEN-LAST:event_jtableSedesMouseClicked

    private void btnEscatblecerIdSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscatblecerIdSedeActionPerformed

    }//GEN-LAST:event_btnEscatblecerIdSedeActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
         if(jConboBuscar.getSelectedItem()=="Buscar por ID")
       
        mostrarSedesByid(txtBuscar.getText());
          
         else
         mostrarSedesByNombre(txtBuscar.getText());
             
         
       
    }//GEN-LAST:event_txtBuscarKeyReleased

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
            java.util.logging.Logger.getLogger(formBuscarSedes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formBuscarSedes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formBuscarSedes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formBuscarSedes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formBuscarSedes dialog = new formBuscarSedes(new javax.swing.JFrame(), true);
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
    
    //permite mostrar los rasultados de la consulta de sedes por id

       public void mostrarSedesByid(String valor) 
   {
       
        DefaultTableModel modelo = new  DefaultTableModel();
        
        //crea una instancia de la clase UsuariosCrud
        SedesCRUD sesCR= new SedesCRUD();
        ResultSet rs =sesCR.buscarSedesById(valor);
        modelo.setColumnIdentifiers(new Object[]{
            "ID SEDE","NOMBRE ", " DIRECCION","CIUDAD"});
   
       if(rs!=null)
        {
            try 
            {
            while(rs.next())
            {
                   modelo.addRow(new Object[]{
                        rs.getString("SEDES_ID"),
                        rs.getString("SEDES_NOMBRE"),
                        rs.getString("SEDES_DIRECCION"),
                        rs.getString("CIUD_NOMBRE")
                        });
            }
         jtableSedes.setModel(modelo);
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
          //permite mostrar los rasultados de la consulta de sedes por nombre
       public void mostrarSedesByNombre( String valor) 
   {
       //validamos de quelas celdas no sea editables
        DefaultTableModel modelo = new  DefaultTableModel();
        
        //crea una instancia de la clase UsuariosCrud
        SedesCRUD sesCR= new SedesCRUD();
        ResultSet rs =sesCR.buscarSedesById(valor);
        modelo.setColumnIdentifiers(new Object[]{
            "ID SEDE","NOMBRE ", " DIRECCION","CIUDAD"});
   
       if(rs!=null)
        {
            try 
            {
            while(rs.next())
            {
                   modelo.addRow(new Object[]{
                        rs.getString("SEDES_ID"),
                        rs.getString("SEDES_NOMBRE"),
                        rs.getString("SEDES_DIRECCION"),
                        rs.getString("CIUD_NOMBRE")
                        });
            }
         jtableSedes.setModel(modelo);
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
      

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEscatblecerIdSede;
    private javax.swing.JComboBox<String> jConboBuscar;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jtableSedes;
    public javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
