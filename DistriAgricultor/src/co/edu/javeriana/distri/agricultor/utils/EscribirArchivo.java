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

        System.out.println("Escribiendo archivo.");

        Informacion info = new Informacion();

        try {
            info.setTipoInfo("Prediccion Clima");
            info.setCultivo("Maiz");
            info.setInformacion("info1");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

            info.setTipoInfo("Precios Insumos");
            info.setCultivo("Frijol");
            info.setInformacion("info2");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

            info.setTipoInfo("Precios Productos");
            info.setCultivo("Papa");
            info.setInformacion("info3");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

            info.setTipoInfo("Prediccion Clima");
            info.setCultivo("Maiz");
            info.setInformacion("info4");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

            info.setTipoInfo("Precios Insumos");
            info.setCultivo("Zanahoria");
            info.setInformacion("info5");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

            info.setTipoInfo("Precios Productos");
            info.setCultivo("Trigo");
            info.setInformacion("info6");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

            info.setTipoInfo("Prediccion Clima");
            info.setCultivo("Soya");
            info.setInformacion("info7");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

            info.setTipoInfo("Precios Insumos");
            info.setCultivo("Mango");
            info.setInformacion("info8");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

            info.setTipoInfo("Precios Productos");
            info.setCultivo("Arroz");
            info.setInformacion("info9");

            output.format("%s %s %s %s\n", info.getFechaInfo(), info.getTipoInfo(),
                    info.getCultivo(), info.getInformacion());

        } catch (FormatterClosedException fce) {
            System.err.println("Error escribiendo archivo.");
            return;
        } catch (NoSuchElementException nse) {
            System.err.println("Input invalido. Intente de nuevo.");
            input.nextLine();
        }

    }

    public void cerrarArchivo() {

        if (output != null) {
            output.close();
        }
    }
}
