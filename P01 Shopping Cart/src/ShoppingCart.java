//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P01 - Simple Shopping Cart Using Java Arrays
// Files: NONE (test file is ShoppingCartTests.java)
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
// Online Sources:
// 1. zyBooks section 2.10 (idea of getAction method)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

public class ShoppingCart {

  // Define final parameters
  private static final int CART_CAPACITY = 20; // shopping cart max capacity
  private static final double TAX_RATE = 0.05; // sales tax

  // a perfect-size two-dimensional array that stores the available items in the market
  // MARKET_ITEMS[i][0] refers to a String that represents the description of the item
  // identified by index i
  // MARKET_ITEMS[i][1] refers to a String that represents the unit price of the item
  // identified by index i in dollars.
  public static final String[][] MARKET_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

  /**
   * Adds the item with the given its identifier (index) at the end of the cart
   * 
   * @param index identifier of the item to add to the cart
   * @param cart  the shopping cart that items will be added to
   * @param count number of items present within the cart
   * @return the number of items present in the cart after the item with identifier index is added
   */
  public static int add(int index, String[] cart, int count) {
    if (count + 1 <= cart.length) {
      if (count >= 0) {
        if (index >= 0 && index <= MARKET_ITEMS.length) {
          cart[count] = MARKET_ITEMS[index][0];
          count++;
        } else {
          System.out.println("WARNING: Index of item to add should be a non-negative integer.");
        }
      } else {
        System.out.println("WARNING: Number of item in cart should be a non-negative interger.");
      }
    } else {
      System.out.println("WARNING: The cart is full. You cannot add any new item.");
    }

    return count;
  }

  /**
   * Returns how many occurrences of the item with index itemIndex are present in the shopping cart
   * 
   * @param itemIndex identifier of the item to count its occurrences in the cart
   * @param cart      the shopping cart that items will be added to
   * @param count     number of items present within the cart
   * @return the number of occurrences of item in the cart
   */
  public static int occurrencesOf(int itemIndex, String[] cart, int count) {
    int occurrence = 0;

    if (itemIndex >= 0 && itemIndex <= MARKET_ITEMS.length) {
      if (count >= 0 && count + 1 <= cart.length) {
        for (int i = 0; i < count; i++) {
          if (cart[i] == MARKET_ITEMS[itemIndex][0]) {
            occurrence++;
          }
        }
      } else {
        System.out.println("WARNING: Number of item in cart is invalid.");
      }
    } else {
      System.out.println("WARNING: Index of item to check occurence is invalid.");
    }

    return occurrence;
  }

  // Before implement remove() method, develop private method indexOf() to return the first index
  // of a specific item within the shopping cart
  /**
   * Returns the first index of an item within the shopping cart
   * 
   * @param item  name of the item to find its index within the shopping cart
   * @param cart  the shopping cart that items will be removed from
   * @param count number of items present within the cart
   * @return first index of the item within the shopping cart, or -1 if the item does not exist in
   *         the cart
   */
  private static int indexOf(String item, String[] cart, int count) {
    int itemIndexInCart = -1;

    for (int i = 0; i < count; i++) {
      if (cart[i] == item) {
        itemIndexInCart = i;
        break;
      }
    }

    return itemIndexInCart;
  }

  /**
   * Removes the first (and just one) occurrence of itemToRemove if found and returns the number of
   * items in the cart after remove operation is completed either successfully or not
   * 
   * @param itemToRemove name of the item to remove from cart
   * @param cart         the shopping cart that items will be removed from
   * @param count        number of items present within the cart
   * @return total number of items within the cart after attempting removal
   */
  public static int remove(String itemToRemove, String[] cart, int count) {
    int itemToRemoveIndex = indexOf(itemToRemove, cart, count);

    if (itemToRemoveIndex != -1) {
      cart[itemToRemoveIndex] = cart[count - 1]; // index of the last item is count - 1
      cart[count - 1] = null;
      count--;
    } else {
      System.out.println("WARNING: " + itemToRemove + " not found in the shopping cart.");
    }

    return count;
  }

  // Before implement getSubTotalPrice() method, we develop a private method getPriceOfItem() that
  // returns the price of a specific item
  /**
   * Returns the price of one specific item
   * 
   * @param item item to return the price of
   * @return the price of item, recovered from MARKET_ITEMS
   */
  private static double getPriceOfItem(String item) {
    double itemPrice = 0;

    for (int i = 0; i < MARKET_ITEMS.length; i++) {
      if (item == MARKET_ITEMS[i][0]) {
        itemPrice = Double.valueOf(MARKET_ITEMS[i][1].substring(1));
        // substring starts with index 1, since the actual price value starts after the $ sign
        break;
      }
    }

    return itemPrice;
  }

  /**
   * Returns the total value (cost) of the cart without tax in $ (double)
   * 
   * @param cart  the shopping cart to check out
   * @param count number of items present within the cart
   * @return the total dollar value of items in cart, not including tax
   */
  public static double getSubTotalPrice(String[] cart, int count) {
    double cartSubTotalWithoutTax = 0;

    for (int i = 0; i < count; i++) {
      cartSubTotalWithoutTax = cartSubTotalWithoutTax + getPriceOfItem(cart[i]);
    }

    return cartSubTotalWithoutTax;
  }

