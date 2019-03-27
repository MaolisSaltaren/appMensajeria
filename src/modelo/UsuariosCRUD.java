package modelo;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
        
        String consultaSql = "INSERT INTO tbl_trabajadores"
                + "(TRABA_ID,TRABA_NOMBRE,TRABA_TELEFONO,TRABA_USU,"
                + "TRABA_PASS,TRABA_ROL,SEDE_ID,TRAB_FECHA_NAC,TRABA_DIRECCION"
                +") VALUES (?,?,?,?,?,?,?,?,?)" ;
        
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1, usu.getId());
            ps.setString(2, usu.getNombre());
            ps.setString(3, usu.getTelefono()); 
            ps.setString(4, usu.getUsuario());
            ps.setString(5, usu.getPassword());
            ps.setString(6, usu.getNombre_rol());
            ps.setInt(7, usu.getId_sede());   
            ps.setString(8, usu.getFecha_nacimiento());
            ps.setString(9, usu.getDireccion_usuario());
           
            
            
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
        String consultaSql = "update tbl_trabajadores set \n" +
                            "TRABA_NOMBRE =?,\n" +
                            "TRABA_TELEFONO =?,\n" +
                            "TRABA_USU=?,\n" +
                            "TRABA_PASS=?,\n" +
                            "TRABA_ROL=?,\n" +
                            "SEDE_ID=?,\n" +
                            "TRAB_FECHA_NAC=?,\n" +
                            "TRABA_DIRECCION=?\n" +
                            "where TRABA_ID =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         
         
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getTelefono()); 
            ps.setString(3, usu.getUsuario());
            ps.setString(4, usu.getPassword());
            ps.setString(5, usu.getNombre_rol());
            ps.setInt(6, usu.getId_sede());   
            ps.setString(7, usu.getFecha_nacimiento());
            ps.setString(8, usu.getDireccion_usuario());
               ps.setInt(9, usu.getId());
            
            
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
        String consultaSql = "DELETE FROM tbl_trabajadores WHERE traba_ID = ? "
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
        String consultaSql = " select  * from tbl_trabajadores  where traba_id= ? and sede_id =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1, usu.getId());
            ps.setInt(2, usu.getId_sede());
            
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery();
            
            //4.llena el modelo con los datos de la bd
            if(rs.next())
            {
                usu.setId(Integer.parseInt(rs.getString("traba_id")));
                usu.setNombre(rs.getString("traba_nombre"));
                usu.setTelefono(rs.getString("traba_telefono"));
                usu.setUsuario(rs.getString("traba_usu"));
                usu.setPassword(rs.getString("traba_pass"));
                usu.setNombre_rol(rs.getString("traba_rol"));
                usu.setId(Integer.parseInt(rs.getString("sede_id")));
                usu.setFecha_nacimiento(rs.getString("trab_fecha_nac"));
                usu.setDireccion_usuario(rs.getString("traba_direccion"));
               
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
        public static ResultSet listarUsuario( String valorBuscado, int idSesion)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "select "+
                "traba_id,\n" +
                "TRABA_NOMBRE,\n" +
                "TRABA_USU,\n" +
                "TRABA_ROL,\n" +
                "TRABA_LAST_SESION,\n" +
                "TRABA_LAST_SESION from tbl_trabajadores where SEDE_ID= ?  and TRABA_NOMBRE like concat('%','"+valorBuscado+"','%')";
                      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
           // ps.setString(1, );
           
           ps.setInt(1,idSesion);
            
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
        String consultaSql= "select tbl_trabajadores.TRABA_NOMBRE,\n" +
                            " tbl_trabajadores.sede_id,\n" +
                            " tbl_trabajadores.traba_id,\n" +
                            " tbl_trabajadores.traba_rol,\n" +
                            " tbl_sedes.sedes_nombre,\n" +
                            " tbl_ciudades.CIUD_NOMBRE\n" +
                            " from tbl_trabajadores inner join tbl_sedes\n" +
                            "on tbl_trabajadores.SEDE_ID= tbl_sedes.SEDES_ID\n" +
                            " inner join tbl_ciudades on tbl_ciudades.CIUD_NOMBRE= tbl_sedes.CIUD_NOMBRE \n" +
                            "where tbl_trabajadores.TRABA_USU =?\n" +
                            " and tbl_trabajadores.TRABA_pass =?";
        
              
        String  updateHoraSesion ="update  tbl_trabajadores set traba_last_sesion = ? where traba_id=?";
  
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
                ps.setString(2,rs.getString("traba_id") );
            
                ps.execute();
                
                sesMod.setId_trabajador(Integer.parseInt(rs.getString("sede_id")));
                sesMod.setNombre_trabajador(rs.getString("traba_nombre"));
                sesMod.setId_sede(Integer.parseInt(rs.getString("sede_id")));
                sesMod.setRol_nombre(rs.getString("traba_rol"));
                sesMod.setCiudad_sede(rs.getString("ciud_nombre"));
                sesMod.setSede_nombre(rs.getString("SEDES_NOMBRE"));
                
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema señor(a) "+sesMod.getNombre_trabajador()+ " ha entrado con el rol de  "+sesMod.getRol_nombre(),"Inicio de sesion ",JOptionPane.INFORMATION_MESSAGE );
                

                return true;
                
            }
           
                    
            return false;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error al iniciar logueo, detalle del error="+e.toString());
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
    
        
        public boolean  disponibilidadUsuario(UsuariosModel usu )
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 

        //CONSULTA SQL 
        String consultaSql= "select count(SEDE_ID) as cantidad_usuario from tbl_trabajadores where traba_usu like ?";
        
              
     
          try
        {
            
            // SE BUSCA EN LA BASE DE DATOS SI EL USUARIO SE ENCUENTRA REGISTRADO
            
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setString(1,usu.getUsuario());
            
            
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery();
            
            //4.llena el modelo con los datos de la bd
            if(rs.next())
            {
                  
                 usu.setDisponibilidad_usuario(Integer.parseInt(rs.getString("cantidad_usuario")));
                return true;   
            }
           
                    
            return false;
            
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error , detalle del error="+e.toString());
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
