package game;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveGame {
    public static void saveGame(WarGame game) {
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
}
