package view;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import service.ModuleService;
import service.StudentService;

public class Export extends VBox {
    private Button exportModuleInfoButton;
    private Button exportCVButton;
    private DirectoryChooser directoryChooser;
    private ModuleService moduleService = new ModuleService();
    private StudentService studentService = new StudentService();
    private final String exportModuleInfoFileName = "moduleInfo.pdf";

    public Export() {
        this.setSpacing(10);
        this.setPadding(new Insets(5, 20, 10, 20));
        getAlignment();
        this.setAlignment(Pos.CENTER);

        directoryChooser = new DirectoryChooser();
        exportModuleInfoButton = new Button("Export Module Info");
        exportModuleInfoButton.setOnAction(event -> {
            handleExportModuleInfoButton();
        });
        
        exportCVButton = new Button("Export CV");
        exportCVButton.setOnAction(event -> {
            handleExportCVButton();
        });

        this.getChildren().addAll(exportModuleInfoButton, exportCVButton);
    }

    private void handleExportModuleInfoButton() {
        directoryChooser.setTitle("Select Directory");
        File file = directoryChooser.showDialog(this.getScene().getWindow());
        if (file != null) {
            System.out.println(file.getAbsolutePath());
            exportModuleInfo(file.getAbsolutePath());
        }else{
            throw new RuntimeException("No directory selected");
        }
    }

    private void handleExportCVButton() {
        directoryChooser.setTitle("Select Directory");
        File file = directoryChooser.showDialog(this.getScene().getWindow());
        if (file != null) {
            System.out.println(file.getAbsolutePath());
            try {
                studentService.ExportCV(file.getAbsolutePath());
            } catch (Exception e) {
                throw new RuntimeException("Export CV failed");
            }
        }else{
            throw new RuntimeException("No directory selected");
        }
    }

    private void exportModuleInfo(String path) {
        path = path + System.getProperty("file.separator") + exportModuleInfoFileName;
        moduleService.exportModuleInfo(path);
    }

}
