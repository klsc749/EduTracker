package util;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Show an error dialog with the exception message
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An unexpected error occurred.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        });
    }
}
