
/**
 * Models the serving of dessert queue
 * 
 * @author Travis Cao
 */
public class DessertSolvers {

  /**
   * Solve to whom the dessert should be first served, which is the same person who got last served
   * for meals. The meal serving starts with the first arrival, then skip guestsSkipped people, and
   * serve the arrival right after
   * 
   * @param numberOfGuests Total number of guests arrived at the dinner party
   * @param guestsSkipped  Number of guests to skip in between two meal serving rounds
   * @return The Guest who will first get the dessert served (i.e. who got served meal the last)
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {
    if (numberOfGuests <= 0) {
      throw new IllegalArgumentException("Number of guests is not positive.");
    }
    if (guestsSkipped < 0) {
      throw new IllegalArgumentException("Number of guests skipped is not non-negative.");
    }

    Guest[] arrivalQueue = new Guest[numberOfGuests];
    Guest.resetNextGuestIndex();
    for (int i = 0; i < numberOfGuests; i++) {
      arrivalQueue[i] = new Guest();
    }

    int indexToServe = 0; // which guest to serve next
    int totalServed = 0; // how many in total have been served
    int skipCounter = 0; // how many times have we skipped guests

    while (totalServed != numberOfGuests - 1) {
      arrivalQueue[indexToServe] = null;
      totalServed++;

      skipCounter = 0;
      while (skipCounter != guestsSkipped + 1) { // skip till reaching the next person to serve
        indexToServe = (indexToServe + 1) % numberOfGuests;
        if (arrivalQueue[indexToServe] != null) {
          skipCounter++;
        }
      }
    }

    return arrivalQueue[indexToServe];
  }

  public static void main(String[] args) {

  }

}
