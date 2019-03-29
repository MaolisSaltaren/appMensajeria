package modelo;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ClientesCRUD extends ConexionBD{// herencia de la clase conecion 
    
    //***************************************
    //EN ESTA CLASE SE ENCUENTRAN TODOS LOS METODOS PARA REALIZAR EL CRUD DE LA TABLA CLIENTES
    //*****************************************
    
 //*************************************************************************************************   
    //METODO  DE INSERTAR 
    public boolean insertarCliente(ClientesModel clie)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        String consultaSql = "INSERT INTO tbl_clientes"
                + "(clie_id,clie_nombre,clie_telefono,clie_fecha_na,"
                + "clie_direccion,ciud_nombre"
                +") VALUES (?,?,?,?,?,?)" ;
        
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1, clie.getId_cliente());
            ps.setString(2, clie.getNombre_cliente());
            ps.setString(3, clie.getTelefono_cliente()); 
            ps.setString(4, clie.getFecha_nac_cliente());
            ps.setString(5, clie.getDireccion_cliente());
            ps.setString(6, clie.getCiudad_cliente());
            
           
            
            
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
        public boolean actualizarCliente(ClientesModel clie)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "update tbl_clientes set \n" +
                            "CLIE_NOMBRE =?,\n" +
                            "CLIE_TELEFONO =?,\n" +
                            "CLIE_FECHA_NA=?,\n" +
                            "CLIE_DIRECCION=?,\n" +
                            "CIUD_NOMBRE=?\n" +
                            "where CLIE_ID =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         
         
            ps.setString(1, clie.getNombre_cliente());
            ps.setString(2, clie.getTelefono_cliente()); 
            ps.setString(3, clie.getFecha_nac_cliente());
            ps.setString(4, clie.getDireccion_cliente());
            ps.setString(5, clie.getCiudad_cliente());
               ps.setInt(6, clie.getId_cliente());
            
            
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
        public boolean eliminarCliente(ClientesModel clie)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "DELETE FROM tbl_clientes WHERE clie_id = ? "
               ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
         
            ps.setInt(1, clie.getId_cliente());
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
    //METODO DE BUSCAR
        public boolean getClienteById(ClientesModel clie)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
        String consultaSql = " select  * from tbl_clientes  where clie_id= ?" ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1, clie.getId_cliente());
          
            
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery();
            
            //4.llena el modelo con los datos de la bd
            if(rs.next())
            {
                clie.setId_cliente(Integer.parseInt(rs.getString("clie_id")));
                clie.setNombre_cliente(rs.getString("clie_nombre"));
                clie.setTelefono_cliente(rs.getString("clie_telefono"));  
                clie.setFecha_nac_cliente(rs.getString("clie_fecha_na"));
                clie.setDireccion_cliente(rs.getString("clie_direccion"));
                clie.setCiudad_cliente(rs.getString("ciud_nombre"));
               
        return true;
                
            }
           
                    
            return false;
            
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
    //ListarClientes
        public static ResultSet getAllCliente( String campo,String valorBuscado)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "select "+
                "clie_id,\n" +
                "clie_nombre,\n" +
                "clie_telefono,\n" +
                "clie_fecha_na,\n" +
                "clie_direccion,\n" +
                "ciud_nombre\n" +
         
                    "from tbl_clientes where \n "
                    + campo +" \n like concat('%','"+valorBuscado+"','%')";
            
            try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
           // ps.setString(1, );
           
         //  ps.setInt(1,idSesion);
            
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery();
            
            //4.llena el modelo con los datos de la bd
          
            return rs;
                
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return null;
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
