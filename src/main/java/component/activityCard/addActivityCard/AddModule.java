package component.activityCard.addActivityCard;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import component.activityCard.ActivityCardPagination;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Mark;
import model.Module;
import service.ModuleService;

public class AddModule extends VBox {
    private TextField nameTextField;
    private TextField degreeTextField;
    private TextField creditTextField;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;

    private Button submitButton;

    private ModuleService moduleService = new ModuleService();

    private final ActivityCardPagination activityCardPagination;

    public AddModule(ActivityCardPagination activityCardPagination) {
        this.activityCardPagination = activityCardPagination;
        GridPane gridPane = initForm();
        getChildren().addAll(gridPane);
    }

    private GridPane initForm() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label nameLabel = new Label("Activity Name:");
        nameTextField = new TextField();
        nameTextField.setPromptText("Activity Name");
        nameTextField.setId("nameTextField");

        Label degreeLabel = new Label("Degree:");
        degreeTextField = new TextField();
        degreeTextField.setPromptText("Degree");
        degreeTextField.setId("degreeTextField");

        Label creditLabel = new Label("Credit:");
        creditTextField = new TextField();
        creditTextField.setPromptText("Credit");
        creditTextField.setId("creditTextField");

        Label startDateLabel = new Label("Start Date:");
        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");
        startDatePicker.setId("startDatePicker");

        Label endDateLabel = new Label("End Date:");
        endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");
        endDatePicker.setId("endDatePicker");

        submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            handleSubmitButton();
        });
        submitButton.setId("submitButton");

        gridPane.addColumn(0, nameLabel, degreeLabel, creditLabel, startDateLabel, endDateLabel);
        gridPane.addColumn(1, nameTextField, degreeTextField, creditTextField, startDatePicker, endDatePicker,
                submitButton);

        return gridPane;
    }

    private void handleSubmitButton() {
        if(nameTextField.getText().isEmpty() || degreeTextField.getText().isEmpty() || creditTextField.getText().isEmpty() || startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
            throw new RuntimeException("All fields must be filled");
        }
        if(!creditTextField.getText().matches("[0-9]+")) {
            throw new RuntimeException("Credit must be a number");
        }
        if(startDatePicker.getValue().isAfter(endDatePicker.getValue())) {
            throw new RuntimeException("Start date must be before end date");
        }

        Module module = new Module();
        module.setName(nameTextField.getText());
        module.setDegree(degreeTextField.getText());
        module.setCredit(Integer.parseInt(creditTextField.getText()));
        module.setStartDate(localDateToDate(startDatePicker.getValue()));
        module.setEndDate(localDateToDate(endDatePicker.getValue()));
        module.setMark(new Mark());

        moduleService.saveModule(module);
        activityCardPagination.refresh();

        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
