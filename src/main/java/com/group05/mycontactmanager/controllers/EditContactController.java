package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
        configureElements();
        //bindings
            //controlli sui textField
            //tra una copia del contatto e tra i text field
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

    private void configureElements() {
        //imposto un effetto visivo e rendo non edistabili i campi
        notesArea.setEditable(true);
        TextField[] fields = { nameField, surnameField, phoneNumber1, phoneNumber2, phoneNumber3, emailAddress1, emailAddress2, emailAddress3, };
        for (TextField field : fields) {
            field.setEditable(true);
            field.setOpacity(1.0);
        }
        ComboBox[] comboBoxes = { prefixMenu1, prefixMenu2, prefixMenu3 };
        for (ComboBox combobox : comboBoxes) {
            combobox.setEditable(true);
            combobox.setOpacity(1);
        }
        //imposto un effetto visivo e rendo non edistabili i campi
        Label[] labelsToHide = {errorName, errorNumber, errorEmail};
        for (Label label : labelsToHide)
            label.setVisible(true);
        
        Button[] buttonsToHide = { imageButton, adderPhoneButton, adderEmailButton};
        for (Button button : buttonsToHide)
            button.setVisible(true);
    
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
}
