package com.group05.mycontactmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @brief Classe principale dell'applicazione JavaFX "MyContactManager".
 * 
 * Estende la classe `Application` e definisce il metodo `start` che inizializza 
 * la scena principale, carica il layout da un file FXML e configura le proprietà 
 * fondamentali della finestra. 
 * Il metodo `main` avvia l'esecuzione dell'applicazione.
 * 
 * @author group05
 * @date Dicembre 08,2024
 * @version 1.0
 */
public class App extends Application {

    private static Scene scene;

    /**
     * @brief Punto di ingresso dell'applicazione JavaFX.
     * 
     * Questo metodo viene invocato all'avvio 
     * dell'applicazione. 
     * Carica la vista "MainTableView" da un file FXML, 
     * imposta le dimensioni minime della finestra, un titolo e mostra lo stage.
     * 
     * @pre 'loadFXML' la risorsa .fxml deve esistere
     * 
     * @param[in] stage Lo `Stage` principale.
     * @throws IOException In caso di errore nel caricamento del file FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainTableView"), 1300, 800);
        stage.setScene(scene);
        stage.setMinWidth(1300); 
        stage.setMinHeight(800); 
        stage.setTitle("MyContactManager");
        stage.show();
    }

    /**
     * @brief Cambia la vista radice della scena corrente.
     * @param[in] fxml Il nome del file FXML da caricare come nuova radice.
     * @throws IOException In caso di errore nel caricamento del file FXML.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * @brief Carica un file FXML e ne restituisce il nodo radice.
     * 
     * Questo metodo utilizza il `FXMLLoader` per caricare la risorsa FXML 
     * corrispondente al nome fornito e restituire il `Parent` ottenuto.
     * 
     * @param[in] fxml Il nome del file FXML (senza `.fxml`) da caricare.
     * @return Un oggetto `Parent` caricato dalla risorsa FXML specificata.
     * @throws IOException In caso di errore nel caricamento del file FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * @brief Metodo principale dell'applicazione.
     * 
     * Esegue il lancio dell'applicazione JavaFX. Una volta chiamato, si attiva 
     * il ciclo di vita JavaFX che a sua volta invoca il metodo `start`.
     * 
     * @param[in] args Argomenti passati da linea di comando, se presenti.
     */
    public static void main(String[] args) {
        launch();
    }

}
