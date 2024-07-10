package ui;

import javax.swing.*; // Imports all classes from the javax.swing package for creating the GUI.

//This class creates the main game window.

public class GameFrame extends JFrame { // Create the GameFrame class that extends JFrame.
    private GamePanel gamePanel; // Declares a GamePanel instance variable.

    //Create a constructor to initialize the game frame with a game panel and menu bar.
    //@param controller The game controller.
    public GameFrame(GameController controller) {
        setTitle("Card Game"); // Sets the title of the window.
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Defines the default close operation
        
        gamePanel = new GamePanel(controller); // It initializes the game panel with the game controller.
        add(gamePanel); // Adds the game panel to the frame.

        setJMenuBar(new MenuBar(controller)); // Sets the menu bar for the frame using the game controller.
        setVisible(true); // Makes the frame visible.
    }

    // Create a getter and setter to get the game panel.
    public GamePanel getGamePanel() {
        return gamePanel; // Returns the game panel.
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel; // Sets the game panel.
    }

    //It prints information about the game frame.
    public void printInfo() {
        System.out.println("Game Frame Info:"); // Prints a message indicating it's game frame info.
        System.out.println("Title: " + getTitle()); // Prints the title of the frame.
        System.out.println("Size: " + getSize()); // Prints the size of the frame.
    }
}
