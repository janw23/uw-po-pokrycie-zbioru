package cover.interpreter;

import cover.set.Set;

public class MatchedSet extends MatchedObject {

    private final Set matchedSet;

    public MatchedSet(Set matchedSet) {
        matchedObjectType = MATCHED_OBJECT_TYPE_SET;
        this.matchedSet = matchedSet;
    }

    public Set getMatchedSet() {
        return this.matchedSet;
    }

}
