package component;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class ChangePersonalStatementDialog extends Dialog<String> {

    public ChangePersonalStatementDialog() {
        initDialog();
    }

    private void initDialog() {
        setTitle("更改个人陈述");
        setHeaderText(null);

        DialogPane dialogPane = getDialogPane();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextArea personalStatementTextArea = new TextArea();
        personalStatementTextArea.setPrefRowCount(5);
        personalStatementTextArea.setWrapText(true);

        gridPane.addRow(0, new Label("新的个人陈述："), personalStatementTextArea);

        dialogPane.setContent(gridPane);

        ButtonType confirmButtonType = new ButtonType("确认", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);

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
        alert.setTitle("警告");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

