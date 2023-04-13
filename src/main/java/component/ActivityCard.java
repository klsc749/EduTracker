package component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Activity;
import view.ModuleInfo;

import java.text.SimpleDateFormat;

public class ActivityCard extends VBox {
    private Activity activity;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    public ActivityCard(Activity activity, String imagePath, double progress, double width, double height) {
        this.activity = activity;
        initStyle(width, height);

        ImageView imageView = createImageView(imagePath, width, height);
        ProgressIndicator progressIndicator = createProgressIndicator(imageView, progress);
        StackPane imageProgress = createImageProgress(imageView, progressIndicator);
        Label nameLabel = createLabel(activity.getName(), "18", "bold", "black");
        Label semesterLabel = createLabel(dateFormatter.format(activity.getStartDate()) + "--" + dateFormatter.format(activity.getEndDate()),
                "14", "normal", "gray");

        setOnMouseClicked(this::handleClick);

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

    private ImageView createImageView(String imagePath, double width, double height) {
        ImageView imageView = new ImageView(new Image(imagePath));
        
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
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

    private void handleClick(MouseEvent event){
        System.out.println("Clicked " + event.getSource() + " " + this);
        if(activity.getType() == Activity.ActivityType.CLASS) {
            ModuleInfo moduleInfo = new ModuleInfo(activity.getName());
            getScene().setRoot(moduleInfo);
        }else if(activity.getType() == Activity.ActivityType.EXTRA) {
            //TODO open extra info
        }
    }

}