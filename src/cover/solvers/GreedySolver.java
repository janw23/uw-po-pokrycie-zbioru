package cover.solvers;

//definicja obiektu implementującego zachłanną
//heurystykę rozwiązania problemu pokrycia zbiorów

import cover.set.FiniteArithmeticProgression;
import cover.set.Set;

import java.util.ArrayList;
import java.util.LinkedList;

public final class GreedySolver extends Solver {

    private int getCountOfElementsContained
            (Set set, LinkedList<Integer> elements) {

        int counter = 0;

        //zliczanie liczby elementów listy [elements]
        //zawartych w zbiorze (lub jego elementach) [set]
        for (int val : elements) {
            if (set.anyElementContains(val)) {
                counter++;
            }
        }

        return counter;
    }

    //zwraca index zbioru w liście [RArrayList] zawierającego
    //najwięce elementów z listy [leftZElements]
    private int getTheSetContainingMost
    (ArrayList<Pair<Set, Integer>> RArrayList,
     LinkedList<Integer> leftZElements) {

        int indexOfMax = 0;
        int maxContainedCount = getCountOfElementsContained
                (RArrayList.get(0).getFirst(), leftZElements);

        for (int i = 1; i < RArrayList.size(); i++) {
            int containedCount = getCountOfElementsContained
                    (RArrayList.get(i).getFirst(), leftZElements);

            if (containedCount > maxContainedCount) {
                maxContainedCount = containedCount;
                indexOfMax = i;
            }
        }

        return maxContainedCount > 0 ? indexOfMax : -1;
    }

    @Override
    public ArrayList<Integer> solveSetCoverage
            (FiniteArithmeticProgression Z, ArrayList<Set> R) {

        ArrayList<Integer> solution = new ArrayList<>();
        LinkedList<Integer> leftZElements = new LinkedList<>();
        ArrayList<Pair<Set, Integer>> RArrayList = new ArrayList<>();

        for (int i = 0; i < R.size(); i++)
            RArrayList.add(new Pair<Set, Integer>(R.get(i), i));

        for (int i = 1; i <= Z.getLimit(); i++)
            leftZElements.add(i);

        while (!RArrayList.isEmpty() && !leftZElements.isEmpty()) {
            int index = getTheSetContainingMost(RArrayList, leftZElements);
            if (index == -1)
                break;

            leftZElements.removeIf
                    (RArrayList.get(index).getFirst()::anyElementContains);

            solution.add(RArrayList.get(index).getSecond());

            RArrayList.remove(index);
        }

        return leftZElements.isEmpty() ? solution : null;
    }
}

class Pair<L, R> {
    private final L first;
    private final R second;

    public Pair(L first, R second) {
        this.first = first;
        this.second = second;
    }

    public L getFirst() {
        return first;
    }

    public R getSecond() {
        return second;
    }
}