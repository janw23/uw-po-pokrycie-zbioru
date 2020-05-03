package cover.interpreter;

public class MatchedObject<T> {

    private final T matchedObject;

    public MatchedObject(T matchedObject) {
        this.matchedObject = matchedObject;
    }

    public T getMatchedObject() {
        return matchedObject;
    }

}