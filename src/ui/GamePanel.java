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
    private BufferedImage backgroundImage;

    public GamePanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("images/goldcasino.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setOpaque(false);

        JPanel player1Panel = new JPanel(new BorderLayout());
        player1Panel.setOpaque(false);
        player1BackCardLabel = new JLabel(new ImageIcon(resizeImage("images/BackCard.jpg", 100, 150)));
        player1BackCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player1Panel.add(player1BackCardLabel, BorderLayout.NORTH);
        player1FrontCardLabel = new JLabel();
        player1FrontCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player1Panel.add(player1FrontCardLabel, BorderLayout.CENTER);
        topPanel.add(player1Panel);

        JPanel player2Panel = new JPanel(new BorderLayout());
        player2Panel.setOpaque(false);
        player2BackCardLabel = new JLabel(new ImageIcon(resizeImage("images/BackCard.jpg", 100, 150)));
        player2BackCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player2Panel.add(player2BackCardLabel, BorderLayout.NORTH);
        player2FrontCardLabel = new JLabel();
        player2FrontCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player2Panel.add(player2FrontCardLabel, BorderLayout.CENTER);
        topPanel.add(player2Panel);

        add(topPanel, BorderLayout.CENTER);

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setOpaque(false);
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBackground(Color.BLACK);
        resultLabel.setOpaque(true);
        middlePanel.add(resultLabel, BorderLayout.CENTER);

        add(middlePanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.setOpaque(false);
        player1ScoreLabel = new JLabel("Total score: 0", SwingConstants.CENTER);
        player1ScoreLabel.setForeground(Color.WHITE);
        player1ScoreLabel.setBackground(Color.BLACK);
        player1ScoreLabel.setOpaque(true);
        bottomPanel.add(player1ScoreLabel);

        playButton = new JButton(new ImageIcon(resizeImage("images/playbutton.png", 200, 80)));
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.addActionListener(e -> controller.playRound());
        bottomPanel.add(playButton);

        player2ScoreLabel = new JLabel("Total score: 0", SwingConstants.CENTER);
        player2ScoreLabel.setForeground(Color.WHITE);
        player2ScoreLabel.setBackground(Color.BLACK);
        player2ScoreLabel.setOpaque(true);
        bottomPanel.add(player2ScoreLabel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void updateGameView(Card player1Card, Card player2Card, String result, int player1Score, int player2Score) {
        player1FrontCardLabel.setIcon(new ImageIcon(resizeImage("images/" + getCardImageName(player1Card) + ".png", 100, 150)));
        player2FrontCardLabel.setIcon(new ImageIcon(resizeImage("images/" + getCardImageName(player2Card) + ".png", 100, 150)));
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
        return rank + suitInitial;
    }

    private Image resizeImage(String path, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
