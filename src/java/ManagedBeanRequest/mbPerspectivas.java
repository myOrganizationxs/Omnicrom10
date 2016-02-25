
package ManagedBeanRequest;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.JOptionPane;
import modelo.Perspectivas;


@ManagedBean
@RequestScoped
public class mbPerspectivas {

    private Perspectivas per;
    private static List<Perspectivas> lista = new ArrayList(); 

    public Perspectivas getPer() {
        return per;
    }

    public void setPer(Perspectivas per) {
        this.per = per;
    }

    public List<Perspectivas> getLista() {
        return lista;
    }

    public void setLista(List<Perspectivas> lista) {
        this.lista = lista;
    }
    
   public void financiera (){
       
       per = new Perspectivas();
       per.setObjetivo("Aumentar el 10% las ventas ");
       per.setIndicadores("igual");
       per.setMeta("meta");
       per.setActual("actual");
       per.setAnterior("Anterior");
       per.setFecha("fecha");
       //JOptionPane.showMessageDialog(null,per+" "+per.getObjetivo());
       
       
       
   }
  public void clientes (){
       
       per = new Perspectivas();
       per.setObjetivo("Verificar satisfaccion del cliente y aumentar 5% la cartera del cliente");
       per.setIndicadores("igual");
       per.setMeta("meta 1");
       per.setActual("actual 1");
       per.setAnterior("Anterior 1");
       per.setFecha("fecha");
       //JOptionPane.showMessageDialog(null,per+" "+per.getObjetivo());
          
   }
   public void humano (){
       
       per = new Perspectivas();
       per.setObjetivo("otro objetivo ");
       per.setIndicadores("igual 1 1");
       per.setMeta("meta 11");
       per.setActual("actual 11");
       per.setAnterior("Anterior 11");
       per.setFecha("fecha");
       //JOptionPane.showMessageDialog(null,per+" "+per.getObjetivo());
          
   }    
   public void sustentabilidad (){
       
       per = new Perspectivas();
       per.setObjetivo("Crear una reserva ecologica para 2016");
       per.setIndicadores("igual 2");
       per.setMeta("meta 2");
       per.setActual("actual 2");
       per.setAnterior("Anterior 2");
       per.setFecha("fecha");
       //JOptionPane.showMessageDialog(null,per+" "+per.getObjetivo());
          
   }    
   public void proceso (){
       
       per = new Perspectivas();
       per.setObjetivo("Mantener la mejora continua en estructura proceso y resultados");
       per.setIndicadores("igual 3");
       per.setMeta("meta 3");
       per.setActual("actual 3");
       per.setAnterior("Anterior 3");
       per.setFecha("fecha");
       //JOptionPane.showMessageDialog(null,per+" "+per.getObjetivo()); 
   }    
}
