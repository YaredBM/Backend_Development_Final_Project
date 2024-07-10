package ui;

import game.Card; // Imports the Card class from the game package.

import javax.swing.*; // Imports all classes from the javax.swing package for creating the GUI.
import java.awt.*; // Imports all classes from the java.awt package for working with graphics.
import java.awt.image.BufferedImage; // Imports the BufferedImage class for handling images.
import java.io.File; // Imports the File class for file handling.
import java.io.IOException; // Imports the IOException class for handling input/output exceptions.
import javax.imageio.ImageIO; // Imports the ImageIO class for reading and writing images.

// This class represents the main panel in the game window where the game is displayed.

public class GamePanel extends JPanel { // Create the GamePanel class that extends JPanel.
    private static final long serialVersionUID = 1L; // Declares the serialVersionUID for serialization compatibility.
    private GameController controller; // Declares the game controller.
    private JLabel player1BackCardLabel, player2BackCardLabel; // Declares labels for the back of the cards for player 1 and player 2.
    private JLabel player1FrontCardLabel, player2FrontCardLabel; // Declares labels for the front of the cards for player 1 and player 2.
    private JLabel player1ScoreLabel, player2ScoreLabel; // Declares labels for the scores of player 1 and player 2.
    private JLabel resultLabel; // Declares a label for the result of the round.
    private JLabel resultImageLabel; // Declares a label for the result image.
    private JLabel player1Label, player2Label; // Declares labels for the names of player 1 and player 2.
    private JButton playButton; // Declares a button to play a round.
    private BufferedImage backgroundImage; // Declares a BufferedImage for the background image.

    //Create a constructor to initialize the game panel with a controller.
    //@param controller The game controller.
    public GamePanel(GameController controller) {
        this.controller = controller; // Initializes the controller.
        setLayout(new OverlayLayout(this)); // Sets the layout of the panel to OverlayLayout.

        try {
            backgroundImage = ImageIO.read(new File("images/goldcasino.jpeg")); // It tries to read the background image from the file.
        } catch (IOException e) {
            e.printStackTrace(); // It prints the stack trace if an IOException occurs.
        }

        JPanel mainPanel = new JPanel(new BorderLayout()); // Creates a main panel with BorderLayout.
        mainPanel.setOpaque(false); // Sets the main panel to be transparent.

        JPanel topPanel = new JPanel(new GridLayout(1, 2)); // Creates a top panel with GridLayout (1 row, 2 columns).
        topPanel.setOpaque(false); // Sets the top panel to be transparent.

        JPanel player1Panel = new JPanel(new BorderLayout()); // Creates a panel for player 1 with BorderLayout.
        player1Panel.setOpaque(false); // Sets the player 1 panel to be transparent.

        player1Label = createLabel("User Card", 100, 30); // Creates a label for player 1 with the text "User Card".
        player1Panel.add(player1Label, BorderLayout.NORTH); // Adds the label to the north region of the player 1 panel.

        JPanel player1CardPanel = new JPanel(new GridLayout(2, 1)); // Creates a panel for player 1's cards with GridLayout (2 rows, 1 column).
        player1CardPanel.setOpaque(false); // Sets the player 1 card panel to be transparent.
        player1BackCardLabel = new JLabel(new ImageIcon(resizeImage("images/BackCard.jpg", 80, 120))); // Creates a label for the back of player 1's card with an image.
        player1BackCardLabel.setHorizontalAlignment(SwingConstants.CENTER); // Sets the horizontal alignment of the label to center.
        player1CardPanel.add(player1BackCardLabel); // Adds the back card label to the player 1 card panel.

        player1FrontCardLabel = new JLabel(); // Creates an empty label for the front of player 1's card.
        player1FrontCardLabel.setHorizontalAlignment(SwingConstants.CENTER); // Sets the horizontal alignment of the label to center.
        player1CardPanel.add(player1FrontCardLabel); // Adds the front card label to the player 1 card panel.
        player1Panel.add(player1CardPanel, BorderLayout.CENTER); // Adds the card panel to the center region of the player 1 panel.

        JPanel player1ScorePanel = new JPanel(); // Creates a panel for player 1's score.
        player1ScorePanel.setOpaque(false); // Sets the player 1 score panel to be transparent.
        player1ScoreLabel = createLabel("Total score: 0", 160, 25); // Creates a label for player 1's score with initial text "Total score: 0".
        player1ScorePanel.add(player1ScoreLabel); // Adds the score label to the player 1 score panel.
        player1Panel.add(player1ScorePanel, BorderLayout.SOUTH); // Adds the score panel to the south region of the player 1 panel.

        topPanel.add(player1Panel); // Adds the player 1 panel to the top panel.

        JPanel player2Panel = new JPanel(new BorderLayout()); // Creates a panel for player 2 with BorderLayout.
        player2Panel.setOpaque(false); // Sets the player 2 panel to be transparent.

        player2Label = createLabel("Computer Card", 100, 30); // Creates a label for player 2 with the text "Computer Card".
        player2Panel.add(player2Label, BorderLayout.NORTH); // Adds the label to the north region of the player 2 panel.

        JPanel player2CardPanel = new JPanel(new GridLayout(2, 1)); // Creates a panel for player 2's cards with GridLayout (2 rows, 1 column).
        player2CardPanel.setOpaque(false); // Sets the player 2 card panel to be transparent.
        player2BackCardLabel = new JLabel(new ImageIcon(resizeImage("images/BackCard.jpg", 80, 120))); // Creates a label for the back of player 2's card with an image.
        player2BackCardLabel.setHorizontalAlignment(SwingConstants.CENTER); // Sets the horizontal alignment of the label to center.
        player2CardPanel.add(player2BackCardLabel); // Adds the back card label to the player 2 card panel.

        player2FrontCardLabel = new JLabel(); // Creates an empty label for the front of player 2's card.
        player2FrontCardLabel.setHorizontalAlignment(SwingConstants.CENTER); // Sets the horizontal alignment of the label to center.
        player2CardPanel.add(player2FrontCardLabel); // Adds the front card label to the player 2 card panel.
        player2Panel.add(player2CardPanel, BorderLayout.CENTER); // Adds the card panel to the center region of the player 2 panel.

        JPanel player2ScorePanel = new JPanel(); // Creates a panel for player 2's score.
        player2ScorePanel.setOpaque(false); // Sets the player 2 score panel to be transparent.
        player2ScoreLabel = createLabel("Total score: 0", 160, 25); // Creates a label for player 2's score with initial text "Total score: 0".
        player2ScorePanel.add(player2ScoreLabel); // Adds the score label to the player 2 score panel.
        player2Panel.add(player2ScorePanel, BorderLayout.SOUTH); // Adds the score panel to the south region of the player 2 panel.

        topPanel.add(player2Panel); // Adds the player 2 panel to the top panel.

        mainPanel.add(topPanel, BorderLayout.CENTER); // Adds the top panel to the center region of the main panel.

        JPanel middlePanel = new JPanel(new BorderLayout()); // Creates a middle panel with BorderLayout.
        middlePanel.setOpaque(false); // Sets the middle panel to be transparent.
        resultLabel = createLabel("", 300, 30); // Creates an empty label for the result.
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Sets the font of the result label.
        middlePanel.add(resultLabel, BorderLayout.NORTH); // Adds the result label to the north region of the middle panel.

        mainPanel.add(middlePanel, BorderLayout.NORTH); // Adds the middle panel to the north region of the main panel.

        JPanel bottomPanel = new JPanel(); // Creates a bottom panel.
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); // Sets the layout of the bottom panel to BoxLayout (Y-axis).
        bottomPanel.setOpaque(false); // Sets the bottom panel to be transparent.

