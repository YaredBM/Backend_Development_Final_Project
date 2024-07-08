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

        while (card1.getRank() == card2.getRank()) {
            System.out.println("Tie detected, drawing one more card each...");
            card1 = player1.playCard();
            card2 = player2.playCard();

            if (card1 == null || card2 == null) {
                return;
            }
        }

        if (card1.getRank() > card2.getRank()) {
            player1.addScore(card1.getRank() + card2.getRank());
        } else if (card1.getRank() < card2.getRank()) {
            player2.addScore(card1.getRank() + card2.getRank());
        }
    }

    public static void main(String[] args) {
        Player player1 = new Player("Alice", 1);
        Player player2 = new Player("Bob", 2);

        WarGame game = new WarGame(player1, player2);

        while (player1.playCard() != null && player2.playCard() != null) {
            game.playRound();
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
    }
}
