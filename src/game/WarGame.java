package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WarGame implements Serializable {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Card player1Card;
    private Card player2Card;
    private boolean gameOver;
    private String resultMessage;

    public WarGame() {
        deck = new Deck();
        player1 = new Player("Player 1");
        player2 = new Player("Computer");
        gameOver = false;
        resultMessage = "";
    }

    public void dealCards() {
        deck.shuffle();
        while (deck.hasCards()) {
            player1.addCard(deck.dealCard());
            if (deck.hasCards()) {
                player2.addCard(deck.dealCard());
            }
        }
    }

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

    private void handleWar(List<Card> warCards) {
        while (player1Card != null && player2Card != null && player1Card.getRank() == player2Card.getRank()) {
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
            } else if (player1Card.getRank() < player2Card.getRank()) {
                player2.incrementScoreWithCards(warCards);
            }
        }
    }

    private void checkGameOver() {
        if (!player1.hasCards()) {
            gameOver = true;
            resultMessage = "Computer wins :(";
        } else if (!player2.hasCards()) {
            gameOver = true;
            resultMessage = "Match ended, Congratulations " + player1.getName();
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Card getPlayer1Card() {
        return player1Card;
    }

    public Card getPlayer2Card() {
        return player2Card;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getResultMessage() {
        return resultMessage;
    }

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
}
