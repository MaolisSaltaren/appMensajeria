
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
import misClases.Validacion;
import modelo.CiudadesCRUD;
import modelo.CiudadesModel;
import modelo.SedesModel;
import modelo.ClientesCRUD;
import modelo.ClientesModel;
import vista.formClientes;


public class ClientesController  extends javax.swing.JDialog implements ActionListener,KeyListener,MouseListener{
    
    //llamando a las clases creadas
     private final ClientesModel modelClie;
    

     private final formClientes frmCliente;
     private final ClientesCRUD clieCRUD;
     
     public  static  String valorSEDE;
     public  static  SedesModel modelsedes = new SedesModel();
     
     
     
     //objeto de la clase validacion
     Validacion validarCaja= new Validacion();
   
    
    //creando el constructor de la clase 
    public  ClientesController(ClientesModel modelo ,formClientes frmCliente,ClientesCRUD crud)
    {
        this.modelClie= modelo;
        this.clieCRUD=crud;
        this.frmCliente=frmCliente;
        
        //declarar las acciones de los botones
        this.frmCliente.btnGuardarClie.addActionListener(this);
        this.frmCliente.btnCancelarClie.addActionListener(this);
        this.frmCliente.btnEliminarClie.addActionListener(this);
        this.frmCliente.btnActualizarClie.addActionListener(this);
        this.frmCliente.btnBuscarClie.addActionListener(this);
     
        
        //coloca los texbox a la escucha del evento de presionar un boton 
        this.frmCliente.txtIdCliente.addKeyListener(this);
        this.frmCliente.txtNombreCliente.addKeyListener(this);
        this.frmCliente.txtTelefonoCliente.addKeyListener(this);
        this.frmCliente.txtFecha_nac_clie.addKeyListener(this);
        this.frmCliente.txtDireccionCliente.addKeyListener(this);
        this.frmCliente.txtBuscarClie.addKeyListener(this);
        
        //evento de escucha al popop menu
         this.frmCliente.popoDetallle.addActionListener(this);
        
        //coloca a el jtable a la escucha de los eventos del mouse
        this.frmCliente.jtableClientes.addMouseListener(this);
        
    }
     
 
    
  
//    metodo que inicia la vista 
    public void iniciar() 
    {
        frmCliente.setTitle("Clientes");
        frmCliente.setLocationRelativeTo(null);
        limpiarTxt();
     //   String ValorBuscado="";
          mostrarClientes("clie_id","");
         cargarCiudades();

    
        
    }
    
    //pone los botones a  la escucha 

    @Override
    public void actionPerformed(ActionEvent e) {
    
   //1.SE HA PULSADO EL BOTON GUARDAR clientes 
    if (e.getSource()== frmCliente.btnGuardarClie)
    {
       
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmCliente.txtDireccionCliente.getText().equals("") ||frmCliente.txtIdCliente.getText().equals("") ||
            frmCliente.txtNombreCliente.getText().equals("")  ||
            frmCliente.txtTelefonoCliente.getText().equals("")  )
         
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         
         else  if(frmCliente.txtFecha_nac_clie.getDate()==null)
          JOptionPane.showMessageDialog(null, "La  fecha de nacimiento  NO TIENE  el formato aaaa-mm-yy, corrige el error e intenta nuevamente","Advertencia",JOptionPane.ERROR_MESSAGE);
          
         else if(frmCliente.txtCiudadCliente.getSelectedItem().equals("Seleccionar") )
                 JOptionPane.showMessageDialog(null, "Debe agignar una ciudad a este cliente", " ", JOptionPane.INFORMATION_MESSAGE);
         
  
         else 
         
         {
               
          
                   //optiene la fecha del jcalendar 
                     DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    String FechaNacimiento;
                    FechaNacimiento = formatoFecha.format(frmCliente.txtFecha_nac_clie.getDate());
       

           //      1.1 se llena el modelo  de los clientes  
                   modelClie.setId_cliente(Integer.parseInt(frmCliente.txtIdCliente.getText()));
                   modelClie.setNombre_cliente(frmCliente.txtNombreCliente.getText());
                   modelClie.setTelefono_cliente(frmCliente.txtTelefonoCliente.getText());
                   modelClie.setFecha_nac_cliente(FechaNacimiento);
                   modelClie.setDireccion_cliente(frmCliente.txtDireccionCliente.getText());
                   modelClie.setCiudad_cliente(frmCliente.txtCiudadCliente.getSelectedItem().toString());
                   


                        //1.2.llama al metodo insertar de la claseClientesCrud
                        //----------
                        if(clieCRUD.insertarCliente(modelClie))
                        {limpiarTxt();
                        frmCliente.btnEliminarClie.setEnabled(false);
                        frmCliente.btnGuardarClie.setEnabled(true);
                        frmCliente.btnBuscarClie.setEnabled(true);
                        frmCliente.btnActualizarClie.setEnabled(false);
                      mostrarClientes("clie_id","");
                        JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE );
                        //----------
                   }
                     
         }    //fin de guardar clientes
  

    
    //====================================================================================
    //2.se pulsa el boton de eliminar
    
