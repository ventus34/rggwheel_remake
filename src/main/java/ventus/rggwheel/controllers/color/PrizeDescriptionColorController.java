package ventus.rggwheel.controllers.color;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ventus.rggwheel.controllers.PrizeDescriptionController;

public class PrizeDescriptionColorController extends PrizeDescriptionController {

    @FXML
    private Label prizeLabel;

    @FXML
    private Label prizeDescription;

    @Override
    public void setDescription(String prize, String description) {
        prizeLabel.setText(prize);
        prizeDescription.setText(description);
    }
}
