/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Intefaces.Mensajes;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public class DaoMensajes implements Mensajes{

    @Override
    public List getByIdcuentaMensaje(Session session, Integer idcuenta) throws Exception {
        
        try{
            String hql="from Cuenta c inner join c.usuarios u inner join u.nodo n inner join n.mensajeses where IdCuenta=:idCuenta";
            Query query=session.createQuery(hql);
            query.setParameter("idCuenta", idcuenta);
            //JOptionPane.showMessageDialog(null,"LLega aqui 1");
            List lista = (List) query.list();                         
            JOptionPane.showMessageDialog(null,"mensaje en lista de objetos del pojo"+lista);

            return lista;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }
    
    
}
