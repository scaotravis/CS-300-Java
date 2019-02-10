//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P03 Book Library
// Files: "Book.java", "Subscriber.java", "Librarian.java", "Library.java",
// "BookLibraryTests.java" (last one is the testing file)
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
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * A class to model a simple book
 * 
 * @author Travis Cao
 */
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

  // Constructor
  /**
   * Construct a new Book object and initialize its instance fields
   * 
   * @param title  title of this new book
   * @param author author of this new book
   */
  public Book(String title, String author) {
    this.ID = Book.nextId;
    Book.nextId++;
    this.borrowerCardBarCode = null; // when a book is created, nobody has borrowed it yet
    this.title = title;
    this.author = author;
  }

  // Methods of this class
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
