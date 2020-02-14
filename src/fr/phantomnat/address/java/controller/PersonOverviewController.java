/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.phantomnat.address.java.controller;

import fr.phantomnat.address.java.Launcher;
import fr.phantomnat.address.java.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Dorian Pilorge
 */
public class PersonOverviewController {

    // Reference to the main application
    private Launcher app;

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    private Button btnNew, btnEdit, btnDelete;

    /**
     * Default constructor
     * The constructor is called before the initialize() method
     */
    public PersonOverviewController() {}

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button btn = (Button) event.getSource();
        System.out.println("Button '" + btn.getText() + "' has been clicked!");
    }

    /**
     * Initializes the Controller class
     * This method is automatically called after the FXML file has been loaded
     */
    @FXML
    public void initialize() {
        // Initialize the Person Table with the two columns
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the Application (Launcher class) to give a reference back to itself
     * 
     * @param app
     */
    public void setApplication(Launcher app) {
        this.app = app;

        // Add observable list data to the Person Table
        personTable.setItems(app.getPersonData());
    }

}
