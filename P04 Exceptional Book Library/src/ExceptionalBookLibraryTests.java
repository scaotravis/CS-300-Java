//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P04 Exceptional Book Library
// Files: "ExceptionalLibrary.java", "ExceptionalBookLibraryTests.java"
// Course: CS 300, Spring, 2019
//
// Author: Travis
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

public class ExceptionalBookLibraryTests {

  /**
   * Checks that the parseCardBarCode() method in ExceptionalLibrary class objects functions
   * correctly.
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testLibraryParseCardBarCode() {
    boolean testPassed = false; // boolean local variable evaluated to true if this test passed,
                                // false otherwise
    boolean test1 = false; // boolean for each small test
    boolean test2 = false;
    boolean test3 = true; // check constructor; only changes to false when InstantiationException is
                          // thrown
    try {
      // since adding a new subscriber prints something into the console, we uses the following
      // codes to let the printed line saved in a different PrintStream, so to not interfere with
      // our test
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream methodOutput = new PrintStream(baos);
      PrintStream previousOutput = System.out; // save the old system printout into previousOutput
      System.setOut(methodOutput); // tell java to print method output into a specific PrintStream

      // calls methods
      ExceptionalLibrary library = new ExceptionalLibrary("Madison", "Adam", "1111");
      library.addSubscriber("Amy", 1234, "Address", "1234567890");

      System.out.flush(); // clear out all current outputs, ready to put previousOutput back
      System.setOut(previousOutput); // put old outputs back into the console

      try {
        library.parseCardBarCode("2019000001", 0);
        test1 = true;
      } catch (ParseException e) {
        test1 = false;
        System.out.println("Problem detected: Inputing a correct cardBarCode should not throw a "
            + "ParseException. But, it was not the case.");
      }

      try {
        library.parseCardBarCode("ajbd9739791", 0);
      } catch (ParseException e) {
        test2 = true;
      } finally {
        if (test2 == false) {
          System.out.println("Problem detected: Inputing a non-Integer String should throw a "
              + "new ParseException. But, it was not the case.");
        }
      }

    } catch (InstantiationException e) {
      test3 = false;
      System.out.println("Problem detected: a new library is unable to be instantiated. Check "
          + "ExceptionalLibrary class constructor.");
    }

    if (test1 == true && test2 == true && test3 == true) {
      testPassed = true;
    }

    return testPassed;
  }


  /**
   * Checks that when checking out an invalid book, or checking a book to an invalid subscriber, the
   * correct ParseException would be thrown.
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testLibraryParseRunLibrarianCheckoutBookCommand() {
    boolean testPassed = false; // boolean local variable evaluated to true if this test passed,
                                // false otherwise
    boolean test1 = false; // boolean for each small test
    boolean test2 = false;
    boolean test3 = false;
    boolean test4 = true; // check constructor; only changes to false when InstantiationException is
                          // thrown
    try {
      // since adding a new subscriber prints something into the console, we uses the following
      // codes to let the printed line saved in a different PrintStream, so to not interfere with
      // our test
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream methodOutput = new PrintStream(baos);
      PrintStream previousOutput = System.out; // save the old system printout into previousOutput
      System.setOut(methodOutput); // tell java to print method output into a specific PrintStream

      // calls methods
      ExceptionalLibrary library = new ExceptionalLibrary("Madison", "Adam", "1111");
      library.addSubscriber("Amy", 1234, "Address", "1234567890");
      library.addBook("Title", "Author");
      library.addBook("AnotherTitle", "AnotherAuthor");

      try {
        library.parseRunLibrarianCheckoutBookCommand(new String[] {"3", "2019000001", "1"});
        test1 = true;
      } catch (ParseException e) {
        test1 = false;
      }

      try {
        library.parseRunLibrarianCheckoutBookCommand(new String[] {"3", "2019000001", "a"});
      } catch (ParseException e) {
        test2 = true;
      }

      try {
        library.parseRunLibrarianCheckoutBookCommand(new String[] {"3", "2019000001"});
      } catch (ParseException e) {
        test3 = true;
      }

      System.out.flush(); // clear out all current outputs, ready to put previousOutput back
      System.setOut(previousOutput); // put old outputs back into the console

      if (test1 == false) {
        System.out.println("Problem detected: when the commands entered are all valid, a subscriber"
            + " should be able to proceed to check out a book with correctly parsed commands. But, "
            + "it was not the case.");
      }

      if (test2 == false) {
        System.out.println("Problem detected: when checking out a book with a non-integer bookId, "
            + "a ParseException should be thrown. But, it was not the case.");
      }

      if (test3 == false) {
        System.out.println("Problem detected: when the commands String array entered is not "
            + "3 elements long, a ParseException should be thrown. But, it was not the case.");
      }

    } catch (InstantiationException e) {
      test4 = false;
      System.out.println("Problem detected: a new library is unable to be instantiated. Check "
          + "ExceptionalLibrary class constructor.");
    }

    if (test1 == true && test2 == true && test3 == true && test4 == true) {
      testPassed = true;
    }

    return testPassed;
  }


  /**
   * Checks that when a subscriber returns a book, the correct ParseException will be thrown when
   * encountering invalid arguments.
   *
   * @return true if the test passed, false otherwise
   */
  public static boolean testLibraryParseRunSubscriberReturnBookCommand() {
    boolean testPassed = false; // boolean local variable evaluated to true if this test passed,
                                // false otherwise
    boolean test1 = false; // boolean for each small test
    boolean test2 = false;
    boolean test3 = false;
    boolean test4 = true; // check constructor; only changes to false when InstantiationException is
                          // thrown
    try {
      // since adding a new subscriber prints something into the console, we uses the following
      // codes to let the printed line saved in a different PrintStream, so to not interfere with
      // our test
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream methodOutput = new PrintStream(baos);
      PrintStream previousOutput = System.out; // save the old system printout into previousOutput
      System.setOut(methodOutput); // tell java to print method output into a specific PrintStream

      // calls methods
      ExceptionalLibrary library = new ExceptionalLibrary("Madison", "Adam", "1111");
      library.addSubscriber("Amy", 1234, "Address", "1234567890");
      library.addBook("Title", "Author");
      library.addBook("AnotherTitle", "AnotherAuthor");
      Subscriber subscriber = library.findSubscriber(2019000001);

      try {
        library.parseRunSubscriberReturnBookCommand(new String[] {"2", "1"}, subscriber);
        test1 = true;
      } catch (ParseException e) {
        test1 = false;
      }

      try {
        library.parseRunSubscriberReturnBookCommand(new String[] {"2", "ajbwd"}, subscriber);
      } catch (ParseException e) {
        test2 = true;
      }

      try {
        library.parseRunSubscriberReturnBookCommand(new String[] {"2"}, subscriber);
      } catch (ParseException e) {
        test3 = true;
      }

      System.out.flush(); // clear out all current outputs, ready to put previousOutput back
      System.setOut(previousOutput); // put old outputs back into the console

      if (test1 == false) {
        System.out
            .println("Problem detected: when the commands entered are all valid, a subscriber "
                + "should be able to proceed to return a book with correctly parsed commands. But, "
                + "it was not the case.");
      }

      if (test2 == false) {
        System.out.println("Problem detected: when returning a book with a non-integer bookId, "
            + "a ParseException should be thrown. But, it was not the case.");
      }

      if (test3 == false) {
        System.out.println("Problem detected: when the commands String array entered is not "
            + "2 elements long, a ParseException should be thrown. But, it was not the case.");
      }

    } catch (InstantiationException e) {
      test4 = false;
      System.out.println("Problem detected: a new library is unable to be instantiated. Check "
          + "ExceptionalLibrary class constructor.");
    }

    if (test1 == true && test2 == true && test3 == true && test4 == true) {
      testPassed = true;
    }

    return testPassed;
  }


  public static void main(String[] args) {
    System.out.println("===TESTS BEGIN===" + System.lineSeparator());
    System.out.println(
        "testLibraryParseCardBarCode(): " + testLibraryParseCardBarCode() + System.lineSeparator());
    System.out.println("testLibraryParseRunLibrarianCheckoutBookCommand(): "
        + testLibraryParseRunLibrarianCheckoutBookCommand() + System.lineSeparator());
    System.out.println("testLibraryParseRunSubscriberReturnBookCommand(): "
        + testLibraryParseRunSubscriberReturnBookCommand() + System.lineSeparator());
    System.out.println("===TESTS COMPLETES===");
  }

}
