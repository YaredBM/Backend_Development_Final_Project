package game;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Handles loading the game state from a file.
 */
public class LoadGame {
    /**
     * Loads the game state from a file selected by the user.
     * @return The loaded game state.
     */
    public WarGame loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a saved game file");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
                WarGame game = (WarGame) ois.readObject();
                JOptionPane.showMessageDialog(null, "Game loaded successfully!");
                return game;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading game: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * Prints information about the load game.
     */
    public void printInfo() {
        System.out.println("Load Game Info:");
    }
}
