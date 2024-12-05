package com.group05.mycontactmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author group05
 */
public class Controller {

    @FXML
    private Label contactListName;
    @FXML
    private Button addButton;
    @FXML
    private Button selectButton;
    @FXML
    private ComboBox<?> searchParameter;
    @FXML
    private TextField searchField;
    @FXML
    private Label taskLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private Label errorName;
    @FXML
    private ImageView contactImage;
    @FXML
    private Button imageButton;
    @FXML
    private ComboBox<?> prefixMenu1;
    @FXML
    private TextField phoneNumber1;
    @FXML
    private Label errorNumber;
    @FXML
    private Button adderPhoneButton;
    @FXML
    private TextField phoneNumber2;
    @FXML
    private ComboBox<?> prefixMenu2;
    @FXML
    private TextField phoneNumber3;
    @FXML
    private ComboBox<?> prefixMenu3;
    @FXML
    private TextField emailAddress1;
    @FXML
    private Label erorrEmail;
    @FXML
    private TextField emailAddress3;
    @FXML
    private TextField emailAddress2;
    @FXML
    private Button adderEmailButton;
    @FXML
    private TextArea notesArea;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private MenuItem saveContactListButton;
    @FXML
    private MenuItem loadContactListButton;
    @FXML
    private MenuItem importContactsButton;
    @FXML
    private MenuItem exportContactsButton;

    @FXML
    private void addContactToList(ActionEvent event) {
    }

    @FXML
    private void selectContacts(ActionEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void loadImage(ActionEvent event) {
    }

    @FXML
    private void choosePrefix(ActionEvent event) {
    }

    @FXML
    private void addPhoneNumber(ActionEvent event) {
    }

    @FXML
    private void addEmail(ActionEvent event) {
    }

    @FXML
    private void executeLeft(ActionEvent event) {
    }

    @FXML
    private void executeRight(ActionEvent event) {
    }

    @FXML
    private void saveContactLIst(ActionEvent event) {
    }

    @FXML
    private void loadContactList(ActionEvent event) {
    }

    @FXML
    private void importContacts(ActionEvent event) {
    }

    @FXML
    private void exportContacts(ActionEvent event) {
    }
    
}
