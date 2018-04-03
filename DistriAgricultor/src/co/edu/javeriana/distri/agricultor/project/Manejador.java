/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MariaPaula
 */
public class Manejador extends Thread 
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    public final Datos data;
     
 
    // Constructor
    public Manejador(Socket s, DataInputStream dis, DataOutputStream dos, Datos data) 
    {
        
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.data = data;
    }
 
    @Override
    public void run() 
    {
        String received;
        String toreturn;
        while (true) 
        {
            try {
 
                // Ask user what he wants
                dos.writeUTF("Conectado al servidor... por favor digite una opcion");
                 
                // receive the answer from client
                received = dis.readUTF();
                if(received.contains("topicos")){
                    //System.out.println("Estos son los topicos");
                    //System.out.println(this.data.topicos.toString());
                    
                    this.data.topicos.add("NUEVO");
                    toreturn = this.data.topicos.toString();
                    dos.writeUTF(toreturn);
                }else if(received.contains("cultivo")){
                    
                }else{
                        dos.writeUTF("Invalid input");
                        break;
                }
                if(received.equals("Exit"))
                { 
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
                 
                // creating Date object
                Date date = new Date();
                 
                // write on output stream based on the
                // answer from the client
               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
        
        
}
