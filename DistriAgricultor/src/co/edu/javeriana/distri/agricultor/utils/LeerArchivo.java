/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.utils;

import co.edu.javeriana.distri.agricultor.modelo.Informacion;
import co.edu.javeriana.distri.agricultor.project.Datos;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos E Quimbay
 *
 * para ejecutar hilo desde Main (new Thread(new LeerArchivo())).start();
 */
public class LeerArchivo extends Thread {

    private Scanner input;
    private Sincronizar sync;
    public Datos dat;

    public LeerArchivo(Datos dat) {
        this.dat = dat;
    }

    public void abrirArchivo() {

        try {
            input = new Scanner(new File("informacion.txt"));
        } catch (FileNotFoundException fnfe) {
            System.err.println("Error abriendo archivo.");
            System.exit(1);
        }
    }

    public void leerInformacion() throws InterruptedException {

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

                //Thread.sleep(50000);
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

    public void run() {

        Random random = new Random();

        sync = new Sincronizar();
        List<Informacion> in = new ArrayList<>();
        this.abrirArchivo();

        try {
            while (input.hasNext()) {
                System.out.println("imp primero" + imp(this.dat.top_info));
                Informacion info = new Informacion();
                Thread.sleep(1000);
                info.setFechaInfo(input.next());
                info.setTipoInfo(input.next());
                System.out.println("tipoinfo: " + info.getTipoInfo());
                info.setCultivo(input.next());
                info.setInformacion(input.next());
                //sync.read();
                System.out.println(info.getFechaInfo() + " " + info.getTipoInfo() + "\t" + info.getCultivo() + "    \t"
                        + info.getInformacion());
                //in.add(info);
                System.out.println(info.getTipoInfo().contains("Productos") + "" + info.getTipoInfo().contains("Clima") + info.getTipoInfo().contains("Precios"));
                if (info.getTipoInfo().toString().contains("Productos")) {
                    System.out.println("1");
                    List<Informacion> temp = this.dat.top_info.get("Productos");
                    System.out.println("2");
                    temp.add(info);
                    System.out.println("3");
                    this.dat.top_info.put("Productos", temp);
                } else if (info.getTipoInfo().toString().contains("Clima")) {
                    System.out.println("1");
                    List<Informacion> temp = this.dat.top_info.get("Clima");
                    System.out.println("2");
                    temp.add(info);
                    System.out.println("3");
                    this.dat.top_info.put("Clima", temp);
                }
                System.out.println("imp" + (this.dat.top_info));

                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    System.err.println("Error de hilo de lectura");
                }
            }
        } catch (NoSuchElementException nse) {
            System.err.println("Archivo erroneo");
            input.close();
            System.exit(1);
        } catch (IllegalStateException ise) {
            System.err.println("Error leyendo archivo.");
            System.exit(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(LeerArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cerrarArchivo();
    }

    public void cerrarArchivo() {

        if (input != null) {
            input.close();
        }
    }

    public String imp(Map<String, List<Informacion>> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, List<Informacion>>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, List<Informacion>> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            List<Informacion> temp = entry.getValue();
            Iterator<Informacion> it = temp.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getTipoInfo());
            }

            sb.append('"');
            if (iter.hasNext()) {
                sb.append(',').append(' ');
            }
        }
        return sb.toString();

    }

}
