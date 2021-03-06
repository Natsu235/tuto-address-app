/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.phantomnat.address.java;

import fr.phantomnat.address.java.controller.PersonEditDialogController;
import fr.phantomnat.address.java.controller.PersonOverviewController;
import fr.phantomnat.address.java.model.Person;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Dorian Pilorge
 */
public class Launcher extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Default constructor
     */
    public Launcher() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.getIcons().add(new Image(Launcher.class.getResource("/fr/phantomnat/address/resources/img/address-book-32.png").toString()));

        initRootLayout();
        showPersonOverview();
    }

    /**
     * Initializes the Root Layout
     */
    public void initRootLayout() {
        try {
            // Load Root Layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the Scene containing the Root Layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Shows the Person Overview inside the Root Layout
     */
    public void showPersonOverview() {
        try {
            // Load Person Overview from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Launcher.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Give the Controller access to the Application (Launcher class).
            PersonOverviewController controller = loader.getController();
            controller.setApplication(this);

            // Set Person Overview into the center of Root Layout
            rootLayout.setCenter(personOverview);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
    * Opens a dialog to edit details for the specified Person.
    * If the user clicks OK, the changes are saved into the provided
    * Person object and true is returned
    *
    * @param person the Person object to be edited
    * @return true if the user clicked OK, false otherwise
    */
   public boolean showPersonEditDialog(Person person) {
       try {
           // Load the FXML file and create a new Stage for the popup dialog
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(Launcher.class.getResource("view/PersonEditDialog.fxml"));
           AnchorPane page = (AnchorPane) loader.load();

           // Create the dialog Stage
           Stage dialogStage = new Stage();
           dialogStage.setTitle("Edit Person");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(primaryStage);
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);

           // Set the Person into the Controller
           PersonEditDialogController controller = loader.getController();
           controller.setDialogStage(dialogStage);
           controller.setPerson(person);

           // Show the dialog and wait until the user closes it
           dialogStage.showAndWait();

           return controller.isOKClicked();
       }
       catch (IOException ex) {
           ex.printStackTrace();
           return false;
       }
   }

    /**
     * Returns the Main Stage
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns the data as an observable list of Persons
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
