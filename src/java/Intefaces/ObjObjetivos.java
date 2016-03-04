/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import Pojo.Objetivos;
import Pojo.Orbita;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
public interface ObjObjetivos 
{
    public Objetivos saveObjetivos(Session session, Transaction transaction,Orbita orbita,String nombreObj,String metaObj,String NickObj);
}
