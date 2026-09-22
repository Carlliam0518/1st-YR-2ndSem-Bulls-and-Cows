
package Task2;

import java.util.HashSet;
import java.util.Set;

public class InputValidator {

    public static boolean isValidGuess(String guess, int codeLength, String allowedChars) {
        if (guess.length() != codeLength) return false;

        Set<Character> seen = new HashSet<>();
        for (char c : guess.toCharArray()) {
            if (!allowedChars.contains(String.valueOf(c)) || !seen.add(c)) {
                return false;
            }
        }
        return true;
    }

    // Overloaded version for digits only
    public static boolean isValidGuess(String guess, int codeLength) {
        return isValidGuess(guess, codeLength, "0123456789");
    }
}
