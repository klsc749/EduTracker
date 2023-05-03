package view;

import component.ExtraCurriculumTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.ExtraCurriculum;

public class ExtraCurriculumInfo extends BorderPane {
    private Button backButton;

    private VBox content;

    private Label titleLabel;
    private ExtraCurriculumTable extraCurriculumTable;

    private ExtraCurriculum extraCurriculum;

    public ExtraCurriculumInfo(ExtraCurriculum extraCurriculum) {
        this.extraCurriculum = extraCurriculum;
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
        titleLabel = createTitleLabel(extraCurriculum.getName());
        extraCurriculumTable = createExtraCurriculumTable();
        content.getChildren().addAll(titleLabel, extraCurriculumTable);
    }

    private Label createTitleLabel(String text) {
        Label titleLabel = new Label("Details for " + extraCurriculum.getName());
        titleLabel.setStyle("-fx-font-size: 24;");
        return titleLabel;
    }

    private ExtraCurriculumTable createExtraCurriculumTable() {
        ExtraCurriculumTable extraCurriculumTable = new ExtraCurriculumTable();
        extraCurriculumTable.setExtraCurriculum(extraCurriculum);
        return extraCurriculumTable;
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