/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhonePrefix;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
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
    
    private ObjectProperty<Contact> contact;
    private ObservableList<Contact> contactList;
    
    /**
     * @brief SplitPane condiviso tra vari controller per gestire la visualizzazione delle viste.
     */
    protected static SplitPane splitPane;
    
    /**
     * @brief Imposta il riferimento allo SplitPane condiviso.
     * @param sp Il nuovo SplitPane da utilizzare.
     */
    public static void setSplitPane(SplitPane sp){
        splitPane = sp;
    }
    
    /**
     * @brief Imposta l'ObjectProperty Contact, per legare un contatto al controller.
     * @param contact L'ObjectProperty che contiene il contatto da gestire.
     */
    public void setContact(ObjectProperty<Contact> contact) {
        this.contact = contact;
    }
    
    /**
     * @brief Restituisce l'ObjectProperty legata al contatto.
     * @return L'ObjectProperty contenente il contatto.
     */
    public ObjectProperty<Contact> getContact() {
        return contact;
    }

    /**
     * @brief Imposta la lista dei contatti gestita dal controller.
     * @param contactList L'ObservableList di contatti.
     */
    public void setContactList(ObservableList<Contact> contactList) {
        this.contactList = contactList;
    }
    
    /**
     * @brief Restituisce la lista dei contatti gestita dal controller.
     * @return L'ObservableList di contatti.
     */
    public ObservableList<Contact> getContactList() {
        return contactList;
    }
    
    /**
     * @brief Carica un'immagine per il contatto.
     * @param event L'ActionEvent associato all'azione.
     */
    @FXML
    private void loadImage(ActionEvent event) {
    }

    /**
     * @brief Sceglie il prefisso telefonico.
     * @param event L'ActionEvent associato alla scelta del prefisso.
     */
    @FXML
    private void choosePrefix(ActionEvent event) {
    }

    /**
     * @brief Aggiunge un numero di telefono.
     * @param event L'ActionEvent associato all'azione.
     */
    @FXML
    private void addPhoneNumber(ActionEvent event) {
    }

    /**
     * @brief Aggiunge un indirizzo email.
     * @param event L'ActionEvent associato all'azione.
     */
    @FXML
    private void addEmail(ActionEvent event) {
    }
    
    /**
     * @brief Metodo astratto che va implementato nella sottoclasse in base alla funzionalità richiesta.
     * @param event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento vista.
     */
    @FXML
    abstract void executeLeftTask(ActionEvent event) throws IOException;

    /**
     * @brief Metodo astratto che va implementato nella sottoclasse in base alla funzionalità richiesta.
     * @param event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento vista.
     */
    @FXML
    abstract void executeRightTask(ActionEvent event) throws IOException;
}
