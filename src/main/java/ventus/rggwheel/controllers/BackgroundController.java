package ventus.rggwheel.controllers;

import javafx.scene.image.ImageView;
import ventus.rggwheel.RetroBoyModesEnum;

public class BackgroundController {
    private final ImageView backgroundMono;
    private final ImageView backgroundColor;

    public BackgroundController(ImageView backgroundMono, ImageView backgroundColor) {
        this.backgroundMono = backgroundMono;
        this.backgroundColor = backgroundColor;
    }

    private RetroBoyModesEnum backgroundMode = RetroBoyModesEnum.MONO;

    public void switchBackground(){
        if(backgroundMode == RetroBoyModesEnum.MONO){
            backgroundMode = RetroBoyModesEnum.COLOR;
            backgroundMono.setOpacity(0);
            backgroundColor.setOpacity(100);
        } else {
            backgroundMode = RetroBoyModesEnum.MONO;
            backgroundMono.setOpacity(100);
            backgroundColor.setOpacity(0);
        }
    }

}
