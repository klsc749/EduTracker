package view;

import component.personalInfo.MarkTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Activity;
import model.Module;

public class ModuleInfo extends BorderPane {
    private Button backButton;

    private VBox content;

    private Label titleLabel;
    private MarkTable markTable;

    private Activity activity;

    public ModuleInfo(Activity activity) {
        this.activity = activity;
        initStyle();
        setContent();
        setContentStyle();
        backButton = createBackButton();
        setLayout();
    }

    private void initStyle() {
        //set top, right, bottom, and left margins
        setPadding(new Insets(5, 20, 10, 20));
    }

    private void setLayout(){
        setTop(backButton);
        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
        setCenter(content);
    }

    private void setContent(){
        content = new VBox();
        titleLabel = createTitleLabel(activity.getName());
        markTable = createMarkTable();
        content.getChildren().addAll(titleLabel, markTable);
    }

    private Label createTitleLabel(String text) {
        Label titleLabel = new Label("Details for " + activity.getName());
        titleLabel.setStyle("-fx-font-size: 24;");
        return titleLabel;
    }

    private MarkTable createMarkTable() {
        if(!(activity instanceof Module)) {
            return null;
        }
        MarkTable markTable = new MarkTable((Module)activity);
        return markTable;
    }

    private Button createBackButton() {
        Button backButton = new Button("Go Back");

        // Style the button
        backButton.setStyle("-fx-font-size: 14; -fx-padding: 8 16 8 16; -fx-background-color: #f0f0f0; -fx-border-color: #aaaaaa; -fx-border-radius: 4; -fx-background-radius: 4; -fx-text-fill: #333333; -fx-cursor: hand;");

        // Add hover effect
        backButton.setOnMouseEntered(event -> backButton.setStyle("-fx-font-size: 14; -fx-padding: 8 16 8 16; -fx-background-color: #e0e0e0; -fx-border-color: #aaaaaa; -fx-border-radius: 4; -fx-background-radius: 4; -fx-text-fill: #333333; -fx-cursor: hand;"));
        backButton.setOnMouseExited(event -> backButton.setStyle("-fx-font-size: 14; -fx-padding: 8 16 8 16; -fx-background-color: #f0f0f0; -fx-border-color: #aaaaaa; -fx-border-radius: 4; -fx-background-radius: 4; -fx-text-fill: #333333; -fx-cursor: hand;"));

        return backButton;
    }


    private void setContentStyle(){
        content.setSpacing(10);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(10, 0, 0, 0));
    }

    public void setOnBackButtonClick(EventHandler<ActionEvent> eventHandler) {
        backButton.setOnAction(eventHandler);
    }
}
