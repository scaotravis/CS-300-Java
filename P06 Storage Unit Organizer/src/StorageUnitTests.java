//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P06 Storage Unit Organizer
// Files: "Box.java", "LinkedBoxNode.java", "LinkedBoxList.java", (for testing)
// "StorageUnitTests.java"
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
    System.out
        .println("testLinkedBoxListAdd(): " + testLinkedBoxListAdd() + System.lineSeparator());
    System.out.println(
        "testLinkedBoxListRemove(): " + testLinkedBoxListRemove() + System.lineSeparator());
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

  /**
   * Checks whether add method defined in LinkedBoxList class works correctly
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testLinkedBoxListAdd() {
    boolean testPassed = false;
    boolean test1 = false; // sub-test 1 for checking IllegalStateException
    boolean test2 = false; // sub-test 2 for checking IllegalArgumentException
    boolean test3 = false; // check the sorting of Box objects in the LinkedBoxList

    LinkedBoxList list = new LinkedBoxList(3);
    list.add(new Box(1, 4));
    list.add(new Box(3, 2));
    list.add(new Box(5, 8));

    try {
      list.add(new Box(7, 8));
    } catch (IllegalStateException e) {
      test1 = true;
    }

    if (test1 == false) {
      System.out.println("Problem detected: when adding a box beyond capacity, an "
          + "IllegalStateException should be thrown. But, it was not the case.");
    }

    try {
      list.add(null);
    } catch (IllegalArgumentException e) {
      test2 = true;
    }

    if (test2 == false) {
      System.out.println("Problem detected: when adding a box that is null, an "
          + "IllegalArgumentException should be thrown. But, it was not the case.");
    }

    // check that Box objects in the LinkedBoxList are now sorted from the heaviest to the lightest
    if (list.get(0).equals(new Box(5, 8)) && list.get(1).equals(new Box(1, 4))
        && list.get(2).equals(new Box(3, 2))) {
      test3 = true;
    } else {
      System.out.println("Problem detected: after adding Box objects, all boxes should be sorted "
          + "in a descending order based on weights. But, it was not the case.");
    }

    if (test1 == true && test2 == true && test3 == true) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Checks whether remove method defined in LinkedBoxList class works correctly
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testLinkedBoxListRemove() {
    boolean testPassed = false;

    LinkedBoxList list = new LinkedBoxList(3);
    list.add(new Box(1, 4));
    list.add(new Box(3, 2));
    list.add(new Box(5, 8));
    list.remove(1);

    if (list.size() == 2) {
      if (list.get(0).equals(new Box(5, 8)) && list.get(1).equals(new Box(3, 2))) {
        testPassed = true;
      } else {
        System.out.println("Problem detected: the remove() method is not removing nodes from "
            + "a LinkedBoxList.");
      }
    } else {
      System.out.println("Problem detected: after removing one node from a LinkedBoxList with "
          + "3 nodes, the size of the list should be reduced to 2. But, it was not the case.");
    }

    return testPassed;
  }

}
