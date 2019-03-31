
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import misClases.Validacion;
import modelo.ServiciosModel;
import modelo.ServiciosCRUD;
import modelo.CiudadesCRUD;
import modelo.ClientesCRUD;
import modelo.ClientesModel;
import modelo.SesionModel;
import vista.formServicios;

public class ServiciosController implements ActionListener,KeyListener,MouseListener{
    
     private final ServiciosModel modelServicio;
     private final SesionModel modelSesion;
     private final formServicios frmServi;
     private final ServiciosCRUD serviCRUD;
     

 
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
        this.frmServi.btnBuscarClienteEmisor.addActionListener(this);
        this.frmServi.btnBuscarClienteReceptor.addActionListener(this);
        this.frmServi.btnBuscarPaquete.addActionListener(this);
        
//        this.frmServi.btnCancelarSedes.addActionListener(this);
//        this.frmServi.btnEliminarSedes.addActionListener(this);
//        this.frmServi.btnActualizarSedes.addActionListener(this);
//        this.frmServi.btnBuscarSedes.addActionListener(this);
//        this.frmServi.popoDetallle.addActionListener(this);
//     
        
        //coloca los texbox a la escucha del evento de presionar un boton 
       // this.frmServi.txtIdPaquete.addKeyListener(this);
        this.frmServi.txtIdClienteEmisor.addKeyListener(this);
        this.frmServi.txtIdClienteReceptor.addKeyListener(this);
        this.frmServi.txtIdPaquete.addKeyListener(this);
        this.frmServi.txtDireccionServicio.addKeyListener(this);
        this.frmServi.txtObservacionServicio.addKeyListener(this);
      
//        this.frmServi.txtBusquedaSedes.addKeyListener(this);
//        
//        this.frmServi.jTableSedes.addMouseListener(this);
//        

        
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
          
       
        
   
    }
    
    //pone los botones a  la escucha 

    @Override
    public void actionPerformed(ActionEvent e) {
    
   //1.SE HA PULSADO EL BOTON GUARDAR PAQUETES 
    if (e.getSource()== frmServi.btnGuardarServicios)
    {
       
        
//    1. verifimamos de que los campos de texto no esten vacios
         if(frmServi.txtIdClienteEmisor.getText().equals("") ||
            frmServi.txtIdClienteReceptor.getText().equals("") )
         
                  JOptionPane.showMessageDialog(null, "Los campos de texto no pueden estar vacios","Advertencia",JOptionPane.WARNING_MESSAGE);

          else if(frmServi.txtCiudadDestino.getSelectedItem().equals("Seleccionar") )
                 JOptionPane.showMessageDialog(null, "Debe elegir una ciudad destino ", " ", JOptionPane.INFORMATION_MESSAGE);
         
       
  
         else 
         
         {
             
                //2.agarra la hora del sistema y se la asigna al modelo  de servicios 
                        Date fecha = new Date();
                       DateFormat fechaHoraSistema = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

               // 3. se llena el modelo de servicios

               modelServicio.setServi_fecha(fechaHoraSistema.format(fecha).toString()); // llena el modelo con la fecha actual del sistema
               modelServicio.setServi_traba_id(modelSesion.getId_trabajador());// agarra el id del empleado del modelo se sesion con la que  ingreso el trabajador
               modelServicio.setServi_paque_id(Integer.parseInt(frmServi.txtIdPaquete.getText()));
               JOptionPane.showMessageDialog(null, "id_servicio="+ modelServicio.getServi_id(), " ", JOptionPane.INFORMATION_MESSAGE);
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
    
    
    
    
    
    }

      
    //=============================================================================================
           // SE HA PULSADO EL BOTON DE BUSCAR
    public void buscarCliente(JTextField txtIdCliente, JTextField txtNombreCliente)
   {
        ClientesModel modelClie = new ClientesModel();
        ClientesCRUD clieCRUD = new ClientesCRUD();
        
        
        
        
           //validacion de que el id del cliente no este vacion 
           if (txtIdCliente.getText().equals("")) {
               JOptionPane.showMessageDialog(null, "Por favor ingresa el id del cliente", " ", JOptionPane.WARNING_MESSAGE);

           }
           else{
              //1.se llena el modelo con el id ingresado en la caja de texto 
               modelClie.setId_cliente(Integer.parseInt(txtIdCliente.getText()));


               //2. si se encontró algun resultado los carja en los jtextfield correspondientes
               if(clieCRUD.getClienteById(modelClie))  
               { 
                   //lleva los datos a las cjas de texto
                   txtNombreCliente.setText(String.valueOf(modelClie.getNombre_cliente()));
                   
                 
               }
               else
                   JOptionPane.showMessageDialog(null, "No se encontró el cliente buscado", " cliente no encontrado", JOptionPane.ERROR_MESSAGE);        
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
       frmServi.txtVrUnitarioPaquete.setText("");
       frmServi.txtCantidadPaquetes.setText("");
       frmServi.txtDireccionServicio.setText("");
       frmServi.txtObservacionServicio.setText("");
       frmServi.txtCiudadDestino.setSelectedItem("Seleccionar");
      
   
       
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
       
}