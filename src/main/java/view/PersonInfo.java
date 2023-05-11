package view;

import component.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Student;
import service.StudentService;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PersonInfo extends VBox {

    private BasicInfoPane basicInfoPane;
    private AwardsListPane awardsListPane;
    private ChangeEmailButton changeEmailButton;
    private PhotoPane photoPane;
    private StudentService studentService;
    private TextFlow personalStatementFlow;

    public PersonInfo() {
        initComponents();
        setSpacing(10);
        setPadding(new Insets(10, 10, 10, 10));
        initialize();
    }

    private void initComponents() {
        studentService = new StudentService();
        Student student = studentService.getStudent();

        basicInfoPane = new BasicInfoPane(student.getName(), student.getEmail());
        awardsListPane = new AwardsListPane(FXCollections.observableArrayList(studentService.GetAllAwardContents()));
        changeEmailButton = new ChangeEmailButton("更改邮箱");
        Image image = studentService.loadStudentImage();
        photoPane = new PhotoPane(image);

        personalStatementFlow = new TextFlow();
        personalStatementFlow.setPrefWidth(400); // 设置首选宽度，根据实际情况进行调整

        String personalStatement = studentService.loadStudentPS();
        Text personalStatementText = new Text(personalStatement);
        personalStatementText.setStyle("-fx-font-size: 14px;"); // 根据实际需求设置字体大小

        personalStatementFlow.getChildren().add(personalStatementText);

        VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(basicInfoPane, new Label("奖项："), awardsListPane);

        HBox infoLayout = new HBox();
        infoLayout.setSpacing(10);
        infoLayout.getChildren().addAll(leftVBox, changeEmailButton);

        HBox mainLayout = new HBox();
        mainLayout.setSpacing(10);
        mainLayout.getChildren().addAll(infoLayout, photoPane);

        getChildren().addAll(mainLayout, personalStatementFlow);

        changeEmailButton.setOnAction(event -> showEmailDialog());
    }

    private void initialize() {
        // Nothing to do here in this case.
    }

    private void showEmailDialog() {
        ChangeEmailDialog dialog = new ChangeEmailDialog();
        dialog.showAndWait().ifPresent(newEmail -> {
            try {
                studentService.updateEmail(newEmail);
                basicInfoPane.setEmail(newEmail);
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "邮箱更新失败，请检查输入");
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
