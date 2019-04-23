//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P10 Help Desk
// Files: "SupportTicket.java", "HelpDesk.java", "HelpDeskInterface.java",
// "HelpDeskTestSuite.java"
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
 * Models a support ticket received by the help desk
 * 
 * @author Travis Cao
 */
public class SupportTicket implements Comparable<SupportTicket> {

  private String message; // stores the content of the ticket that determines the ticket's priority

  // Constructor
  /**
   * Initializes a new SupportTicket object with a ticket message
   * 
   * @param message Message of the ticket that determines the priority of the ticket
   */
  public SupportTicket(String message) {
    if (message != null) {
      this.message = message;
    } else {
      throw new NullPointerException("Ticket message should not be null.");
    }
  }

  // Methods
  /**
   * Returns the message of a SupportTicket object which determines the priority of the ticket
   * 
   * @return The message set for each SupportTicket object
   */
  public String toString() {
    return this.message;
  }

  /**
   * Compares the priority of tickets in order to later prioritize
   * 
   * @param o A different SupportTicket to compare priority with
   * @return A negative integer, zero, or a positive integer as this object is less than, equal to,
   *         or greater than the SupportTicket compared.
   * 
   *         If the other SupportTicket has a longer content, a positive value will be returned; in
   *         terms of tickets with the same length, lexographical order determines the returned
   *         value (i.e. if the other SupportTicket's first character in the String is in the latter
   *         part of the alphabet, then a positive value will be returned)
   */
  public int compareTo(SupportTicket o) {
    if (this.toString().length() > o.toString().length()) {
      return 1;
    } else if (this.toString().length() < o.toString().length()) {
      return -1;
    } else { // two SupportTicket's inputs are of the same length -> compare lexograohically
             // (ignoring case)
      return this.toString().compareToIgnoreCase(o.toString());
    }
  }

}
