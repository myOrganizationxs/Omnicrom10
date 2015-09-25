/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanRequest;

import Dao.DaoCuenta;
import HibernateUtil.HibernateUtil;
import Pojo.Cuenta;
import Pojo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbRequest")
@RequestScoped
public class MbRequest implements Serializable{
    private Session session;
    private Transaction transaccion;
    
    private Cuenta cuenta;
    private Usuario usuarios;
    
    private String Usuario;
    private String nombreusuario;
    
    
     public List getByListaIdcuenta()
    {
        this.session=null;
        this.transaccion=null;
        
        try
        {
            DaoCuenta daoCuenta=new DaoCuenta();
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion=this.session.beginTransaction();
            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
           
            List lista =daoCuenta.getByIdcuenta(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));
            
            this.transaccion.commit();
            
            return lista;
        }
        catch(Exception ex)
        {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
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
     
    List cuentas=getByListaIdcuenta();

    public Cuenta getByIdcuentas()
    {
        this.session=null;
        this.transaccion=null;
        
        try
        {
            DaoCuenta daoCuenta=new DaoCuenta();
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion=this.session.beginTransaction();
            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
           
            this.cuenta=daoCuenta.getByIdcuentas(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));
            
            this.transaccion.commit();
            
            return this.cuenta;
        }
        catch(Exception ex)
        {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
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
     
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    } 

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public List getCuentas() {
        return cuentas;
    }

    public void setCuentas(List cuentas) {
        this.cuentas = cuentas;
    }
    
}
