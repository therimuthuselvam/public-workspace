package programming;

import java.util.List;

public class FP01Exercises {
  public static void main(String args[]) throws Exception {
    List<Integer> numbers = List.of(12, 8, 3, 9, 12, 15, 10, 10);
    List<String> courses = List.of("GCP", "AWS", "Java", "Spring Boot", "Angular", "Spring");

    System.out.println("\nOdd Numbers - Functional\n");
    printOddNumbersInListFunctional(numbers);
    System.out.println("\nAll Courses - Functional\n");
    printAllCoursesIndividually(courses);
    System.out.println("\nPrint Courses With Word Spring - Functional\n");
    printCoursesWithWordSpring(courses);
    System.out.println("\nPrint Courses Have Atleaset Four Letters - Functional\n");
    printCoursesHaveAtleastFourLetters(courses);
    System.out.println("\nPrint Cube of Odd Numbers - Functional\n");
    printCubesOfOddNumbersInListFunctional(numbers);
    System.out.println("\nPrint Number of Characters In A Course - Functional\n");
    printNumberOfCharactersInACourse(courses);
  }

  private static void printOddNumbersInListFunctional(List<Integer> numbers) {
    // what to do?
    numbers.stream()
        .filter(number -> (number % 2 != 0)) // Lambda Expression - odd numbers
        .forEach(System.out::println);
  }

  private static void printAllCoursesIndividually(List<String> courses) {
    // what to do?
    courses.stream().forEach(System.out::println);
  }

  private static void printCoursesWithWordSpring(List<String> courses) {
    // what to do?
    courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println);
  }

  private static void printCoursesHaveAtleastFourLetters(List<String> courses) {
    // what to do?
    courses.stream().filter(course -> course.length() >= 4).forEach(System.out::println);
  }

  private static void printCubesOfOddNumbersInListFunctional(List<Integer> numbers) {
    // what to do?
    numbers.stream()
        .filter(number -> (number % 2 != 0))
        .map(number -> number * number * number)
        .forEach(System.out::println);
  }

  private static void printNumberOfCharactersInACourse(List<String> courses) {
    // what to do?
    courses.stream().map(course -> course + " " + course.length()).forEach(System.out::println);
  }
}
