package game;

import java.util.Scanner;

public class LoadGame {
    public static void main(String[] args) {
        String[] savedGames = SaveGame.listSavedGames();
        if (savedGames == null || savedGames.length == 0) {
            System.out.println("No saved games found.");
            return;
        }

        System.out.println("Saved games:");
        for (int i = 0; i < savedGames.length; i++) {
            System.out.println((i + 1) + ". " + savedGames[i]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the game you want to load: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > savedGames.length) {
            System.out.println("Invalid choice.");
            return;
        }

        String selectedGame = savedGames[choice - 1];
        WarGame game = SaveGame.load(selectedGame);

        if (game != null) {
            System.out.println("Resuming game...");
            while (true) {
                game.playRound();
                if (game.getPlayer1().playCard() == null || game.getPlayer2().playCard() == null) {
                    break;
                }
            }

            System.out.println(game.getPlayer1().getName() + " score: " + game.getPlayer1().getScore());
            System.out.println(game.getPlayer2().getName() + " score: " + game.getPlayer2().getScore());

            if (game.getPlayer1().getScore() > game.getPlayer2().getScore()) {
                System.out.println(game.getPlayer1().getName() + " wins!");
            } else if (game.getPlayer2().getScore() > game.getPlayer1().getScore()) {
                System.out.println(game.getPlayer2().getName() + " wins!");
            } else {
                System.out.println("It's a tie!");
            }
        }
    }
}
