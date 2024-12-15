/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group05.mycontactmanager.controllers;

import com.group05.mycontactmanager.App;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author group05
 */
public class MainTableViewControllerTest extends ApplicationTest {

    private Stage stage;
    FxRobot robot = new FxRobot();
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        new App().start(stage); // App accede a MainTableView.fxml
    }
    
    @BeforeEach
    public void startUp() {
        stage.setWidth(1300); 
        stage.setHeight(800); 
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
}
