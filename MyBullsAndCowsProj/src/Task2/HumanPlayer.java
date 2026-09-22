package Task2;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner = new Scanner(System.in);
    private final int codeLength;

    public HumanPlayer(String name, int codeLength) {
        super(name);
        this.codeLength = codeLength;
    }

    public String makeGuess() {
        System.out.print("Enter your " + codeLength + "-digit guess: ");
        return scanner.nextLine().trim();
    }
}
