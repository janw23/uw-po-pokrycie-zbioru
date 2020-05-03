package cover.solvers;

//uogólnienie obiektu służącego do wyznaczania
//rozwiązania problemu pokrycia zbioru

import cover.set.FiniteArithmeticProgression;
import cover.set.Set;

import java.util.ArrayList;

public abstract class Solver {

    public abstract ArrayList<Integer> solveSetCoverage
            (FiniteArithmeticProgression Z, ArrayList<Set> R);

}
