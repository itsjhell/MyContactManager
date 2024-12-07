/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhonePrefix;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller Superclass
 *
 * @author group05
 */
abstract class ContactController {
    

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
    private ComboBox<PhonePrefix> prefixMenu2;
    @FXML
    private ComboBox<PhonePrefix> prefixMenu3;
    @FXML
    private Button adderPhoneButton;
    @FXML
    private TextField phoneNumber1;
    @FXML
    private TextField phoneNumber2;
    @FXML
    private TextField phoneNumber3;
    @FXML
    private Label errorNumber;
    @FXML
    private Button adderEmailButton;
    @FXML
    private TextField emailAddress1;
    @FXML
    private TextField emailAddress3;
    @FXML
    private TextField emailAddress2;
    @FXML
    private Label errorEmail;
    @FXML
    private TextArea notesArea;
    
    private Contact contact;
    
    private ObservableList<Contact> contactList;
    
    protected static SplitPane splitPane;
    
    public static void setSplitPane(SplitPane sp){
        splitPane = sp;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setContactList(ObservableList<Contact> contactList) {
        this.contactList = contactList;
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
    abstract void executeLeftTask(ActionEvent event) throws IOException;

    @FXML
    abstract void executeRightTask(ActionEvent event) throws IOException;
}
