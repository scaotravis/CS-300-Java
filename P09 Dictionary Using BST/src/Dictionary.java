//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 Dictionary Using BST
// Files: "Dictionary.java", "DictionaryWord.java", "DictionaryBST.java",
// "DictionaryDriver.java", "DictionaryTests.java"
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
 * Models and abstract data for a dictionary
 * 
 * @author Travis Cao
 */
public interface Dictionary {

  /**
   * Checks whether the dictionary is empty or not
   * 
   * @return true if the dictionary is empty; false otherwise
   */
  public boolean isEmpty();

  /**
   * Adds this word definition (word and the provided meaning) to the dictionary
   * 
   * @param word    The word to be added to the dictionary
   * @param meaning The meaning of the word to be added to the dictionary
   * @return true if the word was successfully added to this dictionary; false if the word was
   *         already in the dictionary
   * @throws IllegalArgumentException if either word or meaning is null or an empty String
   */
  public boolean addWord(String word, String meaning);

  /**
   * Looks up a specific word in the dictionary
   * 
   * @param s The word to look up in the dictionary
   * @return The meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if the word s was not found in this dictionary
   */
  public String lookup(String s);

  /**
   * Gets the number of words stored in this dictionary
   * 
   * @return Number of words stored in this dictionary
   */
  public int size();

}
