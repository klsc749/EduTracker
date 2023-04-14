package component;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MarkItem;

public class MarkTable extends TableView<MarkItem> {

    public MarkTable() {
        // Create columns
        TableColumn<MarkItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MarkItem, Double> markColumn = new TableColumn<>("Mark");
        markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));

        TableColumn<MarkItem, Double> proportionColumn = new TableColumn<>("Proportion");
        proportionColumn.setCellValueFactory(new PropertyValueFactory<>("proportion"));


        // Add columns to TableView
        getColumns().addAll(nameColumn, markColumn, proportionColumn);
    }
}
