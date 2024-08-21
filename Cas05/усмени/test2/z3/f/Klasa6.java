

import java.util.NoSuchElementException;

public class Klasa6 {
    int[] niz;

    Klasa6(int... elements) {
        niz = elements;
    }

    public static void main(String[] args) {
        Klasa6 k6 = new Klasa6(1, 2, 3, 5, 6, 7, 8);
        Klasa6.Iterator itr = k6.iterator();
        while (true)
            System.out.println(itr.next());
    }

    public Iterator iterator() {
        return new Iterator();
    }

    class Iterator {
        int pos;

        int next() {
            try {
                pos++;
                return niz[pos];
            } catch (ArrayIndexOutOfBoundsException e) {
                pos--;
                throw new NoSuchElementException("Kraj niza");
            }
        }
    }
}
