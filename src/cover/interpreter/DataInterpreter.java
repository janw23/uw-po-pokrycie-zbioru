package cover.interpreter;

import cover.set.Set;
import java.util.ArrayList;

//definicja obiektu interpretującego dane
//określające instancję problemu pokrycia zbioru

public class DataInterpreter {

    //zbiór "R", z którego wybierane są następnie zbiory
    //do pokrycia zbioru z zapytania
    private ArrayList<Set> coveringSourceSets;
    private final InputMatcher inputMatcher;

    public DataInterpreter() {
        coveringSourceSets = new ArrayList<>();
        inputMatcher = new InputMatcher();
    }

    //przygotowuje interpreter do przetwarzania
    //nowego zestawu danych
    public void reset() {
        coveringSourceSets.clear();
    }

    private String processQuery(MatchedQuery query) {
        return "";
    }

    public String processNextLine(String line) {
        StringBuilder output = new StringBuilder("");
        String[] strings = line.split("\\s+");

        if (strings.length == 0)
            return "";

        for (String string : strings) {
            int val = Integer.parseInt(string);

            MatchedObject match = inputMatcher.processNextInt(val);

            if (match != null) {
                inputMatcher.reset();
                int matchType = match.getMatchedObjectType();

                switch (matchType) {
                    case MatchedObject.MATCHED_OBJECT_TYPE_QUERY:
                        output.append(processQuery((MatchedQuery) match));
                        System.err.println("Query"); //DEBUG
                        break;

                    case MatchedObject.MATCHED_OBJECT_TYPE_SET:
                        Set matchedSet = ((MatchedSet) match).getMatchedSet();
                        coveringSourceSets.add(matchedSet);
                        System.err.println(matchedSet.toString()); //DEBUG
                        break;
                }
            }
        }

        return output.toString();
    }

}
