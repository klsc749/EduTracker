package component;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.Activity;

import java.util.function.Consumer;

public class ScrollableActivityCardPagination extends ScrollPane {
    public ScrollableActivityCardPagination() {
        ActivityCardPagination activityCardPagination = new ActivityCardPagination();

        setContent(activityCardPagination);
        setFitToWidth(true);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollBarPolicy.NEVER);
    }

    public void setOnActivityClicked(Consumer<Activity> onActivityClicked) {
        ((ActivityCardPagination) getContent()).setOnActivityClicked(onActivityClicked);
    }
}
