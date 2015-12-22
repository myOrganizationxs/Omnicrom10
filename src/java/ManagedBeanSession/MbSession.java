/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanSession;

import Dao.DaoCuenta;
import HibernateUtil.HibernateUtil;
import Pojo.Cuenta;
import Pojo.Usuario;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbSession")
@SessionScoped
public class MbSession implements Serializable{

    private Session session;
    private Transaction transaction;
    
    private String Usuario;
    private String contrasenia;
    private String NombreCompleto;
    /**
     * Creates a new instance of MbSession
     */
    public MbSession() 
    {
        HttpSession miSession=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(50000);
    }

    public String login()
    {
        this.session=null;
        this.transaction=null;
        
        try
        {        
            DaoCuenta  DaoCuenta = new DaoCuenta();

            this.session=HibernateUtil.getSessionFactory().openSession();            
            this.transaction=this.session.beginTransaction();
            
            Cuenta cuenta=DaoCuenta.getByUsuario(session, this.Usuario);
            
            if(cuenta!=null)
            {
                if(cuenta.getContrasena().equals(this.contrasenia))
                {
                    HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);                    
                    httpSession.setAttribute("idcuenta", cuenta.getIdCuenta());                   
                    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto: ","Usuario Establecido"));
                    return "interfazUsuario.xhtml?faces-redirect=true";
                    
                }
            }
            
            this.transaction.commit();
            this.Usuario=null;
            this.contrasenia=null;       
            HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.invalidate();           
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","Usuario Incorrecto"));
            
            return "login.xhtml?faces-redirect=true";
            
        }
        catch(Exception e)
        {
            if(this.transaction!=null)
            {
                transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error",e.getMessage()));
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
    
    public String cerrarSesion()
    {
        this.Usuario=null;
        this.contrasenia=null;       
        HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();
        return "/login?faces-redirect=true";
    }
    
    
    public String getNombreComplete()
    {
        try
        {
            if(Usuario!=null)
            {
                DaoCuenta daoCuenta=new DaoCuenta();
                this.session=HibernateUtil.getSessionFactory().openSession();
                this.transaction=this.session.beginTransaction();          
                HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);         
                List<Object[]> lista =daoCuenta.getByIdcuentaNombre(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));                    
                for (Object[] alist : lista)
                {
                NombreCompleto=(String) alist[0]+" "+alist[1]+" "+alist[2];
                    HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);                    
                    httpSession.setAttribute("idNodo", alist[3]);
                    httpSession.setAttribute("idUsuario", alist[4]);
                }
                this.transaction.commit();
                return NombreCompleto;
            }
                return null;
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            return "/interfazUsuario.xhtml";
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    } 
            
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    } 

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }
    
}
