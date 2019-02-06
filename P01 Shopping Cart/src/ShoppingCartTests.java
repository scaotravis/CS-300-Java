//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Tests for P01 - Simple Shopping Cart Using Java Arrays
// Files: ShoppingCart.java
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
// Online Sources:
// 1. Stack Overflow question 8708342 (how to read output printed in console)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Arrays;
// import ByteArrayOutputStream and PrintStream to read output for testing methods that print
// into the console
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ShoppingCartTests {

  /**
   * Checks whether the total number of items within the cart is incremented after adding one item
   * 
   * @return true if the test passes without problems, false otherwise
   */
  public static boolean testCountIncrementedAfterAddingOnlyOneItem() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[20]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // Add an item to the cart
    count = ShoppingCart.add(3, cart, count); // add an item of index 3 to the cart
    // Check that count was incremented
    if (count != 1) {
      System.out.println("Problem detected: After adding only one item to the cart, "
          + "the cart count should be incremented. But, it was not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks whether add and OccurrencesOf return the correct output when only one item is added to
   * the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddAndOccurrencesOfForOnlyOneItem() {
    boolean testPassed = true; // evaluated to true if test passed without problems
                               // false otherwise
    // define the shopping cart as an oversize array of elements of type String
    // we can set an arbitrary capacity for the cart - for instance 10
    String[] cart = new String[10]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // check that OccurrencesOf returns 0 when called with an empty cart
    if (ShoppingCart.occurrencesOf(10, cart, count) != 0) {
      System.out.println("Problem detected: Tried calling OccurrencesOf() method when the cart is "
          + "empty. The result should be 0. But, it was not.");
      testPassed = false;
    }

    // add one item to the cart
    count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart

    // check that OccurrencesOf("Apples", cart, count) returns 1 after adding the item with key 0
    if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
      System.out.println("Problem detected: After adding only one item with key 0 to the cart, "
          + "OccurrencesOf to count how many of that item the cart contains should return 1. "
          + "But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that items can be added more than one time and are found
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddOccurrencesOfDuplicateItems() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[20]; // shopping cart
    int count = 0; // number of items present in the cart (initially the cart is empty)

    // add item with index 3 to the cart twice, and check that cart size now should be 2
    int timesToAdd = 2;
    for (int i = 0; i < timesToAdd; i++) {
      count = ShoppingCart.add(3, cart, count);
    }
    if (count != 2) {
      System.out.println("Problem detected: After adding item with key 3 twice, add() should "
          + "return count with 2. However, this was not the case.");
      testPassed = false;
    }

    // check the occurrence of item with index 3 is now 2
    if (ShoppingCart.occurrencesOf(3, cart, count) != 2) {
      System.out.println("Problem detected: After adding item with key 3 twice, occurencesOf() "
          + "should return 2. However, this was not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks that the correct output is returned when the user tries to add too much items to the
   * cart exceeding its capacity
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testAddingTooMuchItems() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[20]; // shopping cart
    int count = 20; // initialize the cart to be full

    // check that add(0, cart, count) prints correct warning into the console
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream
    count = ShoppingCart.add(0, cart, count); // call method
    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console

    String methodOutputString = baos.toString(); // store printed method output into a String

    if (!(methodOutputString.equals(
        "WARNING: The cart is full. You cannot add any new item." + System.lineSeparator()))) {
      System.out.println("Problem detected: When the cart is full, the program should return a "
          + "warning message on cart being full. But, it was not the case.");
      System.out.println(baos.toString());
      testPassed = false;
    }

    // check that add(0, cart, count) returns 20 since the cart is full
    if (count != 20) {
      System.out.println("Problem detected: After adding only one item with key 0 to a cart with "
          + "full capacity 20, add() should still return count 20. But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that when only one attempt to remove an item present in the cart is made, only one
   * occurrence of that item is removed from the cart
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveOnlyOneOccurrenceOfItem() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[] {"Apple", "Cheese", "Blueberry", "Cheese", "Cucumber", null, null,
        null, null, null}; // shopping cart
    int count = 5; // number of items present in the cart

    // check that removing Cheese only removes its first occurrence (at index 1)
    count = ShoppingCart.remove("Cheese", cart, count);
    if (count != 4) {
      System.out.println("Problem detected: After removing one occurrence of an item, remove() "
          + "should return the new total number of items in the cart. But, it was not the case.");
      testPassed = false;
    }

    // check that the position of Cheese is filled by the last item in cart
    if (cart[1] != "Cucumber" | cart[4] != null) {
      System.out.println("Problem detected: After removing the first occurence of an item, "
          + "remove() should replace the first Cheese with the last item in the cart, and the "
          + "holder for previous last item should be set to null. But, it was not the case.");
      testPassed = false;
    }
    return testPassed;
  }

  /**
   * Checks that when the user tries to remove an item not present within the cart, remove will
   * print out warning message and leave the cart unchanged
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveItemNotFoundInCart() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[] {"Apple", "Cheese", "Blueberry", "Cheese", "Cucumber", null, null,
        null, null, null}; // shopping cart
    int count = 5; // number of items present in the cart

    // create a deep copy of the cart to later check that cart contents remain the same
    String[] cartReference = new String[] {"Apple", "Cheese", "Blueberry", "Cheese", "Cucumber",
        null, null, null, null, null};

    // check that remove("Pepper", cart, count) produces the correct warning message
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream
    count = ShoppingCart.remove("Pepper", cart, count); // call method
    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console

    String methodOutputString = baos.toString(); // store printed method output into a String

    if (!(methodOutputString
        .equals("WARNING: Pepper not found in the shopping cart." + System.lineSeparator()))) {
      System.out.println("Problem detected: After removing a nonexisting item from the cart, "
          + "a warning message should be printed into the console. But, it was not the case.");
      testPassed = false;
    }

    // check that remove("Pepper", cart, count) returns the correct count of cart size
    if (count != 5) {
      System.out.println("Problem detected: After removing a nonexisting item from the cart, "
          + "number of items in the cart should remain unchanged. But, it was not the case.");
      testPassed = false;
    }

    // check that remove("Pepper", cart, count) does not modify the cart since Pepper does not
    // exist in our shopping cart
    if (!(Arrays.deepEquals(cart, cartReference))) {
      System.out.println("Problem detected: After removing a nonexisting item from the cart, "
          + "the cart contents should not change. But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that removing the only item in cart returns cart size 0, and then cart is modified to be
   * of the same length but with all null elements
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testRemoveTheOnlyItemInCart() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[] {"Spinach", null, null, null, null, null, null}; // shopping cart
    int count = 1;

    // check that remove the only one item in cart, Spinach, will return count = 0
    count = ShoppingCart.remove("Spinach", cart, count);
    if (count != 0) {
      System.out.println("Problem detected: After removing the only item in cart, number of "
          + "items in cart should become 0. But, it was not the case.");
      testPassed = false;
    }

    // check that cart now should be an array with 7 null values
    String[] cartCorrect = new String[cart.length];
    for (int i = 0; i < cart.length; i++) {
      cartCorrect[i] = null; // the correct cart output should have all array elements be empty
    }
    if (!(Arrays.deepEquals(cart, cartCorrect))) {
      System.out.println("Problem detect: After removing the only item in cart, the cart should "
          + "be completely empty with all elements having null value. But, it was not the case.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that the sub total price of a shopping cart is calculated correctly
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testGetSubTotalPrice() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart = new String[] {"Apple", "Cheese", "Blueberry", "Cheese", "Cucumber", null, null,
        null, null, null}; // shopping cart
    int count = 5; // number of items present in the cart

    double correctPrice = 1.59 + 3.49 * 2 + 6.89 + 0.79;
    if (Math.abs(ShoppingCart.getSubTotalPrice(cart, count) - correctPrice) > 0.0001) {
      System.out.println("Problem detected: The sub total price is calculated incorrectly.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that the head, tail, and a middle section of the market catalog is printed correctly
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testPrintMarketCatalog() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    // check that correct cart content was displayed
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream
    ShoppingCart.printMarketCatalog(); // call method
    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console

    String methodOutputString = baos.toString(); // store printed catalog into a String

    final String CATALOG_HEAD = "+++++++++++++++++++++++++++++++++++++++++++++"
        + System.lineSeparator() + "Item id" + "\t\t" + "Description" + "    \t " + "Price"
        + System.lineSeparator() + "+++++++++++++++++++++++++++++++++++++++++++++"
        + System.lineSeparator() + "0" + "\t\t" + "Apple" + "    \t " + "$1.59"
        + System.lineSeparator() + "1" + "\t\t" + "Avocado" + "    \t " + "$0.59";
    final String CATALOG_TAIL = "23" + "\t\t" + "Spinach" + "    \t " + "$3.09"
        + System.lineSeparator() + "24" + "\t\t" + "Tomato" + "    \t " + "$1.79"
        + System.lineSeparator() + "+++++++++++++++++++++++++++++++++++++++++++++";
    final String CATALOG_MIDDLE = "10" + "\t\t" + "Chicken" + "    \t " + "$5.09"
        + System.lineSeparator() + "11" + "\t\t" + "Chocolate" + "    \t " + "$3.19";

    // check that catalog head is correctly printed
    if (!(methodOutputString.contains(CATALOG_HEAD))) {
      System.out.println("Problem detected: printed catalog should contain the head of example "
          + "catalog given. But, it was not the case");
      testPassed = false;
    }

    // check that catalog tail is correctly printed
    if (!(methodOutputString.contains(CATALOG_TAIL))) {
      System.out.println("Problem detected: printed catalog should contain the tail of example "
          + "catalog given. But, it was not the case");
      testPassed = false;
    }

    // check that a middle section of the catalog is correctly printed
    if (!(methodOutputString.contains(CATALOG_MIDDLE))) {
      System.out.println("Problem detected: printed catalog should contain a middle section of the"
          + " example catalog given. But, it was not the case");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that cart contents are correctly displayed
   * 
   * @return true if test passed without problems, false otherwise
   */
  public static boolean testDisplayCartContent() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise
    String[] cart =
        new String[] {"Apple", "Tomato", "Apple", "Milk", null, null, null, null, null, null};
    int count = 4;

    // check that correct cart content was displayed
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream
    ShoppingCart.displayCartContent(cart, count); // call method
    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console

    String methodOutputString = baos.toString(); // store printed method output into a String

    if (!(methodOutputString.equals("Apple, Tomato, Apple, Milk, "))) {
      System.out.println("Problem detected: cart contents are displayed incorrectly.");
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * main method used to call all unit tests
   * 
   * @param args all arguments used to call tests
   */
  public static void main(String[] args) {
    System.out.println("*************Tests Begin*************");
    System.out.println();
    System.out.println("testCountIncrementedAfterAddingOnlyOneItem(): "
        + testCountIncrementedAfterAddingOnlyOneItem() + System.lineSeparator());
    System.out.println("testAddAndOccurrencesOfForOnlyOneItem(): "
        + testAddAndOccurrencesOfForOnlyOneItem() + System.lineSeparator());
    System.out.println("testAddOccurrencesOfDuplicateItems(): "
        + testAddOccurrencesOfDuplicateItems() + System.lineSeparator());
    System.out
        .println("testAddingTooMuchItems(): " + testAddingTooMuchItems() + System.lineSeparator());
    System.out.println("testRemoveOnlyOneOccurrenceOfItem(): " + testRemoveOnlyOneOccurrenceOfItem()
        + System.lineSeparator());
    System.out.println("testRemoveItemNotFoundInCart(): " + testRemoveItemNotFoundInCart()
        + System.lineSeparator());
    System.out.println(
        "testRemoveTheOnlyItemInCart(): " + testRemoveTheOnlyItemInCart() + System.lineSeparator());
    System.out
        .println("testGetSubTotalPrice(): " + testGetSubTotalPrice() + System.lineSeparator());
    System.out
        .println("testPrintMarketCatalog(): " + testPrintMarketCatalog() + System.lineSeparator());
    System.out
        .println("testDisplayCartContent(): " + testDisplayCartContent() + System.lineSeparator());
    System.out.println("*************Tests Completed*************");
  }
}
