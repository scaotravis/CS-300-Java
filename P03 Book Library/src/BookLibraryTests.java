
public class BookLibraryTests {

  /**
   * Checks that the constructor of Book class correctly initializes all instances fields for a new
   * Book object; this includes title, author, borrowerCardBarCode, ID, and increments nextID static
   * field.
   * 
   * @return true if the test passed, false otherwise
   */
  public static boolean testBookConstructor() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    // checks constructing the first book yields correct instances fields
    Book b1 = new Book("A Title", "An Author");
    if (b1.getTitle() != "A Title" || b1.getAuthor() != "An Author"
        || b1.getBorrowerCardBarCode() != null || b1.getID() != 1) {
      testPassed = false;
    }

    // creates the second book; checks all instances fields
    Book b2 = new Book();
    if (b2.getTitle() != null || b2.getAuthor() != null || b2.getBorrowerCardBarCode() != null
        || b2.getID() != 2) {
      testPassed = false;
    }

    // creates the third book; checks all instances fields
    Book b3 = new Book("Another Title", "Another Author");
    if (b3.getTitle() != "Another Title" || b3.getAuthor() != "Another Author"
        || b3.getBorrowerCardBarCode() != null || b3.getID() != 3) {
      testPassed = false;
    }

    return testPassed;
  }

  public static void main(String[] args) {
    System.out.println(testBookConstructor());
  }

}
