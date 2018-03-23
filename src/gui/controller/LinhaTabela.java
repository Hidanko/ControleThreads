/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

/**
 *
 * @author sattra
 */
public class LinhaTabela {
    int ID;
    String valor;

    public LinhaTabela(int ID, String valor) {
        this.ID = ID;
        this.valor = valor;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
