/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.utils;

import co.edu.javeriana.distri.agricultor.modelo.Informacion;
import java.io.*;
import java.util.*;

/**
 *
 * @author Carlos E Quimbay
 */
public class LeerArchivo {

    private Scanner input;

    public void abrirArchivo() {

        try {
            input = new Scanner(new File("informacion.txt"));
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error abriendo archivo.");
            System.exit(1);
        }
    }

    public void leerInformacion() {

        System.out.println("Leyendo informacion de archivo.");

        Informacion info = new Informacion();

        try {
            while (input.hasNext()) {
                info.setFechaInfo(input.next());
                info.setTipoInfo(input.next());
                info.setCultivo(input.next());
                info.setInformacion(input.next());

                System.out.printf("%s %s %s %s \n", info.getFechaInfo(),
                        info.getTipoInfo(), info.getCultivo(), info.getInformacion());
            }
        } catch (NoSuchElementException nse) {
            System.err.println("Archivo erroneo");
            input.close();
            System.exit(1);
        } catch (IllegalStateException ise) {
            System.err.println("Error leyendo archivo.");
            System.exit(1);
        }
    }

    public void cerrarArchivo() {

        if (input != null) {
            input.close();
        }
    }

}
