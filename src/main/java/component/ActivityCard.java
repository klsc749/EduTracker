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
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: white; " +
                "-fx-border-color: #cccccc; -fx-border-width: 2; " +
                "-fx-border-radius: 10; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.15), 10, 0, 0, 4);");

        // Image
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        double progressIndicatorWidth = imageView.getFitWidth() * 0.5;
        double progressIndicatorHeight = progressIndicatorWidth;
        // Circular progress indicator
        ProgressIndicator progressIndicator = new ProgressIndicator(progress);
        progressIndicator.setPrefSize(progressIndicatorWidth, progressIndicatorHeight);
        progressIndicator.setMinSize(progressIndicatorWidth, progressIndicatorHeight);
        progressIndicator.setStyle("-fx-progress-color: blue;");

        // StackPane for overlaying the image and progress indicator
        StackPane imageProgress = new StackPane(imageView, progressIndicator);
        StackPane.setAlignment(progressIndicator, Pos.CENTER_RIGHT);

        // Activity name and semester labels
        Label nameLabel = new Label(activityName);
        nameLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        Label semesterLabel = new Label(semester);
        semesterLabel.setStyle("-fx-font-size: 14; -fx-text-fill: gray;");

        getChildren().addAll(imageProgress, nameLabel, semesterLabel);
    }
}
