package view;

import component.ActivityCardPagination;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import service.ActivityService;


public class DashBoardView extends Application {

    private final ActivityService activityService = new ActivityService();

    @Override
    public void start(Stage primaryStage){

        FlowPane root = new FlowPane();




        root.getChildren().addAll(new ActivityCardPagination());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My JavaFX Dashboard");
        primaryStage.show();
    }

}

