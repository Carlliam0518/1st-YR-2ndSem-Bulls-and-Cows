package Task4;

import java.util.*;

public class BullsAndCowsGame {
    private final int codeLength = 4;
    private final int maxAttempts = 7;

    private final Player human;
    private final Player ai;

    private final String humanSecretCode;
    private final String aiSecretCode;

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();
    private final Scanner scanner = new Scanner(System.in);

    public BullsAndCowsGame() {
        this.human = new HumanPlayer("Player", codeLength);
        this.ai = chooseAI();

        this.humanSecretCode = human.setSecretCode();
        this.aiSecretCode = CodeGenerator.generateSecretCode(codeLength);

        System.out.println("\n--- Game Start ---");
    }

    public void startGame() {
        for (int round = 1; round <= maxAttempts; round++) {
            System.out.println("\nRound " + round);

            // Human's guess
            String humanGuess = human.makeGuess();
            if (!InputValidator.isValidGuess(humanGuess, codeLength)) {
                System.out.println("Invalid input. Try again.");
                round--; // retry
                continue;
            }

            Result humanResult = scoreCalculator.calculateBullsAndCows(aiSecretCode, humanGuess);
            System.out.println("You guessed: " + humanGuess + " => " + humanResult);

            // AI's guess
            String aiGuess = ai.makeGuess();
            Result aiResult = scoreCalculator.calculateBullsAndCows(humanSecretCode, aiGuess);
            System.out.println("AI guessed: " + aiGuess + " => " + aiResult);

            // Win conditions
            boolean humanWins = humanResult.getBulls() == codeLength;
            boolean aiWins = aiResult.getBulls() == codeLength;

            if (humanWins && aiWins) {
                System.out.println("\nIt's a draw! Both guessed correctly.");
                return;
            } else if (humanWins) {
                System.out.println("\nYou win!");
                return;
            } else if (aiWins) {
                System.out.println("\nAI wins!");
                return;
            }
        }

        System.out.println("\nGame over. No one guessed the code. It's a draw. The AI Secret code was:" + aiSecretCode);
    }

    private Player chooseAI() {
        System.out.print("Choose AI difficulty (easy/medium): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("medium")) {
            return new MediumComputerPlayer("Medium AI", codeLength);
        }
        return new ComputerPlayer("Easy AI", codeLength);
    }

    public static void main(String[] args) {
        new BullsAndCowsGame().startGame();
    }
}
