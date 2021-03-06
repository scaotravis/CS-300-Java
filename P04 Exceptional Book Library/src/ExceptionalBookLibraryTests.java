
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

/**
 * This class tests methods available for objects of class ExceptionalLibrary. Focus of testing is
 * Exception handling.
 * 
 * @author Travis Cao
 */
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
      // codes to let the printed line saved in a different PrintStream, so to not affect the
      // readability of our test
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
      // since adding a new subscriber or a book prints something into the console, we uses the
      // following codes to let the printed line saved in a different PrintStream, so to not affect
      // the readability of our test
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
      // codes to let the printed line saved in a different PrintStream, so to not affect the
      // readability of our test
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

  /**
   * Checks that if invalid arguments are passed on to add a subscriber to the library, correct
   * ParseException or InstantiationException will be thrown.
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testLibraryParseRunLibrarianAddSubscriberCommand() {
    boolean testPassed = false; // boolean local variable evaluated to true if this test passed,
                                // false otherwise
    boolean test1 = false; // boolean for each small test; initialized to false
    boolean test2 = false;
    boolean test3 = false;
    boolean test4 = false;
    boolean test5 = false;

    // since adding a new subscriber prints something into the console, we uses the following
    // codes to let the printed line saved in a different PrintStream, so to not affect the
    // readability of our test
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream

    // calls methods
    ExceptionalLibrary library = new ExceptionalLibrary("Madison", "Adam", "1111");

    try { // this should be correctly parsed and create subscriber Amy
      library.parseRunLibrarianAddSubscriberCommand(
          new String[] {"2", "Amy", "1234", "Address", "1234567890"});
      test1 = true;
    } catch (ParseException e) {
      test1 = false;
    } catch (InstantiationException e) {
      test1 = false;
    }

    try { // if the argument counts are incorrect
      library.parseRunLibrarianAddSubscriberCommand(new String[] {"2", "Sam", "1224", "Address"});
    } catch (ParseException e) {
      test2 = true; // should throw ParseException
    } catch (InstantiationException e) {
      test2 = false;
    }

    try { // if the pin cannot be correctly parsed
      library.parseRunLibrarianAddSubscriberCommand(
          new String[] {"2", "Bob", "1", "Address", "715331231"});
    } catch (ParseException e) {
      test3 = true; // should throw ParseException
    } catch (InstantiationException e) {
      test3 = false;
    }

    try { // if the phone number cannot be correctly parsed
      library.parseRunLibrarianAddSubscriberCommand(
          new String[] {"2", "Bob", "1213", "Address", "kjadga"});
    } catch (ParseException e) {
      test4 = true; // should throw ParseException
    } catch (InstantiationException e) {
      test4 = false;
    }

    try { // if way too many subscribers have already been created (i.e. more than 999999)
      for (int i = 0; i < 1000001; i++) {
        library.parseRunLibrarianAddSubscriberCommand(
            new String[] {"2", "Amy", "1234", "Address", "1234567890"});
      }
    } catch (ParseException e) {
      test5 = false;
    } catch (InstantiationException e) {
      test5 = true; // should throw InstantiationException since no more subscriber can be created
    }

    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console

    if (test1 == false) {
      System.out.println("Problem detected: using correct arguments, a subscriber should be created"
          + ". But, it was not the case.");
    }

    if (test2 == false) {
      System.out.println("Problem detected: if argument count to add a new subscriber is incorrect"
          + ", a ParseException should be thrown. But, it was not the case.");
    }

    if (test3 == false) {
      System.out.println("Problem detected: if the pin code cannot be correctly parsed, a "
          + "ParseException should be thrown. But, it was not the case.");
    }

    if (test4 == false) {
      System.out.println("Problem detected: if the phone number cannot be correctly parsed, a "
          + "ParseException should be thrown. But, it was not the case.");
    }

    if (test5 == false) {
      System.out.println("Problem detected: if subscribers created exceeded the max limit (999999)"
          + ", an InstantiationException should be thrown. But, it was not the case.");
    }

    if (test1 == true && test2 == true && test3 == true && test4 == true && test5 == true) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Checks that if invalid arguments are passed on to update phone number in a Subscriber's
   * interface, a ParseException will be thrown.
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testSubscriberParseRunSubscriberUpdatePhoneNumberCommand() {
    boolean testPassed = false; // boolean local variable evaluated to true if this test passed,
                                // false otherwise
    boolean test1 = false; // boolean for each small test; initialized to false
    boolean test2 = false;
    boolean test3 = false;
    boolean test4 = false;

    // since adding a new subscriber prints something into the console, we uses the following
    // codes to let the printed line saved in a different PrintStream, so to not affect the
    // readability of our test
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream

    // calls methods
    try {

      ExceptionalLibrary library = new ExceptionalLibrary("Madison, WI", "april", "abc");
      library.addSubscriber("Amy", 1234, "Address", "1234567890");
      test1 = true; // make sure that the constructor does not throw an InstantiationException when
                    // arguments are correct

      Subscriber subscriber = library.findSubscriber(2019000001);

      try { // update phone number, with correct int argument
        library.parseRunSubscriberUpdatePhoneNumberCommand(new String[] {"8", "12131"}, subscriber);
        test2 = true; // no ParseException should be thrown when the arguments provided are correct
      } catch (ParseException e) {
        test2 = false;
      }

      try { // update phone number, with incorrect arguments count
        library.parseRunSubscriberUpdatePhoneNumberCommand(new String[] {"8"}, subscriber);
      } catch (ParseException e) {
        test3 = true; // a ParseException should be thrown due to invalid argument count
      }

      try { // update phone number, with phoneNumber argument not being numbers
        library.parseRunSubscriberUpdatePhoneNumberCommand(new String[] {"8", "jvhdk"}, subscriber);
      } catch (ParseException e) {
        test4 = true; // a ParseException should be thrown due to phoneNumber not being int
      }

    } catch (InstantiationException e) {
      test1 = false;
    }

    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console

    if (test1 == false) {
      System.out.println("Problem detected: when inputting the correct arguments, an "
          + "InstantiationException should not be thrown. But, it was not the case.");
    }

    if (test2 == false) {
      System.out.println("Problem detected: when updating phone number with the correct object type"
          + ", a ParseException should not be thrown. But, it was not the case.");
    }

    if (test3 == false) {
      System.out.println("Problem detected: when updating phone number with incorrect amount of "
          + "argument counts, a ParseException should be thrown. But, it was not the case.");
    }

    if (test4 == false) {
      System.out.println("Problem detected: when updating phone number with non-number arguments, "
          + "a ParseException should be thrown. But, it was not the case.");
    }

    if (test1 == true && test2 == true && test3 == true && test4 == true) {
      testPassed = true;
    }

    return testPassed;
  }

  public static void main(String[] args) {
    System.out.println("===TESTS BEGIN===" + System.lineSeparator());
    System.out.println("testSubscriberParseRunSubscriberUpdatePhoneNumberCommand(): "
        + testSubscriberParseRunSubscriberUpdatePhoneNumberCommand() + System.lineSeparator());
    System.out.println(
        "testLibraryParseCardBarCode(): " + testLibraryParseCardBarCode() + System.lineSeparator());
    System.out.println("testLibraryParseRunLibrarianCheckoutBookCommand(): "
        + testLibraryParseRunLibrarianCheckoutBookCommand() + System.lineSeparator());
    System.out.println("testLibraryParseRunSubscriberReturnBookCommand(): "
        + testLibraryParseRunSubscriberReturnBookCommand() + System.lineSeparator());
    System.out.println("testLibraryParseRunLibrarianAddSubscriberCommand(): "
        + testLibraryParseRunLibrarianAddSubscriberCommand() + System.lineSeparator());
    System.out.println("===TESTS COMPLETES===");
  }

}
