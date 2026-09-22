package Task3;

import java.util.*;

public class BullsAndCowsGame {
    private final int codeLength = 4;
    private final int maxAttempts = 7;

    private final Player human;
    private final Player ai;

    private final String humanSecretCode;
    private final String aiSecretCode;

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();
    private final Set<String> aiPreviousGuesses = new HashSet<>();

    public BullsAndCowsGame() {
        this.human = new HumanPlayer("Player", codeLength);
        this.ai = new ComputerPlayer("AI", codeLength);

        this.humanSecretCode = human.setSecretCode();
        this.aiSecretCode = CodeGenerator.generateSecretCode(codeLength);

        System.out.println("\n--- Game Start ---");
    }

    public void startGame() {
        for (int round = 1; round <= maxAttempts; round++) {
            System.out.println("\nRound " + round);

            // Player's turn to guess
            String humanGuess = human.makeGuess();
            if (!InputValidator.isValidGuess(humanGuess, codeLength)) {
                System.out.println("Invalid input. Try again.");
                round--; // Retry same round
                continue;
            }

            Result humanResult = scoreCalculator.calculateBullsAndCows(aiSecretCode, humanGuess);
            System.out.println("You guessed: " + humanGuess + " => " + humanResult);
            
            // AI's turn to guess
            String aiGuess = getAIGuess();
            Result aiResult = scoreCalculator.calculateBullsAndCows(humanSecretCode, aiGuess);
            System.out.println("AI guessed: " + aiGuess + " => " + aiResult);

            // Check for win conditions
            boolean playerWon = humanResult.getBulls() == codeLength;
            boolean aiWon = aiResult.getBulls() == codeLength;

            if (playerWon && aiWon) {
                System.out.println("\nIt's a draw! Both guessed correctly.");
                return;
            } else if (playerWon) {
                System.out.println("\nYou win! You guessed the AI's code.");
                return;
            } else if (aiWon) {
                System.out.println("\nAI wins! It guessed your code.");
                return;
            }
        }

        System.out.println("\nGame over. Neither player guessed the code. It's a draw.");
        System.out.println("The AI's Secret code is: " + aiSecretCode);
        System.out.println("The Player's Secret code is: " + humanSecretCode);
    }

    private String getAIGuess() {
        String guess;
        do {
            guess = ai.makeGuess();
        } while (aiPreviousGuesses.contains(guess));
        aiPreviousGuesses.add(guess);
        return guess;
    }

    public static void main(String[] args) {
        new BullsAndCowsGame().startGame();
    }
}
