/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbFoto")
@RequestScoped
public class MbFoto implements Serializable
{
    
    UploadedFile foto;

    /**
     * Creates a new instance of MbFoto
     */
    public MbFoto() 
    {
        
    }
    public void prueba()
    {
        
    }
    public void actualizarFoto()
    {
        
        InputStream inputStream=null;
        OutputStream outputStream=null;
        
        try
        {
            ServletContext servletContext =(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String carpetaFoto = (String) servletContext.getRealPath("/fotoUsuario");
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            //JOptionPane.showConfirmDialog(null, sessionUsuario.getAttribute("idUsuario"));
            outputStream = new  FileOutputStream(new File (carpetaFoto+"/"+sessionUsuario.getAttribute("idUsuario")+".png"));
            inputStream= this.foto.getInputstream();
            int read=0;
            byte[] bytes = new byte [1024];
            //JOptionPane.showMessageDialog(null,"pasa por aqui 2");
            while((read=inputStream.read(bytes))!=-1)
            {
                outputStream.write(bytes,0,read);
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto subida:", "felicidades"));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"error: "+e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+e.getMessage()));
        }
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }
    
}
