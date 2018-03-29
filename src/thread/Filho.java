/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import gui.controller.TelaController;

public class Filho implements Runnable {

	TelaController tela;
	int id;
	int numeroPedidos;
	boolean sinc;

	public Filho(TelaController tela, int id, int numeroPedidos, boolean sinc) {
		this.tela = tela;
		this.id = id;
		this.numeroPedidos = numeroPedidos;
		this.sinc = sinc;
	}

	public void realizarPedidos() {
		// Se a flag de sincronizado for true
		if (sinc) {
			for (int i = 0; i < numeroPedidos; i++) {
				System.out.println("ID:" + id + "\t Pedido Numero:" + i);

				tela.incrementarSync();
			}
			// Se a flag de sincronizado for false
		} else {
			for (int i = 0; i < numeroPedidos; i++) {
				System.out.println("ID:" + id + "\t Pedido Numero:" + i);

				tela.incrementar();
			}
		}

		tela.finalizar(id);
		System.out.println(sinc? "Teste utilizando método sincronizado": "Teste utilizando o método não sincronizado");
	}

	@Override
	public void run() {
		realizarPedidos();
	}

}
