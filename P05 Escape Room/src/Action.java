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

import java.util.ArrayList;

/**
 * Represents the response to an object being clicked or dragged onto another
 * 
 * @author Travis Cao
 */
public class Action {

  private String message; // message printed by this action (or null to do nothing)
  private Thing thing;

  // Constructor
  /**
   * Initializes a new Action object with a message
   * 
   * @param message A message to initialize
   */
  public Action(String message) { // initialize this new action
    this.message = message;
  }

  /**
   * Initializes a new Action object with a Thing object
   * 
   * @param thing A Thing object to activate
   */
  public Action(Thing thing) {
    this.thing = thing; 
  }

  /**
   * Initializes a new Action object with a message and a Thing object
   * 
   * @param message A message to initialize
   * @param thing   A Thing object to activate
   */
  public Action(String message, Thing thing) {
    this.message = message; 
    this.thing = thing; 
  }

  // Methods
  /**
   * When message is not null, message is printed to System.out
   */
  public void act(ArrayList<Thing> thingList) {
    if (this.thing != null) {
      this.thing.activate();
      thingList.add(this.thing); 
      this.thing = null; 
    }
    
    if (this.message != null) {
      System.out.println(this.message);
    }
  }

}
