package game;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class LoadGame {
    public static WarGame loadGame() {
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
}
