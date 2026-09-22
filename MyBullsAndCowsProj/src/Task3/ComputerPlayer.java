package Task3;

import java.util.*;

public class ComputerPlayer extends Player {
    private final int codeLength;
    private final Random random = new Random();

    public ComputerPlayer(String name, int codeLength) {
        super(name);
        this.codeLength = codeLength;
    }

    @Override
    public String makeGuess() {
        List<Character> digits = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) digits.add(c);
        Collections.shuffle(digits);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            sb.append(digits.get(i));
        }
        return sb.toString();
    }

    @Override
    public String setSecretCode() {
        return CodeGenerator.generateSecretCode(codeLength);
    }
}
