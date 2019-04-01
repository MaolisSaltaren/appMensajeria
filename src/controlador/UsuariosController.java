
package controlador;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import misClases.EncriptarPass;
import misClases.Validacion;
import modelo.SedesModel;
import modelo.SesionModel;
import modelo.UsuariosCRUD;
import modelo.UsuariosModel;
import vista.formBuscarSedes;
import vista.formUsuarios;


public class UsuariosController implements ActionListener,KeyListener,MouseListener{
    
    //llamando a las clases creadas
     private final UsuariosModel modelUsu;
     private final SesionModel modelSesion;
     private final formUsuarios frmUsuario;
     private final UsuariosCRUD usuCRUD;
     
     public  static  String valorSEDE;
     public  static  SedesModel modelsedes = new SedesModel();
     
     
     
     //objeto de la clase validacion
     Validacion validarCaja= new Validacion();
   
    
    //creando el constructor de la clase 
    public  UsuariosController(SesionModel ses, UsuariosModel modelo ,formUsuarios frmUsuario,UsuariosCRUD crud)
    {
        this.modelUsu= modelo;
        this.modelSesion= ses;
        this.usuCRUD=crud;
        this.frmUsuario=frmUsuario;
        //declarar las acciones de los botones
        this.frmUsuario.btnGuardarUsu.addActionListener(this);
        this.frmUsuario.btnCancelarUsu.addActionListener(this);
        this.frmUsuario.btnEliminarUsu.addActionListener(this);
        this.frmUsuario.btnActualizarUsu.addActionListener(this);
        this.frmUsuario.btnBuscarUsu.addActionListener(this);
        this.frmUsuario.btnBuscarSedes.addActionListener(this);
        
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
         mostrarUsuarios("");

      
        //carga los datos en el jtable
        frmUsuario.txtIdSede.setText(String.valueOf(modelSesion.getId_sede()));
        frmUsuario.txtNombreSede.setText(modelSesion.getSede_nombre());
        
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
         if(frmUsuario.txtDireccionUsuario.getText().equals("") ||frmUsuario.txtIdSede.getText().equals("") ||
            frmUsuario.txtNombreUsuario.getText().equals("") ||frmUsuario.txtPass.getText().equals("") ||frmUsuario.txtRepPass.getText().equals("") ||
            frmUsuario.txtTelefonoUsuario.getText().equals("") ||frmUsuario.txtUsuario.getText().equals("") )
         
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         
         else  if(frmUsuario.txtFecha_nac.getDate()==null)
          JOptionPane.showMessageDialog(null, "La  fecha de nacimiento  NO TIENE  el formato aaaa-mm-yy, corrige el error e intenta nuevamente","Advertencia",JOptionPane.ERROR_MESSAGE);
          
         else if(frmUsuario.txtNombre_rol.getSelectedItem().equals("Seleccionar") )
                 JOptionPane.showMessageDialog(null, "Debe agignar un rol a este empleado", " ", JOptionPane.INFORMATION_MESSAGE);
         
       
         
           else  if(frmUsuario.lblDisponible.getText()=="¡Ocupado!")
         
        JOptionPane.showMessageDialog(null, "El nombre de usuario ya se encuentra ocupado por otra persona","Advertencia",JOptionPane.ERROR_MESSAGE);

         
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
                    FechaNacimiento = formatoFecha.format(frmUsuario.txtFecha_nac.getDate());
       

           //      1.1 se llena el modelo  de los usuarios trabajadores 
                   modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));
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
        if(frmUsuario.txtIdUsuario.getText().equals(""))
            JOptionPane.showMessageDialog(null, " El id del usuario no puede estar vacio", "app ", JOptionPane.WARNING_MESSAGE);  
        else
        {
              int respuesta= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar este registro?", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
     
            if(respuesta==0)
            {
            //2.1 llena el modelo 
             modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));

           //2.2.llama al metodo eliminar de la claseUsuariosCrud

            if(usuCRUD.eliminarUsuario(modelUsu)){        
               limpiarTxt();
               JOptionPane.showMessageDialog(null,"El registro se elimino exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
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
         //captura el usuario y la confirmacion de la contraseña para mas adelante hacer la validacion 
          String pass = frmUsuario.txtPass.getText();
          String confirmaPass = frmUsuario.txtRepPass.getText();
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmUsuario.txtFecha_nac.getDate()==null||frmUsuario.txtDireccionUsuario.getText().equals("") ||frmUsuario.txtIdSede.getText().equals("") ||
            frmUsuario.txtNombreUsuario.getText().equals("") ||frmUsuario.txtPass.getText().equals("") ||frmUsuario.txtRepPass.getText().equals("") ||
            frmUsuario.txtNombre_rol.getSelectedItem().equals("Seleccionar") ||frmUsuario.txtTelefonoUsuario.getText().equals("") ||frmUsuario.txtUsuario.getText().equals("") )
         {
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         }
         else  if(frmUsuario.txtFecha_nac.getDate()==null)
         
             JOptionPane.showMessageDialog(null, "La  fecha de nacimiento  NO TIENE  el formato aaaa-mm-yy, corrige el error e intenta nuevamente","Advertencia",JOptionPane.ERROR_MESSAGE);

          else  if(frmUsuario.lblDisponible.getText()=="¡Ocupado!")
         
        JOptionPane.showMessageDialog(null, "El nombre de usuario ya se encuentra ocupado por otra persona","Advertencia",JOptionPane.ERROR_MESSAGE);

         
         
         else
         {
               
            //2.comparamos que las contraseñas sean iguales
             if(pass.equals(confirmaPass))
                   {

                        //optiene la fecha del jcalendar 
                        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                       String FechaNacimiento;
                       FechaNacimiento = formatoFecha.format(frmUsuario.txtFecha_nac.getDate());

                       // comprobar que la contraseña no se ha cambiado 
                       if(modelUsu.getPassword().equals(frmUsuario.txtPass.getText()))
                       {
                            modelUsu.setPassword(frmUsuario.txtPass.getText());
                        //     3.1 se llena el modelo  de los usuarios trabajadores sin modificar la contraseña del usuario
                               modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));
                               modelUsu.setNombre(frmUsuario.txtNombreUsuario.getText());
                               modelUsu.setTelefono(frmUsuario.txtTelefonoUsuario.getText());
                               modelUsu.setUsuario(frmUsuario.txtUsuario.getText());                          
                               modelUsu.setFecha_nacimiento(FechaNacimiento);
                               modelUsu.setDireccion_usuario(frmUsuario.txtDireccionUsuario.getText());
                               modelUsu.setId_sede(modelSesion.getId_sede());
                               modelUsu.setNombre_rol(frmUsuario.txtNombre_rol.getSelectedItem().toString());


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
                       else {
                           // se ha detectado cambio en la contraseña 
                            int respuesta= JOptionPane.showConfirmDialog(null, "Se ha detectado que la contraseña ha cambiado, por seguridad,se procederá a encriptarla, por favor asegurese de memorizarla \n ¿ Desea Continuar?", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

                               if(respuesta==0)
                               {
                                          //SE  detecto un cambio en la contraseña se INCRIPTA LA CONTRASELÑA CON EL HASH SHA1    
                                         EncriptarPass passhash= new EncriptarPass();
                                        String passEncrip= passhash.sha1(frmUsuario.txtPass.getText());
                                         modelUsu.setPassword(passEncrip);
                                           //     3.1 se llena el modelo  de los usuarios trabajadores 
                                            modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));
                                            modelUsu.setNombre(frmUsuario.txtNombreUsuario.getText());
                                            modelUsu.setTelefono(frmUsuario.txtTelefonoUsuario.getText());
                                            modelUsu.setUsuario(frmUsuario.txtUsuario.getText());                          
                                            modelUsu.setFecha_nacimiento(FechaNacimiento);
                                            modelUsu.setDireccion_usuario(frmUsuario.txtDireccionUsuario.getText());
                                            modelUsu.setId_sede(modelSesion.getId_sede());
                                            modelUsu.setNombre_rol(frmUsuario.txtNombre_rol.getSelectedItem().toString());


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
                              
                   }
             else
                 JOptionPane.showMessageDialog(null, "Los password no coinciden ", " ", JOptionPane.WARNING_MESSAGE);
        }
   }//fin de actualizar
         
   //=============================================================================================
           // SE HA PULSADO EL BOTON DE BUSCAR
    else if ( e.getSource()==frmUsuario.btnBuscarUsu )
    {
        //validacion de que el id del usuario no este vacion 
        if (frmUsuario.txtIdUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa el id del usuario", " ", JOptionPane.WARNING_MESSAGE);
        
        }
        else{
           //1.se llena el modelo con el id ingresado en la caja de texto 
            modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));
            modelUsu.setId_sede(modelSesion.getId_sede());

            //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(usuCRUD.buscarUsuario(modelUsu))  
            { 
                //lleva los datos a las cjas de texto
                frmUsuario.txtIdSede.setText(String.valueOf(modelUsu.getId_sede()));
                frmUsuario.txtNombreSede.setText(modelSesion.getSede_nombre());
                frmUsuario.txtNombreUsuario.setText(modelUsu.nombre);
                frmUsuario.txtTelefonoUsuario.setText(String.valueOf(modelUsu.telefono));
                frmUsuario.txtDireccionUsuario.setText(String.valueOf(modelUsu.direccion_usuario));
                frmUsuario.txtFecha_nac.setDate(Date.valueOf(modelUsu.getFecha_nacimiento()));
                frmUsuario.txtNombre_rol.setSelectedItem(modelUsu.getNombre_rol());
                frmUsuario.txtUsuario.setText(String.valueOf(modelUsu.usuario));
                frmUsuario.txtPass.setText(String.valueOf(modelUsu.password));
                frmUsuario.txtIdSede.setText(String.valueOf(modelUsu.id));
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
    //SE HA PRESIONADO EL BOTON DE CANCELAR
    
    else if(e.getSource()==frmUsuario.btnCancelarUsu){
     
      limpiarTxt();
      
            frmUsuario.btnEliminarUsu.setEnabled(false);
            frmUsuario.btnGuardarUsu.setEnabled(true);
            frmUsuario.btnBuscarUsu.setEnabled(true);
            frmUsuario.btnActualizarUsu.setEnabled(false);
    }
    
   //=========================================================
    //SE HA PULSADO EL BOTOR DE BUSCAR SEDES
    else if (e.getSource()==frmUsuario.btnBuscarSedes)
    {
      
        
       formBuscarSedes buscaSede= new formBuscarSedes(frmUsuario,true);
 
        buscaSede.setVisible(true);
      //  JOptionPane.showMessageDialog(null, " el valor lleado en el jdialog es"+ valorSEDE, " ", JOptionPane.INFORMATION_MESSAGE);
        frmUsuario.txtNombreSede.setText(modelsedes.getSede_nombre());
        frmUsuario.txtIdSede.setText(String.valueOf(modelsedes.getSede_id()));
        
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
            modelUsu.setId_sede(modelSesion.getId_sede());
            try {
            //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(usuCRUD.buscarUsuario(modelUsu))  
            { 
            //lleva los datos a las cjas de texto
         
  
                frmUsuario.txtIdSede.setText(String.valueOf(modelUsu.getId_sede()));
                frmUsuario.txtNombreSede.setText(modelSesion.getSede_nombre());
                frmUsuario.txtNombreUsuario.setText(modelUsu.nombre);
                frmUsuario.txtTelefonoUsuario.setText(modelUsu.telefono);
                frmUsuario.txtDireccionUsuario.setText(modelUsu.direccion_usuario);
                frmUsuario.txtFecha_nac.setDate(Date.valueOf(modelUsu.getFecha_nacimiento()));
                frmUsuario.txtNombre_rol.setSelectedItem(modelUsu.getNombre_rol());
                frmUsuario.txtUsuario.setText(modelUsu.usuario);
                frmUsuario.txtPass.setText(modelUsu.password);
                frmUsuario.txtRepPass.setText(modelUsu.password);
                frmUsuario.txtIdUsuario.setText(id);

            
            
            frmUsuario.btnEliminarUsu.setEnabled(true);
            frmUsuario.btnGuardarUsu.setEnabled(false);
            frmUsuario.btnBuscarUsu.setEnabled(false);
            frmUsuario.btnActualizarUsu.setEnabled(true);
            
            }
         
     
    }// fin frl try 
    
    catch  (Exception ex)
            {
            
            }
    }//fin del else if 
 }//fin de actionlistener 
    
   //================================================================================== 
     
   public void  limpiarTxt()
   {
       //establece el id  de la sede con la cual el admin inicio sesion 
       frmUsuario.txtIdSede.setText(String.valueOf( modelSesion.getId_sede()));
       frmUsuario.txtNombreSede.setText(modelSesion.getSede_nombre());
       
       //limpia las cajas de texto
       frmUsuario.txtNombreUsuario.setText("");
       frmUsuario.txtIdUsuario.setText("");
       frmUsuario.txtFecha_nac.setDate(null);
       frmUsuario.txtTelefonoUsuario.setText("");
       frmUsuario.txtDireccionUsuario.setText("");
       frmUsuario.txtPass.setText("");
       frmUsuario.txtRepPass.setText("");
       frmUsuario.txtUsuario.setText("");
       frmUsuario.txtNombre_rol.setSelectedItem("Seleccionar");
       frmUsuario.lblDisponible.setText("...");
       
}
   
   //======================================================================================
