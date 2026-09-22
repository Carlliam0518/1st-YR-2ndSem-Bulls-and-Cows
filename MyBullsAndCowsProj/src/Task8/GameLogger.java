package Task8;

import java.io.*;
import java.util.*;

public class GameLogger {
    public static void saveGameToFile(String filename, String playerSecret, String computerSecret,
                                      List<RoundResult> rounds, String finalResult) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("=== Bulls and Cows Game Summary ===\n\n");
            writer.write("Player's Secret Code   : " + playerSecret + "\n");
            writer.write("Computer's Secret Code : " + computerSecret + "\n\n");

            writer.write(String.format("%-8s %-15s %-15s %-20s %-20s\n",
                    "Round", "Player Guess", "AI Guess", "Player Result", "AI Result"));
            writer.write("--------------------------------------------------------------------------\n");

            for (RoundResult round : rounds) {
                writer.write(String.format("%-8d %-15s %-15s %-20s %-20s\n",
                        round.getRoundNumber(),
                        round.getPlayerGuess(),
                        round.getAiGuess(),
                        round.getPlayerResult().toString(),
                        round.getAiResult().toString()));
            }

            writer.write("\nResult: " + finalResult + "\n");
            System.out.println("Game successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }
}
