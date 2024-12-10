package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * @file ContactController.java
 * @brief Classe controller astratta che fornisce elementi comuni per la gestione delle viste legate ai contatti.
 * 
 * Questa classe definisce componenti UI e metodi comuni a diversi controller,
 * come AddContactController, DetailsContactController ed EditContactController.
 * Gestisce campi di input per un contatto e accede al riferimento di uno SplitPane per caricare la successiva interfaccia.
 * 
 * @author group05
 * @date Dicembre 08,2024
 * @version 1.0
 */
abstract class ContactController {
    
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField surnameField;
    @FXML
    protected Label errorName;
    @FXML
    protected ImageView contactImage;
    @FXML
     Button imageButton;
    @FXML
    protected ComboBox<PhonePrefix> prefixMenu1;
    @FXML
    protected ComboBox<PhonePrefix> prefixMenu2;
    @FXML
    protected ComboBox<PhonePrefix> prefixMenu3;
    @FXML
    protected Button adderPhoneButton;
    @FXML
    protected TextField phoneNumber1;
    @FXML
    protected TextField phoneNumber2;
    @FXML
    protected TextField phoneNumber3;
    @FXML
    protected Label errorNumber;
    @FXML
    protected Button adderEmailButton;
    @FXML
    protected TextField emailAddress1;
    @FXML
    protected TextField emailAddress3;
    @FXML
    protected TextField emailAddress2;
    @FXML
    protected Label errorEmail;
    @FXML
    protected TextArea notesArea;
    
    protected SplitPane splitPane;
    
    protected ObjectProperty<Contact> contactProperty;
            
    protected ObservableList<Contact> contactList;
    
    protected List<PhoneNumber> numbers;
    
    protected List<String> emailAddresses;
       
    public ContactController(SplitPane splitPane, ObservableList<Contact> contactList) {
        contactProperty = new SimpleObjectProperty();
        contactList = FXCollections.observableArrayList();
        this.contactList = contactList;
        this.splitPane = splitPane;
    }
    
    public ContactController(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) {
        contactProperty = new SimpleObjectProperty();
        contactList = FXCollections.observableArrayList();
        contactProperty.set(contact);
        this.contactList = contactList;
        this.splitPane = splitPane;
    }
    /**
     * @brief Carica un'immagine per il contatto.
     * @param[in] event L'ActionEvent associato all'azione.
     */
    @FXML
    private void loadImage(ActionEvent event) {
        
    }

    /**
     * @brief Sceglie il prefisso telefonico.
     * @param[in] event L'ActionEvent associato alla scelta del prefisso.
     */
    @FXML
    private void choosePrefix(ActionEvent event) {
    }

    /**
     * @brief Aggiunge un numero di telefono.
     * @param[in] event L'ActionEvent associato all'azione.
     */
    @FXML
    private void addPhoneNumber(ActionEvent event) {
        TextField[] phoneFields = { phoneNumber1, phoneNumber2, phoneNumber3 };
        for (int i = 0; i < contactList.size(); i++) {
            phoneFields[i].setEditable(true);
            phoneFields[i].setOpacity(0.75);
        }
        phoneFields[contactList.size()+1].setEditable(true);
    }

    /**
     * @brief Aggiunge un indirizzo email.
     * @param[in] event L'ActionEvent associato all'azione.
     */
    @FXML
    private void addEmail(ActionEvent event) {
    }
    
    public void loadDetailsContact(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DetailsContactView.fxml"));
        if (splitPane.getItems().size() > 1)
            splitPane.getItems().remove(1);
        fxmlLoader.setControllerFactory(param -> new DetailsContactController(splitPane, contact, contactList)); // Usa una fabbrica per creare il controller
        splitPane.getItems().add(fxmlLoader.load());
    }
    
    public void loadEditContact(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditContactView.fxml"));
        if (splitPane.getItems().size() > 1)
            splitPane.getItems().remove(1);
        fxmlLoader.setControllerFactory(param -> new EditContactController(splitPane, contact, contactList)); // Usa una fabbrica per creare il controller
        splitPane.getItems().add(fxmlLoader.load());
    }

    /**
     * @brief Metodo astratto che va implementato nella sottoclasse in base alla funzionalità richiesta.
     * @param[in] event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento vista.
     */
    @FXML
    abstract void executeLeftTask(ActionEvent event) throws IOException;

    /**
     * @brief Metodo astratto che va implementato nella sottoclasse in base alla funzionalità richiesta.
     * @param[in] event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento vista.
     */
    @FXML
    abstract void executeRightTask(ActionEvent event) throws IOException;
}
