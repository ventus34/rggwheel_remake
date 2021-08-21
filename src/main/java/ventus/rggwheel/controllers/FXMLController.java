package ventus.rggwheel.controllers;

import javafx.scene.layout.Pane;

public abstract class FXMLController {

    protected Pane mainPane;

    protected RetroBoyController retroBoy;

    public void setRetroBoy(RetroBoyController retroBoy) {
        this.retroBoy = retroBoy;
    }

    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }

    public void show(){
        mainPane.setOpacity(100);
    }

    public void hide(){
        mainPane.setOpacity(0);
    }

}
