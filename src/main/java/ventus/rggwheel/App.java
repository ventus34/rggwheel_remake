package ventus.rggwheel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ventus.rggwheel.services.retroBoy.RetroBoySizeHandlerService;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("fonts/BitCell.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("fonts/emulogic.ttf"), 14);
        scene = new Scene(loadFXML("retroBoy"));
        stage.setScene(scene);
        stage.show();
        RetroBoySizeHandlerService.resizeHandler(scene);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}