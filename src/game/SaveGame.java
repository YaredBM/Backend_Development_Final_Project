package game;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Handles saving the game state to a file.
 */
public class SaveGame {
    /**
     * Saves the game state to a file selected by the user.
     * @param game The game to save.
     */
    public void saveGame(WarGame game) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()))) {
                oos.writeObject(game);
                JOptionPane.showMessageDialog(null, "Game saved successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error saving game: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints information about the save game.
     */
    public void printInfo() {
        System.out.println("Save Game Info:");
    }
}
