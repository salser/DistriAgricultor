/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

import co.edu.javeriana.distri.agricultor.modelo.Agricultor;
import co.edu.javeriana.distri.agricultor.modelo.Cultivo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henry Salazar
 */
public class Cliente {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 2020;
    private static final int SEND_DATA = 1024;
    private static final int RECIEVE_DATA = 1024;
    private static byte[] sendData;
    private static byte[] receiveData;
    private static final String TOPICS = "topics";
    private static String cedula;

    private static final String DATA_CREATE = "dataCreate";

    DatagramSocket clientSocket;
    InetAddress IPAddress;

    public Cliente() {
        try {
            this.IPAddress = InetAddress.getByName(HOST);
            this.clientSocket = new DatagramSocket();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) throws UnknownHostException, IOException {

       Cliente cliente = new Cliente();

        iniciarSesion();
        //registro();
        startMenu(cliente);
    }

    private static void iniciarSesion() throws IOException {
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        Scanner input = new Scanner(System.in);
        int opcion = input.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Digite cédula");
                String cc = input.nextLine();
                cedula = cc;
                conectar(cc);
                break;
            case 2:
                registro();
                break;
        }
    }

    private static void printMenu() {
        System.out.println("1. Subscribirse");
        System.out.println("2. Agregar Cultivo");
        System.out.println("3. Ver estadisticas");
        System.out.println("4. Salir");
    }

    private static void registro() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese su cedula:");
        String id = input.nextLine();
        conectar(id.toString());
        //Validar usuario
        Agricultor a = new Agricultor();
        a.setId(Long.parseLong(id));
        System.out.println("Ingrese su nombre:");
        a.setNombre(input.nextLine());

        Cultivo c = new Cultivo();
        // c.setIdAgricultor(Long.parseLong(id));
        System.out.println("Ingrese la ubicacion de su cultivo:");
        c.setUbicacion(input.nextLine());
        System.out.println("Ingrese el tipo de su cultivo:");
        c.setTipoCultivo(input.nextLine());
        System.out.println("Ingrese el tamano de su cultivo:");
        c.setTamCultivo(input.nextLine());

    }

    private static void startMenu(Cliente cliente) throws IOException {
        printMenu();
        Scanner input = new Scanner(System.in);
        int opcion = input.nextInt();
        while (4 != opcion) {
            switch (opcion) {
                case 1:
                    subscribeTopics(cliente);
                    printMenu();
                    input.nextInt();
                    break;
                case 2:
                    dataCreate();
                    printMenu();
                    input.nextInt();
                    break;
                case 3: // Ver estadisticas de los tópicos subscrito
                    printMenu();
                    input.nextInt();
                    break;
                default:
                    System.out.println("No es una opción");
                    printMenu();
                    input.nextInt();
                    break;
            }
        }
    }

    private static void subscribeTopics(Cliente cliente) throws IOException {
        String msj = TOPICS;
        byte[] sendData = new byte[SEND_DATA];
        byte[] receiveData = new byte[RECIEVE_DATA];
        sendData = msj.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        cliente.clientSocket.send(sendPacket);
        DatagramPacket receive = new DatagramPacket(receiveData, receiveData.length);
        cliente.clientSocket.receive(receive);
        receiveData = receive.getData();
        String topics = new String(receiveData);
        System.out.println("Los tópicos son los siguientes: ");
        StringTokenizer st = new StringTokenizer(topics, ",");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken().trim());
        }
        System.out.println("Digite exactamente el nombre del tópico");
        Scanner input = new Scanner(System.in);
        String selectedTopic = input.nextLine();
        sendData = selectedTopic.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        cliente.clientSocket.send(sendPacket);
    }

    private static void conectar(String id) throws IOException {

        sendData = new byte[SEND_DATA];
        receiveData = new byte[RECIEVE_DATA];
        sendData = id.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        Cliente cliente = new Cliente();
        cliente.clientSocket.send(sendPacket);

    }

    private static void dataCreate() throws UnknownHostException, IOException {
        String msj = DATA_CREATE;
        byte[] sendData = new byte[SEND_DATA];
        byte[] receiveData = new byte[RECIEVE_DATA];
        sendData = msj.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        Cliente cliente = new Cliente();
        cliente.clientSocket.send(sendPacket);
        DatagramPacket receive = new DatagramPacket(receiveData, receiveData.length);
        cliente.clientSocket.receive(receive);
        receiveData = receive.getData();
        String topics = new String(receiveData);
        System.out.println("Los tópicos son los siguientes: ");
        StringTokenizer st = new StringTokenizer(topics, ",");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken().trim());
        }
        System.out.println("Digite exactamente el nombre del tópico");
        Scanner input = new Scanner(System.in);
        String selectedTopic = input.nextLine();
        System.out.println("Digite de que es el cultivo: ");
        String cultivo = input.nextLine().trim();
        System.out.println("Digite digite fecha dato: ");
        String fecha = input.nextLine().trim();
        System.out.println("Digite Tipo de info: ");
        String tipo = input.nextLine();
        System.out.println("Digite información adicional: ");
        String info = input.nextLine();
        String toSend = selectedTopic + "-" + cultivo + "-" + fecha + "-" + tipo + "-" + info;
        sendData = toSend.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        cliente.clientSocket.send(sendPacket);
    }

    public static byte[] getSendData() {
        return sendData;
    }

    public static void setSendData(byte[] sendData) {
        Cliente.sendData = sendData;
    }

    public static byte[] getReceiveData() {
        return receiveData;
    }

    public static void setReceiveData(byte[] receiveData) {
        Cliente.receiveData = receiveData;
    }

    public static String getCedula() {
        return cedula;
    }

    public static void setCedula(String cedula) {
        Cliente.cedula = cedula;
    }

    public DatagramSocket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public InetAddress getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(InetAddress IPAddress) {
        this.IPAddress = IPAddress;
    }
    
    
}
