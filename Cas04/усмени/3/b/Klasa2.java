public class Klasa2 {
    private int x;

    public static void main(String[] args) {
        Klasa21 k2 = new Klasa21() {
            @Override
            public void print(String str) {
                System.out.println("Poruka");
                ;
                ;
            }
        };
        k2.print(x);
    }
}

class Klasa21 extends Klasa2 {
    public void print(String str) {
        System.out.println(str);
    }
}
