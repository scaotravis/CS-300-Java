
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
    System.out.println("testAddToQueue(): " + testAddToQueue() + System.lineSeparator());
    System.out.println("=== TESTING CONCLUDES ===");
  }

  // Testing methods
  /**
   * Test the toString() method implemented in an object of class ServingQueue. 
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testQueueToString() {
    boolean testPassed = false; 
    
    return testPassed; 
  }
  
  /**
   * Test adding a new guest to a ServingQueue.
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testAddToQueue() {
    boolean testPassed = false;

    ServingQueue queue = new ServingQueue(3);
    queue.add(new Guest());
    queue.add(new Guest("no gluten"));

    String printedByQueue = queue.toString();
    if (printedByQueue.equals("[#1, #2(no gluten)]")) {
      testPassed = true;
    } else {
      System.out.println("Problem detected: after adding two guests, the queue should print out "
          + "two guests with their guestIndex and respective dietary restriction (if any). But, "
          + "it was not the case.");
    }

    return testPassed;
  }

}
