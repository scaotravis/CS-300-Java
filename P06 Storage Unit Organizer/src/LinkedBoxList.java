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
 * Models a dynamic list to store box objects sorted in a descendant order with respect to their
 * weights
 * 
 * @author Travis Cao
 */
public class LinkedBoxList {

  private LinkedBoxNode head; // head of this LinkedBoxList (refers to the element stored at index 0
                              // within this list)
  private int size; // number of boxes already stored in this list
  private int capacity; // capacity of this LinkedBoxList maximum number of box elements that this
                        // LinkedBoxList can store

  // Constructors
  /**
   * Creates an empty LinkedBoxList with a given initial capacity
   * 
   * @param capacity Initial capacity of the LinkedBoxList
   */
  public LinkedBoxList(int capacity) {
    this.head = null;
    this.size = 0;
    this.capacity = capacity;
  }

  // Methods
  /**
   * Returns the size of this list
   * 
   * @return Size of the list
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns the capacity of this list
   * 
   * @return Capacity of the list
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Expands the capacity of this LinkedBoxList with the specified number a of additional elements
   * 
   * @param a Number of additional elements to expand the capacity of the LinkedBoxList
   */
  public void expandCapacity(int a) {
    this.capacity = this.capacity + a;
  }

  /**
   * Checks whether this LinkedBoxList is empty
   * 
   * @return true if this LinkedBoxList is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Checks whether this LinkedBoxList is full
   * 
   * @return true if this list is full, false otherwise
   */
  public boolean isFull() {
    return this.size == this.capacity;
  }

  /**
   * Adds a new box into this sorted list
   * 
   * @param newBox The new Box object to add into the sorted list
   * @throws IllegalArgumentException If newBox is null
   * @throws IllegalStateException    If this list is full
   */
  public void add(Box newBox) throws IllegalArgumentException, IllegalStateException {
    if (newBox == null) {
      throw new IllegalArgumentException("WARNING: Create a Box First!");
    }

    if (this.isFull()) {
      throw new IllegalStateException("WARNING: Storage List Full!");
    }

    boolean boxAdded = false; // keeps track of whether the newBox has been added to the list

    LinkedBoxNode searchNode = this.head;
    LinkedBoxNode nodeToInsert = new LinkedBoxNode(newBox);

    // 1. If LinkedBoxList is empty: insert up front
    if (this.isEmpty()) {
      this.head = nodeToInsert;
      boxAdded = true;
    } else {
      // 2. If needed to be inserted before head
      if (newBox.compareTo(searchNode.getBox()) > 0) {
        nodeToInsert.setNext(searchNode);
        this.head = nodeToInsert;
        boxAdded = true;
      } else {
        // 3. Insert between two nodes
        while (searchNode.getNext() != null) {
          if (searchNode.getBox().compareTo(newBox) >= 0 // current node is heavier than newBox...
              && newBox.compareTo(searchNode.getNext().getBox()) > 0) { // ...while next node is
                                                                        // lighter than newBox
            nodeToInsert.setNext(searchNode.getNext());
            searchNode.setNext(nodeToInsert);
            boxAdded = true;
            break;
          }
          searchNode = searchNode.getNext();
        }
      }
    }
    // 4. If newBox still hasn't been added, then newBox has to be lighter than the tail
    if (boxAdded == false) {
      searchNode.setNext(nodeToInsert);
      boxAdded = true;
    }

    if (boxAdded == true) { // increase size by 1 after insertion
      this.size++;
    }
  }

  /**
   * Checks if this list contains a box that matches with (equals) a specific box object
   * 
   * @param findBox The Box object to search for
   * @return true if this list contains findBox, false otherwise
   */
  public boolean contains(Box findBox) {
    boolean findBoxContained = false;

    LinkedBoxNode searchNode = this.head;
    while (searchNode != null) {
      if (searchNode.getBox().equals(findBox)) {
        findBoxContained = true;
        break;
      }
      searchNode = searchNode.getNext();
    }

    return findBoxContained;
  }

  /**
   * Returns a box stored in this list given its index
   * 
   * @param index Index of which to retrieve the Box object
   * @return A Box object stored at the given index
   * @throws IndexOutOfBoundsException If index is out of the range [0, .size()-1]
   */
  public Box get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > this.size - 1) {
      throw new IndexOutOfBoundsException("WARNING: Index Invalid!");
    }

    LinkedBoxNode searchNode = this.head;
    for (int i = 0; i < index; i++) {
      searchNode = searchNode.getNext();
    }

    return searchNode.getBox();
  }

  /**
   * Removes and returns the box stored at index from this LinkedBoxList
   * 
   * @param index Index of which to remove the Box object
   * @return A box object removed from the given index
   * @throws IndexOutOfBoundsException If index is out of the range [0, .size()-1]
   */
  public Box remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > this.size - 1) {
      throw new IndexOutOfBoundsException("WARNING: Index Invalid!");
    }

    LinkedBoxNode searchNode = this.head;
    Box boxRemoved;

    if (index == 0) {
      boxRemoved = searchNode.getBox();
      this.head = searchNode.getNext();
    } else {
      for (int i = 0; i < index - 1; i++) {
        searchNode = searchNode.getNext();
      }
      boxRemoved = searchNode.getNext().getBox();
      searchNode.setNext(searchNode.getNext().getNext());
    }
    this.size--;

    return boxRemoved;
  }

  /**
   * Removes all the boxes from this list
   */
  public void clear() {
    this.head = null;
    this.size = 0;
  }

  /**
   * Returns a String representation of the state and content of this LinkedBoxList
   * 
   * @return A String representation of LinkedBoxList
   */
  public String toString() {
    StringBuilder result = new StringBuilder(); // creates a StringBuilder object
    String newLine = System.getProperty("line.separator");
    result.append("------------------------------------------------" + newLine);
    if (!isEmpty()) {
      LinkedBoxNode runner = head;
      int index = 0;
      // traverse the list and add a String representation for each box
      while (runner != null) {
        result.insert(0,
            "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs" + newLine);
        runner = runner.getNext();
        index++;
      }
      result.insert(0, "Box List Content:" + newLine);
    }
    result.insert(0, "List size: " + size + " box(es)." + newLine);
    result.insert(0, "Box List is empty: " + isEmpty() + newLine);
    result.insert(0, "------------------------------------------------" + newLine);
    return result.toString();
  }

}
