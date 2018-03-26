/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import gui.controller.TelaController;

/**
 *
 * @author 6248489
 */
public class Sincronizado implements Runnable {
    
    TelaController tela;
    int id;
    int numeroPedidos;

    public Sincronizado(TelaController tela, int id, int numeroPedidos) {
        this.tela = tela;
        this.id = id;
        this.numeroPedidos = numeroPedidos;
    }

     public synchronized void realizarPedidos() {
        for (int i = 0; i < numeroPedidos; i++) {
            System.out.println("ID:"+id+"\t Pedido Numero:"+numeroPedidos);
        }
        // finalizar
    }

    @Override
    public void run() {
        realizarPedidos();
    }
}
