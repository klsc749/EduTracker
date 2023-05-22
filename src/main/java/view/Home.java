package view;

import component.IconButton;
import component.LeftMenu;
import component.activityCard.ScrollableActivityCardPagination;
import handler.OnActivitySelectedHandler;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Home extends Application {

    private BorderPane root;
    private ScrollableActivityCardPagination scrollableActivityCardPagination;

    private PersonInfo personInfo;
    private LeftMenu leftMenu;

    public static Home instance;

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        root = new BorderPane();
        VBox leftMenu = createLeftMenu();
        root.setLeft(leftMenu);

        Label defaultLabel = new Label("Click an item on the left menu to display content");
        root.setCenter(defaultLabel);

        setRootRight(createScrollableActivityCardPaginationIfNotExit());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Window");
        primaryStage.show();
    }

    /*
    * Create the left menu
     */
    private VBox createLeftMenu() {
        leftMenu = new LeftMenu();
        //add buttons to left menu
        IconButton button1 = new IconButton("image/dashboard-default.png", "image/dashboard-hover.png", "Dashboard", 40);
        IconButton button2 = new IconButton("image/personal-default.png", "image/personal-hover.png", "Personal Info", 40);
        leftMenu.getChildren().add(button1);
        leftMenu.getChildren().add(button2);

        button1.setOnAction(event -> {
            setRootRight(createScrollableActivityCardPaginationIfNotExit());
        });

        button2.setOnAction(actionEvent -> {
            setRootRight(createPersonalInfoIfNotExit());
        });

        return leftMenu;
    }


    /*
    * Create a new ScrollableActivityCardPagination if it does not exist
     */
    public ScrollableActivityCardPagination createScrollableActivityCardPaginationIfNotExit() {
        if(scrollableActivityCardPagination == null){
            scrollableActivityCardPagination = new ScrollableActivityCardPagination();
            scrollableActivityCardPagination.setOnActivityClicked(new OnActivitySelectedHandler(this));
        }

        return scrollableActivityCardPagination;
    }

    public PersonInfo createPersonalInfoIfNotExit(){
        if(personInfo == null){
            personInfo = new PersonInfo();
        }
        return personInfo;
    }

    public void  setHome(){
        root.setCenter(createScrollableActivityCardPaginationIfNotExit());
    }

    /*
    * Set the right node of the root
     */
    public void setRootRight(Node node){
        root.setCenter(node);
    }

}

