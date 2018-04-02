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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                String message = new String(paqueteRecibe.getData());
                System.out.println("message: " +  message);
                switch(message){
                    case TOPICS:
                }
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    

}
