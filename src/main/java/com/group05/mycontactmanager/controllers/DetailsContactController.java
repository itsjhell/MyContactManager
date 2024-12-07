/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author group05
 */
public class DetailsContactController extends ContactController implements Initializable {

    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    @Override
     void executeLeftTask(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditContactView" + ".fxml"));
        splitPane.getItems().remove(1);
        splitPane.getItems().add(fxmlLoader.load());
    /*  EditContactController editController = fxmlLoader.getController(); // ottengo l'oggetto controller
        editController.setContact(new Contact("a", "b", null, null, "", "")); // test comunicazione
    */
    }

    @FXML
    @Override
     void executeRightTask(ActionEvent event) {
    }
}
