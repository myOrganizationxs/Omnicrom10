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
import com.google.gson.Gson;
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
import org.primefaces.event.NodeSelectEvent;
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
    private TreeNode singleSelectedTreeNode;
    private String[][] matrizArbol;
    private String Dep="Departamento X";
    private int suma=123;
 //dasdasd
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
        
        NumeroDeNodos=(Integer) sessionUsuario.getAttribute("idNodo");
        
        for(Object[] itr:liste)
        {
            donanim=new DefaultTreeNode(itr[1], root);
            idNodo=(int) itr[0];            
        }
        recursive(liste,idNodo,donanim);  
    }
    public  void recursive(List<Object[]> lista, int id,TreeNode node) throws Exception{
        
        HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        lista=daoNodo.getByOrganizacion(session,(Integer) sessionUsuario.getAttribute("idorganizacion"),id);
        for(Object[] n:lista)
        {

                TreeNode childNode=new DefaultTreeNode(n[1], node);
                idNodo=(int) n[0];    
                recursive(lista,idNodo,childNode);

        }
        
    }
    
    //inicia la forma dinamica de hacer el arbol
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
            ListaNodosHijos=daoNodo.getByIdNodo(session, NumeroDeNodos,(Integer) sessionUsuario.getAttribute("idorganizacion"));
            //Gson gson= new Gson();
            //gson.toJson(ListaNodosHijos);  
            //.showMessageDialog(null, "numero "+ListaNodosHijos+" seleccionado");
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
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
    public void onNodeSelect(NodeSelectEvent event){
        //JOptionPane.showMessageDialog(null, "Node Data ::"+singleSelectedTreeNode+" :: Selected");
        suma++;
        //buscar por nombre de nodo
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

    public TreeNode getSingleSelectedTreeNode() {
        return singleSelectedTreeNode;
    }

    public void setSingleSelectedTreeNode(TreeNode singleSelectedTreeNode) {
        this.singleSelectedTreeNode = singleSelectedTreeNode;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public String[][] getMatrizArbol() {
        return matrizArbol;
    }

    public void setMatrizArbol(String[][] matrizArbol) {
        this.matrizArbol = matrizArbol;
    }

    public String getDep() {
        return Dep;
    }

    public void setDep(String Dep) {
        this.Dep = Dep;
    }
    
}
