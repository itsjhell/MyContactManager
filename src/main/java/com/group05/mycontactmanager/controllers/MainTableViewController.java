package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.ContactManager;
import com.group05.mycontactmanager.models.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private Contact contact;
    private ObservableList<Contact> contactList;

    /**
     * @brief Imposta la lista di contatti.
     * @param[in] contactList L'ObservableList di contatti.
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
     * @param[in] url L'URL per percorsi relativi.
     * @param[in] rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ContactController.setSplitPane(splitPane);
        
        surnameClm.setCellValueFactory(new PropertyValueFactory("surname"));
        nameClm.setCellValueFactory(new PropertyValueFactory("name"));
           
        ContactManager contactManager = new ContactManager("Nuova rubrica");
        contactList = FXCollections.observableArrayList(contactManager.getContactList());
        // Lista ordinata per cognome-nome da visualizzare nella tabella */
        SortedList<Contact> sortedContactList = new SortedList(contactList, new Comparator<Contact>() { 
            @Override
            public int compare(Contact o1, Contact o2) {
                // Confronto cognome
                int cmp = o1.getSurname().compareToIgnoreCase(o2.getSurname());
                if (cmp != 0)
                    return cmp;
                // Confronto nome se i cognomi sono uguali
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        contactTable.setItems(sortedContactList);

        contactList.add(new Contact("Mario", "Rossi", null, null, "", ""));
        contactList.add(new Contact("Riccardo", "Rossi", null, null, "", ""));
        contactList.add(new Contact("Mario", "Verdi", null, null, "", ""));
        contactList.add(new Contact("Mario", "Gialli", null, null, "", ""));
        
        
        //settare il contatto selezionato
        //contact.bindBidirectional((Property<Contact>) contactTable.getSelectionModel().getSelectedItem());
        /*ObjectBinding<Contact> c = Bindings.createObjectBinding(
            () -> contactTable.getSelectionModel().getSelectedItem(),
                contactTable.getSelectionModel().selectedItemProperty()
        );
        
        System.out.println(c.getValue());*/
        
        contactTable.setOnMouseClicked(event -> {
            Contact selectedContact = contactTable.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                contact = selectedContact;
                try {
                    loadNextInterface("DetailsContactView");
                    System.out.println(contact);
                } catch (IOException ex) {
                    Logger.getLogger(MainTableViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        
    }    

    /**
     * @brief Salva la lista dei contatti su file.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void saveContactList(ActionEvent event) {
    }

    /**
     * @brief Carica la lista dei contatti da file.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void loadContactList(ActionEvent event) {
    }

    /**
     * @brief Importa contatti da un file CSV.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void importContacts(ActionEvent event) {
    }

    /**
     * @brief Esporta contatti in un file CSV.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void exportContacts(ActionEvent event) {
    }

    /**
     * @brief Aggiunge un nuovo contatto alla lista, caricando la vista di aggiunta.
     * @param[in] event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento della vista FXML.
     */
    @FXML
    private void addContactToList(ActionEvent event) throws IOException {
        loadNextInterface("AddContactView");
    }

    /**
     * @brief Seleziona i contatti.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void selectContacts(ActionEvent event) {
    }

    /**
     * @brief Sceglie il parametro di ricerca.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void chooseSearchParameter(ActionEvent event) {
    }
    
    /**
     * @brief Ricerca contatti per cognome e nome.
     * @param[in] surname La proprietà Stringa del cognome.
     * @param[in] name La proprietà Stringa del nome.
     * @return Un'ObservableList di contatti corrispondenti che soddisfano i criteri di ricerca 
     */
    public ObservableList searchBySurnameAndName(StringProperty surname, StringProperty name) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per cognome .
     * @param[in] surname La proprietà Stringa del cognome.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchBySurname(StringProperty surname) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per nome .
     * @param[in] name La proprietà Stringa del nome.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca. 
     */
    public ObservableList searchByName(StringProperty name) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per email (non implementato).
     * @param[in] email La proprietà Stringa dell'email.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchByEmail(StringProperty email) {
        return null;
    }
  
    /**
     * @brief Ricerca contatti per numero di telefono (non implementato).
     * @param[in] phone La proprietà Stringa del numero di telefono.
     * @return UUn'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchByPhoneNumber(StringProperty phone) {
        return null;
    }
    
    public void loadNextInterface(String fxml) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        splitPane.getItems().remove(1);
        splitPane.getItems().add(fxmlLoader.load());
        configureController(fxmlLoader.getController());
    }
    
    public void configureController(ContactController c) {
        if (c instanceof AddContactController) {
            AddContactController controller = (AddContactController) c;
            controller.setContactList(contactList); 
            controller.setContact(contact);
        }
        if (c instanceof DetailsContactController) {
            DetailsContactController controller = (DetailsContactController) c;
            controller.setContactList(contactList); 
            controller.setContact(contact);
        }
        if (c instanceof EditContactController) {
            EditContactController controller = (EditContactController) c;
            controller.setContactList(contactList); 
            controller.setContact(contact);
        }
    }
}
