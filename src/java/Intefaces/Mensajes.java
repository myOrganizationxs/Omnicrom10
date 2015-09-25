/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import Pojo.Cuenta;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public interface Mensajes {
    
    public List getByIdcuentaMensaje(Session session,Integer idcuenta)throws Exception;
    
}
