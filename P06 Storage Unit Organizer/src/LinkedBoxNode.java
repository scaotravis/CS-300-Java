
/**
 * Models a linked node
 * 
 * @author Travis Cao
 */
public class LinkedBoxNode {

  private Box box; // box that represents the data for this Linked node
  private LinkedBoxNode next; // reference to the next Linked Box Node

  // Constructors
  /**
   * Creates a new LinkedBoxNode object with a given box and without referring to any next
   * LinkedBoxNode
   * 
   * @param box The box object to initialize a new LinkedBoxNode
   */
  public LinkedBoxNode(Box box) {
    this.box = box;
    this.next = null;
  }

  /**
   * Creates a new LinkedBoxNode object and sets its instance fields box and next to the specified
   * ones
   * 
   * @param box  The box object to initialize a new LinkedBoxNode
   * @param next The next node to initialize a new LinkedBoxNode
   */
  public LinkedBoxNode(Box box, LinkedBoxNode next) {
    this.box = box;
    this.next = next;
  }

  // Getters and setters methods
  /**
   * Gets reference to the next Linked Box Node
   * 
   * @return Reference to the next Linked Box Node
   */
  public LinkedBoxNode getNext() {
    return this.next;
  }

  /**
   * Sets reference to the next Linked Box Node
   * 
   * @param next Reference to the next Linked Box Node
   */
  public void setNext(LinkedBoxNode next) {
    this.next = next;
  }

  /**
   * Gets box that represents the data for this Linked node
   * 
   * @return Box that represents the data for this Linked node
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * Sets box that represents the data for this Linked node
   * 
   * @param box Box that represents the data for this Linked node
   */
  public void setBox(Box box) {
    this.box = box;
  }

}
