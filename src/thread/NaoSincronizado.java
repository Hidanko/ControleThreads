/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import gui.controller.TelaController;

public class NaoSincronizado implements Runnable {

    TelaController tela;
    int id;
    int numeroPedidos;

    public NaoSincronizado(TelaController tela, int id, int numeroPedidos) {
        this.tela = tela;
        this.id = id;
        this.numeroPedidos = numeroPedidos;
    }

    public void realizarPedidos() {
        for (int i = 0; i < numeroPedidos; i++) {
            tela.adicionarValor(id);
        }
    }

    @Override
    public void run() {

    }

}
