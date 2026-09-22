package Task8;

import java.util.*;

public class ComputerPlayer extends Player {

    private final int codeLength;
    private final String allowedChars;

    public ComputerPlayer(String name, int codeLength, String allowedChars) {
        super(name);
        this.codeLength = codeLength;
        this.allowedChars = allowedChars;
    }

    @Override
    public String makeGuess() {
        List<Character> chars = new ArrayList<>();
        for (char c : allowedChars.toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);
        StringBuilder guess = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            guess.append(chars.get(i));
        }
        return guess.toString();
    }

    @Override
    public String setSecretCode() {
        return CodeGenerator.generateSecretCode(codeLength, allowedChars);
    }
}

class MediumComputerPlayer extends Player {

    private final int codeLength;
    private final String allowedChars;
    private final Set<String> previousGuesses = new HashSet<>();

    public MediumComputerPlayer(String name, int codeLength, String allowedChars) {
        super(name);
        this.codeLength = codeLength;
        this.allowedChars = allowedChars;
    }

    @Override
    public String makeGuess() {
        String guess;
        do {
            guess = generateGuess();
        } while (previousGuesses.contains(guess));
        previousGuesses.add(guess);
        return guess;
    }

    private String generateGuess() {
        List<Character> chars = new ArrayList<>();
        for (char c : allowedChars.toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);
        StringBuilder guess = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            guess.append(chars.get(i));
        }
        return guess.toString();
    }

    @Override
    public String setSecretCode() {
        return CodeGenerator.generateSecretCode(codeLength, allowedChars);
    }
}
