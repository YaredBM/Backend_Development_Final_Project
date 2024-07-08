package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int id;
    private List<Card> hand;
    private int score;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card playCard() {
        if (hand.isEmpty()) {
            return null;
        }
        return hand.remove(0);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
