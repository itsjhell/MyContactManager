/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import com.group05.mycontactmanager.models.Contact;
import com.group05.mycontactmanager.models.PhoneNumber;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author jhell
 */
public class AddContactControllerTest extends ApplicationTest {
    
    protected ObjectProperty<Contact> contactProperty;
    protected ObservableList<Contact> contactList;
    
    protected List<PhoneNumber> numbers;
    
    protected List<String> emailAddresses;
       
    private Stage stage;
    FxRobot robot = new FxRobot();
    private AddContactController controller;
       
    @Override
    public void start(Stage stage) throws Exception {
        // Launch the main application
        contactList = FXCollections.observableArrayList(new ArrayList());
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddContactView.fxml"));
        fxmlLoader.setControllerFactory(param -> new AddContactController(new SplitPane(), new Contact("", "", null, null, "", ""), contactList)); // Usa una fabbrica per creare il controller
        Parent root = fxmlLoader.load(); 
        stage.setScene(new Scene(root, 600, 650));
        stage.show();
    }

    @Test
    public void testName() {
        robot.clickOn("#nameField");
        robot.write("Mario");
        assertEquals("", ((Label) lookup("#errorName").query()).getText(), "La label non è vuota");
    }

    @Test
    public void testSurname() {
        robot.clickOn("#surnameField");
        robot.write("Rossi");
        assertEquals("", ((Label) lookup("#errorName").query()).getText(), "La label non è vuota");
    }
        
    @Test
    public void testPhoneNumber() {
        Label errorNumber = lookup("#errorNumber").query();
        verifyThat("#adderPhoneButton", hasText("+"));
        
        robot.clickOn("#adderPhoneButton");
        robot.clickOn("#prefixMenu1");
        robot.clickOn("+39");
        robot.clickOn("#phoneNumber1");
        robot.write("3881234567");
        
        robot.clickOn("#adderPhoneButton");
        robot.clickOn("#prefixMenu2");
        robot.clickOn("+33");
        robot.clickOn("#phoneNumber2");
        robot.write("12345678910");
        robot.sleep(500);
        robot.eraseText(2);
        
        robot.clickOn("#adderPhoneButton");
        robot.clickOn("#phoneNumber3");
        robot.write("1");
        robot.sleep(500);
        assertNotEquals("La label ha testo", "", errorNumber.getText()); // verifica che il formato non è corretto
        robot.write("12");
        
        verifyThat("#adderPhoneButton", isInvisible());
        assertEquals("", errorNumber.getText(), "La label non è vuota"); // solo se tutti e tre i formati sono corretti
    }
    
    @Test
    public void testEmail() {
        Label errorEmail = lookup("#errorEmail").query();
        verifyThat("#adderEmailButton", hasText("+"));
        
        robot.clickOn("#adderEmailButton");
        robot.clickOn("#emailAddress1");
        robot.write("abc@gmail.com");
        
        robot.clickOn("#adderEmailButton");
        robot.clickOn("#emailAddress2");
        robot.write("abc.abc@gmail.it");
        
        robot.clickOn("#adderEmailButton");
        robot.clickOn("#emailAddress3");
        robot.write("ab@c@@@gmail.com");
        robot.sleep(500);
        assertNotEquals("La label ha testo", "", errorEmail.getText()); // verifica che il formato non è corretto
        robot.eraseText(14);
        robot.write("d+app@gmail.com");
        
        verifyThat("#adderEmailButton", isInvisible());
        assertEquals("", errorEmail.getText(), "La label non è vuota"); // solo se tutti e tre i formati sono corretti
    }
   
    @Test
    public void testAdd() {
        robot.clickOn("#nameField");
        robot.write("Mario");
        
        robot.clickOn("#adderPhoneButton");
        robot.clickOn("#phoneNumber1");
        robot.write("123");
        
        robot.clickOn("#adderEmailButton");
        robot.clickOn("#emailAddress1");
        robot.write("abc@gmail.com");
        
        Label errorName = lookup("#errorEmail").query();
        Label errorPhone = lookup("#errorEmail").query();
        Label errorEmail = lookup("#errorEmail").query();
        assertEquals("", errorName.getText(), "La label non è vuota"); 
        assertEquals("", errorPhone.getText(), "La label non è vuota");
        assertEquals("", errorEmail.getText(), "La label non è vuota");
        
        robot.clickOn("#addButton");
        assertEquals(1, contactList.size(), "La lista dovrebbe contenere 1 contatto");
    }
    
}
