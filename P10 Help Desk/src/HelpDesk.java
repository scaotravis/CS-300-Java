
/**
 * Models a priority queue
 * 
 * @author Travis Cao
 */
public class HelpDesk implements HelpDeskInterface {

  protected SupportTicket[] array; // zero-indexed max-heap
  protected int size;

  // Constructor
  /**
   * Initializes the SupportTicket array with a specified capacity to indicate the amount of tickets
   * the HelpDesk can handle
   * 
   * @param capacity The fixed capacity for the amount of tickets a HelpDesk can handle
   */
  public HelpDesk(int capacity) {
    this.array = new SupportTicket[capacity];
    this.size = 0;
  }

  // Methods
  /**
   * Creates and adds a new SupportTicket to this HelpDesk.
   * 
   * @param message names the client and describes their need for support.
   * @throws NullPointerException      when the String message argument is null.
   * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
   */
  public void createNewTicket(String message) {
    this.array[size] = new SupportTicket(message);

  }

  /**
   * Returns the message within this HelpDesk that has the highest priority. This method does not
   * change the state of this HelpDesk.
   * 
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  public String checkNextTicket() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   * 
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  public String closeNextTicket() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Given an index into the heap array, this method returns that index's parent index
   * 
   * @param index The index of the heap array element which needs to find its parent
   * @return The index of current heap array element's parent
   * @throws IllegealArgumentException if index is zero (at the root) or negative (cannot happen)
   */
  protected static int parentOf(int index) {
    if (index >= 0) {
      return ((index + 1) / 2) - 1;
    } else {
      throw new IllegalArgumentException("This element does not have a parent.");
    }
  }

  /**
   * Given an index into the heap array, this method returns that index's left child index
   * 
   * @param index The index of the heap array element which needs to find its left child
   * @return The index of the current heap array element's left child
   */
  protected static int leftChildOf(int index) {
    return index * 2 + 1;
  }

  /**
   * Given an index into the heap array, this method returns that index's right child index
   * 
   * @param index The index of the heap array element which needs to find its right child
   * @return The index of the current heap array element's right child
   */
  protected static int rightChildOf(int index) {
    return index * 2 + 2;
  }

  /**
   * Given two indexes into the heap array, this method swaps the SupportTickets at those indexes
   * 
   * @param indexA Index of one of the two array elements to swap with
   * @param indexB Index of the other array element to swap with
   */
  protected void swap(int indexA, int indexB) {
    SupportTicket temp = this.array[indexA]; // store indexA's content into a temp holding variable
    this.array[indexA] = this.array[indexB];
    this.array[indexB] = temp;
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and the heap's root.
   * 
   * @param index The index of the array element to swap with the heap's root
   */
  protected void propagateUp(int index) {
    swap(index, 0); // root is at index 0
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and its children.
   * 
   * @param index The index of the array element to swap with the greater of its children
   */
  protected void propagateDown(int index) {
    int maxChild = leftChildOf(index);
    int potentialRightChild = rightChildOf(index);
    if (potentialRightChild < this.size) {
      if (this.array[maxChild].compareTo(this.array[potentialRightChild]) < 0) {
        maxChild = potentialRightChild;
      }
    }
    swap(index, maxChild); // swap with the bigger of the two children to propagate down
  }

}
