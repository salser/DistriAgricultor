/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

/**
 *
 * @author MariaPaula
 */
import co.edu.javeriana.distri.agricultor.modelo.Agricultor;
import co.edu.javeriana.distri.agricultor.modelo.Cultivo;
import static co.edu.javeriana.distri.agricultor.project.Cliente.HOST;
import static co.edu.javeriana.distri.agricultor.project.Cliente.PORT;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

// Client class
public class Client {

    private static final int PORT = 5056;
    private static final String HOST = "127.0.0.1";
    private static final int SEND_DATA = 1024;
    private static final int RECIEVE_DATA = 1024;
    private static final String TOPICS = "topics";
    private static final String DATA_CREATE = "dataCreate";
    private static final String NOTICIAS = "noticias";
 private static final String CULTIVO_ADD = "cultivoAdd";

    private static InetAddress ip;
    private static Socket s;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    public Client() {
        try {
            // getting localhost ip
            ip = InetAddress.getByName(HOST);
            // establish the connection with server port 5056
            s = new Socket(ip, PORT);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {

        try {

            // the following loop performs the exchange of
            // information between client and client handler
            Client cliente = new Client();
            startMenu(cliente);

            // closing resources
            s.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startMenu(Client cliente) throws IOException {
        printMenu();
        Scanner input = new Scanner(System.in);
        int opcion = input.nextInt();
        while (5 != opcion) {
            switch (opcion) {
                case 1:
                    subscribeTopics(cliente);
                    printMenu();
                    opcion = input.nextInt();
                    break;
                case 2:
                    dataCreate(cliente);
                    printMenu();
                    opcion = input.nextInt();
                    break;
                case 3: // Ver estadisticas de los tópicos subscrito
                    verNoticias(cliente);
			printMenu();
                    opcion = input.nextInt();
                    break;
                case 4:
                    agregarCultivo(cliente);
                    printMenu();
                    opcion = input.nextInt();
                    break;
                default:
                    System.out.println("No es una opción");
                    printMenu();
                    opcion = input.nextInt();
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Subscribirse");
        System.out.println("2. Agregar Noticia");
        System.out.println("3. Ver estadisticas");
        System.out.println("4. Agregar Cultivo");
        System.out.println("5. Salir");
    }

    private static void registro() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese su cedula:");
        String id = input.nextLine();

        //Validar usuario
        Agricultor a = new Agricultor();
        a.setId(Long.parseLong(id));
        System.out.println("Ingrese su nombre:");
        a.setNombre(input.nextLine());

    }

    private static void subscribeTopics(Client cliente) throws IOException {
        String msj = TOPICS;
        Scanner scn = new Scanner(System.in);
        cliente.dos.writeUTF(msj);
        cliente.dis.readUTF();
        String received = cliente.dis.readUTF();
        System.out.println("Los tópicos son los siguientes: ");
        System.out.println(received);
        msj = scn.nextLine();
        dos.writeUTF(msj);
    }

    private static void verNoticias(Client cliente) throws IOException {
        String msj = NOTICIAS;
        Scanner scn = new Scanner(System.in);
        cliente.dos.writeUTF(msj);
        cliente.dis.readUTF();
        String received = cliente.dis.readUTF();
        String received1= cliente.dis.readUTF();
        String received2= cliente.dis.readUTF();
        String received3= cliente.dis.readUTF();
        String received4= cliente.dis.readUTF();
        System.out.println("-"+received);
        System.out.println("" +  received1+received2+received3+received4);

        //System.out.println("Los tópicos son los siguientes: ");
        //System.out.println(received);
        // msj = scn.nextLine();
        //dos.writeUTF(msj);
    }

    /*private static void conectar(String id) throws IOException {

        sendData = new byte[SEND_DATA];
        receiveData = new byte[RECIEVE_DATA];
        sendData = id.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        Cliente cliente = new Cliente();
        cliente.clientSocket.send(sendPacket);

    }*/

    private static void dataCreate(Client cliente) throws UnknownHostException, IOException {
        String msj = DATA_CREATE;
        Scanner input = new Scanner(System.in);
        cliente.dis.readUTF();
        cliente.dos.writeUTF(msj);
        String received = cliente.dis.readUTF();
        System.out.println("Los tópicos son los siguientes: ");
        System.out.println(received);
        System.out.println("Escoja uno escribalo textualmente: ");
        msj = input.nextLine();
        System.out.println("Digite de que es el cultivo: ");
        String cultivo = input.nextLine().trim();
        System.out.println("Digite digite fecha dato: ");
        String fecha = input.nextLine().trim();
        System.out.println("Digite Tipo de info: ");
        String tipo = input.nextLine();
        System.out.println("Digite información adicional: ");
        String info = input.nextLine();
        String toSend = msj + "-" + cultivo + "-" + fecha + "-" + tipo + "-" + info;
        dos.writeUTF(toSend);
    }

    private static void agregarCultivo(Client cliente) throws IOException {
        String msj = CULTIVO_ADD;
        Scanner scn = new Scanner(System.in);
        cliente.dis.readUTF();
        cliente.dos.writeUTF(msj);
        System.out.println("ubicación del cultivo: ");
        String ubicacion = scn.nextLine();
        System.out.println("tipo de cultivo: ");
        String tipo = scn.nextLine();
        System.out.println("tamaño del cultivo: ");
        String tamano = scn.nextLine();
        msj = ubicacion + "-" + tipo + "-" + tamano;
        cliente.dos.writeUTF(msj);
        String received = cliente.dis.readUTF();
        System.out.println("Por cuestiones de filtro se tomo la decición de agregarlo a los siguientes topicos y/n: ");
        System.out.println(received);
        msj = scn.nextLine();
        dos.writeUTF(msj);
    }
}
