import java.util.Random;

public class Main {
    public static void main(String[] args) {
        new Random().ints(1, 31).distinct().limit(5).forEach(System.out::println);
    }
}
