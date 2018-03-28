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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import thread.NaoSincronizado;
import thread.Sincronizado;

public class TelaController implements Initializable {

    @FXML
    private TableView<LinhaTabela> tableErro;
    @FXML
    private Tab tableOtimizado;
    @FXML
    private TextField txtThreads;
    @FXML
    private TextField txtRequest;
    @FXML
    private Button buttonRodar;
    @FXML
    private TabPane panePrincipal;
    @FXML
    private TableColumn<LinhaTabela, String> coluna1;
    @FXML
    private TableColumn<LinhaTabela, String> coluna2;
    @FXML
    private TableColumn<LinhaTabela, String> coluna3;
    @FXML
    private TableColumn<LinhaTabela, String> coluna4;

    ObservableList<LinhaTabela> lista;
    int contador;
    int threadsFinalizados;

    @FXML
    private TableView<LinhaTabela> tableTeste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tabela da aba não sincronizada
        coluna1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        coluna2.setCellValueFactory(new PropertyValueFactory<>("valor"));
        // Tabela da aba sincronizada
        coluna3.setCellValueFactory(new PropertyValueFactory<>("ID"));
        coluna4.setCellValueFactory(new PropertyValueFactory<>("valor"));
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

            // Corrigir esse trecho
            //if (ALGUMA COISA)
            NaoSincronizado ns = new NaoSincronizado(this, i, ((int) requests / threads));
//            else 
//            Sincronizado ns1 = new Sincronizado(this, i,((int) requests/threads));
            Thread th = new Thread(ns);
            th.start();
        }
        tableErro.setItems(lista);
    }

    public void finalizar(int ID) {
        LinhaTabela atualzado = new LinhaTabela(ID, "FINALIZADO");
        lista.remove(ID);
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
