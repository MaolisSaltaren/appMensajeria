
package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import misClases.EncriptarPass;
import misClases.Validacion;
import modelo.UsuariosCRUD;
import modelo.UsuariosModel;
import vista.formUsuarios;


public class UsuariosController implements ActionListener,KeyListener,MouseListener{
    
    //llamando a las clases creadas
     private final UsuariosModel modelUsu;
     private final formUsuarios frmUsuario;
     private final UsuariosCRUD usuCRUD;
     
     //objeto de la clase validacion
     Validacion validarCaja= new Validacion();
   
    
    //creando el constructor de la clase 
    public  UsuariosController( UsuariosModel modelo ,formUsuarios frmUsuario,UsuariosCRUD crud)
    {
        this.modelUsu= modelo;
        this.usuCRUD=crud;
        this.frmUsuario=frmUsuario;
        
     
   
        //declarar las acciones de los botones
        this.frmUsuario.btnGuardarUsu.addActionListener(this);
        this.frmUsuario.btnCancelarUsu.addActionListener(this);
        this.frmUsuario.btnEliminarUsu.addActionListener(this);
        this.frmUsuario.btnActualizarUsu.addActionListener(this);
        this.frmUsuario.btnBuscarUsu.addActionListener(this);
        
        //coloca los texbox a la escucha del evento de presionar un boton 
        this.frmUsuario.txtIdSede.addKeyListener(this);
        this.frmUsuario.txtNombreUsuario.addKeyListener(this);
       
        this.frmUsuario.txtDireccionUsuario.addKeyListener(this);
        this.frmUsuario.txtPass.addKeyListener(this);
        this.frmUsuario.txtRepPass.addKeyListener(this);
        
        this.frmUsuario.txtTelefonoUsuario.addKeyListener(this);
        this.frmUsuario.txtUsuario.addKeyListener(this);
        this.frmUsuario.txtBuscar.addKeyListener(this);
        
        //evento de escucha al popop menu
this.frmUsuario.popoDetallle.addActionListener(this);
        
        //coloca a el jtable a la escucha de los eventos del mouse
        this.frmUsuario.jtableUsuarios.addMouseListener(this);
    }
    
//    metodo que inicia la vista 
    public void iniciar() 
    {
        frmUsuario.setTitle("Usuarios");
        frmUsuario.setLocationRelativeTo(null);
        limpiarTxt();
     //   String ValorBuscado="";
       // mostrarUsuarios("");

      
        //carga los datos en el jtable
        
    }
    
    //pone los botones a  la escucha 

    @Override
    public void actionPerformed(ActionEvent e) {
    
    //1.SE HA PULSADO EL BOTON GUARDAR USUARIOS 
    if (e.getSource()== frmUsuario.btnGuardarUsu)
    {
        String pass = frmUsuario.txtPass.getText();
        String confirmaPass = frmUsuario.txtRepPass.getText();
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmUsuario.txtFecha_nacimiento.getDate()==null||frmUsuario.txtDireccionUsuario.getText().equals("") ||frmUsuario.txtIdSede.getText().equals("") ||
            frmUsuario.txtNombreUsuario.getText().equals("") ||frmUsuario.txtPass.getText().equals("") ||frmUsuario.txtRepPass.getText().equals("") ||
            frmUsuario.txtNombre_rol.getSelectedItem().equals("Seleccionar") ||frmUsuario.txtTelefonoUsuario.getText().equals("") ||frmUsuario.txtUsuario.getText().equals("") )
         {
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         }
         else
         {
               
            //2.comparamos que las contraseñas sean iguales
             if(pass.equals(confirmaPass))
                   {
                   //SE INCRIPTA LA CONTRASELÑA CON EL HASH SHA1    
                   EncriptarPass passhash= new EncriptarPass();
                   String passEncrip= passhash.sha1(pass);
                   
                   //optiene la fecha del jcalendar 
                     DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    String FechaNacimiento;
                    FechaNacimiento = formatoFecha.format(frmUsuario.txtFecha_nacimiento.getDate());
       
                   
           //      1.1 se llena el modelo  de los usuarios trabajadores 
                   modelUsu.setId(Integer.parseInt(frmUsuario.txtIdSede.getText()));
                   modelUsu.setNombre(frmUsuario.txtNombreUsuario.getText());
                   modelUsu.setTelefono(frmUsuario.txtTelefonoUsuario.getText());
                   modelUsu.setUsuario(frmUsuario.txtUsuario.getText());
                   modelUsu.setPassword(passEncrip);
                   modelUsu.setFecha_nacimiento(FechaNacimiento);
                   modelUsu.setDireccion_usuario(frmUsuario.txtDireccionUsuario.getText());
                   modelUsu.setId_sede(Integer.parseInt(frmUsuario.txtIdSede.getText()));
                   modelUsu.setNombre_rol(frmUsuario.txtNombre_rol.getSelectedItem().toString());
                   


                        //1.2.llama al metodo insertar de la claseUsuariosCrud
                        //----------
                        if(usuCRUD.insertarUsuario(modelUsu))
                        {limpiarTxt();
                        frmUsuario.btnEliminarUsu.setEnabled(false);
                        frmUsuario.btnGuardarUsu.setEnabled(true);
                        frmUsuario.btnBuscarUsu.setEnabled(true);
                        frmUsuario.btnActualizarUsu.setEnabled(false);
                            mostrarUsuarios("");
                        JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE );
                        //----------
                   }
                   else 
                   {
                       JOptionPane.showMessageDialog(null , "las contraseñas deben ser iguales","Advertencia",JOptionPane.WARNING_MESSAGE);
                   }

                    
         }
    
      
        
    }//fin de guardar usuarios
    
