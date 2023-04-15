package component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import model.Mark;
import model.MarkItem;

import java.util.ArrayList;
import java.util.List;

public class MarkTable extends VBox {

    private TableView<MarkItem> table = new TableView<>();

    private Mark mark;

    private Label totalMarkLabel;
    private TextField nameTextField;
    private TextField markTextField;
    private TextField proportionTextField;

    public MarkTable(Mark mark) {
        this.mark = mark;
        if(mark.getMarkItems() == null) {
            mark.setMarkItems(new ArrayList<>());
        }
        setSpacing(10);
        setAlignment(Pos.CENTER);
        table.setEditable(true);
        TableColumn<MarkItem, String> nameColumn = createNameColumn();
        TableColumn<MarkItem, Double> markColumn = createMarkColumn();
        TableColumn<MarkItem, Double> proportionColumn = createProportionColumn();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        table.getColumns().addAll(nameColumn, markColumn, proportionColumn);

        // Add columns to TableView
        ObservableList<MarkItem> observableMarkItems = FXCollections.observableArrayList(mark.getMarkItems());
        table.setItems(observableMarkItems);

        totalMarkLabel = createTotalMarkLabel();

        BorderPane textFields = addTextFields(nameColumn.getPrefWidth(), markColumn.getPrefWidth(), proportionColumn.getPrefWidth());
        getChildren().addAll(table, totalMarkLabel, textFields);
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

    public Label createTotalMarkLabel() {
        Label label = new Label("Total mark: " + mark.getTotalMark());
        label.setStyle("-fx-font-size: 16;");
        return label;
    }

    public void updateTotalMarkLabel() {
        totalMarkLabel.setText("Total mark: " + mark.getTotalMark());
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
        mark.getMarkItems().add(markItem);
        table.getItems().add(markItem);
        nameTextField.clear();
        markTextField.clear();
        proportionTextField.clear();

        //update total mark
        updateTotalMarkLabel();
    }

    private void handleSaveButtonClick(Event event) {
        //TODO: Save the markItems
        System.out.println(mark.getMarkItems());
    }

    private void updateMarkItemList() {
        mark.getMarkItems().clear();
        mark.getMarkItems().addAll(table.getItems());
        updateTotalMarkLabel();
    }
}
