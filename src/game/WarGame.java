package game;

import java.io.Serializable;

public class WarGame implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Player player1;
    private Player player2;
    private Deck deck;

    public WarGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck = new Deck();
        dealCards();
    }

    private void dealCards() {
        while (true) {
            Card card = deck.drawCard();
            if (card == null) break;
            player1.addCard(card);

            card = deck.drawCard();
            if (card == null) break;
            player2.addCard(card);
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void playRound() {
        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        if (card1 == null || card2 == null) {
            return;
        }

        int accumulatedPoints = card1.getRank() + card2.getRank();

        while (card1.getRank() == card2.getRank()) {
            System.out.println("Tie detected, drawing one more card each...");
            Card additionalCard1 = player1.playCard();
            Card additionalCard2 = player2.playCard();

            if (additionalCard1 == null || additionalCard2 == null) {
                return;
            }

            accumulatedPoints += additionalCard1.getRank() + additionalCard2.getRank();

            card1 = additionalCard1;
            card2 = additionalCard2;
        }

        if (card1.getRank() > card2.getRank()) {
            player1.addScore(accumulatedPoints);
        } else {
            player2.addScore(accumulatedPoints);
        }
    }

    public static void main(String[] args) {
        Player player1 = new Player("Alice", 1);
        Player player2 = new Player("Bob", 2);

        WarGame game = new WarGame(player1, player2);

        while (true) {
            game.playRound();
            if (player1.playCard() == null || player2.playCard() == null) {
                break;
            }
        }

        System.out.println(player1.getName() + " score: " + player1.getScore());
        System.out.println(player2.getName() + " score: " + player2.getScore());

        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.getName() + " wins!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }

        SaveGame.save(game, "saved_game.dat");
        WarGame loadedGame = SaveGame.load("saved_game.dat");
    }
}
