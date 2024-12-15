package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import com.group05.mycontactmanager.utilities.Checker;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * @brief Classe controller astratta che fornisce elementi comuni per la gestione delle viste legate ai contatti.
 * 
 * Questa classe definisce componenti UI e metodi comuni a diversi controller,
 * come AddContactController, DetailsContactController ed EditContactController.
 * Gestisce campi di input per un contatto e accede al riferimento di uno SplitPane per caricare la successiva interfaccia.
 * 
 * @author group05
 * @date Dicembre 08, 2024
 * @version 1.1
 */
abstract class ContactController {
    
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField surnameField;
    @FXML
    protected Label errorName;
    @FXML
    protected ImageView contactImage;
    @FXML
    protected Button imageButton;
    @FXML
    protected ComboBox<PhonePrefix> prefixMenu1;
    @FXML
    protected ComboBox<PhonePrefix> prefixMenu2;
    @FXML
    protected ComboBox<PhonePrefix> prefixMenu3;
    @FXML
    protected Button adderPhoneButton;
    @FXML
    protected TextField phoneNumber1;
    @FXML
    protected TextField phoneNumber2;
    @FXML
    protected TextField phoneNumber3;
    @FXML
    protected Label errorNumber;
    @FXML
    protected Button adderEmailButton;
    @FXML
    protected TextField emailAddress1;
    @FXML
    protected TextField emailAddress3;
    @FXML
    protected TextField emailAddress2;
    @FXML
    protected Label errorEmail;
    @FXML
    protected TextArea notesArea;
    
    protected SplitPane splitPane;
    
    protected String imageNameApp;
    
    protected ObjectProperty<Contact> contactProperty;
            
    protected ObservableList<Contact> contactList;
    
    protected List<PhoneNumber> numbers;
    
    protected List<String> emailAddresses;
       
    /**
     * @brief Costruttore della superclasse astratta.
     * 
     * @param[in] splitPane Node dell'interfaccia.
     * @param[in] contactList lista osservabile di contatti.
     */
    public ContactController(SplitPane splitPane, ObservableList<Contact> contactList) {
        contactProperty = new SimpleObjectProperty();
        this.contactList = contactList;
        this.splitPane = splitPane;
    }
    
    /**
     * @brief Costruttore alternativo della superclasse astratta.
     * 
     * @param[in] splitPane Node dell'interfaccia.
     * @param[in] contact il contatto da modificare o visualizzare.
     * @param[in] contactList lista osservabile di contatti.
     */
    public ContactController(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) {
        contactProperty = new SimpleObjectProperty();
        contactProperty.set(contact);
        this.contactList = contactList;
        this.splitPane = splitPane;
        imageNameApp = contactProperty.get().getImageName();
    }
    
    /**
     * @brief Carica l'immagine attuale del contatto.
     * 
     */
    protected void preloadImage() {
        // HO CREATO UN CONTATTO CON UN'IMMAGINE E POI SALVARE LA RUBRICA CHE LO CONTINE
            // SUCCESSIVAMENTE CARICANDO LA RUBRICA SALVATA SE NON VIENE TROVATA L'IMMAGINE CAUSA UN'ECCEZIONE
        // QUINDI CONTROLLO SE SI TROVA IL FILE
            // SE NON SI TROVA CARICO L'IMMAGINE DI DEFAULT <-- SAR
        File file = new File(contactProperty.get().getImagePath());
        String pathToDefault = "images/default.png";
        Image newImage;
        if (file.exists()) {
            newImage = new Image(file.toURI().toString());
        } else {
            newImage = new Image(getClass().getClassLoader().getResource(pathToDefault).toExternalForm()); // prende l'immagine di default dalle risorse del JAR
        }
        contactImage.setImage(newImage);
    }
    
