package game;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a player in the game, managing their hand of cards and score.
 */
public class Player implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Queue<Card> hand;
    private int score;
    private String name;

    /**
     * Constructor to initialize the player with a name.
     * @param name The name of the player.
     */
    public Player(String name) {
        hand = new LinkedList<>();
        score = 0;
        this.name = name;
    }

    public Queue<Card> getHand() {
        return hand;
    }

    public void setHand(Queue<Card> hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a card to the player's hand.
     * @param card The card to add.
     */
    public void addCard(Card card) {
        if (card != null) {
            hand.add(card);
        }
    }

    /**
     * Plays a card from the player's hand.
     * @return The played card.
     */
    public Card playCard() {
        return hand.poll();
    }

    /**
     * Increments the player's score by a specified value.
     * @param value The value to add to the score.
     */
    public void incrementScore(int value) {
        score += value;
    }

    /**
     * Checks if the player has cards remaining.
     * @return true if the player has cards, false otherwise.
     */
    public boolean hasCards() {
        return !hand.isEmpty();
    }

    /**
     * Returns the number of cards in the player's hand.
     * @return The number of cards.
     */
    public int getCardCount() {
        return hand.size();
    }

    /**
     * Adds a list of cards to the bottom of the player's hand.
     * @param cards The list of cards to add.
     */
    public void addCardsToBottom(List<Card> cards) {
        hand.addAll(cards);
    }

    /**
     * Increments the player's score based on the ranks of a list of cards.
     * @param cards The list of cards.
     */
    public void incrementScoreWithCards(List<Card> cards) {
        for (Card card : cards) {
            incrementScore(card.getRank());
        }
        addCardsToBottom(cards);
    }

    /**
     * Prints information about the player.
     */
    public void printInfo() {
        System.out.println("Player: " + name);
        System.out.println("Score: " + score);
        System.out.println("Cards: ");
        for (Card card : hand) {
            card.printInfo();
        }
    }
}
