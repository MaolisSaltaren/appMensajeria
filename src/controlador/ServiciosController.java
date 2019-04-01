
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
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import misClases.Validacion;
import modelo.ServiciosModel;
import modelo.ServiciosCRUD;
import modelo.CiudadesCRUD;
import modelo.ClientesCRUD;
import modelo.ClientesModel;
import modelo.PaquetesCRUD;
import modelo.PaquetesModel;
import vista.formBuscarPaquetes;
import modelo.SesionModel;

import vista.formServicios;
import modelo.PaquetesModel;
import vista.formBuscarClienteEmisor;
import vista.formBuscarClienteReceptor;


public class ServiciosController implements ActionListener,KeyListener,MouseListener{
    
     private final ServiciosModel modelServicio;
     private final SesionModel modelSesion;
     private final formServicios frmServi;
     private final ServiciosCRUD serviCRUD;
     
     //modelos publicos accedibles desde las ventanas de busquedas de clientes y paquetes
      public static  PaquetesModel  modelpaque =new PaquetesModel();
      public static  ClientesModel  modelclieEmisor =new ClientesModel();
      public static  ClientesModel  modelclieReceptor=new ClientesModel();

     
     public  static 
 
     //objeto de la clase validacion
     Validacion validarCaja= new Validacion();
   
    
    //creando el constructor de la clase 
    public  ServiciosController(ServiciosModel modelo ,formServicios frmServi,ServiciosCRUD crud,SesionModel sesion)
    {
        this.modelServicio= modelo;
        this.serviCRUD=crud;
        this.frmServi=frmServi;
        this.modelSesion=sesion;
        
        
        //declarar las acciones de los botones
        this.frmServi.btnGuardarServicios.addActionListener(this);
        this.frmServi.btnIrClienteEmisor.addActionListener(this);
        this.frmServi.btnIrClienteReceptor.addActionListener(this);
        this.frmServi.btnIrClientePaquete.addActionListener(this);
        this.frmServi.btnBuscarClienteEmisor.addActionListener(this);
        this.frmServi.btnBuscarClienteReceptor.addActionListener(this);
        this.frmServi.btnBuscarPaquete.addActionListener(this);
        this.frmServi.btnCancelar.addActionListener(this);
         
        
        //coloca los texbox a la escucha del evento de presionar un boton 
       // this.frmServi.txtIdPaquete.addKeyListener(this);
        this.frmServi.txtIdClienteEmisor.addKeyListener(this);
        this.frmServi.txtIdClienteReceptor.addKeyListener(this);
        this.frmServi.txtIdPaquete.addKeyListener(this);
        this.frmServi.txtDireccionServicio.addKeyListener(this);
        this.frmServi.txtObservacionServicio.addKeyListener(this);
      

        
    }
     

//    metodo que inicia la vista 
    public void iniciar() 
    {
        frmServi.setTitle("Paquetes");
        frmServi.setLocationRelativeTo(null);
        limpiarTxt();
     //   String ValorBuscado="";
     
          cargarCiudades();
          
         if( serviCRUD.selectMax(modelServicio))
             frmServi.lblMaxServicio.setText(String.valueOf(modelServicio.getServi_id()));
           //2.agarra la hora del sistema y se la asigna al modelo  de servicios 
            Date fecha = new Date();
           DateFormat fechaHoraSistema = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
          frmServi.lblFechaActual.setText(fechaHoraSistema.format(fecha).toString());
       
        
   
    }
    
    //pone los botones a  la escucha 

