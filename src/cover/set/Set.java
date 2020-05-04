package cover.set;

import java.util.ArrayList;
import java.util.LinkedList;

public class Set {

    private ArrayList<SetElement> elements;

    public Set() {
        elements = new ArrayList<>();
    }

    public void add(SetElement element) {

        for (SetElement elem : elements)
            if (elem.equals(element)) return;

        elements.add(element);
    }

    //sprawdza, czy dowolny element zbioru, który również
    //może być zbiorem, zawiera element [val]
    public boolean anyElementContains(int val) {
        for (SetElement elem : elements)
            if (elem.contains(val)) return true;

        return false;
    }

    //sprawdza, czy dowolny element zbioru zawiera
    //jakikolwiek element z [list]
    public boolean anyElementContainsAnyOf(LinkedList<Integer> list) {
        for (int val : list)
            if (anyElementContains(val)) return true;

        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Set{");

        for (SetElement elem : elements)
            stringBuilder.append(elem.toString());

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

}
