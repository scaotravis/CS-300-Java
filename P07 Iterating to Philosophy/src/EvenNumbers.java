//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P07 Iterating to Philosophy
// Files: "TestDriver.java", "EvenNumbers.java", "InfiniteIterator.java",
// "FiniteIterator.java", "Generator.java", "NextWikiLink.java"
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

import java.util.Iterator;

/**
 * Generates an infinite sequence of even integers starting at a specified starting point
 * 
 * @author Travis Cao
 */
public class EvenNumbers implements Iterator<Integer> {

  private Integer number; // originally records the first number of the sequence; replaced with
                          // iterated values as the sequence grows
  private boolean firstCall = true; // keep track of whether this is the first call

  // Constructor
  /**
   * Initializes an object of class EvenNumbers
   * 
   * @param number An even integer as the starting point to iterate an infinite sequence of even
   *               integers
   */
  public EvenNumbers(Integer number) {
    this.number = number;
  }

  // Methods
  /**
   * Checks if there exists an even integer that is larger than the previously returned one. Since
   * numbers are infinite, this method always returns true
   * 
   * @return true always
   */
  public boolean hasNext() {
    return true;
  }

  /**
   * Retrieves the smallest even number that is larger than the previously returned one
   * 
   * @return The smallest even number that is larger than the previously returned one
   */
  public Integer next() {
    if (firstCall) {
      firstCall = false;
      return this.number;
    } else {
      this.number = this.number + 2;
      return this.number;
    }
  }

}
