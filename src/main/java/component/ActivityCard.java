package component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ActivityCard extends VBox {
    public ActivityCard(String activityName, String semester, String imagePath, double progress) {
        initStyle();

        ImageView imageView = createImageView(imagePath);
        ProgressIndicator progressIndicator = createProgressIndicator(imageView, progress);
        StackPane imageProgress = createImageProgress(imageView, progressIndicator);
        Label nameLabel = createLabel(activityName, "18", "bold", "black");
        Label semesterLabel = createLabel(semester, "14", "normal", "gray");


        getChildren().addAll(imageProgress, nameLabel, semesterLabel);
    }

    private void initStyle(){
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: white; " +
                "-fx-border-color: #cccccc; -fx-border-width: 2; " +
                "-fx-border-radius: 10; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.15), 10, 0, 0, 4);");
    }

    private ImageView createImageView(String imagePath) {
        ImageView imageView = new ImageView(new Image(imagePath));
        
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        return imageView;
    }


    private ProgressIndicator createProgressIndicator(ImageView imageView, double progress) {
        double progressIndicatorWidth = imageView.getFitWidth() * 0.5;
        ProgressIndicator progressIndicator = new ProgressIndicator(progress);
        progressIndicator.setMinSize(progressIndicatorWidth, progressIndicatorWidth);
        progressIndicator.setStyle("-fx-progress-color: blue;");
        return progressIndicator;
    }

    private StackPane createImageProgress(ImageView imageView, ProgressIndicator progressIndicator) {
        StackPane imageProgress = new StackPane(imageView, progressIndicator);
        StackPane.setAlignment(progressIndicator, Pos.CENTER_RIGHT);
        return imageProgress;
    }

    private Label createLabel(String text, String fontSize, String fontWeight, String textColor) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: " + fontSize + "; -fx-font-weight: " + fontWeight + "; -fx-text-fill: " + textColor + ";");
        return label;
    }
}