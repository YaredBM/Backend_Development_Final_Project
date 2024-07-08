package ui;

import game.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
    private GameController controller;
    private JLabel player1BackCardLabel, player2BackCardLabel;
    private JLabel player1FrontCardLabel, player2FrontCardLabel;
    private JLabel player1ScoreLabel, player2ScoreLabel;
    private JLabel resultLabel;
    private JButton playButton;

    public GamePanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        JPanel player1Panel = new JPanel(new BorderLayout());
        player1BackCardLabel = new JLabel(resizeImage("images/BackCard.jpg"));
        player1BackCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player1Panel.add(player1BackCardLabel, BorderLayout.NORTH);
        player1FrontCardLabel = new JLabel();
        player1FrontCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player1Panel.add(player1FrontCardLabel, BorderLayout.CENTER);
        topPanel.add(player1Panel);

        JPanel player2Panel = new JPanel(new BorderLayout());
        player2BackCardLabel = new JLabel(resizeImage("images/BackCard.jpg"));
        player2BackCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player2Panel.add(player2BackCardLabel, BorderLayout.NORTH);
        player2FrontCardLabel = new JLabel();
        player2FrontCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player2Panel.add(player2FrontCardLabel, BorderLayout.CENTER);
        topPanel.add(player2Panel);

        add(topPanel, BorderLayout.CENTER);

        JPanel middlePanel = new JPanel(new BorderLayout());
        resultLabel = new JLabel("", SwingConstants.CENTER);
        middlePanel.add(resultLabel, BorderLayout.CENTER);

        add(middlePanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        player1ScoreLabel = new JLabel("Total score: 0", SwingConstants.CENTER);
        bottomPanel.add(player1ScoreLabel);
        playButton = new JButton("Play");
        playButton.addActionListener(e -> controller.playRound());
        bottomPanel.add(playButton);
        player2ScoreLabel = new JLabel("Total score: 0", SwingConstants.CENTER);
        bottomPanel.add(player2ScoreLabel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateGameView(Card player1Card, Card player2Card, String result, int player1Score, int player2Score) {
        player1FrontCardLabel.setIcon(resizeImage("images/" + getCardImageName(player1Card) + ".png"));
        player2FrontCardLabel.setIcon(resizeImage("images/" + getCardImageName(player2Card) + ".png"));
        resultLabel.setText(result);
        player1ScoreLabel.setText("Total score: " + player1Score);
        player2ScoreLabel.setText("Total score: " + player2Score);
    }

    private String getCardImageName(Card card) {
        String rank = "";
        switch (card.getRank()) {
            case 11: rank = "J"; break; // Jack
            case 12: rank = "Q"; break; // Queen
            case 13: rank = "K"; break; // King
            case 1 : rank = "A"; break; // Ace
            default: rank = String.valueOf(card.getRank()); break; // Number cards
        }

        String suitInitial = card.getSuit().substring(0, 1).toUpperCase();
        switch (suitInitial) {
            case "S": case "C": case "H": case "D": break; // Hearts and Diamonds stay the same
            default: throw new IllegalArgumentException("Unknown suit: " + suitInitial);
        }
        return rank + suitInitial;
    }

    private ImageIcon resizeImage(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image scaledImg = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImg);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
