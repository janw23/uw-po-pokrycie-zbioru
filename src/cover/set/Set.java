package cover.set;

import java.util.ArrayList;

public class Set {

    private ArrayList<SetElement> elements;

    public Set() {
        elements = new ArrayList<>();
    }

    public void add(SetElement element) {
        elements.add(element);
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
