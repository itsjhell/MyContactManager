package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.ContactManager;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.utilities.ContactGenerator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * @file MainTableViewController.java
 * @brief Controller principale della tabella dei contatti.
 * 
 * Questa classe gestisce l'interfaccia principale, mostrando la lista dei contatti in una tabella.
 * Fornisce azioni per aggiungere un contatto, caricare/salvare da file, importare/esportare,
 * e funzioni di ricerca.
 * 
 * @author group05
 * @date Dicembre 08,2024
 * @version 1.0
 */
public class MainTableViewController implements Initializable {

    @FXML
    private MenuItem saveContactListButton;
    @FXML
    private MenuItem loadContactListButton;
    @FXML
    private MenuItem importContactsButton;
    @FXML
    private MenuItem exportContactsButton;
    @FXML
    private SplitPane splitPane;
    @FXML
    private TextField contactListName;
    @FXML
    private Button addButton;
    @FXML
    private Button selectButton;
    @FXML
    private Button cancelSelectionButton;
    @FXML
    private Button deleteAllButton;
    @FXML
    private ComboBox<String> searchParameter;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<Contact, Image> iconClm;
    @FXML
    private TableColumn<Contact, String> surnameClm;
    @FXML
    private TableColumn<Contact, String> nameClm;
    @FXML
    private TableColumn<Contact, String> phoneClm;
    @FXML
    private TableColumn<Contact, CheckBox> checkClm;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private TableView<Contact> selectedTable;
    @FXML
    private TableColumn<Contact, String> selectedSurnameClm;
    @FXML
    private TableColumn<Contact, String> selectedNameClm;
    
    
    private Contact contact;
    
    private ObservableList<Contact> contactList;
    
    private ContactManager contactManager;
    
    private FilteredList<Contact> selectedContacts;

    public MainTableViewController() {
        // Creazione di un'istanza di ContactManager
        contactManager = new ContactManager("Nuova rubrica");
        contactList = FXCollections.observableArrayList(contactManager.getContactList());
        selectedContacts = new FilteredList<>(contactList);
        selectedContacts.setPredicate(contact1 -> {
                    return contact1.isSelected().get();
                });
    }

