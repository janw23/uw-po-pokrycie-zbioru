package cover.set;

public class FiniteArithemticProgression extends ArithmeticProgressionElement {

    private final int start, delta, limit;

    public FiniteArithemticProgression(int start, int delta, int limit) {
        this.start = start;
        this.delta = delta;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "{start: " + start +
                ", delta: " + delta +
                ", limit: " + limit + "}";
    }
}
