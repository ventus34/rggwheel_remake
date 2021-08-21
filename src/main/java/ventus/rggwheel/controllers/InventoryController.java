package ventus.rggwheel.controllers;

import ventus.rggwheel.model.ItemEnum;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.services.spreadsheet.GoogleFormsPostService;

import java.util.Map;

public abstract class InventoryController extends FXMLController {
    private MediaPlayerService mediaPlayerService;
    private InventoryController oppositeModeController;

    public InventoryController getOppositeModeController() {
        return oppositeModeController;
    }

    public void setOppositeModeController(InventoryController oppositeModeController) {
        this.oppositeModeController = oppositeModeController;
    }

    public void setMediaPlayerService(MediaPlayerService mediaPlayerService) {
        this.mediaPlayerService = mediaPlayerService;
    }

    public MediaPlayerService getMediaPlayerService() {
        return mediaPlayerService;
    }

    public void add(ItemEnum item) {
        if(retroBoy.isInventory()) {
            Map<ItemEnum, Integer> inventory = retroBoy.getProgress().getInventory();
            inventory.replace(item, inventory.get(item) + 1);
            System.out.println("Added 1 " + item.getDescription() + " Hints: " + inventory.get(ItemEnum.Hints) + "; Potions: " + inventory.get(ItemEnum.Potion));
            retroBoy.save();
        }
    }

    public void substract(ItemEnum item) {
        if(retroBoy.isInventory()) {
            Map<ItemEnum, Integer> inventory = retroBoy.getProgress().getInventory();
            inventory.replace(item, inventory.get(item) - 1);
            System.out.println("Substracted 1 " + item.getDescription() + " Hints: " + inventory.get(ItemEnum.Hints) + "; Potions: " + inventory.get(ItemEnum.Potion));
            GoogleFormsPostService.savePrizeToSpreadsheet("Used " + item.getDescription(), "Hints: " + inventory.get(ItemEnum.Hints) + "; Potions: " + inventory.get(ItemEnum.Potion));
            retroBoy.save();
        }
    }

    abstract public void setRerollLabel();

    abstract public void setHintLabel();

}
