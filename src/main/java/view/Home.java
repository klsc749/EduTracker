package view;

import component.IconButton;
import component.ScrollableActivityCardPagination;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Activity;

import java.util.function.Consumer;


public class Home extends Application {

    private BorderPane root;
    private ScrollableActivityCardPagination scrollableActivityCardPagination;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        VBox leftMenu = createLeftMenu();
        root.setLeft(leftMenu);

        Label defaultLabel = new Label("Click an item on the left menu to display content");
        root.setCenter(defaultLabel);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Window");
        primaryStage.show();
    }

    private VBox createLeftMenu() {
        VBox leftMenu = new VBox(10);
        leftMenu.setAlignment(Pos.CENTER);
        leftMenu.setPrefWidth(50);
        leftMenu.setStyle("-fx-background-color: rgba(245, 245, 245, 0.95); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0.2, 2, 2);");

        IconButton button1 = new IconButton("image/dashboard-default.png", "image/dashboard-hover.png", "Dashboard", 40);
        IconButton button2 = new IconButton("image/personal-default.png", "image/personal-hover.png", "Personal Info", 40);
        leftMenu.getChildren().add(button1);
        leftMenu.getChildren().add(button2);

        button1.setOnAction(event -> {
            setRootRight(createScrollableActivityCardPaginationIfNotExit());
        });

        return leftMenu;
    }

    private ScrollableActivityCardPagination createScrollableActivityCardPaginationIfNotExit() {
        if(scrollableActivityCardPagination == null){
            scrollableActivityCardPagination = new ScrollableActivityCardPagination();
            scrollableActivityCardPagination.setOnActivityClicked(getOnActivitySelected());
        }

        return scrollableActivityCardPagination;
    }

    private Consumer<Activity> getOnActivitySelected(){
        return activity -> {
            if (activity.getType() == Activity.ActivityType.CLASS) {
                ModuleInfo moduleInfo = new ModuleInfo(activity.getName());
                moduleInfo.setOnBackButtonClick(event -> {
                    setRootRight(createScrollableActivityCardPaginationIfNotExit());
                });
                setRootRight(moduleInfo);
            } else if (activity.getType() == Activity.ActivityType.EXTRA) {
                //TODO open extra info
            }
        };
    }

    public void setRootRight(Node node){
        root.setCenter(node);
    }

}

