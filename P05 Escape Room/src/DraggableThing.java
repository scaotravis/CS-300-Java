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
