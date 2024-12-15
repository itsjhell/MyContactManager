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
 * @brief Controller dedicato alla vista per l'aggiunta di un nuovo contatto.
 * 
 * Questa classe gestisce l'interfaccia grafica relativa alla creazione di un nuovo contatto.
 * Permette di iniziare il processo, mostrare la vista di dettaglio dopo l'aggiunta e interagire con i campi di input.
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
     *
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
        setupButtons(phoneNumbers, adderPhoneButton, errorNumber);
        setupButtons(emailFields, adderEmailButton, errorEmail);
        setupEmailBinding(emailFields);
        
        setupSaveButtonBinding(addButton);
    }

    /**
     * @brief Azione associata al "compito sinistro" (LeftTask).
     * 
     * Aggiunge il contatto alla lista e ne permette visualizzazione dei dettagli.
     * 
     * @param[in] event L'evento ActionEvent generato dall'interfaccia.
     * @throws IOException In caso di errore nel caricamento della nuova vista FXML.
     */
    @FXML
    @Override
    void executeLeftTask(ActionEvent event) throws IOException {
        
        numbers = new ArrayList<PhoneNumber>();
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
        
        Contact contact = new Contact(nameField.getText(), surnameField.getText(), numbers, emailAddresses, imageNameApp, notesArea.getText());
        contactList.add(contact);
        
        loadDetailsContact(splitPane, contact, contactList);
    }

    /**
     * @brief Azione associata al "compito destro" (RightTask).
     *        
     * Anulla l'operazione di aggiunta contatto.
     * 
     * @param[in] event L'evento ActionEvent generato dall'interfaccia.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
        contactProperty.get().setImageName(imageNameApp);
        splitPane.getItems().remove(1);
    }
 
    private void setupButtons(TextField[] fields, Button button, Label error) {
        ComboBox<PhonePrefix>[] prefixMenus = new ComboBox[]{ prefixMenu1, prefixMenu2, prefixMenu3 };
        
        final int[] count = {0};
        //Integer count = new Integer(0);
        for(int i = 0; i < 3; i++) {
            fields[i].setDisable(true);
            prefixMenus[i].setDisable(true);
        }
        
        button.setOnAction(event -> {
        switch (count[0]) {
            case 0:
                fields[0].setDisable(false);
                prefixMenus[0].setDisable(false);
                break;
            case 1:
                if(fields[0].getText().equals("") || !error.getText().equals("")) {
                    count[0]--;
                    break;
                }
                fields[1].setDisable(false);
                prefixMenus[1].setDisable(false); 
                break;
            case 2:
                if(fields[1].getText().equals("") || !error.getText().equals("")) {
                    count[0]--;
                    break;
                }
                fields[2].setDisable(false);
                prefixMenus[2].setDisable(false);
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
