package view;

import component.ActivityCardPagination;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ExtraCurriculumInfo extends VBox {
    public ExtraCurriculumInfo(String extraCurriculumName) {
        setSpacing(10);

        Label titleLabel = new Label("Details for " + extraCurriculumName);
        titleLabel.setStyle("-fx-font-size: 24;");

        Button backButton = new Button("Go Back");
        backButton.setOnAction(event -> getScene().setRoot(new ActivityCardPagination())); // Assuming Dashboard is your main layout class

        getChildren().addAll(titleLabel, backButton);
    }
}
