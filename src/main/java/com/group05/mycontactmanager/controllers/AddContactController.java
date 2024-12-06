/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.PhonePrefix;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author group05
 */
public class AddContactController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private Label errorName;
    @FXML
    private ImageView contactImage;
    @FXML
    private Button imageButton;
    @FXML
    private ComboBox<PhonePrefix> prefixMenu1;
    @FXML
    private TextField phoneNumber1;
    @FXML
    private Label errorNumber;
    @FXML
    private Button adderPhoneButton;
    @FXML
    private TextField phoneNumber2;
    @FXML
    private ComboBox<PhonePrefix> prefixMenu2;
    @FXML
    private TextField phoneNumber3;
    @FXML
    private ComboBox<PhonePrefix> prefixMenu3;
    @FXML
    private TextField emailAddress1;
    @FXML
    private TextField emailAddress3;
    @FXML
    private TextField emailAddress2;
    @FXML
    private Button adderEmailButton;
    @FXML
    private TextArea notesArea;
    @FXML
    private Button addButton;
    @FXML
    private Button CancelButton;
    
    private static SplitPane splitPane;
    @FXML
    private Label errorEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadImage(ActionEvent event) {
    }

    @FXML
    private void choosePrefix(ActionEvent event) {
    }

    @FXML
    private void addPhoneNumber(ActionEvent event) {
    }

    @FXML
    private void addEmail(ActionEvent event) {
    }

    @FXML
    private void addContact(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DetailsContactView" + ".fxml"));
        splitPane.getItems().remove(1);
        splitPane.getItems().add(fxmlLoader.load());
    }

    @FXML
    private void cancelAddContact(ActionEvent event) {
    }
    
    public static void setSplitPane(SplitPane sp){
        splitPane = sp;
    }
}
