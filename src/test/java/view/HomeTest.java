package view;

import component.IconButton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;

@ExtendWith(ApplicationExtension.class)
public class HomeTest {
    private Home home;

    @Start
    public void start (javafx.stage.Stage stage) throws Exception {
        home = new Home();
        home.start(stage);
    }

    @Test
    public void testLeftMenu(FxRobot robot){
        IconButton button1 = robot.lookup("#button1").queryAs(IconButton.class);
        IconButton button2 = robot.lookup("#button2").queryAs(IconButton.class);
        IconButton button3 = robot.lookup("#button3").queryAs(IconButton.class);

        robot.clickOn(button1);
        robot.clickOn(button2);
        robot.clickOn(button3);
    }

    @Stop
    public void stop() throws Exception{
        FxToolkit.hideStage();
    }
}
