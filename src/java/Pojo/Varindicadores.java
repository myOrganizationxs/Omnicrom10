package Pojo;
// Generated 25/02/2016 11:53:01 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Varindicadores generated by hbm2java
 */
public class Varindicadores  implements java.io.Serializable {


     private Integer idvarIndicadores;
     private Indicadores indicadores;
     private Date periodo;
     private Double variableIndicador;

    public Varindicadores() {
    }

	
    public Varindicadores(Indicadores indicadores) {
        this.indicadores = indicadores;
    }
    public Varindicadores(Indicadores indicadores, Date periodo, Double variableIndicador) {
       this.indicadores = indicadores;
       this.periodo = periodo;
       this.variableIndicador = variableIndicador;
    }
   
    public Integer getIdvarIndicadores() {
        return this.idvarIndicadores;
    }
    
    public void setIdvarIndicadores(Integer idvarIndicadores) {
        this.idvarIndicadores = idvarIndicadores;
    }
    public Indicadores getIndicadores() {
        return this.indicadores;
    }
    
    public void setIndicadores(Indicadores indicadores) {
        this.indicadores = indicadores;
    }
    public Date getPeriodo() {
        return this.periodo;
    }
    
    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }
    public Double getVariableIndicador() {
        return this.variableIndicador;
    }
    
    public void setVariableIndicador(Double variableIndicador) {
        this.variableIndicador = variableIndicador;
    }




}


