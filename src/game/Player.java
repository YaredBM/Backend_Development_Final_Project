package game;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Player implements Serializable {
    private Queue<Card> hand;
    private int score;
    private String name;

    public Player(String name) {
        hand = new LinkedList<>();
        score = 0;
        this.name = name;
    }

    public void addCard(Card card) {
        if (card != null) {
            hand.add(card);
        }
    }

    public Card playCard() {
        return hand.poll();
    }

    public void incrementScore(int value) {
        score += value;
    }

    public int getScore() {
        return score;
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public int getCardCount() {
        return hand.size();
    }

    public void addCardsToBottom(List<Card> cards) {
        hand.addAll(cards);
    }

    public void incrementScoreWithCards(List<Card> cards) {
        for (Card card : cards) {
            incrementScore(card.getRank());
        }
        addCardsToBottom(cards);
    }

    public String getName() {
        return name;
    }
}
