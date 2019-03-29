
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import misClases.Validacion;
import modelo.PaquetesCRUD;
import modelo.PaquetesModel;
import modelo.SedesModel;
import vista.formPaquetes;


public class PaquetesController implements ActionListener,KeyListener,MouseListener{
    
     private final PaquetesModel modelPaque;
     

     private final formPaquetes frmPaque;
     private final PaquetesCRUD paqueCRUD;
     
   //  public  static  String valorSEDE;
 ///    public  static  SedesModel modelsedes = new SedesModel();
     
     
      
     //objeto de la clase validacion
     Validacion validarCaja= new Validacion();
   
    
    //creando el constructor de la clase 
    public  PaquetesController(PaquetesModel modelo ,formPaquetes frmPaque,PaquetesCRUD crud)
    {
        this.modelPaque= modelo;
        this.paqueCRUD=crud;
        this.frmPaque=frmPaque;
        
        //declarar las acciones de los botones
        this.frmPaque.btnGuardarPaquete.addActionListener(this);
        this.frmPaque.btnCancelarPaquete.addActionListener(this);
        this.frmPaque.btnEliminarPaquete.addActionListener(this);
        this.frmPaque.btnActualizarPaquete.addActionListener(this);
        this.frmPaque.btnBuscarPaquete.addActionListener(this);
        this.frmPaque.popoDetallle.addActionListener(this);
     
        
        //coloca los texbox a la escucha del evento de presionar un boton 
        this.frmPaque.txtIdPaquete.addKeyListener(this);
        this.frmPaque.txtNombrePaquete.addKeyListener(this);
        this.frmPaque.txtDescripcionPaquete.addKeyListener(this);
        this.frmPaque.txtPrecioPaquete.addKeyListener(this);
        this.frmPaque.txtBusquedaPaquete.addKeyListener(this);
        
        this.frmPaque.jtablePaquetes.addMouseListener(this);
        

        
    }
     

//    metodo que inicia la vista 
    public void iniciar() 
    {
        frmPaque.setTitle("Paquetes");
        frmPaque.setLocationRelativeTo(null);
        limpiarTxt();
     //   String ValorBuscado="";
          mostrarPaquetes("");
          
          frmPaque.btnBuscarPaquete.setVisible(false);
          frmPaque.txtIdPaquete.setVisible(false);
          frmPaque.lblIdPaquete.setVisible(false);
   
    }
    
    //pone los botones a  la escucha 

    @Override
    public void actionPerformed(ActionEvent e) {
    
   //1.SE HA PULSADO EL BOTON GUARDAR PAQUETES 
    if (e.getSource()== frmPaque.btnGuardarPaquete)
    {
       
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmPaque.txtPrecioPaquete.getText().equals("") ||
            frmPaque.txtNombrePaquete.getText().equals("") )
         
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         
  
         else 
         
         {
               
          

           //      1.1 se llena el modelo  de los paquetes  
                 //  modelPaque.setPaqueteId(Integer.parseInt(frmPaque.txtIdPaquete.getText()));
                   modelPaque.setPaquete_nombre(frmPaque.txtNombrePaquete.getText());
                   modelPaque.setPaquete_descripcion(frmPaque.txtDescripcionPaquete.getText());
                   modelPaque.setPaquete_precio(Double.parseDouble(frmPaque.txtPrecioPaquete.getText()));
                     


                        //1.2.llama al metodo insertar de la paquetes crud
                        //----------
                        if(paqueCRUD.insertarPaquete(modelPaque))
                        {limpiarTxt();
                        frmPaque.btnEliminarPaquete.setEnabled(false);
                        frmPaque.btnGuardarPaquete.setEnabled(true);
                        frmPaque.btnBuscarPaquete.setEnabled(true);
                        frmPaque.btnActualizarPaquete.setEnabled(false);
                         mostrarPaquetes("");
                        JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE );
                        //----------
                   }
                     
         }    //fin de guardar clientes
  

    
    //====================================================================================
    //2.se pulsa el boton de eliminar
    
