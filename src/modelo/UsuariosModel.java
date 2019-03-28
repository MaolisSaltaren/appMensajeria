
package modelo;


//Atributos de clase para el modelo de usuarios en el sistema 

public class UsuariosModel {
    
    
    public int id;
    public int id_sede;
    
    public String fecha_nacimiento;
    public String nombre;
    public String telefono;
    public String usuario;
    public String nombre_rol;
    public String password;
    public String ultimaSesion;
    public String direccion_usuario;
    public int disponibilidad_usuario;

    public String getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(String ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    
    public int getId() {
        return id;
    }

    public String getDireccion_usuario() {
        return direccion_usuario;
    }

    public void setDireccion_usuario(String direccion_usuario) {
        this.direccion_usuario = direccion_usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getDisponibilidad_usuario() {
        return disponibilidad_usuario;
    }

    public void setDisponibilidad_usuario(int disponibilidad_usuario) {
        this.disponibilidad_usuario = disponibilidad_usuario;
    }

    
    
}
