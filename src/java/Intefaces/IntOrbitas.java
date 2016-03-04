/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import Pojo.Nodo;
import Pojo.Orbita;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public interface IntOrbitas 
{
    public Orbita getByIdNodoOrg(Session session,int IdNodo)throws Exception;  
}
