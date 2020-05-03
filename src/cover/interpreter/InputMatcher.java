package cover.interpreter;

import cover.set.*;

import java.util.ArrayList;

public class InputMatcher {

    private static final int INT_TYPE_U = 1; // < 0
    private static final int INT_TYPE_D = 2; // > 0
    private static final int INT_TYPE_N = 3; // = 0

    private static final int MATCHING_TYPE_NONE = -1;
    private static final int MATCHING_TYPE_QUERY = 1;
    private static final int MATCHING_TYPE_SET_SINGULAR = 2;
    private static final int MATCHING_TYPE_SET_FINITE = 3;
    private static final int MATCHING_TYPE_SET_INFINITE = 4;
    private static final int MATCHING_TYPE_SET_EMPTY = 5;

    private final ArrayList<Integer> currentMatchingTypes;
    private final ArrayList<Integer> currentMatchingValues;
    private Set currentMatchingSet;

    //zamienić w przyszłości na drzewo
    private int determineMatching(int nextIntType) {

        int length = currentMatchingTypes.size();

        if (length == 0) {
            if (nextIntType == INT_TYPE_N)
                return MATCHING_TYPE_SET_EMPTY;

            return MATCHING_TYPE_NONE;
        }

        if (currentMatchingTypes.get(0) == INT_TYPE_U)
            return MATCHING_TYPE_QUERY;

        if (nextIntType == INT_TYPE_N || nextIntType == INT_TYPE_D) {
            if (length == 1) return MATCHING_TYPE_SET_SINGULAR;
            if (length == 2) return MATCHING_TYPE_SET_INFINITE;
            if (length == 3) return MATCHING_TYPE_SET_FINITE;
        }

        return MATCHING_TYPE_NONE;
    }

    public InputMatcher() {
        currentMatchingTypes = new ArrayList<>();
        currentMatchingValues = new ArrayList<>();
        currentMatchingSet = new Set();
    }

    private void clearCache() {
        currentMatchingTypes.clear();
        currentMatchingValues.clear();
    }

    //powinno być wywołane po zwróceniu przez
    //processNextInt() wartości różnej od null
    public void reset() {
        clearCache();
        currentMatchingSet = new Set();
    }

    private void currentMatchingAdd(int val, int type) {
        currentMatchingTypes.add(type);
        currentMatchingValues.add(val);
    }

    private SetElement createMatchedSetElement(int matching) {
        SetElement matchedElement;
        ArrayList<Integer> CMV = currentMatchingValues;

        switch (matching) {
            case MATCHING_TYPE_SET_FINITE:
                matchedElement = new FiniteArithmeticProgression
                        (CMV.get(0), -CMV.get(1), -CMV.get(2));
                break;

            case MATCHING_TYPE_SET_INFINITE:
                matchedElement = new InfiniteArithmeticProgression
                        (CMV.get(0), -CMV.get(1));
                break;

            case MATCHING_TYPE_SET_SINGULAR:
                matchedElement = new SingularElement(CMV.get(0));
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + matching);
        }

        return matchedElement;
    }

    //przetwarza następną wejściową wartość
    //jeśli powstało dopasowanie, to zwraca dopasowany obiekt
    //jeśli nie, to zwraca null
    public MatchedObject processNextInt(int val) {

        int type = getIntType(val);
        int matching = determineMatching(type);

        if (matching == MATCHING_TYPE_QUERY) {
            int query_first = currentMatchingValues.get(0);
            clearCache();
            return new MatchedObject<Query>(new Query(-query_first, val));
        }
        if (matching == MATCHING_TYPE_SET_EMPTY) {
            clearCache();
            return new MatchedObject<Set>(currentMatchingSet);
        }
        if (matching == MATCHING_TYPE_SET_FINITE
                || matching == MATCHING_TYPE_SET_INFINITE
                || matching == MATCHING_TYPE_SET_SINGULAR) {

            currentMatchingSet.add(createMatchedSetElement(matching));
            clearCache();

            if (type == INT_TYPE_N)
                return new MatchedObject<Set>(currentMatchingSet);
        }

        currentMatchingAdd(val, type);
        return null;
    }

    private int getIntType(int val) {
        if (val == 0) return INT_TYPE_N;
        if (val > 0) return INT_TYPE_D;
        return INT_TYPE_U;
    }

}
