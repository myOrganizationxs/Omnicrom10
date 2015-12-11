/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Intefaces.IntMensajes;
import Pojo.Mensajes;
import Pojo.Nodo;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
public class DaoMensajes implements IntMensajes{

    @Override
    public List getByIdcuentaMensaje(Session session, Integer idcuenta) throws Exception {
        
        try{
            String hql="from Cuenta c inner join c.usuarios u inner join u.nodo n inner join n.mensajeses where IdCuenta=:idCuenta";
            Query query=session.createQuery(hql);
            query.setParameter("idCuenta", idcuenta);
            //JOptionPane.showMessageDialog(null,"LLega aqui 1");
            List lista = (List) query.list();                         
            //JOptionPane.showMessageDialog(null,"mensaje en lista de objetos del pojo"+lista);

            return lista;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }

    @Override
    public Mensajes saveMensaje(Session session,Transaction transaction, Integer idcuenta,int Receptor,String Peticion,Nodo nodo,Date date,Date fecha2) throws Exception {
        
        Mensajes mensajes = new Mensajes();        
        mensajes = new Mensajes();
        //el que manda int id
        mensajes.setEmisor(null);
        //Leido o No leido
        mensajes.setEstadoMensaje(1);
        //la hora que se envia
        mensajes.setHoraEnvio(date);
        //hora cuando se acepta
        mensajes.setHoraRecibido(fecha2);
        //texto que se va a poner
        mensajes.setPeticion(Peticion);
        //si no o no conidicional
        mensajes.setPromesa(null);
        // a quien le llega
        mensajes.setReceptor(Receptor);
        //numero de revocaciones
        mensajes.setRevocaciones(0);
        //Variables para el programador
        mensajes.setTiempoProceso(date);
        //variables para el programador
        mensajes.setTiempoProceso2(null);       
        //nodo
        mensajes.setNodo(nodo);
        //Set<Mensajes> set2 = new HashSet<Mensajes>();
        //set2.add(mensajes);
        //JOptionPane.showMessageDialog(null,nodo+" "+nodo.getIdNodo()+" "+set2);
        //nodo.setMensajeses(set2);       
        //nodo.setCargo("hola a todos");
        //session.saveOrUpdate(nodo);
        session.saveOrUpdate(mensajes);
        JOptionPane.showMessageDialog(null,"guardado correcto");
        transaction.commit();  
        session.close();
        
        return null;
    }

    @Override
    public List<Object> getBandejaDeEntradaByReceptor(Session session, Integer receptor) throws Exception {
        
        try{
            String hql="from Mensajes m inner join m.nodo n inner join n.usuarios where m.receptor=:receptor and m.estadoMensaje=1";
            Query query=session.createQuery(hql);
            query.setParameter("receptor", receptor);;
            List<Object> lista =(List<Object>)query.list(); 
            //JOptionPane.showMessageDialog(null, lista);
            return lista;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return null;
    }

    @Override
    public Mensajes uploadMensaje(Session session, Transaction transaction, Integer idcuenta, Integer estado, Date NuevaFecha,Nodo nodo) throws Exception {
       
        Mensajes mensajes = new Mensajes();        
        mensajes = new Mensajes();
        //el que manda int id
        //Leido o No leido
        mensajes.setEstadoMensaje(estado);
        mensajes.setHoraRecibido(NuevaFecha);
        //la hora que se envia
        //hora cuando se acepta
        //texto que se va a poner
        //si no o no conidicional
        // a quien le llega
        //numero de revocaciones
        //Variables para el programador
        //variables para el programador      
        //nodo
        //Set<Mensajes> set2 = new HashSet<Mensajes>();
        //set2.add(mensajes);
        //JOptionPane.showMessageDialog(null,nodo+" "+nodo.getIdNodo()+" "+set2);
        //nodo.setMensajeses(set2);       
        //nodo.setCargo("hola a todos");
        //session.saveOrUpdate(nodo);
        session.saveOrUpdate(mensajes);
        JOptionPane.showMessageDialog(null,"guardado correcto");
        transaction.commit();  
        session.close();
        
        return null; 
    }
    
    
}