    //====================================================================================
    //2.se pulsa el boton de eliminar
    
     else if (e.getSource()==frmUsuario.btnEliminarUsu)     
    {
        // 2.0 verifica que el id del usuario no este vacio
        if(frmUsuario.txtIdSede.getText().equals(""))
            JOptionPane.showMessageDialog(null, " El id del usuario no puede estar vacio", "app ", JOptionPane.WARNING_MESSAGE);  
        else
        {
              int respuesta= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar este registro?", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
     
            if(respuesta==0)
            {
            //2.1 llena el modelo 
             modelUsu.setId(Integer.parseInt(frmUsuario.txtIdSede.getText()));

           //2.2.llama al metodo eliminar de la claseUsuariosCrud

            if(usuCRUD.eliminarUsuario(modelUsu)){        
               limpiarTxt();JOptionPane.showConfirmDialog(null,"El registro se elimino exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
            frmUsuario.btnEliminarUsu.setEnabled(false);
            frmUsuario.btnGuardarUsu.setEnabled(true);
            frmUsuario.btnBuscarUsu.setEnabled(true);
            frmUsuario.btnActualizarUsu.setEnabled(false);
                mostrarUsuarios("");
            }else
                JOptionPane.showMessageDialog(null,"Operacion no realizada","informe de error ",JOptionPane.ERROR_MESSAGE );
       
            
            }
        }
        
    }
     
     //=====================================================================================
     
     //3. SE HA PULSADO EL BOTON DE ACTUALIZAR
     else if (e.getSource()==frmUsuario.btnActualizarUsu)
     {
         
          String pass = frmUsuario.txtPass.getText();
          String confirmaPass = frmUsuario.txtRepPass.getText();
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmUsuario.txtFecha_nacimiento.getDate()==null||frmUsuario.txtDireccionUsuario.getText().equals("") ||frmUsuario.txtIdSede.getText().equals("") ||
            frmUsuario.txtNombreUsuario.getText().equals("") ||frmUsuario.txtPass.getText().equals("") ||frmUsuario.txtRepPass.getText().equals("") ||
            frmUsuario.txtNombre_rol.getSelectedItem().equals("Seleccionar") ||frmUsuario.txtTelefonoUsuario.getText().equals("") ||frmUsuario.txtUsuario.getText().equals("") )
         {
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         }
         else
         {
               
            //2.comparamos que las contraseñas sean iguales
             if(pass.equals(confirmaPass))
                   {
                        int respuesta= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea actualizar este registro?", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

                       if(respuesta==0)
                       {
                        //      3.1 se llena el modelo 
                       modelUsu.setId(Integer.parseInt(frmUsuario.txtIdSede.getText()));
                       modelUsu.setNombre(frmUsuario.txtNombreUsuario.getText());
                       modelUsu.setTelefono(frmUsuario.txtTelefonoUsuario.getText());
                    
                       modelUsu.setUsuario(frmUsuario.txtUsuario.getText());
                     //  modelUsu.setId_rol(Integer.parseInt(frmUsuario.txtRol.getText()));
                       modelUsu.setPassword(frmUsuario.txtPass.getText());

                       //3.2. llama al metodo insertar de la claseUsuariosCrud

                       if(usuCRUD.actualizarUsuario(modelUsu)){
                           limpiarTxt(); JOptionPane.showMessageDialog(null,"El registro se ha actualizado exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                        frmUsuario.btnEliminarUsu.setEnabled(false);
                        frmUsuario.btnGuardarUsu.setEnabled(true);
                        frmUsuario.btnBuscarUsu.setEnabled(true);
                        frmUsuario.btnActualizarUsu.setEnabled(false);
                           mostrarUsuarios("");
                       }   
                        else JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE);
                        }
                   }
             else
                 JOptionPane.showMessageDialog(null, "Las cajas de texto no pueden estar vacias ", " ", JOptionPane.WARNING_MESSAGE);
        }
   }//fin de actualizar
         
