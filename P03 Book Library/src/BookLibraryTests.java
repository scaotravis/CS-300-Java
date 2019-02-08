import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class BookLibraryTests {

  /**
   * Checks that the constructor of Book class correctly initializes all instance fields for a new
   * Book object; this includes title, author, borrowerCardBarCode, ID (this test also implicitly
   * checks the increments nextID static field)
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testBookConstructor() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    // checks constructing the first book yields correct instance fields
    Book b1 = new Book("A Title", "An Author");
    if (b1.getTitle() != "A Title" || b1.getAuthor() != "An Author"
        || b1.getBorrowerCardBarCode() != null || b1.getID() != 1) {
      testPassed = false;
    }

    // creates the second book; checks all instance fields
    Book b2 = new Book("A Different Title", "A Different Author");
    if (b2.getTitle() != "A Different Title" || b2.getAuthor() != "A Different Author"
        || b2.getBorrowerCardBarCode() != null || b2.getID() != 2) {
      testPassed = false;
    }

    // creates the third book; checks all instance fields
    Book b3 = new Book("Another Title", "Another Author");
    if (b3.getTitle() != "Another Title" || b3.getAuthor() != "Another Author"
        || b3.getBorrowerCardBarCode() != null || b3.getID() != 3) {
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks whether all implemented getter methods defined within the Book class work correctly.
   * This includes .getAuthor(), .getTitle(), .getBorrowerCardBarCode(), .getID()
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testBookGetters() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    // creates a new object of Book class
    Book book = new Book("TITLE", "AUTHOR");

    // checks all get methods
    if (book.getAuthor() != "AUTHOR" || book.getTitle() != "TITLE"
        || book.getBorrowerCardBarCode() != null || book.getID() != 4) { // book ID is 4 since there
                                                                         // are already 3 books
                                                                         // created in the previous
                                                                         // test
      testPassed = false;
    }

    // checks that, after borrowing the book, the .getBorrowerCardBarCode() method should return the
    // correct borrowerCardBarCode
    book.borrowBook(12345);

    if (book.getBorrowerCardBarCode() != 12345) {
      testPassed = false;
    }

    return testPassed;
  }


  /**
   * Checks whether returnBook() in the Book class sets correctly the instance field
   * borrowerCardBarCode
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testBookReturnBook() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    Book book = new Book("TITLE", "AUTHOR");

    // sets the book to be borrowed by borrowerCardBarCode 123456
    book.borrowBook(123456);

    // returns the book, and checks that borrowerCardBarCode should now be set to null to indicate
    // that the book is available
    book.returnBook();
    if (book.getBorrowerCardBarCode() != null) {
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Checks that a new Book object is automatically available; when the book is borrowed, it becomes
   * unavailable; when the book is returned, it automatically becomes available again.
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testIsAvailable() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    Book book = new Book("TITLE", "AUTHOR");

    // book now has yet to be borrowed by anyone; checks that book is available
    if (book.isAvailable() != true) {
      testPassed = false;
    }

    // sets the book to be borrowed; checks that book now is not available
    book.borrowBook(123456);
    if (book.isAvailable() != false) {
      testPassed = false;
    }

    // returns the book; checks that book now is available again
    book.returnBook();
    if (book.isAvailable() != true) {
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * 
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testSubscriberCheckoutBook() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    // creates 13 books available for checkout
    ArrayList<Book> bookShelf = new ArrayList<>();
    for (int i = 0; i <= 13; i++) {
      bookShelf.add(new Book("Title " + i, "Author " + i));
    }

    // creates a new subscriber
    Subscriber subscriber = new Subscriber("Amy", 1234, "University Ave", "9876543210");

    // attempts to check out 11 books, which is larger than MAX_BOOKS_CHECKED_OUT (10); this should
    // return an error message informing user that they cannot check out more than
    // MAX_BOOKS_CHECKED_OUT
    for (int i = 0; i < 11; i++) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream methodOutput = new PrintStream(baos);
      PrintStream previousOutput = System.out; // save the old system printout into previousOutput
      System.setOut(methodOutput); // tell java to print method output into a specific PrintStream
      subscriber.checkoutBook(bookShelf.get(i)); // call method
      System.out.flush(); // clear out all current outputs, ready to put previousOutput back
      System.setOut(previousOutput); // put old outputs back into the console
      String methodOutputString = baos.toString(); // store printed method output into a String

      if (i != 10) {
        if (!(methodOutputString.equals(""))) {
          testPassed = false;
          break;
        }
      } else {
        if (!(methodOutputString.equals("Checkout Failed: You cannot check out more than 10 books."
            + System.lineSeparator()))) {
          testPassed = false;
          break;
        }
      }
    }

    return testPassed;
  }

  /**
   * Main method call to run all tests.
   * 
   * @param args arguments to call all testing methods.
   */
  public static void main(String[] args) {
    System.out.println(testBookConstructor());
    System.out.println(testBookGetters());
    System.out.println(testBookReturnBook());
    System.out.println(testIsAvailable());
    System.out.println(testSubscriberCheckoutBook());
  }

}
