/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

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
    public List<Socket> usuarios;
    public Map<String, List<String>> top_usu;
    public Map<String, List<Informacion>> top_info;

    public Datos() {
        this.topicos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.top_usu = new HashMap<String, List<String>>();
        this.top_info = new HashMap<String, List<Informacion>>();
    }

    public Datos(List<String> topicos, Map<String, List<String>> top_usu, Map<String, List<Informacion>> top_info) {
        this.topicos = topicos;
        this.usuarios = new ArrayList<>();
        this.top_usu = top_usu;
        this.top_info = top_info;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    public List<Socket> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Socket> usuarios) {
        this.usuarios = usuarios;
    }

    public Map<String, List<String>> getTop_usu() {
        return top_usu;
    }

    public void setTop_usu(Map<String, List<String>> top_usu) {
        this.top_usu = top_usu;
    }

    public Map<String, List<Informacion>> getTop_info() {
        return top_info;
    }

    public void setTop_info(Map<String, List<Informacion>> top_info) {
        this.top_info = top_info;
    }

    @Override
    public String toString() {
        return "Datos{" + "topicos=" + topicos + ", usuarios=" + usuarios + ", top_usu=" + top_usu + ", top_info=" + top_info + '}';
    }

}
