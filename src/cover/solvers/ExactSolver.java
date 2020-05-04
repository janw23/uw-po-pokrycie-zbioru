package cover.solvers;

//definicja obiektu implementującego dokładne
//rozwiązanie problemu pokrycia zbiorów

import cover.set.FiniteArithmeticProgression;
import cover.set.Set;

import java.util.ArrayList;

public final class ExactSolver extends Solver {

    //sprawdza, czy zaproponowane zbiory użyte do pokrycia
    //(te, które odpowiadają wartościom true z [state])
    //faktycznie pokrywają zbiór Z
    private boolean checkCovering
            (boolean[] state, ArrayList<Set> R, int ZMax) {

        ArrayList<Set> subR = new ArrayList<>();

        for (int i = 0; i < state.length; i++)
            if (state[i]) subR.add(R.get(i));

        for (int val = 1; val <= ZMax; val++) {
            boolean anyContains = false;

            for (Set set : subR) {
                if ((anyContains = set.anyElementContains(val)))
                    break;
            }

            if (!anyContains)
                return false;
        }

        return true;
    }

    @Override
    public ArrayList<Integer> solveSetCoverage
            (FiniteArithmeticProgression Z, ArrayList<Set> R) {

        BinaryStateIterator binIter = new BinaryStateIterator(R.size());

        //przechodzenie po liczbie wybranych zbiorów z rodziny R
        for (int setsCount = 1; setsCount <= R.size(); setsCount++) {
            binIter.init(setsCount);

            //sprawdzanie wszystkich podzbiorów mocy [setsCount]
            //zbioru R w kolejności leksykograficznej
            do {
                boolean[] state = binIter.getState();

                if (checkCovering(state, R, Z.getLimit())) {
                    ArrayList<Integer> solution = new ArrayList<>();

                    for (int i = 0; i < state.length; i++)
                        if (state[i]) solution.add(i);

                    return solution;
                }
            } while (binIter.nextState());
        }

        return null;
    }
}
