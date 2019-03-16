import java.util.function.Function;

/**
 * A class that hosts all testing methods of P07: Iterating to Philosophy
 * 
 * @author Travis Cao
 */
public class TestDriver {

  /**
   * Main method to run all testing methods
   * 
   * @param args Arguments that call all testing methods
   */
  public static void main(String[] args) {
    System.out.println("=== BEGIN TESTING ===" + System.lineSeparator());
    System.out.println("testEvenNumbers(): " + testEvenNumbers() + System.lineSeparator());
    System.out.println("testPowersOfTwo(): " + testPowersOfTwo() + System.lineSeparator());
    System.out.println("=== TESTING COMPLETES ===");
  }

  // Testing Methods
  /**
   * Checks if an object of EvenNumbers class behaves correctly when calling .next() and .hasNext()
   * methods upon
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testEvenNumbers() {
    EvenNumbers it = new EvenNumbers(44);
    if (it.next() != 44) {
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");
      return false;
    }
    if (it.next() != 46) {
      System.out.println(
          "The second call of EvenNumbers.next() " + "did not return the smallest even number, "
              + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out.println("EvenNumbers.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Checks that a NextPowerOfTwo class function paired with an InfiniteIterator object is correctly
   * returning an infinite sequence of numbers based on the NextPowerOfTwo function
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testPowersOfTwo() {
    InfiniteIterator it = new InfiniteIterator(8, new NextPowerOfTwo());
    if (it.next() != 8) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;
    }
    if (it.next() != 16) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

}


/**
 * Generates integers that double in value
 * 
 * @author Travis Cao
 */
class NextPowerOfTwo implements Function<Integer, Integer> {
  /**
   * Doubles an integer value
   * 
   * @param previous An interger that has been returned previously and waiting to be doubled
   * @return An integer that is twice the size of its input
   */
  public Integer apply(Integer previous) {
    return 2 * previous;
  }
}
