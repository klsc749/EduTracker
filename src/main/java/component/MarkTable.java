package component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import model.MarkItem;

import java.util.ArrayList;
import java.util.List;

public class MarkTable extends VBox {

    private TableView<MarkItem> table = new TableView<>();

    private List<MarkItem> markItems;

    public MarkTable(List<MarkItem> markItems) {
        this.markItems = markItems == null ? new ArrayList<>() : markItems;
        setSpacing(10);
        table.setEditable(true);
        TableColumn<MarkItem, String> nameColumn = createNameColumn();
        TableColumn<MarkItem, Double> markColumn = createMarkColumn();
        TableColumn<MarkItem, Double> proportionColumn = createProportionColumn();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        HBox hBox = addTextFields(nameColumn.getPrefWidth(), markColumn.getPrefWidth(), proportionColumn.getPrefWidth());

        table.getColumns().addAll(nameColumn, markColumn, proportionColumn);

        // Add columns to TableView
        ObservableList<MarkItem> observableMarkItems = FXCollections.observableArrayList(markItems);
        table.setItems(observableMarkItems);

        getChildren().addAll(table, hBox);
    }

    private TableColumn<MarkItem, String> createNameColumn() {
        TableColumn<MarkItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
            updateMarkItemList();
        });
        return nameColumn;
    }

    private TableColumn<MarkItem, Double> createMarkColumn() {
        TableColumn<MarkItem, Double> markColumn = new TableColumn<>("Mark");
        markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
        markColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        markColumn.setOnEditCommit(event -> {
            event.getRowValue().setMark(event.getNewValue());
            updateMarkItemList();
        });
        return markColumn;
    }

    private TableColumn<MarkItem, Double> createProportionColumn() {
        TableColumn<MarkItem, Double> proportionColumn = new TableColumn<>("Proportion");
        proportionColumn.setCellValueFactory(new PropertyValueFactory<>("proportion"));
        proportionColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        proportionColumn.setOnEditCommit(event -> {
            event.getRowValue().setProportion(event.getNewValue());
            updateMarkItemList();
        });
        return proportionColumn;
    }

    private HBox addTextFields(Double nameFieldWidth, Double markFieldWidth, Double proportionFieldWidth) {
        TextField nameField = new TextField();
        nameField.setPromptText("Mark name");
        nameField.setMaxWidth(nameFieldWidth);
        TextField markField = new TextField();
        markField.setPromptText("Mark");
        markField.setMaxWidth(markFieldWidth);
        TextField proportionField = new TextField();
        proportionField.setPromptText("Proportion");
        proportionField.setMaxWidth(proportionFieldWidth);

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            MarkItem markItem = new MarkItem(nameField.getText(), Double.parseDouble(markField.getText()), Double.parseDouble(proportionField.getText()));
            System.out.println(markItem);
            table.getItems().add(markItem);
            nameField.clear();
            markField.clear();
            proportionField.clear();
        });
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            //TODO: Save the markItems
            System.out.println(markItems);
        });

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(nameField, markField, proportionField, addButton, saveButton);

        return hBox;
    }

    private void updateMarkItemList() {
        markItems.clear();
        markItems.addAll(table.getItems());
    }
}
