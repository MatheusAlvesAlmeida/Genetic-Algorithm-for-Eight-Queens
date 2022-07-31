package utils;

public class Pair<T, U, W> {
    public T first;
    public U second;
    public W third;

    public Pair(T t, U u, W w) {
        first = t;
        second = u;
        third = w;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}
