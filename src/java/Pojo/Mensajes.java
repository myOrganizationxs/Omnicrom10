package Pojo;
// Generated 25/02/2016 11:53:01 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Mensajes generated by hbm2java
 */
public class Mensajes  implements java.io.Serializable {


     private Integer idMensajes;
     private Nodo nodo;
     private Integer emisor;
     private Integer receptor;
     private String peticion;
     private String promesa;
     private Date tiempoProceso;
     private Date horaEnvio;
     private Date horaRecibido;
     private Integer revocaciones;
     private Date tiempoProceso2;
     private Integer estadoMensaje;

    public Mensajes() {
    }

	
    public Mensajes(Nodo nodo) {
        this.nodo = nodo;
    }
    public Mensajes(Nodo nodo, Integer emisor, Integer receptor, String peticion, String promesa, Date tiempoProceso, Date horaEnvio, Date horaRecibido, Integer revocaciones, Date tiempoProceso2, Integer estadoMensaje) {
       this.nodo = nodo;
       this.emisor = emisor;
       this.receptor = receptor;
       this.peticion = peticion;
       this.promesa = promesa;
       this.tiempoProceso = tiempoProceso;
       this.horaEnvio = horaEnvio;
       this.horaRecibido = horaRecibido;
       this.revocaciones = revocaciones;
       this.tiempoProceso2 = tiempoProceso2;
       this.estadoMensaje = estadoMensaje;
    }
   
    public Integer getIdMensajes() {
        return this.idMensajes;
    }
    
    public void setIdMensajes(Integer idMensajes) {
        this.idMensajes = idMensajes;
    }
    public Nodo getNodo() {
        return this.nodo;
    }
    
    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
    public Integer getEmisor() {
        return this.emisor;
    }
    
    public void setEmisor(Integer emisor) {
        this.emisor = emisor;
    }
    public Integer getReceptor() {
        return this.receptor;
    }
    
    public void setReceptor(Integer receptor) {
        this.receptor = receptor;
    }
    public String getPeticion() {
        return this.peticion;
    }
    
    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }
    public String getPromesa() {
        return this.promesa;
    }
    
    public void setPromesa(String promesa) {
        this.promesa = promesa;
    }
    public Date getTiempoProceso() {
        return this.tiempoProceso;
    }
    
    public void setTiempoProceso(Date tiempoProceso) {
        this.tiempoProceso = tiempoProceso;
    }
    public Date getHoraEnvio() {
        return this.horaEnvio;
    }
    
    public void setHoraEnvio(Date horaEnvio) {
        this.horaEnvio = horaEnvio;
    }
    public Date getHoraRecibido() {
        return this.horaRecibido;
    }
    
    public void setHoraRecibido(Date horaRecibido) {
        this.horaRecibido = horaRecibido;
    }
    public Integer getRevocaciones() {
        return this.revocaciones;
    }
    
    public void setRevocaciones(Integer revocaciones) {
        this.revocaciones = revocaciones;
    }
    public Date getTiempoProceso2() {
        return this.tiempoProceso2;
    }
    
    public void setTiempoProceso2(Date tiempoProceso2) {
        this.tiempoProceso2 = tiempoProceso2;
    }
    public Integer getEstadoMensaje() {
        return this.estadoMensaje;
    }
    
    public void setEstadoMensaje(Integer estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }




}


