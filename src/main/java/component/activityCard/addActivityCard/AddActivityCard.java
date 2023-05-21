package component.activityCard.addActivityCard;

import component.activityCard.ActivityCardPagination;
import component.activityCard.addActivityCard.AddActivity;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.ActivityService;
import util.ImageCache;


public class AddActivityCard extends VBox {
    private final double maxWidth = 160;

    private Image addDefaultImage;
    private Image addHoverImage;

    private final ImageView imageView;

    private final ActivityService activityService = new ActivityService();

    private final ActivityCardPagination activityCardPagination;
    public AddActivityCard(ActivityCardPagination activityCardPagination){
        this.activityCardPagination = activityCardPagination;
        initStyle();
        initAddImage();
        imageView = new ImageView(addDefaultImage);
        imageView.setFitWidth(maxWidth * 0.8);
        imageView.setFitHeight(maxWidth * 0.8);
        Label label = new Label("Add Activity");
        label.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #cccccc;");
        getChildren().addAll(imageView, label);

        addEventHandler(MouseEvent.MOUSE_ENTERED, event -> handleMouseEntered());
        addEventHandler(MouseEvent.MOUSE_EXITED, event -> handleMouseExited());
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleMouseClicked());
    }

    private void initStyle(){
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setMinWidth(maxWidth);
        setMaxWidth(maxWidth);
        setStyle("-fx-background-color: white; " +
                "-fx-border-color: #cccccc; -fx-border-width: 2; " +
                "-fx-border-radius: 10; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.15), 10, 0, 0, 4);");
    }

    private void initAddImage(){
        addDefaultImage = ImageCache.getImage("image/add-default.png");
        addHoverImage = ImageCache.getImage("image/add-hover.png");
    }

    public void handleMouseEntered(){
        imageView.setImage(addHoverImage);
    }

    public void handleMouseExited(){
        imageView.setImage(addDefaultImage);
    }

    public void handleMouseClicked(){
        showAddActivityDialog();
    }

    private void showAddActivityDialog() {
        // Create a new stage for the dialog
        Stage dialogStage = new Stage();
        dialogStage.initOwner(getStageFromNode(this)); // Pass the reference to the main window stage
        dialogStage.initModality(Modality.WINDOW_MODAL); // make the dialog modal and block the main window
        dialogStage.setTitle("Add Activity");

        AddActivity addActivity = new AddActivity(activityCardPagination);
        Scene dialogScene = new Scene(addActivity);
        dialogStage.setScene(dialogScene);
        dialogStage.sizeToScene(); // Resizes the stage to fit the preferred size of its content
        dialogStage.setResizable(false);
        dialogStage.showAndWait();
    }


    private Stage getStageFromNode(Node node) {
        if (node != null) {
            Scene scene = node.getScene();
            if (scene != null) {
                Window window = scene.getWindow();
                if (window instanceof Stage) {
                    return (Stage) window;
                }
            }
        }
        return null;
    }
}
