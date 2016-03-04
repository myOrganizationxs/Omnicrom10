/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Intefaces.CuentaUsuario;
import Pojo.Cuenta;
import Pojo.Nodo;
import Pojo.Usuario;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
public class DaoCuentaUsuario implements CuentaUsuario
{

    @Override
    public void saveCuenta(Session session, Transaction transaction, Cuenta cuenta, Date ultimaConexion, String usuario, String contrasena) throws Exception
    {
        JOptionPane.showMessageDialog(null,cuenta+" "+usuario);
        cuenta.setUsuario(usuario);
        cuenta.setUltimaConexion(ultimaConexion);
        cuenta.setUsuario(usuario);
        cuenta.setContrasena(contrasena);
        session.saveOrUpdate(cuenta);
        
        transaction.commit();  

    }

    @Override
    public void saveUsuario(Session session, Transaction transaction, Usuario usuario, String nombre, String ap, String am, Date fechaNacimiento, String correo, String telefono) throws Exception 
    {
        usuario.setNombreUsu(nombre);
        usuario.setApellidoPaterno(ap);
        usuario.setApellidoMaterno(am);
        usuario.setFechaDeNacimiento(fechaNacimiento);
        usuario.setCorreoElectro(correo);
        usuario.setTelefono(telefono);
        session.saveOrUpdate(usuario);
        JOptionPane.showMessageDialog(null,"guardado correcto");
        transaction.commit();  

    }
    
}
