
public class Book {

  // class/static fields
  private static int nextId = 1; // class variable that represents the identifier of the next book

  // Instance fields
  private final int ID; // unique identifier of this book
  private String author; // name of the author of this book
  private String title; // title of this book
  private Integer borrowerCardBarCode; // card bar code of the borrower of this book
                                       // When borrowerCardBarCode == null, the book is available
                                       // (no one has the book)

  // Constructors
  /**
   * Default constructor sets book's author and title to null, gives the book correct ID, and sets
   * the book to be available.
   */
  public Book() {
    this.ID = Book.nextId;
    Book.nextId++;
    this.borrowerCardBarCode = null; // when a book is created, nobody has borrowed it yet
    this.author = null;
    this.title = null;
  }

  /**
   * Alternative constructor that does everything the default constructor does, plus sets the book
   * title and author to match the input provided
   * 
   * @param title title of the book
   * @param author author of the book
   */
  public Book(String title, String author) {
    this(); // load default constructor setup
    this.title = title;
    this.author = author;
  }


  /**
   * Return the author of this book
   * 
   * @return the author
   */
  public String getAuthor() {
    return this.author;
  }

  /**
   * Return the title of this book
   * 
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Return the the borrower's card bar code of this book if the book has been checked out or null
   * if not
   * 
   * @return the borrowerCardBarCode
   */
  public Integer getBorrowerCardBarCode() {
    return this.borrowerCardBarCode;
  }

  /**
   * Returns the ID of this Book object
   * 
   * @return the ID of this Book object
   */
  public int getID() {
    return this.ID;
  }

  /**
   * Records the borrower's card bar code of this book if the book is available. If this book is not
   * available, this method does nothing.
   * 
   * @param borrowerCardBarCode the borrowerCardBarCode to set
   */
  public void borrowBook(Integer borrowerCardBarCode) {
    if (this.getBorrowerCardBarCode() == null) {
      this.borrowerCardBarCode = borrowerCardBarCode;
    }
  }

  /**
   * Sets this book to be available. When the borrowerCardBarCode is set to null, no one is
   * borrowing it
   */
  public void returnBook() {
    this.borrowerCardBarCode = null;
  }

  /**
   * Checks if this book is available
   * 
   * @return true if no one is borrowing this book, false otherwise
   */
  public boolean isAvailable() {
    return (this.borrowerCardBarCode == null);
  }

}
