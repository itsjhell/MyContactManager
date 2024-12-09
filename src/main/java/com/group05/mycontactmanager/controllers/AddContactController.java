package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
        
        // contactList.add(new Contact(...));
    }

    /**
     * @brief Azione associata al "compito destro" (RightTask).
     *        Anulla l'operazione di aggiunta contatto.
     * @param[in] event L'evento ActionEvent generato dall'interfaccia.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
       
    }
}
