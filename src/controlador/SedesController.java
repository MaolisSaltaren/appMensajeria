
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
import modelo.CiudadesCRUD;
import modelo.CiudadesModel;
import modelo.SedesCRUD;
import modelo.SedesModel;
import vista.formSedes;


public class SedesController implements ActionListener,KeyListener,MouseListener{
    
     private final SedesModel modelSede;
     private final formSedes frmSede;
     private final SedesCRUD sedeCRUD;
     

 
     //objeto de la clase validacion
     Validacion validarCaja= new Validacion();
   
    
    //creando el constructor de la clase 
    public  SedesController(SedesModel modelo ,formSedes frmSede,SedesCRUD crud)
    {
        this.modelSede= modelo;
        this.sedeCRUD=crud;
        this.frmSede=frmSede;
        
        //declarar las acciones de los botones
        this.frmSede.btnGuardarSedes.addActionListener(this);
        this.frmSede.btnCancelarSedes.addActionListener(this);
        this.frmSede.btnEliminarSedes.addActionListener(this);
        this.frmSede.btnActualizarSedes.addActionListener(this);
        this.frmSede.btnBuscarSedes.addActionListener(this);
        this.frmSede.popoDetallle.addActionListener(this);
     
        
        //coloca los texbox a la escucha del evento de presionar un boton 
       // this.frmSede.txtIdPaquete.addKeyListener(this);
        this.frmSede.txtNombreSedes.addKeyListener(this);
        this.frmSede.txtDireccionSedes.addKeyListener(this);
      
        this.frmSede.txtBusquedaSedes.addKeyListener(this);
        
        this.frmSede.jTableSedes.addMouseListener(this);
        

        
    }
     

//    metodo que inicia la vista 
    public void iniciar() 
    {
        frmSede.setTitle("Paquetes");
        frmSede.setLocationRelativeTo(null);
        limpiarTxt();
     //   String ValorBuscado="";
          mostrarSedes("");
          cargarCiudades();
          
          frmSede.btnBuscarSedes.setVisible(false);
          frmSede.txtIdSedes.setVisible(false);
        
   
    }
    
    //pone los botones a  la escucha 

    @Override
    public void actionPerformed(ActionEvent e) {
    
   //1.SE HA PULSADO EL BOTON GUARDAR PAQUETES 
    if (e.getSource()== frmSede.btnGuardarSedes)
    {
       
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmSede.txtDireccionSedes.getText().equals("") ||
            frmSede.txtNombreSedes.getText().equals("") )
         
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

          else if(frmSede.txtCiudadSede.getSelectedItem().equals("Seleccionar") )
                 JOptionPane.showMessageDialog(null, "Debe agignar una ciudad a esta sede", " ", JOptionPane.INFORMATION_MESSAGE);
         
       
  
         else 
         
         {
               
          

           //      1.1 se llena el modelo  de los paquetes  
                 //  modelSede.setPaqueteId(Integer.parseInt(frmSede.txtIdPaquete.getText()));
                   modelSede.setSede_nombre(frmSede.txtNombreSedes.getText());
                   modelSede.setSede_direccion(frmSede.txtDireccionSedes.getText());
                   modelSede.setSede_ciudad(frmSede.txtCiudadSede.getSelectedItem().toString());
                     


                        //1.2.llama al metodo insertar de la paquetes crud
                        //----------
                        if(sedeCRUD.insertarSede(modelSede))
                        {limpiarTxt();
                        frmSede.btnEliminarSedes.setEnabled(false);
                        frmSede.btnGuardarSedes.setEnabled(true);
                        frmSede.btnBuscarSedes.setEnabled(true);
                        frmSede.btnActualizarSedes.setEnabled(false);
                         mostrarSedes("");
                        JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE );
                        //----------
                   }
                     
         }    //fin de guardar clientes
  

    
    //====================================================================================
    //2.se pulsa el boton de eliminar
    
     else if (e.getSource()==frmSede.btnEliminarSedes)     
    {
      
        int respuesta= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar este registro?", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
     
            if(respuesta==0)
            {
            //2.1 llena el modelo 
             modelSede.setSede_id(Integer.parseInt(frmSede.txtIdSedes.getText()));

           //2.2.llama al metodo eliminar de la clasePaquetesCrud

            if(sedeCRUD.eliminarSede(modelSede)){        
               limpiarTxt();
               JOptionPane.showMessageDialog(null,"El registro se elimino exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
            frmSede.btnEliminarSedes.setEnabled(false);
            frmSede.btnGuardarSedes.setEnabled(true);
            frmSede.btnBuscarSedes.setEnabled(true);
            frmSede.btnActualizarSedes.setEnabled(false);
              mostrarSedes("");
            }else
                JOptionPane.showMessageDialog(null,"Operacion no realizada","informe de error ",JOptionPane.ERROR_MESSAGE );
       
            
            }
        
        
    }
     
     //=====================================================================================
     
     //3. SE HA PULSADO EL BOTON DE ACTUALIZAR
     else if (e.getSource()==frmSede.btnActualizarSedes)
     {
         
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmSede.txtDireccionSedes.getText().equals("") ||
            frmSede.txtNombreSedes.getText().equals("") )
         {
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         }
             else if(frmSede.txtCiudadSede.getSelectedItem().equals("Seleccionar") )
                 JOptionPane.showMessageDialog(null, "Debe agignar una ciudad a esta sede", " ", JOptionPane.INFORMATION_MESSAGE);
         else
         {  
                //      1.1 se llena el modelo  de los clientes  
                modelSede.setSede_id(Integer.parseInt(frmSede.txtIdSedes.getText()));
                modelSede.setSede_nombre(frmSede.txtNombreSedes.getText());
                modelSede.setSede_direccion(frmSede.txtDireccionSedes.getText());
                modelSede.setSede_ciudad(frmSede.txtCiudadSede.getSelectedItem().toString());
               
                if(sedeCRUD.actualizarSede(modelSede)){
                    limpiarTxt(); JOptionPane.showMessageDialog(null,"El registro se ha actualizado exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                 frmSede.btnEliminarSedes.setEnabled(false);
                 frmSede.btnGuardarSedes.setEnabled(true);
                 frmSede.btnBuscarSedes.setEnabled(true);
                 frmSede.btnActualizarSedes.setEnabled(false);
                  mostrarSedes("");
                }   
                 else JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE);            

               }
   }//fin de actualizar
         
    
    
