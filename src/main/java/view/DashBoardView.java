package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Activity;
import service.ActivityService;
import view.component.DashbordItem.DashBoardItem;

import java.util.List;


public class DashBoardView extends Application {

    private ActivityService activityService = new ActivityService();

    @Override
    public void start(Stage primaryStage) throws Exception {

        List<Activity> activities = activityService.getAllActivities();

        // Create a FlowPane with a horizontal layout and spacing of 10 pixels
        FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 10, 10);
        flowPane.setPadding(new Insets(10));

        // Add the DashBoardItem components to the FlowPane
        for (Activity activity : activities) {
            DashBoardItem item = new DashBoardItem(activity);
            flowPane.getChildren().add(item);
        }

        // Create a Scene with the FlowPane as the root node
        Scene scene = new Scene(flowPane);
        primaryStage.setTitle("DashBoard");
        primaryStage.setScene(scene);
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.show();
    }

}

