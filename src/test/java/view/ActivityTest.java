package view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;

import component.activityCard.ActivityCard;
import component.activityCard.addActivityCard.AddActivityCard;
import javafx.scene.control.ComboBox;
import model.Activity;



@ExtendWith(ApplicationExtension.class)
public class ActivityTest {
    Home home;
    private String name = "TestActivity";

    @Start
    public void start (javafx.stage.Stage stage) throws Exception {
        home = new Home();
        home.start(stage);
    }

    @Test
    @Order(1)
    public void testAddActivity(FxRobot robot){
        AddActivityCard addActivityCard = robot.lookup("#addActivityCard").queryAs(AddActivityCard.class);
        robot.clickOn(addActivityCard);
        ComboBox<Activity.ActivityType> activityTypeComboBox = robot.lookup("#activityTypeComboBox").queryAs(ComboBox.class);
        activityTypeComboBox.getSelectionModel().select(Activity.ActivityType.CLASS);
        robot.clickOn("#nameTextField").write("TestActivity");
        robot.clickOn("#degreeTextField").write("2");
        robot.clickOn("#creditTextField").write("3");
        robot.clickOn("#startDatePicker").write("5/12/2020");
        robot.clickOn("#endDatePicker").write("5/15/2020");
        robot.clickOn("#submitButton");
    }

    @Test
    @Order(2)
    public void testModifyAndDelete(FxRobot robot){
        robot.clickOn("#TestActivity");

        //modify
        robot.clickOn("#markTableNameTextField").write("Class test1");
        robot.clickOn("#markTableMarkTextField").write("90");
        robot.clickOn("#markTableProportionTextField").write("0.2");
        robot.clickOn("#markTableAddButton");

        robot.clickOn("#markTableNameTextField").write("Class test2");
        robot.clickOn("#markTableMarkTextField").write("93");
        robot.clickOn("#markTableProportionTextField").write("0.2");
        robot.clickOn("#markTableAddButton");

        robot.clickOn("#markTableNameTextField").write("Final");
        robot.clickOn("#markTableMarkTextField").write("97");
        robot.clickOn("#markTableProportionTextField").write("0.6");
        robot.clickOn("#markTableAddButton");

        robot.clickOn("#markTableSaveButton");

        //delete
        robot.clickOn("#deleteActivityButton");
    }

}
