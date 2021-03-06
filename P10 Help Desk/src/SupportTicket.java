
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
