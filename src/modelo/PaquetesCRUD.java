package modelo;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;


public class PaquetesCRUD extends ConexionBD{// herencia de la clase conecion 
    
    //***************************************
    //EN ESTA CLASE SE ENCUENTRAN TODOS LOS METODOS PARA REALIZAR EL CRUD DE LA TABLA PAQUETES
    //*****************************************
    
 //*************************************************************************************************   
    //METODO  DE INSERTAR 
    public boolean insertarPaquete(PaquetesModel paque)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        String consultaSql = "INSERT INTO tbl_paquetes"
                + "(PAQUE_NOMBRE,PAQUE_DESCRIPCION,PAQUE_PRECIO)"
                +"VALUES (?,?,?)" ;
        
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
 
            ps.setString(1, paque.getPaquete_nombre());
            ps.setString(2, paque.getPaquete_descripcion());
            ps.setDouble(3, paque.getPaquete_precio());
            
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
        public boolean actualizarPaquete(PaquetesModel paque)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        //CONSULTA SQL 
        String consultaSql = "update tbl_paquetes set \n" +
                            
                            "paque_nombre =?,\n" +
                            "paque_precio =?,\n" +
                            "paque_descripcion=?\n" +
                            "where paque_id =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         
         
           
            ps.setString(1, paque.getPaquete_nombre());
            ps.setDouble(2, paque.getPaquete_precio()); 
            ps.setString(3, paque.getPaquete_descripcion());

            ps.setInt(4, paque.getPaquete_id());
            
            
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
        public boolean eliminarPaquete(PaquetesModel paque)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        
        
        
        //CONSULTA SQL 
        String consultaSql = "DELETE FROM tbl_paquetes WHERE PAQUE_id = ? "
               ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
         
            ps.setInt(1, paque.getPaquete_id());
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
   public boolean getPaquetesById (PaquetesModel paque)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
        String consultaSql = " select *from tbl_paquetes where paque_id=?" ;
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            ps.setInt(1,paque.getPaquete_id());
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery(); 
            
          //4.llena el modelo con los datos de la bd
            if(rs.next())
            {
                paque.setPaqueteId(Integer.parseInt(rs.getString("paque_id")));
                paque.setPaquete_precio(Double.parseDouble(rs.getString("paque_precio")));
                paque.setPaquete_nombre(rs.getString("paque_nombre"));
                paque.setPaquete_descripcion(rs.getString("paque_descripcion"));  
       
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
        public static ResultSet getPaquetesByNombre( String valorBuscado)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "select "+
                "PAQUE_ID,\n" +
                "PAQUE_NOMBRE,\n" +
                "PAQUE_DESCRIPCION,\n" +
                "PAQUE_PRECIO\n" +
                    "from TBL_PAQUETES where  "
                    + " PAQUE_NOMBRE like concat('%','"+valorBuscado+"','%')";
            
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
