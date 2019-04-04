

//esta clase contiene  la ejecucion de las consultas a la base de datos para el formulario de reportes
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static modelo.ConexionBD.getConexion;


public class ReportesModel {
    

    
      //======================================================================================
    ////////////////////////////////////////////////////////////////////////////////////////
    //ListarClientes
        public static ResultSet getSeviciosDespachados( String parametro1,String parametro2)
    {
     
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "SELECT \n" +
                                "\n" +
                                "  `tbl_ciudades`.`CIUD_NOMBRE`   ,\n" +
                                "  `tbl_trabajadores`.`TRABA_NOMBRE`,\n" +
                                "  `tbl_paquetes`.`PAQUE_NOMBRE`,\n" +
                                "  `tbl_servicios`.`SERVI_FECHA`,\n" +
                                "  `tbl_servicios`.`TRABA_ID`,\n" +
                                "  `tbl_servicios`.`SERVI_ESTADO`,\n" +
                                "  `tbl_servicios`.`SERVI_DIRECCION`,\n" +
                                "  `tbl_servicios`.`SERV_CIUDAD_DESTINO`,\n" +
                                "  `tipo_cliente`.`TICI_NOMBRE`,\n" +
                                "  `tbl_clientes`.`CLIE_NOMBRE`,\n" +
                                "  `tbl_servicios`.`SERVI_ID`\n" +
                                "FROM\n" +
                                "  `tbl_sedes`\n" +
                                "  INNER JOIN `tbl_ciudades` ON (`tbl_sedes`.`CIUD_NOMBRE` = `tbl_ciudades`.`CIUD_NOMBRE`)\n" +
                                "  INNER JOIN `tbl_trabajadores` ON (`tbl_sedes`.`SEDES_ID` = `tbl_trabajadores`.`SEDE_ID`)\n" +
                                "  INNER JOIN `tbl_servicios` ON (`tbl_trabajadores`.`TRABA_ID` = `tbl_servicios`.`TRABA_ID`)\n" +
                                "  INNER JOIN `tbl_clien_servicios` ON (`tbl_servicios`.`SERVI_ID` = `tbl_clien_servicios`.`SERVI_ID`)\n" +
                                "  INNER JOIN `tbl_clientes` ON (`tbl_clien_servicios`.`CLIE_ID` = `tbl_clientes`.`CLIE_ID`)\n" +
                                "  INNER JOIN `tbl_paquetes` ON (`tbl_servicios`.`PAQUE_ID` = `tbl_paquetes`.`PAQUE_ID`)\n" +
                                "  INNER JOIN `tipo_cliente` ON (`tbl_clien_servicios`.`TICI_ID` = `tipo_cliente`.`TICI_ID`)\n" +
                                " where  tbl_ciudades.CIUD_NOMBRE like concat('%', ?,'%') and\n" +
                                "tbl_servicios.SERVI_ESTADO  like concat('%', ?,'%') " +
                                "group by \n" +
                                "  `tbl_servicios`.`SERVI_ID`";

            try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
               ps.setString(1,parametro1);
            ps.setString(2,parametro2);
         
           
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
    //muestra la cantidad de clientes por ciudad
        
        public String  getclientesCiudad( String parametro1)
    {
        String resultado="";
        PreparedStatement ps = null;
        Connection con=getConexion();
        ResultSet rs = null ; 
        
        
        //CONSULTA SQL 
            String consultaSql = "select  count(tbl_clientes.CLIE_ID) as cantidadClientes from tbl_clientes "
                                 + "inner join tbl_ciudades on \n" +
                                "tbl_ciudades.CIUD_NOMBRE=tbl_clientes.CIUD_NOMBRE\n" +
                                "group by tbl_ciudades.CIUD_NOMBRE having tbl_ciudades.CIUD_NOMBRE = ?";

            try
        {
            //1. se manda a preparar la consulta
            ps = getConexion().prepareStatement(consultaSql);
            
            //2.se le enbian los parametros a la consulta sql
               ps.setString(1,parametro1);
           
         
           
         //  ps.setInt(1,idSesion);
            
             //.3 se ejecuta la consulta 
            rs= ps.executeQuery();
            
            if(rs.next())
            {
                resultado = rs.getString("cantidadClientes");
                 
                return resultado;
            }
            else
                return "";
            
            //4.llena el modelo con los datos de la bd
          

                
        }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null,"Error"+e.toString());
            return "";
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
