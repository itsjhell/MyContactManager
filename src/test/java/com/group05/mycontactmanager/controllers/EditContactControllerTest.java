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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
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
public class EditContactControllerTest extends ApplicationTest {
    
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditContactView.fxml"));
        fxmlLoader.setControllerFactory(param -> new EditContactController(splitPane, contact, contactList));
        splitPane.getItems().add(fxmlLoader.load());
        this.stage = stage;
        this.stage.setScene(new Scene(splitPane));
        this.stage.show();
    }
    
    @BeforeEach
    public void setUp() {
        this.stage.setWidth(600);
        this.stage.setHeight(650);
        contactList.add(contact);
    }

    @Test
    public void testEditName() {
        String oldName = contactList.get(0).getName();
        robot.clickOn("#nameField");
        robot.eraseText(10);
        robot.write("Luigi");
        robot.clickOn("#saveEditsButton");
        String newName = contactList.get(0).getName();
        assertNotEquals(oldName, newName, "Il nome non è cambiato");
    }
    
    @Test
    public void testEditSurame() {
        String oldSurame = contactList.get(0).getSurname();
        robot.clickOn("#surnameField");
        robot.eraseText(10);
        robot.write("Verdi");
        robot.clickOn("#saveEditsButton");
        String newSurame = contactList.get(0).getSurname();
        assertNotEquals(oldSurame, newSurame, "Il cognome non è cambiato");
    }
    
    @Test
    public void testEditNumbers() {
        String oldPhone = contactList.get(0).getFirstNumber();
        robot.clickOn("#prefixMenu1");
        robot.clickOn("+33");
        robot.clickOn("#phoneNumber1");
        robot.eraseText(1);
        robot.clickOn("#saveEditsButton");
        String newPhone = contactList.get(0).getFirstNumber();
        assertNotEquals(oldPhone, newPhone, "Il numero non è cambiato");
    }
    
    @Test
    public void testEditEmails() {
        String oldEmail = contactList.get(0).getEmailAddresses().get(0);
        robot.clickOn("#emailAddress1");
        robot.write("zzz");
        robot.clickOn("#saveEditsButton");
        String newEmail = contactList.get(0).getEmailAddresses().get(0);
        assertNotEquals(oldEmail, newEmail, "L'email non è cambiata");
    }
    
    @Test
    public void testCheckNotes() {
        String oldNotes = contactList.get(0).getNotes();
        robot.clickOn("#notesArea");
        robot.write(" modifica");
        robot.clickOn("#saveEditsButton");
        String newNotes = contactList.get(0).getNotes();
        assertNotEquals(oldNotes, newNotes, "Le note non sono cambiate");
    }
    
    @Test
    public void testCancel() {
        String oldName = contactList.get(0).getName();
        robot.clickOn("#nameField");
        robot.eraseText(10);
        robot.write("Luigi");
        robot.clickOn("#cancelEditsButton");
        String newName = contactList.get(0).getName();
        assertEquals(oldName, newName, "Il nome è cambiato");
    }
}
