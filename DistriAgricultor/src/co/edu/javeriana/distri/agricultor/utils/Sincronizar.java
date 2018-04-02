/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.distri.agricultor.utils;

/**
 *
 * @author Carlos E Quimbay
 */
public class Sincronizar {

    private boolean empty = true;

    public Sincronizar() {
    }

    public synchronized void read() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {

            }

        }
        empty = true;
        notifyAll();
    }

    public synchronized void write() {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {

            }

        }
        empty = false;
        notifyAll();
    }

}
