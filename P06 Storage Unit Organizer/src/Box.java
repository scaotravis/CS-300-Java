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

import java.util.Random;

/**
 * Models a box to be stored in a Storage Unit using our StorageUnitOrganizer application
 * 
 * @author Travis Cao
 */
public class Box implements Comparable<Box> {

  private static Random randGen = new Random(); // generator of random numbers
  private int color; // color of this box
  private int weight; // weight of this box in lbs between 1 inclusive and 31 exclusive

  // Constructors
  /**
   * Creates a new Box and initializes its instance fields color and weight to random values
   */
  public Box() {
    this.color = randGen.nextInt();
    this.weight = 1 + randGen.nextInt(30);
  }

  /**
   * Creates a new Box and initializes its instance fields color and weight to the specified values.
   * Throws IllegalArgumentException if the provided weight value is out of the range of [1..30]
   * 
   * @param color  color of the box to initialize
   * @param weight weight of the box to initialize
   */
  public Box(int color, int weight) {
    this.color = color;
    if (weight < 1 || weight > 30) {
      throw new IllegalArgumentException("WARNING: Box Weight Not Between 1 and 30!");
    }
    this.weight = weight;
  }

  // Methods
  /**
   * Returns true if the specified other object is a Box and this box and other have the same color
   * and same weight. Otherwise, it returns false.
   * 
   * @param other A different object to compare equality with
   * @return true if this box has the same weight and color as other box, false otherwise
   */
  public boolean equals(Object other) {
    boolean equality = false;

    if (other instanceof Box) {
      Box otherBox = (Box) other; // downcast to a Box object
      if (this.color == otherBox.getColor() && this.weight == otherBox.getWeight()) {
        equality = true;
      }
    }

    return equality;
  }

  /**
   * Returns a negative integer, a positive integer, or zero as this box is lighter than, heavier
   * than, or has the same weight as the specified otherBox.
   * 
   * @param o A different Box object to compare with
   * @return 0 if this box has the same weight as the specified otherBox; 1 if this box is heavier;
   *         -1 if this box is lighter
   */
  public int compareTo(Box o) {
    int comparing = 0;

    if (o.getWeight() == this.weight) {
      comparing = 0;
    }

    if (o.getWeight() > this.weight) {
      comparing = -1;
    }

    if (o.getWeight() < this.weight) {
      comparing = 1;
    }

    return comparing;
  }

  /**
   * Getter for the instance field color of this box
   * 
   * @return color of the box
   */
  public int getColor() {
    return this.color;
  }

  /**
   * Getter for the instance field weight of this box
   * 
   * @return weight of the box
   */
  public int getWeight() {
    return this.weight;
  }

}
