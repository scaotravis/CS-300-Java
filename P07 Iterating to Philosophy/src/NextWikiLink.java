
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
