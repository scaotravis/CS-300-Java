import processing.core.PApplet;

/**
 * Organize the capabilities that are common to all interactive things in Escape Room game
 * 
 * @author Travis Cao
 */
public class Thing {

  private final String NAME; // the constant name identifying this object
  private boolean isActive; // active means thing is visible and can be interacted with

  private static PApplet processing = null;

  // Constructor
  /**
   * Initializes name, and set isActive to true
   * 
   * @param name String to initialize the field NAME with
   */
  public Thing(String name) {
    this.NAME = name;
    this.isActive = true;
  }

  // Methods
  /**
   * Checks that the input name equals to the NAME field in a Thing object
   * 
   * @param name String to check equality with the NAME field in a Thing object
   * @return true only when contents of name equal NAME
   */
  public boolean hasName(String name) {
    return name.equals(this.NAME);
  }

  /**
   * Checks if isActive is true in a Thing object
   * 
   * @return true only when isActive is true
   */
  public boolean isActive() {
    return this.isActive;
  }

  /**
   * Changes isActive to true
   */
  public void activate() {
    this.isActive = true;
  }

  /**
   * Changes isActive to false
   */
  public void deactivate() {
    this.isActive = false;
  }

  /**
   * This method returns null in all cases. Subclass types will override this update() method to do
   * more interesting things
   * 
   * @return null
   */
  public Action update() {
    return null;
  }

  /**
   * Initializes processing field to be a new PApplet object
   * 
   * @param processing PApplet static field to initialize
   */
  public static void setProcessing(PApplet processing) {
    processing = new PApplet(); 
  }

  /**
   * Accessor method to retrieve processing of type PApplet
   * 
   * @return Static field processing of type PApplet
   */
  protected static PApplet getProcessing() {
    return processing; 
  }
  
}
