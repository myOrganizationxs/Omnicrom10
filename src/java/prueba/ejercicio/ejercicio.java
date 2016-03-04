/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.ejercicio;

import Pojo.Cuenta;
import Pojo.Mensajes;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author sergio
 */
public class ejercicio implements Serializable
{
    //from Cuenta c inner join c.usuarios u inner join u.nodo n inner join n.mensajeses where IdCuenta=0
    private Session sesion;
    private Mensajes mensajes;
    
    private void inciaOperacion()
    {
            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            sesion = sessionFactory.openSession();
            sesion.getTransaction().begin();
    }
    
    private void terminaOperacion()
    {
            sesion.getTransaction().commit();
            sesion.close();
    }
    private void obtenNombres()
    {
            inciaOperacion();

            Query query = sesion.createQuery("Select u.nombreUsu FROM Usuario u");

            List<String> listaResultados = query.list();

            for (int i = 0; i < listaResultados.size(); i++)
            {
                System.out.println("Nombre " + i + ": " + listaResultados.get(i));
            }

            terminaOperacion();    
    }
    private void obtenNombresYPasswords()
    {
            inciaOperacion();

            Query query = sesion.createQuery("Select u.nombreUsu,u.apellidoPaterno FROM Usuario u");

            List<Object[]> listaResultados = query.list();

            for (int i = 0; i < listaResultados.size(); i++)
            {
                System.out.println("Nombre: " + i + ": " + listaResultados.get(i)[0] + ", Apellido Paterno: " + listaResultados.get(i)[1]);
            }

            terminaOperacion();
    }
    
    private void obtenNombresYPasswordsComoMapa()
    {
        inciaOperacion();

        Query query = sesion.createQuery("SELECT new map(u.nombreUsu as nombre, u.apellidoPaterno as paterno, u.apellidoMaterno as materno) FROM Usuario as u ");

        List<Map> listaResultados = query.list();

        for (int i = 0; i < listaResultados.size(); i++)
        {
            Map mapa = listaResultados.get(i);

            System.out.println("Datos del mapa " + i);

            Set llaves = mapa.keySet();

            for(Iterator<String> it = llaves.iterator(); it.hasNext();)
            {
                String llaveActual = it.next();

                System.out.println("\tLlave: " + llaveActual + ", valor: " + mapa.get(llaveActual));
            }
        }

        terminaOperacion();
    }

    public List<Cuenta> getUsuariosConComprasInactivas(String usuario) throws HibernateException
    {
        List<Cuenta> listaUsuarios = null;

        try
        {
            inciaOperacion();

            String hql="from Cuenta c inner join c.usuarios  u where c.usuario=:usuario";
            Query query=sesion.createQuery(hql);
            query.setParameter("usuario",usuario);

            listaUsuarios = query.list();

        }catch(HibernateException he)
        {
            he.printStackTrace();
        }finally
        {
            terminaOperacion();
        }
        System.out.println();
        return listaUsuarios;
    }
    
    private void obtenerusu()
    {
        inciaOperacion();

        Query query = sesion.createQuery("Select u.nombreUsu,u.apellidoPaterno FROM Usuario u");

            List<Object[]> listaResultados = query.list();

            for (int i = 0; i < listaResultados.size(); i++)
            {
                System.out.println("Nombre: " + i + ": " + listaResultados.get(i)[0] + ", Apellido Paterno: " + listaResultados.get(i)[1]);
            }

        terminaOperacion();
    }
    
    public void añadirMensaje()
    {
        
    }
    
    public static void main(String args[])
    {
        ejercicio eje = new ejercicio();
       
        //eje.obtenNombres();
        //eje.obtenNombresYPasswords();
        //eje.obtenNombresYPasswordsComoMapa();
        //eje.getUsuariosConComprasInactivas("123456");
        //eje.obtenNombresYPasswords();
        eje.añadirMensaje();
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }    
}
