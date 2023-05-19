package component;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;

public class BasicInfoPane extends GridPane {
    private Text nameText;
    private Text emailText;

    public BasicInfoPane(String name, String email) {

        setHgap(10);
        setVgap(10);

        nameText = new Text(name);
        emailText = new Text(email);

        addRow(0, new Label("姓名："), nameText);
        addRow(1, new Label("邮箱："), emailText);
    }

    public void setEmail(String email) {
        emailText.setText(email);
    }
}

