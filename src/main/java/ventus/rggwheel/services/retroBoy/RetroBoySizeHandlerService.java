package ventus.rggwheel.services.retroBoy;

import javafx.scene.Node;
import javafx.scene.Scene;

public class RetroBoySizeHandlerService {

    private static final int width = 1480;
    private static final int height = 1035;

    public static void resizeHandler(Scene window) {
        Node mainNode = getMainNode(window);
        double windowWidth = window.widthProperty().doubleValue();
        double windowHeight = window.heightProperty().doubleValue();
        window.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            mainNode.setTranslateX(-((width - newSceneWidth.doubleValue()) / 2));
            mainNode.setScaleX(newSceneWidth.doubleValue() / windowWidth);
        });
        window.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            mainNode.setScaleY(newSceneHeight.doubleValue() / windowHeight);
            mainNode.setTranslateY(-((height - newSceneHeight.doubleValue()) / 2));
            mainNode.setLayoutY(0);
        });
    }

    private static Node getMainNode(Scene window){
         return window.getRoot().getChildrenUnmodifiable().get(0);
    }
}
