package game;

import java.io.Serializable;

/**
 * Represents a card in the deck with a rank and a suit.
 */
public class Card implements Serializable {
    private int rank; // 2-10, 11=Jack, 12=Queen, 13=King, 14=Ace
    private String suit;

    /**
     * Constructor to initialize the card with a rank and suit.
     * @param rank The rank of the card.
     * @param suit The suit of the card.
     */
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Prints information about the card.
     */
    public void printInfo() {
        System.out.println(this.toString());
    }
}
