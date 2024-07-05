package ui;

import javax.swing.*;

import game.Card;

import java.awt.*;

public class GameFrame {
	private JFrame frame;
    private GamePanel gamePanel;

    public GameFrame(GameController controller) {
        setTitle("War Card Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new MenuBar(controller));
        gamePanel = new GamePanel(controller);
        add(gamePanel);
    }

    public void updateGameView(Card player1Card, Card player2Card, String result, int player1Score, int player2Score) {
        gamePanel.updateGameView(player1Card, player2Card, result, player1Score, player2Score);
    }
}
