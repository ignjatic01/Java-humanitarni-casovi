

;
public class Klasa2 {
    ;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++)
            new Thread(() -> System.out.println("Thread" + i)).start();
    }

    ;;
}
