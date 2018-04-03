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
import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client class
public class Client {

    public static void main(String[] args) throws IOException {
        try {
            Scanner scn = new Scanner(System.in);
            
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true) {
                System.out.println(dis.readUTF());
                printMenu();

                String tosend = scn.nextLine();
                
                
                System.out.println("se digito" + tosend);
                int opcion = Integer.parseInt(tosend);
                
                switch (opcion) {
                    case 1:
                        tosend = new String("topicos");
                        dos.writeUTF(tosend);
                        String received = dis.readUTF();
                        System.out.println(received);
                        System.out.println("Digite el nombre del topico ");
                        tosend = scn.nextLine();
                        dos.writeUTF(tosend);
                        //input.nextInt();
                        break;
                    case 2:
                        //dataCreate();
                        //printMenu();
                        //input.nextInt();
                        break;
                    case 3: // Ver estadisticas de los tópicos subscrito
                        //printMenu();
                        //input.nextInt();
                        break;
                    default:
                        System.out.println("No es una opción");
                        //printMenu();
                        //input.nextInt();
                        break;
                }
                
                // If client sends exit,close this connection 
                // and then break from the while loop
                if (tosend.equals("Exit")) {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                String received = dis.readUTF();
                System.out.println(received);
            }

            // closing resources
            scn.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
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

        //Validar usuario
        Agricultor a = new Agricultor();
        a.setId(Long.parseLong(id));
        System.out.println("Ingrese su nombre:");
        a.setNombre(input.nextLine());

        Cultivo c = new Cultivo();
        c.setIdAgricultor(Long.parseLong(id));
        System.out.println("Ingrese la ubicacion de su cultivo:");
        c.setUbicacion(input.nextLine());
        System.out.println("Ingrese el tipo de su cultivo:");
        c.setTipoCultivo(input.nextLine());
        System.out.println("Ingrese el tamano de su cultivo:");
        c.setTamCultivo(input.nextLine());

    }
}
