/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author MariaPaula
 */
public class Servidor extends Conexion {

    Queue<String> queue = new LinkedList<String>();
    List<Suscriptor> clientes = new ArrayList<Suscriptor>();
    List<String> topicos = new ArrayList<String>();
    Integer numClientes;
    
    public Servidor() throws IOException {
        super("servidor");
    } //Se usa el constructor para servidor de Conexion

    public void startServer()//Método para iniciar el servidor
    {

        try {
            System.out.println("Esperando..."); //Esperando conexión
            
            //Muestra cliente cargados en el servidor
            for (Suscriptor s : this.clientes) {
                System.out.println("cliente: " + s.getName());
            }
            for (String s : this.topicos) {
                System.out.println("topico: " + s);
            }

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());
            System.out.println("Nuevo cliente"+cs.getOutputStream());
            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada");
            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            {
                while ((mensajeServidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
                {
                    //Se muestra por pantalla el mensaje recibido
                    System.out.println(mensajeServidor);

                }
            }

            System.out.println("Fin de la conexión");

            ss.close();//Se finaliza la conexión con el cliente
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Suscriptor> getClientes() {
        return clientes;
    }

    public void setClientes(List<Suscriptor> clientes) {
        this.clientes = clientes;
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    public Integer getNumClientes() {
        return numClientes;
    }

    public void setNumClientes(Integer numClientes) {
        this.numClientes = numClientes;
    }

}
