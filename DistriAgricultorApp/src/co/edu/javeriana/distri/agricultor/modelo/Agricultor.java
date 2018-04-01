/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.modelo;

import java.util.*;

/**
 *
 * @author Henry Salazar
 */
public class Agricultor {
    private long id;
    private String nombre;
    private List<Cultivo> cultivos;

    public Agricultor(long id, String nombre, List<Cultivo> cultivos) {
        this.id = id;
        this.nombre = nombre;
        this.cultivos = new ArrayList<Cultivo>();
    }

    public Agricultor(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Agricultor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cultivo> getCultivos() {
        return cultivos;
    }

    public void setCultivos(List<Cultivo> cultivos) {
        this.cultivos = cultivos;
    }
    
    
    
}