  //================================================================================================
    //SE HA PRESIONADO EL BOTON DE CANCELAR
    
    else if(e.getSource()==frmSede.btnCancelarSedes){
     
      limpiarTxt();
      
            frmSede.btnEliminarSedes.setEnabled(false);
            frmSede.btnGuardarSedes.setEnabled(true);
            frmSede.btnBuscarSedes.setEnabled(true);
            frmSede.btnActualizarSedes.setEnabled(false);
    }
    
   
    
    
    ///================================================================================================
    //CLIC EN EL MENú DEL JTABLE 
    else if(e.getSource()==frmSede.popoDetallle)
    {   
        //obtiene la fila de la jtable
        int fila = frmSede.jTableSedes.getSelectedRow();
        //guarda el id de la fila seleccionada
        String id= String.valueOf(frmSede.jTableSedes.getValueAt(fila, 0).toString().trim());
        
          //1.se llena el modelo con el id ingresado en la caja de texto 
            modelSede.setSede_id(Integer.parseInt(id));
           
            try {
            //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(sedeCRUD.getSedesById(modelSede))  
            { 
         
                //lleva los datos previamente guardados en el modelo a las cjas de texto
                frmSede.txtIdSedes.setText(String.valueOf(modelSede.getSede_id()));
                frmSede.txtNombreSedes.setText(modelSede.getSede_nombre());
                frmSede.txtDireccionSedes.setText(modelSede.getSede_direccion());
                frmSede.txtCiudadSede.setSelectedItem(modelSede.getSede_ciudad());
              
                frmSede.btnEliminarSedes.setEnabled(true);
                frmSede.btnGuardarSedes.setEnabled(false);
                frmSede.btnBuscarSedes.setEnabled(false);
                frmSede.btnActualizarSedes.setEnabled(true);

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
       frmSede.txtNombreSedes.setText("");
       frmSede.txtIdSedes.setText(""); 
       frmSede.txtDireccionSedes.setText("");
       frmSede.txtCiudadSede.setSelectedItem("Seleccionar");
      
   
       
}
   
   //======================================================================================
//FUNCIONES QUE PERMITEN LA VALIDACION DE LAS CAJAS DE TEXTO CUANDO  UNA TECLA ES PRESIONADA 
    @Override
    public void keyTyped(KeyEvent e) {
        
       
       if (e.getSource ()==frmSede.txtNombreSedes||  e.getSource()==frmSede.txtDireccionSedes) 
            validarCaja.validarUsuario(e);
       else  if (e.getSource()==frmSede.txtDireccionSedes) 
            validarCaja.validarUsuario(e);
   

      
     
    }
    @Override
    public void keyPressed(KeyEvent e) {
        
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       //busca en la base de datos de acuerdo texto ingresado
          if (e.getSource()==frmSede.txtBusquedaSedes)
        {

               mostrarSedes(frmSede.txtBusquedaSedes.getText());
          
            
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

   public void mostrarSedes( String valor) 
   {
       //validamos de quelas celdas no sea editables
        DefaultTableModel modelo = new  DefaultTableModel();
        
        //crea una instancia de la clase UsuariosCrud
        SedesCRUD sesCR= new SedesCRUD();
        ResultSet rs =sesCR.buscarSedesByNombre(valor);
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
        frmSede.jTableSedes.setModel(modelo);
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
      
   //carga en el jcombo todas las ciudades que estan registradas en la base de datos 
   public void cargarCiudades() 
   {
       
       
     
        CiudadesModel ciud= new CiudadesModel();
        //crea una instancia de la clase ClientesCrud
        CiudadesCRUD ciudCR= new CiudadesCRUD();
        ResultSet rs =ciudCR.getAllCiudad();
     
        
       if(rs!=null)
        {
            try 
            {
            while(rs.next())
            {
                
                String resul=rs.getString("ciud_nombre");
                frmSede.txtCiudadSede.addItem(resul);

            }
       
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
       
}