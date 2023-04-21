package component;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class LeftMenu extends VBox {
        public LeftMenu() {
            setSpacing(10);
            setAlignment(Pos.CENTER);
            setPrefWidth(50);
            setStyle("-fx-background-color: rgba(245, 245, 245, 0.95); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0.2, 2, 2);");
        }
}
