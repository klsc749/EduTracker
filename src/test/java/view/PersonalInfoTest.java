package view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import component.IconButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class PersonalInfoTest {
    Home home;

    @Start
    public void start (Stage stage) throws Exception {
        home = new Home();
        home.start(stage);
    }
    @Test
    public void testPersonalInfo(FxRobot robot){
        enterPersonalPage(robot);
        modifyEmail(robot);
        addReward(robot);
        modifyPS(robot);
    }

    private void enterPersonalPage(FxRobot robot){
        IconButton button2 = robot.lookup("#button2").queryAs(IconButton.class);
        robot.clickOn(button2);
    }

    private void modifyEmail(FxRobot robot){
        robot.clickOn("#changeEmailButton");
        robot.clickOn("#personalEmailField").write("test@test.com");
        robot.clickOn("OK");
        robot.clickOn("#changeEmailButton");
        robot.clickOn("#personalEmailField").write("12346789@qmul.com");
        robot.clickOn("OK");
    }

    private void addReward(FxRobot robot){
        robot.clickOn("#changeAwardsButton");
        robot.clickOn("#timeField").write("2022-5-14");
        robot.clickOn("#contentField").write("test4");
        robot.clickOn("OK");
    }

    private void modifyPS(FxRobot robot){

        robot.clickOn("#changePersonalStatementButton");
        TextArea textArea = robot.lookup("#personalStatementTextArea").queryAs(TextArea.class);
        String originText = textArea.getText();
        robot.clickOn(textArea).write("This the test personal statement");
        robot.clickOn("Confirm");
        robot.clickOn("#changePersonalStatementButton");
        robot.clickOn("#personalStatementTextArea").write(originText);
        robot.clickOn("Confirm");
    }


}
