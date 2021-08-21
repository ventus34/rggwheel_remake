package ventus.rggwheel.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ventus.rggwheel.RetroBoyModesEnum;
import ventus.rggwheel.model.PrizeEnum;
import ventus.rggwheel.model.SaveState;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.services.retroBoy.TransitionManagerService;
import ventus.rggwheel.controllers.color.*;
import ventus.rggwheel.controllers.mono.*;
import ventus.rggwheel.services.save.SaveStateService;
import ventus.rggwheel.services.wheel.PrizesService;
import ventus.rggwheel.utils.ColorUtils;
import ventus.rggwheel.utils.WheelUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Main Retro Boy controller
 * <p>Nested controllers names have to use this pattern: fxmlElementName + Controller</p>
 * <p>Example: @FXML AnchorPane example; @FXML ExampleController exampleController</p>
 * <p>This is the easiest way to ensure that controllers instances will be bind
 * to main controller during initialization
 * </p>
 */
public class RetroBoyController {
    @FXML
    private AnchorPane retroBoyPane;
    @FXML
    private ImageView backgroundMono;
    @FXML
    private ImageView backgroundColor;

    @FXML
    private Button speedUp;
    @FXML
    private Button speedDown;
    @FXML
    private Button timeUp;
    @FXML
    private Button timeDown;
    @FXML
    private Button startButton;
    @FXML
    private Button color;
    @FXML
    private Button check;
    @FXML
    private Button colorMode;
    @FXML
    private Button spinButton;

    private Set<Button> buttons;

    //Inventory
    @FXML
    private Pane inventoryMono;
    @FXML
    private InventoryMonoController inventoryMonoController;
    @FXML
    private Pane inventoryColor;
    @FXML
    private InventoryColorController inventoryColorController;

    //Splash screen
    @FXML
    private AnchorPane splashMono;
    @FXML
    private AnchorPane splashColor;
    @FXML
    private SplashColorController splashColorController;
    @FXML
    private SplashMonoController splashMonoController;

    //Statistics
    @FXML
    private Pane statisticsColor;
    @FXML
    private StatisticsColorController statisticsColorController;
    @FXML
    private Pane statisticsMono;
    @FXML
    private StatisticsMonoController statisticsMonoController;

    //Prizes descriptions
    @FXML
    private AnchorPane prizeDescriptionColor;
    @FXML
    private PrizeDescriptionColorController prizeDescriptionColorController;
    @FXML
    private AnchorPane prizeDescriptionMono;
    @FXML
    private PrizeDescriptionMonoController prizeDescriptionMonoController;

    //Prizes history
    @FXML
    private Pane prizesHistoryColor;
    @FXML
    private PrizesHistoryColorController prizesHistoryColorController;
    @FXML
    private Pane prizesHistoryMono;
    @FXML
    private PrizesHistoryMonoController prizesHistoryMonoController;

    //Wheel
    @FXML
    private AnchorPane wheelMono;
    @FXML
    private WheelMonoController wheelMonoController;
    @FXML
    private AnchorPane wheelColor;
    @FXML
    private WheelColorController wheelColorController;

    private TransitionManagerService transitionManagerService;
    private final MediaPlayerService mediaPlayerService = new MediaPlayerService();
    private final PrizesService prizesService = new PrizesService();
    private SaveStateService saveStateService;
    private boolean isAfterSplashScreen = false;

    public void initialize() {
        //Binding root panes
        splashMonoController.setMainPane(splashMono);
        splashColorController.setMainPane(splashColor);
        inventoryMonoController.setMainPane(inventoryMono);
        inventoryColorController.setMainPane(inventoryColor);
        statisticsMonoController.setMainPane(statisticsMono);
        statisticsColorController.setMainPane(statisticsColor);
        prizeDescriptionMonoController.setMainPane(prizeDescriptionMono);
        prizeDescriptionColorController.setMainPane(prizeDescriptionColor);
        prizesHistoryMonoController.setMainPane(prizesHistoryMono);
        prizesHistoryColorController.setMainPane(prizesHistoryColor);
        wheelMonoController.setMainPane(wheelMono);
        wheelColorController.setMainPane(wheelColor);

        splashMonoController.setRetroBoy(this);
        splashColorController.setRetroBoy(this);
        inventoryMonoController.setRetroBoy(this);
        inventoryColorController.setRetroBoy(this);
        statisticsMonoController.setRetroBoy(this);
        statisticsColorController.setRetroBoy(this);
        prizeDescriptionMonoController.setRetroBoy(this);
        prizeDescriptionColorController.setRetroBoy(this);
        prizesHistoryMonoController.setRetroBoy(this);
        prizesHistoryColorController.setRetroBoy(this);
        wheelMonoController.setRetroBoy(this);
        wheelColorController.setRetroBoy(this);

        //Binding controllers to transitionManager;
        //Order of controllers in list defines, transitions order;
        ArrayList<FXMLController> colorScenesControllers = new ArrayList<>();
        colorScenesControllers.add(splashColorController);
        colorScenesControllers.add(prizeDescriptionColorController);
        colorScenesControllers.add(wheelColorController);
        colorScenesControllers.add(inventoryColorController);
//        colorScenesControllers.add(prizesHistoryColorController);
//        colorScenesControllers.add(statisticsColorController);

        ArrayList<FXMLController> monoScenesControllers = new ArrayList<>();
        monoScenesControllers.add(splashMonoController);
        monoScenesControllers.add(prizeDescriptionMonoController);
        monoScenesControllers.add(wheelMonoController);
        monoScenesControllers.add(inventoryMonoController);
//        monoScenesControllers.add(prizesHistoryMonoController);
//        monoScenesControllers.add(statisticsMonoController);

        BackgroundController backgroundController = new BackgroundController(backgroundMono, backgroundColor);

        transitionManagerService = new TransitionManagerService(monoScenesControllers, colorScenesControllers, backgroundController);
        wheelColorController.setMediaPlayerService(mediaPlayerService);
        wheelMonoController.setMediaPlayerService(mediaPlayerService);
        wheelColorController.setPrizesService(prizesService);
        wheelMonoController.setPrizesService(prizesService);
        wheelColorController.setOppositeModeController(wheelMonoController);
        wheelMonoController.setOppositeModeController(wheelColorController);
        inventoryColorController.setOppositeModeController(inventoryMonoController);
        inventoryColorController.setMediaPlayerService(mediaPlayerService);
        inventoryMonoController.setOppositeModeController(inventoryColorController);
        inventoryMonoController.setMediaPlayerService(mediaPlayerService);

        buttons = new HashSet<>();
        buttons.add(speedUp);
        buttons.add(speedDown);
        buttons.add(timeUp);
        buttons.add(timeDown);
        buttons.add(startButton);
        buttons.add(color);
        buttons.add(check);
        buttons.add(colorMode);
        buttons.add(spinButton);

        saveStateService = new SaveStateService();
        inventoryMonoController.setHintLabel();
        inventoryColorController.setHintLabel();
        inventoryMonoController.setRerollLabel();
        inventoryColorController.setRerollLabel();
        unlockButtons();
        wheelMonoController.setLabels();
        wheelColorController.setLabels();
    }

