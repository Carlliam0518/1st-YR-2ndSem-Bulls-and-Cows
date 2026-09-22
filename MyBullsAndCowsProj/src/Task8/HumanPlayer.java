package Task8;

import java.util.*;

public class HumanPlayer extends Player {
    private final int codeLength;
    private final String allowedChars;
    private final Scanner scanner = new Scanner(System.in);
    private final Queue<String> fileGuesses;

    public HumanPlayer(String name, int codeLength, String allowedChars, Queue<String> fileGuesses) {
        super(name);
        this.codeLength = codeLength;
        this.allowedChars = allowedChars;
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
            if (InputValidator.isValidGuess(guess, codeLength, allowedChars)) {
                return guess;
            }
            System.out.println("Invalid guess. Allowed characters: " + allowedChars);
        }
    }

    @Override
    public String setSecretCode() {
        while (true) {
            System.out.print("Set your secret code: ");
            String code = scanner.nextLine().trim();
            if (InputValidator.isValidGuess(code, codeLength, allowedChars)) {
                return code;
            }
            System.out.println("Invalid code. Must be " + codeLength + " unique characters from: " + allowedChars);
        }
    }
}