   //=============================================================================================
           // SE HA PULSADO EL BOTON DE BUSCAR
    else if ( e.getSource()==frmUsuario.btnBuscarUsu )
    {
        //validacion de que el id del usuario no este vacion 
        if (frmUsuario.txtIdSede.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa el id del usuario", " ", JOptionPane.WARNING_MESSAGE);
        }
        else{
           //1.se llena el modelo con el id ingresado en la caja de texto 
            modelUsu.setId(Integer.parseInt(frmUsuario.txtIdSede.getText()));

            //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(usuCRUD.buscarUsuario(modelUsu))  
            { 
                //lleva los datos a las cjas de texto
            frmUsuario.txtIdSede.setText(String.valueOf(modelUsu.id));
          //  frmUsuario.txtRol.setText(String.valueOf(modelUsu.id_rol));
            frmUsuario.txtNombreUsuario.setText(modelUsu.nombre);
            frmUsuario.txtUsuario.setText(String.valueOf(modelUsu.usuario));
            frmUsuario.txtPass.setText(String.valueOf(modelUsu.password));
           // frmUsuario.txtCorreoUsuario.setText(String.valueOf(modelUsu.correo));
            frmUsuario.txtTelefonoUsuario.setText(String.valueOf(modelUsu.telefono));
            frmUsuario.txtRepPass.setText(String.valueOf(modelUsu.password));
            
            frmUsuario.btnEliminarUsu.setEnabled(true);
            frmUsuario.btnGuardarUsu.setEnabled(false);
            frmUsuario.btnBuscarUsu.setEnabled(true);
            frmUsuario.btnActualizarUsu.setEnabled(true);
            }
            else
                JOptionPane.showMessageDialog(null, "No se encontró el usuario buscado", " usuario no encontrado", JOptionPane.ERROR_MESSAGE);        
        }
    }
  //================================================================================================
    else if(e.getSource()==frmUsuario.btnCancelarUsu){
     
      limpiarTxt();
      
            frmUsuario.btnEliminarUsu.setEnabled(false);
            frmUsuario.btnGuardarUsu.setEnabled(true);
            frmUsuario.btnBuscarUsu.setEnabled(true);
            frmUsuario.btnActualizarUsu.setEnabled(false);
    }
    
    
    ///================================================================================================
    //CLIC EN EL MENO DEL JTABLE 
    else if(e.getSource()==frmUsuario.popoDetallle)
    {   
        //obtiene la fila de la jtable
        int fila = frmUsuario.jtableUsuarios.getSelectedRow();
        //guarda el id de la fila seleccionada
        String id= String.valueOf(frmUsuario.jtableUsuarios.getValueAt(fila, 0).toString().trim());
        
        //1.se llena el modelo con el id ingresado en la caja de texto 
            modelUsu.setId(Integer.parseInt(id));

            //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(usuCRUD.buscarUsuario(modelUsu))  
            { 
                //lleva los datos a las cjas de texto
            frmUsuario.txtIdSede.setText(String.valueOf(modelUsu.id));
          //  frmUsuario.txtRol.setText(String.valueOf(modelUsu.id_rol));
            frmUsuario.txtNombreUsuario.setText(modelUsu.nombre);
            frmUsuario.txtUsuario.setText(String.valueOf(modelUsu.usuario));
            frmUsuario.txtPass.setText(String.valueOf(modelUsu.password));
           // frmUsuario.txtCorreoUsuario.setText(String.valueOf(modelUsu.correo));
            frmUsuario.txtTelefonoUsuario.setText(String.valueOf(modelUsu.telefono));
            frmUsuario.txtRepPass.setText(String.valueOf(modelUsu.password));
            frmUsuario.btnEliminarUsu.setEnabled(true);
            frmUsuario.btnGuardarUsu.setEnabled(false);
            frmUsuario.btnBuscarUsu.setEnabled(false);
            frmUsuario.btnActualizarUsu.setEnabled(true);
            
            }
         
     
    }
     }//fin de actionlistener 
    
    
     
   public void  limpiarTxt()
   {
       frmUsuario.txtIdSede.setText("");
       frmUsuario.txtNombreUsuario.setText("");
       //frmUsuario.txtRol.setText("");
       frmUsuario.txtTelefonoUsuario.setText("");
       //frmUsuario.txtCorreoUsuario.setText("");
       frmUsuario.txtDireccionUsuario.setText("");
       frmUsuario.txtPass.setText("");
       frmUsuario.txtRepPass.setText("");
       frmUsuario.txtUsuario.setText("");
       
 

}
   
   //======================================================================================
