package ui;

import java.awt.GridLayout;

import javax.swing.*;

/**
 * Creates the menu bar with options for starting a new game, saving the game, opening a saved game, and displaying an about dialog.
 */
public class MenuBar extends JMenuBar {
    /**
     * Constructor to initialize the menu bar with the specified controller.
     * @param controller The game controller.
     */
    public MenuBar(GameController controller) {
        JMenu menu = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> controller.newGame());
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(e -> controller.saveGame());
        JMenuItem openGame = new JMenuItem("Open Game");
        openGame.addActionListener(e -> controller.openGame());
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> showAboutDialog());

        menu.add(newGame);
        menu.add(saveGame);
        menu.add(openGame);
        menu.add(exit);
        menu.add(about);
        add(menu);
    }

    /**
     * Displays an about dialog with the names of the developers.
     */
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

    /**
     * Prints information about the menu bar.
     */
    public void printInfo() {
        System.out.println("Menu Bar Info:");
        for (int i = 0; i < getMenuCount(); i++) {
            System.out.println("Menu: " + getMenu(i).getText());
        }
    }
}