    /**
     * @brief Inizializza il controller.
     * @param[in] url L'URL per percorsi relativi.
     * @param[in] rb Il ResourceBundle per l'internazionalizzazione.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            //rifinimento Layout
        splitPane.setDividerPositions(0.5);
        splitPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> event.consume());
        splitPane.getItems().remove(rightPane); // rimuove tabella selezionati
        
        // Inserimento dei dati in tabella
            //PLACE HOLDER PER TABELLE SE VUOTE
        contactTable.setPlaceholder(new Label("Nessun contatto in rubrica"));
        selectedTable.setPlaceholder(new Label("Nessun contatto selezionato"));
            //ICONA
        iconClm.setCellValueFactory( contactProperty  -> {
            File file = new File(contactProperty.getValue().getImagePath());
            Image im;
            if (file.exists())
                im = new Image(contactProperty.getValue().getImagePath(), 25, 25, false, false);
            else
                im = new Image("images/default.png", 25, 25, false, false);
            ImageView imView = new ImageView();
            imView.setImage(im);
            return new SimpleObjectProperty(imView); 
        });
            //NOMINATIVI
        surnameClm.setCellValueFactory(new PropertyValueFactory("surname"));
        nameClm.setCellValueFactory(new PropertyValueFactory("name"));
            //NUMERI
        phoneClm.setCellValueFactory(new PropertyValueFactory("firstNumber"));
            //CHECK
        multipleContactSelection();
        
        
            //TEST RUBRICA
        contactList.setAll(ContactGenerator.generateRandomContacts(15));
            //CONFIGURAZIONE DELLA TABLE LIST
        setupTableList();
            
            //SELEZIONE DI UN CONTATTO
        contactTable.setOnMouseClicked( event -> {
            Contact selectedContact = contactTable.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                contact = selectedContact;
                try {
                    //Caricamento dell'interfaccia Dettaglio contatto
                    loadDetailsContact(splitPane, contact, contactList);
                    //System.out.println(contact.isSelected()); //DEBUG
                } catch (IOException ex) {
                    System.out.println(ex.getMessage()); //DEBUG
                }
            }
        });
        
        //contactListName.textProperty().bindBidirectional( new SimpleStringProperty(contactManager.getName()));
        contactListName.setText(contactManager.getName());
        
        searchParameter.getItems().addAll("Cognome", "Nome", "Cognome e nome", "Telefono", "E-mail");
        searchParameter.setValue("Cognome");
    }    
    
    private void setupTableList() {
        // creo una lista filtrata
        FilteredList<Contact> filteredContactList = new FilteredList<>(contactList);
        // scelgo il criterio di filtraggio
        filteredContactList.predicateProperty().bind(
            Bindings.createObjectBinding(() ->
                contact -> {
                    String filter = searchField.getText();
                    if (filter == null || filter.isEmpty()) {
                        return true; // non filtra niente
                    }
                    String lowerCaseFilter = filter.toLowerCase();
                    int selectedFilter = searchParameter.getSelectionModel().getSelectedIndex();
                    switch (selectedFilter) {
                        case 0: // filtro per cognome
                            return contact.getSurname().toLowerCase().contains(lowerCaseFilter);
                        case 1: // filtro per nome
                            return contact.getName().toLowerCase().contains(lowerCaseFilter);
                        case 2: // filtro per cognome e nome separati da spazio
                                String[] filterParts = lowerCaseFilter.split("\\s+"); 

                                // se ci sono due parti nel filtro
                                if (filterParts.length == 2) {
                                    String firstPart = filterParts[0];
                                    String secondPart = filterParts[1];
                                    // controlla se una parte corrisponde al cognome e l'altra al nome o viceversa
                                    return (contact.getSurname().toLowerCase().contains(firstPart) && contact.getName().toLowerCase().contains(secondPart)) ||
                                           (contact.getSurname().toLowerCase().contains(secondPart) && contact.getName().toLowerCase().contains(firstPart));
                                } else {
                                    // se il filtro è solo cognome o nome
                                    return contact.getSurname().toLowerCase().contains(lowerCaseFilter) ||
                                           contact.getName().toLowerCase().contains(lowerCaseFilter);
                                }
                        case 3: // filtro per numero di telefono
                            if (contact.getNumbers() == null) return false;
                            for (PhoneNumber number : contact.getNumbers()) {
                                if (number.toString().contains(lowerCaseFilter)) {
                                    return true; // se uno dei numeri di tutta la lista corrisponde
                                }
                            }
                            return false;
                        case 4: // filtro per email
                            if (contact.getEmailAddresses() == null) return false;
                            for (String email : contact.getEmailAddresses()) {
                                if (email.toLowerCase().contains(lowerCaseFilter)) {
                                    return true; // se una delle email della lista corrisponde
                                }
                            }
                            return false; 
                        default:
                            return true; // mostra tutti i contatti se non è selezionato nulla
                    }
                }, searchField.textProperty(), searchParameter.valueProperty()));
        
        SortedList<Contact> sortedContactList = new SortedList<>(filteredContactList, new Comparator<Contact>() { 
            @Override
            public int compare(Contact o1, Contact o2) {
                // Confronto cognome
                int cmp = o1.getSurname().compareToIgnoreCase(o2.getSurname());
                if (cmp != 0)
                    return cmp;
                // Confronto nome se i cognomi sono uguali
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        contactTable.setItems(sortedContactList); // la lista che viene visualizzata in tabella è quella filtrata ma ordinata
    }

    /**
     * @brief Salva la lista dei contatti su file.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void saveContactList(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salva rubrica...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File di Testo", "*.bin"));
        // Mostra la finestra di dialogo
        File selectedFile = fileChooser.showSaveDialog(null);
        
        //configuro la ContactManager da salvare
        ArrayList<Contact> serializableList = new ArrayList<>(contactList);
        contactManager.setContactList(serializableList);
        contactManager.setName(contactListName.getText());
        
        if (selectedFile != null) {
            contactManager.writeObject(selectedFile.getAbsolutePath());
        } else {
            showErrorAlert("Salvataggio rubrica annullato.");
        }
    }

    /**
     * @brief Carica la lista dei contatti da file.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void loadContactList(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();fileChooser.setTitle("Carica rubrica...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File di Testo", "*.bin"));
        // Mostra la finestra di dialogo
        File selectedFile = fileChooser.showOpenDialog(null);
        //String[] name = selectedFile.getAbsolutePath().split(".");
        
        if (selectedFile != null) {
            //WARNING
            String title = "Vuoi procedere?";
            String headerText = "Caricando una nuova rubrica potresti perdere quella attuale";
            String contentText = "Assicurati di aver salvato la rubrica corrente prima se non desideri perderla"; 
            if(showConfirmationAlert(title, headerText, contentText)) {
                contactManager = new ContactManager("Rubrica su cui ricarico");
                contactManager.readObject(selectedFile.getAbsolutePath());
                for(Contact c: contactManager.getContactList()) {
                    c.resetSelectedProperty();
                }
                //La nuova istanza di ContactManager va depositata
                contactList = FXCollections.observableArrayList(contactManager.getContactList());
                // Lista ordinata per cognome-nome da visualizzare nella tabella
                setupTableList();
                return;
            }
        }
        showErrorAlert("Caricamento rubrica annullato.");
     
    }

    /**
     * @brief Importa contatti da un file CSV.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void importContacts(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();fileChooser.setTitle("Importa rubrica...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File di Testo", "*.csv"));
        // Mostra la finestra di dialogo
        File selectedFile = fileChooser.showOpenDialog(null);
        //String[] name = selectedFile.getAbsolutePath().split(".");
        //COnstruisco il ContactManager
        ContactManager newContactManager = new ContactManager("prova");
        
        if (selectedFile != null) {
            //WARNING
            String title = "Vuoi procedere?";
            String headerText = "Importando una nuova rubrica potresti perdere quella attuale";
            String contentText = "Assicurati di aver salvato la rubrica corrente prima se non desideri perderla"; 
            if(showConfirmationAlert(title, headerText, contentText)) {
                contactManager.importContactsFromCSV(selectedFile.getAbsolutePath());
                contactList = FXCollections.observableArrayList(contactManager.getContactList());
                setupTableList();
                return;
            }
        }
        showErrorAlert("Importazione rubrica annullata.");
    }

    /**
     * @brief Esporta contatti in un file CSV.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void exportContacts(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();fileChooser.setTitle("Esporta rubrica...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File di Testo", "*.csv"));
        // Mostra la finestra di dialogo
        File selectedFile = fileChooser.showSaveDialog(null);
        //String[] name = selectedFile.getAbsolutePath().split(".");
        //COnstruisco il ContactManager

        contactManager.setContactList(contactList);
        
        if (selectedFile != null) {
            contactManager.exportContactsToCSV(selectedFile.getAbsolutePath());
        } else {
            showErrorAlert("Esportazione rubrica annullata.");
        }
        //Converto l'observableList in un ContactManager.
    }

    /**
     * @brief Aggiunge un nuovo contatto alla lista, caricando la vista di aggiunta.
     * @param[in] event L'ActionEvent associato.
     * @throws IOException In caso di errore nel caricamento della vista FXML.
     */
    @FXML
    private void addContactToList(ActionEvent event) throws IOException {
        loadAddContact(splitPane, contactList);
    }

