package component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class StudentForm extends HBox {
    //private final TextField nameInput;
    private final TextField emailInput;
    private final Button updateButton;

    public StudentForm() {
//        nameInput = new TextField();
//        nameInput.setPromptText("Name");
        emailInput = new TextField();
        emailInput.setPromptText("Email");
        updateButton = new Button("Update");

        setSpacing(10);
        getChildren().addAll( emailInput, updateButton);
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
