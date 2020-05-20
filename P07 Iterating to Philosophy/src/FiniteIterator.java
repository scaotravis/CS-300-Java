
import java.util.Iterator;

/**
 * Retrieves a fixed number of elements from any InfiniteIterator
 * 
 * @author Travis Cao
 */
public class FiniteIterator<T> implements Iterator<T> {

  private InfiniteIterator<T> infiniteIterator;
  private int length; // number of iterations permitted
  private int numberOfCalls = 0; // number of iterations already called

  // Constructor
  /**
   * Initializes an object of class FiniteInterator
   * 
   * @param infiniteIterator An InfiniteIterator object that serves as the baseline for a
   *                         FiniteIterator object
   * @param length           The length of the FiniteIterator
   */
  public FiniteIterator(InfiniteIterator<T> infiniteIterator, int length) {
    this.infiniteIterator = infiniteIterator;
    this.length = length;
  }

  // Methods
  /**
   * Checks if there exists an object in the next sequence position
   * 
   * @return true if there is an object in the next sequence position, false otherwise
   */
  public boolean hasNext() {
    return this.numberOfCalls < this.length;
  }

  /**
   * Retrieves the next object in sequence
   * 
   * @return The next object in sequence if it has yet to reach the finite end; null otherwise
   */
  public T next() {
    if (this.hasNext()) {
      numberOfCalls++;
      return this.infiniteIterator.next();
    } else {
      return null;
    }
  }

}
