package component.activityCard.addActivityCard;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import component.activityCard.ActivityCardPagination;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ExtraCurriculum;
import service.ExtraCurriculumService;

public class AddExtra extends VBox {
    private TextField nameTextField;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Button submitButton;

    private ExtraCurriculumService extraCurriculumService = new ExtraCurriculumService();
    private final ActivityCardPagination activityCardPagination;

    public AddExtra(ActivityCardPagination activityCardPagination) {
        this.activityCardPagination = activityCardPagination;
        getChildren().addAll(initForm());
    }

    private GridPane initForm() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label nameLabel = new Label("Extra curriculum Name:");
        nameTextField = new TextField();
        nameTextField.setPromptText("Extra curriculum Name");

        Label startDateLabel = new Label("Start Date:");
        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");

        Label endDateLabel = new Label("End Date:");
        endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");

        submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            handleSubmitButton();
        });

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(startDateLabel, 0, 1);
        gridPane.add(startDatePicker, 1, 1);
        gridPane.add(endDateLabel, 0, 2);
        gridPane.add(endDatePicker, 1, 2);

        gridPane.add(submitButton, 1, 3);


        return gridPane;
    }

    private void handleSubmitButton() {
        if(nameTextField.getText().isEmpty() || startDatePicker.getValue() == null || endDatePicker.getValue() == null){
            throw new RuntimeException("Please fill in all fields");
        }

        if(startDatePicker.getValue().isAfter(endDatePicker.getValue())){
            throw new  RuntimeException("Start date cannot be after end date");
        }

        ExtraCurriculum extraCurriculum = new ExtraCurriculum();
        extraCurriculum.setName(nameTextField.getText());
        extraCurriculum.setStartDate(localDateToDate(startDatePicker.getValue()));
        extraCurriculum.setEndDate(localDateToDate(endDatePicker.getValue()));
        
        extraCurriculumService.saveExtraCurriculum(extraCurriculum);
        activityCardPagination.refresh();
        
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
