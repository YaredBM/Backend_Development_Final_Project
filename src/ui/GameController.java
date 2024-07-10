package ui;

import game.*;

import javax.swing.JOptionPane;

public class GameController {
    private WarGame game;
    private GameFrame gameFrame;
    private String player1Name;
    private String player2Name;

    public GameController() {
        getPlayerNames();
        game = new WarGame();
        game.dealCards();
        gameFrame = new GameFrame(this);
        updateView();
    }

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

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

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

    public void newGame() {
        getPlayerNames();
        game.reset();
        updateView();
    }

    public void saveGame() {
        SaveGame.saveGame(game);
    }

    public void openGame() {
        WarGame loadedGame = LoadGame.loadGame();
        if (loadedGame != null) {
            game = loadedGame;
            updateView();
        }
    }
}
