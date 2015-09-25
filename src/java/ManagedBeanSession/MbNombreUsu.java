/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanSession;

import Dao.DaoCuenta;
import HibernateUtil.HibernateUtil;
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
@ManagedBean(name="MbNombreUsu")
@SessionScoped
public class MbNombreUsu implements Serializable{
    
    private String NombreCompleto;
    
    private Session session;
    private Transaction transaccion;

    /**
     * Creates a new instance of MbNombreUsu
     */
    public MbNombreUsu() 
    {
      
    }
    public String getNombreComplete()
        {
            this.session=null;
            this.transaccion=null;
        
        try
        {
            DaoCuenta daoCuenta=new DaoCuenta();
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion=this.session.beginTransaction();
            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
           
            List lista =daoCuenta.getByIdcuentaNombre(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));
            
            JOptionPane.showMessageDialog(null,"entra");
            
            this.transaccion.commit();            
                      
            return NombreCompleto="hola";
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
    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }
    
}
