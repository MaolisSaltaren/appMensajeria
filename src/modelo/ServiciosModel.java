/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
//===========================================================
// MODELO QUE  DE LA TABLA SERVICIOS 
public class ServiciosModel {
     
    public int servi_id;
    public String servi_fecha_servicio;
    public int servi_traba_id;
    public int servi_paque_id;
    public String servi_estado;
    public String servi_direccion;
    public String servi_ciudad_destino;
    public String servi_fecha_entrega;
    public String servi_observacion;

    public int getServi_id() {
        return servi_id;
    }

    public void setServi_id(int servi_id) {
        this.servi_id = servi_id;
    }

    public String getServi_fecha_servicio() {
        return servi_fecha_servicio;
    }

    public void setServi_fecha(String servi_fecha_entrega) {
        this.servi_fecha_entrega= servi_fecha_entrega;
    }

    public int getServi_traba_id() {
        return servi_traba_id;
    }

    public void setServi_traba_id(int servi_traba_id) {
        this.servi_traba_id = servi_traba_id;
    }

    public int getServi_paque_id() {
        return servi_paque_id;
    }

    public void setServi_paque_id(int servi_paque_id) {
        this.servi_paque_id = servi_paque_id;
    }

    public String getServi_estado() {
        return servi_estado;
    }

    public void setServi_estado(String servi_estado) {
        this.servi_estado = servi_estado;
    }

    public String getServi_direccion() {
        return servi_direccion;
    }

    public void setServi_direccion(String servi_direccion) {
        this.servi_direccion = servi_direccion;
    }

    public String getServi_ciudad_destino() {
        return servi_ciudad_destino;
    }

    public void setServi_ciudad_destino(String servi_ciudad_destino) {
        this.servi_ciudad_destino = servi_ciudad_destino;
    }

    public String getServi_fecha_entrega() {
        return servi_fecha_entrega;
    }

    public void setServi_fecha_entrega(String servi_fecha_entrega) {
        this.servi_fecha_entrega = servi_fecha_entrega;
    }

    public String getServi_observacion() {
        return servi_observacion;
    }

    public void setServi_observacion(String servi_observacion) {
        this.servi_observacion = servi_observacion;
    }
    
    //=================================================================================
    //MODELO DE LA TABLA CIENTES X SERVICIO 
    
    public   int  cliente_id_emisor;
    public   String  cliente_nombre_emisor;
    
    
    public   int  cliente_id_receptor;
    public   String  cliente_nombre_receptor;

    public   int  tipo_cliente_emisor;
    public   int  tipo_cliente_receptor;

    public int getCliente_id_emisor() {
        return cliente_id_emisor;
    }

    public void setCliente_id_emisor(int cliente_id_emisor) {
        this.cliente_id_emisor = cliente_id_emisor;
    }

    public String getCliente_nombre_emisor() {
        return cliente_nombre_emisor;
    }

    public void setCliente_nombre_emisor(String cliente_nombre_emisor) {
        this.cliente_nombre_emisor = cliente_nombre_emisor;
    }

    public int getCliente_id_receptor() {
        return cliente_id_receptor;
    }

    public void setCliente_id_receptor(int cliente_id_receptor) {
        this.cliente_id_receptor = cliente_id_receptor;
    }

    public String getCliente_nombre_receptor() {
        return cliente_nombre_receptor;
    }

    public void setCliente_nombre_receptor(String cliente_nombre_receptor) {
        this.cliente_nombre_receptor = cliente_nombre_receptor;
    }

    public int getTipo_cliente_emisor() {
        return tipo_cliente_emisor;
    }

    public void setTipo_cliente_emisor(int tipo_cliente_emisor) {
        this.tipo_cliente_emisor = tipo_cliente_emisor;
    }

    public int getTipo_cliente_receptor() {
        return tipo_cliente_receptor;
    }

    public void setTipo_cliente_receptor(int tipo_cliente_receptor) {
        this.tipo_cliente_receptor = tipo_cliente_receptor;
    }

 

    
    
}
