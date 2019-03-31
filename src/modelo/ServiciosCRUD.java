package modelo;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;

public class ServiciosCRUD extends ConexionBD{// herencia de la clase conecion 
    
    //***************************************
    //EN ESTA CLASE SE ENCUENTRAN TODOS LOS METODOS PARA REALIZAR EL CRUD DE LA TABLA SERVICIOS
    //*****************************************
    
 //*************************************************************************************************   
    //METODO  DE INSERTAR 
    public boolean insertarServicios(ServiciosModel servi,SesionModel sesion)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        String consultaSql = "INSERT INTO TBL_SERVICIOS"
                + "(SERVI_FECHA,TRABA_ID,PAQUE_ID,"
                + "SERVI_ESTADO,SERVI_DIRECCION,SERV_CIUDAD_DESTINO"
                +") VALUES (?,?,?,?,?,?)" ;
        
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setString(1, servi.servi_fecha_entrega);
            ps.setInt(2, sesion.getId_trabajador());
            ps.setInt(3, servi.servi_paque_id);
            ps.setString(4, servi.getServi_estado());
            ps.setString(5, servi.getServi_direccion());
            ps.setString(6, servi.getServi_ciudad_destino());
          
            
            
            //.3 se ejecuta la consulta 
            ps.execute();
            
            return true;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return false;
        } 
        finally
        {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Error"+e.toString());
            } 

        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    //METODO DE ACTUALIZAR
        public boolean actualizarServicios(ServiciosModel servi)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "update tbl_servicios set \n" +
                            "paque_id=?,"+
                            "servi_direccion =?,\n" +
                            "servi_ciudad_destino=?\n" +
                            "where SERVI_ID =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         
         
            ps.setInt(1, servi.getServi_paque_id());
            ps.setString(2, servi.getServi_direccion()); 
            ps.setString(3, servi.getServi_ciudad_destino());
            ps.setInt(4, servi.getServi_id());
            
            
            //.3 se ejecuta la consulta 
            ps.execute();
            
            return true;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return false;
        } 
        finally
        {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Error"+e.toString());
            } 

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    //METODO DE ELIMINAR
        public boolean eliminarServicios(ServiciosModel servi)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "DELETE FROM tbl_servicios  WHERE servi_id = ? "
               ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
         
            ps.setInt(1, servi.getServi_id());
            //.3 se ejecuta la consulta 
            ps.execute();
            
            return true;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return false;
        } 
        finally
        {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Error"+e.toString());
            } 

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////
   
        ////////////////////////////////////////////////////////////////////////////////////
        
        //METODO QUE ACTUALIZA EL ESTADO DEL SERVICIOS ES DEVIR SI SE DESPACHO O SE ENTREGO 
 public  boolean actualizarEstado(ServiciosModel servi)
 {
      PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "update tbl_servicios set \n" +
                            "servi_estado=?\n" +
                            "where SERVI_ID =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         

           ps.setString(1,servi.getServi_estado()); 
           ps.setInt(2, servi.getServi_paque_id());


            
            //.3 se ejecuta la consulta 
            ps.execute();
            
            return true;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return false;
        } 
        finally
        {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Error"+e.toString());
            } 

        }
     
 }
 
   //======================================================================================
   //CARGA EL ID DEL ULTIMO SERVICIO DE LOS SERVICIOS +1
    public boolean selectMax(ServiciosModel ses) {        
        Connection con = getConexion();
        Statement st = null;
        ResultSet rs = null;

        String ConsultaSql = "select  max(SERVI_ID)+1 as maxServicio from TBL_SERVICIOS";
        try {
             st = con.createStatement();
             rs = st.executeQuery(ConsultaSql);
            // si se ejecuta la consulta y encuentra algo en ella 
            if (rs.first()) {
               // llena  el campo correspondiente al modelo
               ses.setServi_id(rs.getInt("maxServicio"));
               return true;
            }            
         }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return false;
        } 
        //cierra la conexion cn la bd 
        finally
        {
            try{
             con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Error"+e.toString());
            } 

        }        
      return false;
    }
  
        //==========================================================
        //ESTE METODO BUSCA SI SE ENCUENTRA DISPONIBLE O NO EL NOMBRE DE USUARIO DE UN EMPLEADO
  
 

         //*************************************************************************************************   
    //METODOS  DE LA TABLA CLIENTES POR SERVICIOS (TBL_CLIE_SERVI)
        
        //METODO  DE INSERTAR
    public boolean insertarServiCliente(ServiciosModel servi,int  id_Cliente,int tipoCliente)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        String consultaSql = "INSERT INTO TBL_CLIEN_SERVICIOS"
                + "(CLIE_ID,SERVI_ID,TICI_ID"
                +") VALUES (?,?,?)" ;
        
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1,id_Cliente);
            ps.setInt(2, servi.getServi_id());
            ps.setInt(3,tipoCliente);
           
            
            //.3 se ejecuta la consulta 
            ps.execute();
            
            return true;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return false;
        } 
        finally
        {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Error"+e.toString());
            } 

        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////
    //METODO DE ACTUALIZAR
        public boolean actualizarServiCliente(ServiciosModel servi)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "update tbl_clien_servicios set \n" +
                            "clie_id=?,"+
                            "tici_id=?,\n" +
                            "where SERVI_ID =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         
         
            ps.setInt(1, servi.getServi_paque_id());
            ps.setString(2, servi.getServi_direccion()); 
            ps.setString(3, servi.getServi_ciudad_destino());
            ps.setInt(4, servi.getServi_id());
            
            
            //.3 se ejecuta la consulta 
            ps.execute();
            
            return true;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return false;
        } 
        finally
        {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Error"+e.toString());
            } 

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    //METODO DE ELIMINAR
        public boolean eliminarServiCliente(ServiciosModel servi)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "DELETE FROM tbl_clien_servicios  WHERE servi_id = ? and clie_id =? "
               ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
         
            ps.setInt(1, servi.getServi_id());
            //.3 se ejecuta la consulta 
            ps.execute();
            
            return true;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return false;
        } 
        finally
        {
            try{
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Error"+e.toString());
            } 

        }
    }
        
    
    
    
    
}
