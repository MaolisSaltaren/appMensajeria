//modelo que contiene los datos de la base de datos de la tabla clientes
package modelo;



public class ClientesModel {
    private int id_cliente;
    private String nombre_cliente;
    private String telefono_cliente;
    private String fecha_nac_cliente;
    private String direccion_cliente;
    private String ciudad_cliente;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getFecha_nac_cliente() {
        return fecha_nac_cliente;
    }

    public void setFecha_nac_cliente(String fecha_nac_cliente) {
        this.fecha_nac_cliente = fecha_nac_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getCiudad_cliente() {
        return ciudad_cliente;
    }

    public void setCiudad_cliente(String ciudad_cliente) {
        this.ciudad_cliente = ciudad_cliente;
    }
   
}
