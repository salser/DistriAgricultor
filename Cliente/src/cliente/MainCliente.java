/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;

/**
 *
 * @author MariaPaula
 */
public class MainCliente {
    public static void main(String[] args) throws IOException
    {
        Cliente cli = new Cliente(); //Se crea el cliente
        
        System.out.println("Accion que desea realiza es:"
                + "1. Publicar"
                + "2. leer"
                + "3. Suscribirce a topico");

        System.out.println("Iniciando cliente\n");
        cli.startClient(); //Se inicia el cliente
    }
}
