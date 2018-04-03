
import co.edu.javeriana.distri.agricultor.project.Datos;
import co.edu.javeriana.distri.agricultor.project.Manejador;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

// Server class
public class Server {

    private static final int PORT = 5056;
    private static final String HOST = "127.0.0.1";
    private static final int SEND_DATA = 1024;
    private static final int RECIEVE_DATA = 1024;
    private static final String TOPICS = "topics";
    private static final String DATA_CREATE = "dataCreate";
    private static final String TOPIC1 = "topic1";
    private static final String TOPIC2 = "topic2";

    public List<String> Topicos;

    public static void main(String[] args) throws IOException {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(PORT);
        List<String> clientes = new ArrayList<>();
        Datos dat = new Datos();
        List to = new ArrayList();
        to.add(TOPIC1);
        to.add(TOPIC2);
        dat.setTopicos(to);
        Map<String, List<String>> top_cli = new HashMap<String, List<String>>();
        for (String topic : dat.getTopicos()) {
            top_cli.put(topic, new ArrayList<>());
        }
        dat.setTop_usu(top_cli);
        dat.setUsuarios(clientes);
        // TODO set information of every topic
        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;

            try {
                // socket object to receive incoming client requests
                s = ss.accept();
                
                System.out.println("Clientes" + clientes.toString());
                System.out.println("A new client is connected : " + s);
                dat.getUsuarios().add(s.toString());
                
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
