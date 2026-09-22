package Task8;

import java.util.*;

public class InputValidator {
    public static boolean isValidGuess(String guess, int length, String allowedChars) {
        if (guess.length() != length) return false;
        Set<Character> seen = new HashSet<>();
        for (char c : guess.toCharArray()) {
            if (!allowedChars.contains(String.valueOf(c)) || !seen.add(c)) {
                return false;
            }
        }
        return true;
    }
}