    @FXML
    private void spinAction() {
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        if (transitionManagerService.getCurrentMode().equals(RetroBoyModesEnum.COLOR)) {
            wheelColorController.spinWheel();
        } else {
            wheelMonoController.spinWheel();
        }
    }

    @FXML
    private void moveUp() {
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        if (transitionManagerService.getCurrentMode().equals(RetroBoyModesEnum.COLOR)) {
            wheelColorController.moveToNext();
        } else {
            wheelMonoController.moveToNext();
        }
    }

    @FXML
    private void moveDown() {
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        if (transitionManagerService.getCurrentMode().equals(RetroBoyModesEnum.COLOR)) {
            wheelColorController.moveToPrevious();
        } else {
            wheelMonoController.moveToPrevious();
        }
    }

    @FXML
    private void timeUp() {
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        Integer time = Integer.valueOf(wheelColorController.getSpinTime().getText());
        wheelColorController.setSpinTime(++time);
        wheelMonoController.setSpinTime(time);

    }

    @FXML
    private void timeDown() {
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        Integer time = Integer.valueOf(wheelColorController.getSpinTime().getText());
        if (time > 1) {
            wheelColorController.setSpinTime(--time);
            wheelMonoController.setSpinTime(time);
        }
    }

    @FXML
    public void checkPrize() {
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        spinButton.setDisable(transitionManagerService.checkAndBack());
    }

    @FXML
    private void pickColor() {
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        ColorUtils.changeBackgroundColor(retroBoyPane);
    }

    @FXML
    private void start() {
        transitionManagerService.switchScene();
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        if(!isAfterSplashScreen){
            initWhenRetroBoyVisible();
        }
        spinButton.setDisable(false);
        isAfterSplashScreen = true;
    }

    private void initWhenRetroBoyVisible() {
        setKeyListeners();
    }

    @FXML
    private void switchMode() {
        transitionManagerService.switchMode();
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
    }

    public SaveState getProgress() {
        return saveStateService.getCurrentState();
    }

    public void save() {
        saveStateService.saveState();
    }

    public boolean isInventory(){
        return transitionManagerService.isInventory();
    }

    void lockButtons() {
        buttons.forEach(button -> button.setDisable(true));
    }

    void unlockButtons() {
        buttons.forEach(button -> button.setDisable(false));
    }

    public void setPrizeDesc(PrizeEnum indicatedPrize) {
        prizeDescriptionColorController.setDescription(indicatedPrize.getName(), indicatedPrize.getDescription());
        prizeDescriptionMonoController.setDescription(indicatedPrize.getName(), indicatedPrize.getDescription());
    }

    private void setKeyListeners() {
        Scene currentScene = retroBoyPane.getScene();
        currentScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R) {
                if(WheelUtils.getRandomBoolean()){
                    transitionManagerService.switchMode();
                }
                ColorUtils.changeToRandomColor(retroBoyPane);
                switch (transitionManagerService.getCurrentMode()) {
                    case MONO:
                        wheelMonoController.randomizer();
                        break;
                    case COLOR:
                        wheelColorController.randomizer();
                        break;
                }
                prizesService.shufflePrizes();
                wheelColorController.setLabels();
                wheelMonoController.setLabels();
            }
        });
    }

    public void updateInventory() {
        inventoryMonoController.setRerollLabel();
        inventoryColorController.setRerollLabel();
        inventoryMonoController.setHintLabel();
        inventoryColorController.setHintLabel();
    }
}
