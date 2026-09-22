/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task8;

import java.util.*;

public class Config {
    public static final int MAX_ATTEMPTS = 7;
    public static final int CODE_LENGTH = 4;
    public static final List<Character> ALLOWED_CHARACTERS = createAllowedCharacters();

    private static List<Character> createAllowedCharacters() {
        List<Character> chars = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) {
            chars.add(c);
        }
        return chars;

        // Uncomment below to use custom character sets:
        // return Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
    }
}

