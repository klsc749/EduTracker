package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import service.ActivityService;
import component.ActivityCard;


public class DashBoardView extends Application {

    private ActivityService activityService = new ActivityService();

    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane root = new FlowPane();
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(20));

        // Add your ActivityCard components here
        ActivityCard card1 = new ActivityCard("Activity 1", "Semester 1", "image/icon.png", 0.75);
        ActivityCard card2 = new ActivityCard("Activity 2", "Semester 2", "image/icon.png", 1);

        root.getChildren().addAll(card1, card2);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My JavaFX Dashboard");
        primaryStage.show();
    }

}

