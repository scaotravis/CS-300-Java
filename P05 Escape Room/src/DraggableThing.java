import processing.core.PApplet;

/**
 * Represents objects that can be interacted through dragging
 * 
 * @author Travis Cao
 */
public class DraggableThing extends VisibleThing {

  private boolean mouseWasPressed; // similar to use in ClickableThing
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update

  // Constructor
  /**
   * Initializes a new DraggableThing object
   * 
   * @param name name of the object
   * @param x    the horizontal position
   * @param y    the vertical position
   */
  public DraggableThing(String name, int x, int y) {
    super(name, x, y);
    this.oldMouseX = x;
    this.oldMouseY = y;
  }

  // Methods
  /*
   * (non-Javadoc)
   * 
   * @see VisibleThing#update()
   * 
   * calls VisibleThing update(), then moves according to mouse drag. Each time isDragging changes
   * from true to false, the drop() method below will be called once and any action objects returned
   * from that method should then be returned from update()
   */
  @Override
  public Action update() {
    super.update();
    PApplet papplet = getProcessing();

    this.mouseWasPressed = false; // initializes as mouse not being pressed
    
    if (this.mouseWasPressed == false) {
      if (papplet.mousePressed == true) {
        this.mouseWasPressed = true; 
        if (isOver(papplet.mouseX, papplet.mouseY) == true) {
          this.isDragging = true; 
        }
      } else {
        this.isDragging = false; 
        this.mouseWasPressed = false; 
      }
    }

    if (this.isDragging == true) {
      int movedX = papplet.mouseX - this.oldMouseX;
      int movedY = papplet.mouseY - this.oldMouseY;
      super.move(movedX, movedY);
      this.oldMouseX = papplet.mouseX;
      this.oldMouseY = papplet.mouseY;
      return null;
    } else {
      return this.drop();
    }

  }

  /**
   * This method returns null. Subclass types will override this drop() method to do more
   * interesting things
   * 
   * @return null
   */
  protected Action drop() {
    return null;
  }

}
