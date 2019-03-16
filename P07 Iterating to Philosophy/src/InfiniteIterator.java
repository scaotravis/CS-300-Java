import java.util.Iterator;
import java.util.function.Function;

/**
 * Generates an infinite sequence of integers with a specified generating function and starting
 * point
 * 
 * @author Travis Cao
 */
public class InfiniteIterator implements Iterator<Integer> {

  private Integer number;
  private Function<Integer, Integer> generatingFunction;
  private boolean firstCall = true; // keep track of whether this is the first call

  // Constructor
  /**
   * Initializes an object of class InfiniteInterator
   * 
   * @param number             A starting point to iterate an infinite sequence of integers
   * @param generatingFunction the function used to generates an infinite sequence of integers
   */
  public InfiniteIterator(Integer number, Function<Integer, Integer> generatingFunction) {
    this.number = number;
    this.generatingFunction = generatingFunction;
  }

  // Methods
  /**
   * Checks if there exists an integer in the next sequence position. Since numbers are infinite,
   * this method always returns true
   * 
   * @return true always
   */
  public boolean hasNext() {
    return true;
  }

  /**
   * Generates the next integer in sequence based on the pre-specified generating function
   * 
   * @return The next integer in sequence
   */
  public Integer next() {
    if (firstCall) {
      firstCall = false;
      return this.number;
    } else {
      this.number = this.generatingFunction.apply(this.number);
      return this.number;
    }
  }

}
