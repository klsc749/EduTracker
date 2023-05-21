package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The PersonHome class represents the main entry point of the application.
 * It creates and displays the PersonInfo component.
 */
public class PersonHome extends Application {

    /**
     * Starts the JavaFX application.
     *
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        PersonInfo personInfo = new PersonInfo();

        BorderPane root = new BorderPane();
        root.setCenter(personInfo);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("StudentInfo");
        primaryStage.show();
    }
}