    @Override
    public void actionPerformed(ActionEvent e) {
    
   //1.SE HA PULSADO EL BOTON GUARDAR PAQUETES 
    if (e.getSource()== frmServi.btnGuardarServicios)
    {
       
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmServi.txtIdClienteEmisor.getText().equals("") ||
            frmServi.txtIdClienteReceptor.getText().equals("")||
            frmServi.txtIdPaquete.getText().equals("")
                 )
         
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

          else if(frmServi.txtCiudadDestino.getSelectedItem().equals("Seleccionar") )
                 JOptionPane.showMessageDialog(null, "Debe elegir una ciudad destino ", " ", JOptionPane.WARNING_MESSAGE);
         
       
          else if(frmServi.txtIdClienteEmisor.getText().equals(frmServi.txtIdClienteReceptor.getText()) )
                 JOptionPane.showMessageDialog(null, " El cliente emisor debe ser diferente al cliente receptor ", " ", JOptionPane.WARNING_MESSAGE);
         
       
  
         else 
         
         {
             
                //2.agarra la hora del sistema y se la asigna al modelo  de servicios 
                        Date fecha = new Date();
                       DateFormat fechaHoraSistema = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

               // 3. se llena el modelo de servicios

               modelServicio.setServi_fecha(fechaHoraSistema.format(fecha).toString()); // llena el modelo con la fecha actual del sistema
               modelServicio.setServi_traba_id(modelSesion.getId_trabajador());// agarra el id del empleado del modelo se sesion con la que  ingreso el trabajador
               modelServicio.setServi_paque_id(Integer.parseInt(frmServi.txtIdPaquete.getText()));
               modelServicio.setServi_estado(frmServi.txtEstadoPaquete.getText());
               modelServicio.setServi_direccion(frmServi.txtDireccionServicio.getText());
               modelServicio.setServi_ciudad_destino(frmServi.txtCiudadDestino.getSelectedItem().toString());


               //4.guarda el sevicio
               //----------
               if(serviCRUD.insertarServicios(modelServicio,modelSesion))
               {
                   
                        //4.1 llena el modelo  de la tabla tbl_clien_servicios
             
                        modelServicio.setCliente_id_emisor(Integer.parseInt(frmServi.txtIdClienteEmisor.getText()));
                        modelServicio.setCliente_id_receptor(Integer.parseInt(frmServi.txtIdClienteReceptor.getText()));
                        
                          // 4.2 con base al servicio utilizado signa el cliente emisor 
                           if (serviCRUD.insertarServiCliente(modelServicio, modelServicio.getCliente_id_emisor(), 1)) 
                                
                            {
                                   // 4.3  con base al servicio utilizado signa el cliente receptor
                                 if (serviCRUD.insertarServiCliente(modelServicio, modelServicio.getCliente_id_receptor(), 2))
                                   {
                                
                                    limpiarTxt();

                                    // coloca la numeracion para un nuevo pedido 
                                     if( serviCRUD.selectMax(modelServicio))
                                        frmServi.lblMaxServicio.setText(String.valueOf(modelServicio.getServi_id()));

                                    JOptionPane.showMessageDialog(null,"El registro se guardo exitosamente ","INFORMACION",JOptionPane.INFORMATION_MESSAGE);                           

                                  }
                               
                              }
                     }
               else
                   JOptionPane.showMessageDialog(null," No se guardaron los datos","informe de error ",JOptionPane.ERROR_MESSAGE );
               //----------
              }

         }    //fin de guardar clientes
    
    //=====================================================================================================================
    //SE HA DETECTADO QUE SE PRESIONO EL BOTON DE BUSCAR ID CLIENTE EMISOR
    
    
    else  if(e.getSource()==frmServi.btnIrClienteEmisor)
    {
        buscarCliente(frmServi.txtIdClienteEmisor, frmServi.txtNombreCliente);
     
        
    }
    
    
    //=====================================================================================================================
    //SE HA DETECTADO QUE SE PRESIONO EL BOTON DE BUSCAR ID CLIENTE RECEPTOR
    
    
    
    else  if(e.getSource()==frmServi.btnIrClienteReceptor)
    {
          buscarCliente(frmServi.txtIdClienteReceptor, frmServi.txtNombreCliente1); 
    }
      //=====================================================================================================================
    //SE HA DETECTADO QUE SE PRESIONO EL BOTON DE BUSCAR ID PAQUETES
    
    
    
    else  if(e.getSource()==frmServi.btnIrClientePaquete)
    {
          irPaquetes();
    }
   
    
    
    //=====================================================================================================================
    //SE HA DETECTADO QUE SE PRESIONO EL BOTON DE BUSCAR CLIENTE RECEPTOR
    
     else  if(e.getSource()==frmServi.btnBuscarClienteEmisor)
    {
        
      
          //1instancia de la clase buscar clientes emisores 
        formBuscarClienteEmisor buscaClie= new formBuscarClienteEmisor(frmServi,true);
        buscaClie.setVisible(true);
        
        if(modelclieEmisor!= null)
        {
        //2llena  las cajas de texto con el modelo que se lleno en el paso anterior
        frmServi.txtIdClienteEmisor.setText(String.valueOf(modelclieEmisor.getId_cliente()));
        frmServi.txtNombreCliente.setText(modelclieEmisor.getNombre_cliente());
        }
        
        
    }
    //=====================================================================================================================
    //SE HA DETECTADO QUE SE PRESIONO EL BOTON DE BUSCAR CLIENTE RECEPTOR  
    
     else  if(e.getSource()==frmServi.btnBuscarClienteReceptor)
    {
        
      
          //1instancia de la clase buscar clientes emisores 
        formBuscarClienteReceptor buscaClie= new formBuscarClienteReceptor(frmServi,true);
        buscaClie.setVisible(true);
        
        if(modelclieEmisor!= null)
        {
        //2llena  las cajas de texto con el modelo que se lleno en el paso anterior
        frmServi.txtIdClienteReceptor.setText(String.valueOf(modelclieReceptor.getId_cliente()));
        frmServi.txtNombreCliente1.setText(modelclieReceptor.getNombre_cliente());
        }
        
    }
       //=====================================================================================================================
    //SE HA DETECTADO QUE SE PRESIONO EL BOTON DE BUSCAR ID PAQUETE    
    
