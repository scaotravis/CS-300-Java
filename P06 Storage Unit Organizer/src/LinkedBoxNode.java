//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P06 Storage Unit Organizer
// Files: "Box.java", "LinkedBoxNode.java", "LinkedBoxList.java", (for testing)
// "StorageUnitTests.java"
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
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

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
