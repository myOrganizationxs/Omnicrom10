/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeanRequest;

import Dao.DaoCuenta;
import Dao.DaoCuentaUsuario;
import Dao.DaoMensajes;
import Dao.DaoNodo;
import HibernateUtil.HibernateUtil;
import Pojo.Cuenta;
import Pojo.Nodo;
import Pojo.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
@ManagedBean(name="MbCuentaUsuario")
@RequestScoped
public class MbCuentaUsuario implements Serializable
{
    private Session session;
    private Transaction transaction;
    private String usuario,contrasena,nuevaContrasena,nombre,ap,am,correo,telefono;
    private int idCuenta;
    private Date fechaNacimiento;
    private Cuenta cuenta;
    private Usuario usuarios;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaActual;
    private int idUsuario;
    
    public MbCuentaUsuario() 
    {
        
    }
    
    public String getTodo()
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            DaoCuenta daoCuenta=new DaoCuenta();            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);           
            List<Object[]> lista =daoCuenta.getByIdcuenta(this.session, (Integer) sessionUsuario.getAttribute("idcuenta"));          
 
                    //NombreCompleto=(String) alist[0]+" "+alist[1]+" "+alist[2];
                    //HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);                    
                    //httpSession.setAttribute("idNodo", alist[3]);
                    //JOptionPane.showMessageDialog(null,  alist.);
            this.transaction.commit(); 
            
            for(Object[] lis:lista)
            {
                cuenta = (Cuenta) lis[0];
                usuarios = (Usuario) lis[1];
                
                idCuenta =  cuenta.getIdCuenta();
                setUsuario(cuenta.getUsuario());
                contrasena = cuenta.getContrasena();
                nuevaContrasena = cuenta.getContrasena();
                
                nombre= usuarios.getNombreUsu();
                ap = usuarios.getApellidoPaterno();
                am = usuarios.getApellidoMaterno();
                fechaNacimiento = usuarios.getFechaDeNacimiento();
                correo = usuarios.getCorreoElectro();
                telefono = usuarios.getTelefono();
                idUsuario = usuarios.getIdUsuario();
            }
            return "Mi Perfil";
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            
            return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
        
    }
    
    public String guardardatos()
    {
        this.session=null;
        this.transaction=null;
        try
        {
            DaoCuentaUsuario daoCuentaUsuario = new DaoCuentaUsuario();       
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();            
            HttpSession sessionUsuario=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);      
            fechaActual = new Date();
            cuenta =(Cuenta) session.load(Cuenta.class,idCuenta);
            JOptionPane.showMessageDialog(null,cuenta+" "+getUsuario());
            //daoCuentaUsuario.saveCuenta(session, transaction,cuenta, fechaActual, usuario, contrasena);
            //daoCuentaUsuario.saveUsuario(session, transaction, usuarios, nombre, ap, am, fechaNacimiento, correo, telefono);
            return "interfazUsuario.xhtml?faces-redirect=true";
        } 
        catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
             return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    public void setNuevaContrasena(String nuevaContraseña) {
        this.nuevaContrasena = nuevaContrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    } 

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
