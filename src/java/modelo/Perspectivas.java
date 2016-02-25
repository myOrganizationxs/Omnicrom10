/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jesus
 */
public class Perspectivas {
    private String Objetivo; 
    private String Indicadores;
    private String Meta;
    private String Actual;
    private String Anterior; 
    private String Fecha;

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
    public void setObjetivo(String objetivo) {
        this.Objetivo = objetivo;
    }

    public void setIndicadores(String indicadores) {
        this.Indicadores = indicadores;
    }

    public void setMeta(String meta) {
        this.Meta = meta;
    }

    public void setActual(String actual) {
        this.Actual = actual;
    }

    public void setAnterior(String anterior) {
        this.Anterior = anterior;
    }

    public String getObjetivo() {
        return Objetivo;
    }

    public String getIndicadores() {
        return Indicadores;
    }
    

    public String getMeta() {
        return Meta;
    }

    public String getActual() {
        return Actual;
    }

    public String getAnterior() {
        return Anterior;
    }
    
}