     else if (e.getSource()==frmCliente.btnEliminarClie)     
    {
        // 2.0 verifica que el id del cliente no este vacio
        if(frmCliente.txtIdCliente.getText().equals(""))
            JOptionPane.showMessageDialog(null, " El id del cliente no puede estar vacio", "app ", JOptionPane.WARNING_MESSAGE);  
        else
        {
              int respuesta= JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar este registro?", "Alerta de eliminacion", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
     
            if(respuesta==0)
            {
            //2.1 llena el modelo 
             modelClie.setId_cliente(Integer.parseInt(frmCliente.txtIdCliente.getText()));

           //2.2.llama al metodo eliminar de la claseClientesCrud

            if(clieCRUD.eliminarCliente(modelClie)){        
               limpiarTxt();
               JOptionPane.showMessageDialog(null,"El registro se elimino exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
            frmCliente.btnEliminarClie.setEnabled(false);
            frmCliente.btnGuardarClie.setEnabled(true);
            frmCliente.btnBuscarClie.setEnabled(true);
            frmCliente.btnActualizarClie.setEnabled(false);
              mostrarClientes("clie_id","");
            }else
                JOptionPane.showMessageDialog(null,"Operacion no realizada","informe de error ",JOptionPane.ERROR_MESSAGE );
       
            
            }
        }
        
    }
     
     //=====================================================================================
     
     //3. SE HA PULSADO EL BOTON DE ACTUALIZAR
     else if (e.getSource()==frmCliente.btnActualizarClie)
     {
         
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmCliente.txtFecha_nac_clie.getDate()==null||frmCliente.txtDireccionCliente.getText().equals("") ||frmCliente.txtIdCliente.getText().equals("") ||
            frmCliente.txtNombreCliente.getText().equals("")  ||
            frmCliente.txtCiudadCliente.getSelectedItem().equals("Seleccionar") ||frmCliente.txtTelefonoCliente.getText().equals(""))
         {
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

         }
         else  if(frmCliente.txtFecha_nac_clie.getDate()==null)
         
             JOptionPane.showMessageDialog(null, "La  fecha de nacimiento  NO TIENE  el formato aaaa-mm-yy, corrige el error e intenta nuevamente","Advertencia",JOptionPane.ERROR_MESSAGE);

         
         else
         {
               
            
               //optiene la fecha del jcalendar 
               DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
               String FechaNacimiento;
               FechaNacimiento = formatoFecha.format(frmCliente.txtFecha_nac_clie.getDate());

                     
                //      1.1 se llena el modelo  de los clientes  
                modelClie.setId_cliente(Integer.parseInt(frmCliente.txtIdCliente.getText()));
                modelClie.setNombre_cliente(frmCliente.txtNombreCliente.getText());
                modelClie.setTelefono_cliente(frmCliente.txtTelefonoCliente.getText());
                modelClie.setFecha_nac_cliente(FechaNacimiento);
                modelClie.setDireccion_cliente(frmCliente.txtDireccionCliente.getText());
                modelClie.setCiudad_cliente(frmCliente.txtCiudadCliente.getSelectedItem().toString());
                //3.2. llama al metodo insertar de la claseClientesCrud

                if(clieCRUD.actualizarCliente(modelClie)){
                    limpiarTxt(); JOptionPane.showMessageDialog(null,"El registro se ha actualizado exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                 frmCliente.btnEliminarClie.setEnabled(false);
                 frmCliente.btnGuardarClie.setEnabled(true);
                 frmCliente.btnBuscarClie.setEnabled(true);
                 frmCliente.btnActualizarClie.setEnabled(false);
                  mostrarClientes("clie_id","");
                }   
                 else JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE);            

               }
   }//fin de actualizar
         
