package component.personalInfo;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

/**
 * The ChangeEmailDialog class represents a custom Dialog for updating the email address.
 * It allows the user to enter a new email address.
 */
public class ChangeEmailDialog extends Dialog<String> {

    private TextField newEmailField;

    /**
     * Constructs a new ChangeEmailDialog.
     * Sets the title and header text, and initializes the dialog components.
     */
    public ChangeEmailDialog() {
        setTitle("Update Email");
        setHeaderText("Please input your email address");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        newEmailField = new TextField();
        newEmailField.setId("personalEmailField");
        grid.add(new Label("New Email Address："), 0, 0);
        grid.add(newEmailField, 1, 0);

        getDialogPane().setContent(grid);

        ButtonType submitButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

        setResultConverter(dialogButton -> {
            if (dialogButton == submitButtonType) {
                return newEmailField.getText();
            }
            return null;
        });
    }

    /**
     * Shows an alert dialog with the specified alert type and message.
     *
     * @param alertType The type of the alert dialog.
     * @param message   The message to be displayed in the alert dialog.
     */
    public void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Wrong");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
