package ui;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar {
    public MenuBar(GameController controller) {
        JMenu menu = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> controller.newGame());
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(e -> controller.saveGame());
        JMenuItem openGame = new JMenuItem("Open Game");
        openGame.addActionListener(e -> controller.openGame());

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> showAboutDialog());

        menu.add(newGame);
        menu.add(saveGame);
        menu.add(openGame);
        menu.add(about);
        add(menu);
    }

    private void showAboutDialog() {
        JDialog aboutDialog = new JDialog();
        aboutDialog.setTitle("About");
        aboutDialog.setSize(400, 300);
        aboutDialog.setLayout(new GridLayout(6, 1));

        String[] names = {
            "Yared Iesse Bustillo Medina",
            "Jonathan David Concha Matas",
            "Josue David Pavon Maldonado",
            "Julia Correira Bindi",
            "Bruna Perry Pierobon",
            "Sagar Bhusal"
        };

        for (String name : names) {
            JLabel label = new JLabel(name, SwingConstants.CENTER);
            aboutDialog.add(label);
        }

        aboutDialog.setVisible(true);
    }
}
