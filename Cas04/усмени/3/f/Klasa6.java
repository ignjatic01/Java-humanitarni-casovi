

public class Klasa6 {
    public static void main(String x[]) {
        int[][][] niz = {{{1, 2, 3}, {4, 5, 6}}};
        for (int i = 0; i < niz.length; i++){
           // System.out.println(niz.length);
            for (int j = 0; j < niz[1].length; j++)
                for (int k = 0; k < j; i++)
                    System.out.println((Math.random() > 0.5)
                            ? niz[i][j][k] : "x");
        }

    }
}
