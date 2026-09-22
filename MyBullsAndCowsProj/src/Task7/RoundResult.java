package Task7;

public class RoundResult {
    public final int roundNumber;
    public final String humanGuess;
    public final String aiGuess;
    public final Result humanResult;
    public final Result aiResult;

    public RoundResult(int roundNumber, String humanGuess, String aiGuess, Result humanResult, Result aiResult) {
        this.roundNumber = roundNumber;
        this.humanGuess = humanGuess;
        this.aiGuess = aiGuess;
        this.humanResult = humanResult;
        this.aiResult = aiResult;
    }
}
