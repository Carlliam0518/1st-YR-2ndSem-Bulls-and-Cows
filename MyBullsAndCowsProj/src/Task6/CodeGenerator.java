package Task6;

import java.util.*;

public class CodeGenerator {
    public static String generateSecretCode(int length) {
        List<Character> digits = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) digits.add(c);
        Collections.shuffle(digits);
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(digits.get(i));
        }
        return code.toString();
    }
}
