/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanRequest;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbEnviarMensaje")
@RequestScoped
public class MbEnviarMensaje implements Serializable 
{
    public MbEnviarMensaje()
    {
        
    }
    
    public String enviarMensaje()
    {
        //JOptionPane.showMessageDialog(null,"hola");
        return "/enviarMensaje?faces-redirect=true";
    }    
    public String bandejaEntrada()
    {
        return "/bandejadeEntrada?faces-redirect=true";
    }
    public String bandejaSalida()
    {
        return "/bandejadeSalida?faces-redirect=true";
    }
}