  /**
   * Prints the Market Catalog (item identifiers, description, and unit prices)
   */
  public static void printMarketCatalog() {
    final String DIVIDER = "+++++++++++++++++++++++++++++++++++++++++++++";

    System.out.println(DIVIDER);
    System.out.println("Item id" + "\t\t" + "Description" + "    \t " + "Price");
    System.out.println(DIVIDER);
    for (int i = 0; i < MARKET_ITEMS.length; i++) {
      System.out.println(i + "\t\t" + MARKET_ITEMS[i][0] + "    \t " + MARKET_ITEMS[i][1]);
    }
    System.out.println(DIVIDER);
  }

  /**
   * Displays the cart content (items separated by commas)
   * 
   * @param cart  the shopping cart to display current items from
   * @param count number of items present within the cart
   */
  public static void displayCartContent(String[] cart, int count) {
    for (int i = 0; i < count; i++) {
      System.out.print(cart[i] + ", ");
    }
  }

  // Before finally implementing the driver of this program, we need one more private method to
  // scan and prepare for interpreting user response
  /**
   * Scans input from the console, and returns the input with all characters upper-cased
   * 
   * @param prompt on-screen prompt directing user to key in their response
   * @param scnr   scan user input into the console
   * @return what users have typed into the console, with all characters upper-cased
   */
  private static String getResponse(String prompt, Scanner scnr) {
    String answer;

    System.out.println();
    System.out.print(prompt);
    answer = scnr.nextLine().toUpperCase();

    return answer;
  }

  /**
   * Main shopping cart program driver
   * 
   * @param args arguments needed to run the program
   */
  public static void main(String[] args) {
    final String USER_PROMPT = "COMMAND MENU:" + System.lineSeparator()
        + " [P] print the market catalog" + System.lineSeparator()
        + " [A <index>] add one occurrence of an item to the cart given its identifier"
        + System.lineSeparator() + " [C] checkout" + System.lineSeparator()
        + " [D] display the cart content" + System.lineSeparator()
        + " [O <index>] number of occurrences of an item in the cart given its identifier"
        + System.lineSeparator()
        + " [R <index>] remove one occurrence of an item from the cart given its identifier"
        + System.lineSeparator() + " [Q]uit the application" + System.lineSeparator()
        + System.lineSeparator() + "ENTER COMMAND: ";
    final String WELCOME = "=============   Welcome to the Shopping Cart App   =============";
    final String THANK_YOU = "=============  Thank you for using this App!!!!!  =============";
    final char EXIT_CODE = 'Q';

    String[] cart = new String[CART_CAPACITY];
    int count = 0;
    Scanner scnr = new Scanner(System.in); // Call Scanner
    String userResponse; // full response scanned from user input into the console
    char userAction; // first character indicating the action user want to take

    // Program driver follows
    System.out.println(WELCOME);
    System.out.println();

    userResponse = getResponse(USER_PROMPT, scnr);
    userAction = userResponse.charAt(0); // take the first character from scanned user response

    while (userAction != EXIT_CODE) {
      // [P] print the market catalog
      if (userAction == 'P') {
        printMarketCatalog();
      }

      // [A <index>] add one occurrence of an item to the cart given its identifier
      if (userAction == 'A') {
        String toAddIndexString = userResponse.substring(userResponse.lastIndexOf('A') + 1);
        // read int after char 'A', which returns the index of item to add to shopping cart
        int toAddIndex = Integer.parseInt(toAddIndexString.trim());
        // use trim() to strip white space
        count = add(toAddIndex, cart, count);
      }

      // [C] checkout
      if (userAction == 'C') {
        double cartSubTotalWithoutTax = getSubTotalPrice(cart, count);
        double cartSubTotalTax = cartSubTotalWithoutTax * TAX_RATE;
        double cartSubTotalWithTax = cartSubTotalWithoutTax + cartSubTotalTax;
        System.out.println(
            "#items: " + count + " Subtotal: $" + String.format("%.2f", cartSubTotalWithoutTax)
                + " Tax: $" + String.format("%.2f", cartSubTotalTax) + " TOTAL: $"
                + String.format("%.2f", cartSubTotalWithTax));
      }

      // [D] display the cart content
      if (userAction == 'D') {
        System.out.print("Cart Content: ");
        displayCartContent(cart, count);
        System.out.println();
      }

      // [O <index>] number of occurrences of an item in the cart given its identifier
      if (userAction == 'O') {
        String occurrenceIndexString = userResponse.substring(userResponse.lastIndexOf('O') + 1);
        int occurrenceIndex = Integer.parseInt(occurrenceIndexString.trim());
        System.out.println("The number of occurrences of " + MARKET_ITEMS[occurrenceIndex][0]
            + " (id #" + occurrenceIndex + ") is: " + occurrencesOf(occurrenceIndex, cart, count));
      }

      // [R <index>] remove one occurrence of an item from the cart given its identifier
      if (userAction == 'R') {
        String toRemoveIndexString = userResponse.substring(userResponse.lastIndexOf('R') + 1);
        int toRemoveIndex = Integer.parseInt(toRemoveIndexString.trim());
        count = remove(MARKET_ITEMS[toRemoveIndex][0], cart, count);
      }

      userResponse = getResponse(USER_PROMPT, scnr);
      userAction = userResponse.charAt(0);
    }

    System.out.println(THANK_YOU);
  }

}
