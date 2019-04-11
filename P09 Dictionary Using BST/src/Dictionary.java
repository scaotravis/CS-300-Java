
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
   * adds this word definition (word and the provided meaning) to the dictionary
   * 
   * @param word    The word to be added to the dictionary
   * @param meaning The meaning of the word to be added to the dictionary
   * @return true if the word was successfully added to this dictionary; false if the word was
   *         already in the dictionary
   * @throws IllegalArgumentException if either word or meaning is null or an empty String
   */
  public boolean addWord(String word, String meaning);

  /**
   * @param s The word to look up in the dictionary
   * @return The meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if the word s was not found in this dictionary
   */
  public String lookup(String s);

  /**
   * @return Number of words stored in this dictionary
   */
  public int size();

}
