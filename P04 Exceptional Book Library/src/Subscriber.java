import java.util.ArrayList;

/**
 * This class models a public library subscriber. A subscriber is a card holder who can borrow
 * (checkout) and return library books
 *
 */
public class Subscriber {
  // static fields
  private final static int CARD_BAR_CODE_INIT = 2019000001; // initial card bar code assigned
  private final static int CARD_BAR_CODE_LAST = 2019999999; // last card bar code that can be
                                                             // assigned
  private final static int MAX_BOOKS_CHECKED_OUT = 10; // maximum number of books to be checked out
                                                       // one subscriber
  private static int nextCardBarCode = CARD_BAR_CODE_INIT; // class variable that represents the
                                                           // card bar
  // code of the next subscriber to be created

  // Instance fields
  private int pin; // 4-digits Personal Identification Number to verify the identity of this
                   // subscriber. The most significant digit (the digit at the 4th position from 
                   // the right) MUST NOT be zero
  private final Integer CARD_BAR_CODE; // card bar code of this subscriber

  private String name; // name of this subscriber
  private String address; // address of this subscriber
  private String phoneNumber; // phone number of this subscriber

  private ArrayList<Book> booksCheckedOut; // list of books checked out by this subscriber
                                           // and not yet
  // returned. A subscriber can have at most 10 checked out books
  private ArrayList<Book> booksReturned; // history list of the books returned by this
                                         // subscriber

  /**
   * Creates a new subscriber with given name, address, and phone number, and initializes its other
   * instance fields
   * 
   * @param name name of this subscriber
   * @param pin 4-digits personal information number of this subscriber
   * @param address address of this subscriber
   * @param phoneNumber phone number of this subscriber
   * @throws InstantiationException if no new card CARD_BAR_CODE can be
   *         issued to the new potential subscriber
   */
  public Subscriber(String name, int pin, String address, String phoneNumber)
      throws InstantiationException {
    if (nextCardBarCode < CARD_BAR_CODE_LAST) { // create a new Subscriber
      this.name = name;
      this.pin = pin;
      this.address = address;
      this.phoneNumber = phoneNumber;
      CARD_BAR_CODE = nextCardBarCode;
      nextCardBarCode++;
      booksCheckedOut = new ArrayList<Book>(MAX_BOOKS_CHECKED_OUT);
      booksReturned = new ArrayList<Book>();
    } else {
      throw new InstantiationException(
          "Error: CANNOT create a new subscriber. No more card can be issued.");
    }
  }

  /**
   * Returns this subscriber's address
   * 
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Updates this subscriber's address
   * 
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Returns this subscriber's phone number
   * 
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Updates this subscriber's phone number
   * 
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns this subscriber PIN (4-digits Personal Identification Number)
   * 
   * @return the pin
   */
  public int getPin() {
    return pin;
  }

  /**
   * Returns this subscriber's card bar code
   * 
   * @return the CARD_BAR_CODE
   */
  public Integer getCARD_BAR_CODE() {
    return CARD_BAR_CODE;
  }

  /**
   * Returns this subscriber's name
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }


  /**
   * Checks out an available book. The checkout operation fails if the maximum number of checked out
   * books by this subscriber is already reached
   * 
   * @param book reference to the book to be checked out by this subscriber
   */
  public void checkoutBook(Book book) {
    if (book.isAvailable()) // check if the book is available
      if (booksCheckedOut.size() < MAX_BOOKS_CHECKED_OUT) { // check if the subscriber did not already
                                                            // checked out MAX_BOOKS_CHECKED_OUT 
        // check out/borrow the book
        booksCheckedOut.add(book); 
        book.borrowBook(this.CARD_BAR_CODE);
      } else { // maximum number of books checked out reached
        System.out.println(
            "Checkout Failed: You cannot check out more than " + MAX_BOOKS_CHECKED_OUT + "books.");
      }
    else { // book is not available
      if (booksCheckedOut.contains(book)) // the subscriber has already checked out the book
        System.out.println("You have already checked out " + book.getTitle() + " book.");
      else // another subscriber has checked out the book
        System.out.println("Sorry, " + book.getTitle() + " is not available.");
    }
  }

  /**
   * Returns a library book
   * 
   * @param book reference to the book to return by this subscriber
   */
  public void returnBook(Book book) {
    // check if the book is stored within this Subscriber's booksCheckedOut list
    if (isBookInBooksCheckedOut(book)) {
      // return the book
      booksReturned.add(book);
      booksCheckedOut.remove(book);
      book.returnBook();
    }
    else // display an error message
      System.out.println("Sorry, you cannot return this book. It is not in your booksCheckedOut list.");
  }

  /**
   * Checks if this subscriber booksCheckedOut list contains a given book
   * 
   * @param book book to check if it is within this subscriber booksCheckedOut list
   * @return true if booksCheckedOut contains book, false otherwise
   */
  public boolean isBookInBooksCheckedOut(Book book) {
    return booksCheckedOut.contains(book);
  }

  /**
   * Checks if this subscriber booksReturned list contains a given book
   * 
   * @param book book to check if it is within this subscriber booksReturned list
   * @return true if booksReturned contains book, false otherwise
   */
  public boolean isBookInBooksReturned(Book book) {
    return booksReturned.contains(book);
  }

  /**
   * Helper method to display a list of books
   * @param books ArrayList of books
   * @param message message to display if the ArrayList books is empty
   */
  private void displayBooks(ArrayList<Book> books, String message) {
    if (books.isEmpty()) // empty list
      System.out.println(message);
    else
      // Traverse the list of books checked out by this subscriber and display its content
      for (int i = 0; i < books.size(); i++) {
        System.out.print("Book ID: " + books.get(i).getID() + " ");
        System.out.print("Title: " + books.get(i).getTitle() + " ");
        System.out.println("Author: " + books.get(i).getAuthor());
      }
  }

  /**
   * Displays the list of the books checked out and not yet returned
   */
  public void displayBooksCheckedOut() {
    displayBooks(booksCheckedOut, "No books checked out by this subscriber");
  }

  /**
   * Displays the history of the returned books by this subscriber
   */
  public void displayHistoryBooksReturned() {
    displayBooks(booksReturned, "No books returned by this subscriber");
  }


  /**
   * Displays this subscriber's personal information
   */
  public void displayPersonalInfo() {
    System.out.println("Personal information of the subscriber: " + CARD_BAR_CODE);
    System.out.println("  Name: " + name);
    System.out.println("  Address: " + address);
    System.out.println("  Phone number: " + phoneNumber);
  }

  /**
   * Checks whether a provided bar code is a correct bar code for a subscriber
   * @param barCode provided barCode to check its validity
   * @return true if the barCode is correct, false otherwise
   */
  public static boolean checkCardBarCode(int barCode) {
    return barCode >= CARD_BAR_CODE_INIT && barCode <= CARD_BAR_CODE_LAST ;
  }
}
