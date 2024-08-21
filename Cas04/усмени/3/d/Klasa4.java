


public class Klasa4 extends Thread {
    static int c = 0;

    public static void main(String... args) {
    //System.out.println(c);
        if ((c++) == 0)
            main("arg1", "arg2", "arg3");
        else if (c == 1)
            main(new Integer(args[0].charAt(3)));
        else if (c == 2)
            main('A');
        else
            main();
    }
    public static void main() {
        System.out.println("Pocetak programa");
        new Thread() {
            public void run() {
                for (int i = 0; i < 5; i++);
                {
                    System.out.println("Nit");
                }
            }
        }.start();
    }
    public static void main(int i) {
        System.out.println(i);
        c++;
    }
    public static void main(char c) {
        char x = (""+c).toLowerCase().charAt(0);
        System.out.println(x);
        c++;
    }
}
