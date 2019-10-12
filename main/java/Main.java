import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        ThreadLocalRandom.current().ints(1,31).distinct().forEach(System.out::println);
        new Random().ints(1, 31).distinct().forEach(System.out::println);
    }
}
