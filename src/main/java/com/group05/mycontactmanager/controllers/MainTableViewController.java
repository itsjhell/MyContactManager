package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * @file MainTableViewController.java
 * @brief Controller principale della tabella dei contatti.
 * 
 * Questa classe gestisce l'interfaccia principale, mostrando la lista dei contatti in una tabella.
 * Fornisce azioni per aggiungere un contatto, caricare/salvare da file, importare/esportare,
 * e funzioni di ricerca.
 * 
 * @author group05
 * @date Dicembre 08,2024
 * @version 1.0
 */
public class MainTableViewController implements Initializable {

    @FXML
    private MenuItem saveContactListButton;
    @FXML
    private MenuItem loadContactListButton;
    @FXML
    private MenuItem importContactsButton;
    @FXML
    private MenuItem exportContactsButton;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Label contactListName;
    @FXML
    private Button addButton;
    @FXML
    private Button selectButton;
    @FXML
    private ComboBox<String> searchParameter;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<Contact, Image> iconClm;
    @FXML
    private TableColumn<Contact, String> surnameClm;
    @FXML
    private TableColumn<Contact, String> nameClm;
    @FXML
    private TableColumn<Contact, CheckBox> checkClm;
    
    private ObjectProperty<Contact> contact;
    private ObservableList<Contact> contactList;
    
    /**
     * @brief Imposta l'ObjectProperty del contatto selezionato.
     * @param contact L'ObjectProperty contenente il contatto.
     */
    public void setContact(ObjectProperty<Contact> contact) {
        this.contact = contact;
    }
    
    /**
     * @brief Restituisce l'ObjectProperty del contatto.
     * @return L'ObjectProperty contenente il contatto.
     */
    public ObjectProperty<Contact> getContact() {
        return contact;
    }

    /**
     * @brief Imposta la lista di contatti.
     * @param contactList L'ObservableList di contatti.
     */
    public void setContactList(ObservableList<Contact> contactList) {
        this.contactList = contactList;
    }
    
    /**
     * @brief Restituisce la lista di contatti.
     * @return L'ObservableList di contatti.
     */
    public ObservableList<Contact> getContactList() {
        return contactList;
    }

    /**
     * @brief Inizializza il controller.
     * @param url L'URL per percorsi relativi.
     * @param rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Logica di inizializzazione.
        ContactController.setSplitPane(splitPane);
    }    

    /**
     * @brief Salva la lista dei contatti su file.
     * @param event L'ActionEvent associato.
     */
    @FXML
    private void saveContactList(ActionEvent event) {
    }

    /**
     * @brief Carica la lista dei contatti da file.
     * @param event L'ActionEvent associato.
     */
    @FXML
    private void loadContactList(ActionEvent event) {
    }

    /**
     * @brief Importa contatti da un file CSV.
     * @param event L'ActionEvent associato.
     */
    @FXML
    private void importContacts(ActionEvent event) {
    }

    /**
     * @brief Esporta contatti in un file CSV.
     * @param event L'ActionEvent associato.
     */
    @FXML
    private void exportContacts(ActionEvent event) {
    }

    /**
     * @brief Aggiunge un nuovo contatto alla lista, caricando la vista di aggiunta.
     * @param event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento della vista FXML.
     */
    @FXML
    private void addContactToList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddContactView" + ".fxml"));
        splitPane.getItems().remove(1);
        splitPane.getItems().add(fxmlLoader.load());
    }

    /**
     * @brief Seleziona i contatti.
     * @param event L'ActionEvent associato.
     */
    @FXML
    private void selectContacts(ActionEvent event) {
    }

    /**
     * @brief Sceglie il parametro di ricerca.
     * @param event L'ActionEvent associato.
     */
    @FXML
    private void chooseSearchParameter(ActionEvent event) {
    }
    
    /**
     * @brief Ricerca contatti per cognome e nome.
     * @param surname La proprietà Stringa del cognome.
     * @param name La proprietà Stringa del nome.
     * @return Un'ObservableList di contatti corrispondenti che soddisfano i criteri di ricerca 
     */
    public ObservableList searchBySurnameAndName(StringProperty surname, StringProperty name) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per cognome .
     * @param surname La proprietà Stringa del cognome.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchBySurname(StringProperty surname) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per nome .
     * @param name La proprietà Stringa del nome.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca. 
     */
    public ObservableList searchByName(StringProperty name) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per email (non implementato).
     * @param email La proprietà Stringa dell'email.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchByEmail(StringProperty email) {
        return null;
    }
  
    /**
     * @brief Ricerca contatti per numero di telefono (non implementato).
     * @param phone La proprietà Stringa del numero di telefono.
     * @return UUn'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchByPhoneNumber(StringProperty phone) {
        return null;
    }
}
