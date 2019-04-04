//////
package misClases;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Validacion {  
   //COMIT DE PARTIDA 4
    //ESTE ES UNA MODIFICACION DESDE EL COMMIT DE PARTIDA 
    //rama de desarrollo 
    // validacion de que la cajas de texto solo admitan numeros 
    public void isNumeric(KeyEvent e)
    {
         char caracter = e.getKeyChar();
        if((caracter < '0') ||(caracter > '9') && (caracter != e.VK_BACK_SPACE))
          e.consume(); 
     }
    
    //VALIDA LA CANTIDAD DE CARACTERES A UNA CAJA DE TEXTO
    
    public void limitCaja(KeyEvent e,JTextField cajaTxt)
    {
      if (cajaTxt.getText().length()== 10) 

         e.consume(); 
     }
    
    //COMIT DE PARTIDA 
    
    //solo se permite ingresar letras en el jtextfield
    public void isLetter( KeyEvent e)
    {
    
    char caracter = e.getKeyChar();
        if(     
                (caracter <'a'||caracter>'z')
                &&(caracter <'A'|| caracter >'Z')
                && caracter!= (char)KeyEvent.VK_SPACE
                && caracter!= 'ñ'&& caracter != '.')
         e.consume();
            
    }
  
   //funcion que permite escribir correos electronicos en las cajas de texto 
    public void validarCorreo( KeyEvent e)
    {
    
    char caracter = e.getKeyChar();
        if(   
                ((caracter < '0') ||(caracter > '9'))&&
                (caracter <'a'||caracter>'z')
                &&(caracter <'A'|| caracter >'Z')
                && caracter!= (char)KeyEvent.VK_BACK_SPACE
                && caracter!= '@'&& caracter != '.'
                && caracter!= '-'&& caracter != '_')
         e.consume();
            
    }
    //permite ingresar en las cajas de texto formatos tipo direccion
    public void validarDireccion( KeyEvent e)
    {
    
    char caracter = e.getKeyChar();
        if(   
                   ((caracter < '0') ||(caracter > '9'))&&
                   (caracter <'a'||caracter>'z')
                && (caracter <'A'|| caracter >'Z')
                && caracter!= (char)KeyEvent.VK_SPACE
                && caracter!= '#'
                && caracter != '-'
                && caracter != '_')
         e.consume();
        
            
    }
    //validacion general para los nombres de usuarios del sistema
    public void validarUsuario( KeyEvent e)
    {
    
    char caracter = e.getKeyChar();
        if(   
                   ((caracter < '0') ||(caracter > '9'))&&
                   (caracter <'a'||caracter>'z')
                && (caracter <'A'|| caracter >'Z')
                && caracter!= (char)KeyEvent.VK_BACK_SPACE
                && caracter!= '.'
                && caracter != '-'
                && caracter != '_')
         e.consume();
            
    }
    //valida que en la contraseña no se admitan caracteres especiales aparte del punto
    public void validaPass( KeyEvent e)
    {
    
    char caracter = e.getKeyChar();
        if(   
                   ((caracter < '0') ||(caracter > '9'))&&
                   (caracter <'a'||caracter>'z')
                && (caracter <'A'|| caracter >'Z')
                && caracter!= (char)KeyEvent.VK_BACK_SPACE
                && caracter!= '.'
               
                )
         e.consume();
            
    }

    
}
