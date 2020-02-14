/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.phantomnat.address.java.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author Dorian Pilorge
 */
public class PersonOverviewController implements Initializable {

    @FXML
    private Button btnNew, btnEdit, btnDelete;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        System.out.println("Button '" + btn.getText() + "' has been clicked!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
