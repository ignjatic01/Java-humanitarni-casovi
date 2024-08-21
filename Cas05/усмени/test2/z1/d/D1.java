public class D1 implements DI3 {
    public D1() {
        System.out.println("D1()");
    }

    public static void main(String... args) {
        DI1 d1 = new D1();
        DI2 d2 = new D1();
        DI3 d3 = new D2();
        D1 d4 = new D2();
        D2 d5 = new D2();
        d1.metoda1();
        d2.metoda2();
        d3.metoda1();
        d4.metoda2();
        d5.metoda1();
    }

    void metoda2() {
        System.out.println("D1.metoda2()");
    }
}

class D2 extends D1 implements DI2 {
    public D2() {
        System.out.println("D2()");
    }

    public void metoda1() {
        System.out.println("D2.metoda1()");
    }

    public void metoda2() {
        System.out.println("D2.metoda2()");
    }
}

interface DI1 {
    public default void metoda1() {
        System.out.println("DI1.metoda1()");
    }
}

interface DI2 extends DI1 {
    void metoda1();

    default void metoda2() {
        System.out.println("DI2.metoda2()");
    }
}

abstract interface DI3 extends DI2 {
    final int c = 10;

    default void metoda1() {
        System.out.println("DI3.metoda1()");
    }

    abstract void metoda2();
}
