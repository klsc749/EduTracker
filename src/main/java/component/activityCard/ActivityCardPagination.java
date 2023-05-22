package component.activityCard;

import component.activityCard.addActivityCard.AddActivityCard;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Pagination;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Activity;
import service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ActivityCardPagination extends FlowPane {
    private final Pagination pagination;
    private final int itemsPerPage = 12;
    private List<Activity> activities;
    private final double cardWidth;
    private final double cardHeight;
    private final IntegerProperty columns = new SimpleIntegerProperty(1);

    private final ActivityService activityService = new ActivityService();

    private Consumer<Activity> onActivityClicked;

    private final AddActivityCard addActivityCard;

    public ActivityCardPagination() {
        // get all activities from DB
        this.activities = activityService.getAllActivities();

        this.addActivityCard = new AddActivityCard(this);

        // calculate total pages
        int totalPages = (int) Math.ceil((double) activities.size() / itemsPerPage);
        pagination = new Pagination(totalPages, 0);

        // set pagination
        pagination.setPageFactory(this::createPage);

        VBox container = new VBox();
        container.getChildren().add(pagination);
        getChildren().add(container);

        // Calculate card width and height based on screen size
        cardWidth = 160;
        cardHeight = 200;

        parentProperty().addListener((obs, oldParent, newParent) -> {
            if (newParent != null) {
                newParent.layoutBoundsProperty().addListener((obs2, oldBounds, newBounds) -> {
                    updateColumns();
                });
            }
        });

    }

    private GridPane createPage(int pageIndex) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Add the cards to the grid and populate the grid
        Platform.runLater(() -> {
            updateGrid(grid, pageIndex, columns.get(), cardWidth, cardHeight);
        });

        columns.addListener((obs, oldVal, newVal) -> {
            // Clear the grid and re-add the cards with the updated columns
            grid.getChildren().clear();
            updateGrid(grid, pageIndex, newVal.intValue(), cardWidth, cardHeight);
        });

        return grid;
    }

    private void updateColumns() {
        javafx.scene.Node parent = getParent();
        if (parent != null) {
            int containerWidth = (int) parent.getLayoutBounds().getWidth();
            int numCardsPerRow = containerWidth / (int) cardWidth;
            columns.set(Math.max(numCardsPerRow, 1));
        }
    }

    private final Map<String, ActivityCard> activityCardCache = new HashMap<>();

    private void updateGrid(GridPane grid, int pageIndex, int columns, double cardWidth, double cardHeight) {
        int pageStart = pageIndex * itemsPerPage;
        int pageEnd = Math.min(pageStart + itemsPerPage, activities.size()) - 1;

        for (int i = pageStart; i <= pageEnd; i++) {
            Activity activity = activities.get(i);
            String activityId = activity.getId();

            ActivityCard activityCard = getActivityCard(activityId, activity, cardWidth, cardHeight);

            activityCard.setPrefSize(cardWidth, cardHeight);
            int column = (i - pageStart) % columns;
            int row = (i - pageStart) / columns;

            attachActivityCardToGrid(grid, activityCard, column, row);
        }

        int column = (pageEnd - pageStart + 1) % columns;
        int row = (pageEnd - pageStart + 1) / columns;

        if(addActivityCard.getParent() != null) {
            ((GridPane) addActivityCard.getParent()).getChildren().remove(addActivityCard);
        }
        addActivityCard.setId("addActivityCard");
        grid.add(addActivityCard, column, row);

    }

    private void attachActivityCardToGrid(GridPane grid, ActivityCard activityCard, int column, int row) {
        // remove the activity card from its parent grid pane
        if (activityCard.getParent() != null) {
            ((GridPane) activityCard.getParent()).getChildren().remove(activityCard);
        }

        // add the activity card to the new grid pane
        grid.add(activityCard, column, row);
    }

    private ActivityCard getActivityCard(String activityId, Activity activity, double cardWidth, double cardHeight) {
        // If the activity card is cached, return it
        if (activityCardCache.containsKey(activityId)) {
            return activityCardCache.get(activityId);
        }

        // Otherwise create a new activity card and cache it
        ActivityCard activityCard = new ActivityCard(activity, "image/icon.png", 0.5, cardWidth, cardHeight, onActivityClicked);
        activityCard.setId(activity.getName());
        activityCardCache.put(activityId, activityCard);
        return activityCard;
    }

    public void setOnActivityClicked(Consumer<Activity> onActivityClicked) {
        this.onActivityClicked = onActivityClicked;
    }

    public void refresh() {
        // get all activities from DB
        this.activities = activityService.getAllActivities();

        // calculate total pages
        int totalPages = (int) Math.ceil((double) activities.size() / itemsPerPage);
        pagination.setPageCount(totalPages);

        // set pagination
        pagination.setPageFactory(this::createPage);

        // Clear the cache
        activityCardCache.clear();
    }

}

