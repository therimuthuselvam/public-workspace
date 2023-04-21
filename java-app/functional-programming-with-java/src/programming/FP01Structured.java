package programming;

import java.util.List;

public class FP01Structured {
  public static void main(String args[]) throws Exception {
    List<Integer> numbers = List.of(12, 8, 3, 9, 12, 15, 10, 10);
    System.out.println("\nAll Numbers - Functional\n");
    printAllNumbersInListStructured(numbers);
    System.out.println("\nEven Numbers - Functional\n");
    printEvenNumberInListStructured(numbers);

  }

  private static void printAllNumbersInListStructured(List<Integer> numbers) {
    // How to loop the numbers?
    for (int number : numbers) {
      System.out.println(number);
    }
  }

  private static void printEvenNumberInListStructured(List<Integer> numbers) {
    // How to loop the numbers?
    for (int number : numbers) {
      if (number % 2 == 0) { // How to print even numbers?
        System.out.println(number);
      }
    }
  }
}
