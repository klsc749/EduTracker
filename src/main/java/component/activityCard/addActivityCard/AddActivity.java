package component.activityCard.addActivityCard;

import component.activityCard.ActivityCardPagination;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Activity;

public class AddActivity extends VBox {
    private ComboBox<Activity.ActivityType> activityTypeComboBox;
    private final AddModule addModule;
    private final AddExtra addExtra;

    public AddActivity(ActivityCardPagination activityCardPagination) {
        setSpacing(20);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        this.addModule = new AddModule(activityCardPagination);
        this.addExtra = new AddExtra(activityCardPagination);

        getChildren().addAll(initHeader(), initForm());
    }

    private Label initHeader() {
        Label nameLabel = new Label("Add Activity");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nameLabel.setStyle("-fx-text-fill: #333333;");
        return nameLabel;
    }

    private GridPane initForm() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(10);

        Label activityTypeLabel = new Label("Activity Type:");
        activityTypeLabel.setPadding(new Insets(0, 5, 0, 0));
        activityTypeComboBox = new ComboBox<>();
        activityTypeComboBox.getItems().addAll(Activity.ActivityType.values());
        activityTypeComboBox.getSelectionModel().select(1
        );
        activityTypeComboBox.setPrefWidth(100);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(0, 0, 0, 5));
        hBox.getChildren().addAll(activityTypeLabel, activityTypeComboBox);

        activityTypeComboBox.setOnAction(event -> {
            Activity.ActivityType selectedType = activityTypeComboBox.getSelectionModel().getSelectedItem();

            if (selectedType == Activity.ActivityType.CLASS) {
                gridPane.getChildren().clear();
                gridPane.add(hBox, 0, 0);
                gridPane.add(addModule, 0, 1);
            } else if (selectedType == Activity.ActivityType.EXTRA) {
                gridPane.getChildren().clear();
                gridPane.add(hBox, 0, 0);
                gridPane.add(addExtra, 0, 1);
            } else {
                gridPane.getChildren().clear();
                gridPane.add(hBox, 0, 0);
            }
        });

        gridPane.add(hBox, 0, 0);
        gridPane.add(addModule, 0, 1);

        return gridPane;
    }

}
