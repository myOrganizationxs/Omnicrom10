/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanSession;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sergio
 */
@ManagedBean(name="CambioPagina")
@SessionScoped
public class CambioPagina implements Serializable
{
    

    /**
     * Creates a new instance of CambioPagina
     */
    public CambioPagina() {
    }
    
    public String cambioPaginaxs()
    {   
        return "/enviarMensaje";
    }

}
