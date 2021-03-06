package Pojo;
// Generated 25/02/2016 11:53:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Nodo generated by hbm2java
 */
public class Nodo  implements java.io.Serializable {


     private Integer idNodo;
     private Organizacion organizacion;
     private Integer idPadre;
     private String cargo;
     private Set orbitas = new HashSet(0);
     private Set mensajeses = new HashSet(0);
     private Set usuarios = new HashSet(0);

    public Nodo() {
    }

	
    public Nodo(Organizacion organizacion) {
        this.organizacion = organizacion;
    }
    public Nodo(Organizacion organizacion, Integer idPadre, String cargo, Set orbitas, Set mensajeses, Set usuarios) {
       this.organizacion = organizacion;
       this.idPadre = idPadre;
       this.cargo = cargo;
       this.orbitas = orbitas;
       this.mensajeses = mensajeses;
       this.usuarios = usuarios;
    }
   
    public Integer getIdNodo() {
        return this.idNodo;
    }
    
    public void setIdNodo(Integer idNodo) {
        this.idNodo = idNodo;
    }
    public Organizacion getOrganizacion() {
        return this.organizacion;
    }
    
    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }
    public Integer getIdPadre() {
        return this.idPadre;
    }
    
    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }
    public String getCargo() {
        return this.cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public Set getOrbitas() {
        return this.orbitas;
    }
    
    public void setOrbitas(Set orbitas) {
        this.orbitas = orbitas;
    }
    public Set getMensajeses() {
        return this.mensajeses;
    }
    
    public void setMensajeses(Set mensajeses) {
        this.mensajeses = mensajeses;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}


