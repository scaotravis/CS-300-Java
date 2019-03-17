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
    System.out.println("testAddExtraSmile(): " + testAddExtraSmile() + System.lineSeparator());
    System.out.println("testFiniteIterator(): " + testFiniteIterator() + System.lineSeparator());
    System.out.println("testGenerator(): " + testGenerator() + System.lineSeparator());
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
    InfiniteIterator<Integer> it = new InfiniteIterator<>(8, new NextPowerOfTwo());
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

  /**
   * Checks that the InfiniteIterator class objects have been generalized to be applied on String
   * objects
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testAddExtraSmile() {
    InfiniteIterator<String> it = new InfiniteIterator<>("Hello", new AddExtraSmile());
    if (!it.next().equals("Hello")) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;
    }
    if (!it.next().contains(" :)")) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Checks that an object of class FiniteIterator is behaving correctly (that is, it stops at the
   * specified finite terminal node of the sequence)
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testFiniteIterator() {
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());
    FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);
    String s = "";
    while (it.hasNext())
      s += " " + it.next();
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }

  /**
   * Checks that an object of class Generator is iterable
   * 
   * @return true if test passed, false otherwise
   */
  public static boolean testGenerator() {
    Generator<Integer> gen = new Generator<>(3, new NextPowerOfTwo(), 7);
    String s = "";
    for (Integer num : gen) {
      s += " " + num.intValue();
    }
    if (!s.equals(" 3 6 12 24 48 96 192")) {
      System.out.println("Iterated through an Iterable object using a for-each loop, "
          + "and incorrect values were returned: " + s);
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
   * @param previous An Integer that has been returned previously and waiting to be doubled
   * @return An Integer that is twice the size of its input
   */
  public Integer apply(Integer previous) {
    return 2 * previous;
  }

}


/**
 * Adds a smiley face to the end of a string
 * 
 * @author Travis Cao
 */
class AddExtraSmile implements Function<String, String> {

  /**
   * Attaches a smiley face to the end of a string
   * 
   * @param t A String to attach an smiley face to
   * @return The same String object, with an additional smiley face at the end
   */
  public String apply(String t) {
    return t + " :)";
  }

}
