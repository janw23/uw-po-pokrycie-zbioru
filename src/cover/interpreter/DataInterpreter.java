package cover.interpreter;

import cover.set.FiniteArithmeticProgression;
import cover.set.Set;
import cover.solvers.ExactSolver;
import cover.solvers.GreedySolver;
import cover.solvers.NaiveSolver;
import cover.solvers.Solver;

import java.util.ArrayList;

//definicja obiektu interpretującego dane
//określające instancję problemu pokrycia zbioru

public class DataInterpreter {

    //zbiór "R", z którego wybierane są następnie zbiory
    //do pokrycia zbioru z zapytania
    private final ArrayList<Set> coveringSourceSets;
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

        FiniteArithmeticProgression Z =
                new FiniteArithmeticProgression
                        (1, 1, query.getFirst());

        Solver solver;
        int targetSolver = query.getSecond();

        if (targetSolver == 1) {
            solver = new ExactSolver();
        } else if (targetSolver == 2) {
            solver = new GreedySolver();
        } else if (targetSolver == 3) {
            solver = new NaiveSolver();
        } else return "";

        ArrayList<Integer> solution =
                solver.solveSetCoverage(Z, coveringSourceSets);

        if (solution == null)
            return "0";

        solution.sort(Integer::compareTo);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < solution.size(); i++) {
            stringBuilder.append(String.valueOf(solution.get(i) + 1));

            if (i < solution.size() - 1)
                stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    //przetwarza następną linię tekstu z wejścia
    //i zwraca odpowiedź na przetworzone dane
    public String processNextLine(String line) {
        StringBuilder output = new StringBuilder("");
        String[] strings = line.split("\\s+");

        if (strings.length == 0)
            return "";

        for (String string : strings) {
            if (string.equals(""))
                continue;

            int val = Integer.parseInt(string);

            //próbuje dopasować następną otrzymaną wartość
            //do interpretacji, którą potencjalnie tworzy z poprzednimi
            Object matchedObject = inputMatcher.processNextInt(val);

            if (matchedObject != null) {
                inputMatcher.reset();

                if (matchedObject instanceof Query) {
                    Query matchedQuery = (Query) matchedObject;
                    output.append(processQuery(matchedQuery));

                    //DEBUG
                    //System.err.println("Query(" + matchedQuery.getFirst()
                    //        + "; " + matchedQuery.getSecond() + ")");

                } else if (matchedObject instanceof Set) {
                    Set matchedSet = (Set) matchedObject;
                    coveringSourceSets.add(matchedSet);

                    //System.err.println(matchedSet.toString()); //DEBUG
                }
            }
        }

        return output.toString();
    }

}
