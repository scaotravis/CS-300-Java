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

import java.io.File;
import processing.core.PImage;

/**
 * Represents a visible object with a graphical representation in our Escape Room game
 * 
 * @author Travis Cao
 */
public class VisibleThing extends Thing {

  private PImage image; // the graphical representation of this thing
  private int x; // the horizontal position (in pixels of this thing's left side)
  private int y; // the vertical position (in pixels of this thing's top side)

  // Constructor
  public VisibleThing(String name, int x, int y) {
    super(name);
    this.image = getProcessing().loadImage("images" + File.separator + name + ".png"); 
    this.x = x; 
    this.y = y; 
  }

  // Methods
  /* (non-Javadoc)
   * @see Thing#update()
   * 
   * Draws image at its position before returning null
   */
  @Override
  public Action update() {
    getProcessing().image(image, x, y);
    return null; 
  }

  /**
   * Changes the image position by adding dx to x and dy to y
   * 
   * @param dx Increment to add on x image position
   * @param dy Increment to add on y image position
   */
  public void move(int dx, int dy) {
    this.x += dx; 
    this.y += dy; 
  }

  /**
   * Checks if a point (x, y) is over image in object of class VisibleThing
   * 
   * @param x x-coordinate of a point
   * @param y y-coordinate of a point
   * @return true only when point (x, y) is over image
   */
  public boolean isOver(int x, int y) {
    int width = this.image.width; 
    int height = this.image.height; 
    
    boolean pointIsOver = true; 
    
    // point is outside if it is to the left or right of the image
    if (x < this.x || this.x + width < x) {
      pointIsOver = false; 
    }
    
    // point is outside if it is higher or lower than the image
    if (y < this.y || this.y + height < y) {
      pointIsOver = false; 
    }
    
    return pointIsOver; 
  }

  /**
   * Checks if another object of class VisibleThing overlaps with the current VisibleThing object
   * 
   * @param other Another object of class VisibleThing
   * @return true only when another VisibleThing image overlaps with the current one
   */
  public boolean isOver(VisibleThing other) {
    int thisWidth = this.image.width; 
    int thisHeight = this.image.height; 
    int otherWidth = other.image.width; 
    int otherHeight = other.image.height; 
    
    boolean otherIsOver = true; 
    
    // if one image is on the left side of the other, then it is not over
    if (this.x + thisWidth < other.x || other.x + otherWidth < this.x) {
      otherIsOver = false; 
    }
    
    // if one image is above the other, then it is not over
    if (this.y + thisHeight < other.y || other.y + otherHeight < this.y) {
      otherIsOver = false; 
    }
    
    return otherIsOver; 
  }


}
