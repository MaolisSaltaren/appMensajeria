package modelo;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;

public class UsuariosCRUD extends ConexionBD{// herencia de la clase conecion 
    
    //***************************************
    //EN ESTA CLASE SE ENCUENTRAN TODOS LOS METODOS PARA REALIZAR EL CRUD DE LA TABLA USUARIOS 
    //*****************************************
    
 //*************************************************************************************************   
    //METODO  DE INSERTAR 
    public boolean insertarUsuario(UsuariosModel usu)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        String consultaSql = "insert into usuarios (id,nombre,id_rol,telefono,correo,usuario,password)"
                + "values (?,?,?,?,?,?,?)" ;
        
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1, usu.getId());
            ps.setString(2, usu.getNombre());
            ps.setInt(3, usu.getId_rol());
            ps.setString(4, usu.getTelefono());
            ps.setString(5, usu.getCorreo());
            ps.setString(6, usu.getUsuario());
            ps.setString(7, usu.getPassword());
            
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
        public boolean actualizarUsuario(UsuariosModel usu)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = " UPDATE USUARIOS set NOMBRE=? ,TELEFONO =?,CORREO =?,"
                + " USUARIO=?, PASSWORD=?"
                + "WHERE ID = ?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getTelefono());
            ps.setString(3, usu.getCorreo());
            ps.setString(4, usu.getUsuario());
            ps.setString(5, usu.getPassword());
            ps.setInt(6, usu.getId());
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
        public boolean eliminarUsuario(UsuariosModel usu)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "DELETe FROM USUARIOS WHERE ID = ? "
               ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
         
            ps.setInt(1, usu.getId());
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
        public boolean buscarUsuario(UsuariosModel usu)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
        String consultaSql = "select * from usuarios where id =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1, usu.getId());
            
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery();
            
            //4.llena el modelo con los datos de la bd
            if(rs.next())
            {
                usu.setId(Integer.parseInt(rs.getString("id")));
                usu.setId_rol(Integer.parseInt(rs.getString("id_rol")));
                usu.setNombre(rs.getString("nombre"));
                usu.setCorreo(rs.getString("correo"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setPassword(rs.getString("password"));
                
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
    //METODO DE logueo del usuario 
        public boolean loginUsuario(UsuariosModel usu)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
        String consultaSql = "select * from usuarios where usuario =? and password = ?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setString(1, usu.getUsuario());
            ps.setString(2, usu.getPassword());
            
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery();
            
            //4.llena el modelo con los datos de la bd
            if(rs.next())
            {
                usu.setId(Integer.parseInt(rs.getString("id")));
                usu.setId_rol(Integer.parseInt(rs.getString("id_rol")));
                usu.setNombre(rs.getString("nombre"));
                usu.setCorreo(rs.getString("correo"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setPassword(rs.getString("password"));
                
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
    
        
    
    
    
    
}