    /**
     * @brief Sceglie il parametro di ricerca.
     * @param[in] event L'ActionEvent associato.
     */
    @FXML
    private void chooseSearchParameter(ActionEvent event) {
    }
    
    /**
     * @brief Ricerca contatti per cognome e nome.
     * @param[in] surname La proprietà Stringa del cognome.
     * @param[in] name La proprietà Stringa del nome.
     * @return Un'ObservableList di contatti corrispondenti che soddisfano i criteri di ricerca 
     */
    public ObservableList searchBySurnameAndName(StringProperty surname, StringProperty name) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per cognome .
     * @param[in] surname La proprietà Stringa del cognome.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchBySurname(StringProperty surname) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per nome .
     * @param[in] name La proprietà Stringa del nome.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca. 
     */
    public ObservableList searchByName(StringProperty name) {
        return null;
    }
    
    /**
     * @brief Ricerca contatti per email (non implementato).
     * @param[in] email La proprietà Stringa dell'email.
     * @return Un'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchByEmail(StringProperty email) {
        return null;
    }
  
    /**
     * @brief Ricerca contatti per numero di telefono (non implementato).
     * @param[in] phone La proprietà Stringa del numero di telefono.
     * @return UUn'ObservableList di contatti che soddisfano i criteri di ricerca.
     */
    public ObservableList searchByPhoneNumber(StringProperty phone) {
        return null;
    }
    
