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
public class Cultivo {
    
    private String idAgricultor;
    private String ubicacion;
    private String tipoCultivo;
    private String tamCultivo;

    public Cultivo(String idAgricultor, String ubicacion, String tipoCultivo, String tamCultivo) {
        this.idAgricultor = idAgricultor;
        this.ubicacion = ubicacion;
        this.tipoCultivo = tipoCultivo;
        this.tamCultivo = tamCultivo;
    }

    public Cultivo() {
    }

    public String getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(String idAgricultor) {
        this.idAgricultor = idAgricultor;
    }


    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipoCultivo() {
        return tipoCultivo;
    }

    public void setTipoCultivo(String tipoCultivo) {
        this.tipoCultivo = tipoCultivo;
    }

    public String getTamCultivo() {
        return tamCultivo;
    }

    public void setTamCultivo(String tamCultivo) {
        this.tamCultivo = tamCultivo;
    }
    
    
}
