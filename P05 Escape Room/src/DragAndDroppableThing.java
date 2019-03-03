
/**
 * Extends DraggableThing class to allow certain action to take place when the a Draggable Thing is
 * dropped
 * 
 * @author Travis Cao
 */
public class DragAndDroppableThing extends DraggableThing {

  private VisibleThing target; // object over which this object can be dropped
  private Action action; // action that results from dropping this object over target

  // Constructor
  /**
   * Initializes a new DragAndDroppableThing object
   * 
   * @param name   name of the object
   * @param x      the horizontal position
   * @param y      the vertical position
   * @param target object over which this object can be dropped
   * @param action action that results from dropping this object over target
   */
  public DragAndDroppableThing(String name, int x, int y, VisibleThing target, Action action) {
    super(name, x, y);
    this.target = target;
    this.action = action;
  }

  // Methods
  /*
   * (non-Javadoc)
   * 
   * @see DraggableThing#drop()
   * 
   * Returns action and deactivates objects in response to successful drop. When this object is over
   * its target and its target is active: deactivate both this object and the target object, and
   * return action; otherwise, return null
   */
  @Override
  protected Action drop() {
    if (target.isActive() && this.isOver(target)) {
      this.deactivate();
      target.deactivate();
      return this.action; 
    } else {
      return null; 
    }
  }

}
