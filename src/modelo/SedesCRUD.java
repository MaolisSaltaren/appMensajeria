/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static modelo.ConexionBD.getConexion;

public class SedesCRUD {
    
    
     //*************************************************************************************************   
    //METODO  DE INSERTAR 
    public boolean insertarSede(SedesModel sede)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        String consultaSql = "INSERT INTO tbl_sedes"
                + "(sedes_id,sedes_nombre,sedes_direccion,ciud_nombre)"
                +"VALUES (?,?,?,?)" ;
        
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1, sede.getSede_id());
            ps.setString(2, sede.getSede_nombre());
            ps.setString(3, sede.getSede_direccion());
            ps.setString(4, sede.getSede_ciudad());
          
            
            
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
        public boolean actualizarSede(SedesModel sede)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "update tbl_sedes set \n" +
                            "sedes_nombre =?,\n" +
                            "sedes_direccion =?,\n" +
                            "ciud_nombre =?\n" +
                            "where sedes_id =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         
         
            ps.setString(1, sede.getSede_nombre());
            ps.setString(2, sede.getSede_direccion()); 
            ps.setString(3, sede.getSede_ciudad()); 
            ps.setInt(4, sede.getSede_id()); 
         
            
            
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
        public boolean eliminarSede(SedesModel sede)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        
        //CONSULTA SQL 
        String consultaSql = "DELETE FROM tbl_sedes WHERE sedes_id = ? "
               ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
         
            ps.setInt(1, sede.getSede_id());
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
    //===================================================================================
        //busca una sede en particular
          ////////////////////////////////////////////////////////////////////////////////////////
    //METODO DE BUSCAR
        public boolean getSedesById(SedesModel sede)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
        String consultaSql = " select  * from tbl_sedes  where sedes_id= ?" ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1, sede.getSede_id());
          
            
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery();
            
            //4.llena el modelo con los datos de la bd
            if(rs.next())
            {
                sede.setSede_id(Integer.parseInt(rs.getString("sedes_id")));
                sede.setSede_ciudad(rs.getString("ciud_nombre"));
                sede.setSede_direccion(rs.getString("sedes_direccion"));  
                sede.setSede_nombre(rs.getString("sedes_nombre"));
             
               
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
    //Lista las sedes por id
        public static ResultSet buscarSedesById( String valorBuscado)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "select * from tbl_sedes where sedes_id like concat('%','"+valorBuscado+"','%')";
         try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
           // ps.setString(1, );
           
         //  ps.setInt(1,Integer.parseInt(valorBuscado));
            
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
    
        
        // ==========================================
        //busca una sede por el nombre
        public static ResultSet buscarSedesByNombre( String valorBuscado)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "select * from tbl_sedes where sedes_nombre like concat('%','"+valorBuscado+"','%')";
         try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
           // ps.setString(1, );
           
         //  ps.setInt(1,Integer.parseInt(valorBuscado));
            
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
