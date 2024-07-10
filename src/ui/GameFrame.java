package ui;

import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame(GameController controller) {
        setTitle("Card Game");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel(controller);
        add(gamePanel);

        setJMenuBar(new MenuBar(controller));
        setVisible(true);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