        JPanel playButtonPanel = new JPanel(); // Creates a panel for the play button.
        playButtonPanel.setOpaque(false); // Sets the play button panel to be transparent.
        playButton = new JButton(new ImageIcon(resizeImage("images/playbutton.png", 200, 160))); // Creates a play button with an image.
        playButton.setContentAreaFilled(false); // Sets the content area of the button to be transparent.
        playButton.setBorderPainted(false); // Sets the border of the button to not be painted.
        playButton.setBorder(BorderFactory.createEmptyBorder()); // Sets the border of the button to be empty.
        playButton.setOpaque(false); // Sets the button to be transparent.
        playButton.addActionListener(e -> controller.playRound()); // Adds an action listener to the button to call the playRound method of the controller.
        playButtonPanel.add(playButton); // Adds the play button to the play button panel.
        bottomPanel.add(playButtonPanel); // Adds the play button panel to the bottom panel.

        mainPanel.add(bottomPanel, BorderLayout.SOUTH); // Adds the bottom panel to the south region of the main panel.

        add(mainPanel); // Adds the main panel to the GamePanel.

        resultImageLabel = new JLabel(); // Creates an empty label for the result image.
        resultImageLabel.setAlignmentX(0.5f); // Sets the X alignment of the label to center.
        resultImageLabel.setAlignmentY(0.5f); // Sets the Y alignment of the label to center.
        resultImageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Sets the horizontal alignment of the label to center.
        add(resultImageLabel); // Adds the result image label to the GamePanel.
    }

     // Create a label with the specified text and size.
     // @param text The text of the label.
     // @param width The width of the label.
     // @param height The height of the label.
     // @return The created label.
    private JLabel createLabel(String text, int width, int height) {
        JLabel label = new JLabel(text, SwingConstants.CENTER); // Create a label with the specified text and centers the text horizontally.
        label.setForeground(Color.WHITE); // Sets the text color to white.
        label.setBackground(new Color(128, 0, 0)); // Sets the background color to a wine color.
        label.setOpaque(true); // Makes the label opaque.
        label.setFont(new Font("Arial", Font.BOLD, 14)); // Sets the font of the label.
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Sets a white border around the label.
        label.setPreferredSize(new Dimension(width, height)); // Sets the preferred size of the label.
        label.setMinimumSize(new Dimension(width, height)); // Sets the minimum size of the label.
        label.setMaximumSize(new Dimension(width, height)); // Sets the maximum size of the label.
        return label; // Returns the created label.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // It calls the superclass's paintComponent method.
        if (backgroundImage != null) { // If the background image is not null.
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draws the background image scaled to the size of the panel.
        }
    }

     // Updates the game view with the current state.
     // @param player1Card The card played by player 1.
     // @param player2Card The card played by player 2.
     // @param result The result of the round.
     // @param player1Score The score of player 1.
     // @param player2Score The score of player 2.
     // @param player1Name The name of player 1.
     // @param player2Name The name of player 2.
    public void updateGameView(Card player1Card, Card player2Card, String result, int player1Score, int player2Score, String player1Name, String player2Name) {
        if (player1Card != null) { // If player 1's card is not null.
            player1FrontCardLabel.setIcon(new ImageIcon(resizeImage("images/" + getCardImageName(player1Card) + ".png", 80, 120))); // Sets the icon of player 1's front card label to the image of the card.
        } else {
            player1FrontCardLabel.setIcon(null); // Sets the icon of player 1's front card label to null.
        }

        if (player2Card != null) { // If player 2's card is not null.
            player2FrontCardLabel.setIcon(new ImageIcon(resizeImage("images/" + getCardImageName(player2Card) + ".png", 80, 120))); // Sets the icon of player 2's front card label to the image of the card.
        } else {
            player2FrontCardLabel.setIcon(null); // Sets the icon of player 2's front card label to null.
        }

        resultLabel.setText(result); // Sets the text of the result label.
        player1ScoreLabel.setText("Total score: " + player1Score); // Sets the text of player 1's score label.
        player2ScoreLabel.setText("Total score: " + player2Score); // Sets the text of player 2's score label.

        player1Label.setText(player1Name); // Sets the text of player 1's name label.
        player2Label.setText(player2Name); // Sets the text of player 2's name label.

        if (result.contains(player1Name + " wins this round!")) { // If the result indicates player 1 wins the round.
            resultImageLabel.setIcon(new ImageIcon(resizeImage("images/happyface.png", 150, 150))); // Sets the icon of the result image label to a happy face.
        } else if (result.contains(player2Name + " wins this round!")) { // If the result indicates player 2 wins the round.
            resultImageLabel.setIcon(new ImageIcon(resizeImage("images/sadface.png", 150, 150))); // Sets the icon of the result image label to a sad face.
        } else {
            resultImageLabel.setIcon(null); // Sets the icon of the result image label to null.
        }
    }

    
     // Updates the result message displayed.
     // @param resultMessage The result message.
    public void updateResultMessage(String resultMessage) {
        resultLabel.setText(resultMessage); // Sets the text of the result label.
    }

     // Returns the image name for the specified card.
     // @param card The card.
     // @return The image name.
    private String getCardImageName(Card card) {
        String rank = ""; // Initializes the rank string.
        switch (card.getRank()) { // Switches based on the card's rank.
            case 11: rank = "J"; break; // If the rank is 11, set rank to "J".
            case 12: rank = "Q"; break; // If the rank is 12, set rank to "Q".
            case 13: rank = "K"; break; // If the rank is 13, set rank to "K".
            case 14: rank = "A"; break; // If the rank is 14, set rank to "A".
            default: rank = String.valueOf(card.getRank()); break; // For other ranks, set rank to the string value of the rank.
        }

        String suitInitial = card.getSuit().substring(0, 1); // Gets the first character of the card's suit.
        return rank + suitInitial; // Returns the concatenated rank and suit initial.
    }

     // Resizes an image from the given path to the specified dimensions.
     // @param path The path of the image.
     // @param width The width of the resized image.
     // @param height The height of the resized image.
     // @return The resized image.
    private Image resizeImage(String path, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(path)); // Tries to read the image from the file.
            return img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Resizes the image to the specified dimensions.
        } catch (IOException e) {
            e.printStackTrace(); // Prints the stack trace if an IOException occurs.
            return null; // Returns null if an exception occurs.
        }
    }

     // Prints information about the game panel.
    public void printInfo() {
        System.out.println("Game Panel Info:"); // Prints "Game Panel Info:".
        System.out.println("Background Image: " + (backgroundImage != null ? "Loaded" : "Not Loaded")); // Prints whether the background image is loaded or not.
    }
}

