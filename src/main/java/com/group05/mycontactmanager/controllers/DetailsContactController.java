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
 * @file DetailsContactController.java
 * @brief Controller dedicato alla vista di dettaglio di un contatto.
 * 
 * Questa classe permette la visualizzazione dei dettagli di un contatto selezionato,
 * fornendo anche la possibilità di accedere alla modifica del contatto o alla sua rimozione.
 * 
 * @author group05
 * @date Dicembre 08,2024
 * @version 1.0
 */
public class DetailsContactController extends ContactController implements Initializable {

    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;    

    DetailsContactController(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) {
        super();
        contactProperty.set(contact);
        this.contactList = contactList;
        this.splitPane = splitPane;
    }

    /**
     * @brief Inizializza il controller dopo il caricamento della vista.
     * @param[in] url L'URL per risolvere percorsi relativi.
     * @param[in] rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*     
        StringBinding nameBinding = Bindings.createStringBinding(() -> {
            return new SimpleStringProperty(contactProperty.get().getName());
        }, nameField.textProperty(), contactProperty);
        
        nameField.textProperty().bind(nameBinding);*/
        
        
        fillTextFields(contactProperty.get());
        
        configureElements();
        
        
    }

    /**
     * @brief Azione associata al compito sinistro "LeftTask"
     *        Carica l'interfaccia di modifica del contatto
     * @param[in] event L'evento ActionEvent generato dall'interfaccia.
     * @throws IOException In caso di errore nel caricamento della vista FXML.
     */
    @FXML
    @Override
    void executeLeftTask(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditContactView" + ".fxml"));
        splitPane.getItems().remove(1);
        splitPane.getItems().add(fxmlLoader.load());

       
    }

    /**
     * @brief Azione associata al compito destro "RightTask"
     *        Rimuove il contatto dalla lista.
     * @param[in] event L'evento ActionEvent generato dall'interfaccia.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
        contactList.remove(contactProperty.get());
        splitPane.getItems().remove(1);
        
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

    private void configureElements() {
        //imposto un effetto visivo e rendo non edistabili i campi
        notesArea.setEditable(false);
        TextField[] fields = { nameField, surnameField, phoneNumber1, phoneNumber2, phoneNumber3, emailAddress1, emailAddress2, emailAddress3, };
        for (TextField field : fields) {
            field.setEditable(false);
            field.setOpacity(0.75);
        }
        ComboBox[] comboBoxes = { prefixMenu1, prefixMenu2, prefixMenu3 };
        for (ComboBox combobox : comboBoxes) {
            combobox.setEditable(false);
            combobox.setOpacity(0.75);
        }
        
        //imposto un effetto visivo e rendo non edistabili i campi
        Label[] labelsToHide = {errorName, errorNumber, errorEmail};
        for (Label label : labelsToHide)
            label.setVisible(false);
        
        Button[] buttonsToHide = { imageButton, adderPhoneButton, adderEmailButton};
        for (Button button : buttonsToHide)
            button.setVisible(false);
    }
}
