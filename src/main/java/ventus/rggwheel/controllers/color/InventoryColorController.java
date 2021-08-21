package ventus.rggwheel.controllers.color;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ventus.rggwheel.controllers.InventoryController;
import ventus.rggwheel.model.ItemEnum;
import ventus.rggwheel.services.audio.MediaPlayerService;

public class InventoryColorController extends InventoryController {
    @FXML
    private Label hintsAmount;

    @FXML
    private Label rerollPotionAmount;

    @FXML
    public void addHint() {
        getMediaPlayerService().play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        add(ItemEnum.Hints);
        setHintLabel();
        getOppositeModeController().setHintLabel();
    }

    @FXML
    public void useHint() {
        getMediaPlayerService().play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        substract(ItemEnum.Hints);
        setHintLabel();
        getOppositeModeController().setHintLabel();
    }


    @FXML
    public void addReroll() {
        getMediaPlayerService().play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        add(ItemEnum.Potion);
        setRerollLabel();
        getOppositeModeController().setRerollLabel();
    }

    @FXML
    public void useReroll() {
        getMediaPlayerService().play(MediaPlayerService.AudioPlayerEnum.BUTTON, null);
        substract(ItemEnum.Potion);
        setRerollLabel();
        getOppositeModeController().setRerollLabel();
    }


    @Override
    public void setRerollLabel() {
        rerollPotionAmount.setText("Potions: " + retroBoy.getProgress().getInventory().get(ItemEnum.Potion));
    }

    @Override
    public void setHintLabel() {
        hintsAmount.setText("Hints: " + retroBoy.getProgress().getInventory().get(ItemEnum.Hints));
    }
}