//FUNCIONES QUE PERMITEN LA VALIDACION DE LAS CAJAS DE TEXTO CUANDO  UNA TECLA ES PRESIONADA 
    @Override
    public void keyTyped(KeyEvent e) {
        
        if( e.getSource()==frmUsuario.txtIdSede  ||
          //  e.getSource()==frmUsuario.txtRol ||
            e.getSource()==frmUsuario.txtTelefonoUsuario)
           
            validarCaja.isNumeric(e);
        
        else if (e.getSource ()==frmUsuario.txtNombreUsuario) 
            validarCaja.isLetter(e);
        
       // else if (e.getSource()==frmUsuario.txtCorreoUsuario)
        //    validarCaja.validarCorreo(e);
        
        else if (e.getSource()==frmUsuario.txtDireccionUsuario)
            validarCaja.validarDireccion(e);
        
        else if (e.getSource()==frmUsuario.txtUsuario)
            validarCaja.validarUsuario(e);
        
        else if (e.getSource()==frmUsuario.txtPass || e.getSource()==frmUsuario.txtRepPass)
            validarCaja.validaPass(e);
        else if (e.getSource()==frmUsuario.txtBuscar){
            mostrarUsuarios(frmUsuario.txtBuscar.getText());
        }
        
            
        
            
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        }

    @Override
    public void keyReleased(KeyEvent e) {
      }

  //==============================================================================
//METODOS QYE SE ACTIVAN CUANDO SE PRESIONA CLIC CON EL MAUSE 

    @Override
    public void mouseClicked(MouseEvent e) {
        //JOptionPane.showMessageDialog(null, "", " ", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mousePressed(MouseEvent e) {
      }

    @Override
    public void mouseReleased(MouseEvent e) {
      }

    @Override
    public void mouseEntered(MouseEvent e) {
     }

    @Override
    public void mouseExited(MouseEvent e) {
  
    }

   public void mostrarUsuarios( String valor) 
   {
       
        DefaultTableModel modelo = new  DefaultTableModel();
        //crea una instancia de la clase UsuariosCrud
        UsuariosCRUD usuCR= new UsuariosCRUD();
        ResultSet rs =usuCR.listarUsuario(valor);
        modelo.setColumnIdentifiers(new Object[]{
            "Id USUARIO","USUARIO ", "ID ROL","NOMBRE ROL "});
        
       if(rs!=null)
        {
            try 
            {
            while(rs.next())
            {
                modelo.addRow(new Object[]{
                        rs.getString("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("id_rol"),
                        rs.getString("nombre_rol")
//                    
                      
                });
            }
            frmUsuario.jtableUsuarios.setModel(modelo);
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
   }

   
   
   
}
