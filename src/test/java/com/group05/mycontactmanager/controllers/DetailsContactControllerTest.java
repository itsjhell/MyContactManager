/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import com.group05.mycontactmanager.models.PhonePrefix;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

/**
 *
 * @author group05
 */
public class DetailsContactControllerTest extends ApplicationTest {
    
    private ObservableList<Contact> contactList;
    private Contact contact;
    private Stage stage;
    private FxRobot robot = new FxRobot();
    private SplitPane splitPane;
       
    @Override
    public void start(Stage stage) throws Exception {
        contactList = FXCollections.observableArrayList(new ArrayList());
        this.splitPane = new SplitPane();
        splitPane.getItems().add(new VBox()); // simula la Vbox contenente la TableView, che viene messa a sinistra
        
        List<PhoneNumber> numbers = new ArrayList<>();
        numbers.add(new PhoneNumber(PhonePrefix.ITALY, "1234567890"));
        List<String> emails = new ArrayList<>();
        emails.add("abc@gmail.com");
        emails.add("efg@yahoo.it");
        contact = new Contact("Mario", "Rossi", numbers, emails, "", "Note per Mario Rossi");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("DetailsContactView.fxml"));
        fxmlLoader.setControllerFactory(param -> new DetailsContactController(splitPane, contact, contactList));
        splitPane.getItems().add(fxmlLoader.load());
        this.stage = stage;
        this.stage.setScene(new Scene(splitPane));
        this.stage.show();
    }
    
    @BeforeEach
    public void setUp() {
        this.stage.setWidth(600);
        this.stage.setHeight(650);
      }

    @Test
    public void testCheckName() {
        String expectedName = contact.getName();
        assertEquals(expectedName, ((TextField) lookup("#nameField").query()).getText(), "Il nome non corrisponde");
    }
    
    @Test
    public void testCheckSurame() {
        String expectedName = contact.getSurname();
        assertEquals(expectedName, ((TextField) lookup("#surnameField").query()).getText(), "Il cognome non corrisponde");
    }
    
    @Test
    public void testCheckNumbers() {
        PhoneNumber phone = contact.getNumbers().get(0);
        String expectedPrefix = phone.getPrefix().toString();
        String expectedNumber = phone.getNumber();
        assertEquals(expectedPrefix, ((ComboBox) lookup("#prefixMenu1").query()).getValue().toString(), "Il prefisso non corrisponde");
        assertEquals(expectedNumber, ((TextField) lookup("#phoneNumber1").query()).getText(), "Il numero non corrisponde");
    }
    
    @Test
    public void testCheckEmails() {
        String expectedEmail1 = contact.getEmailAddresses().get(0);
        String expectedEmail2 = contact.getEmailAddresses().get(1);
        assertEquals(expectedEmail1, ((TextField) lookup("#emailAddress1").query()).getText().toString(), "La prima email non corrisponde");
        assertEquals(expectedEmail2, ((TextField) lookup("#emailAddress2").query()).getText(), "La seconda email non corrisponde");
    }
    
    @Test
    public void testCheckNotes() {
        String expectedNotes = contact.getNotes();
        assertEquals(expectedNotes, ((TextArea) lookup("#notesArea").query()).getText(), "Le note non corrispondono");
    }
    
    @Test
    public void testRemove() {
        int initialSize = 0;
        assertEquals(initialSize, contactList.size(), "La lista non dovrebbe contenere contatti");
        contactList.add(contact);
        ObjectProperty<Contact> contactProperty = new SimpleObjectProperty();
        contactProperty.set(contact);
    
        int addedSize = 1;
        assertEquals(addedSize, contactList.size(), "La lista dovrebbe contenere 1 contatto");
      
        robot.clickOn("#deleteButton");
        robot.clickOn("Conferma");
        assertEquals(initialSize, contactList.size(), "La lista non dovrebbe contenere contatti");
    }
    
    @Test
    public void testLoadEdit() {
        robot.clickOn("#editButton");
        Button editButton = lookup("#saveEditsButton").query();
        assertNotNull(editButton, "Il bottone 'Salva modifiche' non è stato trovato");
        assertTrue(editButton.isVisible(), "Il bottone 'Salva modifiche' non è visibile");
    }
}
