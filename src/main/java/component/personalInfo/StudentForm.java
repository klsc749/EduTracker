package component.personalInfo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class StudentForm extends HBox {
    private final TextField emailInput;
    private final Button updateButton;

    public StudentForm() {
        Label emailLabel = new Label("电子邮件：");
        emailInput = new TextField();
        emailInput.setPromptText("Email");
        updateButton = new Button("修改");

        setSpacing(10);
        getChildren().addAll(emailLabel, emailInput, updateButton);
    }

    public void setOnUpdateButtonClick(EventHandler<ActionEvent> eventHandler) {
        updateButton.setOnAction(eventHandler);
    }

    public String getEmailInput() {
        return emailInput.getText();
    }

    public void setEmailInput(String email) {
        emailInput.setText(email);
    }
}
