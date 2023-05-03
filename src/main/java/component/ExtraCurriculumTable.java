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
import model.ExtraCurriculum;

public class ExtraCurriculumTable extends VBox {

    private TableView<ExtraCurriculum> table = new TableView<>();

    private Label titleLabel;
    private TextField nameTextField;
    private TextField contentTextField;

    private ExtraCurriculum extraCurriculum;

    public void setExtraCurriculum(ExtraCurriculum extraCurriculum) {
        this.extraCurriculum = extraCurriculum;
    }
    public ExtraCurriculumTable() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        table.setEditable(true);

        TableColumn<ExtraCurriculum, String> nameColumn = createNameColumn();
        TableColumn<ExtraCurriculum, String> contentColumn = createContentColumn();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        table.getColumns().addAll(nameColumn, contentColumn);

        // Add columns to TableView
        ObservableList<ExtraCurriculum> observableItems = FXCollections.observableArrayList();
        table.setItems(observableItems);

        titleLabel = createTitleLabel();

        BorderPane textFields = addTextFields(nameColumn.getPrefWidth(), contentColumn.getPrefWidth());
        getChildren().addAll(titleLabel, table, textFields);
    }

    private TableColumn<ExtraCurriculum, String> createNameColumn() {
        TableColumn<ExtraCurriculum, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
        });
        return nameColumn;
    }

    private TableColumn<ExtraCurriculum, String> createContentColumn() {
        TableColumn<ExtraCurriculum, String> contentColumn = new TableColumn<>("Content");
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        contentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        contentColumn.setOnEditCommit(event -> {
            event.getRowValue().setContent(event.getNewValue());
        });
        return contentColumn;
    }

    public Label createTitleLabel() {
        Label label = new Label("Extra Curriculum");
        label.setStyle("-fx-font-size: 16;");
        return label;
    }

    private BorderPane addTextFields(Double nameFieldWidth, Double contentFieldWidth) {
        // create text fields
        nameTextField = createTextField("Name", nameFieldWidth);
        contentTextField = createTextField("Content", contentFieldWidth);

        //add button
        Button addButton = new Button("Add");
        addButton.setOnAction(this::handleAddButtonClick);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(nameTextField, contentTextField, addButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(hBox);

        return borderPane;
    }

    private TextField createTextField(String promptText, Double width){
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setMaxWidth(width);
        return textField;
    }

    private void handleAddButtonClick(Event event) {
        if (nameTextField.getText().isEmpty() || contentTextField.getText().isEmpty()) {
            throw new RuntimeException("Please fill all the fields");
        }
        ExtraCurriculum item = new ExtraCurriculum();
        item.setName(nameTextField.getText());
        item.setContent(contentTextField.getText());
        table.getItems().add(item);
        nameTextField.clear();
        contentTextField.clear();
    }
}