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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A driver application to make use of your Dictionary implemented using BST
 * 
 * @author Travis Cao
 */
public class DictionaryDriver {

  /**
   * Main method to call the application driver
   * 
   * @param args Arguments that call testing methods
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // create a scanner object to read user inputs
    processUserInput(scanner);
    scanner.close();// close this scanner
  }

  /**
   * Helper method to print out options in a menu for the user to select from
   */
  private static void printMenu() {
    String userCommands = "\n=========================== Dictionary ============================\n"
        + "Enter one of the following options:\n"
        + "[A <word> <meaning>] to add a new word and its definition in the dictionary\n"
        + "[L <word>] to search a word in the dictionary and display its definition\n"
        + "[G] to print all the words in the dictionary in sorted order\n"
        + "[S] to get the count of all words in the dictionary\n"
        + "[H] to get the height of this dictionary implemented as a binary search tree\n"
        + "[Q] to quit the program\n"
        + "======================================================================\n";
    System.out.println(userCommands);
  }

  /**
   * Checks if an array of command arguments has the correct length with respect to a provided count
   * 
   * @param commands      An array of Strings that stores the arguments extracted from a user
   *                      command line
   * @param validArgCount Valid count of arguments of the provided commands
   * @param commandLine   Name of the command line to help identify syntax error
   * @throws ParseException If commands length is different from validArgCount with default
   *                        errorOffset equals to zero
   */
  public static void checkCommandArgumentsCount(String[] commands, int validArgCount,
      String commandLine) throws ParseException {
    if (commands.length != validArgCount)
      throw new ParseException("WARNING: Syntax Error for " + commandLine + " command line.", 0);
  }

  /**
   * Checks if an array of command arguments has the correct length with respect to a provided count
   * 
   * @param commands      An array of Strings that stores the arguments extracted from a user
   *                      command line
   * @param validArgCount Valid count of arguments of the provided commands
   * @param commandLine   Name of the command line to help identify syntax error
   * @throws ParseException If commands length is different from validArgCount with default
   *                        errorOffset equals to zero
   */
  public static void checkCommandArgumentsCountLessThan(String[] commands, int validArgCount,
      String commandLine) throws ParseException {
    if (!(commands.length >= validArgCount))
      throw new ParseException("WARNING: Syntax Error for " + commandLine + " command line.", 0);
  }

  /**
   * Reads and processes commands for keeping the dictionary management system
   * 
   * @param scanner An object of class Scanner to read user inputs
   */
  private static void processUserInput(Scanner scanner) {
    // creates a DictionaryBST object to make changes to
    DictionaryBST dictbst = new DictionaryBST();

    // provides options for the user to choose from
    String promptCommandLine = "Please enter your command: ";
    printMenu(); // display the dictionary management system main menu
    System.out.print(promptCommandLine);

    // reads user input
    String command = scanner.nextLine();
    String[] commands = command.trim().split(" "); // split user command
    while (!(commands[0].toUpperCase().equals("Q") && commands.length == 1)) { // "q" to quit
      try {
        switch (commands[0].toUpperCase()) {
          case "A": // A <word> <meaning> add word
            checkCommandArgumentsCountLessThan(commands, 3, "[A <word> <meaning>]");
            String meaning = ""; // stitch back word meaning
            for (int i = 2; i < commands.length - 2; i++) {
              meaning += commands[i] + " ";
            }
            meaning += commands[commands.length - 1]; // no need for an extra space after the last
                                                      // word
            dictbst.addWord(commands[1], meaning);
            break;
          case "L": // L <word> look up word
            checkCommandArgumentsCount(commands, 2, "[L <word>]");
            try {
              System.out.println(commands[1] + ": " + dictbst.lookup(commands[1]));
            } catch (NoSuchElementException e) {
              System.out.println("No definition found for the word " + commands[1]);
            }
            break;
          case "G": // G get all words
            checkCommandArgumentsCount(commands, 1, "[G]");
            ArrayList<String> allwords = dictbst.getAllWords();
            if (dictbst.isEmpty()) {
              System.out.println("Dictionary is empty.");
            } else {
              if (allwords.size() == 1) {
                System.out.println(allwords.get(0));
              } else {
                String allwordsOutput = "";
                for (int i = 0; i < allwords.size() - 1; i++) {
                  allwordsOutput += allwords.get(i) + ", ";
                }
                allwordsOutput += allwords.get(allwords.size() - 1);
                System.out.println(allwordsOutput);
              }
            }
            break;
          case "S": // S size
            checkCommandArgumentsCount(commands, 1, "[S]");
            System.out.println(dictbst.size());
            break;
          case "H": // H height
            checkCommandArgumentsCount(commands, 1, "[H]");
            System.out.println(dictbst.height());
            break;
          default:
            System.out.println("WARNING: Unrecognized command");
        }
      } catch (ParseException e) {
        // display which line had the problem
        System.out.println(e.getMessage());
      }

      // prompt command again after action
      printMenu();
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
    System.out.println("============================== END ===================================");
  }

}
