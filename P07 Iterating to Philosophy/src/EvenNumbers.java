
import java.util.Iterator;

/**
 * Generates an infinite sequence of even integers starting at a specified starting point
 * 
 * @author Travis Cao
 */
public class EvenNumbers implements Iterator<Integer> {

  private Integer number; // originally records the first number of the sequence; replaced with
                          // iterated values as the sequence grows
  private boolean firstCall = true; // keep track of whether this is the first call

  // Constructor
  /**
   * Initializes an object of class EvenNumbers
   * 
   * @param number An even integer as the starting point to iterate an infinite sequence of even
   *               integers
   */
  public EvenNumbers(Integer number) {
    this.number = number;
  }

  // Methods
  /**
   * Checks if there exists an even integer that is larger than the previously returned one. Since
   * numbers are infinite, this method always returns true
   * 
   * @return true always
   */
  public boolean hasNext() {
    return true;
  }

  /**
   * Retrieves the smallest even number that is larger than the previously returned one
   * 
   * @return The smallest even number that is larger than the previously returned one
   */
  public Integer next() {
    if (firstCall) {
      firstCall = false;
      return this.number;
    } else {
      this.number = this.number + 2;
      return this.number;
    }
  }

}
