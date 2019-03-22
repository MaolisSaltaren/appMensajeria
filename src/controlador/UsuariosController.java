
package controlador;

import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import misClases.Validacion;
import modelo.UsuariosCRUD;
import modelo.UsuariosModel;
import vista.formUsuarios;


public class UsuariosController implements ActionListener,KeyListener{
    
    //llamando a las clases creadas
     private final UsuariosModel modelUsu;
     private final formUsuarios frmUsuario;
     private final UsuariosCRUD usuCRUD;
     
     //objeto de la clase validacion
     Validacion validarNumero = new Validacion();
   
    
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
        this.frmUsuario.txtIdUsuario.addActionListener(this);
        this.frmUsuario.txtNombreUsuario.addKeyListener(this);
        this.frmUsuario.txtCorreoUsuario.addKeyListener(this);
        this.frmUsuario.txtDireccionUsuario.addKeyListener(this);
        this.frmUsuario.txtPass.addKeyListener(this);
        this.frmUsuario.txtRepPass.addKeyListener(this);
        this.frmUsuario.txtRol.addKeyListener(this);
        this.frmUsuario.txtTelefonoUsuario.addKeyListener(this);
        this.frmUsuario.txtUsuario.addKeyListener(this);
      
        
      
    }
 
 
    
//    metodo que inicia la vista 
    public void iniciar()
    {
        frmUsuario.setTitle("Usuarios");
        frmUsuario.setLocationRelativeTo(null);
      
        
    }
    
    //pone los botones a  la escucha 

    @Override
    public void actionPerformed(ActionEvent e) {
    
    //1.se pulso el boton de guardar
    if (e.getSource()== frmUsuario.btnGuardarUsu)
    {
        String pass = frmUsuario.txtPass.getText();
        String confirmaPass = frmUsuario.txtRepPass.getText();
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmUsuario.txtCorreoUsuario.getText().equals("")||frmUsuario.txtDireccionUsuario.getText().equals("") ||frmUsuario.txtIdUsuario.getText().equals("") ||
            frmUsuario.txtNombreUsuario.getText().equals("") ||frmUsuario.txtPass.getText().equals("") ||frmUsuario.txtRepPass.getText().equals("") ||
            frmUsuario.txtRol.getText().equals("") ||frmUsuario.txtTelefonoUsuario.getText().equals("") ||frmUsuario.txtUsuario.getText().equals("") )
         {
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         }
         else
         {
               
            //2.comparamos que las contraseñas sean iguales
             if(pass.equals(confirmaPass))
                   {


           //      1.1 se llena el modelo 
                   modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));
                   modelUsu.setNombre(frmUsuario.txtNombreUsuario.getText());
                   modelUsu.setTelefono(frmUsuario.txtTelefonoUsuario.getText());
                   modelUsu.setCorreo(frmUsuario.txtCorreoUsuario.getText());
                   modelUsu.setUsuario(frmUsuario.txtUsuario.getText());
                   modelUsu.setId_rol(Integer.parseInt(frmUsuario.txtRol.getText()));
                   modelUsu.setPassword(frmUsuario.txtPass.getText());


                        //1.2.llama al metodo insertar de la claseUsuariosCrud
                        //----------
                        if(usuCRUD.insertarUsuario(modelUsu))
                        JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR );
                        //----------
                   }
                   else 
                   {
                       JOptionPane.showMessageDialog(null , "las contraseñas deben ser iguales","Advertencia",JOptionPane.WARNING_MESSAGE);
                   }

                    
         }
    
      
        
    }
    //2.se pulsa el boton de eliminar
    
     else if (e.getSource()==frmUsuario.btnEliminarUsu)     
    {
        //2.1 llena el modelo 
         modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));
         
       //2.2.llama al metodo eliminar de la claseUsuariosCrud
        
        if(usuCRUD.eliminarUsuario(modelUsu)){        
           limpiarTxt();JOptionPane.showConfirmDialog(null,"El registro se elimino exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
        }else
            JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR );
            
        
    }
     
     //3. SE HA PULSADO EL BOTON DE ACTUALIZAR
     else if (e.getSource()==frmUsuario.btnActualizarUsu)
     {
         //      3.1 se llena el modelo 
        modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));
        modelUsu.setNombre(frmUsuario.txtNombreUsuario.getText());
        modelUsu.setTelefono(frmUsuario.txtTelefonoUsuario.getText());
        modelUsu.setCorreo(frmUsuario.txtCorreoUsuario.getText());
        modelUsu.setUsuario(frmUsuario.txtUsuario.getText());
        modelUsu.setId_rol(Integer.parseInt(frmUsuario.txtRol.getText()));
        modelUsu.setPassword(frmUsuario.txtPass.getText());
        
        //3.2. llama al metodo insertar de la claseUsuariosCrud
        
        if(usuCRUD.actualizarUsuario(modelUsu)){
            limpiarTxt(); JOptionPane.showMessageDialog(null,"El registro se ha actualizado exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
        }   
         else
            JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR );
    
    }
   
           // SE HA PULSADO EL BOTON DE BUSCAR
     if ( e.getSource()==frmUsuario.btnBuscarUsu )
    {
        modelUsu.setId(Integer.parseInt(frmUsuario.txtIdUsuario.getText()));
        
       
        
        if(usuCRUD.buscarUsuario(modelUsu))  
        { 
            //lleva los datos a las cjas de texto
        frmUsuario.txtIdUsuario.setText(String.valueOf(modelUsu.id));
        frmUsuario.txtRol.setText(String.valueOf(modelUsu.id_rol));
        frmUsuario.txtNombreUsuario.setText(modelUsu.nombre);
        frmUsuario.txtUsuario.setText(String.valueOf(modelUsu.usuario));
        frmUsuario.txtPass.setText(String.valueOf(modelUsu.password));
        frmUsuario.txtCorreoUsuario.setText(String.valueOf(modelUsu.correo));
        frmUsuario.txtTelefonoUsuario.setText(String.valueOf(modelUsu.telefono));
        }    
         else
            JOptionPane.showMessageDialog(null," No se encontró el usuario","informe de error ",JOptionPane.ERROR );   
    }
  
    }   
     
   public void  limpiarTxt()
   {
       frmUsuario.txtIdUsuario.setText("");
       frmUsuario.txtNombreUsuario.setText("");
       frmUsuario.txtRol.setText("");
       frmUsuario.txtTelefonoUsuario.setText("");
       frmUsuario.txtCorreoUsuario.setText("");
       frmUsuario.txtDireccionUsuario.setText("");
       frmUsuario.txtPass.setText("");
       frmUsuario.txtRepPass.setText("");
       frmUsuario.txtUsuario.setText("");
       
 

}

    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
       //validaciones de las cajas de texto al 
//       isnumeic = valida que solo se ingresen numeros
//       isletter= valida que solo se ingresen mayusculas y caracteres especiales 
       
        if (e.getSource()== frmUsuario.txtIdUsuario  || 
            e.getSource()== frmUsuario.txtRol  ) 
         validarNumero.isNumeric(e);
        
        //se valida que solo se ingresen letras
        
        else if (
                e.getSource()== frmUsuario.txtCorreoUsuario ||
                e.getSource()== frmUsuario.txtDireccionUsuario ||
                e.getSource()== frmUsuario.txtNombreUsuario ||
                e.getSource()== frmUsuario.txtCorreoUsuario ||
                e.getSource()== frmUsuario.txtTelefonoUsuario  
                ) 
         validarNumero.isLetter(e);
        
        else if (e.getSource()==frmUsuario.txtUsuario)
            validarNumero.isLetterAndCaracter(e);
        
         else if (e.getSource()==frmUsuario.txtPass || e.getSource()==frmUsuario.txtRepPass) 
             validarNumero.isLetterAndCaracterNoGuion(e);
        
     
    

    
        
    
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
}
