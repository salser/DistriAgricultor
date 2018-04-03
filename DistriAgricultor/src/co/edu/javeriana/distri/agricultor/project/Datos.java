/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

import co.edu.javeriana.distri.agricultor.modelo.Cultivo;
import co.edu.javeriana.distri.agricultor.modelo.Informacion;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MariaPaula
 */
public class Datos {

    public List<String> topicos;
    public Map<String, String> usuarios;
    public Map<String, List<Socket>> top_usu;
    public Map<String, List<Informacion>> top_info;
    public Map<Socket, List<Cultivo>> cultivos_usu;

    public Datos() {
        this.topicos = new ArrayList<>();
        this.usuarios = new HashMap<String, String> ();
        this.top_usu = new HashMap<String, List<Socket>>();
        this.top_info = new HashMap<String, List<Informacion>>();
        this.cultivos_usu = new HashMap<Socket, List<Cultivo>>();
        
    }

    public Datos(List<String> topicos, Map<String, String>  usuarios, Map<String, List<String>> top_usu, Map<String, List<Informacion>> top_info) {
        this.topicos = topicos;
        this.usuarios = usuarios;
        this.top_info = top_info;
        this.cultivos_usu = new HashMap<Socket, List<Cultivo>>();
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    public Map<String, String>  getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, String>  usuarios) {
        this.usuarios = usuarios;
    }

    public Map<String, List<Socket>> getTop_usu() {
        return top_usu;
    }

    public void setTop_usu(Map<String, List<Socket>> top_usu) {
        this.top_usu = top_usu;
    }

    public Map<String, List<Informacion>> getTop_info() {
        return top_info;
    }

    public void setTop_info(Map<String, List<Informacion>> top_info) {
        this.top_info = top_info;
    }

    public Map<Socket, List<Cultivo>> getCultivos_usu() {
        return cultivos_usu;
    }

    public void setCultivos_usu(Map<Socket, List<Cultivo>> cultivos_usu) {
        this.cultivos_usu = cultivos_usu;
    }

    @Override
    public String toString() {
        return "Datos{" + "topicos=" + topicos + ", usuarios=" + usuarios + ", top_usu=" + top_usu + ", top_info=" + top_info + '}';
    }

}
