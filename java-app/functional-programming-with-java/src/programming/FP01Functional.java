package programming;

import java.util.List;

public class FP01Functional {
  public static void main(String args[]) throws Exception {
    List<Integer> numbers = List.of(12, 8, 3, 9, 12, 15, 10, 10);
    System.out.println("\nAll Numbers - Functional\n");
    printAllNumbersInListFunctional(numbers);
    System.out.println("\nEven Numbers - Functional\n");
    printEvenNumbersInListFunctional(numbers);
    System.out.println("\nSquare of Even Numbers - Functional\n");
    printSquaresOfEvenNumbersInListFunctional(numbers);
  }

  private static void printAllNumbersInListFunctional(List<Integer> numbers) {
    // what to do?
    // numbers.stream().forEach(FP01Functional::print);
    // In above we did Method Reference - FP01Functional::print
    numbers.stream().forEach(System.out::println);
    // In above we did Method Reference - System.out::println
    // println is a static method of System class
  }

  private static void print(int number) {
    System.out.println(number);
  }

  private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
    // what to do?
    numbers.stream()
        // .filter(FP01Functional::isEven) // Filter - even numbers
        .filter(number -> number % 2 == 0) // Lambda Expression - even numbers
        .forEach(System.out::println);
  }

  private static boolean isEven(int number) {
    return number % 2 == 0;
  }

  private static void printSquaresOfEvenNumbersInListFunctional(List<Integer> numbers) {
    // what to do?
    numbers.stream()
        // .filter(FP01Functional::isEven) // Filter - even numbers
        .filter(number -> number % 2 == 0) // Lambda Expression - even numbers
        .map(number -> number * number) // Mapping - Lamda expression to find squares
        .forEach(System.out::println);
  }
}
