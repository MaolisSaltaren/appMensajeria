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
    
     ////////////////////////////////////////////////////////////////////////////////////////
    //ListarUsuarios
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
    
        public static ResultSet buscarSedesByNombre( String valorBuscado)
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
    
}
