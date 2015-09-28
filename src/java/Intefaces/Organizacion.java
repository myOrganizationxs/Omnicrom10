/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public interface Organizacion 
{
    public String getByIdcuentaOrganizacion(Session session,Integer idcuenta)throws Exception;
}
