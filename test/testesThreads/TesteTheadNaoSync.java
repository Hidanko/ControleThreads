/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testesThreads;

import gui.controller.TelaController;
import thread.NaoSincronizado;

/**
 *
 * @author sattra
 */
public class TesteTheadNaoSync {
    public static void main(String[] args) {
        NaoSincronizado ns = new NaoSincronizado(new TelaController(), 1, 5);
        Thread thr = new Thread(ns);
        thr.run();
        System.out.println("RODOU");
    }
}
