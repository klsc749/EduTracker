package component;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MarkItem;
import model.Student;

public class StudentInfoTable extends TableView<Student> {
    public StudentInfoTable() {
        // Create columns
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student,String> idColumn = new TableColumn<>("StudentId");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));


        // Add columns to TableView
        getColumns().addAll(nameColumn, idColumn, emailColumn);
    }
}
