
/**
 * Implements tests to check the functionality of HelpDesk
 * 
 * @author Travis Cao
 */
public class HelpDeskTestSuite extends HelpDesk {

  /**
   * Main method to call all testing methods
   * 
   * @param args Arguments of the testing commands
   */
  public static void main(String[] args) {
    int i = 1; // test number counter
    System.out.println("=== TESING BEGINS ===" + System.lineSeparator());
    System.out.println(i++ + ". testCompareTo(): " + testCompareTo() + System.lineSeparator());
    System.out.println(i++ + ". testCreateNewTicketPreserveOrder(): "
        + testCreateNewTicketPreserveOrder() + System.lineSeparator());
    System.out.println(i++ + ". testCreateNewTicketExceptions(): " + testCreateNewTicketExceptions()
        + System.lineSeparator());
    System.out.println(
        i++ + ". testCheckNextTicket(): " + testCheckNextTicket() + System.lineSeparator());
    System.out.println(i++ + ". testCheckNextTicketExceptions(): " + testCheckNextTicketExceptions()
        + System.lineSeparator());
    System.out.println(
        i++ + ". testCloseNextTicket(): " + testCloseNextTicket() + System.lineSeparator());
    System.out.println(i++ + ". testCheckNextTicketExceptions(): " + testCheckNextTicketExceptions()
        + System.lineSeparator());
    System.out.println(i++ + ". testParentOf(): " + testParentOf() + System.lineSeparator());
    System.out.println(
        i++ + ". testLeftAndRightChildOf(): " + testLeftAndRightChildOf() + System.lineSeparator());
    System.out.println("=== TESTING CONCLUDES ====");
  }

  /**
   * Constructor included so that this class extends HelpDesk and can access its protected methods;
   * no actual HelpDeskTestSuite object will be created
   * 
   * @param capacity The fixed capacity for the amount of tickets a HelpDesk can handle
   */
  public HelpDeskTestSuite(int capacity) {
    super(capacity);
  }

