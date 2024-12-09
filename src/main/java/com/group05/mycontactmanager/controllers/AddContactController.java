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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

    /**
     * @brief Inizializza il controller dopo il caricamento della vista.
     * @param[in] url L'URL utilizzato per risolvere percorsi relativi.
     * @param[in] rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.setDisable(true);
        prefixMenu1.setItems(FXCollections.observableArrayList(PhonePrefix.values()));
        setupNameBinding();
       // setupPhoneBinding(prefixMenu1,phoneNumber1);
        errorNumber.setVisible(false);
        setupEmailBinding(emailAddress1);
      //  setupEmailBinding(emailAddress2);
      //  setupEmailBinding(emailAddress3);
        setupAddBinding();
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DetailsContactView" + ".fxml"));
        splitPane.getItems().remove(1);
        splitPane.getItems().add(fxmlLoader.load());
        
        numbers = new ArrayList();
        numbers.add(new PhoneNumber(PhonePrefix.OTHERS,phoneNumber1.getText()));
        numbers.add(new PhoneNumber(PhonePrefix.OTHERS,phoneNumber2.getText()));
        numbers.add(new PhoneNumber(PhonePrefix.OTHERS,phoneNumber3.getText()));
       
        emailAddresses = new ArrayList();
        emailAddresses.add(emailAddress1.getText());
        emailAddresses.add(emailAddress2.getText());
        emailAddresses.add(emailAddress3.getText());
      
        contactList.add(new Contact(nameField.getText(), surnameField.getText(), numbers, emailAddresses, "", ""));
        
    }

    /**
     * @brief Azione associata al "compito destro" (RightTask).
     *        Anulla l'operazione di aggiunta contatto.
     * @param[in] event L'evento ActionEvent generato dall'interfaccia.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
        splitPane.getItems().remove(1);
    }
    
    private void setupNameBinding() {
        BooleanBinding nameBinding = Bindings.createBooleanBinding(() -> {
            return !nameField.getText().isEmpty() || !surnameField.getText().isEmpty();
        }, nameField.textProperty(), surnameField.textProperty());
        
        errorName.visibleProperty().bind(nameBinding.not());
    }
    
    private void setupPhoneBinding(ComboBox<PhonePrefix> prefix, TextField phoneNumber) {
        BooleanBinding phoneBinding = Bindings.createBooleanBinding(() -> {
            return Checker.checkNumber(new PhoneNumber(prefix.getValue(), phoneNumber.getText()));
        }, prefix.valueProperty(), phoneNumber.textProperty());

        // Bind per la label
        errorNumber.visibleProperty().bind(phoneBinding.not());
       // addButton.disableProperty().bind(phoneBinding);   
    }
    
    private void setupEmailBinding(TextField emailAddress) {
        BooleanBinding emailBinding = Bindings.createBooleanBinding(() -> {
            return Checker.checkEmail(emailAddress.getText());
        }, emailAddress.textProperty());

        // Bind per la label
        errorEmail.visibleProperty().bind(emailBinding.not());
    }

    private void setupAddBinding() {
        // Creazione del BooleanBinding che verifica se tutte e tre le label sono visibili
        BooleanBinding addBinding = Bindings.createBooleanBinding(() -> {
            return !errorName.isVisible() && !errorNumber.isVisible() && !errorEmail.isVisible();
        }, errorName.visibleProperty(), errorNumber.visibleProperty(), errorEmail.visibleProperty());

        // Imposta la visibilità di una quarta label (o qualsiasi altro componente) in base alla condizione
        addButton.disableProperty().bind(addBinding.not());   
    }
}
