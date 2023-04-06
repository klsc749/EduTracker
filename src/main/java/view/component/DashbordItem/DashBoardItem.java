package view.component.DashbordItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Activity;

public class DashBoardItem extends AnchorPane {
    private Label titleLabel;
    private Label typeLabel;
    private ImageView iconView;


    public DashBoardItem(Activity activity) {
        titleLabel = new Label(activity.getName());
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.BLACK);

        typeLabel = new Label(activity.getType().toString());
        typeLabel.setFont(Font.font("System", FontWeight.NORMAL, 12));
        typeLabel.setTextFill(Color.DARKGRAY);

        Image icon = new Image("image/icon.png");
        iconView = new ImageView(icon);
        iconView.setFitWidth(75);
        iconView.setFitHeight(75);

        HBox contentBox = new HBox(iconView, createInfoBox(titleLabel, typeLabel));
        contentBox.setSpacing(50);
        contentBox.setPadding(new Insets(10));
        contentBox.setAlignment(Pos.CENTER_LEFT);

        Rectangle background = new Rectangle(0, 0, 200, 100);
        background.setArcWidth(10);
        background.setArcHeight(10);
        background.setFill(Color.WHITE);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.GRAY);

        setPrefWidth(150);
        setPrefHeight(100);
        setStyle("-fx-background-color: transparent;");

        getChildren().addAll(background, contentBox);
        setEffect(dropShadow);
    }

    private VBox createInfoBox(Label titleLabel, Label typeLabel) {
        VBox infoBox = new VBox(titleLabel, typeLabel);
        infoBox.setSpacing(10);
        infoBox.setAlignment(Pos.CENTER_LEFT);

        return infoBox;
    }
}
