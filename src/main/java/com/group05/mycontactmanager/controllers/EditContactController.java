package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import com.group05.mycontactmanager.utilities.Checker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    
    //private Contact newContact;

    EditContactController(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) {
        super(splitPane, contact, contactList);
    }
    
    /**
     * @brief Inizializza il controller dopo il caricamento della vista.
     * @param[in] url L'URL per percorsi relativi.
     * @param[in] rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preloadImage();
        fillTextFields(contactProperty.get());
      
        TextField[] emailFields = { emailAddress1, emailAddress2, emailAddress3 };
        TextField[] phoneNumbers = { phoneNumber1, phoneNumber2, phoneNumber3 };
        setupButtons(phoneNumbers, adderPhoneButton);
        setupButtons(emailFields, adderEmailButton);
        setupNameBinding();
        setupPhoneBinding();
        setupEmailBinding(emailFields);
        
        setupSaveButtonBinding(saveEditsButton);
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
        //Raccolgo i dati dai campi
        numbers = new ArrayList();
        numbers.add(new PhoneNumber(prefixMenu1.getValue(),phoneNumber1.getText()));
        numbers.add(new PhoneNumber(prefixMenu2.getValue(),phoneNumber2.getText()));
        numbers.add(new PhoneNumber(prefixMenu3.getValue(),phoneNumber3.getText()));
       
        emailAddresses = new ArrayList();
        emailAddresses.add(emailAddress1.getText());
        emailAddresses.add(emailAddress2.getText());
        emailAddresses.add(emailAddress3.getText());
        //creo il contatto
        Contact contact = new Contact(nameField.getText(), surnameField.getText(), numbers, emailAddresses, contactProperty.get().getImagePath(), notesArea.getText());
        //lo sostituisco
        contactList.remove(contactProperty.get());
        //lo sostituisco
        contactProperty.set(contact);
        contactList.add(contact);
        //carcio la prossima interfaccia
        loadDetailsContact(splitPane, contactProperty.get(), contactList);        
    }

    /**
     * @brief Azione associata al compito destro.
     *        salva le modifiche al contatto.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
        splitPane.getItems().remove(1);
    }
    
    private void setupButtons(TextField[] fields, Button button) {
        final int[] count= {0};
        while(!fields[count[0]].getText().equals("")) {
            count[0]++;
        }
        //Integer count = new Integer(0);
        for(int i = count[0]; i < 3; i++) {
            fields[i].setDisable(true);
        }
        
        button.setOnAction(event -> {
            switch (count[0]) {
                case 0:
                    fields[0].setDisable(false);
                    break;
                case 1:
                    fields[1].setDisable(false);
                    break;
                case 2:
                    fields[2].setDisable(false);
                    button.setVisible(false);
                    break;
                default:
                    // Non fare nulla dalla quarta pressione in poi
                    return;
            }
            count[0]++;
        });
    }
}
