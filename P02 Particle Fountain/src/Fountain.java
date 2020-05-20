/**
 * @author Travis Cao
 */

import java.util.Random;

public class Fountain {

  // declare random number generator
  private static Random randGen = new Random();

  // declare particles array
  private static Particle[] particles;

  // define size of the particles array
  private static final int PARTICLE_ARRAY_SIZE = 800;

  // define centered position of new particles generated
  private static int positionX = 400; // middle of the screen (left to right)
  private static int positionY = 300; // middle of the screen (top to bottom)

  /**
   * Creates a specified amount of new Particle objects in the available memory provided in
   * particles array (will stop creating new objects either when enough objects have been created,
   * or there is no more space in the particles array for new objects).
   * 
   * @param numberOfNewNeeded number of new Particle objects intended to be created
   */
  private static void createNewParticles(int numberOfNewNeeded) {
    // methods within Utility class cannot be called before Utility.runApplication(), hence we
    // initiate the startColor and endColor variables as local instead of private static
    int startColor = Utility.color(23, 141, 235); // blue
    int endColor = Utility.color(23, 200, 255); // lighter blue

    int numberOfNewCreated = 0;
    for (int i = 0; i < particles.length; i++) {
      if (numberOfNewCreated < numberOfNewNeeded) {
        if (particles[i] == null) { // only creates object at null-referenced memory in the
                                    // particles array
          particles[i] = new Particle();

          // X position is initialized to be +/- 3 of positionX
          particles[i].setPositionX((-3f + positionX) + randGen.nextFloat() * 6f);

          // Y position is initialized to be +/- 3 of positionY
          particles[i].setPositionY((-3f + positionY) + randGen.nextFloat() * 6f);

          // size is initialized to be between 4 and 11
          particles[i].setSize(4f + randGen.nextFloat() * (11f - 4f));

          // color is initialized to be between startColor and endColor
          particles[i].setColor(Utility.lerpColor(startColor, endColor, randGen.nextFloat()));

          // X velocity is initialized to be between -1 and 1
          particles[i].setVelocityX(-1f + randGen.nextFloat() * (1f + 1f));

          // Y velocity is initialized to be between -10 and -5
          particles[i].setVelocityY(-10f + randGen.nextFloat() * (-5f + 10f));

          // age of each particle is initialized to be between 0 and 40 (upper bound inclusive)
          particles[i].setAge(randGen.nextInt(41)); // int bound is 41 since 41 is excluded per
                                                    // Random.nextInt(int bound) method

          // transparency of each particle is initialized to be between 32 and 127 (upper inclusive)
          particles[i].setTransparency(32 + randGen.nextInt(127 + 1 - 32)); // added 1 so that 127
                                                                            // can be generated

          numberOfNewCreated++;
        }
      } else {
        break;
      }
    }
  }

  /**
   * Updates the Particle object at index in the particles array (if, at index, the Particle object
   * is not null-referenced) with gravitational effect to make Particle moves similar to water drops
   * in a fountain.
   * 
   * @param index index in the particles array needed to be updated with the gravitational effect
   */
  private static void updateParticle(int index) {
    if (particles[index] != null) {
      // increases Particle's age by 1; deployed to later free up memory in the particles array so
      // to create an everlasting fountain effect
      particles[index].setAge(particles[index].getAge() + 1);

      // gradually increases Y velocity to create the gravitational effect
      particles[index].setVelocityY(particles[index].getVelocityY() + 0.3f);

      // updates Particle's X and Y position
      particles[index]
          .setPositionX(particles[index].getPositionX() + particles[index].getVelocityX());
      particles[index]
          .setPositionY(particles[index].getPositionY() + particles[index].getVelocityY());

      // draws circle at certain X-Y position, with its specified size, color, and transparency
      Utility.circle(particles[index].getPositionX(), particles[index].getPositionY(),
          particles[index].getSize());
      Utility.fill(particles[index].getColor(), particles[index].getTransparency());
    }
    removeOldParticles(80); // remove Particle of age greater than 80 to free up memory for new
                            // Particle, therefore creating an everlasting fountain effect
  }

  /**
   * Sets Particle objects to null if they have exceed a specified age.
   * 
   * @param maxAge maximum age of the Particle object to not be set to null
   */
  private static void removeOldParticles(int maxAge) {
    for (int i = 0; i < particles.length; i++) {
      if (particles[i] != null) {
        if (particles[i].getAge() > maxAge) {
          particles[i] = null;
        }
      }
    }
  }

