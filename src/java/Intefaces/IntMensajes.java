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
    public List getBandejaDeEntradaByReceptorProceso(Session session,Integer idcuenta)throws Exception;
    public Mensajes uploadMensajeNo(Mensajes mensajes,Session session,Transaction transaction)throws Exception;
    public List getBandejaDeEntradaByReceptorNo(Session session,Integer idcuenta)throws Exception;
    public Mensajes uploadMensajeSiCondicional(Mensajes mensajes,Session session,Transaction transaction,Date date)throws Exception;
    public List getBandejaDeEntradaByReceptorSiCOndicional(Session session,Integer idcuenta)throws Exception;
    public List<Object> getBandejaDeEntradaByEmisor(Session session,Integer Emisor)throws Exception;
    public List<Object> getBandejaDeEntradaByEmisorSiCondicional(Session session,Integer Emisor)throws Exception;
    public Mensajes uploadMensajeFin(Mensajes mensajes,Session session,Transaction transaction)throws Exception;
    public List<Object> getBandejaDeEntradaByReceptorFin(Session session,Integer Receptor)throws Exception;
    public List<Object> getBandejaDeEntradaByEmisorFin(Session session,Integer Emisor)throws Exception;
    public Mensajes uploadMensajeValorar(Mensajes mensajes,Session session,Transaction transaction,String valoracionS)throws Exception;
}
