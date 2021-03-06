
public class GradeScopeCheck {

  public static void main(String[] args) {
    DictionaryBST dict = new DictionaryBST();
    dict.addWord("A", "A meaning");
    dict.addWord("B", "B meaning");
    dict.addWord("C", "C meaning");
    dict.addWord("D", "D meaning");
    System.out.println(dict.height()); // returned 4 after fixing the bug where instead of getting
                                       // right child, left child was retrieved twice
    System.out.println(dict.lookup("C")); 
    System.out.println(dict.lookup("no such word")); 
  }

}
