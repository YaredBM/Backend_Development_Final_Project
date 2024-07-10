package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the overall game logic, including dealing cards, playing rounds, handling wars, and checking game-over conditions.
 */
public class WarGame implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Deck deck;
    private Player player1;
    private Player player2;
    private Card player1Card;
    private Card player2Card;
    private boolean gameOver;
    private String resultMessage;

    /**
     * Constructor to initialize the game with a deck and two players.
     */
    public WarGame() {
        deck = new Deck();
        player1 = new Player("Player 1");
        player2 = new Player("Computer");
        gameOver = false;
        resultMessage = "";
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Card getPlayer1Card() {
        return player1Card;
    }

    public void setPlayer1Card(Card player1Card) {
        this.player1Card = player1Card;
    }

    public Card getPlayer2Card() {
        return player2Card;
    }

    public void setPlayer2Card(Card player2Card) {
        this.player2Card = player2Card;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * Shuffles and deals the cards to the players.
     */
    public void dealCards() {
        deck.shuffle();
        while (deck.hasCards()) {
            player1.addCard(deck.dealCard());
            if (deck.hasCards()) {
                player2.addCard(deck.dealCard());
            }
        }
    }

    /**
     * Plays a single round of the game.
     */
    public void playRound() {
        if (gameOver || !player1.hasCards() || !player2.hasCards()) {
            return;
        }

        player1Card = player1.playCard();
        player2Card = player2.playCard();

        if (player1Card != null && player2Card != null) {
            List<Card> warCards = new ArrayList<>();
            warCards.add(player1Card);
            warCards.add(player2Card);

            if (player1Card.getRank() > player2Card.getRank()) {
                player1.incrementScoreWithCards(warCards);
            } else if (player1Card.getRank() < player2Card.getRank()) {
                player2.incrementScoreWithCards(warCards);
            } else {
                handleWar(warCards);
            }
        }

        checkGameOver();
    }

    /**
     * Handles the logic for a "war" when two cards of equal rank are played.
     * @param warCards The list of cards involved in the war.
     */
    private void handleWar(List<Card> warCards) {
        while (true) {
            if (player1.getCardCount() < 4 || player2.getCardCount() < 4) {
                // Player loses if they don't have enough cards to continue the war
                if (player1.getCardCount() < 4) {
                    player2.incrementScoreWithCards(warCards);
                    gameOver = true;
                    resultMessage = "Computer wins :(";
                    return;
                }
                if (player2.getCardCount() < 4) {
                    player1.incrementScoreWithCards(warCards);
                    gameOver = true;
                    resultMessage = "Match ended, Congratulations " + player1.getName();
                    return;
                }
            }

            // Add two cards face down and one card face up for both players
            for (int i = 0; i < 2; i++) {
                warCards.add(player1.playCard());
                warCards.add(player2.playCard());
            }

            player1Card = player1.playCard();
            player2Card = player2.playCard();
            warCards.add(player1Card);
            warCards.add(player2Card);

            if (player1Card.getRank() > player2Card.getRank()) {
                player1.incrementScoreWithCards(warCards);
                break;
            } else if (player1Card.getRank() < player2Card.getRank()) {
                player2.incrementScoreWithCards(warCards);
                break;
            }
        }

        checkGameOver();
    }

    /**
     * Checks if the game is over and sets the result message accordingly.
     */
    private void checkGameOver() {
        if (!player1.hasCards()) {
            gameOver = true;
            resultMessage = "Computer wins :(";
        } else if (!player2.hasCards()) {
            gameOver = true;
            resultMessage = "Match ended, Congratulations " + player1.getName();
        }
    }

    /**
     * Resets the game with a new deck and players.
     */
    public void reset() {
        deck = new Deck();
        player1 = new Player("Player 1");
        player2 = new Player("Computer");
        player1Card = null;
        player2Card = null;
        gameOver = false;
        resultMessage = "";
        dealCards();
    }

    /**
     * Prints information about the game.
     */
    public void printInfo() {
        System.out.println("Game Over: " + gameOver);
        System.out.println("Result: " + resultMessage);
        player1.printInfo();
        player2.printInfo();
    }
}
