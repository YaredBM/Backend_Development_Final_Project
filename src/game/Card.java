package game;

import java.io.Serializable; // Import the Serializable interface from the java.io package


// It represents a card in the deck with a rank and a suit.

public class Card implements Serializable { // The Card class implements Serializable, to serialize the objects of this class
    private int rank; // 2-10, 11=Jack, 12=Queen, 13=King, 14=Ace
    private String suit; // The suit of the card (e.g., "Hearts", "Diamonds", "Clubs", "Spades")

     // Create a constructor to initialize the card with a rank and suit.
     // @param rank The rank of the card.
     // @param suit The suit of the card.
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Create Getters and setters 
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
    public String toString() { // Override the toString() method to return a string representation of the card
        return rank + " of " + suit;
    }

    // Print the information about the card.
    public void printInfo() { // Create a method to print the card's information to the console
        System.out.println(this.toString()); // Print the result of the toString() method
    }
}
