

public class A1 {
    A1 a1;

    static {
        System.out.println("A1-S");
    }

    {
        System.out.println("A1-N");
    }

    public A1() {
        System.out.println("A1()");
    }

    public A1(A1 a1) {
        this.a1 = a1;
        System.out.println("A1(A1)");
    }

    protected void metoda1() {
        System.out.println("A1.metoda1()");
    }

    public void metoda2() {
        System.out.println("A1.metoda2()");
    }
}

class A2 extends A1 {
    static {
        System.out.println("A2-S");
    }

    private A1 a1;

    {
        a1 = new A1();
        System.out.println("A2-N");
    }

    public A2() {
        super();
        System.out.println("A2()");
    }

    public A2(A1 a1) {
        super(a1);
        System.out.println("A2(A1)");
    }

    @Override
    public final void metoda1() {
        System.out.println("A2.metoda1()");
    }

    public void metoda2() {
        System.out.println("A2.metoda2()");
    }
}

class A3 extends A2 {
    protected A2 a2;

    {
        a2 = new A2(new A1());
    }

    public A3() {
        System.out.println("A3()");
    }

    public void metoda2() {
        System.out.println("A3.metoda2()");
    }
}

class A4 extends A3 {
    public A4() {
        System.out.println("A4()");
    }

    public static void main(String[] args) {
        A4 a4 = new A4();
        a4.a2.metoda1();
        a4.metoda2();
    }
}
