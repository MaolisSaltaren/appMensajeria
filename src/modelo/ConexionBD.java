
package modelo;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ConexionBD {
     
    //metodo para conecion con la bd
   
   //variables publicas
   public static  String driver;
   public static  String db;
   public static  String hostname;
   public static  String port;
   public static  String url;
   public static  String usuario;
   public static  String pass;
   
   //********************************
   
   //ESTA CLASE CONTIENE METODOS QUE CAPTURAN LA CADENA DE CONEXION  Y ESTABLECE UNA CONEXION CON LA BASE DE DATOS 
   //*******************************
   
  //metodo que lee el archivo de configuracion de la aplicacion 
   public static void obtenerCadena ()
   {
       //1.instancia del archivo de propiedades 
       Properties prop= new Properties();
       
       //2.declara objetos para lectura de archivo
       InputStream isArchivo;
       try
       {
           //3.abre el archivo
           isArchivo= new FileInputStream("src/config/appConfig.properties");
           //4.lee el archivo
           prop.load(isArchivo);
           
          //5.captura del archivo  llamado appConfig.properties toda la coniguracion de conexion con la base de datos
          // de esta manera se facilita el cambio de usuario y contraseña de forma muy rapida y segura 
          // solo hay que abrir el archivo en cuestion y realizar los respectivos ajustes
   
            driver=prop.getProperty("driver");
            db=prop.getProperty("db");
            hostname=prop.getProperty("hostname");
            port=prop.getProperty("port");
            usuario=prop.getProperty("usuario");
            pass=prop.getProperty("pass");
//            url= "jdbc:mysql://" + hostname + ":"+ port +"/"+db +"?useSSL=false";    
        url= "jdbc:mysql://" + hostname + ":"+ port +"/"+db ;    
            
           
       }
       catch(IOException e ){
           JOptionPane.showMessageDialog(null, "Error de conexion con la base de datos, descripcion= "+e.toString(),"No se pudo conectar a la base de datos",JOptionPane.ERROR_MESSAGE);      
       }

   }
   
   //--------------------------------------------------------------------------------------
   // ESTE METODO ESTABLECE UNA CONEXION CON LA BASE DE DATOS EN MYSQL
   public  static Connection getConexion()
      {  
          
     obtenerCadena();// llama al metodo  que contiene todos los parametros de conexion con la base de datos       
     Connection conn= null;
     try  
       {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,usuario,pass);  
       }
       catch (HeadlessException | ClassNotFoundException | SQLException ex ){
           ex.printStackTrace();
           JOptionPane.showMessageDialog(null, " CONEXION FALLIDA, ERROR= " +ex.toString(),"Error",JOptionPane.ERROR_MESSAGE);
       }
       return conn;

    
   }
   
}
