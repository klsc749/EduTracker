package component;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ScrollableActivityCardPagination extends ScrollPane {
    public ScrollableActivityCardPagination() {
        ActivityCardPagination activityCardPagination = new ActivityCardPagination();

        setContent(activityCardPagination);
        setFitToWidth(true);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollBarPolicy.NEVER);
    }
}