     else  if(e.getSource()==frmServi.btnBuscarPaquete)
    {
        
      
         formBuscarPaquetes buscaPaque= new formBuscarPaquetes(frmServi,true);
 
        buscaPaque.setVisible(true);
          if(modelpaque!= null)
        {
      //  JOptionPane.showMessageDialog(null, " el valor lleado en el jdialog es"+ valorSEDE, " ", JOptionPane.INFORMATION_MESSAGE);
        frmServi.txtIdPaquete.setText(String.valueOf(modelpaque.getPaquete_id()));
       //frmServi.txtVrUnitarioPaquete.setText(""+modelpaque.getPaquete_precio());
       // frmServi.llbl.setText(""+modelpaque.getPaquete_precio());
       frmServi.lblTotal.setText(""+modelpaque.getPaquete_precio());
        frmServi.txtnombrePaquete.setText(modelpaque.getPaquete_nombre());
        }
        
    }
     
     
     
      else  if(e.getSource()==frmServi.btnCancelar)
    {
        limpiarTxt();
    }
    
    
    
    }

      
    //=============================================================================================
           // SE HA PULSADO EL BOTON DE IR  DE PAQUETES 
    public void buscarCliente(JTextField txtIdCliente, JTextField txtNombreCliente)
   {
        ClientesModel modelServi = new ClientesModel();
        ClientesCRUD serviCRUD = new ClientesCRUD();
        
        
        
        
           //validacion de que el id del cliente no este vacion 
           if (txtIdCliente.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Por favor ingresa el id del cliente", " ", JOptionPane.WARNING_MESSAGE);

           }
           else{
              //1.se llena el modelo con el id ingresado en la caja de texto 
               modelServi.setId_cliente(Integer.parseInt(txtIdCliente.getText()));


               //2. si se encontró algun resultado los carja en los jtextfield correspondientes
               if(serviCRUD.getClienteById(modelServi))  
               { 
                   //lleva los datos a las cjas de texto
                   txtNombreCliente.setText(String.valueOf(modelServi.getNombre_cliente()));
                   
                 
               }
               else
                   JOptionPane.showMessageDialog(null, "No se encontró el cliente buscado", " cliente no encontrado", JOptionPane.ERROR_MESSAGE);        
           }
       
        
    }
    

    
    
    
    //=============================================================================================
           // funcion que busca un paquete determinado en la base de datos y lo muestra en las cajas de texto correspondientes
    public void irPaquetes()
   {
        PaquetesModel modelServi = new PaquetesModel();
        PaquetesCRUD serviCrd = new PaquetesCRUD();
        
        
        
        
           //validacion de que el id del cliente no este vacion 
           if (frmServi.txtIdPaquete.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Por favor ingresa el id del paquete", " ", JOptionPane.WARNING_MESSAGE);

           }
           else{
              //1.se llena el modelo con el id ingresado en la caja de texto 
              
               modelServi.setPaqueteId(Integer.parseInt(frmServi.txtIdPaquete.getText()));


               //2. si se encontró algun resultado los carja en los jtextfield correspondientes
               if(serviCrd.getPaquetesById(modelServi))  
               { 
                   //lleva los datos a las cjas de texto
                   frmServi.txtIdPaquete.setText(String.valueOf(modelServi.getPaquete_id()));
                
                   frmServi.txtnombrePaquete.setText(modelServi.getPaquete_nombre()); 
                          frmServi.lblTotal.setText(""+modelpaque.getPaquete_precio());
                   
                 
               }
               else
                   JOptionPane.showMessageDialog(null, "No se encontró el paquete ingresado", " cliente no encontrado", JOptionPane.ERROR_MESSAGE);        

           }
       
        
    }
    
    
  //================================================================================================
    //SE HA PRESIONADO EL BOTON DE CANCELAR
    
    
    
    
   //================================================================================== 
     
   public void  limpiarTxt()
   {
            
       //limpia las cajas de texto
       frmServi.txtIdClienteEmisor.setText("");
       frmServi.txtNombreCliente.setText("");
       frmServi.txtIdClienteReceptor.setText("");
       frmServi.txtNombreCliente1.setText("");
       frmServi.txtnombrePaquete.setText("");
      // frmServi.txtVrUnitarioPaquete.setText("");
     
       frmServi.txtDireccionServicio.setText("");
       frmServi.txtObservacionServicio.setText("");
       frmServi.txtCiudadDestino.setSelectedItem("Seleccionar");
      
   
       
}
   //=====================================================================================
   
   //carga en el jcombo todas las ciudades que estan registradas en la base de datos 
   public void cargarCiudades() 
   {
       
       
     
      //  CiudadesModel ciud= new CiudadesModel();
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
                frmServi.txtCiudadDestino.addItem(resul);

            }
       
            }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
      
            } 
        }
     }
   

   
   //======================================================================================
//FUNCIONES QUE PERMITEN LA VALIDACION DE LAS CAJAS DE TEXTO CUANDO  UNA TECLA ES PRESIONADA 
    @Override
    public void keyTyped(KeyEvent e) {
        
       
       if (e.getSource ()==frmServi.txtIdClienteEmisor||  e.getSource()==frmServi.txtIdClienteReceptor) 
            validarCaja.isNumeric(e);
       else  if (e.getSource()==frmServi.txtDireccionServicio|| e.getSource()==frmServi.txtObservacionServicio) 
            validarCaja.validarDireccion(e);
   

      
     
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
   
   
       
}