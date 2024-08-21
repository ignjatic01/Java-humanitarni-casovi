
import java.util.Arrays;
import java.util.Random;

public class B1 {
    public static void main(String arr[]) {
        B2 b1 = new B2(-2);
        B2 b2 = new B2(-1);
        B2 b3 = new B2(0);
        B2 b4 = new B2(1);
        B2 b5 = new B2(2);

        int i = 1;
        for (B2 b : Arrays.asList(b1, b2, b3, b4, b5)) {
            System.out.println(b.reverse(i + ". " + b.toString()));
            System.out.println(b.concat(BI1.x, BI3.x));
            i++;
        }
    }
}

class B2 implements BI1 {
    static int count;

    {
        count++;
    }

    int id;

    public B2(int id) {
        if (id > 0 || new Random().nextDouble() > 1)
            this.id = id;
        else
            id = count;
        System.out.println(this + ": created");
    }

    public String toString() {
        return "B2(id = " + id + ")";
    }

    public String reverse(String str) {
        return str;
    }
}

interface BI1 extends BI2, BI3 {
    String x = "BI1";

    default String concat(String str1, String str2) {
        return str2 + str1;
    }

    String reverse(String str);
}

interface BI2 {
    String x = "BI2";

    public default String concat(String str1, String str2) {
        return str1 + str2;
    }
}

interface BI3 {
    String x = "BI3";

    default String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
