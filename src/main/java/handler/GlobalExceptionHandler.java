package handler;

import component.CustomAlert;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Show an error dialog with the exception message
        Platform.runLater(() -> {

            CustomAlert customAlert = new CustomAlert(Alert.AlertType.ERROR);
            customAlert.setTitle("Error");
            customAlert.setCustomHeaderText("An unexpected error occurred.");
            customAlert.setCustomContentText(e.getMessage());
            customAlert.showAndWait();
        });
    }
}
