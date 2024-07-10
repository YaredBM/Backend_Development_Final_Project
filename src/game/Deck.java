package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Manages a deck of cards, including shuffling and dealing cards.
 */
public class Deck implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Card> cards;

    /**
     * Constructor to initialize the deck with cards.
     */
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

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Shuffles the deck using a random number generator.
     */
    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }

    /**
     * Deals a card from the top of the deck.
     * @return The dealt card.
     */
    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    /**
     * Checks if the deck has cards remaining.
     * @return true if the deck has cards, false otherwise.
     */
    public boolean hasCards() {
        return !cards.isEmpty();
    }

    /**
     * Returns the number of cards in the deck.
     * @return The size of the deck.
     */
    public int size() {
        return cards.size();
    }

    /**
     * Prints information about the deck.
     */
    public void printInfo() {
        for (Card card : cards) {
            card.printInfo();
        }
    }
}
