package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import com.group05.mycontactmanager.utilities.Checker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
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
 * @file AddContactController.java
 * @brief Controller dedicato alla vista per l'aggiunta di un nuovo contatto.
 * 
 * Questa classe gestisce l'interfaccia grafica relativa alla creazione di un nuovo contatto.
 * Permette di iniziare il processo, mostrare la vista di dettaglio dopo l'aggiunta,
 * e interagire con i campi di input.
 * 
 * @author group05
 * @date Dicembre 08,2024
 * @version 1.0
 */
public class AddContactController extends ContactController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    
    AddContactController(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) {
        super(splitPane, contact, contactList);
    }


    /**
     * @brief Inizializza il controller dopo il caricamento della vista.
     * @param[in] url L'URL utilizzato per risolvere percorsi relativi.
     * @param[in] rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preloadImage();
        addButton.setDisable(true);
        setupPrefixMenu();
        setupNameBinding();
        setupPhoneBinding();
        
        TextField[] emailFields = { emailAddress1, emailAddress2, emailAddress3 };
        TextField[] phoneNumbers = { phoneNumber1, phoneNumber2, phoneNumber3 };
        setupButtons(phoneNumbers, adderPhoneButton);
        setupButtons(emailFields, adderEmailButton);
        setupEmailBinding(emailFields);
        
        setupSaveButtonBinding(addButton);
    }

    /**
     * @brief Azione associata al "compito sinistro" (LeftTask).
     *        Aggiunge il connatto alla lista e ne permette visualizzazione dei dettagli.
     * 
     * @param[in] event L'evento ActionEvent generato dall'interfaccia.
     * @throws IOException In caso di errore nel caricamento della nuova vista FXML.
     */
    @FXML
    @Override
    void executeLeftTask(ActionEvent event) throws IOException {
        
        numbers = new ArrayList();
        numbers.add(new PhoneNumber(prefixMenu1.getValue(),phoneNumber1.getText()));
        numbers.add(new PhoneNumber(prefixMenu2.getValue(),phoneNumber2.getText()));
        numbers.add(new PhoneNumber(prefixMenu3.getValue(),phoneNumber3.getText()));
       
        emailAddresses = new ArrayList();
        emailAddresses.add(emailAddress1.getText());
        emailAddresses.add(emailAddress2.getText());
        emailAddresses.add(emailAddress3.getText());
      
        //Contact contact = new Contact(nameField.getText(), surnameField.getText(), numbers, emailAddresses, contactProperty.get().getImageName(), notesArea.getText());
        Contact contact = new Contact(nameField.getText(), surnameField.getText(), numbers, emailAddresses, imageNameApp, notesArea.getText());
        contactList.add(contact);
        
        loadDetailsContact(splitPane, contact, contactList);
    }

    /**
     * @brief Azione associata al "compito destro" (RightTask).
     *        Anulla l'operazione di aggiunta contatto.
     * @param[in] event L'evento ActionEvent generato dall'interfaccia.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
        contactProperty.get().setImageName(imageNameApp);
        splitPane.getItems().remove(1);
    }
 
    private void setupButtons(TextField[] fields, Button button) {
        final int[] count = {0};
        //Integer count = new Integer(0);
        for(int i = 0; i < 3; i++) {
            fields[i].setDisable(true);
        }
        prefixMenu1.setDisable(true); 
        prefixMenu2.setDisable(true); 
        prefixMenu3.setDisable(true);
        
        button.setOnAction(event -> {
        switch (count[0]) {
            case 0:
                fields[0].setDisable(false);
                prefixMenu1.setDisable(false);
                break;
            case 1:
                fields[1].setDisable(false);
                prefixMenu2.setDisable(false); 
                break;
            case 2:
                fields[2].setDisable(false);
                prefixMenu3.setDisable(false);
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
