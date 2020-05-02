package cover.interpreter;

public class MatchedQuery extends MatchedObject {

    private final int first, second;

    public MatchedQuery(int a, int b) {
        matchedObjectType = MATCHED_OBJECT_TYPE_QUERY;
        first = a;
        second = b;
    }

    public int getFirstVal() {
        return first;
    }

    public int getSecondVal() {
        return second;
    }

}
