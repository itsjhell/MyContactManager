package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

/**
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

    EditContactController(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) {
        super(splitPane, contact, contactList);
    }
    
    /**
     * @brief Inizializza il controller dopo il caricamento della vista.
     *
     * @param[in] url L'URL per percorsi relativi.
     * @param[in] rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preloadImage();
        setupPrefixMenu();
        fillTextFields(contactProperty.get());
        setupNameBinding();
        setupPhoneBinding();
      
        TextField[] emailFields = { emailAddress1, emailAddress2, emailAddress3 };
        TextField[] phoneNumbers = { phoneNumber1, phoneNumber2, phoneNumber3 };
        setupButtons(phoneNumbers, adderPhoneButton, errorNumber);
        setupButtons(emailFields, adderEmailButton, errorEmail);
        loadPrefixMenu(phoneNumbers);
        setupEmailBinding(emailFields);
        
        setupSaveButtonBinding(saveEditsButton);
    }

    /**
     * @brief Azione associata al compito sinistro:
     *
     * ritorna alla visualizzazione dei dettagli del contatto.
     *
     * @param[in] event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento della vista.
     */
    @FXML
    @Override
    void executeLeftTask(ActionEvent event) throws IOException {
        numbers = new ArrayList(); //Raccolgo i dati dai campi
        if(!phoneNumber1.getText().equals(""))
            numbers.add(new PhoneNumber(prefixMenu1.getValue(),phoneNumber1.getText()));
        if(!phoneNumber2.getText().equals(""))
            numbers.add(new PhoneNumber(prefixMenu2.getValue(),phoneNumber2.getText()));
        if(!phoneNumber3.getText().equals(""))
            numbers.add(new PhoneNumber(prefixMenu3.getValue(),phoneNumber3.getText()));
        while(numbers.size() < 3)  numbers.add(new PhoneNumber(PhonePrefix.fromString("-"),""));
        
        emailAddresses = new ArrayList();
        if(!emailAddress1.getText().equals(""))
            emailAddresses.add(emailAddress1.getText());
        if(!emailAddress2.getText().equals(""))
            emailAddresses.add(emailAddress2.getText());
        if(!emailAddress3.getText().equals(""))
            emailAddresses.add(emailAddress3.getText());
        while(emailAddresses.size() < 3)  emailAddresses.add("");

        Contact contact = new Contact(nameField.getText(), surnameField.getText(), numbers, emailAddresses, imageNameApp, notesArea.getText()); //creo il contatto
        contactList.remove(contactProperty.get()); //lo sostituisco
        contactProperty.set(contact); //lo sostituisco
        contactList.add(contact);
        loadDetailsContact(splitPane, contactProperty.get(), contactList); //carcio la prossima interfaccia
    }

    /**
     * @brief Azione associata al compito destro.
     *
     * salva le modifiche al contatto.
     *
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) throws IOException {
        splitPane.getItems().remove(1);
        loadDetailsContact(splitPane, contactProperty.get(), contactList);        
    }
    
    private void setupButtons(TextField[] fields, Button button, Label error) {
        
        final int[] count= {0};
        while(count[0]<3 && !fields[count[0]].getText().equals("")) {
                count[0]++;
        }
        
        for(int i = count[0]; i < 3; i++) {
            fields[i].setDisable(true);
        }
        
        if (count[0] == 3) button.setVisible(false);
        
        button.setOnAction(event -> {
            switch (count[0]) {
                case 0:
                    fields[0].setDisable(false);
                    break;
                case 1:
                    if(fields[0].getText().equals("") || !error.getText().equals("")) {
                        return;
                    }
                    fields[1].setDisable(false);
                    break;
                case 2:
                    if(fields[1].getText().equals("") || !error.getText().equals("")) {
                        return;
                    }
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
    
    private void loadPrefixMenu(TextField[] phoneNumbers) {
        ComboBox<PhonePrefix>[] prefixMenus = new ComboBox[]{ prefixMenu1, prefixMenu2, prefixMenu3 };
        for (int i = 0; i < 3; i++) {
            int index = i;
            prefixMenus[index].disableProperty().bind(phoneNumbers[index].disableProperty());
        }
    }
}
