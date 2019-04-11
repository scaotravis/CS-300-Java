
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
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Looks up a specific word in the dictionary
   * 
   * @param s The word to look up in the dictionary
   * @return The meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if the word s was not found in this dictionary
   */
  public String lookup(String s) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Gets the number of words stored in this dictionary
   * 
   * @return Number of words stored in this dictionary
   */
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  // Public methods not defined in the Dictionary interface
  /**
   * Computes and returns the height of this dictionaryBST, as the number of nodes from root to the
   * deepest leaf DictionaryWord node.
   * 
   * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
   */
  public int height() {
  }

  /**
   * Returns all the words within this dictionary sorted from A to Z
   * 
   * @return an ArrayList that contains all the words within this dictionary sorted in the ascendant
   *         order
   */
  public ArrayList<String> getAllWords() {
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
  }

  /**
   * Recursive helper method that returns the number of dictionary words stored in the subtree
   * rooted at current
   * 
   * @param current current DictionaryWord within this dictionaryBST
   * @return the size of the subtree rooted at current
   */
  private static int sizeHelper(DictionaryWord current) {
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current DictionaryWord within this DictionaryBST
   * @return height of the subtree rooted at current counting the number of DictionaryWord nodes
   *         from the current node to the deepest leaf in the subtree rooted at current
   */
  private static int heightHelper(DictionaryWord current) {
  }

  /**
   * Recursive Helper method that returns a list of all the words stored in the subtree rooted at
   * current
   * 
   * @param current pointer to the current DictionaryWord within this dictionaryBST
   * @return an ArrayList of all the words stored in the subtree rooted at current
   */
  private static ArrayList<String> getAllWordsHelper(DictionaryWord current) {
  }

}
