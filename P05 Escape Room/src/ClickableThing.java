import processing.core.PApplet;

/**
 * Represents objects that are interactable through clicking
 * 
 * @author Travis Cao
 */
public class ClickableThing extends VisibleThing {

  private Action action; // action returned from update when this object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed during the last update()

  // Constructor
  /**
   * Initializes a new ClickableThing object
   * 
   * @param name   name of the object
   * @param x      the horizontal position
   * @param y      the vertical position
   * @param action action returned from update when this object is clicked
   */
  public ClickableThing(String name, int x, int y, Action action) {
    super(name, x, y);
    this.action = action;
  }

  // Methods
  /*
   * (non-Javadoc)
   * 
   * @see VisibleThing#update()
   * 
   * Calls VisibleThing update, then returns action only when mouse is first clicked on the
   * clickable thing
   */
  @Override
  public Action update() {
    super.update();
    PApplet papplet = getProcessing();

    if (papplet.mousePressed == true) {
      if (this.mouseWasPressed == false) {
        if (this.isOver(papplet.mouseX, papplet.mouseY) == true) {
          this.mouseWasPressed = true; 
          return this.action; 
        } else {
          return null; 
        }
      } else {
        return null; 
      }
    } else {
      this.mouseWasPressed = false; 
      return null; 
    }
  }

}
