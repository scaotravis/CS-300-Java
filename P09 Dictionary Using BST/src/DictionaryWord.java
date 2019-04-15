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
 * Models a binary node of a Binary Search Tree
 * 
 * @author Travis Cao
 */
public class DictionaryWord {

  private final String word; // word that represents the search key for this dictionary word
  private final String meaning; // The meaning of the word that this dictionary node defines
  private DictionaryWord leftChild; // The leftChild of the the current WebPageNode
  private DictionaryWord rightChild; // The rightChild of the the current WebPageNode

  // The following should be the only constructor for this class
  /**
   * Creates a new dictionary word with the provided word and its meaning pair
   * 
   * @param word    The word to be added to the BST node
   * @param meaning The meaning of the word to be added to the BST node
   * @throws IllegalArgumentException when the word or meaning are either references to an empty
   *                                  string or null references. The thrown exception should include
   *                                  a significant error message describing which of these problems
   *                                  was encountered.
   */
  public DictionaryWord(String word, String meaning) {
    this.word = word;
    this.meaning = meaning;
    this.leftChild = null;
    this.rightChild = null;
  }

  // Methods
  /**
   * Getter for the left child of this dictionary word
   * 
   * @return The left child of this dictionary word
   */
  public DictionaryWord getLeftChild() {
    return this.leftChild;
  }

  /**
   * Setter for the left child of this dictionary word
   * 
   * @param leftChild The DictionaryWord node to be set as the left child of this dictionary word
   */
  public void setLeftChild(DictionaryWord leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * Getter for the right child of this dictionary word
   * 
   * @return The right child of this dictionary word
   */
  public DictionaryWord getRightChild() {
    return this.rightChild;
  }

  /**
   * Setter for the right child of this dictionary word
   * 
   * @param rightChild The DictionaryWord node to be set as the right child of this dictionary word
   */
  public void setRightChild(DictionaryWord rightChild) {
    this.rightChild = rightChild;
  }

  //
  /**
   * Getter for the word of this dictionary word
   * 
   * @return The word of this dictionary word
   */
  public String getWord() {
    return this.word;
  }

  /**
   * Getter for the meaning of the word of this dictionary word
   * 
   * @return The meaning of the word of this dictionary word
   */
  public String getMeaning() {
    return this.meaning;
  }

  /**
   * Returns a String representation of this DictionaryWord. This String should be formatted as
   * follows. "<word>: <meaning>" For instance, for a dictionaryWord that has the String "Awesome"
   * for the instance field word and the String "adj. Inspiring awe; dreaded." as value for meaning
   * field, the String representing that dictionaryWord is "Awesome: adj. Inspiring awe; dreaded."
   * 
   * @return A String representation of this DictionaryWord
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return this.word + ": " + this.meaning;
  }

}
