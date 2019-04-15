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
 * Tests the functionality of the implemented BST dictionary
 * 
 * @author Travis Cao
 */
public class DictionaryTests {

  /**
   * Main method to call testing methods
   * 
   * @param args Arguments that call testing methods
   */
  public static void main(String[] args) {
    System.out.println("=== TESTING BEGINS ===" + System.lineSeparator());
    System.out.println(
        "testAddWordAndGetAllWords(): " + testAddWordAndGetAllWords() + System.lineSeparator());
    System.out.println("testLookupExisted(): " + testLookupExisted() + System.lineSeparator());
    System.out
        .println("testLookupInexistent(): " + testLookupInexistent() + System.lineSeparator());
    System.out.println("testSize(): " + testSize() + System.lineSeparator());
    System.out.println("testHeight(): " + testHeight() + System.lineSeparator());
    System.out.println("=== TESTING CONCLUDES ===");
  }

  /**
   * Tests the addWord() and getAllWords() methods in DictionaryBST
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testAddWordAndGetAllWords() {
    boolean testPassed = false;

    DictionaryBST dictTree = new DictionaryBST();
    try {
      dictTree.addWord("C", "C meaning");
      dictTree.addWord("K", "K meaning");
      dictTree.addWord("A", "A meaning");
      dictTree.addWord("B", "B meaning");
      dictTree.addWord("D", "D meaning");
      dictTree.addWord("L", "L meaning");
      ArrayList<String> allWords = dictTree.getAllWords();
      ArrayList<String> comparison = new ArrayList<>();
      comparison.add("A");
      comparison.add("B");
      comparison.add("C");
      comparison.add("D");
      comparison.add("K");
      comparison.add("L");

      boolean eachComparison = true;
      String allWordsToString = "[ ";
      String comparisonToString = "[ ";
      for (int i = 0; i < 3; i++) {
        if (!(allWords.get(i).equals(comparison.get(i)))) {
          eachComparison = false;
        }
        allWordsToString += allWords.get(i).toString() + " ";
        comparisonToString += comparison.get(i).toString() + " ";
      }
      allWordsToString += "]";
      comparisonToString += "]";

      testPassed = eachComparison;
      if (testPassed == false) {
        System.out.println("Problem detected: after adding in words C, A, K, the .getAllWords() "
            + "method returned the following erroneous ArrayList of Strings: " + allWordsToString
            + ". Whereas the correct ArrayList should have the order: " + comparisonToString);
      }
    } catch (Exception e) {
      testPassed = false;
      System.out.println("Problem detected: the following exception has been thrown from adding new"
          + " words to or getting all words from the DictionaryBST: " + e.toString());
    }

    return testPassed;
  }

  /**
   * Tests the lookup() method in DictionaryBST class for looking up a word existed in the BST
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testLookupExisted() {
    boolean testPassed = false;
    boolean subtestA = false; // test the lookup process
    boolean subtestB = false; // test looking up words ignoring case

    DictionaryBST dictTree = new DictionaryBST();
    try {
      dictTree.addWord("C", "C meaning");
      dictTree.addWord("K", "K meaning");
      dictTree.addWord("A", "A meaning");
      dictTree.addWord("B", "B meaning");
      dictTree.addWord("D", "D meaning");
      dictTree.addWord("L", "L meaning");

      String lookupMeaningD = dictTree.lookup("D");
      String lookupMeaningL = dictTree.lookup("l");
      if (lookupMeaningD.equals("D meaning")) {
        subtestA = true;
      } else {
        System.out.println("Problem detected: when looking up a word existed in the DictionaryBST, "
            + "the meaning of the word should be returned. However, this is not the case.");
      }
      if (lookupMeaningL.equals("L meaning")) {
        subtestB = true;
      } else {
        System.out.println("Problem detected: when looking up a word existed in the DictionaryBST, "
            + "the lookup() method should ignore cases. However, this is not the case.");
      }

    } catch (Exception e) {
      System.out.println("Problem detected: the following exception has been thrown from looking up"
          + " word D that exists in the DictionaryBST: " + e.toString());
    }

    if (subtestA && subtestB) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Tests the lookup() method in DictionaryBST class for looking up a word that does not exist in
   * the BST
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testLookupInexistent() {
    boolean testPassed = false;

    DictionaryBST dictTree = new DictionaryBST();
    try {
      dictTree.addWord("C", "C meaning");
      dictTree.addWord("K", "K meaning");
      dictTree.addWord("A", "A meaning");
      dictTree.addWord("B", "B meaning");
      dictTree.addWord("D", "D meaning");
      dictTree.addWord("L", "L meaning");

      dictTree.lookup("Z");

    } catch (NoSuchElementException e) {
      testPassed = true;
    } catch (Exception e) {
      System.out.println("Problem detected: the following unexpected exception has been thrown from"
          + " looking up a word that does not exist in the DictionaryBST: " + e.toString());
    }

    if (testPassed == false) {
      System.out.println("Problem detected: when looking up a word that doesn't exist in the "
          + "DictionaryBST, a NoSuchElementException should be thrown. But, it was not the case.");
    }

    return testPassed;
  }


  /**
   * Tests the size() method within DictionaryBST class
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testSize() {
    boolean testPassed = false;

    try {
      DictionaryBST dictTree = new DictionaryBST();

      dictTree.addWord("C", "C meaning");
      dictTree.addWord("K", "K meaning");
      dictTree.addWord("A", "A meaning");
      dictTree.addWord("B", "B meaning");
      dictTree.addWord("D", "D meaning");
      dictTree.addWord("L", "L meaning");

      if (dictTree.size() == 6) {
        testPassed = true;
      } else {
        System.out.println("Problem detected: when getting the size of a binary search tree with 6 "
            + "nodes, the size() method should return 6. However, it returned " + dictTree.size());
      }
    } catch (Exception e) {
      System.out.println("Problem detected: the following exception has been thrown from getting "
          + "the size of the binary search tree: " + e.toString());
    }

    return testPassed;
  }

  /**
   * Tests the height() method within DictionaryBST class
   * 
   * @return true if the test passed; false otherwise
   */
  public static boolean testHeight() {
    boolean testPassed = false;

    try {
      DictionaryBST dictTree = new DictionaryBST();

      dictTree.addWord("C", "C meaning");
      dictTree.addWord("K", "K meaning");
      dictTree.addWord("A", "A meaning");
      dictTree.addWord("B", "B meaning");
      dictTree.addWord("D", "D meaning");
      dictTree.addWord("L", "L meaning");

      if (dictTree.height() == 3) {
        testPassed = true;
      } else {
        System.out.println("Problem detected: when getting the height of a binary search tree with "
            + "3 levels of nodes, the height() method should return 3. However, it returned "
            + dictTree.height());
      }
    } catch (Exception e) {
      System.out.println("Problem detected: the following exception has been thrown from getting "
          + "the height of the binary search tree: " + e.toString());
    }

    return testPassed;
  }

}
