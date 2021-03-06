
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
    if (size == array.length) {
      throw new IndexOutOfBoundsException("HelpDesk is full.");
    }
    if (message == null) {
      throw new NullPointerException("Message of the ticket is null.");
    }

    array[size] = new SupportTicket(message);
    size++;
    int currentIndex = size - 1;
    int parentIndex = parentOf(currentIndex);
    while (parentIndex != -1) {
      if (array[currentIndex].compareTo(array[parentIndex]) > 0) {
        swap(currentIndex, parentIndex);
        currentIndex = parentIndex;
        parentIndex = parentOf(currentIndex);
      } else {
        break;
      }
    }
  }

  /**
   * Returns the message within this HelpDesk that has the highest priority. This method does not
   * change the state of this HelpDesk.
   * 
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  public String checkNextTicket() {
    if (size != 0) {
      return array[0].toString();
    } else {
      throw new IllegalStateException("This HelpDesk has 0 SupportTickets.");
    }
  }

  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   * 
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  public String closeNextTicket() {
    if (size == 0) {
      throw new IllegalStateException("This HelpDesk has 0 SupportTickets.");
    }

    String highestPriorityTicket = array[0].toString();
    array[0] = array[size - 1];
    array[size - 1] = null;
    size--;
    this.propagateDown(0); // enforce heap order property from the root

    return highestPriorityTicket;
  }

  /**
   * Given an index into the heap array, this method returns that index's parent index
   * 
   * @param index The index of the heap array element which needs to find its parent
   * @return The index of current heap array element's parent; -1 if the current element is the root
   */
  protected static int parentOf(int index) {
    return ((index + 1) / 2) - 1;
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
   * to enforce the heap's order property between this index and its parent
   * 
   * @param index The index of the array element to swap with its parent
   */
  protected void propagateUp(int index) {
    int parentIndex = parentOf(index);
    if (parentIndex == -1) {
      // do nothing: already at the root; no need to propagate up
    } else {
      if (array[parentIndex].compareTo(array[index]) >= 0) {
        // do nothing: parent is already of higher priority compared with current ticket
      } else {
        swap(parentIndex, index);
        index = parentIndex;
        propagateUp(index);
      }
    }
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and its children.
   * 
   * @param index The index of the array element to swap with the greater of its children
   */
  protected void propagateDown(int index) {
    int maxChild = leftChildOf(index);
    if (maxChild >= this.size) { // SHOULD BE GREATER OR EQUAL TO
      // do nothing: a left child does not even exist
    } else {
      int potentialRightChild = rightChildOf(index);
      if (potentialRightChild < size) {
        if (array[maxChild].compareTo(array[potentialRightChild]) < 0) {
          maxChild = potentialRightChild;
        }
      }
      if (array[maxChild].compareTo(array[index]) <= 0) {
        // do nothing: ticket at maxChild is of lower priority
      } else {
        swap(index, maxChild); // swap with the bigger of the two children to propagate down
        index = maxChild;
        propagateDown(index);
      }
    }
  }

}
