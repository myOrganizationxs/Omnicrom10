/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Intefaces.Cuentas;
import Intefaces.Organizacion;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public class DaoOrganizacion implements Organizacion
{

    @Override
    public String getByIdcuentaOrganizacion(Session session, Integer idcuenta) throws Exception {
        
        try{
            String hql="select o.nombre from Cuenta c  inner join c.usuarios u inner join u.nodo n inner join n.organizacion o where c.idCuenta=:idCuenta";
            Query query=session.createQuery(hql);
            query.setParameter("idCuenta", idcuenta);
            //JOptionPane.showMessageDialog(null,"LLega aqui 1");
            String Organizacion = (String) query.uniqueResult();                         
            //JOptionPane.showMessageDialog(null,"mensaje en lista de objetos del pojo"+Organizacion);

            return Organizacion;
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,e);
        }
        return null;

    }
    
}
