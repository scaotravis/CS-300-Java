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

import processing.core.PApplet;

/**
 * Represents objects that can be interacted through clicking
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
