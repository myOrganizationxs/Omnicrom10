/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaces;

import Pojo.Cuenta;
import Pojo.Nodo;
import Pojo.Usuario;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
public interface CuentaUsuario 
{
    public void saveCuenta(Session session, Transaction transaction,Cuenta cuenta,Date ultimaConexion,String usuario, String contrasena)throws Exception;
    public void saveUsuario(Session session,Transaction transaction,Usuario usuario,String nombre, String ap, String am,Date fechaNacimiento,String correo,String telefono)throws Exception;
    
}
