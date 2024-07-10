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
    private JLabel resultImageLabel;
    private JLabel player1Label, player2Label;
    private JButton playButton;
    private BufferedImage backgroundImage;

    public GamePanel(GameController controller) {
        this.controller = controller;
        setLayout(new OverlayLayout(this));

        try {
            backgroundImage = ImageIO.read(new File("images/goldcasino.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setOpaque(false);

        JPanel player1Panel = new JPanel(new BorderLayout());
        player1Panel.setOpaque(false);

        player1Label = createLabel("User Card", 100, 30);
        player1Panel.add(player1Label, BorderLayout.NORTH);

        JPanel player1CardPanel = new JPanel(new GridLayout(2, 1));
        player1CardPanel.setOpaque(false);
        player1BackCardLabel = new JLabel(new ImageIcon(resizeImage("images/BackCard.jpg", 80, 120)));
        player1BackCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player1CardPanel.add(player1BackCardLabel);

        player1FrontCardLabel = new JLabel();
        player1FrontCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player1CardPanel.add(player1FrontCardLabel);
        player1Panel.add(player1CardPanel, BorderLayout.CENTER);

        JPanel player1ScorePanel = new JPanel();
        player1ScorePanel.setOpaque(false);
        player1ScoreLabel = createLabel("Total score: 0", 180, 25);
        player1ScorePanel.add(player1ScoreLabel);
        player1Panel.add(player1ScorePanel, BorderLayout.SOUTH);

        topPanel.add(player1Panel);

        JPanel player2Panel = new JPanel(new BorderLayout());
        player2Panel.setOpaque(false);

        player2Label = createLabel("Computer Card", 100, 30);
        player2Panel.add(player2Label, BorderLayout.NORTH);

        JPanel player2CardPanel = new JPanel(new GridLayout(2, 1));
        player2CardPanel.setOpaque(false);
        player2BackCardLabel = new JLabel(new ImageIcon(resizeImage("images/BackCard.jpg", 80, 120)));
        player2BackCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player2CardPanel.add(player2BackCardLabel);

        player2FrontCardLabel = new JLabel();
        player2FrontCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player2CardPanel.add(player2FrontCardLabel);
        player2Panel.add(player2CardPanel, BorderLayout.CENTER);

        JPanel player2ScorePanel = new JPanel();
        player2ScorePanel.setOpaque(false);
        player2ScoreLabel = createLabel("Total score: 0", 180, 25);
        player2ScorePanel.add(player2ScoreLabel);
        player2Panel.add(player2ScorePanel, BorderLayout.SOUTH);

        topPanel.add(player2Panel);

        mainPanel.add(topPanel, BorderLayout.CENTER);

        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setOpaque(false);
        resultLabel = createLabel("", 300, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        middlePanel.add(resultLabel, BorderLayout.NORTH);

        mainPanel.add(middlePanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setOpaque(false);

        JPanel playButtonPanel = new JPanel();
        playButtonPanel.setOpaque(false);
        playButton = new JButton(new ImageIcon(resizeImage("images/playbutton.png", 200, 140)));
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setBorder(BorderFactory.createEmptyBorder());
        playButton.setOpaque(false);
        playButton.addActionListener(e -> controller.playRound());
        playButtonPanel.add(playButton);
        bottomPanel.add(playButtonPanel);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        resultImageLabel = new JLabel();
        resultImageLabel.setAlignmentX(0.5f);
        resultImageLabel.setAlignmentY(0.5f);
        resultImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultImageLabel);
    }

    private JLabel createLabel(String text, int width, int height) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(128, 0, 0)); // Wine color
        label.setOpaque(true);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        label.setPreferredSize(new Dimension(width, height));
        label.setMinimumSize(new Dimension(width, height));
        label.setMaximumSize(new Dimension(width, height));
        return label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void updateGameView(Card player1Card, Card player2Card, String result, int player1Score, int player2Score, String player1Name, String player2Name) {
        if (player1Card != null) {
            player1FrontCardLabel.setIcon(new ImageIcon(resizeImage("images/" + getCardImageName(player1Card) + ".png", 80, 120)));
        } else {
            player1FrontCardLabel.setIcon(null);
        }

        if (player2Card != null) {
            player2FrontCardLabel.setIcon(new ImageIcon(resizeImage("images/" + getCardImageName(player2Card) + ".png", 80, 120)));
        } else {
            player2FrontCardLabel.setIcon(null);
        }

        resultLabel.setText(result);
        player1ScoreLabel.setText("Total score: " + player1Score);
        player2ScoreLabel.setText("Total score: " + player2Score);

        player1Label.setText(player1Name);
        player2Label.setText(player2Name);

        if (result.contains(player1Name + " wins this round!")) {
            resultImageLabel.setIcon(new ImageIcon(resizeImage("images/happyface.png", 150, 150)));
        } else if (result.contains(player2Name + " wins this round!")) {
            resultImageLabel.setIcon(new ImageIcon(resizeImage("images/sadface.png", 150, 150)));
        } else {
            resultImageLabel.setIcon(null);
        }
    }

    public void updateResultMessage(String resultMessage) {
        resultLabel.setText(resultMessage);
    }

    private String getCardImageName(Card card) {
        String rank = "";
        switch (card.getRank()) {
            case 11: rank = "J"; break;
            case 12: rank = "Q"; break;
            case 13: rank = "K"; break;
            case 14: rank = "A"; break;
            default: rank = String.valueOf(card.getRank()); break;
        }

        String suitInitial = card.getSuit().substring(0, 1);
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
