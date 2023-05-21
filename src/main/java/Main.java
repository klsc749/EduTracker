import handler.GlobalExceptionHandler;
import javafx.application.Application;
import view.Home;

public class Main {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
        Application.launch(Home.class, args);
    }
}