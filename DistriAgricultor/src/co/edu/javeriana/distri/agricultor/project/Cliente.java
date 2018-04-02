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
        
        Scanner input = new Scanner(System.in);
        
        registro();
        startMenu();
        
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
        a.setNombre( input.nextLine());
        
        Cultivo c = new Cultivo();
        c.setIdAgricultor(Long.parseLong(id));
        System.out.println("Ingrese la ubicacion de su cultivo:");
        c.setUbicacion(input.nextLine());
        System.out.println("Ingrese el tipo de su cultivo:");
        c.setTipoCultivo(input.nextLine());
        System.out.println("Ingrese el tamano de su cultivo:");
        c.setTamCultivo(input.nextLine());

        
    }

    private static void startMenu() throws IOException {
        printMenu();
        Scanner input = new Scanner(System.in);
        int opcion = 0;
        while (4 != opcion) {
            opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    subscribeTopics("Topics");
                    printMenu();
                    break;
                case 2: //TODO agregar CULTIVO
                    subscribeTopics("Cultivos");
                    printMenu();
                    break;
                case 3: // Ver estadisticas de los tópicos subscrito
                    printMenu();
                    break;
                default:
                    System.out.println("No es una opción");
                    printMenu();
                    break;
            }

        }
    }

    private static void subscribeTopics(String msj) throws IOException {
        sendData = msj.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        Cliente cliente = new Cliente();
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
}
