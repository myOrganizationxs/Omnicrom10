/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MbView;

import Pojo.Nodo;
import Util.KategorilerJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author sergio
 */
@ManagedBean(name="NodeBean")
@ViewScoped
public class NodeBean {

    private TreeNode root;
    private TreeNode donanim;
    private TreeNode selectedNode;
    private static  List<Nodo> liste;
    private static List<Nodo> araListe;
    private KategorilerJpaController katCont;
    private Nodo katNesnesi;
    private List<Nodo> subList2;
    private String kategIsmi;
    public NodeBean() {
        liste=new ArrayList<Nodo>();
        root=new DefaultTreeNode("Root",null);
        katCont=new KategorilerJpaController();
        liste=katCont.findKategorilerEntities();
        donanim=new DefaultTreeNode("Inicio", root);
//Butun kategorileri tutan bir ana kategori olusturuyor.
//Dynamic olarak sub kategori ekliyor. Recursive olarak hepsini kontrol edilmesi lazim.
        recursive(liste, 0,donanim);

    }
//Dynamic tree viewi olutruan method.
    public  void recursive(List<Nodo> liste, int id,TreeNode node){
            subList2=new ArrayList<Nodo>();
            subList2=subKategori(id);
            for(Nodo k:subList2)
            {
                TreeNode childNode=new DefaultTreeNode(k.getCargo(), node);
                //Veritabaninda kategori tablosunu tree view seklinde dynamic olarak olusturmayi saglar.
                 recursive(subList2, k.getIdNodo(),childNode);
            }

    }
//herhangi bir tree nodenin childlarini buluyor.
    public static List<Nodo> subKategori(int i)
    {
        araListe=new ArrayList<Nodo>();
        for(Nodo k:getListe()){
            if(k.getIdPadre()==i){
                araListe.add(k);
            }
        }
        return araListe;
    }
    public static List<Nodo> getListe() {
        return liste;
    }
    public Nodo getKatNesnesi() {
        return katNesnesi;
    }
    public void setKatNesnesi(Nodo katNesnesi) {
        this.katNesnesi = katNesnesi;
    }
    public TreeNode getRoot() {
        return root;
    }
    
    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    public void secilenNode(NodeSelectEvent event){
        FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_INFO, "selected", event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
   
    public String getKategIsmi() {
        return kategIsmi;
    }

    public void setKategIsmi(String kategIsmi) {
        this.kategIsmi = kategIsmi;
    }


    public void yeniKatEkle(){
//        JOptionPane.showMessageDialog(null,katCont.kategoriIdDonder(getSelectedNode().getData().toString()).getKategoriId());
        //katNesnesi=new Nodo(getKategIsmi(), katCont.kategoriIdDonder(getSelectedNode().getData().toString()).getKategoriId());
         katCont.create(katNesnesi);
         setKategIsmi(null);
    }
    
}
