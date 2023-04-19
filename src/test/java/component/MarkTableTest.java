package component;

import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import model.Mark;
import model.MarkItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class MarkTableTest {
    List<MarkItem> markItems = new ArrayList<>();
    MarkTable markTable;
    @Start
    public void start(Stage stage) throws Exception {
        markItems.add(new MarkItem("Item 1", 80.0, 0.4));
        markItems.add(new MarkItem("Item 2", 90.0, 0.6));

        markTable = new MarkTable((Mark) markItems);

        stage.setScene(new Scene(markTable));
        stage.show();
    }

    @Test
    public void test(FxRobot robot){
        List<TableCell> tableCells = (List<TableCell>) robot.lookup(".table-cell").queryAllAs(TableCell.class);

        // Test the table contents
        assertEquals("Item 1", tableCells.get(0).getText());
        assertEquals("80.0", tableCells.get(1).getText());
        assertEquals("0.4", tableCells.get(2).getText());

        assertEquals("Item 2", tableCells.get(3).getText());
        assertEquals("90.0", tableCells.get(4).getText());
        assertEquals("0.6", tableCells.get(5).getText());
    }
}
