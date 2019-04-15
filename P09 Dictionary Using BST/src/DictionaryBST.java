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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Models a Binary Search Tree for a Dictionary
 * 
 * @author Travis Cao
 */
public class DictionaryBST implements Dictionary {

  private DictionaryWord root;

  // This should be the only constructor of this class
  /**
   * Creates an empty DictionaryBST
   */
  public DictionaryBST() {
  }

  // Methods defined in the Dictionary interface
  /**
   * Checks whether the dictionary is empty or not
   * 
   * @return true if the dictionary is empty; false otherwise
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * Adds this word definition (word and the provided meaning) to the dictionary
   * 
   * @param word    The word to be added to the dictionary
   * @param meaning The meaning of the word to be added to the dictionary
   * @return true if the word was successfully added to this dictionary; false if the word was
   *         already in the dictionary
   * @throws IllegalArgumentException if either word or meaning is null or an empty String
   */
  public boolean addWord(String word, String meaning) {
    DictionaryWord newWordNode = new DictionaryWord(word, meaning);

    if (isEmpty()) { // add to root
      root = newWordNode;
      return true;
    } else { // add to branches
      return addWordHelper(newWordNode, root);
    }
  }

  /**
   * Looks up a specific word in the dictionary
   * 
   * @param s The word to look up in the dictionary
   * @return The meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if the word s was not found in this dictionary
   */
  public String lookup(String s) {
    if (!isEmpty()) {
      return lookupHelper(s, root);
    } else {
      System.out.println("There are no definitions in this empty dictionary.");
      return null;
    }
  }

  /**
   * Gets the number of words stored in this dictionary
   * 
   * @return Number of words stored in this dictionary
   */
  public int size() {
    if (!isEmpty()) {
      return sizeHelper(root);
    } else {
      return 0;
    }
  }

  // Public methods not defined in the Dictionary interface
  /**
   * Computes and returns the height of this dictionaryBST, as the number of nodes from root to the
   * deepest leaf DictionaryWord node.
   * 
   * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
   */
  public int height() {
    if (!isEmpty()) {
      return heightHelper(root);
    } else {
      return 0;
    }
  }

  /**
   * Returns all the words within this dictionary sorted from A to Z
   * 
   * @return an ArrayList that contains all the words within this dictionary sorted in the ascendant
   *         order
   */
  public ArrayList<String> getAllWords() {
    return getAllWordsHelper(root);
  }

  // Recursive private helper methods
  // Each public method should make call to the recursive helper method with the corresponding name
  /**
   * Recursive helper method to add newWord in the subtree rooted at node
   * 
   * @param newWordNode a new DictionaryWord to be added to this dictionaryBST
   * @param current     the current DictionaryWord that is the root of the subtree where newWord
   *                    will be inserted
   * @return true if the newWordNode is successfully added to this dictionary, false otherwise
   */
  private static boolean addWordHelper(DictionaryWord newWordNode, DictionaryWord current) {
    // stores how the newWordNode compares with current node
    int comparison = newWordNode.getWord().compareToIgnoreCase(current.getWord());

    if (comparison == 0) { // duplicated words not allowed -> return false always
      System.out.println("WARNING: failed to add duplicate word: " + newWordNode.getWord());
      return false;
    } else if (comparison < 0) { // add to left child
      if (current.getLeftChild() == null) {
        current.setLeftChild(newWordNode);
        return true;
      } else {
        return addWordHelper(newWordNode, current.getLeftChild());
      }
    } else {// add to right child
      if (current.getRightChild() == null) {
        current.setRightChild(newWordNode);
        return true;
      } else {
        return addWordHelper(newWordNode, current.getRightChild());
      }
    }
  }

  /**
   * Recursive helper method to lookup a word s in the subtree rooted at current
   * 
   * @param s       String that represents a word
   * @param current pointer to the current DictionaryWord within this dictionary
   * @return the meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if s is not found in this dictionary
   */
  private static String lookupHelper(String s, DictionaryWord current) {
    // examine which direction within the tree should we continue to look up
    int comparison = s.compareToIgnoreCase(current.getWord());

    if (comparison == 0) {
      return current.getMeaning();
    } else if (comparison < 0) { // go through the left child
      if (current.getLeftChild() != null) {
        return lookupHelper(s, current.getLeftChild());
      }
    } else { // go through the right child
      if (current.getRightChild() != null) {
        return lookupHelper(s, current.getRightChild());
      }
    }

    // if nothing has been found
    throw new NoSuchElementException("Word " + s + " is not found in this dictionary.");
  }

  /**
   * Recursive helper method that returns the number of dictionary words stored in the subtree
   * rooted at current
   * 
   * @param current current DictionaryWord within this dictionaryBST
   * @return the size of the subtree rooted at current
   */
  private static int sizeHelper(DictionaryWord current) {
    int subtreeSize = 1; // count current node

    if (current.getLeftChild() != null) {
      subtreeSize += sizeHelper(current.getLeftChild());
    }
    if (current.getRightChild() != null) {
      subtreeSize += sizeHelper(current.getRightChild());
    }

    return subtreeSize;
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current DictionaryWord within this DictionaryBST
   * @return height of the subtree rooted at current counting the number of DictionaryWord nodes
   *         from the current node to the deepest leaf in the subtree rooted at current
   */
  private static int heightHelper(DictionaryWord current) {
    int heightLeft = 1;
    int heightRight = 1;

    // get the height for the left tree
    if (current.getLeftChild() != null) {
      heightLeft += heightHelper(current.getLeftChild());
    }
    // get the height for the right tree
    if (current.getLeftChild() != null) {
      heightRight += heightHelper(current.getRightChild());
    }

    // height is the max of the two
    return Math.max(heightLeft, heightRight);
  }

  /**
   * Recursive Helper method that returns a list of all the words stored in the subtree rooted at
   * current
   * 
   * @param current pointer to the current DictionaryWord within this dictionaryBST
   * @return an ArrayList of all the words stored in the subtree rooted at current
   */
  private static ArrayList<String> getAllWordsHelper(DictionaryWord current) {
    ArrayList<String> allWords = new ArrayList<>();

    // base case: current is null
    if (current == null) {
      return allWords;
    } else { // current is not null
      // current has leftChild
      if (current.getLeftChild() != null) {
        allWords.addAll(getAllWordsHelper(current.getLeftChild()));
      }
      // add current node
      allWords.add(current.getWord());
      // current has rightChild
      if (current.getRightChild() != null) {
        allWords.addAll(getAllWordsHelper(current.getRightChild()));
      }
      return allWords;
    }
  }

}
