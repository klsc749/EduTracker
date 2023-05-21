package component.personalInfo;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * The AwardsListPane class represents a custom ListView component that displays a list of awards.
 * It extends the ListView class and provides a constructor to set the items of the ListView.
 */
public class AwardsListPane extends ListView<String> {
    /**
     * Constructs a new AwardsListPane with the specified awards.
     *
     * @param awards The observable list of awards to be displayed in the ListView.
     */
    public AwardsListPane(ObservableList<String> awards) {
        setItems(awards);
    }
}


