
/**
 * Test serving a queue of guests
 * 
 * @author Travis Cao
 */
public class QueueTests {

  /**
   * Main method to call all test methods
   * 
   * @param args Arguments to call test methods
   */
  public static void main(String[] args) {
    System.out.println("=== TESTING BEGIN ===" + System.lineSeparator());
    System.out.println(
        "testAddToAndRemoveFromQueue(): " + testAddToAndRemoveFromQueue() + System.lineSeparator());
    System.out.println("testQueueToString(): " + testQueueToString() + System.lineSeparator());
    System.out
        .println("testCircularIndexing(): " + testCircularIndexing() + System.lineSeparator());
    System.out.println("=== TESTING CONCLUDES ===");
  }

  // Testing methods
  /**
   * Test adding a new guest to a ServingQueue
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testAddToAndRemoveFromQueue() {
    boolean testPassed = false;

    Guest.resetNextGuestIndex();
    ServingQueue queue = new ServingQueue(3);
    queue.add(new Guest());
    queue.add(new Guest("no gluten"));

    if (queue.remove().toString().equals("#1")
        && queue.remove().toString().equals("#2(no gluten)")) {
      testPassed = true;
    } else {
      System.out.println("Problem detected: after adding two guests, removing from queue should "
          + "return the two guests added. But, it was not the case.");
    }

    return testPassed;
  }

  /**
   * Test the toString() method implemented in an object of class ServingQueue
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testQueueToString() {
    boolean testPassed = false;

    Guest.resetNextGuestIndex();
    ServingQueue queue = new ServingQueue(3);
    queue.add(new Guest());
    queue.add(new Guest("no wheat"));

    String printedByQueue = queue.toString();
    if (printedByQueue.equals("[#1, #2(no wheat)]")) {
      testPassed = true;
    } else {
      System.out.println("Problem detected: after adding two guests, the queue should print out "
          + "two guests with their guestIndex and respective dietary restriction (if any). But, "
          + "it was not the case.");
    }

    return testPassed;
  }

  /**
   * Test the circular indexing implemented in ServingQueue
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testCircularIndexing() {
    boolean testPassed = false;

    Guest.resetNextGuestIndex();
    ServingQueue queue = new ServingQueue(4);

    // use three String objects to store toString() output, which records queue's content
    try {
      queue.add(new Guest("1"));
      queue.add(new Guest("2"));
      queue.remove();
      queue.add(new Guest("3"));
      String toStringA = queue.toString();
      queue.remove();
      queue.remove();
      queue.add(new Guest("4"));
      String toStringB = queue.toString();
      queue.add(new Guest("5"));
      queue.add(new Guest("6"));
      queue.add(new Guest("7"));
      String toStringC = queue.toString();

      if (toStringA.equals("[#2(2), #3(3)]") && toStringB.equals("[#4(4)]")
          && toStringC.equals("[#4(4), #5(5), #6(6), #7(7)]")) {
        testPassed = true;
      } else {
        System.out.println("Problem detected: using circular indexing, the queue should be able to "
            + "continuously add in new elements after removing some guests to make room. But, it "
            + "was not the case.");
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Exception was thrown by running the circular indexing "
          + "test. It is likely that the enqueueTo variable was not pointing to the correct "
          + "index. Check enqueueTo used in .add() and .remove() methods.");
    }

    return testPassed;
  }

}
