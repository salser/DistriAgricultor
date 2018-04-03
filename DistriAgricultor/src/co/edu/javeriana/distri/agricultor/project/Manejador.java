/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

import co.edu.javeriana.distri.agricultor.modelo.Informacion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author MariaPaula
 */
public class Manejador extends Thread {

    private static final int PORT = 5056;
    private static final String HOST = "127.0.0.1";
    private static final int SEND_DATA = 1024;
    private static final int RECIEVE_DATA = 1024;
    private static final String TOPICS = "topics";
    private static final String DATA_CREATE = "dataCreate";

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    public final Datos data;

    // Constructor
    public Manejador(Socket s, DataInputStream dis, DataOutputStream dos, Datos data) {

        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.data = data;
    }

    @Override
    public void run() {
        String received;
        String toreturn;
        while (true) {
            try {

                // Ask user what he wants
                dos.writeUTF("Conectado al servidor... por favor digite una opcion");

                // receive the answer from client
                received = dis.readUTF();
                System.out.println("received: " + received);
                if (received.contains(TOPICS)) {
                    System.out.println("in topics");
                    toreturn = this.data.topicos.toString();
                    dos.writeUTF(toreturn);
                    received = dis.readUTF();
                    List<String> act = data.top_usu.get(received);
                    act.add(s.toString());
                    this.data.top_usu.remove(received);
                    this.data.top_usu.put(received, act);
                    System.out.println("Data: " + this.data.getTop_usu());
                } else if (received.contains(DATA_CREATE)) {
                    System.out.println("in creation");
                    toreturn = this.data.topicos.toString();
                    dos.writeUTF(toreturn);
                    received = dis.readUTF();
                    StringTokenizer st = new StringTokenizer(received, "-");
                    String topic = st.nextToken().trim();
                    String cultivo = st.nextToken().trim();
                    String fecha = st.nextToken().trim();
                    String tipo = st.nextToken().trim();
                    String info = st.nextToken();
                    System.out.println("Agregar al tópico: " + topic + " info: " + cultivo + ", " + fecha + ", " + info);
                    Informacion informacion = new Informacion(tipo, cultivo, info);
                    informacion.setFechaInfo(fecha);
                    this.data.getTop_info().get(topic).add(informacion);
                    System.out.println("lista de top_info end: " + this.data.getTop_info());
                } else {
                    dos.writeUTF("Invalid input");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
