package component.personalInfo;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ChangePersonalStatementDialog extends Dialog<String> {

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

    public void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

