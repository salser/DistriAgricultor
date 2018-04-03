import co.edu.javeriana.distri.agricultor.modelo.Informacion;
import co.edu.javeriana.distri.agricultor.project.Datos;
import co.edu.javeriana.distri.agricultor.project.Manejador;
import co.edu.javeriana.distri.agricultor.utils.LeerArchivo;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

// Server class
public class Server {

    public List<String> Topicos;

    public static void main(String[] args) throws IOException {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);
        List<String> clientes = new ArrayList<>();
        Datos dat = new Datos();
        //Quemando topicos
        List<Informacion> topics = new ArrayList<>();
        
        dat.top_info.put("Clima", topics);
        dat.top_info.put("Productos", topics);
        dat.top_info.put("Insumos", topics);
        dat.top_info.put("Precios", topics);
        
        Thread hilo = new LeerArchivo(dat);
        hilo.start();
        
        List to = new ArrayList();
        to.add("topico1");
        to.add("topico2");
        dat.setTopicos(to); 
    // running infinite loop for getting
                // client request
        while (true) {
            Socket s = null;

            try {
                // socket object to receive incoming client requests
                s = ss.accept();

                clientes.add(s.toString());
                System.out.println("Clientes" + clientes.toString());
                System.out.println("A new client is connected : " + s);

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
                //System.out.println(dat.top_info.toString());
                
                
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
