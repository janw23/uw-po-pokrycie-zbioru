package cover.set;

public class FiniteArithmeticProgression extends SetElement {

    private final int start, delta, limit;

    public FiniteArithmeticProgression(int start, int delta, int limit) {
        this.start = start;
        this.delta = delta;
        this.limit = limit;
    }

    @Override
    public boolean contains(int val) {
        return val >= start && val <= limit && (val - start) % delta == 0;
    }

    public int getStart() {
        return start;
    }

    public int getDelta() {
        return delta;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "{start: " + start +
                ", delta: " + delta +
                ", limit: " + limit + "}";
    }
}
