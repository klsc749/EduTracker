package view;

import component.ActivityCardPagination;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ActivityService;


public class DashBoardView extends Application {

    private final ActivityService activityService = new ActivityService();

    @Override
    public void start(Stage primaryStage){

        ActivityCardPagination activityCardPagination = new ActivityCardPagination();

        VBox root = new VBox();
        root.getChildren().addAll(activityCardPagination);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My JavaFX Dashboard");
        primaryStage.show();
    }

}

