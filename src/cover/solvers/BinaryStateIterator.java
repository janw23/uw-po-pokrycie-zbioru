package cover.solvers;

import java.util.Arrays;

//obiekt służący do generowania wszystkich
//stanów binarnych danej wielkości o danej
//liczbie stanów prawdziwych

public class BinaryStateIterator {

    private final boolean[] state;
    private int[] trueIndices;

    public BinaryStateIterator(int bitsCount) {
        state = new boolean[bitsCount];
        trueIndices = new int[0];
    }

    //przygotowuje iterator do generowania stanów
    //o [trueStatesCount] wartościach prawdziwych
    public void init(int trueStatesCount) {
        assert trueStatesCount <= state.length;

        trueIndices = new int[trueStatesCount];

        for (int i = 0; i < trueIndices.length; i++)
            trueIndices[i] = i;
    }

    //próbuje przesunąć bit na pozycji [index]
    //w tablicy [trueIndices] o jeden w prawo.
    //Jeśli się nie da, to próbuje zmodyfikować
    //rekurencyjnie wcześniejsze bity, a jeśli
    //to się nie powiedzie, to zwraca false
    private boolean tryMoveBit(int index) {
        if (index < 0) return false;

        trueIndices[index]++;
        int bound = state.length - trueIndices.length + 1 + index;

        if (trueIndices[index] >= bound) {
            if (tryMoveBit(index - 1)) {
                trueIndices[index] = trueIndices[index - 1] + 1;

            } else return false;
        }

        return true;
    }

    //generuje kolejny stan i zwraca true, jeśli
    //udało się to zrobić
    public boolean nextState() {
        return tryMoveBit(trueIndices.length - 1);
    }

    //zwraca aktualny stan w postaci tablicy wartości logicznych
    public boolean[] getState() {
        Arrays.fill(state, false);

        for (int index : trueIndices)
            state[index] = true;

        return state;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder =
                new StringBuilder("BinaryStateIterator{");

        for (boolean bit : getState())
            stringBuilder.append(bit ? 1 : 0);

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
