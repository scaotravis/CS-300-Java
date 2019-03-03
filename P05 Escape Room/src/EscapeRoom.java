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

  /*
   * (non-Javadoc)
   * 
   * @see processing.core.PApplet#settings()
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /*
   * (non-Javadoc)
   * 
   * @see processing.core.PApplet#setup()
   */
  @Override
  public void setup() {
    Thing.setProcessing(this);
    allThings = new ArrayList<Thing>();
    backgroundImage = super.loadImage("images/computerCenter.png");

    allThings.add(new ClickableThing("koala", 350, 65, new Action("What a cute stuffed koala!")));
    allThings.add(new VisibleThing("chest", 590, 310));
    allThings.add(new DragAndDroppableThing("key", 250, 170, (VisibleThing) allThings.get(1),
        new Action("Open sesame!")));
  }

  /*
   * (non-Javadoc)
   * 
   * @see processing.core.PApplet#draw()
   */
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
