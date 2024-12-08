package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    
    /**
     * @brief Inizializza il controller dopo il caricamento della vista.
     * @param url L'URL per percorsi relativi.
     * @param rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    /**
     * @brief Azione associata al compito sinistro:
     *        ritorna alla vista dei dettagli del contatto.
     * @param event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento della vista.
     */
    @FXML
    @Override
    void executeLeftTask(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DetailsContactView" + ".fxml"));        
        splitPane.getItems().remove(1);
        splitPane.getItems().add(fxmlLoader.load());
    }

    /**
     * @brief Azione associata al compito destro.
     *        salvare le modifiche al contatto.
     * @param event L'ActionEvent associato.
     */
    @FXML
    @Override
    void executeRightTask(ActionEvent event) {
       
    }
}
