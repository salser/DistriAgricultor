/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

import co.edu.javeriana.distri.agricultor.modelo.Agricultor;
import co.edu.javeriana.distri.agricultor.modelo.Dato;
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

    public Servidor(int datosEnvio, int datosRecibir) {
        this.agricultor = new Agricultor();
        this.dato = new Dato();
        try {
            this.serverSocket = new DatagramSocket(11001);
        } catch (SocketException ex) {
            System.out.println("error abriendo socket servidor");
        }
    }

    public static void main(String args[]) {
        Servidor servidor = new Servidor(SEND_DATA, RECIEVE_DATA);
        servidor.iniciarServidor();
    }

    public void iniciarServidor() {
        while (true) {
            try {
                DatagramPacket paqueteRecibe = new DatagramPacket(new byte[1024], 1024);

                serverSocket.receive(paqueteRecibe);
                Agricultor agricultor = (Agricultor) deserialize(paqueteRecibe.getData());
                // TODO hacer inserción agricultor a la DB con el tema escogido
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

}
