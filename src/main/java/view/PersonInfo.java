package view;

import component.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Student;
import service.StudentService;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.util.HashMap;

public class PersonInfo extends VBox {

    private BasicInfoPane basicInfoPane;
    private AwardsListPane awardsListPane;
    private ChangeEmailButton changeEmailButton;
    private Button changeAwardsButton;
    private Button changePersonalStatementButton;
    private Button changePhotoButton;
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
        changeAwardsButton = new Button("更改奖项");
        changePersonalStatementButton = new Button("更改个人陈述");
        changePhotoButton = new Button("更改照片");
        Image image = studentService.loadStudentImage();
        photoPane = new PhotoPane(image);

        personalStatementFlow = new TextFlow();
        personalStatementFlow.setPrefWidth(400); // 设置首选宽度，根据实际情况进行调整

        String personalStatement = studentService.loadStudentPS();
        Text personalStatementText = new Text(personalStatement);
        personalStatementText.setStyle("-fx-font-size: 14px;"); // 根据实际需求设置字体大小

        personalStatementFlow.getChildren().add(personalStatementText);

        HBox mainLayout = new HBox();
        mainLayout.setSpacing(10);

        VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(
                basicInfoPane,
                new Label("奖项："),
                awardsListPane,
                changeEmailButton,
                changeAwardsButton,
                changePersonalStatementButton,
                changePhotoButton
        );

        mainLayout.getChildren().addAll(leftVBox, photoPane);

        getChildren().addAll(mainLayout, personalStatementFlow);

        changeEmailButton.setOnAction(event -> showEmailDialog());
        changeAwardsButton.setOnAction(event -> showAwardsDialog());
        changePersonalStatementButton.setOnAction(event -> showPersonalStatementDialog());
        changePhotoButton.setOnAction(event -> showPhotoDialog());
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
                dialog.showAlert(Alert.AlertType.ERROR, "邮箱更新失败，请检查输入");
            }
        });
    }

    private void showAwardsDialog(){
        ChangeAwardsDialog dialog = new ChangeAwardsDialog();
        dialog.showAndWait().ifPresent(awards -> {
            try {
                // 更新奖项
                HashMap<String, String> newAwards = studentService.AddAwards(awards.getTime(), awards.getContent());
                awardsListPane.setItems(FXCollections.observableArrayList(newAwards.values()));
            } catch (Exception e) {
                dialog.showAlert(Alert.AlertType.ERROR, "添加奖项失败，请检查输入");
            }
        });
    }

    private void showPersonalStatementDialog() {
        ChangePersonalStatementDialog dialog = new ChangePersonalStatementDialog();
        dialog.showAndWait().ifPresent(newPersonalStatement -> {
            try {
                // 更新个人陈述
                String modifiedPersonalStatement = studentService.ModifyStudnetPS(newPersonalStatement);
                Text newPersonalStatementText = new Text(modifiedPersonalStatement);
                newPersonalStatementText.setStyle("-fx-font-size: 14px;");
                personalStatementFlow.getChildren().clear();
                personalStatementFlow.getChildren().add(newPersonalStatementText);
            } catch (Exception e) {
                dialog.showAlert(Alert.AlertType.ERROR, "更新个人陈述失败，请检查输入");
            }
        });
    }

    private void showPhotoDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp"));
        File selectedFile = fileChooser.showOpenDialog(getScene().getWindow());
        if (selectedFile != null) {
            try {
                // 更新照片
                Image newImage = studentService.updateStudentImage(selectedFile);
                if(newImage != null) System.out.println("NULL");
                photoPane.setImage(newImage);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                System.out.println(e);
                alert.setTitle("错误");
                alert.setHeaderText(null);
                alert.setContentText("更改照片失败，请检查文件格式和路径");
                alert.showAndWait();
            }
        }
    }
}

