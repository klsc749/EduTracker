package component;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BasicInfoPane extends GridPane {

    private Label nameLabel;
    private Label emailLabel;

    public BasicInfoPane(String name, String email) {
        nameLabel = new Label(name);
        emailLabel = new Label(email);

        setHgap(10);
        setVgap(10);

        add(new Label("姓名："), 0, 0);
        add(nameLabel, 1, 0);
        add(new Label("邮箱："), 0, 1);
        add(emailLabel, 1, 1);
    }

    public void setEmail(String email) {
        emailLabel.setText(email);
    }
}

