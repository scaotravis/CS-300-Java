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
 * Retrieves a fixed number of elements from any InfiniteIterator
 * 
 * @author Travis Cao
 */
public class FiniteIterator<T> implements Iterator<T> {

  private InfiniteIterator<T> infiniteIterator;
  private int length; // number of iterations permitted
  private int numberOfCalls = 0; // number of iterations already called

  // Constructor
  public FiniteIterator(InfiniteIterator<T> infiniteIterator, int length) {
    this.infiniteIterator = infiniteIterator;
    this.length = length;
  }

  // Methods
  /**
   * Checks if there exists an object in the next sequence position
   * 
   * @return true if there is an object in the next sequence position, false otherwise
   */
  public boolean hasNext() {
    return this.numberOfCalls < this.length; 
  }

  /**
   * Retrieves the next object in sequence
   * 
   * @return The next object in sequence if it has yet to reach the finite end; null otherwise
   */
  public T next() {
    if (this.hasNext()) {
      numberOfCalls++;
      return this.infiniteIterator.next();
    } else {
      return null;
    }
  }

}
