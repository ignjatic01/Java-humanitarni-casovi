

public class Klasa3 {
    public static void main(String x[]) {
        Object obj = new String("Word");
        String str = "Word";
        StringBuilder sb = new StringBuilder("Word");

        System.out.println(obj.equals(str));
        System.out.println(obj == "Word");
        System.out.println(str == "Word");
        System.out.println(str == sb.toString());
    }
}
