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
 * Organizes the capabilities that are common to all interactive things in Escape Room game
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
   * This method returns null. Subclass types will override this update() method to do more
   * interesting things
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
    Thing.processing = processing;
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
