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
                            "servi_estado=?,\n" +
                            "servi_fecha_entrega=?\n" +
                            "where SERVI_ID =?";
      try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le envian los parametros a la consulta sql
         

           ps.setString(1,servi.getServi_estado()); 
           ps.setString(2,servi.getServi_fecha_entrega()); 
           ps.setInt(3, servi.getServi_id());


            
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
 
   //======================================================================================
    ////////////////////////////////////////////////////////////////////////////////////////
    //ListarClientes
        public static ResultSet getServcioNoEntregado( String campo,String estado_servicio)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "SELECT \n" +
"  `tbl_servicios`.`SERVI_ID`,\n" +
"  `tbl_servicios`.`SERV_CIUDAD_DESTINO`,\n" +
"  `tbl_ciudades`.`CIUD_NOMBRE`,\n" +
"  `tbl_CLIENTES`.`CLIE_ID`,\n" +
"  `tbl_paquetes`.`PAQUE_NOMBRE`,\n" +
"  `tbl_clientes`.`CLIE_NOMBRE`\n" +
"FROM\n" +
"  `tbl_ciudades`\n" +
"  INNER JOIN `tbl_sedes` ON (`tbl_ciudades`.`CIUD_NOMBRE` = `tbl_sedes`.`CIUD_NOMBRE`)\n" +
"  INNER JOIN `tbl_trabajadores` ON (`tbl_sedes`.`SEDES_ID` = `tbl_trabajadores`.`SEDE_ID`)\n" +
"  INNER JOIN `tbl_servicios` ON (`tbl_trabajadores`.`TRABA_ID` = `tbl_servicios`.`TRABA_ID`)\n" +
"  INNER JOIN `tbl_clien_servicios` ON (`tbl_servicios`.`SERVI_ID` = `tbl_clien_servicios`.`SERVI_ID`)\n" +
"  INNER JOIN `tipo_cliente` ON (`tbl_clien_servicios`.`TICI_ID` = `tipo_cliente`.`TICI_ID`)\n" +
"  INNER JOIN `tbl_paquetes` ON (`tbl_servicios`.`PAQUE_ID` = `tbl_paquetes`.`PAQUE_ID`)\n" +
"  INNER JOIN `tbl_clientes` ON (`tbl_clien_servicios`.`CLIE_ID` = `tbl_clientes`.`CLIE_ID`)\n" +
"WHERE\n" +
"  `tbl_clien_servicios`.`CLIE_ID` like  concat('%', ?, '%' ) AND \n" +
"  `tipo_cliente`.`TICI_ID` = 2 AND \n" +
"  `tbl_servicios`.`SERVI_ESTADO` = ? \n" +
"GROUP BY\n" +
"  tbl_servicios.SERVI_ID,\n" +
"  tbl_servicios.SERV_CIUDAD_DESTINO,\n" +
"  tbl_servicios.SERVI_ESTADO,\n" +
"  tbl_ciudades.CIUD_NOMBRE,\n" +
"  tbl_sedes.SEDES_NOMBRE";

            try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
               ps.setString(1,campo);
            ps.setString(2,estado_servicio);
         
           
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
 
   //======================================================================================
    ////////////////////////////////////////////////////////////////////////////////////////
    //ListarClientes
        public static ResultSet getServciosEntregados( String campo)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "SELECT \n" +
                                "  `tbl_servicios`.`SERVI_ID`,\n" +
                                "  `tbl_servicios`.`SERV_CIUDAD_DESTINO`,\n" +
                                "  `tbl_ciudades`.`CIUD_NOMBRE` ,\n" +
                                "  `tbl_paquetes`.`PAQUE_NOMBRE`,\n" +
                                "  `tbl_clientes`.`CLIE_NOMBRE`\n" +
                                "FROM\n" +
                                "  `tbl_ciudades`\n" +
                                "  INNER JOIN `tbl_sedes` ON (`tbl_ciudades`.`CIUD_NOMBRE` = `tbl_sedes`.`CIUD_NOMBRE`)\n" +
                                "  INNER JOIN `tbl_trabajadores` ON (`tbl_sedes`.`SEDES_ID` = `tbl_trabajadores`.`SEDE_ID`)\n" +
                                "  INNER JOIN `tbl_servicios` ON (`tbl_trabajadores`.`TRABA_ID` = `tbl_servicios`.`TRABA_ID`)\n" +
                                "  INNER JOIN `tbl_clien_servicios` ON (`tbl_servicios`.`SERVI_ID` = `tbl_clien_servicios`.`SERVI_ID`)\n" +
                                "  INNER JOIN `tipo_cliente` ON (`tbl_clien_servicios`.`TICI_ID` = `tipo_cliente`.`TICI_ID`)\n" +
                                "  INNER JOIN `tbl_paquetes` ON (`tbl_servicios`.`PAQUE_ID` = `tbl_paquetes`.`PAQUE_ID`)\n" +
                                "  INNER JOIN `tbl_clientes` ON (`tbl_clien_servicios`.`CLIE_ID` = `tbl_clientes`.`CLIE_ID`)\n" +
                                "WHERE\n" +
                                "  `tbl_servicios`.`SERVI_ESTADO` = 'Ingresado a  Bodega' AND \n" +
                                "  `tbl_clien_servicios`.`CLIE_ID` = ? AND \n" +
                                "  `tipo_cliente`.`TICI_NOMBRE` = 'Cliente Receptor'\n" +
                                "GROUP BY\n" +
                                "  tbl_servicios.SERVI_ID,\n" +
                                "  tbl_servicios.SERV_CIUDAD_DESTINO,\n" +
                                "  tbl_servicios.SERVI_ESTADO,\n" +
                                "  tbl_ciudades.CIUD_NOMBRE,\n" +
                                "  tbl_sedes.SEDES_NOMBRE";

            try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
            ps.setInt(1,Integer.parseInt(campo));
           
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
//        public boolean actualizarServiCliente(ServiciosModel servi)
//    {
//     
//        PreparedStatement ps = null;
//        Connection con=getConexion();
//        
//        
//        //CONSULTA SQL 
//        String consultaSql = "update tbl_clien_servicios set \n" +
//                            "clie_id=?,"+
//                            "tici_id=?,\n" +
//                            "where SERVI_ID =?";
//      try
//        {
//            //1. se manda a preparar la consulta
//            ps = getConexion().prepareStatement(consultaSql);
//            
//            //2.se le envian los parametros a la consulta sql
//         
//         
//            ps.setInt(1, servi.getServi_paque_id());
//            ps.setString(2, servi.getServi_direccion()); 
//            ps.setString(3, servi.getServi_ciudad_destino());
//            ps.setInt(4, servi.getServi_id());
//            
//            
//            //.3 se ejecuta la consulta 
//            ps.execute();
//            
//            return true;
//            
//        }catch(SQLException e){
//           
//            JOptionPane.showMessageDialog(null,"Error"+e.toString());
//            return false;
//        } 
//        finally
//        {
//            try{
//                con.close();
//            }
//            catch(SQLException e)
//            {
//                JOptionPane.showMessageDialog(null,"Error"+e.toString());
//            } 
//
//        }
//    }
    ////////////////////////////////////////////////////////////////////////////////////////
    //METODO DE ELIMINAR
//        public boolean eliminarServiCliente(ServiciosModel servi)
//    {
//     
//        PreparedStatement ps = null;
//        Connection con=getConexion();
//        
//        
//        //CONSULTA SQL 
//        String consultaSql = "DELETE FROM tbl_clien_servicios  WHERE servi_id = ? and clie_id =? "
//               ;
//      try
//        {
//            //1. se manda a preparar la consulta
//            ps = getConexion().prepareStatement(consultaSql);
//            
//            //2.se le enbian los parametros a la consulta sql
//         
//            ps.setInt(1, servi.getServi_id());
//            //.3 se ejecuta la consulta 
//            ps.execute();
//            
//            return true;
//            
//        }catch(SQLException e){
//           
//            JOptionPane.showMessageDialog(null,"Error"+e.toString());
//            return false;
//        } 
//        finally
//        {
//            try{
//                con.close();
//            }
//            catch(SQLException e)
//            {
//                JOptionPane.showMessageDialog(null,"Error"+e.toString());
//            } 
//
//        }
//    }
//        
    
    
    
    
}
