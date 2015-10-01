/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;


import Pojo.Nodo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author sergio
 */
public interface Nodos {
    
    public List<Nodo> getByOrganizacion(Session session,int IdOrganizacion, int IdNodo)throws Exception;
    
}
