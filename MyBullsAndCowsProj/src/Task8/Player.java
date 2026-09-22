package Task8;

public abstract class Player {
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public abstract String makeGuess();
    public abstract String setSecretCode();
}
