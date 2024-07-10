package ui;

import javax.swing.*;

/**
 * Creates the main game window.
 */
public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    /**
     * Constructor to initialize the game frame with a game panel and menu bar.
     * @param controller The game controller.
     */
    public GameFrame(GameController controller) {
        setTitle("Card Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel(controller);
        add(gamePanel);

        setJMenuBar(new MenuBar(controller));
        setVisible(true);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Prints information about the game frame.
     */
    public void printInfo() {
        System.out.println("Game Frame Info:");
        System.out.println("Title: " + getTitle());
        System.out.println("Size: " + getSize());
    }
}
