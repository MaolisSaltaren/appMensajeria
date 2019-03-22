//////
package misClases;

import java.awt.event.KeyEvent;



public class Validacion {
    
    
   //COMMIT DE NUMERO 3

    
    
    //rama de desarrollo 
    // validacion de que la cajas de texto solo admitan numeros 
    public void isNumeric(KeyEvent e)
    {
        
         char caracter = e.getKeyChar();
        if(((caracter < '0') ||(caracter > '9')) && (caracter != e.VK_BACK_SPACE))
          e.consume();
          
     }
    
    //COMIT DE  NUMERO 3
    
    //solo se permite ingresar letras en el jtextfield
    public void isLetter( KeyEvent e)
    {
    
    char caracter = e.getKeyChar();
        if((caracter <'a'||caracter>'z')&&(caracter <'A'|| caracter >'Z') && caracter!= (char)KeyEvent.VK_SPACE && caracter!= 'ñ'&& caracter != 'Ñ')
         e.consume();
            
    }
    
    //ayuda avalidar letras numeros y  loc caracteres "-" "_"
    public void isLetterAndCaracterNoGuion( KeyEvent e)
    {
    
    char caracter = e.getKeyChar();
        if((caracter < '0') ||(caracter > '9')&&(caracter <'a'||caracter>'z')&&(caracter <'A'|| caracter >'Z') && caracter!= e.VK_SPACE && caracter!= 'ñ'&& caracter != 'Ñ')
             e.consume();
            
    }
    
    //valida que solo se ingresen caracteres
    public void isLetterAndCaracter( KeyEvent e)
    {
    
    char caracter = e.getKeyChar();
        if((caracter < '0') ||(caracter > '9')&&(caracter <'a'||caracter>'z')&&(caracter <'A'|| caracter >'Z') && caracter!= e.VK_SPACE && caracter!= 'ñ'&& caracter != '-'&& caracter != '_'&& caracter != 'Ñ')
             e.consume();
        
            
    }
    
    
    
    
   
        
    
    
    
    
}
