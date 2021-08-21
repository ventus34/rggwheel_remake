package ventus.rggwheel.utils;

import javafx.scene.layout.Pane;
import ventus.rggwheel.model.RetroBoyColorEnum;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

@SuppressWarnings("SyntaxError")
public class ColorUtils {
    private static final ArrayList<RetroBoyColorEnum> retroBoyColors = new ArrayList<>(EnumSet.allOf(RetroBoyColorEnum.class));
    private static int currentColorCounter = 0;
    private static final int allColorsNumber = retroBoyColors.size();

    private static final Random random = new Random();


    public static void changeBackgroundColor(Pane pane){
        String color;
        if(currentColorCounter < allColorsNumber - 1){
            color = retroBoyColors.get(++currentColorCounter).getColor();
        } else if (currentColorCounter == allColorsNumber - 1){
            currentColorCounter = 0;
            color = retroBoyColors.get(currentColorCounter).getColor();
        } else{
            throw new IllegalStateException();
        }
        pane.setStyle("-fx-background-color: " + color + ";");
    }

    public static void changeToRandomColor(Pane pane){
        String color;
        int randomColorHex = random.nextInt(0xffffff + 1);
        color = String.format("#%06x", randomColorHex);
        pane.setStyle("-fx-background-color: " + color + ";");
    }

}
