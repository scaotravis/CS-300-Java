
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

}
