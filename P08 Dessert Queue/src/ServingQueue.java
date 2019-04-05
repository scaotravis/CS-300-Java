
/**
 * Models a queue of Guest objects. Implemented using an array with circular indexing
 * 
 * @author Travis Cao
 */
public class ServingQueue {

  private Guest[] array;
  private int size;
  private int enqueueTo = 0;

  // Constructors
  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" guests. This queue should be
   * initialized to be empty.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   */
  public ServingQueue(int seatsAtTable) {
    this.array = new Guest[seatsAtTable];
  }

  // Methods
  /**
   * Checks whether there are any guests in this serving queue.
   * 
   * @return true when this queue contains zero guests, and false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Adds a single new guest to this queue (to be served after the others that were previously added
   * to the queue).
   * 
   * @param newGuest is the guest that is being added to this queue.
   * @throws IllegalStateException when called on a ServingQueue with an array that is full
   */
  public void add(Guest newGuest) {
    if (this.size == this.array.length) {
      throw new IllegalStateException();
    } else {
      this.array[enqueueTo] = newGuest;
      enqueueTo = (enqueueTo + 1) % array.length;
      this.size++; 
    }
  }

  /**
   * Accessor for the guest that has been in this queue for the longest. This method does not add or
   * remove any guests.
   * 
   * @return a reference to the guest that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest peek() {
    if (isEmpty()) {
      throw new IllegalStateException();
    } else {
      return this.array[(enqueueTo - size + array.length) % array.length];
    }
  }

  /**
   * Removes the guest that has been in this queue for the longest.
   * 
   * @return a reference to the specific guest that is being removed.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest remove() {
    if (isEmpty()) {
      throw new IllegalStateException();
    } else {
      int indexToRemove = (enqueueTo - size + array.length) % array.length;
      Guest guestRemoved = this.array[indexToRemove]; // keep a reference to the guest removed to
                                                      // return
      this.array[indexToRemove] = null;
      this.size--;
      return guestRemoved;
    }
  }

  /**
   * The string representation of the guests in this queue should display each of the guests in this
   * queue (using their toString() implementation), and should display them in a comma separated
   * list that is surrounded by a set of square brackets. (this is similar to the formatting of
   * java.util.ArrayList.toString()). The order that these guests are presented in should be (from
   * left to right) the guest that has been in this queue the longest, to the guest that has been in
   * this queue the shortest. When called on an empty ServingQueue, returns "[]".
   * 
   * @return string representation of the ordered guests in this queue
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    int firstIndex = (enqueueTo - size + array.length) % array.length;
    int currentIndex = firstIndex;
    String result = "[";

    if (!isEmpty()) {
      // first element
      result += this.array[currentIndex].toString();
      currentIndex = (currentIndex + 1) % array.length;
      // all elements following the first
      while (currentIndex != firstIndex) {
        result += ", " + this.array[currentIndex].toString();
        currentIndex = (currentIndex + 1) % array.length;
        // break while loop if there is no more guest within array to print 
        if (this.array[currentIndex] == null) {
          break; 
        }
      }
    }
    result += "]";

    return result;
  }

}
