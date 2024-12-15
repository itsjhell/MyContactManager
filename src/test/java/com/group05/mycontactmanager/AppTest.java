package com.group05.mycontactmanager;

import com.group05.mycontactmanager.App;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author group05
 */
public class AppTest extends ApplicationTest {

    private Stage stage;
    FxRobot robot = new FxRobot();
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        new App().start(stage);
    }

    @Test
    void testAppLaunch() {
        assertNotNull(stage);
    }
    
    @Test
    public void testSceneMinSize() {
        assert(stage.getMinWidth() == 1300);
        assert(stage.getMinHeight() == 800);
    }
    
    @Test
    public void testAppTile() {
        assertEquals(stage.getTitle(),"MyContactManager");
    }
    
}