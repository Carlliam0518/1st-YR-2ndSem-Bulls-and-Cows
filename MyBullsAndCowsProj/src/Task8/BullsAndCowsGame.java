package Task8;

import java.io.*;
import java.util.*;

public class BullsAndCowsGame {
    private final Scanner scanner = new Scanner(System.in);

    private int codeLength = 4;
    private int maxAttempts = 7;
    private String allowedChars = "0123456789";

    private Player human;
    private Player ai;

    private String humanSecretCode;
    private String aiSecretCode;

    private final ScoreCalculator scoreCalculator = new ScoreCalculator();
    private final List<RoundResult> roundHistory = new ArrayList<>();

    public BullsAndCowsGame() {
        Player selectedAI;
        boolean isEasy = false;

        // Choose AI difficulty first
        while (true) {
            System.out.print("Choose AI difficulty (easy/medium): ");
            String aiChoice = scanner.nextLine().trim().toLowerCase();
            if (aiChoice.equals("easy")) {
                isEasy = true;
                selectedAI = new ComputerPlayer("Easy AI", codeLength, allowedChars);
                break;
            } else if (aiChoice.equals("medium")) {
                // Use default config for Medium AI
                System.out.println("Using default configuration for Medium AI.");
                selectedAI = new MediumComputerPlayer("Medium AI", 4, "0123456789");
                codeLength = 4;
                maxAttempts = 7;
                allowedChars = "0123456789";
                break;
            } else {
                System.out.println("Invalid input. Please enter 'easy' or 'medium'.");
            }
        }

        // Only allow config if Easy AI is selected
        if (isEasy) {
            configureSettings();
            selectedAI = new ComputerPlayer("Easy AI", codeLength, allowedChars);
        }

        this.ai = selectedAI;

        System.out.print("Use a file for your guesses? (yes/no): ");
        String inputMode = scanner.nextLine().trim().toLowerCase();

        Queue<String> fileGuesses = inputMode.equals("yes") ? loadGuessesFromFile() : new LinkedList<>();
        this.human = new HumanPlayer("Player", codeLength, allowedChars, fileGuesses);

        this.humanSecretCode = human.setSecretCode();
        this.aiSecretCode = CodeGenerator.generateSecretCode(codeLength, allowedChars);

        System.out.println("\n--- Game Start ---");
    }

    private void configureSettings() {
        System.out.print("Use custom configuration? (yes/no): ");
        String custom = scanner.nextLine().trim().toLowerCase();

        if (custom.equals("yes")) {
            System.out.print("Enter code length (default 4): ");
            codeLength = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter max number of attempts (default 7): ");
            maxAttempts = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter allowed characters (default 0123456789): ");
            allowedChars = scanner.nextLine().trim();

            System.out.println("Configuration set: " + codeLength + " characters, " + maxAttempts +
                    " attempts, allowed chars: " + allowedChars);
        } else {
            System.out.println("Using default settings.");
        }
    }

    public void startGame() {
        String outcome = "Draw";

        for (int round = 1; round <= maxAttempts; round++) {
            System.out.println("\nRound " + round);

            String humanGuess = human.makeGuess();
            Result humanResult = scoreCalculator.calculateBullsAndCows(aiSecretCode, humanGuess);
            System.out.println("You guessed: " + humanGuess + " => " + humanResult);

            String aiGuess = ai.makeGuess();
            Result aiResult = scoreCalculator.calculateBullsAndCows(humanSecretCode, aiGuess);
            System.out.println("AI guessed: " + aiGuess + " => " + aiResult);

            roundHistory.add(new RoundResult(round, humanGuess, aiGuess, humanResult, aiResult));

            if (humanResult.bulls == codeLength && aiResult.bulls == codeLength) {
                outcome = "Draw (both guessed correctly)";
                break;
            } else if (humanResult.bulls == codeLength) {
                outcome = "Player wins!";
                break;
            } else if (aiResult.bulls == codeLength) {
                outcome = "AI wins!";
                break;
            }
        }

        System.out.println("\nGame Over: " + outcome + " The AI's Secret Code was: " +aiSecretCode);;
        promptToSave(outcome);
    }

    private void promptToSave(String outcome) {
        System.out.print("\nSave the game result to file? (yes/no): ");
        String save = scanner.nextLine().trim().toLowerCase();
        if (save.equals("yes")) {
            System.out.print("Enter filename to save (e.g., result.txt): ");
            String filename = scanner.nextLine();
            GameLogger.saveGameToFile(filename, humanSecretCode, aiSecretCode, roundHistory, outcome);
        }
    }

    private Queue<String> loadGuessesFromFile() {
        Queue<String> guesses = new LinkedList<>();
        while (true) {
            System.out.print("Enter filename: ");
            String filename = scanner.nextLine();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                int lineNum = 0;
                while ((line = br.readLine()) != null) {
                    lineNum++;
                    String guess = line.trim();
                    if (InputValidator.isValidGuess(guess, codeLength, allowedChars)) {
                        guesses.add(guess);
                    } else {
                        System.out.println("Invalid guess at line " + lineNum + ": \"" + guess + "\"");
                    }
                }
                break;
            } catch (IOException e) {
                System.out.println("Could not read file. Try again.");
            }
        }
        return guesses;
    }

    public static void main(String[] args) {
        new BullsAndCowsGame().startGame();
    }
}
