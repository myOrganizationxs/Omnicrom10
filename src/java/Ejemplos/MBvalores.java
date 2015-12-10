/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MBvalores")
@SessionScoped
public class MBvalores {

    private String letraA="hola";
    /**
     * Creates a new instance of MBvalores
     */
    public MBvalores() 
    {
       
    }

    
    
    public String getLetraA() {
        return letraA;
    }

    public void setLetraA(String letraA) {
        this.letraA = letraA;
    }
    
}
