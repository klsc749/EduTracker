package component.personalInfo;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;

public class BasicInfoPane extends GridPane {
    private Text nameText;
    private Text emailText;
    private Text gpaText;

    public BasicInfoPane(String name, String email, double gpa) {

        setHgap(10);
        setVgap(10);

        nameText = new Text(name);
        emailText = new Text(email);
        gpaText = new Text();
        gpaText.setText("GPA: " + String.format("%.2f", gpa));

        addRow(0, new Label("Name："), nameText);
        addRow(1, new Label("Email："), emailText);
        addRow(2, new Label("GPA"),gpaText);

    }

    public void setEmail(String email) {
        emailText.setText(email);
    }
}

