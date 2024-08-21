
public class Test1 {
    public static void main(String... a) {
        Klasa1 k1 = new Klasa1() {
            public void add(int a, int b) {
                return a + b;
            }

            public void sub(int a, int b) {
                return a - b;
            }
        };
        System.out.println(k1.add(10, 20));
        System.out.println(k1.add(5, 10));
    }

    ;;;;

    static abstract class Klasa1 {
        public abstract add(int x, int y);

        public abstract sub(int x, int y);
    }
}
