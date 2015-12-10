/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanRequest;

import Dao.DaoCuenta;
import Dao.DaoMensajes;
import Dao.DaoNodo;
import HibernateUtil.HibernateUtil;
import Pojo.Mensajes;
import Pojo.Nodo;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbEnviarMensaje")
@RequestScoped
public class MbEnviarMensaje implements Serializable 
{
     
    private Session session;
    private Transaction transaction;
    private Nodo nodo;
    private Mensajes mensajes;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;
    
    public MbEnviarMensaje()
    {
        
    }
    
    public String enviarMensaje()
    {        
        return "/enviarMensaje?faces-redirect=true";
    }    
    
    public String saveMensaje() throws Exception
    {
        this.session=null;
        this.transaction=null;
        DaoNodo daoNodo = new DaoNodo();
        DaoMensajes daoMensajes = new DaoMensajes();
        
        this.session=HibernateUtil.getSessionFactory().openSession();
        this.transaction=this.session.beginTransaction();            
        HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        int idNodo = daoNodo.getByIdCuenta(session, (Integer) sessionUsuario.getAttribute("idcuenta"));
        date= new Date();
        JOptionPane.showMessageDialog(null, idNodo+" "+date);
        nodo =(Nodo) session.load(Nodo.class,idNodo);
        
        daoMensajes.saveMensaje(session, transaction, idNodo, 15, "hola a todos es mi primer mensaje", nodo,date);
        /*mensajes = new Mensajes();
        //el que manda int id
        mensajes.setEmisor(1);
        //Leido o No leido
        mensajes.setEstadoMensaje(1);
        //la hora que se envia
        mensajes.setHoraEnvio(null);
        //hora cuando se acepta
        mensajes.setHoraRecibido(null);
        //texto que se va a poner
        mensajes.setPeticion(null);
        //si no o no conidicional
        mensajes.setPromesa(null);
        // a quien le llega
        mensajes.setReceptor(1);
        //numero de revocaciones
        mensajes.setRevocaciones(1);
        //Variables para el programador
        mensajes.setTiempoProceso(null);
        //variables para el programador
        mensajes.setTiempoProceso2(null);       
        //nodo
        mensajes.setNodo(nodo);
        //Set<Mensajes> set2 = new HashSet<Mensajes>();
        //set2.add(mensajes);
        //JOptionPane.showMessageDialog(null,nodo+" "+nodo.getIdNodo()+" "+set2);
        //nodo.setMensajeses(set2);       
        //nodo.setCargo("hola a todos");
        //session.saveOrUpdate(nodo);
        session.saveOrUpdate(mensajes);
        //JOptionPane.showMessageDialog(null,"guardado correcto");
        transaction.commit();  
        session.close();  
        */
        
        return"/interfazUsuario?faces-redirect=true";
  
    }
    
    public String bandejaEntrada()
    {
        return "/bandejadeEntrada?faces-redirect=true";
    }
    public String bandejaSalida()
    {
        return "/bandejadeSalida?faces-redirect=true";
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
