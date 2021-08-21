package ventus.rggwheel.controllers.color;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ventus.rggwheel.controllers.WheelController;
import ventus.rggwheel.utils.WheelUtils;

public class WheelColorController extends WheelController {
    @FXML private Label spinTime;
    @FXML private ImageView wheelColor;
    @FXML private AnchorPane colorPane;

    @FXML private Label prizeLabel_color1;
    @FXML private Label prizeLabel_color2;
    @FXML private Label prizeLabel_color3;
    @FXML private Label prizeLabel_color4;
    @FXML private Label prizeLabel_color5;
    @FXML private Label prizeLabel_color6;
    @FXML private Label prizeLabel_color7;
    @FXML private Label prizeLabel_color8;
    @FXML private Label prizeLabel_color9;
    @FXML private Label prizeLabel_color10;
    @FXML private Label prizeLabel_color11;
    @FXML private Label prizeLabel_color12;
    @FXML private Label prizeLabel_color13;
    @FXML private Label prizeLabel_color14;
    @FXML private Label prizeLabel_color15;
    @FXML private Label prizeLabel_color16;
    @FXML private Label prizeLabel_color17;
    @FXML private Label prizeLabel_color18;

    @FXML void initialize(){
        colorPane.setRotate(colorPane.getRotate() + WheelUtils.getAngle()/2 + 7200);
        prizesList.add(prizeLabel_color1);
        prizesList.add(prizeLabel_color2);
        prizesList.add(prizeLabel_color3);
        prizesList.add(prizeLabel_color4);
        prizesList.add(prizeLabel_color5);
        prizesList.add(prizeLabel_color6);
        prizesList.add(prizeLabel_color7);
        prizesList.add(prizeLabel_color8);
        prizesList.add(prizeLabel_color9);
        prizesList.add(prizeLabel_color10);
        prizesList.add(prizeLabel_color11);
        prizesList.add(prizeLabel_color12);
        prizesList.add(prizeLabel_color13);
        prizesList.add(prizeLabel_color14);
        prizesList.add(prizeLabel_color15);
        prizesList.add(prizeLabel_color16);
        prizesList.add(prizeLabel_color17);
        prizesList.add(prizeLabel_color18);
    }

    @Override
    public void setRotate(Double angle) {
        colorPane.setRotate(angle);
    }

    @Override
    public void spinWheel(){
        String label = spinTime.getText();
        Duration duration = Duration.seconds(Integer.parseInt(label));
        spin(duration, colorPane, WheelUtils.getRandomAngle(colorPane.getRotate(), Integer.parseInt(label)));
    }

    @Override
    public void moveToNext() {
        this.goToNext(colorPane);
    }

    @Override
    public void moveToPrevious() {
        this.goBack(colorPane);
    }

    @Override
    public Label getSpinTime() {
        return spinTime;
    }

    @Override
    public void setSpinTime(Integer spinTime) {
        this.spinTime.setText(spinTime.toString());
    }

    @Override
    public void randomizer() {
        goToRandomPrize(colorPane);
        setRandomTime();
    }

}
