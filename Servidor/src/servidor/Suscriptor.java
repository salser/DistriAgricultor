/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MariaPaula
 */
public class Suscriptor {
    private Integer id;
    private String name;
    private String direccion;
    private List<String> topicos;
    private String terreno;

    public Suscriptor(Integer id, String name, String direccion, List<String> topicos, String terreno) {
        this.name = name;
        this.direccion = direccion;
        this.topicos = topicos;
        this.terreno = terreno;
    }

public Suscriptor() {
        this.topicos = new ArrayList<String>();
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
