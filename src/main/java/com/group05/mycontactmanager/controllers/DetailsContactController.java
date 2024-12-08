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

    /**
     * @brief Inizializza il controller dopo il caricamento della vista.
     * @param url L'URL per risolvere percorsi relativi.
     * @param rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Logica di inizializzazione se necessaria.
    }

    /**
     * @brief Azione associata al compito sinistro "LeftTask"
     *        Carica l'interfaccia di modifica del contatto
     * @param event L'evento ActionEvent generato dall'interfaccia.
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
     * @param event L'evento ActionEvent generato dall'interfaccia.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
      
    }
}
