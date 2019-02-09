import java.util.ArrayList;

/**
 * This class models a simple book library. The main method of this class implements the management
 * system for this library.
 * 
 * @author Travis Cao
 */
public class Library {

  // instance fields
  private String address; // Street address of this library
  private Librarian librarian; // this library's librarian. This library must have only ONE
                               // librarian
  private ArrayList<Book> books; // list of the books in this library
  private ArrayList<Subscriber> subscribers; // list of this library's subscribers

  // Constructor
  /**
   * Creates a new Library and initializes all its instance fields. Initially both books and
   * subscribers lists are empty.
   * 
   * @param address           Address of this Library
   * @param librarianUsername username of the librarian of this book library
   * @param librarianLogin    login of the librarian of this book library
   */
  public Library(String address, String librarianUsername, String librarianLogin) {
    this.address = address;
    this.librarian = new Librarian(librarianUsername, librarianLogin);
    this.books = new ArrayList<Book>();
    this.subscribers = new ArrayList<Subscriber>();
  }

  // Methods of this class
  /**
   * Returns the librarian of this library
   * 
   * @return the librarian
   */
  public Librarian getLibrarian() {
    return this.librarian;
  }

  /**
   * Returns the address of this library
   * 
   * @return the address
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * Returns a Book given a book identifier if found, and null otherwise. If the book is not found,
   * this method displays the following message: "Error: this book identifier didn't match any of
   * our books identifiers."
   * 
   * @param bookId identifier of the book to find
   * @return reference to the Book if found and null otherwise
   */
  public Book findBook(int bookId) {
    try {
      int foundBookIndex = -1;

      for (int i = 0; i < this.books.size(); i++) {
        if (this.books.get(i).getID() == bookId) {
          foundBookIndex = i;
          break;
        }
      }

      if (foundBookIndex != -1) { // if a book with matching identifier is found
        return this.books.get(foundBookIndex);
      } else { // if a book with matching identifier is not found
        throw new Exception(
            "Error: this book identifier didn't match any of our books identifiers.");
      }
    } catch (Exception excpt) {
      System.out.println(excpt.getMessage());
      return null; // not finding a matching book results in returning null
    }
  }

  /**
   * Returns the list of books having a given title in this library. The comparison used by this
   * method is case insensitive
   * 
   * @param title title of the book(s) to find
   * @return ArrayList of the books having a given title in this library (0 or more books can be
   *         found)
   */
  public ArrayList<Book> findBookByTitle(String title) {
    ArrayList<Book> booksFound = new ArrayList<>();

    for (int i = 0; i < this.books.size(); i++) {
      if (this.books.get(i).getTitle().toLowerCase().equals(title.toLowerCase())) {
        // makes the method case insensitive
        booksFound.add(this.books.get(i));
      }
    }

    return booksFound;
  }

  /**
   * Returns the list of books having a given author. The comparison used by this method is case
   * insensitive
   * 
   * @param author author of the book(s) to find
   * @return ArrayList of the books having a given author (0 or more books can be found)
   */
  public ArrayList<Book> findBookByAuthor(String author) {
    ArrayList<Book> booksFound = new ArrayList<>();

    for (int i = 0; i < this.books.size(); i++) {
      if (this.books.get(i).getAuthor().toLowerCase().equals(author.toLowerCase())) {
        // makes the method case insensitive
        booksFound.add(this.books.get(i));
      }
    }

    return booksFound;
  }

  /**
   * Adds a new book to the library (to the books list). This method displays the following message:
   * "Book with Title " + title + " is successfully added to the library."
   * 
   * @param title  title of the new book
   * @param author author of the new book
   */
  public void addBook(String title, String author) {
    this.books.add(new Book(title, author));
    System.out.println("Book with Title " + title + " is successfully added to the library.");
  }

  /**
   * Removes a book given its identifier from the library (from books list)
   * 
   * @param bookId identifier of the book to remove
   * @return a reference to the removed book, and null if the book is not found in this library or
   *         if it is not available
   */
  public Book removeBook(int bookId) {
    int bookToRemoveId = -1;

    for (int i = 0; i < this.books.size(); i++) {
      if (this.books.get(i).getID() == bookId) {
        bookToRemoveId = i;
        break;
      }
    }

    if (bookToRemoveId != -1) {
      return this.books.get(bookToRemoveId);
    } else {
      return null;
    }
  }

  /**
   * Adds a new subscriber to this library (to subscribers list). This method displays the following
   * message: "Library card with bar code " + card bar code + " is successfully issued to the new
   * subscriber " + name + "."
   * 
   * @param name        name of the new subscriber
   * @param pin         4-digit personal identifier number of the new subscriber
   * @param address     address of the new subscriber
   * @param phoneNumber phone number of the new subscriber
   */
  public void addSubscriber(String name, int pin, String address, String phoneNumber) {
    // WORK CONTINUES
  }

}