   //=============================================================================================
           // SE HA PULSADO EL BOTON DE BUSCAR
    else if ( e.getSource()==frmCliente.btnBuscarClie )
    {
        //validacion de que el id del cliente no este vacion 
        if (frmCliente.txtIdCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa el id del cliente", " ", JOptionPane.WARNING_MESSAGE);
        
        }
        else{
           //1.se llena el modelo con el id ingresado en la caja de texto 
            modelClie.setId_cliente(Integer.parseInt(frmCliente.txtIdCliente.getText()));
          

            //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(clieCRUD.getClienteById(modelClie))  
            { 
                //lleva los datos a las cjas de texto
                frmCliente.txtIdCliente.setText(String.valueOf(modelClie.getId_cliente()));
                frmCliente.txtNombreCliente.setText(modelClie.getNombre_cliente());
                frmCliente.txtTelefonoCliente.setText(modelClie.getTelefono_cliente());
                frmCliente.txtDireccionCliente.setText(String.valueOf(modelClie.getDireccion_cliente()));
                frmCliente.txtFecha_nac_clie.setDate(Date.valueOf(modelClie.getFecha_nac_cliente()));
                frmCliente.txtCiudadCliente.setSelectedItem(modelClie.getCiudad_cliente());

                frmCliente.btnEliminarClie.setEnabled(true);
                frmCliente.btnGuardarClie.setEnabled(false);
                frmCliente.btnBuscarClie.setEnabled(true);
                frmCliente.btnActualizarClie.setEnabled(true);
            }
            else
                JOptionPane.showMessageDialog(null, "No se encontró el cliente buscado", " cliente no encontrado", JOptionPane.ERROR_MESSAGE);        
        }
    }
  //================================================================================================
    //SE HA PRESIONADO EL BOTON DE CANCELAR
    
