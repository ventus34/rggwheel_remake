package ventus.rggwheel.services.retroBoy;

import ventus.rggwheel.RetroBoyModesEnum;
import ventus.rggwheel.controllers.BackgroundController;
import ventus.rggwheel.controllers.FXMLController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransitionManagerService {
    private final Map<RetroBoyModesEnum, ArrayList<FXMLController>> availableScenes;
    private final BackgroundController backgroundController;
    private RetroBoyModesEnum currentMode;
    private int currentScene;
    private final int numberOfScenes;

    private final int splashScreen = 0;
    private final int wheelScene = 1;
    private final int prizeDescScene = 2;

    public TransitionManagerService(ArrayList<FXMLController> monoScenes, ArrayList<FXMLController> colorScenes, BackgroundController backgroundController) {
        Map<RetroBoyModesEnum, ArrayList<FXMLController>> scenes = new HashMap<>();
        scenes.put(RetroBoyModesEnum.MONO, monoScenes);
        scenes.put(RetroBoyModesEnum.COLOR, colorScenes);
        this.availableScenes = scenes;
        this.currentMode = RetroBoyModesEnum.MONO;
        this.currentScene = 0;
        this.backgroundController = backgroundController;
        if (monoScenes.size() == colorScenes.size()) {
            this.numberOfScenes = monoScenes.size();
        } else {
            throw new IllegalStateException("Number of scenes for each RetroBoy version have to be the same");
        }
    }

    public void switchScene() {
        if (this.currentScene == splashScreen) {
            availableScenes.get(currentMode).get(this.currentScene).hide();
            this.currentScene = wheelScene;
        }
        availableScenes.get(currentMode).get(this.currentScene).hide();
        if (this.currentScene < numberOfScenes - wheelScene) {
            availableScenes.get(currentMode).get(++this.currentScene).show();
        } else if (this.currentScene == numberOfScenes - wheelScene) {
            this.currentScene = prizeDescScene;
            availableScenes.get(currentMode).get(this.currentScene).show();
        } else {
            throw new IllegalStateException("Scene doesnt exist");
        }


    }

    public void switchMode() {
        backgroundController.switchBackground();
        if (currentMode == RetroBoyModesEnum.MONO) {
            currentMode = RetroBoyModesEnum.COLOR;
            availableScenes.get(RetroBoyModesEnum.MONO).get(currentScene).hide();
            availableScenes.get(RetroBoyModesEnum.COLOR).get(currentScene).show();
        } else {
            currentMode = RetroBoyModesEnum.MONO;
            availableScenes.get(RetroBoyModesEnum.COLOR).get(currentScene).hide();
            availableScenes.get(RetroBoyModesEnum.MONO).get(currentScene).show();
        }
    }

    public boolean checkAndBack() {
        boolean isCheckScene;
        if (currentScene == prizeDescScene) {
            availableScenes.get(currentMode).get(currentScene).hide();
            currentScene = wheelScene;
            availableScenes.get(currentMode).get(currentScene).show();
            isCheckScene = true;
        } else {
            availableScenes.get(currentMode).get(currentScene).hide();
            currentScene = prizeDescScene;
            availableScenes.get(currentMode).get(currentScene).show();
            isCheckScene = false;
        }
        return isCheckScene;
    }

    public boolean isInventory(){
        return currentScene == 3;
    }

    public RetroBoyModesEnum getCurrentMode() {
        return currentMode;
    }
}
