
/**
 * Testing file for Storage Unit Organizer
 * 
 * @author Travis Cao
 */
public class StorageUnitTests {

  // Main driver
  /**
   * Main driver of the test program
   * 
   * @param args Testing arguments
   */
  public static void main(String[] args) {
    System.out.println("=== TESTING BEGINS ===" + System.lineSeparator());
    System.out.println("testBoxEquals(): " + testBoxEquals() + System.lineSeparator());
    System.out.println("testBoxCompareTo(): " + testBoxCompareTo() + System.lineSeparator());
    System.out.println(
        "testLinkedBoxNodeCreation(): " + testLinkedBoxNodeCreation() + System.lineSeparator());
    System.out.println("=== TESTING CONCLUDES ===");
  }

  /**
   * Checks whether the behavior of equals method in Box class is correct
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testBoxEquals() {
    boolean testPassed = false;

    Box box1 = new Box(28, 26);
    Box box2 = new Box(3276, 12);
    Box box3 = new Box(28, 26);

    if (box1.equals(box3) && !box1.equals(box2) && !box2.equals(box3)) {
      testPassed = true;
    }

    if (testPassed == false) {
      System.out.println("Problem detected: when two Box objects have the same color and weight "
          + "fields, they should be considered as equal. But, it was not the case.");
    }

    return testPassed;
  }

  /**
   * Checks whether the behavior of compareTo method is correctly implemented in Box class
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testBoxCompareTo() {
    boolean testPassed = false;

    Box box1 = new Box(28, 26);
    Box box2 = new Box(3276, 12);
    Box box3 = new Box(28, 12);

    if (box1.compareTo(box2) == 1 && box2.compareTo(box3) == 0 && box3.compareTo(box1) == -1) {
      testPassed = true;
    }

    if (testPassed == false) {
      System.out.println("Problem detected: compareTo() method in Box class does not return correct"
          + " integer value when comparing weight between Box objects.");
    }

    return testPassed;
  }

  /**
   * Checks whether the constructor, getter, and setter methods in LinkedBoxNode class function
   * correctly
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testLinkedBoxNodeCreation() {
    boolean testPassed = false;

    LinkedBoxNode boxNode1 = new LinkedBoxNode(new Box(11, 22));
    LinkedBoxNode boxNode2 = new LinkedBoxNode(new Box(), boxNode1);

    if (boxNode1.getBox().equals(new Box(11, 22)) && boxNode2.getNext() == boxNode1
        && boxNode1.getNext() == null) {
      boxNode2.setBox(new Box(10, 21));
      if (boxNode2.getBox().equals(new Box(10, 21))) {
        testPassed = true;
      } else {
        System.out.println(
            "Problem detected: setters of LinkedBoxNode class are not functioning correctly");
      }
    } else {
      System.out.println(
          "Problem detected: getters of LinkedBoxNode class are not functioning correctly.");
    }

    return testPassed;
  }

}
