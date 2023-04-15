package component;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Activity;
import util.ImageCache;

import java.text.SimpleDateFormat;
import java.util.function.Consumer;

public class ActivityCard extends VBox {
    private Activity activity;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    private final Consumer<Activity> onActivityClicked;
    public ActivityCard(Activity activity, String imagePath, double progress,
                        double width, double height, Consumer<Activity> onActivityClicked) {
        this.activity = activity;
        this.onActivityClicked = onActivityClicked;
        initStyle(width, height);

        ImageView imageView = createImageView(generateImagePath(), width);
        ProgressIndicator progressIndicator = createProgressIndicator(imageView, progress);
        StackPane imageProgress = createImageProgress(imageView, progressIndicator);
        Label nameLabel = createLabel(activity.getName(), "18", "bold", "black");
        Label semesterLabel = createLabel(dateFormatter.format(activity.getStartDate()) + "--" + dateFormatter.format(activity.getEndDate()),
                "14", "normal", "gray");

        setOnMouseClicked(this::handleClick);
        setOnMouseEntered(this::handleMouseEntered);
        setOnMouseExited(this::handleMouseExited);

        getChildren().addAll(imageProgress, nameLabel, semesterLabel);
    }

    private void initStyle(double width, double height){
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setMinWidth(width);
        setMaxWidth(width);
        setStyle("-fx-background-color: white; " +
                "-fx-border-color: #cccccc; -fx-border-width: 2; " +
                "-fx-border-radius: 10; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.15), 10, 0, 0, 4);");
    }

    private String generateImagePath(){
        int end = 3;
        int imageNum = Math.abs(activity.getId().hashCode()) % end + 1;
        if(activity.getType() == Activity.ActivityType.CLASS){
            return "image/module/image" + imageNum + ".png";
        }else {
            return "image/icon.png";
        }
    }

    private ImageView createImageView(String imagePath, double width) {
        Image image = ImageCache.getImage(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(width);
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

    private void handleClick(MouseEvent event) {
        if(onActivityClicked != null)
            onActivityClicked.accept(activity);
    }

    private void handleMouseEntered(MouseEvent event) {
        setStyle("-fx-background-color: #f5f5f5; " +
                "-fx-border-color: #cccccc; -fx-border-width: 2; " +
                "-fx-border-radius: 10; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.25), 10, 0, 0, 6);");
        setCursor(Cursor.HAND);
    }

    private void handleMouseExited(MouseEvent event) {
        setStyle("-fx-background-color: white; " +
                "-fx-border-color: #cccccc; -fx-border-width: 2; " +
                "-fx-border-radius: 10; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.15), 10, 0, 0, 4);");
        setCursor(Cursor.DEFAULT);
    }


}