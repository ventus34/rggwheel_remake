package ventus.rggwheel.services.save;

import ventus.rggwheel.model.SaveState;

import java.io.*;

public class SaveStateService {
    private SaveState currentState;

    public SaveStateService() {
        currentState = new SaveState();
        loadState();
    }

    public SaveState getCurrentState() {
        return currentState;
    }


    public void loadState() {
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/saves/" +System.getProperty("user.name") + ".save");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.currentState = (SaveState) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.err.println("Save state not found, creating new one");
            saveState();
        } catch (ClassNotFoundException c) {
            System.err.println("Save state class not found");
            c.printStackTrace();
        }
    }

    public void saveState() {
        try {
            File saveFile = new File(System.getProperty("user.dir") + "/saves/" + System.getProperty("user.name") + ".save");
            FileOutputStream fileOut = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(currentState);
            out.close();
            fileOut.close();
            System.out.println("Progress saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
