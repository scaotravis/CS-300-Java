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
 * Generates an Iterable object making use of a FiniteIterator or an InfiniteIterator
 * 
 * @author Travis Cao
 */
public class Generator<T> implements Iterable<T> {

  private Iterator<T> iterator;

  // Constructors
  /**
   * Initializes a Generator object paired with an InfiniteIterator
   * 
   * @param firstValue           The first value of the sequence to iterate
   * @param generateNextFromLast Function that defines how to iterate from the first value
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    this.iterator = new InfiniteIterator<T>(firstValue, generateNextFromLast);
  }

  /**
   * Initializes a Generator object paired with a FiniteIterator
   * 
   * @param firstValue           The first value of the sequence to iterate
   * @param generateNextFromLast Function that defines how to iterate from the first value
   * @param length               Length of the finite sequence
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    this.iterator =
        new FiniteIterator<T>(new InfiniteIterator<T>(firstValue, generateNextFromLast), length);
  }

  // Methods
  /**
   * Returns the appropriate Iterator object
   * 
   * @return A FiniteIterator object if the Generator was created with a pre-specified length of the
   *         sequence, otherwise an InfiniteIterator object
   */
  public Iterator<T> iterator() {
    if (this.iterator instanceof FiniteIterator) {
      return (FiniteIterator<T>) this.iterator;
    } else {
      return (InfiniteIterator<T>) this.iterator;
    }
  }

}
