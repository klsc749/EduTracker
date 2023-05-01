package view;

import component.AwardsListPane;
import component.BasicInfoPane;
import component.ChangeEmailButton;
import component.PhotoPane;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Student;
import service.StudentService;

public class PersonInfo extends VBox {

    private BasicInfoPane basicInfoPane;
    private AwardsListPane awardsListPane;
    private ChangeEmailButton changeEmailButton;
    private PhotoPane photoPane;
    private StudentService studentService;

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

        HBox mainLayout = new HBox();
        mainLayout.setSpacing(10);

        VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(basicInfoPane, new Label("奖项："), awardsListPane, changeEmailButton);

        mainLayout.getChildren().addAll(leftVBox, photoPane);

        getChildren().add(mainLayout);

        changeEmailButton.setOnAction(event -> showEmailDialog());
    }

    private void initialize() {
        // Nothing to do here in this case.
    }

    private void showEmailDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("更改邮箱");
        dialog.setHeaderText("请输入新的电子邮件地址");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField newEmailField = new TextField();
        grid.add(new Label("新邮箱："), 0, 0);
        grid.add(newEmailField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        ButtonType submitButtonType = new ButtonType("提交", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == submitButtonType) {
                return newEmailField.getText();
            }
            return null;
        });

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
