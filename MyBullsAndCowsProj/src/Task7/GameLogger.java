package Task7;

import java.io.*;
import java.util.*;

public class GameLogger {
    public static void saveGameToFile(String filename, String playerCode, String aiCode,
                                      List<RoundResult> rounds, String outcome) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("==== Bulls and Cows Game Log ====\n\n");
            writer.write("Player's Secret Code  : " + playerCode + "\n");
            writer.write("Computer's Secret Code: " + aiCode + "\n\n");

            writer.write(String.format("%-8s %-15s %-15s %-18s %-18s\n",
                    "Round", "Player Guess", "AI Guess", "Player Result", "AI Result"));
            writer.write("------------------------------------------------------------------\n");

            for (RoundResult r : rounds) {
                writer.write(String.format("%-8d %-15s %-15s %-18s %-18s\n",
                        r.roundNumber,
                        r.humanGuess,
                        r.aiGuess,
                        r.humanResult,
                        r.aiResult));
            }

            writer.write("\nGame Outcome: " + outcome + "\n");
            System.out.println("Game saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
