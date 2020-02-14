/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.phantomnat.address.java.controller;

import fr.phantomnat.address.java.Launcher;
import fr.phantomnat.address.java.model.Person;
import fr.phantomnat.address.java.util.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Dorian Pilorge
 */
public class PersonOverviewController {

    // Reference to the Main Application
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

    /**
     * Default Constructor
     * The constructor is called before the initialize() method
     */
    public PersonOverviewController() {}

    /**
     * Initializes the Controller class
     * This method is automatically called after the FXML file has been loaded
     */
    @FXML
    public void initialize() {
        // Initialize the Person Table with the two columns
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear Person Details
        showPersonDetails(null);

        // Listen for selection changes and show the Person Details when changed
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
    * Called when the user clicks on the New Button
    * Opens a dialog to edit details for a new Person
    */
   @FXML
   private void handleNewPerson() {
       Person tempPerson = new Person();
       boolean okClicked = app.showPersonEditDialog(tempPerson);
       if (okClicked) {
           app.getPersonData().add(tempPerson);
       }
   }

   /**
    * Called when the user clicks on the Edit Button
    * Opens a dialog to edit details for the selected Person
    */
   @FXML
   private void handleEditPerson() {
       Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
       if (selectedPerson != null) {
           boolean okClicked = app.showPersonEditDialog(selectedPerson);
           if (okClicked) {
               showPersonDetails(selectedPerson);
           }
       }
       else {
            // Show the Warning Message
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(app.getPrimaryStage());
            alert.setTitle("Warning");
            alert.setHeaderText("No person selected");
            alert.setContentText("Please select a person in the table.");
            alert.setResizable(false);

            alert.showAndWait();
       }
   }

    /**
    * Called when the user clicks on the Delete Button
    */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        }
        else {
            // Show the Warning Message
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(app.getPrimaryStage());
            alert.setTitle("Warning");
            alert.setHeaderText("No person selected");
            alert.setContentText("Please select a person in the table.");
            alert.setResizable(false);

            alert.showAndWait();
        }
    }

    /**
     * Fills all Text Fields to show details about the Person
     * If the specified Person is null, all Text Fields are cleared
     *
     * @param person the Person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the Person object
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        }
        else {
            // Person is null, remove all the text
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Is called by the Application (Launcher class) to give a reference back to itself
     * 
     * @param app
     */
    public void setApplication(Launcher app) {
        this.app = app;

        // Add Observable List data to the Person Table
        personTable.setItems(app.getPersonData());
    }

}