     else if (e.getSource()==frmPaque.btnEliminarPaquete)     
    {
        // 2.0 verifica que el id del paquetes no este vacio
        if(frmPaque.txtIdPaquete.getText().equals(""))
            JOptionPane.showMessageDialog(null, " El id del paquete no puede estar vacio", "app ", JOptionPane.WARNING_MESSAGE);  
        else
        {
              int respuesta= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar este registro?", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
     
            if(respuesta==0)
            {
            //2.1 llena el modelo 
             modelPaque.setPaqueteId(Integer.parseInt(frmPaque.txtIdPaquete.getText()));

           //2.2.llama al metodo eliminar de la clasePaquetesCrud

            if(paqueCRUD.eliminarPaquete(modelPaque)){        
               limpiarTxt();
               JOptionPane.showMessageDialog(null,"El registro se elimino exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
            frmPaque.btnEliminarPaquete.setEnabled(false);
            frmPaque.btnGuardarPaquete.setEnabled(true);
            frmPaque.btnBuscarPaquete.setEnabled(true);
            frmPaque.btnActualizarPaquete.setEnabled(false);
              mostrarPaquetes("");
            }else
                JOptionPane.showMessageDialog(null,"Operacion no realizada","informe de error ",JOptionPane.ERROR_MESSAGE );
       
            
            }
        }
        
    }
     
     //=====================================================================================
     
     //3. SE HA PULSADO EL BOTON DE ACTUALIZAR
     else if (e.getSource()==frmPaque.btnActualizarPaquete)
     {
         
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmPaque.txtPrecioPaquete.getText().equals("") ||
            frmPaque.txtNombrePaquete.getText().equals("") )
         {
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         }
         
         else
         {
               
                   
                //      1.1 se llena el modelo  de los clientes  
                modelPaque.setPaqueteId(Integer.parseInt(frmPaque.txtIdPaquete.getText()));
                modelPaque.setPaquete_nombre(frmPaque.txtNombrePaquete.getText());
                modelPaque.setPaquete_descripcion(frmPaque.txtDescripcionPaquete.getText());
                modelPaque.setPaquete_precio(Double.parseDouble(frmPaque.txtPrecioPaquete.getText()));
               
                if(paqueCRUD.actualizarPaquete(modelPaque)){
                    limpiarTxt(); JOptionPane.showMessageDialog(null,"El registro se ha actualizado exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                 frmPaque.btnEliminarPaquete.setEnabled(false);
                 frmPaque.btnGuardarPaquete.setEnabled(true);
                 frmPaque.btnBuscarPaquete.setEnabled(true);
                 frmPaque.btnActualizarPaquete.setEnabled(false);
                  mostrarPaquetes("");
                }   
                 else JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE);            

               }
   }//fin de actualizar
         
   //=============================================================================================
           // SE HA PULSADO EL BOTON DE BUSCAR
    else if ( e.getSource()==frmPaque.btnBuscarPaquete )
    {
        //validacion de que el id del paquetes no este vacion 
        if (frmPaque.txtIdPaquete.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa el id del paquete", " ", JOptionPane.WARNING_MESSAGE);
        
        }
        else{
           //1.se llena el modelo con el id ingresado en la caja de texto 
            modelPaque.setPaqueteId(Integer.parseInt(frmPaque.txtIdPaquete.getText()));
          

             //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(paqueCRUD.getPaquetesById(modelPaque))  
            { 
                //lleva los datos previamente guardados en el modelo a las cjas de texto
                frmPaque.txtIdPaquete.setText(String.valueOf(modelPaque.getPaquete_id()));
                frmPaque.txtNombrePaquete.setText(modelPaque.getPaquete_nombre());
                frmPaque.txtDescripcionPaquete.setText(modelPaque.getPaquete_descripcion());
                frmPaque.txtPrecioPaquete.setText(String.valueOf(modelPaque.getPaquete_precio()));
              
                frmPaque.btnEliminarPaquete.setEnabled(true);
                frmPaque.btnGuardarPaquete.setEnabled(false);
                frmPaque.btnBuscarPaquete.setEnabled(true);
                frmPaque.btnActualizarPaquete.setEnabled(true);
            }
            else
                JOptionPane.showMessageDialog(null, "No se encontró el paquetes buscado", " paquete no encontrado", JOptionPane.ERROR_MESSAGE);        
        }
    }
  //================================================================================================
    //SE HA PRESIONADO EL BOTON DE CANCELAR
    