  // Testing methods
  /**
   * Tests that when comparing two SupportTicket objects, correct comparison result is given
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testCompareTo() {
    boolean testPassed = false;
    boolean subtest1 = false;
    boolean subtest2 = false;
    boolean subtest3 = false;

    SupportTicket ticket1 = new SupportTicket("Aaaaaa");
    SupportTicket ticket2 = new SupportTicket("Higher priority");
    SupportTicket ticket3 = new SupportTicket("Aaaaab");

    if (ticket1.compareTo(ticket2) < 0) {
      subtest1 = true;
    } else {
      System.out.println("Problem detected: longer tickets should have higher priority. But, it was"
          + " not the case.");
    }

    if (ticket1.compareTo(ticket3) < 0) {
      subtest2 = true;
    } else {
      System.out.println("Problem detected: when tickets have the same length, lexicographic order "
          + "should be used to determine order. But, it was not the case.");
    }

    if (ticket2.compareTo(ticket3) > 0) {
      subtest3 = true;
    } else {
      System.out.println("Problem detected: longer tickets should have higher priority. But, it was"
          + " not the case.");
    }

    if (subtest1 && subtest2 && subtest3) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Tests the createNewTicket() method of HelpDesk preserves the correct order of priority queue
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testCreateNewTicketPreserveOrder() {
    boolean testPassed = false;
    boolean subtest1 = false;
    boolean subtest2 = false;

    try {
      HelpDesk hd = new HelpDesk(10);
      hd.createNewTicket("A");
      hd.createNewTicket("Longer message");
      hd.createNewTicket("Something of high priority");
      hd.createNewTicket("Ticket");
      hd.createNewTicket("A super super super super super long ticket");
      hd.createNewTicket("b");

      if (hd.size == 6) {
        subtest1 = true;
      } else {
        System.out.println("Problem detected: after adding 6 tickets to HelpDesk, the size of "
            + "the heap should be 6. Instead, it returned " + hd.size);
      }

      if (hd.array[0].toString().equals("A super super super super super long ticket")
          && hd.array[1].toString().equals("Something of high priority")
          && hd.array[2].toString().equals("Longer message") && hd.array[3].toString().equals("A")
          && hd.array[4].toString().equals("Ticket") && hd.array[5].toString().equals("b")) {
        subtest2 = true;
      } else {
        System.out.println("Problem detected: adding to this HelpDesk priority queue, the order of"
            + " the priority queue is lost. Here is the current (wrong) order: ");
        for (int i = 0; i < hd.array.length; i++) {
          System.out.println("Index " + i + ": " + hd.array[i].toString());
        }
      }

      if (subtest1 && subtest2) {
        testPassed = true;
      }

    } catch (Exception e) {
      System.out.println("Problem detected: unexpected Exception thrown from creating new tickets. "
          + "The Exception's message is " + e.getMessage());
    }

    return testPassed;
  }

  /**
   * Tests conditions where createNewTicket() should result in throwing exceptions
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testCreateNewTicketExceptions() {
    boolean testPassed = false;
    boolean subtest1 = false;
    boolean subtest2 = false;

    try {

      HelpDesk hd = new HelpDesk(5);

      try {
        hd.createNewTicket(null);
      } catch (NullPointerException e) {
        subtest1 = true;
      } finally {
        if (subtest1 == false) {
          System.out.println("Problem detected: if a new ticket is created with a null message, "
              + "a NullPointerException should've been thrown. But, it was not the case.");
        }
      }

      hd.createNewTicket("A");
      hd.createNewTicket("Longer message");
      hd.createNewTicket("Something of high priority");
      hd.createNewTicket("Ticket");
      hd.createNewTicket("A super super super super super long ticket");

      try {
        hd.createNewTicket("b");
      } catch (IndexOutOfBoundsException e) {
        subtest2 = true;
      } finally {
        if (subtest2 == false) {
          System.out.println("Problem detected: after the HelpDesk is full, adding new tickets "
              + "should throw an IndexOutOfBoundsException. But, it was not the case.");
        }
      }

    } catch (Exception e) {
      System.out.println("Problem detected: unexpected Exception thrown from creating new tickets. "
          + "The Exception's message is " + e.getMessage());
    }

    if (subtest1 && subtest2) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Tests the checkNextTicket() method of HelpDesk
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testCheckNextTicket() {
    boolean testPassed = false;
    boolean subtest1 = false;
    boolean subtest2 = false;

    try {
      HelpDesk hd = new HelpDesk(10);
      hd.createNewTicket("A");
      hd.createNewTicket("Longer message");
      hd.createNewTicket("Something of high priority");
      hd.createNewTicket("Ticket");
      hd.createNewTicket("A super super super super super long ticket");
      hd.createNewTicket("b");

      String highestPriority = hd.checkNextTicket();

      if (highestPriority.equals("A super super super super super long ticket")) {
        subtest1 = true;
      } else {
        System.out.println("Problem detected: checkNextTicket() should've returned the ticket with"
            + " the highest priority in the test HelpDesk object. Instead, this is returned: "
            + highestPriority);
      }

      if (hd.array[0].toString().equals("A super super super super super long ticket")) {
        subtest2 = true;
      } else {
        System.out.println("Problem detected: after calling checkNextTicket(), the state of the "
            + "HelpDesk should not be changed. However, now the ticket with the highest priority "
            + "in the HelpDesk is changed into: " + hd.array[0].toString());
      }

    } catch (Exception e) {
      System.out.println("Problem detected: unexpected Exception thrown from creating new tickets. "
          + "The Exception's message is " + e.getMessage());
    }

    if (subtest1 && subtest2) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Tests conditions where checkNextTicket() should result in throwing exceptions
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testCheckNextTicketExceptions() {
    boolean testPassed = false;

    try {

      HelpDesk hd = new HelpDesk(10);

      try {
        hd.checkNextTicket();
      } catch (IllegalStateException e) {
        testPassed = true;
      }

    } catch (Exception e) {
      System.out.println("Problem detected: unexpected Exception thrown from creating new tickets. "
          + "The Exception's message is " + e.getMessage());
    }

    if (testPassed == false) {
      System.out.println("Problem detected: when there is no ticket in the HelpDesk, calling "
          + "checkNextTicket() method should result in throwing IllegalStateException. But, "
          + "it was not the case.");
    }

    return testPassed;
  }


  /**
   * Tests the closeNextTicket() method of HelpDesk
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testCloseNextTicket() {
    boolean testPassed = false;
    boolean subtest1 = false;
    boolean subtest2 = false;
    boolean subtest3 = false;

    try {

      HelpDesk hd = new HelpDesk(10);
      hd.createNewTicket("A");
      hd.createNewTicket("Longer message");
      hd.createNewTicket("Something of high priority");
      hd.createNewTicket("Ticket");
      hd.createNewTicket("A super super super super super long ticket");
      hd.createNewTicket("b");

      String firstToRemove = hd.closeNextTicket();
      String secondToRemove = hd.closeNextTicket();

      if (firstToRemove.equals("A super super super super super long ticket")
          && secondToRemove.equals("Something of high priority")) {
        subtest1 = true;
      } else {
        System.out.println("Problem detected: calling closeNextTicket() twice should remove the "
            + "SupportTickets with the highest and second highest priorities. Instead, these two "
            + "following tickets have been removed: ");
        System.out.println("First removal: " + firstToRemove);
        System.out.println("Second removal: " + secondToRemove);
      }

      if (hd.array[0].toString().equals("Longer message") && hd.array[1].toString().equals("Ticket")
          && hd.array[2].toString().equals("b") && hd.array[3].toString().equals("A")) {
        subtest2 = true;
      } else {
        System.out.println("Problem detected: after calling closeNextTicket() twice, the heap order"
            + " property is violated. Here is the current (wrong) order: ");
        for (int i = 0; i < hd.array.length; i++) {
          System.out.println("Index " + i + ": " + hd.array[i].toString());
        }
      }

      if (hd.size == 4) {
        subtest3 = true;
      } else {
        System.out.println("Problem detected: after removing 2 tickets from a HelpDesk that has "
            + "6 tickets to start with, the size of the HelpDesk should be 4. But, it is recorded "
            + "as " + hd.size);
      }

    } catch (Exception e) {
      System.out.println("Problem detected: unexpected Exception thrown from creating new tickets. "
          + "The Exception's message is " + e.getMessage());
    }

    if (subtest1 && subtest2 && subtest3) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Tests conditions where closeNextTicket() should result in throwing exceptions
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testCloseNextTicketExceptions() {
    boolean testPassed = false;

    try {

      HelpDesk hd = new HelpDesk(10);

      try {
        hd.closeNextTicket();
      } catch (IllegalStateException e) {
        testPassed = true;
      }

    } catch (Exception e) {
      System.out.println("Problem detected: unexpected Exception thrown from creating new tickets. "
          + "The Exception's message is " + e.getMessage());
    }

    if (testPassed == false) {
      System.out.println("Problem detected: when there is no ticket in the HelpDesk, calling "
          + "closeNextTicket() method should result in throwing IllegalStateException. But, "
          + "it was not the case.");
    }

    return testPassed;
  }

  /**
   * Tests whether the static parentOf() method is returning the correct parent index for a heap
   * array
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testParentOf() {
    boolean testPassed = false;

    try {

      if (HelpDesk.parentOf(1) == 0 && HelpDesk.parentOf(5) == 2) {
        testPassed = true;
      } else {
        System.out.println("Problem detected: the parentOf() static method is not returning the "
            + "correct parent indices for a heap array. When called parentOf(1), it returned "
            + HelpDesk.parentOf(1) + " instead of 0; when called parentOf(5), it returned "
            + HelpDesk.parentOf(5) + " instead of 2.");
      }

    } catch (Exception e) {
      System.out.println("Problem detected: unexpected Exception thrown from creating new tickets. "
          + "The Exception's message is " + e.getMessage());
    }

    return testPassed;
  }

  /**
   * Tests whether the static leftChildOf() and rightChildOf() methods are returning the correct
   * left and right children for a heap array
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testLeftAndRightChildOf() {
    boolean testPassed = false;
    boolean subtest1 = false;
    boolean subtest2 = false;

    try {

      if (HelpDesk.leftChildOf(1) == 3 && HelpDesk.leftChildOf(5) == 11) {
        subtest1 = true;
      } else {
        System.out.println("Problem detected: the leftChildOf() static method is not returning the "
            + "correct left child index for a heap array. When called leftChildOf(1), it returned "
            + HelpDesk.leftChildOf(1) + " instead of 3; when called leftChildOf(5), it returned "
            + HelpDesk.leftChildOf(5) + " instead of 11.");
      }

      if (HelpDesk.rightChildOf(1) == 4 && HelpDesk.rightChildOf(5) == 12) {
        subtest2 = true;
      } else {
        System.out.println("Problem detected: the rightChildOf() static method is not returning the"
            + " correct right child index for a heap array. When called rightChildOf(1), it "
            + "returned " + HelpDesk.rightChildOf(1) + " instead of 4; when called rightChildOf(5),"
            + " it returned " + HelpDesk.rightChildOf(5) + " instead of 12.");
      }

    } catch (Exception e) {
      System.out.println("Problem detected: unexpected Exception thrown from creating new tickets. "
          + "The Exception's message is " + e.getMessage());
    }

    if (subtest1 && subtest2) {
      testPassed = true;
    }

    return testPassed;
  }


}
