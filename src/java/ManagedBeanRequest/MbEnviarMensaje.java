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
import Pojo.Usuario;
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
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class MbEnviarMensaje implements Serializable 
{
     
    private Session session;
    private Transaction transaction;
    private Nodo nodo;
    private Mensajes mensajes;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;
    private String peticion;
    //fecha2 = a la hora que se quiere la peticion
    private Date fecha2;
    private int idNodo;
    //si dice si no si condicional
    private int Promesa;
    private int TargerMensaje;
    
    public MbEnviarMensaje()
    {
        
    }
    
    public String enviarMensaje()
    {        
        return "/enviarMensaje?faces-redirect=true";
    }    
    
    public void BuscarIdNodo() throws Exception
    {
        this.session=null;
        this.transaction=null;
        DaoNodo daoNodo = new DaoNodo();        
        this.session=HibernateUtil.getSessionFactory().openSession();
        this.transaction=this.session.beginTransaction();            
        HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        idNodo = daoNodo.getByIdCuenta(session, (Integer) sessionUsuario.getAttribute("idcuenta"));
       //date= new Date();
        //JOptionPane.showMessageDialog(null, idNodo+" "+date);
        nodo =(Nodo) session.load(Nodo.class,idNodo);
    }
    public String saveMensaje() throws Exception
    {
        BuscarIdNodo();
        this.session=null;
        this.transaction=null;
        DaoMensajes daoMensajes = new DaoMensajes();       
        this.session=HibernateUtil.getSessionFactory().openSession();
        this.transaction=this.session.beginTransaction();            
        HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);      
        date= new Date();
        JOptionPane.showMessageDialog(null, idNodo+" "+date);
        nodo =(Nodo) session.load(Nodo.class,idNodo);
        //falta el que le va allegar con webservice
        daoMensajes.saveMensaje(session, transaction, idNodo, 15,peticion, nodo,date,fecha2);
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
    public String getResponderMensaje() throws Exception
    {
        DaoMensajes daoMensajes = new DaoMensajes();
        
        switch( Promesa ) {
        case 1:
       
            
            this.session=null;
            this.transaction=null;      
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();              
            mensajes =(Mensajes) session.load(Mensajes.class,TargerMensaje);
            JOptionPane.showMessageDialog(null,"Aceptaste el mesaje con id: "+mensajes.getIdMensajes());
            daoMensajes.uploadMensajeSi(mensajes, session, transaction);
            
            
            break;
            
        
        case 2:
            
            this.session=null;
            this.transaction=null;      
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();              
            mensajes =(Mensajes) session.load(Mensajes.class,TargerMensaje);
            JOptionPane.showMessageDialog(null,"No Aceptaste el mesaje con id: "+mensajes.getIdMensajes());
            daoMensajes.uploadMensajeNo(mensajes, session, transaction);
            
        break;
        
        case 3:
            this.session=null;
            this.transaction=null;      
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();              
            mensajes =(Mensajes) session.load(Mensajes.class,TargerMensaje);
            JOptionPane.showMessageDialog(null,"No Aceptaste el mesaje con id: "+mensajes.getIdMensajes());
            daoMensajes.uploadMensajeSiCondicional(mensajes, session, transaction, fecha2);
        break;
    
        default:
            JOptionPane.showMessageDialog(null,"Selecciona algo");
        break;
        }
        
        return null;
        //JOptionPane.showMessageDialog(null, TargerMensaje);
    }
    
    public String bandejaEntrada()
    {
        return "/bandejadeEntrada?faces-redirect=true";
    }
    public String bandejaSalida()
    {
        return "/bandejadeSalida?faces-redirect=true";
    }

    public List<Object> getByListaReceptor()
    {
        
        this.session=null;
        this.transaction=null;

        try
        {
            BuscarIdNodo();
            DaoMensajes daoMensajes=new DaoMensajes();            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);           
            List<Object> lista=daoMensajes.getBandejaDeEntradaByReceptor(session,idNodo);
            //List lista =daoCuenta.getByIdcuenta(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));          
            //JOptionPane.showMessageDialog(null, lista);
            
            this.transaction.commit();           
            return lista;
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            
            return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    public List<Object> getByListaReceptorProceso()
    {
        
        this.session=null;
        this.transaction=null;

        try
        {
            BuscarIdNodo();
            DaoMensajes daoMensajes=new DaoMensajes();            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);           
            List<Object> lista=daoMensajes.getBandejaDeEntradaByReceptorProceso(session,idNodo);
            //List lista =daoCuenta.getByIdcuenta(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));          
            //JOptionPane.showMessageDialog(null, lista);
            
            this.transaction.commit();           
            return lista;
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            
            return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
    public List<Object> getByListaReceptorNo()
    {
        
        this.session=null;
        this.transaction=null;

        try
        {
            BuscarIdNodo();
            DaoMensajes daoMensajes=new DaoMensajes();            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);           
            List<Object> lista=daoMensajes.getBandejaDeEntradaByReceptorNo(session,idNodo);
            //List lista =daoCuenta.getByIdcuenta(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));          
            //JOptionPane.showMessageDialog(null, lista);
            
            this.transaction.commit();           
            return lista;
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            
            return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }      
    public List<Object> getByListaReceptorSiCondicional()
    {
        
        this.session=null;
        this.transaction=null;

        try
        {
            BuscarIdNodo();
            DaoMensajes daoMensajes=new DaoMensajes();            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);           
            List<Object> lista=daoMensajes.getBandejaDeEntradaByReceptorSiCOndicional(session, idNodo);
            //List lista =daoCuenta.getByIdcuenta(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));          
            //JOptionPane.showMessageDialog(null, lista);
            
            this.transaction.commit();           
            return lista;
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            
            return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
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

    public String getPeticion() {
        return peticion;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public int getPromesa() {
        return Promesa;
    }

    public void setPromesa(int Promesa) {
        this.Promesa = Promesa;
    }

    public int getTargerMensaje() {
        return TargerMensaje;
    }

    public void setTargerMensaje(int TargerMensaje) {
        this.TargerMensaje = TargerMensaje;
    }

}
