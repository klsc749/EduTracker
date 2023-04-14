package view;

import component.ActivityCard;
import component.ActivityCardPagination;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ModuleInfo extends  VBox {
    private Button backButton;
    private EventHandler<ActionEvent> backButtonClickEvent;


    public ModuleInfo(String activityName) {
        setSpacing(10);

        Label titleLabel = new Label("Details for " + activityName);
        titleLabel.setStyle("-fx-font-size: 24;");

        backButton = new Button("Go Back");
        // Assuming Dashboard is your main layout class
        getChildren().addAll(titleLabel, backButton);
    }

    public void setOnBackButtonClick(EventHandler<ActionEvent> eventHandler) {
        backButtonClickEvent = eventHandler;
        backButton.setOnAction(backButtonClickEvent);
    }
}
