/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainServidor {

    public static void main(String[] args) throws IOException {
        Servidor serv = new Servidor(); //Se crea el servidor
        //Carga de Usuarios
        Suscriptor su = new Suscriptor(1, "Maria", "Bogota", null, "arenoso");
        Suscriptor su2 = new Suscriptor(2, "Jose", "Bogota", null, "arenoso");

        List<Suscriptor> Lsuscriptores = new ArrayList<>();
        Lsuscriptores.add(su);
        Lsuscriptores.add(su2);
        serv.setClientes(Lsuscriptores);
        
        //Carga de topicos
        List<String> Ltopicos = new ArrayList<>();
        Ltopicos.add("Cultivo secos");
        Ltopicos.add("Cultivo humedos");
        serv.setTopicos(Ltopicos);
        
        serv.setNumClientes(2);
        
        System.out.println("Iniciando servidor\n");

        
        serv.startServer(); //Se inicia el servidor

    }
}
