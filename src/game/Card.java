package game;

import java.io.Serializable;

public class Card implements Serializable {
    private int rank; // 2-10, 11=Jack, 12=Queen, 13=King, 14=Ace
    private String suit;

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
