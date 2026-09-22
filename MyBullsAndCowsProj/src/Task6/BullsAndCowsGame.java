package Task6;

import java.io.*;
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
        System.out.print("Do you want to use a file for your guesses? (yes/no): ");
        String inputMode = scanner.nextLine().trim().toLowerCase();

        Queue<String> fileGuesses = new LinkedList<>();
        if (inputMode.equals("yes")) {
            fileGuesses = loadGuessesFromFile();
        }

        this.human = new HumanPlayer("Player", codeLength, fileGuesses);

        // Choose AI Difficulty
        Player selectedAI;
        while (true) {
            System.out.print("Choose AI difficulty (easy/medium): ");
            String aiChoice = scanner.nextLine().trim().toLowerCase();
            if (aiChoice.equals("easy")) {
                selectedAI = new ComputerPlayer("Easy AI", codeLength);
                break;
            } else if (aiChoice.equals("medium")) {
                selectedAI = new MediumComputerPlayer("Medium AI", codeLength);
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'easy' or 'medium'.");
            }
        }
        this.ai = selectedAI;

        this.humanSecretCode = human.setSecretCode();
        this.aiSecretCode = CodeGenerator.generateSecretCode(codeLength);

        System.out.println("\n--- Game Start ---");
    }

    public void startGame() {
        for (int round = 1; round <= maxAttempts; round++) {
            System.out.println("\nRound " + round);

            String humanGuess = human.makeGuess();
            Result humanResult = scoreCalculator.calculateBullsAndCows(aiSecretCode, humanGuess);
            System.out.println("You guessed: " + humanGuess + " => " + humanResult);

            String aiGuess = ai.makeGuess();
            Result aiResult = scoreCalculator.calculateBullsAndCows(humanSecretCode, aiGuess);
            System.out.println("AI guessed: " + aiGuess + " => " + aiResult);

            boolean playerWon = humanResult.getBulls() == codeLength;
            boolean aiWon = aiResult.getBulls() == codeLength;

            if (playerWon && aiWon) {
                System.out.println("\nIt's a draw! Both guessed correctly.");
                return;
            } else if (playerWon) {
                System.out.println("\nYou win!");
                return;
            } else if (aiWon) {
                System.out.println("\nAI wins!");
                return;
            }
        }

        System.out.println("\nGame over. It's a draw. The AI's Secret code was: "+aiSecretCode);
    }

    private Queue<String> loadGuessesFromFile() {
        Scanner input = new Scanner(System.in);
        Queue<String> guesses = new LinkedList<>();

        while (true) {
            System.out.print("Enter filename (e.g., guesses.txt): ");
            String filename = input.nextLine();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                int lineNum = 0;
                while ((line = br.readLine()) != null) {
                    lineNum++;
                    String guess = line.trim();
                    if (InputValidator.isValidGuess(guess, codeLength)) {
                        guesses.add(guess);
                    } else {
                        System.out.println("Skipping invalid guess at line " + lineNum + ": \"" + guess + "\"");
                    }
                }

                if (guesses.isEmpty()) {
                    System.out.println("No valid guesses found in the file.");
                } else {
                    System.out.println("Loaded " + guesses.size() + " valid guesses from file.");
                }
                break;
            } catch (IOException e) {
                System.out.println("Invalid file. Please try again.");
            }
        }

        return guesses;
    }

    public static void main(String[] args) {
        new BullsAndCowsGame().startGame();
    }
}
