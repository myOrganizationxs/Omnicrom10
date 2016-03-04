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
public interface Cuentas
{
    public Cuenta getByUsuario(Session session,String Usuario)throws Exception;
    public List getByIdcuenta(Session session,Integer idcuenta)throws Exception;
    public Cuenta getByIdcuentas(Session session,Integer idcuenta)throws Exception;
    public List getByIdcuentaNombre(Session session,Integer idcuenta)throws Exception;
}
