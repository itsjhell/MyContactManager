package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import com.group05.mycontactmanager.utilities.Checker;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

/**
 * @file EditContactController.java
 * @brief Controller dedicato alla modifica di un contatto esistente.
 * 
 * Questa classe permette di visualizzare i campi di un contatto già esistente e modificarli.
 * 
 * 
 * @author group05
 * @date Dicembre 08,2024
 * @version 1.0
 */
public class EditContactController extends ContactController implements Initializable {

    @FXML
    private Button saveEditsButton;
    @FXML
    private Button cancelEditsButton;
    
    private Contact newContact;

    EditContactController(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) {
        super();
        contactProperty.set(contact);
        this.contactList = contactList;
        this.splitPane = splitPane;
    }
    
    /**
     * @brief Inizializza il controller dopo il caricamento della vista.
     * @param[in] url L'URL per percorsi relativi.
     * @param[in] rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTextFields(contactProperty.get());
        viewImageSetted(contactProperty.get().getImagePath());
        //bindings
            //controlli sui textField
            //tra una copia del contatto e tra i text field
        newContact = contactProperty.get().clone();
        //setupNameBinding();
        //setupPhoneBinding(prefixMenu1,phoneNumber1,"1) Inserisci un formato corretto.");
        
        setupEmailBinding();
        
        
    }

    /**
     * @brief Azione associata al compito sinistro:
     *        ritorna alla visualizzazione dei dettagli del contatto.
     * @param[in] event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento della vista.
     */
    @FXML
    @Override
    void executeLeftTask(ActionEvent event) throws IOException {
        loadDetailsContactInterface();
        //salvataggio del contatto
        contactProperty.set(newContact);
        
        
    }

    /**
     * @brief Azione associata al compito destro.
     *        salva le modifiche al contatto.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
       
    }
    
    private void fillTextFields(Contact contact) {
        //array di TextField per rendere più pulito in caricamento al suo interno
        TextField[] phoneFields = { phoneNumber1, phoneNumber2, phoneNumber3 };
        TextField[] emailFields = { emailAddress1, emailAddress2, emailAddress3 };
        List<PhoneNumber> numbers = contact.getNumbers();
        List<String> emailAddresses = contact.getEmailAddresses();
        //caricamento dei campi
        nameField.setText(contact.getName());
        surnameField.setText(contact.getSurname());
        notesArea.setText(contact.getNotes());
        //caricamento dei numeri
        for(int i=0; numbers != null && i<numbers.size(); i++)
            phoneFields[i].setText(numbers.get(i).getNumber());
        //caricamento delle email
        for(int i=0; emailAddresses != null && i<emailAddresses.size(); i++)
            emailFields[i].setText(emailAddresses.get(i));
    }

    private void viewImageSetted(String imagePath) {
        contactImage.setId(imagePath);
    }

    private void loadDetailsContactInterface() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditContactView.fxml"));
        splitPane.getItems().remove(1);
        // BINDING PER NOTIFICARE IL CONTATTO CHE DEVE PASSARE!
        fxmlLoader.setControllerFactory(param -> new EditContactController(splitPane, contactProperty.get(), contactList)); // Usa una fabbrica per creare il controller
        splitPane.getItems().add(fxmlLoader.load());
    }
    
    private void setupNameBinding() {
        BooleanBinding nameBinding = Bindings.createBooleanBinding(() -> {
            return !nameField.getText().isEmpty() || !surnameField.getText().isEmpty();
        }, nameField.textProperty(), surnameField.textProperty());
        
        errorName.visibleProperty().bind(nameBinding.not());
    }
    
    private void setupPhoneBinding(ComboBox<PhonePrefix> prefix, TextField phoneNumber, String errorText) {
        BooleanBinding phoneBinding = Bindings.createBooleanBinding(() -> {
            return Checker.checkNumber(new PhoneNumber(prefix.getValue(), phoneNumber.getText()));
        }, prefix.valueProperty(), phoneNumber.textProperty());
        
        StringBinding errorBinding = Bindings.createStringBinding(() -> {
            return errorText;
        }, phoneBinding);
        
        // Bind per la label e segnalare l'eventuale errore
        errorNumber.visibleProperty().bind(phoneBinding.not());
        errorNumber.textProperty().bind(errorBinding);
    }
    
    private void setupEmailBinding() {
        TextField[] emailFields = { emailAddress1, emailAddress2, emailAddress3 };

        StringBinding emailBinding = Bindings.createStringBinding(() -> {
            for(int i = 0; i <emailFields.length; i++) {
                if(Checker.checkEmail(emailFields[i].getText()))
                    return "Email "+i+" ha un formato errato";
            }
            return "";
        }, emailFields[0].textProperty(), emailFields[1].textProperty(), emailFields[2].textProperty());

        // Bind per la label
        errorEmail.textProperty().bind(emailBinding);
    }

    private void setupEditBinding() {
        // Creazione del BooleanBinding che verifica se tutte e tre le label sono visibili
        BooleanBinding addBinding = Bindings.createBooleanBinding(() -> {
            return !errorName.isVisible() && !errorNumber.isVisible() && !errorEmail.isVisible();
        }, errorName.visibleProperty(), errorNumber.visibleProperty(), errorEmail.visibleProperty());

        saveEditsButton.disableProperty().bind(addBinding.not());   
    }
}
