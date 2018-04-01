/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.utils;

import co.edu.javeriana.distri.agricultor.modelo.Informacion;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author Carlos E Quimbay
 */
public class EscribirArchivo {

    private Formatter output;
    private Scanner input;

    public void abrirArchivo() {

        try {
            output = new Formatter("informacion.txt");
        } catch (SecurityException s) {
            System.err.println("No se puede escribir en archivo.");
            System.exit(1); // termina el programa
        } catch (FileNotFoundException f) {
            System.err.println("Error creando / abriendo archivo.");
            System.exit(1); // termina el programa
        }
    }

    public void agregarInformacion() {

        System.out.println("Ingrese informacion. Presione ctrl + z al finalizar");

        Informacion info = new Informacion();

        while (input.hasNext()) {
            System.out.printf( "%s\n%s",
				"Digite tipo de informacion, cultivo, informacion.",
				"? " );
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

    public void cerrarArchivo() {

        if (output != null) {
            output.close();
        }
    }
}
