package view;

import component.ActivityCard;
import component.ActivityCardPagination;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ModuleInfo extends  VBox {
    public ModuleInfo(String activityName) {
        setSpacing(10);

        Label titleLabel = new Label("Details for " + activityName);
        titleLabel.setStyle("-fx-font-size: 24;");

        Button backButton = new Button("Go Back");
        backButton.setOnAction(event -> getScene().setRoot(new ActivityCardPagination())); // Assuming Dashboard is your main layout class

        getChildren().addAll(titleLabel, backButton);
    }
}
