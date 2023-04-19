package component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconButton extends Button {

    private final ObjectProperty<Image> defaultIcon;
    private final ObjectProperty<Image> hoverIcon;

    public IconButton(String defaultIconPath, String hoverIconPath, String tooltipText, double size) {
        super();

        // Load the default and hover icons
        defaultIcon = new SimpleObjectProperty<>(new Image(defaultIconPath));
        hoverIcon = new SimpleObjectProperty<>(new Image(hoverIconPath));

        // Set the button's icon using the default image
        ImageView imageView = new ImageView(defaultIcon.get());
        setGraphic(imageView);

        // Set the button's tooltip using the provided text
        Tooltip tooltip = new Tooltip(tooltipText);
        Tooltip.install(this, tooltip);

        // Style the button
        getStyleClass().add("icon-button");
        setMaxSize(size, size);
        setMinSize(size, size);

        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        // Add event listeners to change the icon when the mouse enters and exits the button
        setOnMouseEntered(event -> imageView.setImage(hoverIcon.get()));
        setOnMouseExited(event -> imageView.setImage(defaultIcon.get()));
    }
}