    else if(e.getSource()==frmCliente.btnCancelarClie){
     
      limpiarTxt();
      
            frmCliente.btnEliminarClie.setEnabled(false);
            frmCliente.btnGuardarClie.setEnabled(true);
            frmCliente.btnBuscarClie.setEnabled(true);
            frmCliente.btnActualizarClie.setEnabled(false);
    }
    
   
    
    
    ///================================================================================================
    //CLIC EN EL MENú DEL JTABLE 
    else if(e.getSource()==frmCliente.popoDetallle)
    {   
        //obtiene la fila de la jtable
        int fila = frmCliente.jtableClientes.getSelectedRow();
        //guarda el id de la fila seleccionada
        String id= String.valueOf(frmCliente.jtableClientes.getValueAt(fila, 0).toString().trim());
        
          //1.se llena el modelo con el id ingresado en la caja de texto 
            modelClie.setId_cliente(Integer.parseInt(id));
           
            try {
            //2. si se encontró algun resultado los carja en los jtextfield correspondientes
            if(clieCRUD.getClienteById(modelClie))  
            { 
         
                //lleva los datos a las cjas de texto
                frmCliente.txtIdCliente.setText(String.valueOf(modelClie.getId_cliente()));
                frmCliente.txtNombreCliente.setText(modelClie.getNombre_cliente());
                frmCliente.txtTelefonoCliente.setText(modelClie.getTelefono_cliente());
                frmCliente.txtDireccionCliente.setText(String.valueOf(modelClie.getDireccion_cliente()));
                frmCliente.txtFecha_nac_clie.setDate(Date.valueOf(modelClie.getFecha_nac_cliente()));
                frmCliente.txtCiudadCliente.setSelectedItem(modelClie.getCiudad_cliente());
            
                frmCliente.btnEliminarClie.setEnabled(true);
                frmCliente.btnGuardarClie.setEnabled(false);
                frmCliente.btnBuscarClie.setEnabled(false);
                frmCliente.btnActualizarClie.setEnabled(true);

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
       frmCliente.txtNombreCliente.setText("");
       frmCliente.txtIdCliente.setText("");
       frmCliente.txtFecha_nac_clie.setDate(null);
       frmCliente.txtTelefonoCliente.setText("");
       frmCliente.txtDireccionCliente.setText("");
       frmCliente.txtCiudadCliente.setSelectedItem("Seleccionar");
   
       
}
   
   //======================================================================================
//FUNCIONES QUE PERMITEN LA VALIDACION DE LAS CAJAS DE TEXTO CUANDO  UNA TECLA ES PRESIONADA 
    @Override
    public void keyTyped(KeyEvent e) {
        
        if( e.getSource()==frmCliente.txtIdCliente|| e.getSource()==frmCliente.txtTelefonoCliente)
     
            validarCaja.isNumeric(e);
           
        else if (e.getSource ()==frmCliente.txtNombreCliente) 
            validarCaja.isLetter(e);

        else if (e.getSource()==frmCliente.txtDireccionCliente)
            validarCaja.validarDireccion(e);
      
      
        else if (e.getSource()==frmCliente.txtBuscarClie){
           validarCaja.validarDireccion(e);
        }
        

    }
    @Override
    public void keyPressed(KeyEvent e) {
        
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       //busca en la base de datos de acuerdo texto ingresado
       if (e.getSource()==frmCliente.txtBuscarClie)
        {
//            Buscar por ID
//            Buscar por Nombre
//            Buscar por Ciudad
            
            if(frmCliente.txtTipoBusquedaCliente.getSelectedItem()=="Buscar por ID")
            
               mostrarClientes("clie_id",frmCliente.txtBuscarClie.getText());
            
            if(frmCliente.txtTipoBusquedaCliente.getSelectedItem()=="Buscar por Nombre")
            
               mostrarClientes("clie_nombre",frmCliente.txtBuscarClie.getText());
            
            if(frmCliente.txtTipoBusquedaCliente.getSelectedItem()=="Buscar por Ciudad")
            
               mostrarClientes("ciud_nombre", frmCliente.txtBuscarClie.getText());
            
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

   public void mostrarClientes( String campo,String valor) 
   {
       
       
        DefaultTableModel modelo = new  DefaultTableModel();
        
        //crea una instancia de la clase ClientesCrud
        ClientesCRUD usuCR= new ClientesCRUD();
        ResultSet rs =usuCR.getAllCliente(campo,valor);
        modelo.setColumnIdentifiers(new Object[]{
            "Id ","NOMBRE ", " TELEFONO"," FECHA NACI..","DIRECCION","CIUDAD"});
 
       if(rs!=null)
        {
            try 
            {
            while(rs.next())
            {
                      modelo.addRow(new Object[]{
                        rs.getString("CLIE_ID"),
                        rs.getString("ClIE_NOMBRE"),
                        rs.getString("CLIE_TELEFONO"),
                        rs.getString("CLIE_FECHA_NA"),
                        rs.getString("CLIE_DIRECCION"),
                        rs.getString("CIUD_NOMBRE"),
            
//                    
                      
                });
            }
            frmCliente.jtableClientes.setModel(modelo);
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
       
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
                frmCliente.txtCiudadCliente.addItem(resul);

            }
       
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
       
}