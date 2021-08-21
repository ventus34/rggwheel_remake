package ventus.rggwheel.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveState implements Serializable {
    Map<ItemEnum, Integer> inventory;
    List<PrizeEnum> prizesHistory;

    public SaveState() {
        this.inventory = new HashMap<>();
        inventory.put(ItemEnum.Potion,0);
        inventory.put(ItemEnum.Hints,0);
        this.prizesHistory = new ArrayList<>();
    }

    public Map<ItemEnum, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<ItemEnum, Integer> inventory) {
        this.inventory = inventory;
    }

    public List<PrizeEnum> getPrizesHistory() {
        return prizesHistory;
    }

    public void setPrizesHistory(List<PrizeEnum> prizesHistory) {
        this.prizesHistory = prizesHistory;
    }
}
