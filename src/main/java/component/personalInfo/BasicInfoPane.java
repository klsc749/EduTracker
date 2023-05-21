package component.personalInfo;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * The BasicInfoPane class represents a custom GridPane component that displays basic information of a student.
 * It includes the student's name, email, and GPA.
 */
public class BasicInfoPane extends GridPane {
    private Text nameText;
    private Text emailText;
    private Text gpaText;

    /**
     * Constructs a new BasicInfoPane with the specified name, email, and GPA.
     *
     * @param name  The name of the student.
     * @param email The email address of the student.
     * @param gpa   The GPA (Grade Point Average) of the student.
     */
    public BasicInfoPane(String name, String email, double gpa) {

        setHgap(10);
        setVgap(10);

        nameText = new Text(name);
        emailText = new Text(email);
        gpaText = new Text();
        gpaText.setText(String.format("%.2f", gpa));

        addRow(0, new Label("Name："), nameText);
        addRow(1, new Label("Email："), emailText);
        addRow(2, new Label("GPA"),gpaText);

    }

    /**
     * Sets the email address of the student.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        emailText.setText(email);
    }
}

