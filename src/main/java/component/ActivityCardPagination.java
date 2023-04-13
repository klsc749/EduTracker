package component;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import model.Activity;
import service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityCardPagination extends VBox {
    private Pagination pagination;
    private final int itemsPerPage = 12;
    private List<Activity> activities;

    private final ActivityService activityService = new ActivityService();

    public ActivityCardPagination() {
        this.activities = activityService.getAllActivities();
        int totalPages = (int) Math.ceil((double) activities.size() / itemsPerPage);
        pagination = new Pagination(totalPages, 0);
        pagination.setPageFactory(this::createPage);
        getChildren().add(pagination);
    }

    private GridPane createPage(int pageIndex) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Get screen size
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Calculate card width and height based on screen size
        double cardWidth = screenWidth * 0.15; // Adjust this factor as needed
        double cardHeight = screenHeight * 0.25; // Adjust this factor as needed

        IntegerBinding columns = Bindings.createIntegerBinding(() -> {
            int windowWidth = (int) getScene().getWindow().getWidth();
            return Math.max(windowWidth / (int) cardWidth, 1);
        }, getScene().getWindow().widthProperty());

        columns.addListener((obs, oldVal, newVal) -> {
            // Clear the grid and re-add the cards with the updated columns
            grid.getChildren().clear();
            updateGrid(grid, pageIndex, newVal.intValue(), cardWidth, cardHeight);
        });

        updateGrid(grid, pageIndex, columns.get(), cardWidth, cardHeight);

        return grid;
    }
    private Map<String, ActivityCard> activityCardCache = new HashMap<>();

    private void updateGrid(GridPane grid, int pageIndex, int columns, double cardWidth, double cardHeight) {
        int pageStart = pageIndex * itemsPerPage;
        int pageEnd = Math.min(pageStart + itemsPerPage, activities.size());

        for (int i = pageStart; i < pageEnd; i++) {
            Activity activity = activities.get(i);
            String activityId = activity.getId();

            // Retrieve the cached ActivityCard if it exists, or create a new one and cache it
            ActivityCard activityCard = activityCardCache.computeIfAbsent(activityId, id -> {
                return new ActivityCard(activity, "image/icon.png", 0.5, cardWidth, cardHeight);
            });

            activityCard.setPrefSize(cardWidth, cardHeight);
            int column = (i - pageStart) % columns;
            int row = (i - pageStart) / columns;
            grid.add(activityCard, column, row);
        }
    }
}

