/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.modelo;

import java.util.*;

/**
 *
 * @author Carlos E Quimbay
 */
public class Tema {
    
    private String tema;
    private Dato dato;
    private Map cultivos;

    public Tema(String tema, Dato dato) {
        this.tema = tema;
        this.dato = dato;
    }

    public Tema() {
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Dato getDato() {
        return dato;
    }

    public void setDato(Dato dato) {
        this.dato = dato;
    }

    public Map getCultivos() {
        return cultivos;
    }

    public void setCultivos(Map cultivos) {
        this.cultivos = cultivos;
    }
    
}
