package ui;

import javax.swing.*;

public class MenuBar {
	private JMenuBar menuBar;
    public MenuBar(GameController controller) {
        JMenu menu = new JMenu("File");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> controller.newGame());
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(e -> controller.saveGame());
        JMenuItem openGame = new JMenuItem("Open Game");
        openGame.addActionListener(e -> controller.openGame());

        menu.add(newGame);
        menu.add(saveGame);
        menu.add(openGame);
        add(menu);
    }
}
