package programming;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PlayingWithOptional {
  public static void main(String[] args) throws Exception {
    List<String> fruits = Arrays.asList("apple", "orange", "banana", "mango");
    // available
    Predicate<? super String> predicateAvailable = fruit -> fruit.startsWith("b");
    Optional<String> optionalAvailable = fruits.stream().filter(predicateAvailable).findFirst();
    System.out.println(optionalAvailable);
    System.out.println(optionalAvailable.isEmpty());
    System.out.println(optionalAvailable.isPresent());
    System.out.println(optionalAvailable.get());

    // not available
    Predicate<? super String> predicateNotAvailable = fruit -> fruit.startsWith("c");
    Optional<String> optionalNotAvailable = fruits.stream().filter(predicateNotAvailable).findFirst();
    System.out.println(optionalNotAvailable);
    System.out.println(optionalNotAvailable.isEmpty());
    System.out.println(optionalNotAvailable.isPresent());
    System.out.println(optionalNotAvailable.get()); // comment this line to run
    // the below codes

    Optional<String> fruit = Optional.of("banana");
    System.out.println(fruit);
    Optional<String> empty = Optional.empty();
    System.out.println(empty);

  }
}
