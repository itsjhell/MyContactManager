package com.group05.mycontactmanager;

import com.group05.mycontactmanager.App;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class AppTest extends ApplicationTest {

    private Stage stage;
    FxRobot robot = new FxRobot();
    
    @Override
    public void start(Stage stage) throws Exception {
        // Launch the main application
        this.stage = stage;
        new App().start(stage);
    }

    @Test
    void testAppLaunch() {
        // Assert that the primary stage is shown
        assertNotNull(stage);
    }
    
    @Test
    public void testSceneMinSize() {
        // Verifica che le dimensioni minime della finestra siano correttamente impostate
        assert(stage.getMinWidth() == 1300);
        assert(stage.getMinHeight() == 800);
    }
    
    @Test
    public void testAppTile() {
        // Verifica che il titolo della finestra sia corretto
        assertEquals(stage.getTitle(),"MyContactManager");
    }
    
    @Test
    public void testSelectionButton() {
        verifyThat("#selectButton", hasText("Seleziona"));
        robot.clickOn("#selectButton");
    }
    
    @Test
    public void testAddButton() {
        verifyThat("#addButton", hasText("Aggiungi"));
        robot.clickOn("#addButton");
        

        // Scrivi nel campo nameField
        robot.clickOn("#nameField");
        write("Mario");
        
        robot.clickOn("#surnameField");
        write("Rossi");
        
        assertEquals("Mario", ((TextField) lookup("#nameField").query()).getText());
        assertEquals("Rossi", ((TextField) lookup("#surnameField").query()).getText());
    }
    
/*
    @ParameterizedTest
    void testInitialUI(FxRobot robot) {
        // Verify the initial state of the UI
        assertTrue(robot.lookup("#mainTableView").tryQuery().isPresent());
    }

    @Test
    void testAddContact(FxRobot robot) {
        // Simulate clicking the Add Contact button
        robot.clickOn("#addContactButton");

        // Simulate input in the Add Contact form
        robot.clickOn("#nameField").write("John Doe");
        robot.clickOn("#phoneField").write("1234567890");
        robot.clickOn("#saveButton");

        // Verify the contact was added
        assertTrue(robot.lookup("#mainTableView").queryListView().getItems().stream()
                .anyMatch(item -> item.toString().contains("John Doe")));
    }

    @Test
    void testEditContact(FxRobot robot) {
        // Simulate selecting a contact and clicking Edit
        robot.clickOn("#mainTableView").clickOn("John Doe");
        robot.clickOn("#editContactButton");

        // Edit contact details
        robot.clickOn("#nameField").eraseText(8).write("John Smith");
        robot.clickOn("#saveButton");

        // Verify the contact details were updated
        assertTrue(robot.lookup("#mainTableView").queryListView().getItems().stream()
                .anyMatch(item -> item.toString().contains("John Smith")));
    }

    @Test
    void testDeleteContact(FxRobot robot) {
        // Simulate selecting a contact and clicking Delete
        robot.clickOn("#mainTableView").clickOn("John Smith");
        robot.clickOn("#deleteContactButton");

        // Verify the contact was removed
        assertFalse(robot.lookup("#mainTableView").queryListView().getItems().stream()
                .anyMatch(item -> item.toString().contains("John Smith")));
    } */
}