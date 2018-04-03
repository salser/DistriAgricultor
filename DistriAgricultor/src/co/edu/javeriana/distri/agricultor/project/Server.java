package co.edu.javeriana.distri.agricultor.project;

import co.edu.javeriana.distri.agricultor.project.Datos;
import co.edu.javeriana.distri.agricultor.project.Manejador;
import java.io.*;
import java.util.*;
import java.net.*;

// Server class
public class Server {

    private static final int PORT = 5056;
    private static final String HOST = "127.0.0.1";
    private static final String CLIMA = "Clima";
    private static final String PRODUCTOS = "Productos";
    private static final String INSUMOS = "Insumos";
    private static final String PRECIOS = "Precios";

    public List<String> Topicos;

    public static void main(String[] args) throws IOException {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(PORT);
        List<String> clientes = new ArrayList<>();
        Datos dat = new Datos();
        //Quemando topicos
        

        List<String> topicsAux = new ArrayList<String>();
        topicsAux.add(CLIMA);
        topicsAux.add(PRODUCTOS);
        topicsAux.add(INSUMOS);
        topicsAux.add(PRECIOS);
        dat.setTopicos(topicsAux);
        Map<String, List<Socket>> top_cli = new HashMap<String, List<Socket>>();
        for (String topic : topicsAux) {
            top_cli.put(topic, new ArrayList<>());
        }
        dat.setTop_usu(top_cli);
        dat.top_info.put(CLIMA, new ArrayList<>());
        dat.top_info.put(PRODUCTOS, new ArrayList<>());
        dat.top_info.put(INSUMOS, new ArrayList<>());
        dat.top_info.put(PRECIOS, new ArrayList<>());

        /*Thread hilo = new LeerArchivo(dat);
        hilo.start();*/
        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;

            try {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("Clientes" + clientes.toString());
                System.out.println("A new client is connected : " + s);
                dat.getUsuarios().add(s);
                dat.getCultivos_usu().put(s, new ArrayList<>());

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object 
                Thread t = new Manejador(s, dis, dos, dat);

                // Invoking the start() method
                t.start();

                System.out.println("Topicos del servidor");
                System.out.println(dat.topicos.toString());
            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }

    public Server() {
        this.Topicos = new ArrayList<String>();
    }

}
