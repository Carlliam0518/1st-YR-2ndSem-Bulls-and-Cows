package Task4;

import Task3.*;
import java.util.HashSet;
import java.util.Set;

public class InputValidator {
    public static boolean isValidGuess(String guess, int codeLength) {
        if (guess.length() != codeLength || !guess.matches("\\d+")) return false;
        Set<Character> seen = new HashSet<>();
        for (char c : guess.toCharArray()) {
            if (!seen.add(c)) return false;
        }
        return true;
    }
}
