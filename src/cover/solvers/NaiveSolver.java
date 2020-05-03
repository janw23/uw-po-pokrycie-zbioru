package cover.solvers;

//definicja obiektu implementującego neiwną
//heurystykę rozwiązania problemu pokrycia zbiorów

import cover.set.FiniteArithmeticProgression;
import cover.set.Set;

import java.util.ArrayList;
import java.util.LinkedList;

public final class NaiveSolver extends Solver {

    @Override
    public ArrayList<Integer> solveSetCoverage
            (FiniteArithmeticProgression Z, ArrayList<Set> R) {

        ArrayList<Integer> solution = new ArrayList<>();
        LinkedList<Integer> leftZElements = new LinkedList<>();

        for (int i = 1; i <= Z.getLimit(); i++)
            leftZElements.add(i);

        for (int i = 0; i < R.size() && !leftZElements.isEmpty(); i++) {
            Set set = R.get(i);

            if (set.anyElementContainsAnyOf(leftZElements)) {
                solution.add(i);
                leftZElements.removeIf(set::anyElementContains);
            }
        }

        return solution;
    }
}
