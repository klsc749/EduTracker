package view;

import component.personalInfo.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.Student;
import service.ModuleService;
import service.StudentService;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.util.HashMap;

public class PersonInfo extends VBox {

    private BasicInfoPane basicInfoPane;
    private AwardsListPane awardsListPane;
    private Button changeEmailButton;
    private Button changeAwardsButton;
    private Button changePersonalStatementButton;
    private Button changePhotoButton;
    private Button exportCVButton;
    private PhotoPane photoPane;
    private StudentService studentService;
    private ModuleService moduleService;
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
        moduleService = new ModuleService();

        basicInfoPane = new BasicInfoPane(student.getName(), student.getEmail(),moduleService.calculateGPA());
        awardsListPane = new AwardsListPane(FXCollections.observableArrayList(studentService.GetAllAwardContents()));
        changeEmailButton = new Button("Update Email");
        changeAwardsButton = new Button("Add Award");
        changePersonalStatementButton = new Button("Update PS");
        changePhotoButton = new Button("Update Photo");
        exportCVButton = new Button("Export CV");
        Image image = studentService.loadStudentImage();
        photoPane = new PhotoPane(image);

        personalStatementFlow = new TextFlow();
        personalStatementFlow.setPrefWidth(400);

        String personalStatement = studentService.loadStudentPS();
        Text personalStatementText = new Text(personalStatement);
        personalStatementText.setStyle("-fx-font-size: 14px;");

        personalStatementFlow.getChildren().add(personalStatementText);

        HBox mainLayout = new HBox();
        mainLayout.setSpacing(10);


        VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(
                basicInfoPane,
                new Label("Awards："),
                awardsListPane,
                changeEmailButton,
                changeAwardsButton,
                changePersonalStatementButton,
                changePhotoButton,
                exportCVButton
        );

        mainLayout.getChildren().addAll(leftVBox, photoPane);

        getChildren().addAll(mainLayout, personalStatementFlow);

        changeEmailButton.setOnAction(event -> showEmailDialog());
        changeAwardsButton.setOnAction(event -> showAwardsDialog());
        changePersonalStatementButton.setOnAction(event -> showPersonalStatementDialog());
        changePhotoButton.setOnAction(event -> showPhotoDialog());
        exportCVButton.setOnAction(event -> exportCV());
    }

    private void initialize() {

    }

    private void showEmailDialog() {
        ChangeEmailDialog dialog = new ChangeEmailDialog();
        dialog.showAndWait().ifPresent(newEmail -> {
            try {
                studentService.updateEmail(newEmail);
                basicInfoPane.setEmail(newEmail);
            } catch (Exception e) {
                dialog.showAlert(Alert.AlertType.ERROR, "Invalid Input");
            }
        });
    }

    private void showAwardsDialog(){
        ChangeAwardsDialog dialog = new ChangeAwardsDialog();
        dialog.showAndWait().ifPresent(awards -> {
            try {
                // Update Awards
                HashMap<String, String> newAwards = studentService.AddAwards(awards.getTime(), awards.getContent());
                awardsListPane.setItems(FXCollections.observableArrayList(newAwards.values()));
            } catch (Exception e) {
                dialog.showAlert(Alert.AlertType.ERROR, "Invalid Input");
            }
        });
    }

    private void showPersonalStatementDialog() {
        ChangePersonalStatementDialog dialog = new ChangePersonalStatementDialog();
        dialog.showAndWait().ifPresent(newPersonalStatement -> {
            try {
                // Update PS
                String modifiedPersonalStatement = studentService.ModifyStudentPS(newPersonalStatement);
                Text newPersonalStatementText = new Text(modifiedPersonalStatement);
                newPersonalStatementText.setStyle("-fx-font-size: 14px;");
                personalStatementFlow.getChildren().clear();
                personalStatementFlow.getChildren().add(newPersonalStatementText);
            } catch (Exception e) {
                dialog.showAlert(Alert.AlertType.ERROR, "Invalid Input");
            }
        });
    }

    private void showPhotoDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp"));
        File selectedFile = fileChooser.showOpenDialog(getScene().getWindow());
        if (selectedFile != null) {
            try {
                // Update Photo
                Image newImage = studentService.updateStudentImage(selectedFile);
                photoPane.setImage(newImage);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                System.out.println(e);
                alert.setTitle("Wrong!");
                alert.setHeaderText(null);
                alert.setContentText("Fail in Updating Photo");
                alert.showAndWait();
            }
        }
    }
    private void exportCV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("CV.pdf");
        File selectedFile = fileChooser.showSaveDialog(getScene().getWindow());
        if (selectedFile != null) {
            try {
                File outputFile = studentService.ExportCV(selectedFile.getParent());
                // 导出CV成功，显示成功提示
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Export CV");
                successAlert.setHeaderText(null);
                successAlert.setContentText("CV exported successfully!");
                successAlert.showAndWait();
            } catch (Exception e) {
                // 导出CV失败，显示错误提示
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Export CV");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to export CV. Please try again.");
                errorAlert.showAndWait();
            }
        }
    }

}

