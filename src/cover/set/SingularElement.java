package cover.set;

public class SingularElement extends SetElement {

    private final int value;

    public SingularElement(int value) {
        this.value = value;
    }

    @Override
    public boolean contains(int val) {
        return value == val;
    }

    @Override
    public String toString() {
        return "{value: " + value + "}";
    }

}
