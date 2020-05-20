
import java.util.ArrayList;

/**
 * Represents the response to an object being clicked or dragged onto another
 * 
 * @author Travis Cao
 */
public class Action {

  private String message; // message printed by this action (or null to do nothing)
  private Thing thing;

  // Constructors
  /**
   * Initializes a new Action object with a message
   * 
   * @param message A message to initialize
   */
  public Action(String message) { // initialize this new action
    this.message = message;
  }

  /**
   * Initializes a new Action object with a Thing object
   * 
   * @param thing A Thing object to activate
   */
  public Action(Thing thing) {
    this.thing = thing;
  }

  /**
   * Initializes a new Action object with a message and a Thing object
   * 
   * @param message A message to initialize
   * @param thing   A Thing object to activate
   */
  public Action(String message, Thing thing) {
    this.message = message;
    this.thing = thing;
  }

  // Methods
  /**
   * When message is not null, message is printed to System.out. When the thing object is not null,
   * it will activate the thing and add it to the ArrayList of type Thing, then sets the instance
   * field thing back to null.
   */
  public void act(ArrayList<Thing> thingList) {
    if (this.thing != null) {
      this.thing.activate();
      thingList.add(this.thing);
      this.thing = null;
    }

    if (this.message != null) {
      System.out.println(this.message);
    }
  }

}
