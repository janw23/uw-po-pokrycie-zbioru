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

    private String processQuery(Query query) {
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

                Object matchedObject = match.getMatchedObject();

                if (matchedObject instanceof Query) {
                    Query matchedQuery = (Query) matchedObject;
                    output.append(processQuery(matchedQuery));

                    //DEBUG
                    System.err.println("Query(" + matchedQuery.getFirst()
                            + "; " + matchedQuery.getSecond() + ")");

                } else if (matchedObject instanceof Set) {
                    Set matchedSet = (Set) matchedObject;
                    coveringSourceSets.add(matchedSet);

                    System.err.println(matchedSet.toString()); //DEBUG
                }
            }
        }

        return output.toString();
    }

}
