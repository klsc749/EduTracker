package component.personalInfo;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class AwardsListPane extends ListView<String> {

    public AwardsListPane(ObservableList<String> awards) {
        setItems(awards);
    }
}


