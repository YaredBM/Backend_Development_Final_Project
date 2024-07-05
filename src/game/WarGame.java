package game;

public class WarGame {
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
        while (deck != null) {
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

        if (card1.getRank() > card2.getRank()) {
            player1.addScore(card1.getRank() + card2.getRank());
        } else {
            player2.addScore(card1.getRank() + card2.getRank());
        }
    }
}
