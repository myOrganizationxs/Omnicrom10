/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Dao.DaoNodo;
import Dao.DaoOrbita;
import Dao.DaoOrganizacion;
import HibernateUtil.HibernateUtil;
import static MbView.NodeBean.getListe;
import Pojo.Nodo;
import Util.KategorilerJpaController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbTree")
@ViewScoped
public class MbTree implements Serializable
{
    
    private Session session;
    private Transaction transaction;

    private TreeNode root;
    private TreeNode donanim;
    private static  List<Object[]> liste;
    private DaoNodo daoNodo;
    private int idNodo;
    private int NumeroDeNodos;
    private List<Object[]> ListaNodosHijos;
 
    public MbTree() throws Exception 
    { 
        this.session=null;
        this.transaction=null;

        root = new DefaultTreeNode("Root", null);
        daoNodo= new DaoNodo(); 
        this.session=HibernateUtil.getSessionFactory().openSession();
        this.transaction=this.session.beginTransaction(); 
        HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);   
        liste=daoNodo.getByOrganizacion(session,(Integer) sessionUsuario.getAttribute("idorganizacion"),0);
        
        for(Object[] itr:liste)
        {
            DaoOrbita daoOrbita= new DaoOrbita();
            String nombredep= daoOrbita.getByIdNodoOrg(session,(int) itr[0]);
            donanim=new DefaultTreeNode(nombredep+"("+itr[1]+")", root);
            idNodo=(int) itr[0];            
        }
        recursive(liste,idNodo,donanim);  
    }
    public  void recursive(List<Object[]> lista, int id,TreeNode node) throws Exception{
        
        HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        lista=daoNodo.getByOrganizacion(session,(Integer) sessionUsuario.getAttribute("idorganizacion"),id);
        for(Object[] n:lista)
        {
            DaoOrbita daoOrbita= new DaoOrbita();
            String nombredep= daoOrbita.getByIdNodoOrg(session,(int) n[0]);
            if(nombredep!=null)
            {
                TreeNode childNode=new DefaultTreeNode(nombredep+"("+n[1]+")", node);
                idNodo=(int) n[0];    
                recursive(lista,idNodo,childNode);
            }
            else
            {
                TreeNode childNode=new DefaultTreeNode(n[1], node);
                idNodo=(int) n[0];    
                recursive(lista,idNodo,childNode);
            }
        }
    }
    
    public List getNodoCentro()
    {
        session=null;
        transaction=null;
        try
        {
            DaoNodo daoNodo=new DaoNodo();
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();          
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            NumeroDeNodos=(Integer) sessionUsuario.getAttribute("idNodo");
            ListaNodosHijos=daoNodo.getByIdNodo(session, NumeroDeNodos,(Integer) sessionUsuario.getAttribute("idorganizacion"));
            return ListaNodosHijos;
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
    }
    public Integer getNodoHijo()
    {
        try
        {
            return NumeroDeNodos;
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
    }
    public TreeNode getRoot() {
        return root;
    }

    public static List<Object[]> getListe() {
        return liste;
    }

    public static void setListe(List<Object[]> liste) {
        MbTree.liste = liste;
    }

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public int getNumeroDeNodos() {
        return NumeroDeNodos;
    }

    public void setNumeroDeNodos(int NumeroDeNodos) {
        this.NumeroDeNodos = NumeroDeNodos;
    }

    public List<Object[]> getListaNodosHijos() {
        return ListaNodosHijos;
    }

    public void setListaNodosHijos(List<Object[]> ListaNodosHijos) {
        this.ListaNodosHijos = ListaNodosHijos;
    }
    
}
