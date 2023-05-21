package component.personalInfo;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

public class ChangeEmailDialog extends Dialog<String> {

    private TextField newEmailField;

    public ChangeEmailDialog() {
        setTitle("更改邮箱");
        setHeaderText("请输入新的电子邮件地址");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        newEmailField = new TextField();
        grid.add(new Label("新邮箱："), 0, 0);
        grid.add(newEmailField, 1, 0);

        getDialogPane().setContent(grid);

        ButtonType submitButtonType = new ButtonType("提交", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

        setResultConverter(dialogButton -> {
            if (dialogButton == submitButtonType) {
                return newEmailField.getText();
            }
            return null;
        });
    }

    public void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
