/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Intefaces.ObjObjetivos;
import Pojo.Objetivos;
import Pojo.Orbita;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
@ManagedBean
@RequestScoped
public class DaoObjetivos implements ObjObjetivos
{

    /**
     * Creates a new instance of DaoObjetivos
     */
    public DaoObjetivos() {
    }

    @Override
    public Objetivos saveObjetivos(Session session, Transaction transaction ,Orbita orbita, String nombreObj, String metaObj, String NickObj) {
        try
        {
            Objetivos objetivos = new Objetivos();
            objetivos.setNombreObjetivo(nombreObj);
            objetivos.setNickObjetivo(NickObj);
            objetivos.setMeta(metaObj);
            objetivos.setOrbita(orbita);
            session.saveOrUpdate(objetivos);
            JOptionPane.showMessageDialog(null,"guardado correcto");
            transaction.commit();  
            session.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }
    
}
