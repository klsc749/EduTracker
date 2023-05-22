package component.activityCard;

import javafx.scene.control.ScrollPane;
import model.Activity;

import java.util.function.Consumer;

public class ScrollableActivityCardPagination extends ScrollPane {
    private ActivityCardPagination activityCardPagination;
    
    public ScrollableActivityCardPagination() {
        this.activityCardPagination = new ActivityCardPagination();

        setContent(activityCardPagination);
        setFitToWidth(true);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollBarPolicy.NEVER);
    }

    public void refresh() {
        activityCardPagination.refresh();
    }

    public void setOnActivityClicked(Consumer<Activity> onActivityClicked) {
        ((ActivityCardPagination) getContent()).setOnActivityClicked(onActivityClicked);
    }
}
