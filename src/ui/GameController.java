package ui;

import game.*;

import javax.swing.JOptionPane;

/**
 * Acts as the controller for the game, managing the interaction between the game logic and the user interface.
 */
public class GameController {
    private WarGame game;
    private GameFrame gameFrame;
    private String player1Name;
    private String player2Name;
    private SaveGame saveGameHandler;
    private LoadGame loadGameHandler;

    /**
     * Constructor to initialize the game controller.
     */
    public GameController() {
        saveGameHandler = new SaveGame();
        loadGameHandler = new LoadGame();
        getPlayerNames();
        game = new WarGame();
        game.dealCards();
        gameFrame = new GameFrame(this);
        updateView();
    }

    public WarGame getGame() {
        return game;
    }

    public void setGame(WarGame game) {
        this.game = game;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    /**
     * Prompts for player names.
     */
    private void getPlayerNames() {
        player1Name = JOptionPane.showInputDialog("Enter Player 1 name:");
        String[] options = {"Play against another player", "Play against the computer"};
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode", "Game Mode",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
        if (choice == 0) {
            player2Name = JOptionPane.showInputDialog("Enter Player 2 name:");
        } else {
            player2Name = "Computer";
        }
    }

    /**
     * Plays a round of the game and updates the view.
     */
    public void playRound() {
        if (!game.isGameOver()) {
            game.playRound();
            updateView();
            if (game.isGameOver()) {
                JOptionPane.showMessageDialog(null, game.getResultMessage());
                gameFrame.getGamePanel().updateResultMessage(game.getResultMessage());
            }
        }
    }

    /**
     * Updates the game view with the current state.
     */
    public void updateView() {
        gameFrame.getGamePanel().updateGameView(
                game.getPlayer1Card(),
                game.getPlayer2Card(),
                getResultText(),
                game.getPlayer1().getScore(),
                game.getPlayer2().getScore(),
                player1Name,
                player2Name
        );
    }

    /**
     * Returns the result text for the current round.
     * @return The result text.
     */
    private String getResultText() {
        if (game.getPlayer1Card() != null && game.getPlayer2Card() != null) {
            if (game.getPlayer1Card().getRank() > game.getPlayer2Card().getRank()) {
                return player1Name + " wins this round!";
            } else if (game.getPlayer1Card().getRank() < game.getPlayer2Card().getRank()) {
                return player2Name + " wins this round!";
            } else {
                return "It's a tie!";
            }
        }
        return "";
    }

    /**
     * Starts a new game.
     */
    public void newGame() {
        getPlayerNames();
        game.reset();
        updateView();
    }

    /**
     * Saves the game state.
     */
    public void saveGame() {
        saveGameHandler.saveGame(game);
    }

    /**
     * Opens a saved game state.
     */
    public void openGame() {
        WarGame loadedGame = loadGameHandler.loadGame();
        if (loadedGame != null) {
            game = loadedGame;
            updateView();
        }
    }

    /**
     * Prints information about the controller.
     */
    public void printInfo() {
        System.out.println("Game Controller Info:");
        game.printInfo();
    }
}
