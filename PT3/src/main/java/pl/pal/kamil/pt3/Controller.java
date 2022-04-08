package pl.pal.kamil.pt3;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    @FXML
    public AnchorPane rootAnchorPane;
    @FXML
    private Label statusLabel;
    @FXML
    private ProgressBar progressBar;
    private
    ExecutorService executor = Executors.newSingleThreadExecutor();


    @FXML
    public void ButtonOnAction(ActionEvent actionEvent)
    {
        Stage stage = (Stage) rootAnchorPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter fileExts = new FileChooser.ExtensionFilter("Graphics","*.jpg");
        fileChooser.getExtensionFilters().add(fileExts);

        File file = fileChooser.showOpenDialog(stage);

        Task sendFileTask = new SendFileTask(file); //klasa zadania
        statusLabel.textProperty().bind(sendFileTask.messageProperty());
        progressBar.progressProperty().bind(sendFileTask.progressProperty());
        executor.submit(sendFileTask);

    }
}
