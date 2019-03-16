import java.util.Iterator;

/**
 * Generates a sequence of even numbers started a specified starting point
 * 
 * @author Travis Cao
 */
public class EvenNumbers implements Iterator<Integer> {

  private Integer number;
  private int increment = -2;

  // Constructor
  /**
   * Initializes an object of class EvenNumbers
   * 
   * @param number an even integer as the starting point to iterate an infinite sequence of even
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
   * Generates the smallest even number that is larger than the previously returned one
   * 
   * @return the smallest even number that is larger than the previously returned one
   */
  public Integer next() {
    this.increment += 2;
    return this.number + this.increment;
  }

}
