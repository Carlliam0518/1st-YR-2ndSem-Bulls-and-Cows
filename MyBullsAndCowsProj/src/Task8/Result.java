package Task8;

public class Result {
    public int bulls, cows;

    public Result(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    @Override
    public String toString() {
        return bulls + " Bulls, " + cows + " Cows";
    }
}
