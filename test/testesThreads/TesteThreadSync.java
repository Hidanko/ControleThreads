/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testesThreads;

import gui.controller.TelaController;
import thread.Sincronizado;

/**
 *
 * @author sattra
 */
public class TesteThreadSync {

    public static void main(String[] args) {
        Sincronizado sync = new Sincronizado(new TelaController(), 5, 5);
        Thread thr = new Thread(sync);
        thr.run();
        System.out.println("RODOU");
    }
}
