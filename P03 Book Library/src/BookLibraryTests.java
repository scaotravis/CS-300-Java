
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Tests the book library we built using Book, Subscriber, Librarian, and Library classes
 * 
 * @author Travis Cao
 */
public class BookLibraryTests {

  /**
   * Checks that the constructor of Book class correctly initializes all instance fields for a new
   * Book object; this includes title, author, borrowerCardBarCode, ID (this test also implicitly
   * checks the increments nextID static field)
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testBookConstructorGetters() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    // checks constructing the first book yields correct instance fields
    Book b1 = new Book("A Title", "An Author");
    if (b1.getTitle() != "A Title" || b1.getAuthor() != "An Author"
        || b1.getBorrowerCardBarCode() != null || b1.getID() != 1) {
      testPassed = false;
      System.out.println("Problem detected: Adding a new book using Book class constructor "
          + "failed: either title, author, borrowerCardBarCode, or ID is wrong.");
    }

    // creates the second book; checks all instance fields
    Book b2 = new Book("A Different Title", "A Different Author");
    if (b2.getTitle() != "A Different Title" || b2.getAuthor() != "A Different Author"
        || b2.getBorrowerCardBarCode() != null || b2.getID() != 2) {
      testPassed = false;
      System.out.println("Problem detected: Adding a new book using Book class constructor "
          + "failed: either title, author, borrowerCardBarCode, or ID is wrong.");
    }

    // creates the third book; checks all instance fields
    Book b3 = new Book("Another Title", "Another Author");
    if (b3.getTitle() != "Another Title" || b3.getAuthor() != "Another Author"
        || b3.getBorrowerCardBarCode() != null || b3.getID() != 3) {
      testPassed = false;
      System.out.println("Problem detected: Adding a new book using Book class constructor "
          + "failed: either title, author, borrowerCardBarCode, or ID is wrong.");
    }

    // checks that, after borrowing b3, its .getBorrowerCardBarCode() method should return the
    // correct borrowerCardBarCode
    b3.borrowBook(12345);

    if (b3.getBorrowerCardBarCode() != 12345) {
      testPassed = false;
      System.out.println("Problem detected: After borrowing a book, borrowerCardBarCode should "
          + "change from null to subscriber's borrowerCardBarCode. But, it was not the case.");
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
      System.out.println("Problem detected: returning a book should result in borrowerCardBarCode "
          + "changed back to null. But, it was not the case.");
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
      System.out.println("Problem detected: after adding a new book, the book should be available "
          + "for borrowing. But, it was not the case.");
    }

    // sets the book to be borrowed; checks that book now is not available
    book.borrowBook(123456);
    if (book.isAvailable() != false) {
      testPassed = false;
      System.out.println("Problem detected: after one subscriber checking out the book, the book "
          + "should be unavailable. But, it was not the case.");
    }

    // returns the book; checks that book now is available again
    book.returnBook();
    if (book.isAvailable() != true) {
      testPassed = false;
      System.out.println("Problem detected: after a book is returned, the book should become "
          + "available. But, it was not the case.");
    }

    return testPassed;
  }

  /**
   * Checks that when check out books, a subscriber can only check out less than
   * MAX_BOOKS_CHECKED_OUT allowed. Plus, checking out a book results in correct recording of
   * borrowerCardBarCode in the book object, and the checked out book is recorded in subscriber's
   * booksCheckedOut list
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testSubscriberCheckoutBook() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    // creates 13 books (of index 0 to 12) available for checkout
    ArrayList<Book> bookShelf = new ArrayList<>();
    for (int i = 0; i < 13; i++) {
      bookShelf.add(new Book("Title " + i, "Author " + i));
    }

    // creates a new subscriber
    Subscriber subscriber = new Subscriber("Amy", 1234, "University Ave", "9876543210");

    // attempts to check out 11 books, which is larger than MAX_BOOKS_CHECKED_OUT (10); this should
    // return an error message informing user that they cannot check out more than
    // MAX_BOOKS_CHECKED_OUT
    for (int i = 0; i < 11; i++) {

      // below records the warning message printed into the console, if there is any
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream methodOutput = new PrintStream(baos);
      PrintStream previousOutput = System.out; // save the old system printout into previousOutput
      System.setOut(methodOutput); // tell java to print method output into a specific PrintStream
      subscriber.checkoutBook(bookShelf.get(i)); // calls method
      System.out.flush(); // clear out all current outputs, ready to put previousOutput back
      System.setOut(previousOutput); // put old outputs back into the console
      String methodOutputString = baos.toString(); // store printed method output into a String

      if (i != 10) { // book from index 0 to 9 was able to check out
        if (!(methodOutputString.equals(""))) { // since the books can be checked out, no warning
                                                // message should be printed
          testPassed = false;
          System.out.println("Problem detected: when a subscriber has borrowed less than "
              + "MAX_BOOKS_CHECKED_OUT, they should be able to borrow all available books with no "
              + "warning message printed into the console. But, it was not the case.");
          break;
        }
        if (subscriber.isBookInBooksCheckedOut(bookShelf.get(i)) == false) { // book from index 0 to
                                                                             // 9 should be
                                                                             // unavailable
          testPassed = false;
          System.out.println("Problem detected: when checking out a book, the book should be "
              + "added to the subscriber's list of borrowed books. But, it was not the case.");
          break;
        }
        if (!(bookShelf.get(i).getBorrowerCardBarCode().equals(subscriber.getCARD_BAR_CODE()))) {
          // book being checked out should record correct borrowerCardBarCode
          testPassed = false;
          System.out.println("Problem detected: when a subscriber borrows a book, the book should "
              + "record the subscriber's borrowerCardBarCode. But, it was not the case.");
          break;
        }
      } else { // book of index 10 cannot be checked out
        if (!(methodOutputString.equals("Checkout Failed: You cannot check out more than 10 books."
            + System.lineSeparator()))) {
          testPassed = false;
          System.out.println("Problem detected: when a borrower borrowed more than "
              + "MAX_BOOKS_CHECKED_OUT, the subscriber should not be able to borrow any more book, "
              + "and a warning message should be printed into the console. But, it was not the "
              + "case.");
          break;
        }
        if (subscriber.isBookInBooksCheckedOut(bookShelf.get(i)) == true) { // book of index 10
                                                                            // should still be
                                                                            // available
          testPassed = false;
          System.out.println("Problem detected: when a borrower borrowed more than "
              + "MAX_BOOKS_CHECKED_OUT, the subscriber should not be able to borrow any more book. "
              + "But, it was not the case.");
          break;
        }
        if (bookShelf.get(i).getBorrowerCardBarCode() != null) { // book of index 10 should have
                                                                 // borrowerCardBarCode to be null
          testPassed = false;
          System.out.println("Problem detected: when a borrower borrowed more than "
              + "MAX_BOOKS_CHECKED_OUT, the subscriber should not be able to borrow any more book. "
              + "Thus, any book the subscriber attempts to borrow should remain available. But, "
              + "it was not the case.");
          break;
        }
      }
    }

    return testPassed;
  }

  /**
   * Checks that if a subscriber wants to check out a book already in their hand, the correct
   * warning message will be printed in the console
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testSubscriberRepeatedlyCheckout() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    Book book = new Book("Title", "Author");
    Subscriber subscriber = new Subscriber("Adam", 1357, "Johnson St", "1234567890");

    // subscriber repeatedly checks out book
    // below records the warning message printed into the console
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream
    // calls method
    for (int i = 0; i < 2; i++) { // check out twice
      subscriber.checkoutBook(book);
    }
    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console
    String methodOutputString = baos.toString(); // store printed method output into a String

    if (!(methodOutputString.equals(
        "You have already checked out " + book.getTitle() + " book." + System.lineSeparator()))) {
      testPassed = false;
      System.out.println("Problem detected: when a subscriber attempts to check out a book already "
          + "in their hand, a warning message should be printed into the console. But, it was not "
          + "the case.");
    }

    return testPassed;
  }

  /**
   * Checks that if a subscriber is trying to check out a book that is not available, then a warning
   * message will be printed into the console
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testSubscriberCheckOutBookNotAvailable() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    Book book = new Book("Title", "Author");
    Subscriber subscriber1 = new Subscriber("Amy", 1234, "University Ave", "9876543210");
    Subscriber subscriber2 = new Subscriber("Adam", 1357, "Johnson St", "1234567890");

    // subscriber1 and 2 both check out the same book
    // below records the warning message printed into the console
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream
    // calls method
    subscriber1.checkoutBook(book);
    subscriber2.checkoutBook(book);
    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console
    String methodOutputString = baos.toString(); // store printed method output into a String

    if (!(methodOutputString
        .equals("Sorry, " + book.getTitle() + " is not available." + System.lineSeparator()))) {
      testPassed = false;
      System.out.println("Problem detected: when a subscriber attempts to check out a book that is "
          + "not available, a warning message should be printed into the console. But, it was "
          + "not the case.");
    }

    return testPassed;
  }

  /**
   * Checks that a librarian has to have the correct password to access the library system
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testLibrarianCheckPassword() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    Librarian librarian = new Librarian("Name", "PaSsWoRd");

    if (librarian.checkPassword("password") == true) {
      testPassed = false;
      System.out.println("Problem detected: when a librarian keys in the wrong password, checking "
          + "their password should return false. But, it was not the case.");
    }

    if (librarian.checkPassword("PaSsWoRd") == false) {
      testPassed = false;
      System.out.println("Problem detected: when a librarian keys in the correct password, checking"
          + " their password should return true. But, it was not the case.");
    }

    return testPassed;
  }

  /**
   * Checks that findBookByAuthor() method in Library class can correctly return a book (or books)
   * of the specified author (case insensitive), and if no book with the specified author exists, an
   * empty ArrayList of class Book is returned
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testLibraryFindBookByAuthor() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    Library library = new Library("Madison", "Bob", "9087");

    // below records all messages printed into the console
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream methodOutput = new PrintStream(baos);
    PrintStream previousOutput = System.out; // save the old system printout into previousOutput
    System.setOut(methodOutput); // tell java to print method output into a specific PrintStream

    // calls methods
    library.addBook("Title 1", "Adam Something");
    library.addBook("Title 2", "Adam Something");
    library.addBook("Title 3", "Amy");
    ArrayList<Book> booksFound1 = library.findBookByAuthor("ADAM soMEthinG");
    ArrayList<Book> booksFound2 = library.findBookByAuthor("Nonexisting Author");

    System.out.flush(); // clear out all current outputs, ready to put previousOutput back
    System.setOut(previousOutput); // put old outputs back into the console
    String methodOutputString = baos.toString(); // store printed method output into a String

    // what should be returned
    ArrayList<Book> booksFound1Correct = new ArrayList<>();
    booksFound1Correct.add(library.getBooks().get(0));
    booksFound1Correct.add(library.getBooks().get(1));

    if (!(booksFound1.equals(booksFound1Correct))) {
      testPassed = false;
      System.out.println("Problem detected: use findBookByAuthor() method should result in an "
          + "ArrayList containing all books by that author (case insensitive for what author "
          + "name has been inputed). But, it was not the case.");
    }
    if (!(booksFound2.equals(new ArrayList<Book>()))) {
      testPassed = false;
      System.out.println("Problem detected: when no book by a specific author exists in the library"
          + ", an empty ArrayList should be returned. But, it was not the case.");
    }

    // checks that correct outputs have been printed into the console (result from adding books to
    // the library
    final String CORRECT_CONSOLE_OUTPUT =
        "Book with Title Title 1 is successfully added to the library." + System.lineSeparator()
            + "Book with Title Title 2 is successfully added to the library."
            + System.lineSeparator()
            + "Book with Title Title 3 is successfully added to the library."
            + System.lineSeparator();
    if (!(methodOutputString.equals(CORRECT_CONSOLE_OUTPUT))) {
      testPassed = false;
    }

    return testPassed;
  }

  /**
   * Main method call to run all tests.
   * 
   * @param args arguments to call all testing methods.
   */
  public static void main(String[] args) {
    System.out.println("===TESTS BEGIN===" + System.lineSeparator());
    System.out.println(
        "testBookConstructorGetters(): " + testBookConstructorGetters() + System.lineSeparator());
    System.out.println("testBookReturnBook(): " + testBookReturnBook() + System.lineSeparator());
    System.out.println("testIsAvailable(): " + testIsAvailable() + System.lineSeparator());
    System.out.println(
        "testSubscriberCheckoutBook(): " + testSubscriberCheckoutBook() + System.lineSeparator());
    System.out.println("testSubscriberRepeatedlyCheckout(): " + testSubscriberRepeatedlyCheckout()
        + System.lineSeparator());
    System.out.println("testSubscriberCheckOutBookNotAvailable(): "
        + testSubscriberCheckOutBookNotAvailable() + System.lineSeparator());
    System.out.println(
        "testLibrarianCheckPassword(): " + testLibrarianCheckPassword() + System.lineSeparator());
    System.out.println(
        "testLibraryFindBookByAuthor(): " + testLibraryFindBookByAuthor() + System.lineSeparator());
    System.out.println("===TESTS COMPLETES===");
  }

}
