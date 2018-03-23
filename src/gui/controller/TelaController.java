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
import thread.NaoSincronizado;

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

    ObservableList<LinhaTabela> lista;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void iniciarPedidos(ActionEvent event) {
        int numThreads = Integer.getInteger(txtThreads.getText());
        int numReq = Integer.getInteger(txtRequest.getText());

        if (numThreads > 0 && numThreads < 1000 && numReq < 1000000) {

            System.out.println("Validado");
            int eachReq = numReq / numThreads;

            lista = FXCollections.observableArrayList();
            for (int i = 0; i < numThreads; i++) {
                NaoSincronizado ns = new NaoSincronizado(this, i, eachReq);
                ns.run();
                lista.add(new LinhaTabela(i, "INICIADO"));
            }
            tableErro.setItems(lista);

        }

    }

    public void finalizar(int ID) {
        LinhaTabela atualzado = new LinhaTabela(ID, "FINALIZADO");
        lista.remove(ID);
        lista.add(atualzado);
    }
}
