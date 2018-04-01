/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Carlos E Quimbay
 */
public class Informacion {
    
    private String fechaInfo;
    private String tipoInfo;
    private String cultivo;
    private String informacion;

    public Informacion(String tipoInfo, String cultivo, String informacion) {
        this.setFechaInfo();
        this.tipoInfo = tipoInfo;
        this.cultivo = cultivo;
        this.informacion = informacion;
    }

    public Informacion() {
        this("","","");
    }

    public String getFechaInfo() {
        return fechaInfo;
    }

    public final void setFechaInfo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_hh:mm_aaa");
        this.fechaInfo = sdf.format(cal.getTime());
    }

    public void setFechaInfo(String fechaInfo) {
        this.fechaInfo = fechaInfo;
    }
    
    public String getTipoInfo() {
        return tipoInfo;
    }

    public void setTipoInfo(String tipoInfo) {
        this.tipoInfo = tipoInfo;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
    
}
