
/**
 * Models the serving of dessert queue
 * 
 * @author Travis Cao
 */
public class DessertSolvers {

  /**
   * Solve to whom the dessert should be first served, which is the same person who got last served
   * for meals. The meal serving starts with the first arrival, then skip guestsSkipped people, and
   * serve the arrival right after the skipped one
   * 
   * @param numberOfGuests Total number of guests arrived at the dinner party
   * @param guestsSkipped  Number of guests to skip in between two meal servings
   * @return The Guest who will first get the dessert served
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {
    if (numberOfGuests <= 0) {
      throw new IllegalArgumentException("Number of guests is not positive.");
    }
    if (guestsSkipped < 0) {
      throw new IllegalArgumentException("Number of guests skipped is not non-negative.");
    }

    ServingQueue queue = new ServingQueue(numberOfGuests);

    // create a queue of guests based on their arrival
    Guest.resetNextGuestIndex();
    for (int i = 0; i < numberOfGuests; i++) {
      queue.add(new Guest());
    }

    // remove guests from the queue if they have been served, otherwise remove them and then add it
    // back to the queue
    int totalServed = 0; // how many in total have been served
    while (totalServed != numberOfGuests - 1) {
      queue.remove();
      totalServed++;

      for (int i = 0; i < guestsSkipped; i++) {
        queue.add(queue.remove());
      }
    }

    return queue.remove();
  }

  public static void main(String[] args) {

  }

}