//FUNCIONES QUE PERMITEN LA VALIDACION DE LAS CAJAS DE TEXTO CUANDO  UNA TECLA ES PRESIONADA 
    @Override
    public void keyTyped(KeyEvent e) {
        
        if( e.getSource()==frmUsuario.txtIdUsuario|| e.getSource()==frmUsuario.txtTelefonoUsuario)
     
            validarCaja.isNumeric(e);
           
        else if (e.getSource ()==frmUsuario.txtNombreUsuario) 
            validarCaja.isLetter(e);

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
         if (e.getSource()==frmUsuario.txtUsuario)
        {
          //1.lleno el modelo con el usuario ingresado 
            modelUsu.setUsuario(frmUsuario.txtUsuario.getText());
            
           
            //valida  la disponibilidad del usuario
            if(usuCRUD.disponibilidadUsuario(modelUsu)!=false)
                {
                    //2. declaro la variable que determina si hay disponibilidad de nombre de usuario o no 
                    int usus_cantidad =modelUsu.disponibilidad_usuario;
                    
                    if(usus_cantidad==0){
                        frmUsuario.lblDisponible.setText("¡Disponible!");
                        frmUsuario.lblDisponible.setForeground(Color.blue);
          
                        }
                    else
                    {
                        frmUsuario.lblDisponible.setText("¡Ocupado!");
                          frmUsuario.lblDisponible.setForeground(Color.RED);
                    }
                        
                    
                         
                    
                }
        }
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
        ResultSet rs =usuCR.listarUsuario(valor,modelSesion.getId_sede());
        modelo.setColumnIdentifiers(new Object[]{
            "Id EMPLEADO","NOMBRE ", " USUARIO","NOMBRE ROL ","ULTIMA SESION "});
        String ultimaSesion ;
       if(rs!=null)
        {
            try 
            {
            while(rs.next())
            {
                ultimaSesion= String.valueOf( rs.getTimestamp("TRABA_LAST_SESION"));
               // JOptionPane.showMessageDialog(null, "sesion ultima="+ juan, " ", JOptionPane.INFORMATION_MESSAGE);
                modelo.addRow(new Object[]{
                        rs.getString("traba_id"),
                        rs.getString("TRABA_NOMBRE"),
                        rs.getString("TRABA_USU"),
                        rs.getString("TRABA_ROL"),
                        ultimaSesion
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