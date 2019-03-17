//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P07 Iterating to Philosophy
// Files: "TestDriver.java", "EvenNumbers.java", "InfiniteIterator.java",
// "FiniteIterator.java", "Generator.java", "NextWikiLink.java"
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

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Obtains the first link on the current Wikipedia page as the next page
 * 
 * @author Travis Cao
 */
public class NextWikiLink implements Function<String, String> {

  /**
   * Returns the first available Wikipedia page link on the current page
   * 
   * @param t Current Wikipedia page (from which we obtain the first link available on the page)
   * @return The first available Wikipedia page link on the current page
   */
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }

  /**
   * Main method to implement the Wikipedia crawling application
   * 
   * @param args Arguments of the main driver to reach the next Wikipedia page
   */
  public static void main(String[] args) {
    // Implement your own Wikipedia crawling application here.
    // 1. prompt the user to enter a topic name and number of iterations to follow
    Scanner scnr = new Scanner(System.in);
    System.out.print("Enter a wikipedia page topic: ");
    String input = scnr.nextLine();
    System.out.print("Enter the number of pages you'd like to step through: ");
    int lengthOfIteration = scnr.nextInt();
    scnr.close();

    // 2. prepend "/wiki/" to the user's input, and replace spaces with underscores
    input = input.replaceAll("\\s+", "_");
    input = "/wiki/" + input;

    // 3. use a for-each loop to iterate through the number of links requested
    Generator<String> gen = new Generator<>(input, new NextWikiLink(), lengthOfIteration);
    for (String output : gen) {
      System.out.println(output);
      if (output.contains("FAILED to find ")) {
        break;
      }
    }
  }

}
