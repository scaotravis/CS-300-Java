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
 * This class models a librarian who works at a book library. A Librarian is responsible for
 * managing the books in the library and helping the library subscribers to checkout and return
 * books
 * 
 * @author Travis Cao
 */
public class Librarian {

  // instance fields
  private String username; // librarian's username
  private String password; // librarian password to have access to this application

  // Constructor
  /**
   * Creates a new Librarian with a given name and a given password
   * 
   * @param username username of this librarian
   * @param password password of this librarian to have access to this application
   */
  public Librarian(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // Methods of this class
  /**
   * Returns the name of this librarian
   * 
   * @return the name of this librarian
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Checks if a given password equals the password of this librarian
   * 
   * @param password a given password
   * @return true if a given password equals the password of this librarian, false otherwise
   */
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

}
