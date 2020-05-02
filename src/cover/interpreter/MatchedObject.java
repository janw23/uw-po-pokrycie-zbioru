package cover.interpreter;

public abstract class MatchedObject {

    public static final int MATCHED_OBJECT_TYPE_QUERY = 1;
    public static final int MATCHED_OBJECT_TYPE_SET = 2;

    protected int matchedObjectType;

    public int getMatchedObjectType() {
        return matchedObjectType;
    }
}