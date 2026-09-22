package Task6;

import java.util.*;

public class ComputerPlayer extends Player {

    private final int codeLength;

    public ComputerPlayer(String name, int codeLength) {
        super(name);
        this.codeLength = codeLength;
    }

    @Override
    public String makeGuess() {
        List<Character> digits = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) {
            digits.add(c);
        }
        Collections.shuffle(digits);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            sb.append(digits.get(i));
        }
        return sb.toString();
    }

    public String setSecretCode() {
        return CodeGenerator.generateSecretCode(codeLength);
    }
}

class MediumComputerPlayer extends Player {

    private final int codeLength;
    private final Set<String> previousGuesses = new HashSet<>();

    public MediumComputerPlayer(String name, int codeLength) {
        super(name);
        this.codeLength = codeLength;
    }

    @Override
    public String makeGuess() {
        String guess;
        do {
            guess = generateUniqueCode();
        } while (previousGuesses.contains(guess));
        previousGuesses.add(guess);
        return guess;
    }

    public String setSecretCode() {
        return CodeGenerator.generateSecretCode(codeLength);
    }

    private String generateUniqueCode() {
        List<Character> digits = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) {
            digits.add(c);
        }
        Collections.shuffle(digits);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            sb.append(digits.get(i));
        }
        return sb.toString();
    }
}
