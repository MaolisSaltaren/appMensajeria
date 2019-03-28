package modelo;


//ESTA CLASE PERMITE GUARDAR EL NOMBRE DEL USUARIO QUE INICIÓ SESION EN EL SISTEMA
public class SesionModel {
    public int id_trabajador;
    public String nombre_trabajador;
    public String rol_nombre;
    public String usuario_trabajador;
    public int id_sede;
    public String sede_nombre;
    public String ciudad_sede;

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getNombre_trabajador() {
        return nombre_trabajador;
    }

    public void setNombre_trabajador(String nombre_trabajador) {
        this.nombre_trabajador = nombre_trabajador;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

    public String getUsuario_trabajador() {
        return usuario_trabajador;
    }

    public void setUsuario_trabajador(String usuario_trabajador) {
        this.usuario_trabajador = usuario_trabajador;
    }

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }

    public String getSede_nombre() {
        return sede_nombre;
    }

    public void setSede_nombre(String sede_nombre) {
        this.sede_nombre = sede_nombre;
    }

    public String getCiudad_sede() {
        return ciudad_sede;
    }

    public void setCiudad_sede(String ciudad_sede) {
        this.ciudad_sede = ciudad_sede;
    }

    

    
}
