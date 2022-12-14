package utils;

public class Pair<T, U> {
    public T first;
    public U second;

    public Pair(T t, U u) {
        first = t;
        second = u;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}
