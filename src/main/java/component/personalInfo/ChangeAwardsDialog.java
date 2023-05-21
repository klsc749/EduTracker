package component.personalInfo;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Awards;

public class ChangeAwardsDialog extends Dialog<Awards> {
    private TextField timeField;
    private TextField contentField;

    public ChangeAwardsDialog() {
        setTitle("Add Award");
        setHeaderText(null);

        ButtonType submitButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        timeField = new TextField();
        contentField = new TextField();

        grid.add(new Label("Time："), 0, 0);
        grid.add(timeField, 1, 0);
        grid.add(new Label("Content："), 0, 1);
        grid.add(contentField, 1, 1);

        getDialogPane().setContent(grid);

        setResultConverter(dialogButton -> {
            if (dialogButton == submitButtonType) {
                String time = timeField.getText();
                String content = contentField.getText();
                return new Awards(time, content);
            }
            return null;
        });
    }

    public void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Wrong!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

