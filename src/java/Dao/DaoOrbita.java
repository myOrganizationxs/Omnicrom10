/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Intefaces.IntOrbitas;
import Pojo.Nodo;
import Pojo.Orbita;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public class DaoOrbita implements IntOrbitas
{

    @Override
    public Orbita getByIdNodoOrg(Session session,int IdNodo) throws Exception {
        try 
        {
            //select object(o) from Nodo as o            
            String hql="Select o from Nodo n inner join n.orbitas o where IdNodo=:IdNodo";
            Query query=session.createQuery(hql);
            query.setParameter("IdNodo",IdNodo);  
            Orbita orbita =(Orbita) query.uniqueResult();    
            return orbita;
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }  
}
