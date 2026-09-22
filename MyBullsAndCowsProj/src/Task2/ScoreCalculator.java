package Task2;

public class ScoreCalculator {
    public Result calculateBullsAndCows(String secretCode, String guess) {
        int bulls = 0, cows = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                bulls++;
            } else if (secretCode.contains(Character.toString(guess.charAt(i)))) {
                cows++;
            }
        }
        return new Result(bulls, cows);
    }
}
