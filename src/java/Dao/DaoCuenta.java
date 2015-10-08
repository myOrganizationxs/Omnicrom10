/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Intefaces.Cuentas;
import Pojo.Cuenta;
import Pojo.Usuario;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class DaoCuenta implements Cuentas
{
    @Override
    public Cuenta getByUsuario(Session session, String usuario) throws Exception {
        
        String hql="from Cuenta c where c.usuario=:usuario";
        Query query=session.createQuery(hql);
        query.setParameter("usuario",usuario);  
        Cuenta cuenta=(Cuenta) query.uniqueResult();      
        return cuenta;
    }

    @Override
    public List getByIdcuenta(Session session, Integer idcuenta)
    {
        try{
            String hql="from Cuenta c  inner join c.usuarios u inner join u.nodo  where c.idCuenta=:idCuenta";
            Query query=session.createQuery(hql);
            query.setParameter("idCuenta", idcuenta);
            //JOptionPane.showMessageDialog(null,"LLega aqui 1");
            List todos = query.list();                         
            //JOptionPane.showMessageDialog(null,"mensaje en lista de objetos del pojo"+todos);

            return todos;
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }
    
    @Override
    public Cuenta getByIdcuentas(Session session, Integer idcuenta) throws Exception {
        try{
            String hql="from Cuenta c where c.idCuenta=:idCuenta";
            Query query=session.createQuery(hql);
            query.setParameter("idCuenta", idcuenta);
            //JOptionPane.showMessageDialog(null,"LLega aqui 1");
            Cuenta Cuenta = (Cuenta) query.uniqueResult();                         
            //JOptionPane.showMessageDialog(null,"LLega aqui 2"+Cuenta);

            return Cuenta;
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }

    @Override
    public List getByIdcuentaNombre(Session session, Integer idcuenta) throws Exception {
        try{
            String hql="select u.nombreUsu,u.apellidoPaterno,u.apellidoMaterno, n.idNodo from Cuenta c  inner join c.usuarios u inner join u.nodo n where c.idCuenta=:idCuenta";
            Query query=session.createQuery(hql);
            query.setParameter("idCuenta", idcuenta);
            //JOptionPane.showMessageDialog(null,"LLega aqui 1");
            List lista = (List) query.list();                         
            //JOptionPane.showMessageDialog(null,"mensaje en lista de objetos del pojo"+lista);

            return lista;
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }
}
