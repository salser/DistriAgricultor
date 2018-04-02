/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.utils;

import co.edu.javeriana.distri.agricultor.modelo.Informacion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Carlos E Quimbay
 * 
 * para ejecutar hilo desde Main
 * (new Thread(new CrearInformacion())).start();
 */
public class EscribirArchivo {

    private Formatter output;
    private FileWriter writer;
    private Scanner input;
    private Sincronizar sync;

    public void abrirArchivo() {

        File f = new File("informacion.txt");
        try {
            if (!f.exists() && f.isDirectory()) {
                output = new Formatter("informacion.txt");
            } else {
                writer = new FileWriter("informacion.txt", true);
                output = new Formatter(writer);
            }
        } catch (SecurityException s) {
            System.err.println("No se puede escribir en archivo.");
            System.exit(1); // termina el programa
        } catch (FileNotFoundException fnf) {
            System.err.println("Error creando / abriendo archivo.");
            System.exit(1); // termina el programa
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarInformacion() {

        System.out.println("Ingrese informacion. Presione ctrl + z al finalizar");

        Informacion info = new Informacion();

        while (input.hasNext()) {
            System.out.printf("%s\n%s",
                    "Digite tipo de informacion, cultivo, informacion.",
                    "? ");
            try {
                info.setTipoInfo(input.next());
                info.setCultivo(input.next());
                info.setInformacion(input.next());

                output.format("%s %s %s %s", info.getFechaInfo(), info.getTipoInfo(),
                        info.getCultivo(), info.getInformacion());
            } catch (FormatterClosedException fce) {
                System.err.println("Error escribiendo archivo.");
                return;
            } catch (NoSuchElementException nse) {
                System.err.println("Input invalido. Intente de nuevo.");
                input.nextLine();
            }
        }

    }

    public void run() {

        System.out.println("Escribiendo archivo.");
        Random random = new Random();
        Informacion info = new Informacion();
        sync = new Sincronizar();
        this.abrirArchivo();

        try {
            info.setTipoInfo(input.next());
            info.setCultivo(input.next());
            info.setInformacion(input.next());

            output.format("%s %s %s %s", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());
            sync.write();
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                System.err.println("Error de hilo de escritura");
            }
            System.out.println("Finalice escritura");
        } catch (FormatterClosedException fce) {
            System.err.println("Error escribiendo archivo.");
            return;
        } catch (NoSuchElementException nse) {
            System.err.println("Input invalido. Intente de nuevo.");
            input.nextLine();
        }
        this.cerrarArchivo();
    }

    public void cerrarArchivo() {

        if (output != null) {
            output.close();
        }
    }
}
