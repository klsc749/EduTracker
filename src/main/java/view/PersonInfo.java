package view;

import component.StudentForm;
import dao.StudentDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Student;
import service.StudentService;

public class PersonInfo extends Application {

    StudentService sv = new StudentService();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("学生个人信息管理");

        // 创建表格
        TableView<Student> table = createStudentTable();
        ObservableList<Student> data = FXCollections.observableArrayList();
        table.setItems(data);

        // 初始化学生数据
        initStudentData(data);

        // 创建输入框和按钮
        StudentForm form = new StudentForm();
        form.setOnUpdateButtonClick(event -> {
            if (!data.isEmpty()) {
                Student student = data.get(0);
                student.setEmail(form.getEmailInput());
                table.refresh(); // 立即更新表格中的信息
            }
        });

        // 布局
        VBox root = new VBox(10, table, form);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }

    private TableView<Student> createStudentTable() {
        TableView<Student> table = new TableView<>();

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(150);

        TableColumn<Student, String> idColumn = new TableColumn<>("StudentId");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setPrefWidth(150);

        TableColumn<Student, String> degreeColumn = new TableColumn<>("Degree");
        degreeColumn.setCellValueFactory(new PropertyValueFactory<>("degree"));

        // ... 更多列 ...

        table.getColumns().addAll(nameColumn, idColumn,emailColumn, degreeColumn);

        return table;
    }

    private void initStudentData(ObservableList<Student> data) {
        // 创建一个学生对象
        Student student = sv.getStudent();

        // 添加学生对象到数据列表
        data.add(student);
    }
}


