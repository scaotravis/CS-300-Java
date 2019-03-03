
/**
 * Represents the response to an object being clicked or dragged onto another
 * 
 * @author Travis Cao
 */
public class Action {

  private String message; // message printed by this action (or null to do nothing)

  // Constructor
  /**
   * Constructor for objects of class Action
   * 
   * @param message Initialize a new Action object with this message
   */
  public Action(String message) { // initialize this new action
    this.message = message; 
  }

  // Methods
  /**
   * When message is not null, message is printed to System.out
   */
  public void act() {
    if (this.message != null) {
      System.out.println(this.message);
    }
  } 

}
