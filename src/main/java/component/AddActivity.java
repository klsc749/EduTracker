package component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Activity;
import model.ExtraCurriculum;
import model.Mark;
import model.Module;
import service.ExtraCurriculumService;
import service.ModuleService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class AddActivity extends VBox {
    private final ModuleService moduleService = new ModuleService();
    private final ExtraCurriculumService extraCurriculumService = new ExtraCurriculumService();
    private ComboBox<Activity.ActivityType> activityTypeComboBox;
    private TextField activityNameTextField;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;

    private final ActivityCardPagination activityCardPagination;

    public AddActivity(ActivityCardPagination activityCardPagination) {
        setSpacing(20);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        this.activityCardPagination = activityCardPagination;

        getChildren().addAll(initHeader(), initForm(), initButton());
    }

    private Label initHeader() {
        Label nameLabel = new Label("Add Activity");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nameLabel.setStyle("-fx-text-fill: #333333;");
        return nameLabel;
    }

    private GridPane initForm() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label activityTypeLabel = new Label("Activity Type:");
        activityTypeComboBox = new ComboBox<>();
        activityTypeComboBox.getItems().addAll(Activity.ActivityType.values());
        activityTypeComboBox.getSelectionModel().select(0);

        Label activityNameLabel = new Label("Activity Name:");
        activityNameTextField = new TextField();
        activityNameTextField.setPromptText("Activity Name");

        Label startDateLabel = new Label("Start Date:");
        startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");

        Label endDateLabel = new Label("End Date:");
        endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");

        gridPane.addColumn(0, activityTypeLabel, activityNameLabel, startDateLabel, endDateLabel);
        gridPane.addColumn(1, activityTypeComboBox, activityNameTextField, startDatePicker, endDatePicker);
        GridPane.setMargin(activityTypeComboBox, new Insets(0, 0, 0, 10));

        return gridPane;
    }

    private HBox initButton() {
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> handleAddButtonClicked());
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(addButton);
        return buttonBox;
    }

    private void handleAddButtonClicked() {
        if(activityTypeComboBox.getValue() == null) {
            throw new RuntimeException("Please select an activity type");
        }
        if(activityNameTextField.getText().isEmpty()) {
            throw new RuntimeException("Please enter an activity name");
        }
        if(startDatePicker.getValue() == null) {
            throw new RuntimeException("Please select a start date");
        }
        if(endDatePicker.getValue() == null) {
            throw new RuntimeException("Please select an end date");
        }
        if(startDatePicker.getValue().isAfter(endDatePicker.getValue())) {
            throw new RuntimeException("Start date cannot be after end date");
        }


        if(activityTypeComboBox.getValue() == Activity.ActivityType.CLASS) {
            Module module = new Module();
            module.setName(activityNameTextField.getText());
            module.setStartDate(localDateToDate(startDatePicker.getValue()));
            module.setEndDate(localDateToDate(endDatePicker.getValue()));
            module.setMark(new Mark());
            module.setSkillTags(new ArrayList<>());
            moduleService.saveModule(module);
        } else if(activityTypeComboBox.getValue() == Activity.ActivityType.EXTRA) {
            ExtraCurriculum extraCurriculum = new ExtraCurriculum();
            extraCurriculum.setName(activityNameTextField.getText());
            extraCurriculum.setStartDate(localDateToDate(startDatePicker.getValue()));
            extraCurriculum.setEndDate(localDateToDate(endDatePicker.getValue()));
            extraCurriculumService.saveExtraCurriculum(extraCurriculum);
        }

        activityCardPagination.refresh();

        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
