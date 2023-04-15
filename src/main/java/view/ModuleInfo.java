package view;

import component.ActivityCard;
import component.ActivityCardPagination;
import component.MarkTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Activity;
import model.MarkItem;

import java.util.ArrayList;
import java.util.List;

public class ModuleInfo extends  VBox {
    private final Button backButton;

    private MarkTable markTable;

    private Activity activity;


    public ModuleInfo(Activity activity) {
        this.activity = activity;
        setSpacing(10);
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Details for " + activity.getName());
        titleLabel.setStyle("-fx-font-size: 24;");

        backButton = new Button("Go Back");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.getChildren().add(backButton);
        //TODO: Read MarkItems from activity
        List<MarkItem> markItems = new ArrayList<>();
        markItems.add(new MarkItem("Item 1", 80.0, 0.4));
        markItems.add(new MarkItem("Item 2", 90.0, 0.6));

        markTable = new MarkTable(markItems);
        // Assuming Dashboard is your main layout class
        getChildren().addAll(backButton, titleLabel, markTable);
    }

    public void setOnBackButtonClick(EventHandler<ActionEvent> eventHandler) {
        backButton.setOnAction(eventHandler);
    }
}
