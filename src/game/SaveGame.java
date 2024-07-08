package game;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SaveGame {
    private static final String SAVE_DIRECTORY = "saves/";

    static {
        File saveDir = new File(SAVE_DIRECTORY);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
    }

    public static void save(WarGame game, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_DIRECTORY + filename))) {
            out.writeObject(game);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    public static WarGame load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_DIRECTORY + filename))) {
            WarGame game = (WarGame) in.readObject();
            System.out.println("Game loaded successfully.");
            return game;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }

    public static String[] listSavedGames() {
        File saveDir = new File(SAVE_DIRECTORY);
        return saveDir.list((dir, name) -> name.endsWith(".dat"));
    }
}
