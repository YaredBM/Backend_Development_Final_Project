package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements Serializable {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        for (String suit : suits) {
            for (int i = 2; i <= 10; i++) {
                cards.add(new Card(i, suit));
            }
            cards.add(new Card(11, suit)); // Jack
            cards.add(new Card(12, suit)); // Queen
            cards.add(new Card(13, suit)); // King
            cards.add(new Card(14, suit)); // Ace (high)
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }
}
