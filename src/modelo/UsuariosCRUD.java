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
        String consultaSql = "DELETE FROM USUARIOS WHERE ID = ? "
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
                usu.setTelefono(rs.getString("telefono"));
                
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
    //ListarUsuarios
        public static ResultSet listarUsuario( String valorBuscado)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
        String consultaSql = "select usuarios.id as id_usuario,usuarios.nombre as nombre_usuario,roles.id as id_rol,roles.nombre as nombre_rol from roles inner join usuarios on usuarios.id_rol = roles.id where usuarios.nombre like concat('%','"+valorBuscado+"','%');";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
           // ps.setString(1, "");
            
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
           ////////////////////////////////////////////////////////////////////////////////////////
    //METODO DE logueo del usuario 
        public boolean loginUsuario(UsuariosModel usu, SesionModel sesMod)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
      //  SesionModel sesMod= new SesionModel();
       
        
        
        
        //CONSULTA SQL 
        String consultaSql = " select usuarios.id as id_usuario,usuarios.nombre as nombre_usuario, roles.nombre as nombre_rol from usuarios inner join roles on usuarios.id_rol = roles.id where usuarios.usuario= ? and usuarios.password=?";
         
        String  updateHoraSesion ="update  usuarios set last_sesion = ? where id=?";
  
          try
        {
            
            // SE BUSCA EN LA BASE DE DATOS SI EL USUARIO SE ENCUENTRA REGISTRADO
            
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
               //ACTUALIZA LA FECHA Y HORA EN QUE INRESO EL USUARIO AL SISTEMA
                ps= getConexion().prepareStatement(updateHoraSesion);
                ps.setString(1,usu.getUltimaSesion());
                ps.setString(2,rs.getString("id_usuario") );
                
                ps.execute();
                
                sesMod.setNombre_usuario(rs.getString("nombre_usuario"));
                sesMod.setRol_nombre(rs.getString("nombre_rol"));
                
                usu.setNombre(rs.getString("nombre_usuario"));
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema señor(a) "+sesMod.getNombre_usuario()+ " ha entrado con el rol de  "+sesMod.getRol_nombre(),"Inicio de sesion ",JOptionPane.INFORMATION_MESSAGE );
                

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
