package modelo;


//ESTA CLASE PERMITE GUARDAR EL NOMBRE DEL USUARIO QUE INICIÓ SESION EN EL SISTEMA
public class SesionModel {
    public String nombre_usuario;
    public String rol_nombre;

    

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

  
    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }
   
    
    
}
