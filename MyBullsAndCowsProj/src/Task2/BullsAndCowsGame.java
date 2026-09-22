package Task2;

import java.util.*;

public class BullsAndCowsGame {

    private final String secretCode;
    private final int maxAttempts = 7;
    private int currentAttempt = 0;
    private final Player player;
    private final ScoreCalculator scoreCalculator = new ScoreCalculator();
    private final int codeLength = 4;

    public BullsAndCowsGame() {
        this.secretCode = CodeGenerator.generateSecretCode(codeLength);
        this.player = new HumanPlayer("Player", codeLength);
    }

    public void startGame() {
        System.out.println("Welcome to Bulls and Cows!");

        while (currentAttempt < maxAttempts) {
            String guess = player.makeGuess();

            if (!InputValidator.isValidGuess(guess, codeLength)) {
                System.out.println("Invalid input. Enter " + codeLength + " unique digits.");
                continue;
            }

            currentAttempt++;
            Result result = scoreCalculator.calculateBullsAndCows(secretCode, guess);
            System.out.println("Attempt " + currentAttempt + ": " + result);
            System.out.println("----");

            if (secretCode.equals(guess)) {
                System.out.println("Congratulations! You guessed the code!");
                return;
            }
        }

        System.out.println("Game over. The secret code was: " + secretCode);
    }

    private boolean isValidGuess(String guess) {
        if (guess.length() != codeLength) {
            return false;
        }
        if (!guess.matches("\\d+")) {
            return false;
        }
        return guess.chars().distinct().count() == codeLength;
    }

    public static void main(String[] args) {
        new BullsAndCowsGame().startGame();
    }
}
