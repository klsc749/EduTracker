import javafx.application.Application;
import view.ExtraCurriculumHome;
import view.Home;
import view.PersonHome;
import view.PersonInfo;


public class Main {
    public static void main(String[] args) {
        //Application.launch(Home.class, args);
        Application.launch(PersonHome.class, args);
        //Application.launch(ExtraCurriculumHome.class, args);
    }
}