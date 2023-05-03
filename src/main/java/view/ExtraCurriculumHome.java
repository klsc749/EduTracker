package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ExtraCurriculum;


//到时候把这个嵌到Home里就可以
public class ExtraCurriculumHome extends Application {

    @Override
    public void start(Stage primaryStage) {
        ExtraCurriculum extraCurriculum = new ExtraCurriculum();
        ExtraCurriculumInfo extraCurriculumInfo = new ExtraCurriculumInfo(extraCurriculum);

        BorderPane root = new BorderPane();
        root.setCenter(extraCurriculumInfo);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("额外项目或研究");
        primaryStage.show();
    }


}