/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

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
        startMenu();
    }

    private static void printMenu() {
        System.out.println("1. Subscribirse");
        System.out.println("2. Agregar Dato");
        System.out.println("3. Ver estadisticas");
        System.out.println("4. Salir");
    }

    private static void startMenu() throws IOException {
        printMenu();
        Scanner input = new Scanner(System.in);
        int opcion = input.nextInt();
        while (4 != opcion) {
            switch (opcion) {
                case 1:
                    subscribeTopics();
                    printMenu();
                    input.nextInt();
                    break;
                case 2: //TODO agregar dato
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

    private static void subscribeTopics() throws IOException {
        String msj = "topics";
        byte[] sendData = new byte[SEND_DATA];
        byte[] receiveData = new byte[RECIEVE_DATA];
        sendData = msj.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        Cliente cliente = new Cliente();
        cliente.clientSocket.send(sendPacket);
    }
}
