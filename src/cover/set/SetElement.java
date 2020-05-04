package cover.set;

public abstract class SetElement {

    //sprawdza, czy element zbioru zawiera wartość [val]
    public abstract boolean contains(int val);

    public abstract String toString();

    //sprawdza, czy dwa SetElement'y są takie same
    public abstract boolean equals(SetElement element);

}
