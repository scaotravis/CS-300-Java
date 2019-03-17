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
import java.util.function.Function;

/**
 * Generates an infinite sequence of objects with a specified generating function and starting point
 * 
 * @author Travis Cao
 */
public class InfiniteIterator<T> implements Iterator<T> {

  private T t; // originally records the first object of the sequence; replaced with iterated values
               // as the sequence grows
  private Function<T, T> generatingFunction; // function that defines how the object iterates
  private boolean firstCall = true; // keep track of whether this is the first call

  // Constructor
  /**
   * Initializes an object of class InfiniteInterator
   * 
   * @param number             A starting point to iterate an infinite sequence
   * @param generatingFunction the function used to generates an infinite sequence
   */
  public InfiniteIterator(T t, Function<T, T> generatingFunction) {
    this.t = t;
    this.generatingFunction = generatingFunction;
  }

  // Methods
  /**
   * Checks if there exists an object in the next sequence position. Since numbers are infinite,
   * this method always returns true
   * 
   * @return true always
   */
  public boolean hasNext() {
    return true;
  }

  /**
   * Retrieves the next object in sequence based on the pre-specified generating function
   * 
   * @return The next object in sequence
   */
  public T next() {
    if (firstCall) {
      firstCall = false;
      return this.t;
    } else {
      this.t = this.generatingFunction.apply(this.t);
      return this.t;
    }
  }

}
