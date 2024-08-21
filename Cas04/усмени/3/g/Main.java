public class Main {
    public static void main(String argv[]) {
        Klasa7 k7 = new Klasa7();
        System.out.println(k7.broj);
    }
}

class Klasa7 {
    private int broj = 1;

    Klasa7() {
        broj += broj;
    }
}
