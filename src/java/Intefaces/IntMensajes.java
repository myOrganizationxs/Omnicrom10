/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import Pojo.Cuenta;
import Pojo.Mensajes;
import Pojo.Nodo;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
public interface IntMensajes {
    
    public List getByIdcuentaMensaje(Session session,Integer idcuenta)throws Exception;
    public Mensajes saveMensaje(Session session,Transaction transaction,Integer idcuenta,int Receptor,String Peticion,Nodo nodo,Date date,Date fecha2)throws Exception;
    public List<Object> getBandejaDeEntradaByReceptor(Session session,Integer Receptor)throws Exception;
    public Mensajes uploadMensajeSi(Mensajes mensajes,Session session,Transaction transaction)throws Exception;
}
