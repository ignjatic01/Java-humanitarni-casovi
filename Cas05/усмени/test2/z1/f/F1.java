

import java.util.Arrays;

public class F1<T extends Runnable>
        extends F2<Runnable> implements FI {
    ;

    public static void main(String args[]) {
        FI f1 = new F1<Thread>();
        F1<F1> f2 = new F1<>();
        Runnable f3 = new F1<F1>();
        F2<Runnable> container = new F2<>(f1, f2, f3);

        System.out.println(container.toString());
        container.runAll();
        System.out.println("End");
    }

    public String toString() {
        return "T1";
    }

    ;
}

class F2<T extends Runnable> implements FI {
    T[] arr;

    public F2(T... elements) {
        arr = elements;
    }

    public void runAll() {
        for (T e : arr) {
            new Thread(e).start();
        }
    }

    public String toString() {
        return Arrays.asList(arr).toString();
    }
}

interface FI extends Runnable {
    default void run() {
        System.out.println("Running...");
    }
}
