package pl.pal.kamil.pt3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        stage.setScene(new Scene(loadFXML()));
        stage.show();
    }

    private static Parent loadFXML() throws IOException {
        final var url = Main.class.getResource("/config.fxml");
        final var loader = new FXMLLoader(url);

        return loader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}