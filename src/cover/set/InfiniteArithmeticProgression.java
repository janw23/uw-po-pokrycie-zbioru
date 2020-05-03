package cover.set;

public class InfiniteArithmeticProgression extends SetElement {

    private final int start, delta;

    public InfiniteArithmeticProgression(int start, int delta) {
        this.start = start;
        this.delta = delta;
    }

    @Override
    public boolean contains(int val) {
        return val >= start && (val - start) % delta == 0;
    }

    public int getStart() {
        return start;
    }

    public int getDelta() {
        return delta;
    }

    @Override
    public String toString() {
        return "{start: " + start +
                ", delta: " + delta + "}";
    }
}
