import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Main Driver of the Escape Room game
 * 
 * @author Travis Cao
 */
public class EscapeRoom extends PApplet {

  private PImage backgroundImage;
  private ArrayList<Thing> allThings;

  @Override
  public void settings() {
    size(800, 600);
  }

  @Override
  public void setup() {
    Thing.setProcessing(this);
    allThings = new ArrayList<Thing>();
    backgroundImage = super.loadImage("images/computerCenter.png");
  }

  @Override
  public void draw() {
    super.image(backgroundImage, 0f, 0f);

    // Calls update method on every element in allThings, then acts on each returned Action object
    // that is non-null
    for (int i = 0; i < allThings.size(); i++) {
      Action action = allThings.get(i).update();
      if (action != null) {
        action.act();
      }
    }
  }

  /**
   * Main method for EscapeRoom class
   * 
   * @param args Arguments in the main method
   */
  public static void main(String[] args) {
    PApplet.main("EscapeRoom");
  }

}
