/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.modelo;

/**
 *
 * @author Henry Salazar
 */
public class Dato {
    private long id;
    private String nombre;
    private String descripción;

    public Dato(long id, String nombre, String descripción) {
        this.id = id;
        this.nombre = nombre;
        this.descripción = descripción;
    }

    public Dato() { this(0, "nombre Dato", "descripcion Dato"); }

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

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }
       
}
