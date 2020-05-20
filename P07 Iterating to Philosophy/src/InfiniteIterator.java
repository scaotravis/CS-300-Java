
import java.util.Iterator;
import java.util.function.Function;

/**
 * Generates an infinite sequence of objects with a specified generating function and starting point
 * 
 * @author Travis Cao
 */
public class InfiniteIterator<T> implements Iterator<T> {

  private T t; // originally records the first object of the sequence; replaced with iterated values
               // as the sequence grows
  private Function<T, T> generatingFunction; // function that defines how the object iterates
  private boolean firstCall = true; // keep track of whether this is the first call

  // Constructor
  /**
   * Initializes an object of class InfiniteInterator
   * 
   * @param number             A starting point to iterate an infinite sequence
   * @param generatingFunction the function used to generates an infinite sequence
   */
  public InfiniteIterator(T t, Function<T, T> generatingFunction) {
    this.t = t;
    this.generatingFunction = generatingFunction;
  }

  // Methods
  /**
   * Checks if there exists an object in the next sequence position. Since numbers are infinite,
   * this method always returns true
   * 
   * @return true always
   */
  public boolean hasNext() {
    return true;
  }

  /**
   * Retrieves the next object in sequence based on the pre-specified generating function
   * 
   * @return The next object in sequence
   */
  public T next() {
    if (firstCall) {
      firstCall = false;
      return this.t;
    } else {
      this.t = this.generatingFunction.apply(this.t);
      return this.t;
    }
  }

}
