/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import thread.Filho;

public class TelaController implements Initializable {

	ObservableList<LinhaTabela> lista;
	int contador;
	int threadsFinalizados;

	@FXML
	private TextField txtThreads;
	@FXML
	private TextField txtRequest;
	@FXML
	private Button buttonRodar;
	@FXML
	private TableView<LinhaTabela> tableErro;
	@FXML
	private TableColumn<LinhaTabela, String> coluna1;
	@FXML
	private TableColumn<LinhaTabela, String> coluna2;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Tabela da aba não sincronizada
		coluna1.setCellValueFactory(new PropertyValueFactory<>("ID"));
		coluna2.setCellValueFactory(new PropertyValueFactory<>("valor"));
	}

	@FXML
	public void iniciarPedidos(ActionEvent event) {
		contador = 0;
		lista = FXCollections.observableArrayList();

		int threads = Integer.valueOf(txtThreads.getText());
		threadsFinalizados = threads;
		int requests = Integer.valueOf(txtRequest.getText());
		for (int i = 0; i < threads; i++) {
			lista.add(new LinhaTabela(i, "INICIADO"));

			// Implementar seletor de modo
			boolean selecao = false;
			Filho filho = new Filho(this, i, ((int) requests / threads), selecao);

			Thread th = new Thread(filho);
			th.start();
		}
		tableErro.setItems(lista);
	}

	public synchronized void finalizar(int ID) {
		
		LinhaTabela atualzado = new LinhaTabela(ID, "FINALIZADO");
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getID() == ID) {
				lista.remove(i);
			}
		}
		
		lista.add(atualzado);
		System.out.println(ID + " finalizado");
		System.out.println("Contador atual: " + contador);

		threadsFinalizados--;
		if (threadsFinalizados == 0) {
			JOptionPane.showMessageDialog(null, "Valor final: " + contador);
		}
	}

	public synchronized void incrementarSync() {
		contador++;
	}

	public void incrementar() {
		contador++;
	}
}
