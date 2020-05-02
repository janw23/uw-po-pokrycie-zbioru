package cover.set;

public class InfiniteArithmeticProgression extends ArithmeticProgressionElement {

    private final int start, delta;

    public InfiniteArithmeticProgression(int start, int delta) {
        this.start = start;
        this.delta = delta;
    }

    @Override
    public String toString() {
        return "{start: " + start +
                ", delta: " + delta + "}";
    }
}
