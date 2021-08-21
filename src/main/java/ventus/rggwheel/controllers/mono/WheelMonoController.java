package ventus.rggwheel.controllers.mono;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ventus.rggwheel.controllers.WheelController;
import ventus.rggwheel.utils.WheelUtils;

import java.util.ArrayList;

public class WheelMonoController extends WheelController {
    @FXML private Label spinTime;
    @FXML private ImageView wheelMono;
    @FXML private AnchorPane monoPane;

    @FXML private Label prizeLabel_mono1;
    @FXML private Label prizeLabel_mono2;
    @FXML private Label prizeLabel_mono3;
    @FXML private Label prizeLabel_mono4;
    @FXML private Label prizeLabel_mono5;
    @FXML private Label prizeLabel_mono6;
    @FXML private Label prizeLabel_mono7;
    @FXML private Label prizeLabel_mono8;
    @FXML private Label prizeLabel_mono9;
    @FXML private Label prizeLabel_mono10;
    @FXML private Label prizeLabel_mono11;
    @FXML private Label prizeLabel_mono12;
    @FXML private Label prizeLabel_mono13;
    @FXML private Label prizeLabel_mono14;
    @FXML private Label prizeLabel_mono15;
    @FXML private Label prizeLabel_mono16;
    @FXML private Label prizeLabel_mono17;
    @FXML private Label prizeLabel_mono18;

    @FXML void initialize(){
        monoPane.setRotate(monoPane.getRotate() + WheelUtils.getAngle()/2 + 7200);

        prizesList.add(prizeLabel_mono1);
        prizesList.add(prizeLabel_mono2);
        prizesList.add(prizeLabel_mono3);
        prizesList.add(prizeLabel_mono4);
        prizesList.add(prizeLabel_mono5);
        prizesList.add(prizeLabel_mono6);
        prizesList.add(prizeLabel_mono7);
        prizesList.add(prizeLabel_mono8);
        prizesList.add(prizeLabel_mono9);
        prizesList.add(prizeLabel_mono10);
        prizesList.add(prizeLabel_mono11);
        prizesList.add(prizeLabel_mono12);
        prizesList.add(prizeLabel_mono13);
        prizesList.add(prizeLabel_mono14);
        prizesList.add(prizeLabel_mono15);
        prizesList.add(prizeLabel_mono16);
        prizesList.add(prizeLabel_mono17);
        prizesList.add(prizeLabel_mono18);
    }

    @Override
    public void spinWheel(){
        String label = spinTime.getText();
        Duration duration = Duration.seconds(Integer.parseInt(label));
        spin(duration, monoPane, WheelUtils.getRandomAngle(monoPane.getRotate(), Integer.parseInt(label)));
    }

    @Override
    public void setRotate(Double angle) {
        monoPane.setRotate(angle);
    }

    @Override
    public void moveToNext() {
        this.goToNext(monoPane);
    }

    @Override
    public void moveToPrevious() {
        this.goBack(monoPane);
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
        goToRandomPrize(monoPane);
        setRandomTime();
    }

}
