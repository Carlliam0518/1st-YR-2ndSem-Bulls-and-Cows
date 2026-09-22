package Task6;

import java.util.*;

public class HumanPlayer extends Player {
    private final int codeLength;
    private final Scanner scanner = new Scanner(System.in);
    private Queue<String> fileGuesses;

    public HumanPlayer(String name, int codeLength, Queue<String> fileGuesses) {
        super(name);
        this.codeLength = codeLength;
        this.fileGuesses = fileGuesses;
    }

    @Override
    public String makeGuess() {
        if (fileGuesses != null && !fileGuesses.isEmpty()) {
            String guess = fileGuesses.poll();
            System.out.println("[File] Your guess: " + guess);
            return guess;
        }

        while (true) {
            System.out.print("Enter your guess: ");
            String guess = scanner.nextLine().trim();
            if (InputValidator.isValidGuess(guess, codeLength)) {
                return guess;
            }
            System.out.println("Invalid guess. Try again.");
        }
    }

    @Override
    public String setSecretCode() {
        while (true) {
            System.out.print("Set your secret code: ");
            String code = scanner.nextLine().trim();
            if (InputValidator.isValidGuess(code, codeLength)) {
                return code;
            }
            System.out.println("Invalid code. Please enter " + codeLength + " unique digits.");
        }
    }
}
