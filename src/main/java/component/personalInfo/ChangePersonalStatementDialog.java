package component.personalInfo;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * The ChangePersonalStatementDialog class represents a custom Dialog for updating the personal statement.
 * It allows the user to enter a new personal statement.
 */
public class ChangePersonalStatementDialog extends Dialog<String> {

    /**
     * Constructs a new ChangePersonalStatementDialog.
     * Initializes the dialog components.
     */
    public ChangePersonalStatementDialog() {
        initDialog();
    }

    private void initDialog() {
        setTitle("Update PS");
        setHeaderText(null);

        DialogPane dialogPane = getDialogPane();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextArea personalStatementTextArea = new TextArea();
        personalStatementTextArea.setId("personalStatementTextArea");
        personalStatementTextArea.setPrefRowCount(5);
        personalStatementTextArea.setWrapText(true);

        gridPane.addRow(0, new Label("New PS："), personalStatementTextArea);

        dialogPane.setContent(gridPane);

        ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialogPane.getButtonTypes().addAll(confirmButtonType, cancelButtonType);

        setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                return personalStatementTextArea.getText();
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
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

