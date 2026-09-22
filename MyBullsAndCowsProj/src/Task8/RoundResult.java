package Task8;

public class RoundResult {
    private final int roundNumber;
    private final String playerGuess;
    private final String aiGuess;
    private final Result playerResult;
    private final Result aiResult;

    public RoundResult(int roundNumber, String playerGuess, String aiGuess, Result playerResult, Result aiResult) {
        this.roundNumber = roundNumber;
        this.playerGuess = playerGuess;
        this.aiGuess = aiGuess;
        this.playerResult = playerResult;
        this.aiResult = aiResult;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getPlayerGuess() {
        return playerGuess;
    }

    public String getAiGuess() {
        return aiGuess;
    }

    public Result getPlayerResult() {
        return playerResult;
    }

    public Result getAiResult() {
        return aiResult;
    }
}
