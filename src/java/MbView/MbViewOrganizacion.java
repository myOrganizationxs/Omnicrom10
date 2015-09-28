/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Dao.DaoCuenta;
import Dao.DaoOrganizacion;
import HibernateUtil.HibernateUtil;
import java.io.Serializable;
import static java.rmi.Naming.list;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbViewOrganizacion")
@ViewScoped
public class MbViewOrganizacion implements Serializable{

    private Session session;
    private Transaction transaction;
    
    private String Organizacion;
    /**
     * Creates a new instance of MbViewOrganizacion
     */
    public MbViewOrganizacion() 
    {
        
    }
    
    public String getNombreOrganizacion()
    {
        try
        {
                DaoOrganizacion daoOrganizacion=new DaoOrganizacion();               
                this.session=HibernateUtil.getSessionFactory().openSession();
                this.transaction=this.session.beginTransaction();          
                HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);         
                Organizacion=daoOrganizacion.getByIdcuentaOrganizacion(session, (Integer) sessionUsuario.getAttribute("idcuenta"));                    
                
                this.transaction.commit();
                return Organizacion;
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

    public String getOrganizacion() {
        return Organizacion;
    }

    public void setOrganizacion(String Organizacion) {
        this.Organizacion = Organizacion;
    }
    
}