    else if(e.getSource()==frmPaque.btnCancelarPaquete){
     
      limpiarTxt();
      
            frmPaque.btnEliminarPaquete.setEnabled(false);
            frmPaque.btnGuardarPaquete.setEnabled(true);
            frmPaque.btnBuscarPaquete.setEnabled(true);
            frmPaque.btnActualizarPaquete.setEnabled(false);
    }
    
   
    
    
    ///================================================================================================
    //CLIC EN EL MENú DEL JTABLE 
    else if(e.getSource()==frmPaque.popoDetallle)
    {   
        //obtiene la fila de la jtable
        int fila = frmPaque.jtablePaquetes.getSelectedRow();
        //guarda el id de la fila seleccionada
        String id= String.valueOf(frmPaque.jtablePaquetes.getValueAt(fila, 0).toString().trim());
        
          //1.se llena el modelo con el id ingresado en la caja de texto 
            modelPaque.setPaqueteId(Integer.parseInt(id));
           
            try {
            //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(paqueCRUD.getPaquetesById(modelPaque))  
            { 
         
                //lleva los datos previamente guardados en el modelo a las cjas de texto
                frmPaque.txtIdPaquete.setText(String.valueOf(modelPaque.getPaquete_id()));
                frmPaque.txtNombrePaquete.setText(modelPaque.getPaquete_nombre());
                frmPaque.txtDescripcionPaquete.setText(modelPaque.getPaquete_descripcion());
                frmPaque.txtPrecioPaquete.setText(String.valueOf(modelPaque.getPaquete_precio()));
              
                frmPaque.btnEliminarPaquete.setEnabled(true);
                frmPaque.btnGuardarPaquete.setEnabled(false);
                frmPaque.btnBuscarPaquete.setEnabled(false);
                frmPaque.btnActualizarPaquete.setEnabled(true);

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
            
       //limpia las cajas de texto
       frmPaque.txtNombrePaquete.setText("");
       frmPaque.txtIdPaquete.setText(""); 
       frmPaque.txtDescripcionPaquete.setText("");
       frmPaque.txtPrecioPaquete.setText("");
      
   
       
}
   
   //======================================================================================
//FUNCIONES QUE PERMITEN LA VALIDACION DE LAS CAJAS DE TEXTO CUANDO  UNA TECLA ES PRESIONADA 
    @Override
    public void keyTyped(KeyEvent e) {
        
        if( e.getSource()==frmPaque.txtIdPaquete)
     
            validarCaja.isNumeric(e);
           
        else if (e.getSource ()==frmPaque.txtNombrePaquete||  e.getSource()==frmPaque.txtDescripcionPaquete) 
            validarCaja.isLetter(e);
   

        else if (e.getSource()==frmPaque.txtPrecioPaquete)
            validarCaja.isNumeric(e);
      
      
        else if (e.getSource()==frmPaque.txtBusquedaPaquete){
            validarCaja.isLetter(e);
        }
        

    }
    @Override
    public void keyPressed(KeyEvent e) {
        
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       //busca en la base de datos de acuerdo texto ingresado
          if (e.getSource()==frmPaque.txtBusquedaPaquete)
        {

               mostrarPaquetes(frmPaque.txtBusquedaPaquete.getText());
          
            
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

   public void mostrarPaquetes( String Valor )
   {
       
       
        DefaultTableModel modelo = new  DefaultTableModel();
        
        //crea una instancia de la clase PaquetesCrud
        PaquetesCRUD paqueCR= new PaquetesCRUD();
        ResultSet rs =paqueCR.getPaquetesByNombre(Valor);
        modelo.setColumnIdentifiers(new Object[]{
            "Id ","NOMBRE ", " DESCRIPCION "," PRECIO"});
 
       if(rs!=null)
        {
            try 
            {
            while(rs.next())
            {
                      modelo.addRow(new Object[]{
                        rs.getInt("PAQUE_ID"),
                        rs.getString("PAQUE_NOMBRE"),
                        rs.getString("PAQUE_DESCRIPCION"),
                        rs.getDouble("PAQUE_PRECIO"),
            
                      
                });
            }
            frmPaque.jtablePaquetes.setModel(modelo);
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
       
       
}