package Task8;

import java.util.*;

public class CodeGenerator {
    public static String generateSecretCode(int length, String allowedChars) {
        List<Character> chars = new ArrayList<>();
        for (char c : allowedChars.toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(chars.get(i));
        }
        return code.toString();
    }
}
