package Task3;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final int codeLength;
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name, int codeLength) {
        super(name);
        this.codeLength = codeLength;
    }

    @Override
    public String makeGuess() {
        System.out.print("Enter your guess: ");
        return scanner.nextLine().trim();
    }

    @Override
    public String setSecretCode() {
        while (true) {
            System.out.print("Set your secret 4 digit code: ");
            String code = scanner.nextLine().trim();
            if (InputValidator.isValidGuess(code, codeLength)) {
                return code;
            }
            System.out.println("Invalid code. Please enter " + codeLength + " unique digits.");
        }
    }
}
