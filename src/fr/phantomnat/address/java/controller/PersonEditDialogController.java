/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.phantomnat.address.java.controller;

import fr.phantomnat.address.java.model.Person;
import fr.phantomnat.address.java.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Dorian Pilorge
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean OKClicked = false;

    /**
     * Initializes the Controller class
     * This method is automatically called after the FXML file has been loaded
     */
    @FXML
    private void initialize() {}

    /**
     * Sets the Stage of this dialog
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the Person to be edited in the dialog
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise
     *
     * @return
     */
    public boolean isOKClicked() {
        return OKClicked;
    }

    /**
     * Called when the user clicks on the OK Button
     */
    @FXML
    private void handleOK() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            OKClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks Cancel
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the Text Fields
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "• First Name parameter is missing!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "• Last Name parameter is missing!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "• Street parameter is missing!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "• Postal Code parameter is missing!\n";
        }
        else {
            // Try to parse the Postal Code into an int
            try {
                Integer.parseInt(postalCodeField.getText());
            }
            catch (NumberFormatException e) {
                errorMessage += "• Invalid Postal Code parameter (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "• City parameter is missing!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "• Birthday parameter is missing!!\n";
        }
        else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "• Invalid Birthday parameter (format must be 'dd.mm.yyyy')!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // Show the Error Message
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid fields");
            alert.setContentText(errorMessage);
            alert.setResizable(false);

            alert.showAndWait();

            return false;
        }
    }

}
