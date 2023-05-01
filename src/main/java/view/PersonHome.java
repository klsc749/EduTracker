package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//到时候把这个嵌到Home里就可以
public class PersonHome extends Application {

    @Override
    public void start(Stage primaryStage) {
        PersonInfo personInfo = new PersonInfo();

        BorderPane root = new BorderPane();
        root.setCenter(personInfo);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("学生个人信息展示");
        primaryStage.show();
    }


}
