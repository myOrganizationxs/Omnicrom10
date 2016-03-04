/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbViewObjCuantitativos;

import Dao.DaoObjetivos;
import Dao.DaoOrbita;
import HibernateUtil.HibernateUtil;
import Pojo.Orbita;
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
@ManagedBean(name="MbAltaObjetivo")
@ViewScoped
public class MbAltaObjetivo {

     private String nombreObjetivo;
     private String nickObjetivo;
     private String meta;
     private Orbita orbitas;
     private Session session;
     private Transaction transaction;
    /**
     * Creates a new instance of AltaObjetivo
     */
    public MbAltaObjetivo() 
    {

    }
    public void findOrbita()
    {
        session=null;
        transaction=null;
        try
        {
            DaoOrbita daoOrbita=new DaoOrbita();
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();          
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            orbitas = daoOrbita.getByIdNodoOrg(session, (Integer) sessionUsuario.getAttribute("idNodo"));
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
        }      
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
    public void guardarObjetivo()
    {
        findOrbita();      
        JOptionPane.showMessageDialog(null, "Nombre Objetivo: "+nombreObjetivo + "  Nomeglatura: "+ nickObjetivo +"  meta: "+meta);
        
        session=null;
        transaction=null;
        try
        {
            DaoObjetivos daoObjetivos=new DaoObjetivos();
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();   
            daoObjetivos.saveObjetivos(session, transaction, orbitas, nombreObjetivo, meta, nickObjetivo);
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
        }      
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }


    
    public String getNombreObjetivo() {
        return nombreObjetivo;
    }

    public void setNombreObjetivo(String nombreObjetivo) {
        this.nombreObjetivo = nombreObjetivo;
    }

    public String getNickObjetivo() {
        return nickObjetivo;
    }

    public void setNickObjetivo(String nickObjetivo) {
        this.nickObjetivo = nickObjetivo;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Orbita getOrbitas() {
        return orbitas;
    }

    public void setOrbitas(Orbita orbitas) {
        this.orbitas = orbitas;
    }
    
}
