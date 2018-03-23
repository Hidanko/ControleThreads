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
    @FXML
    private TableColumn<LinhaTabela, String> coluna1;
    @FXML
    private TableColumn<LinhaTabela, String> coluna2;
    @FXML
    private TableColumn<LinhaTabela, String> coluna3;
    @FXML
    private TableColumn<LinhaTabela, String> coluna4;
    
    
    ObservableList<LinhaTabela> lista;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       coluna1.setCellValueFactory(new PropertyValueFactory<>("ID"));
       coluna2.setCellValueFactory(new PropertyValueFactory<>("valor"));
       
       coluna3.setCellValueFactory(new PropertyValueFactory<>("ID"));
       coluna4.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    @FXML
    public void iniciarPedidos(ActionEvent event) {
        String text = txtThreads.getText();
        // Erro na proxima linha
        int numThreads = Integer.parseInt(text);
        int numReq = Integer.getInteger(txtRequest.getText());

        if (numThreads > 0 && numThreads < 1000 && numReq < 1000000) {

            System.out.println("Validado");
            int eachReq =(int) numReq / numThreads;

            lista = FXCollections.observableArrayList();
            for (int i = 0; i < numThreads; i++) {
                NaoSincronizado ns = new NaoSincronizado(this, i, eachReq);
                ns.run();
                lista.add(new LinhaTabela(i, "INICIADO"));
            }
            tableErro.setItems(lista);

        }

    }

//    public void finalizar(int ID) {
//        LinhaTabela atualzado = new LinhaTabela(ID, "FINALIZADO");
//        lista.remove(ID);
//        lista.add(atualzado);
//    }
}