  /**
   * Called by Utility.runApplication() to obtain the X and Y position of mouse click while Fountain
   * program is running, and move the position of fountain particles array to the clicked X-Y
   * position.
   * 
   * @param xClickPosition X position of mouse click
   * @param yClickPosition Y position of mouse click
   */
  public static void mousePressed(int xClickPosition, int yClickPosition) {
    positionX = xClickPosition;
    positionY = yClickPosition;
  }

  /**
   * Called by Utility.runApplication() to obtain keystroke while Fountain program is running, and
   * create a screenshot named "screenshot.png" in program directory when the 'p' key is pressed.
   * 
   * @param key reads which key has been pressed
   */
  public static void keyPressed(char key) {
    if (key == 'p') {
      Utility.save("screenshot.png");
    }
  }

  /**
   * Called by Utility.runApplication() to set up the Fountain program. Will run tests of the
   * program before creating the particles fountain.
   */
  public static void setup() {
    // perform tests
    if (testUpdateParticle() == false || testRemoveOldParticles() == false
        || testCreateNewParticles() == false) {
      System.out.println("FAILED");
    }

    // start actual fountain program setup
    particles = new Particle[PARTICLE_ARRAY_SIZE];
  }

  /**
   * Called by Utility.runApplication() repeatedly before Fountain program termination.
   */
  public static void update() {
    Utility.background(Utility.color(235, 213, 186)); // sets background color

    createNewParticles(10); // creates 10 new particles every time update() is called by
                            // Utility.runApplication()

    for (int i = 0; i < particles.length; i++) {
      updateParticle(i); // updates all particles with the gravitational effect and removes old
                         // particles to create an everlasting fountain effect
    }
  }

  /**
   * Main method to initiate the Fountain program.
   * 
   * @param args arguments used to run the main Fountain program
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  // Below are tests implemented for the Fountain class
  /**
   * Creates a single particle at position (3, 3) with velocity (-1, -2). Then checks whether
   * calling updateParticle() on this particle's index correctly results in changing its position to
   * (2, 1.3).
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateParticle() {
    boolean testPassed = false; // boolean local variable evaluated to true if this test passed,
                                // false otherwise

    // creates particles array with one element that has pre-specified positions and velocities
    particles = new Particle[1];
    particles[0] = new Particle();
    particles[0].setPositionX(3f);
    particles[0].setPositionY(3f);
    particles[0].setVelocityX(-1f);
    particles[0].setVelocityY(-2f);

    updateParticle(0);

    // checks that the updateParticle(0) method actually updates the Particle object at index 0 with
    // correct attributes
    if (particles[0].getPositionX() == 2f && particles[0].getPositionY() == 1.3f) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Calls removeOldParticles(6) on an array with three particles (two of which have ages over six
   * and another that does not). Then checks whether the old particles were removed and the young
   * particle was left alone.
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldParticles() {
    boolean testPassed = false; // boolean local variable evaluated to true if this test passed,
                                // false otherwise

    // generates a particles array with 3 elements, among which 2 has age greater than the threshold
    // specified by removeOldParticles(6)
    particles = new Particle[3];
    particles[0] = new Particle();
    particles[0].setAge(7);

    particles[1] = new Particle();
    particles[1].setAge(3);

    particles[2] = new Particle();
    particles[2].setAge(10);

    removeOldParticles(6);

    // checks that the removeOldParticles(6) method sets the right memory to null
    if (particles[0] == null && particles[1] != null && particles[2] == null) {
      testPassed = true;
    }

    return testPassed;
  }

  /**
   * Initiates a particles array of length 4, where index 3 already containing a Particle object.
   * Then, calls createNewParticles(5) method to check that, within particles array, only index 0,
   * 1, 2 now reference to new Particle objects, and parameters in particles[3] remain unchanged.
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testCreateNewParticles() {
    boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
                               // false otherwise

    particles = new Particle[4];

    // initialize a Particle object at index 3
    particles[3] = new Particle();
    particles[3].setAge(100);
    particles[3].setPositionX(10f);

    createNewParticles(5);

    for (int i = 0; i < particles.length; i++) {
      if (i != 3) {
        if (particles[i] == null) {
          testPassed = false;
          break;
        } else {
          if (particles[i].getAge() < 0 || particles[i].getAge() > 40
              || particles[i].getPositionX() < (positionX - 3f)
              || particles[i].getPositionX() > (positionX + 3f)) { // use age and X position as
                                                                   // checking criteria
            testPassed = false;
            break;
          }
        }
      } else {
        if (particles[i] == null) {
          testPassed = false;
          break;
        } else {
          if (particles[i].getAge() != 100 || particles[i].getPositionX() != 10f) {
            testPassed = false;
            break;
          }
        }
      }
    }

    return testPassed;
  }

}
