/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanRequest;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbObjetivos")
@RequestScoped
public class MbObjetivos implements Serializable
{

    /**
     * Creates a new instance of MbObjetivos
     */
    public MbObjetivos() 
    {
    }
    public String enviarCuantitavos()
    {
        return "/objetivosCuantitativos?faces-redirect=true";
    }
    
    public String enviarCuantitativosAlta()
    {
        return "/ObjetivoscuantitativosAlta?faces-redirect=true";
    }
}
