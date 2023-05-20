package component;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PhotoPane extends Pane {

    private ImageView imageView;

    public PhotoPane(Image image) {
        imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        getChildren().add(imageView);
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }
}

