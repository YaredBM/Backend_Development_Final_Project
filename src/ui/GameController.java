package ui;

import game.*; //Imports all classes from the game package.

import javax.swing.JOptionPane; //Imports JOptionPane for dialog boxes.

// This class acts as the controller for the game, managing the interaction between the game logic and the user interface.

public class GameController {
    private WarGame game; // Instance of the game logic.
    private GameFrame gameFrame; // Main game window.
    private String player1Name; // Name of player 1.
    private String player2Name; // Name of player 2.
    private SaveGame saveGameHandler; // Handler for saving the game state.
    private LoadGame loadGameHandler; // Handler for loading the game state.

    // Create a Constructor to initialize the game controller.
    public GameController() {
        saveGameHandler = new SaveGame(); // Initializes the save game handler.
        loadGameHandler = new LoadGame(); // Initializes the load game handler.
        getPlayerNames(); // Prompts for player names.
        game = new WarGame(); // Creates a new instance of the game.
        game.dealCards(); // Deals cards to the players.
        gameFrame = new GameFrame(this); // Creates the main game window.
        updateView();  // Updates the game view.
    }

    // Create Getters and Setters
    public WarGame getGame() {
        return game; // Returns the game instance.
    }

    public void setGame(WarGame game) {
        this.game = game; // Sets the game instance.
    }

    public GameFrame getGameFrame() {
        return gameFrame; // Returns the game window.
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame; // Sets the game window.
    }

    public String getPlayer1Name() {
        return player1Name; // Returns the name of player 1.
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name; // Sets the name of player 1.
    }

    public String getPlayer2Name() {
        return player2Name; // Returns the name of player 2.
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name; // Sets the name of player 2.
    }

    //Prompts for player names.
    private void getPlayerNames() {
        player1Name = JOptionPane.showInputDialog("Enter Player 1 name:"); // Shows input dialog for player 1 name.
        String[] options = {"Play against another player", "Play against the computer"}; // Options for game mode.
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode", "Game Mode",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]); // Shows option dialog for game mode.
        if (choice == 0) {
            player2Name = JOptionPane.showInputDialog("Enter Player 2 name:"); // Input dialog for player 2 name.
        } else {
            player2Name = "Computer"; // Sets player 2 name as "Computer".
        }
    }

    //Plays a round of the game and updates the view.
    public void playRound() {
        if (!game.isGameOver()) { // Checks if the game is not over.
            game.playRound(); // Plays a round.
            updateView(); // Updates the game view.
            if (game.isGameOver()) { // Checks if the game is over after the round.
                JOptionPane.showMessageDialog(null, game.getResultMessage()); // Shows end of game message.
                gameFrame.getGamePanel().updateResultMessage(game.getResultMessage()); // Updates the result message on the game panel.
            }
        }
    }

    //Updates the game view with the current state.
    public void updateView() {
        gameFrame.getGamePanel().updateGameView(
                game.getPlayer1Card(), // Gets the card of player 1.
                game.getPlayer2Card(), // Gets the card of player 2.
                getResultText(), // Gets the result text for the current round.
                game.getPlayer1().getScore(), // Gets the score of player 1.
                game.getPlayer2().getScore(), // Gets the score of player 2.
                player1Name, // Gets the name of player 1.
                player2Name // Gets the name of player 2.
        );
    }

    //Returns the result text for the current round.
    //@return The result text.
    private String getResultText() {
        if (game.getPlayer1Card() != null && game.getPlayer2Card() != null) { // Checks if both players have cards.
            if (game.getPlayer1Card().getRank() > game.getPlayer2Card().getRank()) {
                return player1Name + " wins this round!"; // Returns message if player 1 wins the round.
            } else if (game.getPlayer1Card().getRank() < game.getPlayer2Card().getRank()) {
                return player2Name + " wins this round!"; // Returns message if player 2 wins the round.
            } else {
                return "It's a tie!"; // Returns message if it's a tie.
            }
        }
        return ""; // Returns empty string if no cards are present.
    }

    //Starts a new game.
    public void newGame() {
        getPlayerNames(); // Prompts for player names again.
        game.reset(); // Resets the game.
        updateView(); // Updates the game view.
    }

    //Saves the game state.
    public void saveGame() {
        saveGameHandler.saveGame(game); // Saves the game state using the save game handler.
    }

    //Opens a saved game state.
    public void openGame() {
        WarGame loadedGame = loadGameHandler.loadGame(); // Loads a saved game state using the load game handler.
        if (loadedGame != null) { // Checks if the game was loaded successfully.
            game = loadedGame; // Sets the loaded game as the current game.
            updateView(); // Updates the game view.
        }
    }

    //Prints information about the controller.
    public void printInfo() {
        System.out.println("Game Controller Info:"); // Prints info message for the game controller.
        game.printInfo(); // Calls the method to print game information.
    }
}