    /**
     * @brief Carica un'immagine per il contatto.
     * 
     * @param[in] event L'ActionEvent associato all'azione.
     */
    @FXML
    private void loadImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Immagini", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            File imagesDir = new File("images"); // crea la cartella images nella stessa directory del JAR, se non esiste
            if (!imagesDir.exists()) {
                imagesDir.mkdirs(); 
            }
            File copiedFile = new File(imagesDir, selectedFile.getName());
            Files.copy(selectedFile.toPath(), copiedFile.toPath(), StandardCopyOption.REPLACE_EXISTING); // crea copia in locale
            Image newImage = new Image(copiedFile.toURI().toString());
            contactImage.setImage(newImage);
            imageNameApp = copiedFile.getName();
        }
    }
    
    /**
     * @brief Sceglie il prefisso telefonico.
     * 
     * @param[in] event L'ActionEvent associato alla scelta del prefisso.
     */
    @FXML
    private void choosePrefix(ActionEvent event) {
    }

    /**
     * @brief Aggiunge un numero di telefono.
     * 
     * @param[in] event L'ActionEvent associato all'azione.
     */
    @FXML
    private void addPhoneNumber(ActionEvent event) {
        TextField[] phoneFields = { phoneNumber1, phoneNumber2, phoneNumber3 };
        for (int i = 0; i < contactList.size(); i++) {
            phoneFields[i].setEditable(true);
            phoneFields[i].setOpacity(0.75);
        }
        phoneFields[contactList.size()+1].setEditable(true);
    }

    /**
     * @brief Aggiunge un indirizzo email.
     * 
     * @param[in] event L'ActionEvent associato all'azione.
     */
    @FXML
    private void addEmail(ActionEvent event) {
    }
        
    /**
     * @brief Carica l'interfaccia di dettaglio del contatto.
     * 
     * @param[in] splitPane Node dell'interfaccia.
     * @param[in] contact il contatto da modificare o visualizzare.
     * @param[in] contactList lista osservabile di contatti.
     */
    protected void loadDetailsContact(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DetailsContactView.fxml"));
        if (splitPane.getItems().size() > 1)
            splitPane.getItems().remove(1);
        fxmlLoader.setControllerFactory(param -> new DetailsContactController(splitPane, contact, contactList)); // Usa una fabbrica per creare il controller
        splitPane.getItems().add(fxmlLoader.load());
    }
    
    /**
     * @brief Carica l'interfaccia di modifica del contatto.
     * 
     * @param[in] splitPane Node dell'interfaccia.
     * @param[in] contact il contatto da modificare o visualizzare.
     * @param[in] contactList lista osservabile di contatti.
     */
    protected void loadEditContact(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditContactView.fxml"));
        if (splitPane.getItems().size() > 1)
            splitPane.getItems().remove(1);
        fxmlLoader.setControllerFactory(param -> new EditContactController(splitPane, contact, contactList)); // Usa una fabbrica per creare il controller
        splitPane.getItems().add(fxmlLoader.load());
    }
    
    /**
     * @brief Carica nei TextField le informazioni del contatto.
     * 
     * @param[in] contact il contatto da modificare o visualizzare.
     */
    protected void fillTextFields(Contact contact) {
        //array di TextField per rendere più pulito in caricamento al suo interno
        TextField[] phoneFields = { phoneNumber1, phoneNumber2, phoneNumber3 };
        TextField[] emailFields = { emailAddress1, emailAddress2, emailAddress3 };
        ComboBox<PhonePrefix>[] prefixMenus = new ComboBox[]{ prefixMenu1, prefixMenu2, prefixMenu3 };
        List<PhoneNumber> numbers = contact.getNumbers();
        List<String> emailAddresses = contact.getEmailAddresses();
        //caricamento dei campi
        nameField.setText(contact.getName());
        surnameField.setText(contact.getSurname());
        notesArea.setText(contact.getNotes());
        //caricamento dei numeri
        for(int i=0; numbers != null && i<numbers.size(); i++) {
            prefixMenus[i].getSelectionModel().select(numbers.get(i).getPrefix());
            phoneFields[i].setText(numbers.get(i).getNumber());
        }
            
        //caricamento delle email
        for(int i=0; emailAddresses != null && i<emailAddresses.size(); i++)
            emailFields[i].setText(emailAddresses.get(i));
    }
    
    /**
     * @brief Effettua un binding tra nome, cognome e relativa etichetta di errore.
     * 
     */
    protected void setupNameBinding() {
        StringBinding nameBinding = Bindings.createStringBinding(() -> {
            if (nameField.getText().isEmpty() && surnameField.getText().isEmpty()) 
                return "Inserisci nome o cognome";
            else
                return "";
        }, nameField.textProperty(), surnameField.textProperty());

        // Bind per la label
        errorName.textProperty().bind(nameBinding);
    }
    
    /**
     * @brief Effettua un binding tra i numeri di telefono, i prefissi e relativa etichetta di errore.
     * 
     */
    protected void setupPhoneBinding() {
        ComboBox<PhonePrefix>[] prefixMenus = new ComboBox[]{ prefixMenu1, prefixMenu2, prefixMenu3 }; 
        TextField[] phoneFields = { phoneNumber1, phoneNumber2, phoneNumber3 };
        StringBinding phoneBinding = Bindings.createStringBinding(() -> {
            for(int i = 0; i <prefixMenus.length; i++) {
                if (!Checker.checkNumber(new PhoneNumber(prefixMenus[i].getValue(), phoneFields[i].getText())))
                    return (i+1) + ") numero in formato errato";
            }
            return "";
        }, prefixMenus[0].valueProperty(), prefixMenus[1].valueProperty(), prefixMenus[2].valueProperty(), phoneFields[0].textProperty(), phoneFields[1].textProperty(), phoneFields[2].textProperty());
        
        // Bind per la label
        errorNumber.textProperty().bind(phoneBinding);
    }
    
    /**
     * @brief Effettua un binding tra le email e la relativa etichetta di errore.
     * 
     * @param[in] emailFields vettore dei TextField delle email.
     */
    protected void setupEmailBinding(TextField[] emailFields) {
        StringBinding emailBinding = Bindings.createStringBinding(() -> {
            for(int i = 0; i <emailFields.length; i++) {
                if(!Checker.checkEmail(emailFields[i].getText()))
                    return (i+1) + ") e-mail in formato errato";
            }
            return "";
        }, emailFields[0].textProperty(), emailFields[1].textProperty(), emailFields[2].textProperty());

        // Bind per la label
        errorEmail.textProperty().bind(emailBinding);
    }
    
     /**
     * @brief Effettua un binding tra il bottone di aggiunta del contatto o 
     *  salvataggio delle modifiche e tutte le etichette di errore.
     * 
     * @param[in] emailFields vettore dei TextField delle email.
     */
    protected void setupSaveButtonBinding(Button button) {
        // Creazione del BooleanBinding che verifica se tutte e tre le label sono visibili
        BooleanBinding addBinding = Bindings.createBooleanBinding(() -> {
            return errorName.getText().equals("") && errorNumber.getText().equals("") && errorEmail.getText().equals("");
        }, errorName.textProperty(), errorNumber.textProperty(), errorEmail.textProperty());

        button.disableProperty().bind(addBinding.not());   
    }
    
    /**
     * @brief Inizializza i ComboBox con i prefissi telefonici.
     * 
     */
    protected void setupPrefixMenu() {
        prefixMenu1.setItems(FXCollections.observableArrayList(PhonePrefix.values()));
        prefixMenu1.getSelectionModel().selectFirst();
        prefixMenu2.setItems(FXCollections.observableArrayList(PhonePrefix.values()));
        prefixMenu2.getSelectionModel().selectFirst();
        prefixMenu3.setItems(FXCollections.observableArrayList(PhonePrefix.values()));
        prefixMenu3.getSelectionModel().selectFirst();
    }
    
    /**
     * @brief Metodo astratto che va implementato nella sottoclasse in base alla funzionalità richiesta.
     * 
     * @param[in] event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento vista.
     */
    @FXML
    abstract void executeLeftTask(ActionEvent event) throws IOException;

    /**
     * @brief Metodo astratto che va implementato nella sottoclasse in base alla funzionalità richiesta.
     * 
     * @param[in] event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento vista.
     */
    @FXML
    abstract void executeRightTask(ActionEvent event) throws IOException;
}
