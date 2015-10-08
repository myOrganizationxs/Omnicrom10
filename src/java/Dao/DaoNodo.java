/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Intefaces.Nodos;
import Pojo.Nodo;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public class DaoNodo implements Nodos
{    
    @Override
    public List getByOrganizacion(Session session, int idOrganizacion,int IdPadre) throws Exception 
    {
        try 
        {
            //select object(o) from Nodo as o            
            String hql="Select n.idNodo,n.cargo,n.idPadre from Nodo n inner join n.organizacion o where o.idOrganizacion=:idOrganizacion and IdPadre=:IdPadre";
            Query query=session.createQuery(hql);
            query.setParameter("idOrganizacion",idOrganizacion);
            query.setParameter("IdPadre",IdPadre);  
            List lista =query.list();    
            return lista;
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,e);
        }
        return null;

    }

    @Override
    public List<Object[]> getByIdNodo(Session session, int idPadre, int idOrganizacion) throws Exception {
        try 
        {
            //select object(o) from Nodo as o            
            String hql="Select n.idNodo from Nodo n inner join n.organizacion o where n.idPadre=:idPadre and o.idOrganizacion=:idOrganizacion";
            Query query=session.createQuery(hql);
            query.setParameter("idPadre",idPadre); 
            query.setParameter("idOrganizacion",idOrganizacion);
            List lista =query.list();    
            return lista;
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }

}
