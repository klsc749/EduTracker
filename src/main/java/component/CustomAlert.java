package component;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;

public class CustomAlert extends Alert {

    public CustomAlert(AlertType alertType) {
        super(alertType);

        // Apply styles to the dialog pane
        DialogPane dialogPane = this.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 10px; -fx-font-family: 'Arial';");
    }


    public void setCustomHeaderText(String headerText) {
        super.setHeaderText(headerText);

        // Apply styles to the header
        Label headerLabel = (Label) this.getDialogPane().lookup(".header");
        if (headerLabel != null) {
            headerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #3F3F3F;");
        }
    }

    public void setCustomContentText(String contentText) {
        super.setContentText(contentText);

        // Apply styles to the content
        Label contentLabel = (Label) this.getDialogPane().lookup(".content");
        if (contentLabel != null) {
            contentLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #3F3F3F;");
        }
    }
}
