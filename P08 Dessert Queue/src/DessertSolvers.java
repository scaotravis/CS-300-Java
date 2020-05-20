
/**
 * Solves two dessert serving problems using ServingQueue
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

    return queue.peek();
  }

  /**
   * Solve to whom the dessert should be first served, when there are multiple courses of meal
   * involved. Dessert is always served at the last round, so whoever first gets the dessert will be
   * the person who got served last in the second to last course of meal. In this question, assume
   * the number of guests to skip between two servings is always 1
   * 
   * @param numberOfGuests Total number of guests arrived at the dinner party
   * @param coursesServed  Number of meal courses to serve
   * @return The Guest who will first get the dessert served
   */
  public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {
    if (numberOfGuests <= 0) {
      throw new IllegalArgumentException("Number of guests is not positive.");
    }
    if (coursesServed <= 0) {
      throw new IllegalArgumentException("Number of meal courses is not positive.");
    }

    ServingQueue queue = new ServingQueue(numberOfGuests);

    // create a queue of guests based on their arrival
    Guest.resetNextGuestIndex();
    for (int i = 0; i < numberOfGuests; i++) {
      queue.add(new Guest());
    }

    // if there is only one course of meal, then this one course is dessert
    if (coursesServed == 1) {
      return queue.remove();
    } else {
      // otherwise, store guests in the order served in an alternative ServingQueue, and replace
      // this alternative one with the main one when a course of meal has been served
      ServingQueue altQueue = new ServingQueue(numberOfGuests);
      for (int i = 0; i < coursesServed - 1; i++) {
        int totalServed = 0; // how many in total during this one course have been served
        while (totalServed != numberOfGuests - 1) {
          altQueue.add(queue.remove());
          totalServed++;
          queue.add(queue.remove()); // skip one guest between two servings
        }
        altQueue.add(queue.remove()); // add the last served guest in this course to the alt queue

        // since the last person will be first served in the next course, we need to shift all but
        // the last added element in altQueue to make the last added person first to be removed
        for (int j = 0; j < numberOfGuests - 1; j++) {
          altQueue.add(altQueue.remove());
        }

        queue = altQueue; // after one course of meal, replace the main queue ref to the alt one
        altQueue = new ServingQueue(numberOfGuests); // clear out the alt queue to start next course
      }
      return queue.peek();
    }
  }

}