    private void loadDetailsContact(SplitPane splitPane, Contact contact, ObservableList<Contact> contactList) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DetailsContactView.fxml"));
        if (splitPane.getItems().size() > 1)
            splitPane.getItems().remove(1);
        fxmlLoader.setControllerFactory(param -> new DetailsContactController(splitPane, contact, contactList)); // Usa una fabbrica per creare il controller
        splitPane.getItems().add(fxmlLoader.load());
    }
    
    private void loadAddContact(SplitPane splitPane, ObservableList<Contact> contactList) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddContactView.fxml"));
        if (splitPane.getItems().size() > 1)
            splitPane.getItems().remove(1);
        fxmlLoader.setControllerFactory(param -> new AddContactController(splitPane, new Contact("", "", null, null, "", ""), contactList)); // Usa una fabbrica per creare il controller
        splitPane.getItems().add(fxmlLoader.load());
    }
    
    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Errore");
        alert.setHeaderText("Si è verificato un errore");
        alert.setContentText(errorMessage);

        alert.showAndWait();
    }
    
    private boolean showConfirmationAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.setGraphic(null);

        ButtonType okButton = new ButtonType("Conferma");
        ButtonType cancelButton = new ButtonType("Annulla");
        alert.getButtonTypes().setAll(okButton, cancelButton);

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        return result == okButton;
    }    

    private void multipleContactSelection() {
        checkClm.setCellValueFactory((contactProperty) -> {
            CheckBox cb = new CheckBox();
            ObjectProperty obj = new SimpleObjectProperty<>(cb);
            //HANDLER DELL'EVENTO: CLICK SU UNA CHECKBOX
            cb.setOnMouseClicked((MouseEvent event) -> {
                selectedContacts = new FilteredList<>(contactList);     //1. PRENDE CONTACTLIST PER INTERA
                selectedContacts.setPredicate(contact -> {              //2. FILTRA LA LISTA CON I CONTATTI SELEZIONATI
                    return contact.isSelected().get();
                });
                //CONFIGURAZIONE TABELLA SELEZIONATI
                selectedSurnameClm.setCellValueFactory(new PropertyValueFactory("surname"));
                selectedNameClm.setCellValueFactory(new PropertyValueFactory("name"));
                selectedTable.setItems(selectedContacts);
                //VA RICARICATO IL RIGHTPANE PER TENERE SOTTO CONTROLLO TUTTE LE SELEZIONI;
                if(splitPane.getItems().size()>1)
                    splitPane.getItems().remove(1);
                splitPane.getItems().add(rightPane);
            });
            cb.selectedProperty().bindBidirectional(contactProperty.getValue().isSelected());   //COLLEGAMENTO TRA VALORE DELLA CHECKBOX E PROPRIETA' selected DI CONTACT   
            return obj; //CARICA LA CHECKBOX NELLA COLONNA
        });
        checkClm.setVisible(false); //IMPOSTATA INVISIBILE (TORNERA' VISIBILE CON LA FUNZIONE SELEZIONA ATTIVA)
        
            //Avvio funzionalità seleziona
        selectButton.setOnMouseClicked((MouseEvent event) -> {
                //RENDO VISIBILI LE CHECKBOX DI OGNI CONTATTO
            checkClm.setVisible(true);
                //DISABILITO L'AVVIO DI ALTRE FUNZIONALITA' DURANTE LA SELEZIONE MULTIPLA
            addButton.setDisable(true);
            selectButton.setDisable(true);
                //CARICO L'APPOSITO PANE
            if(splitPane.getItems().size()>1)
                splitPane.getItems().remove(1);
            splitPane.getItems().add(rightPane);

                //CONFERMA DELL'OPERAZIONE DI ELIMINAZIONE
            deleteAllButton.setOnAction(e -> {
                if(selectedContacts.isEmpty() || selectedContacts==null)
                    showErrorAlert("Non hai selezionato nessun contatto");
                else{
                    if(showConfirmationAlert("Attenzione", "Stai per eliminare definitivamente questi contatti.", "Clicca 'Conferma' per procedere, 'Annulla' per rivedere la tua decisione")) {
                        checkClm.setVisible(false);
                        splitPane.getItems().remove(rightPane);
                        System.out.println(selectedContacts); //DEBUG

                        List<Contact> toRemove = new ArrayList<>();
                        Iterator<Contact> iterator = selectedContacts.iterator();
                        while (iterator.hasNext()) {
                            Contact contact = iterator.next();
                            if (contactList.contains(contact)) {
                                toRemove.add(contact);  //Aggiungi gli elementi da rimuovere
                            }
                        }
                        // Rimuovi gli elementi dalla contactList
                        contactList.removeAll(toRemove);

                        addButton.setDisable(false);
                        selectButton.setDisable(false);
                    }
                }
            });

                //ANNULLAMENTO DELL'OPERAZIONE
            cancelSelectionButton.setOnAction(e -> {
                checkClm.setVisible(false);
                splitPane.getItems().remove(rightPane);
                    System.out.println(selectedContacts); //DEBUG
                for (Contact contact : selectedContacts) {
                    if (contactList.contains(contact)) {
                        contact.isSelected().set(false);
                    }
                }
                addButton.setDisable(false);
                selectButton.setDisable(false);
            });
        });
    }
}
