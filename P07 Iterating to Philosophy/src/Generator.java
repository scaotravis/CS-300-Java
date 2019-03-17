import java.util.Iterator;
import java.util.function.Function;

/**
 * Generates an Iterable object making use of a FiniteIterator or an InfiniteIterator
 * 
 * @author Travis Cao
 */
public class Generator<T> implements Iterable<T> {

  private Iterator<T> iterator;

  // Constructors
  /**
   * Initializes a Generator object paired with an InfiniteIterator
   * 
   * @param firstValue           The first value of the sequence to iterate
   * @param generateNextFromLast Function that defines how to iterate from the first value
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    this.iterator = new InfiniteIterator<T>(firstValue, generateNextFromLast);
  }

  /**
   * Initializes a Generator object paired with a FiniteIterator
   * 
   * @param firstValue           The first value of the sequence to iterate
   * @param generateNextFromLast Function that defines how to iterate from the first value
   * @param length               Length of the finite sequence
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    this.iterator =
        new FiniteIterator<T>(new InfiniteIterator<T>(firstValue, generateNextFromLast), length);
  }

  // Methods
  /**
   * Returns the appropriate Iterator object
   * 
   * @return A FiniteIterator object if the Generator was created with a pre-specified length of the
   *         sequence, otherwise an InfiniteIterator object
   */
  public Iterator<T> iterator() {
    if (this.iterator instanceof FiniteIterator) {
      return (FiniteIterator<T>) this.iterator;
    } else {
      return (InfiniteIterator<T>) this.iterator;
    }
  }

}
