

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Klasa4 {
    public static void main(String[] args) {
        Stream.iterate(1, e -> e + 1)
                .filter(Klasa4::isPrime)
                .limit(5)
                .forEach(System.out::println);
    }

    public static boolean isPrime(int number) {
        return number > 1 &&
                IntStream.range(2, number)
                        .noneMatch(i -> number % i == 0);
    }
}
