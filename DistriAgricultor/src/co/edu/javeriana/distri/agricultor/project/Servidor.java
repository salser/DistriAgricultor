/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

import co.edu.javeriana.distri.agricultor.modelo.Agricultor;
import co.edu.javeriana.distri.agricultor.modelo.Dato;
import co.edu.javeriana.distri.agricultor.utils.UtilsAgricultor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 *
 * @author Henry Salazar
 */
public class Servidor {

    protected Agricultor agricultor;
    protected Dato dato;
    protected DatagramSocket serverSocket;

    private static final int SEND_DATA = 1024;
    private static final int RECIEVE_DATA = 1024;
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 2020;
    private static final String TOPICS = "topics";
    private static final String TOPIC1 = "topico1";
    private static final String TOPIC2 = "topico2";
    private static final String TOPICS_STRING = TOPIC1 + "," + TOPIC2;

    public Servidor(int datosEnvio, int datosRecibir) {
        this.agricultor = new Agricultor();
        this.dato = new Dato();
        try {
            this.serverSocket = new DatagramSocket(PORT);
        } catch (SocketException ex) {
            System.out.println("error abriendo socket servidor");
        }
    }

    public static void main(String args[]) {
        System.out.println("inicia main");
        Servidor servidor = new Servidor(SEND_DATA, RECIEVE_DATA);
        servidor.iniciarServidor();
    }

    public void iniciarServidor() {
        System.out.println("Servidor corriendo...y esperando ");
        while (true) {
            try {
                DatagramPacket paqueteRecibe = new DatagramPacket(new byte[RECIEVE_DATA], RECIEVE_DATA);

                serverSocket.receive(paqueteRecibe);
                byte[] receiveData = new byte[RECIEVE_DATA];
                receiveData = paqueteRecibe.getData();
                String message = new String(receiveData);
                if (message.contains(TOPICS)) {
                    System.out.println("Entro a topics");
                    InetAddress ipClient = paqueteRecibe.getAddress();
                    int port = paqueteRecibe.getPort();
                    byte[] sendData = new byte[SEND_DATA];
                    sendData = TOPICS_STRING.getBytes();
                    DatagramPacket envia = new DatagramPacket(sendData, sendData.length, ipClient, port);
                    serverSocket.send(envia);
                    paqueteRecibe = new DatagramPacket(new byte[RECIEVE_DATA], RECIEVE_DATA);

                    serverSocket.receive(paqueteRecibe);
                    receiveData = new byte[RECIEVE_DATA];
                    receiveData = paqueteRecibe.getData();
                    String topic = new String(receiveData);
                    if (topic.contains(TOPIC1)) {
                        System.out.println("topic1");
                        // GUARDAR CLIENTE TOPICO 1
                    } else if (topic.contains(TOPIC2)) {
                        System.out.println("topic2");
                        // GUARDAR CLIENTE TOPICO 2
                    }
                }
            } catch (IOException ex) {
            }
        }
    }

}
