package component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import model.MarkItem;

import java.util.ArrayList;
import java.util.List;

public class MarkTable extends VBox {

    private TableView<MarkItem> table = new TableView<>();

    private List<MarkItem> markItems;

    private TextField nameTextField;
    private TextField markTextField;
    private TextField proportionTextField;

    public MarkTable(List<MarkItem> markItems) {
        this.markItems = markItems == null ? new ArrayList<>() : markItems;
        setSpacing(10);
        table.setEditable(true);
        TableColumn<MarkItem, String> nameColumn = createNameColumn();
        TableColumn<MarkItem, Double> markColumn = createMarkColumn();
        TableColumn<MarkItem, Double> proportionColumn = createProportionColumn();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        table.getColumns().addAll(nameColumn, markColumn, proportionColumn);

        // Add columns to TableView
        ObservableList<MarkItem> observableMarkItems = FXCollections.observableArrayList(markItems);
        table.setItems(observableMarkItems);

        BorderPane textFields = addTextFields(nameColumn.getPrefWidth(), markColumn.getPrefWidth(), proportionColumn.getPrefWidth());
        getChildren().addAll(table, textFields);
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

    private BorderPane addTextFields(Double nameFieldWidth, Double markFieldWidth, Double proportionFieldWidth) {
        // create text fields
        nameTextField = createTextFiled("Mark name", nameFieldWidth);
        markTextField = createTextFiled("Mark", markFieldWidth);
        proportionTextField = createTextFiled("Proportion", proportionFieldWidth);

        //add button
        Button addButton = new Button("Add");
        addButton.setOnAction(this::handleAddButtonClick);

        //save button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(this::handleSaveButtonClick);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(nameTextField, markTextField, proportionTextField, addButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(saveButton);
        borderPane.setLeft(hBox);

        return borderPane;
    }

    private TextField createTextFiled(String promptText, Double width){
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setMaxWidth(width);
        return textField;
    }

    private void handleAddButtonClick(Event event) {
        if (nameTextField.getText().isEmpty() || markTextField.getText().isEmpty() || proportionTextField.getText().isEmpty()) {
            return;
        }
        MarkItem markItem = new MarkItem(nameTextField.getText(), Double.parseDouble(markTextField.getText()), Double.parseDouble(proportionTextField.getText()));
        System.out.println(markItem);
        //TODO: Add markItem
        table.getItems().add(markItem);
        nameTextField.clear();
        markTextField.clear();
        proportionTextField.clear();
    }

    private void handleSaveButtonClick(Event event) {
        //TODO: Save the markItems
        System.out.println(markItems);
    }

    private void updateMarkItemList() {
        markItems.clear();
        markItems.addAll(table.getItems());
    }
}
