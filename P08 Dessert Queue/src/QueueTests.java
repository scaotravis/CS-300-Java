//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 Dessert Queue
// Files: "Guest.java", "ServingQueue.java", "QueueTests.java",
// "DessertSolvers.java"
// Course: CS 300, Spring, 2019
//
// Author: Travis Cao
// Email: travis.cao@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

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
