package view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
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
    public void testAddAndModifyAndDeleteActivity(FxRobot robot){
        testAddActivity(robot);
        testModifyAndDelete(robot);
    }

    public void testAddActivity(FxRobot robot){
        AddActivityCard addActivityCard = robot.lookup("#addActivityCard").queryAs(AddActivityCard.class);
        robot.clickOn(addActivityCard);
        ComboBox<Activity.ActivityType> activityTypeComboBox = robot.lookup("#activityTypeComboBox").queryAs(ComboBox.class);
        activityTypeComboBox.getSelectionModel().select(Activity.ActivityType.CLASS);
        robot.clickOn("#nameTextField").write(name);
        robot.clickOn("#degreeTextField").write("2");
        robot.clickOn("#creditTextField").write("3");
        robot.clickOn("#startDatePicker").write("5/12/2020");
        robot.clickOn("#endDatePicker").write("5/15/2020");
        robot.clickOn("#submitButton");
    }

    
    public void testModifyAndDelete(FxRobot robot){
        robot.clickOn("#" + name);

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
