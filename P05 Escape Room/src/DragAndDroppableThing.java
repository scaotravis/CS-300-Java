//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P05 Escape Room
// Files: "EscapeRoom.java", "Action.java", "Thing.java", "VisibleThing.java",
// "ClickableThing.java", "DraggableThing.java", "DragAndDroppableThing.java"
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
// Online Sources: Googled on how to check rectangles for overlap
// https://www.geeksforgeeks.org/find-two-rectangles-overlap/
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

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
