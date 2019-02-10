import java.util.ArrayList;
import java.util.Scanner;

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
      if (this.books.get(i).getTitle().equalsIgnoreCase(title)) {
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
      if (this.books.get(i).getAuthor().equalsIgnoreCase(author)) {
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
    System.out.println("Book with Title " + this.books.get(this.books.size() - 1).getTitle()
        + " is successfully added to the library."); // last added item has index .size() - 1
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
    this.subscribers.add(new Subscriber(name, pin, address, phoneNumber));
    System.out.println("Library card with bar code "
        + this.subscribers.get(this.subscribers.size() - 1).getCARD_BAR_CODE()
        + " is successfully issued to the new subscriber "
        + this.subscribers.get(this.subscribers.size() - 1).getName() + ".");
    // last added item has index .size() - 1
  }

  /**
   * Finds a subscriber given its cardBarCode. This method displayed the following message: "Error:
   * this card bar code didn't match any of our records." and returns null if the provided
   * cardBarCode did not match with any of the subscribers' card bar codes
   * 
   * @param cardBarCode of the subscriber to find
   * @return a reference to the subscriber if found, otherwise null
   */
  public Subscriber findSubscriber(int cardBarCode) {
    int subscriberFound = -1;

    for (int i = 0; i < this.subscribers.size(); i++) {
      if (this.subscribers.get(i).getCARD_BAR_CODE() == cardBarCode) {
        subscriberFound = i;
        break;
      }
    }

    if (subscriberFound != -1) {
      return this.subscribers.get(subscriberFound);
    } else {
      System.out.println("Error: this card bar code didn't match any of our records.");
      return null;
    }
  }

  /**
   * Displays a list of books
   * 
   * @param books ArrayList of books
   */
  public void displayBooks(ArrayList<Book> books) {
    // Traverse the list of books and display book id, title, author, and availability of each book
    for (int i = 0; i < books.size(); i++) {
      System.out.print("<Book ID>: " + books.get(i).getID() + " ");
      System.out.print("<Title>: " + books.get(i).getTitle() + " ");
      System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
      System.out.println("<Is Available>: " + books.get(i).isAvailable());
    }
  }

  /**
   * Reads and processes the user commands with respect to the menu of this application
   * 
   * @param scanner Scanner object used to read the user command lines
   */
  public void readProcessUserCommand(Scanner scanner) {
    final String PROMPT_COMMAND_LINE = "ENTER COMMAND: ";
    displayMainMenu(); // display the library management system main menu
    System.out.print(PROMPT_COMMAND_LINE);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    while (commands[0].trim().charAt(0) != '3') { // '3': Exit the application
      switch (commands[0].trim().charAt(0)) {
        case '1': // login as librarian commands[1]: password
          if (this.librarian.checkPassword(commands[1])) {
            // read and process librarian commands
            readProcessLibrarianCommand(scanner);
          } else { // error password
            System.out.println("Error: Password incorrect!");
          }
          break;
        case '2': // login as subscriber commands[1]: card bar code
          Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
          if (subscriber != null) {
            if (subscriber.getPin() == Integer.parseInt(commands[2])) // correct PIN
              // read and process subscriber commands
              readProcessSubscriberCommand(subscriber, scanner);
            else
              System.out.println("Error: Incorrect PIN.");
          }
          break;
      }
      // read and split next user command line
      displayMainMenu(); // display the library management system main menu
      System.out.print(PROMPT_COMMAND_LINE);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Displays the main menu for this book library application
   */
  private static void displayMainMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("     Welcome to our Book Library Management System");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <password>] Login as a librarian");
    System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
    System.out.println("[3] Exit"); // Exit the application
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for a Subscriber
   */
  private static void displaySubscriberMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Subscriber's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <book ID>] Check out a book");
    System.out.println("[2 <book ID>] Return a book");
    System.out.println("[3 <title>] Search a Book by title");
    System.out.println("[4 <author>] Search a Book by author");
    System.out.println("[5] Print list of books checked out");
    System.out.println("[6] Print history of returned books");
    System.out.println("[7 <address>] Update address");
    System.out.println("[8 <phone number>] Update phone number");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for the Librarian
   */
  private static void displayLibrarianMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Librarian's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <title> <author>] Add new Book");
    System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
    System.out.println("[3 <card bar code> <book ID>] Check out a Book");
    System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
    System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
    System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
    System.out.println("[7] Display All Books");
    System.out.println("[8 <book ID>] Remove a Book");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Display the Application GoodBye and logout message.
   */
  private static void displayGoodByeLogoutMessage() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("       Thanks for Using our Book Library App!!!!");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Reads and processes the librarian commands according to the librarian menu
   * 
   * @param scanner Scanner object used to read the librarian command lines
   */
  private void readProcessLibrarianCommand(Scanner scanner) {
    final String PROMPT_COMMAND_LINE = "ENTER COMMAND: ";
    displayLibrarianMenu(); // display the librarian menu
    System.out.print(PROMPT_COMMAND_LINE);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    while (commands[0].trim().charAt(0) != '9') { // '9': Log out of commander interface
      switch (commands[0].trim().charAt(0)) {
        case '1': // add new book
          this.addBook(commands[1].trim(), commands[2].trim());
          break;
        case '2': // add new subscriber
          this.addSubscriber(commands[1].trim(), Integer.parseInt(commands[2].trim()),
              commands[3].trim(), commands[4].trim());
          break;
        case '3': // check out a book
          Subscriber subscriberWhoChecksOut =
              this.findSubscriber(Integer.parseInt(commands[1].trim()));
          Book bookToCheckOut = this.findBook(Integer.parseInt(commands[2].trim()));
          subscriberWhoChecksOut.checkoutBook(bookToCheckOut);
          break;
        case '4': // return a book
          Subscriber subscriberWhoReturns =
              this.findSubscriber(Integer.parseInt(commands[1].trim()));
          Book bookToReturn = this.findBook(Integer.parseInt(commands[2].trim()));
          subscriberWhoReturns.checkoutBook(bookToReturn);
          break;
        case '5': // display personal info of a subscriber
          Subscriber subscriberToDisplayInfo =
              this.findSubscriber(Integer.parseInt(commands[1].trim()));
          subscriberToDisplayInfo.displayPersonalInfo();
        case '6': // display books checked out by a subscriber
          Subscriber subscriberToDisplayBooksCheckedOut =
              this.findSubscriber(Integer.parseInt(commands[1].trim()));
          subscriberToDisplayBooksCheckedOut.displayBooksCheckedOut();
        case '7': // display all books
          this.displayBooks(this.books);
        case '8': // remove a book
          Book bookToRemove = this.findBook(Integer.parseInt(commands[1].trim())); 
          this.books.remove(bookToRemove); 
      }
      // read and split next user command line
      displayLibrarianMenu(); // display the library management system main menu
      System.out.print(PROMPT_COMMAND_LINE);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Reads and processes the subscriber commands according to the subscriber menu
   * 
   * @param subscriber Current logged in subscriber
   * @param scanner    Scanner object used to read the librarian command lines
   */
  private void readProcessSubscriberCommand(Subscriber subscriber, Scanner scanner) {
  }

  /**
   * Returns all books in the library, available for borrowing or not. Built for testing.
   * 
   * @return an ArrayList of all books in the library
   */
  public ArrayList<Book> getBooks() {
    return this.books;
  }

  /**
   * Main method that represents the driver for this application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // create a scanner object to read user inputs
    // create a new library object
    Library madisonLibrary = new Library("Madison, WI", "april", "abc");
    // read and process user command lines
    madisonLibrary.readProcessUserCommand(scanner);
    displayGoodByeLogoutMessage(); // display good bye message
    scanner.close();// close this scanner
  }

}
