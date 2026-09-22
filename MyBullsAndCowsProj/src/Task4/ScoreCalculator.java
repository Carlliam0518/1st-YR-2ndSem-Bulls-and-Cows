package Task4;

import Task3.*;

public class ScoreCalculator {
    public Result calculateBullsAndCows(String secret, String guess) {
        int bulls = 0, cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                bulls++;
            } else if (secret.contains(String.valueOf(guess.charAt(i)))) {
                cows++;
            }
        }
        return new Result(bulls, cows);
    }
}
