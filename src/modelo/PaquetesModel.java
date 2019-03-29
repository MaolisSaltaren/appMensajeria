/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class PaquetesModel {
    public  int paquete_id;
    public  String paquete_nombre;
    public  String paquete_descripcion;
    public  double paquete_precio;

    public int getPaquete_id() {
        return paquete_id;
    }

    public void setPaqueteId(int id_paquete) {
        this.paquete_id = id_paquete;
    }

    public String getPaquete_nombre() {
        return paquete_nombre;
    }

    public void setPaquete_nombre(String paquete_nombre) {
        this.paquete_nombre = paquete_nombre;
    }

    public String getPaquete_descripcion() {
        return paquete_descripcion;
    }

    public void setPaquete_descripcion(String paquete_descripcion) {
        this.paquete_descripcion = paquete_descripcion;
    }

    public double getPaquete_precio() {
        return paquete_precio;
    }

    public void setPaquete_precio(double paquete_precio) {
        this.paquete_precio = paquete_precio;
    }
}
