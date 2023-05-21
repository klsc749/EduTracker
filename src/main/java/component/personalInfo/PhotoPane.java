package component.personalInfo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * The PhotoPane class represents a pane for displaying a photo.
 * It contains an ImageView component to display the image.
 */
public class PhotoPane extends Pane {

    private ImageView imageView;

    /**
     * Constructs a new PhotoPane with the specified image.
     *
     * @param image The image to be displayed in the pane.
     */
    public PhotoPane(Image image) {
        imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        getChildren().add(imageView);
    }

    /**
     * Sets the image to be displayed in the pane.
     *
     * @param image The image to be set.
     */
    public void setImage(Image image) {
        imageView.setImage(image);
    }
}

