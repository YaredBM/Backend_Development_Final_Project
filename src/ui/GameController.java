package ui;

import game.Card;
import game.Player;
import game.WarGame;

public class GameController {
    private WarGame game;
    private GameFrame frame;

    public GameController() {
        Player player1 = new Player("User", 1);
        Player player2 = new Player("Computer", 2);
        this.game = new WarGame(player1, player2);
        this.frame = new GameFrame(this);
        this.frame.setVisible(true);
    }

    public void playRound() {
        Card player1Card = game.getPlayer1().playCard();
        Card player2Card = game.getPlayer2().playCard();

        if (player1Card == null || player2Card == null) {
            return;
        }

        String result;
        if (player1Card.getRank() > player2Card.getRank()) {
            result = "User wins this round!";
            game.getPlayer1().addScore(player1Card.getRank() + player2Card.getRank());
        } else {
            result = "Computer wins this round!";
            game.getPlayer2().addScore(player1Card.getRank() + player2Card.getRank());
        }

        frame.updateGameView(player1Card, player2Card, result, game.getPlayer1().getScore(), game.getPlayer2().getScore());
    }

    public void newGame() {
        // Logic for starting a new game
    }

    public void saveGame() {
        // Logic for saving the game
    }

    public void openGame() {
        // Logic for opening a saved game
    }
    
    public static void main(String[] args) {
        new GameController();
    }
}
    public static void main(String[] args) {
        new GameController();
    }
}
